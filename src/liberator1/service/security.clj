(ns liberator1.service.security
  (:require [liberator1.service.json :refer [clj->json]]))

(defn authenticated?
  "Sample authentication function."
  [request]
  true)

(defn admin?
  "Sample admin authorization function."
  [request]
  false)

(defn current-user
  "Get current user."
  [request]
  {:user {:username "foo"}})
