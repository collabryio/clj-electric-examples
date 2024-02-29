(ns dbcreate.dbsetup
  (:require [datomic.client.api :as d]))


  (def client (d/client {:server-type :dev-local
                         :system      "ci"}))

  (d/create-database client {:db-name "db"})

  (def conn (d/connect client {:db-name "db"}))

(def schema [{:db/ident :form/username
                  :db/valueType :db.type/string
                  :db/unique :db.unique/identity
                  :db/cardinality :db.cardinality/one}
                  {:db/ident :form/email
                    :db/valueType :db.type/string
                    :db/unique :db.unique/identity
                    :db/cardinality :db.cardinality/one}
                  {:db/ident :form/password
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data schema})

(d/transact conn {:tx-data [{:form/username "fxdeniz"
                             :form/email "deniz@example.com"
                             :form/password "passwrod123"}]})

(d/q '[:find (pull ?e [*])
                :where [?e :form/username _]] (d/db conn))




