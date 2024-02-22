(ns electric-starter-app.register-form
  (:require [clojure.string :as str]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]))

(def user-details (atom {:username  "",
                         :password  "",
                          :email  ""}))
(defn update-user-details [new-username new-password new-email]
  (swap! user-details assoc :username new-username)
  (swap! user-details assoc :password new-password)
  (swap! user-details assoc :email new-email))


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
            (ui4/button (e/fn [] (update-user-details (deref !temp-username) (deref !temp-password) (deref !temp-email)))
                        (dom/text "Register")))

          )))
