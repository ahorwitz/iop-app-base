(ns iop-app-base.views
  (:require [re-frame.core :as re-frame]
            [iop-app-base.events :as events]
            [iop-app-base.subs :as subs]
            [reagent.core :as reagent]
            [soda-ash.core :as sa]))

;; home

(defn home-panel []
  (let [sidebar-open (re-frame/subscribe [::subs/sidebar-open])]
    [:div
      [sa/Menu {:fixed "top" :inverted true :icon true}
        [sa/Button {:icon "bars" :color "black" :on-click #(re-frame/dispatch [::events/toggle-sidebar] [])}]
        [sa/Container
         [sa/MenuItem {:as "a"}
          [sa/Icon {:name "leaf" :style {:padding-right "20px"}}]
          "Internet of Plants"]
         [sa/MenuItem {:as "a"}
          "Home"]]]
      [sa/SidebarPushable :style {:box-shadow "0px"}
       [sa/Sidebar {:animation "slide along"
                    :visible @sidebar-open
                    :width "thin"
                    :style {:box-shadow "0px"}
                    :icon "labeled"}
           [sa/Menu {:fluid true :vertical true}
            [sa/MenuItem {:name "Dashboard" :link true :style { :margin-top "40px" :text-align "center"}}]
            [sa/MenuItem {:name "Register Device" :href "google.com" :style {:text-align "center"}}]
            [sa/MenuItem {:name "Browse Recipes" :href "#browse-recipes" :style {:text-align "center"}}]
            [sa/MenuItem {:name "Settings" :href "google.com" :style {:text-align "center"}}]]]
       [sa/SidebarPusher
         [sa/Segment {:basic true}
           [sa/Container {:style {:margin "100px"}}
            "..."]]]]]))


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
        "Internet of Plants"]
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
