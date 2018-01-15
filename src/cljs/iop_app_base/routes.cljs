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

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (let [logged-in (re-frame/subscribe [::subs/logged-in])]
      (if @logged-in (secretary/dispatch! "/home")
                     (secretary/dispatch! "/login"))))

  (defroute "/about" []
    (re-frame/dispatch [::events/set-active-panel :about-panel]))

  (defroute "/login" []
    (re-frame/dispatch [::events/set-active-panel :login-panel]))


  ;; --------------------
  (hook-browser-navigation!))
