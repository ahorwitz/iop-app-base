(ns iop-app-base.views
  (:require [re-frame.core :as re-frame]
            [iop-app-base.events :as events]
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
  [:div {:class-name "login-form"}
   [sa/Grid {:text-align "center" :vertical-align "middle" :style { :height "100%"}}
    [sa/GridColumn {:style {:max-width 450}}
      [sa/Header {:as "h2" :color "teal" :text-align "center"}
        [sa/Image {:src "https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAViAAAAJDU3ZmQwN2Q1LWQyNGUtNDIyNS04NGJhLWJhMmM1ODllY2E5Yg.png"}]
        "Welcome"] 
      [sa/Form {:size "large"}
        [sa/Segment {:stacked true}
          [sa/FormInput {:fluid true :icon "user" :icon-position "left" :placeholder "email address"}]
          [sa/FormInput {:fluid true :icon "lock" :icon-position "left" :placeholder "password" :type "password"}]
          [sa/Button {:color "teal" :fluid true :size "large" :on-click #(re-frame/dispatch [::events/login []])}
           "Login"]]]
      [sa/Message [:a {:href "#"} "Contact us"] " if you're having trouble logging in"]]]])
      
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
