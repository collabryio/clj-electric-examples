(ns electric-starter-app.admin-form
  (:require [clojure.string :as str]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]
            #?(:clj [datomic.client.api :as dt])))

(e/def conn)
(e/def db)

(e/defn QueryAllUsers []
        (e/server
          (vec
            (flatten
                  (dt/q '[:find (pull ?e [*]) :where [?e :form/username _]] db)))))

(e/defn RenderForm []
        (e/server
          (binding [conn @(requiring-resolve 'dev/datomic-conn)]
            (binding [db (dt/db conn)]

              (e/client
                (dom/h4 (dom/text "User list displayed as => Username | Password | Email"))
                (dom/div
                  (e/for-by identity [current-item (QueryAllUsers.)]
                      (dom/li
                          (dom/text
                            (get current-item :form/username)
                            " | "
                            (get current-item :form/email)
                            " | "
                            (get current-item :form/password))))))))))