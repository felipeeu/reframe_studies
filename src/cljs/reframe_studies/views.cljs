(ns reframe-studies.views
  (:require
   [re-frame.core :as re-frame]
   [reframe-studies.subs :as subs]
   [reframe-studies.events :as events]))

(defn button [label color event code]
  [:button   {:style {:backgroundColor color} :onClick #(re-frame/dispatch [event code])}  label])

(defn color-painel [red green blue]
  [:div  {:style {:backgroundColor (str "rgba"  "(" red  "," green "," blue ")" ) :width "20px" :height "20px"}}])
  


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        red (re-frame/subscribe [::subs/red-code])
        green (re-frame/subscribe [::subs/green-code])
        blue (re-frame/subscribe [::subs/blue-code])]

    [:div
     [:h1 "Hello from " @name]
     (button "R" "red" ::events/change-red-code @red)
     (button "G" "green" ::events/change-green-code @green)
     (button "B" "blue" ::events/change-blue-code @blue)
     (button "reset" "pink" ::events/reset-code 0)
     (color-painel @red @green @blue)
     [:h1 @red]
     [:h1 @green]
     [:h1 @blue]]))
