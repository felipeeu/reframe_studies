(ns reframe-studies.events
  (:require
   [re-frame.core :as re-frame]
   [reframe-studies.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::change-red-code
 (fn [db code]
   (assoc db :red-code (+ 8 (get code 1)))))

(re-frame/reg-event-db
 ::change-green-code
 (fn [db code]
   (assoc db :green-code (+ 8 (get code 1)))))

(re-frame/reg-event-db
 ::change-blue-code
 (fn [db code]
   (assoc db :blue-code (+ 8 (get code 1)))))


(re-frame/reg-event-db
 ::reset-code
 (fn [db code]
   (assoc db :red-code (get code 1) :green-code (get code 1) :blue-code (get code 1))))
