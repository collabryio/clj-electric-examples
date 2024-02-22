(ns electric-starter-app.main-page
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.router :as r]
            [electric-starter-app.register-form]))

;;Pages
(e/defn Goto-Register-Page []
        (e/client (dom/br))
        (e/server
          (electric-starter-app.register-form/RenderForm.)))

(e/defn Goto-Admin-Page []
        (e/client (dom/h1 (dom/text "Admin Page"))))
(e/defn Main [ring-request]
        ;;Router try
        (e/client
          (binding [dom/node js/document.body]
            (dom/h1 (dom/text "MAIN MENU "))
            ;;NAVBAR
            (dom/div (dom/text "Nav: ")
                     (r/link ['.. [::register-page]] (dom/text "Register Page")) (dom/text " ")
                     (r/link ['.. [::admin-page]] (dom/text "Admin Page")))

            (let [[page x :as route] (ffirst r/route)]
              (if-not page
                (r/Navigate!. [[::register-page]])
                (r/focus [route]
                         (case page
                           ::register-page (e/server (Goto-Register-Page.))
                           ::admin-page (e/server (Goto-Admin-Page.))
                           (e/client (dom/text "no matching route: " (pr-str page))))))))))