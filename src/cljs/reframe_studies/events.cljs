(ns reframe-studies.events
  (:require
   [re-frame.core :as re-frame]
   [reframe-studies.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
