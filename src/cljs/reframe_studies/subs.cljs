(ns reframe-studies.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::red-code
 (fn [db]
   (:red-code db)))

(re-frame/reg-sub
 ::green-code
 (fn [db]
   (:green-code db)))

(re-frame/reg-sub
 ::blue-code
 (fn [db]
   (:blue-code db)))

(re-frame/reg-sub
 ::success-http-result
 (fn [db]
   (:success-http-result db)))

(re-frame/reg-sub
 ::failure-http-result
 (fn [db]
   (:failure-http-result db)))

(re-frame/reg-sub
 ::route
 (fn [db]
   (:route db)))