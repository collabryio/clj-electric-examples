(ns electric-starter-app.register-form
  (:require [clojure.string :as str]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]
            #?(:clj [datomic.client.api :as dt])))

(def !last-added-user (atom []))

(e/def conn)
(e/def db)

(e/defn add-new-user [new-username new-password new-email]
        (e/server
          (binding [conn @(requiring-resolve 'dev/datomic-conn)]
            (binding [db (dt/db conn)]
              (dt/transact conn  {:tx-data
                                  [{:form/username new-username
                                    :form/password new-password
                                    :form/email new-email}]})))))


(e/defn RenderForm []
        (e/client
          (let [!temp-username (atom ""), !temp-password (atom ""), !temp-email (atom "")]
          (dom/div
            (dom/text "Username: ")
            (ui4/input (e/watch !temp-username)
                       (e/fn [input-username] (reset! !temp-username input-username))))

          (dom/br)

          (dom/div
            (dom/text "Password: ")
            (ui4/input (e/watch !temp-password)
                       (e/fn [input-password] (reset! !temp-password input-password))))

          (dom/br)

          (dom/div
            (dom/text "Email: ")
            (ui4/input (e/watch !temp-email)
                       (e/fn [input-email] (reset! !temp-email input-email))))

          (dom/br)

          (dom/div
            (ui4/button (e/fn [] (add-new-user.
                                   (deref !temp-username)
                                   (deref !temp-password)
                                   (deref !temp-email)))
                        (dom/text "Register")))
          (dom/br)

          (dom/div (let [{:keys [username email password]}
                         (last (e/watch !last-added-user))]
                     (dom/text "Last added user is: "
                               username " with password: "
                               password " and has email: "
                               email)))
          )))
