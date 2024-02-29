(ns electric-starter-app.main
  (:require electric-starter-app.main-page
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.router :as r]))

;; Saving this file will automatically recompile and update in your browser

(e/defn Main [ring-request]
        (e/client
          (binding [dom/node js/document.body]
            (r/router (r/HTML5-History.)
                      (dom/text "This is main page of Example 7")
                      (e/server (electric-starter-app.main-page/Main. nil)))
            )))