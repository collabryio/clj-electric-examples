(ns electric-starter-app.router-example
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.router :as r]))

;;Pages
(e/defn Home []
        (e/client (dom/h1 (dom/text "Home page"))))

(e/defn AboutUs []
        (e/client (dom/h1 (dom/text "About Us page"))))

(e/defn Contact []
        (e/client (dom/h1 (dom/text "Contact page"))))
(e/defn Main [ring-request]
        ;;Router try
        (e/client
          (binding [dom/node js/document.body]
            (dom/h1 (dom/text "ROUTER EXAMPLE "))
            ;;NAVBAR
            (dom/div (dom/text "Nav: ")
                     (r/link ['.. [::home]] (dom/text "home")) (dom/text " ")
                     (r/link ['.. [::about-us]] (dom/text "about us")) (dom/text " ")
                     (r/link ['.. [::contact]] (dom/text "contact")))

            (let [[page x :as route] (ffirst r/route)]
              (if-not page
                (r/Navigate!. [[::home]])
                (r/focus [route]
                         (case page
                           ::home (e/server (Home.))
                           ::about-us (e/server (AboutUs.))
                           ::contact (e/server (Contact.))
                           (e/client (dom/text "no matching route: " (pr-str page))))))))))