(ns reframe-studies.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [reframe-studies.events :as events]
   [reframe-studies.views :as views]
   [reframe-studies.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(def views-to-route {:routes/home views/main-panel
                     :routes/dog views/dog-panel
                     :routes/color views/color-panel
            ;; Value of :else will be used if there's no mapping for route
                     :else views/loading-view})

(def routes ["/" {"home" :routes/home
                  "color" :routes/color
                  "dog" :routes/dog}])

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (re-frame/dispatch [:re-fill/init-routing routes])
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel views-to-route] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch [::events/handler-with-http])
  (dev-setup)
  (mount-root))
