(ns liberator1.service.db)

;;
;; A simple in-memory database for testing purpose.
;; 

(def database (atom {}))

;;
;; User management
;; 

(defn get-user
  "Returns the user corresponding to the given username."
  [username]
  (@database username))

(defn add-user
  "Add a new user to database."
  [{:keys [username] :as user}]
  (when (and username
             (not (get-user username)))
    (swap! database assoc (:username user) user)))
