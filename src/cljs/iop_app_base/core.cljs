(ns iop-app-base.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [iop-app-base.events :as events]
            [iop-app-base.routes :as routes]
            [iop-app-base.views :as views]
            [iop-app-base.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  ;;; TODO ensure this can be switched with next line
  (re-frame/dispatch-sync [::events/initialize-db])
  (routes/app-routes)
  (dev-setup)
  (mount-root))
