(ns reframe-studies.events
  (:require
   [re-frame.core :as re-frame]
   [ajax.core :as ajax]
   [day8.re-frame.http-fx]
   [reframe-studies.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::change-red-code
 (fn [db [_ code]]
   (assoc db :red-code (+ 8 code))))

(re-frame/reg-event-db
 ::change-green-code
 (fn [db [_ code]]
   (assoc db :green-code (+ 8 code))))

(re-frame/reg-event-db
 ::change-blue-code
 (fn [db [_ code]]
   (assoc db :blue-code (+ 8 code))))

(re-frame/reg-event-db
 ::reset-code
 (fn [db [_ code]]
   (assoc db :red-code code :green-code code :blue-code code)))

(re-frame/reg-event-fx                             ;; note the trailing -fx
 ::handler-with-http                     ;; usage:  (dispatch [:handler-with-http])
 (fn [{:keys [db]} _]                 ;; the first param will be "world"
   {:db   (assoc db :show-twirly true)   ;; causes the twirly-waiting-dialog to show??
    :http-xhrio {:method          :get
                 :uri             "https://dog.ceo/api/breeds/list/all"
                 :timeout         8000                                           ;; optional see API docs
                 :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                 :on-success     [::success-http-result]
                 :on-failure     [::failure-http-result]}}))

(re-frame/reg-event-db
 ::success-http-result
 (fn [db [_ result]]
   (assoc db :success-http-result (:message result))))

(re-frame/reg-event-db
 ::failure-http-result
 (fn [db [_ result]]
   (println "failure : " result)
    ;; result is a map containing details of the failure
   (assoc db :failure-http-result result)))

(re-frame/reg-event-db
 ::change-route
 (fn [db [_ route]]
   (assoc db :route route)))
