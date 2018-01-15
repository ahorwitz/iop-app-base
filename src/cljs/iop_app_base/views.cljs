(ns iop-app-base.views
  (:require [re-frame.core :as re-frame]
            [iop-app-base.subs :as subs]
            [reagent.core :as reagent]
            [soda-ash.core :as sa]))
            
;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [sa/Container {:class-name "app-container"}
      [sa/Header {:as "h1"} "Appy"]]))
    
;; about

(defn about-panel []
  [:div "This is the About Page."
   [:div [:a {:href "#/"} "go to Home Page"]]])

;; login-panel

(defn login-panel []
  [:div
   [sa/Grid
    {:text-align "center"
     :vertical-align "middle" }[sa/GridColumn "jlkjlkj"]]]) 
    
    
;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    :login-panel [login-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
