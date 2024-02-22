(ns electric-starter-app.admin-form
  (:require [clojure.string :as str]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]
            [electric-starter-app.register-form :refer [!user-db]]))

(e/defn RenderForm []
        (e/client
          (dom/h4 (dom/text "User list displayed as => Username | Password | Email"))
          (e/for-by identity [current-item (e/watch !user-db)]
                    (dom/li (dom/text
                              (get current-item :username)
                              " | "
                              (get current-item :password)
                              " | "
                              (get current-item :email))))))