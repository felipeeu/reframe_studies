(ns reframe-studies.views
  (:require
   [re-frame.core :as re-frame]
   [reframe-studies.subs :as subs]
   [reframe-studies.events :as events]))

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