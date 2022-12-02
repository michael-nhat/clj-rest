(ns liberator1.api.user
  (:require [compojure.core :refer [defroutes ANY]]
            [liberator.core :refer [defresource]]
            [liberator1.service.security :refer [authenticated? admin? current-user]]
            [liberator1.service.json :refer [clj->json]]))

(declare user)
(declare user-by-username)
(declare request)

(defresource user
  [request]
  :allowed-methods [:get :put]
  :available-media-types ["application/json"]
  :authorized? #(authenticated? (:request %))
  :allowed? (fn [context]
              (let [request (:request context)
                    method (:request-method request)]
                (if (= :put method)
                  (admin? request)
                  true)))
  :handle-ok #(clj->json (current-user (:request %)))
  :put! (fn [_] (println "New user")))

(defresource user-by-username
  :available-media-types ["application/json"]
  :authorized? #(authenticated? (:request %))
  :handle-ok #(clj->json {:user {:username (get-in % [:request :params :username])}}))

(defroutes user-routes
  (ANY "/user" request (user request))
  (ANY "/user/:username" request (user-by-username request)))
