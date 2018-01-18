(ns iop-app-base.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [secretary.core :as secretary]
            [goog.events :as gevents]
            [goog.history.EventType :as EventType]
            [re-frame.core :as re-frame]
            [iop-app-base.events :as events]
            [iop-app-base.subs :as subs]))



(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; Just for updating URL after logging in
;; Might be better way to do this through secretary
(defn set-hash
  [route]
  (set! (.-hash (.-location js/document)) route))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (let [logged-in (re-frame/subscribe [::subs/logged-in])]
      (if @logged-in (do (set-hash "#home")
                         (secretary/dispatch! "/home"))
                     (do (set-hash "#login")
                         (secretary/dispatch! "/login")))))

  (defroute "/home" []
    (set-hash "#home")
    (re-frame/dispatch [::events/set-active-panel :home-panel]))

  (defroute "/about" []
    (re-frame/dispatch [::events/set-active-panel :about-panel]))

  (defroute "/login" []
    (re-frame/dispatch [::events/set-active-panel :login-panel]))

  (defroute "/browse-recipes" []
    (re-frame/dispatch [::events/set-sidebar-status false])
    (re-frame/dispatch [::events/set-active-panel :login-panel]))


;; Quick and dirty history configuration.
  (let [h (History.)]
    (goog.events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
    (doto h (.setEnabled true)))

  ;; --------------------
  (hook-browser-navigation!))
