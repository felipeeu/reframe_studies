(ns reframe-studies.views
  (:require
   [re-frame.core :as re-frame]
   [reframe-studies.subs :as subs]
   [reframe-studies.events :as events]
   [re-fill.routing :as routing]))

;--------Dog APP -----------


(defn list-breeds [breed sub-breed]
  [:div
   {:key breed}
   [:p   breed]
   (map-indexed (fn [idx item] [:span {:key idx} (str " "  item)]) sub-breed)])

(defn dog-panel []
  (let [result (re-frame/subscribe [::subs/success-http-result])]
    (re-frame/dispatch [::events/handler-with-http])
    [:div
     [:h1 "Dogs"]
     [:div (map  #(list-breeds  %1 %2) (keys @result) (vals @result))]]))



;-----Color APP -------


(defn button [label color event code]
  [:button   {:style {:backgroundColor color} :onClick #(re-frame/dispatch [event code])}  label])

(defn color-painel [red green blue]
  [:div  {:style {:backgroundColor (str "rgba"  "(" red  "," green "," blue ")") :width "20px" :height "20px"}}])

(defn color-panel []
  (let [name (re-frame/subscribe [::subs/name])
        red (re-frame/subscribe [::subs/red-code])
        green (re-frame/subscribe [::subs/green-code])
        blue (re-frame/subscribe [::subs/blue-code])]

    [:div
     [:h1 "Hello from " @name]
     (button "R" "red" ::events/change-red-code @red)
     (button "G" "green" ::events/change-green-code @green)
     (button "B" "blue" ::events/change-blue-code @blue)
     (button "reset" "pink" ::events/reset-code 0) [:button {:onClick #(re-frame/dispatch [::events/handler-with-http])} "fetch"]
     (button "fetch" "gray" ::events/handler-with-http "hound")

     (color-painel @red @green @blue)
     [:h1 @red]
     [:h1 @green]
     [:h1 @blue]]))

;---------Main -------
(defn main-panel [views]
  [:div [:p "My Apps in reframe-cljs"]
   [:button "color mix"]
   [:button "dog breeds"]
   [routing/routed-view views]])

(defn loading-view []
  [:div "Loading "])

