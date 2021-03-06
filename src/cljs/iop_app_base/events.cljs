(ns iop-app-base.events
  (:require [secretary.core :as secretary]
            [re-frame.core :as re-frame]
            [iop-app-base.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
  ::login
  (fn [db]
    (do (secretary/dispatch! "/home"))
    (assoc db :logged-in true)))

(re-frame/reg-event-db
 ::toggle-sidebar
 (fn [db]
   (assoc db :sidebar-open (not (:sidebar-open db)))))

(re-frame/reg-event-db
 ::set-sidebar-status
 (fn [db [_ bool]]
   (assoc db :sidebar-open bool)))
