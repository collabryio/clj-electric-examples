(ns electric-starter-app.main
  #?(:cljs (:require-macros [electric-starter-app.main :refer [with-reagent]]))
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            #?(:cljs [reagent.core :as r])
            #?(:cljs [reagent.dom :as rdom])
            #?(:cljs ["react" :as react])
            #?(:cljs ["react-dom/client" :as ReactDom])
            #?(:cljs ["react-data-table-component$default" :as DataTable])))



;; Saving this file will automatically recompile and update in your browser

#?(:cljs (defn create-root
           "See https://reactjs.org/docs/react-dom-client.html#createroot"
           ([node] (create-root node (str (gensym))))
           ([node id-prefix]
            (ReactDom/createRoot node #js {:identifierPrefix id-prefix}))))


#?(:cljs (defn render [root & args]
           (.render root (r/as-element (into [:f>] args)))))

(defmacro with-reagent [& args]
  `(dom/div
     (let [root# (create-root dom/node)]
       (render root# ~@args)
       (e/on-unmount #(.unmount root#)))))

#?(:cljs (defn hiccup-test []
           "Raw Hiccup code"
           [:div
            [:p "This is hiccup!"]]))
#?(:cljs (defn table-test []
           [:> DataTable {:onRowClicked (fn [^js v] (apply (.-log js/console) [v]))
                          :columns [{:name :test1 :selector (fn [^js row] (.-test1 row))}
                                    {:name :test2 :selector (fn [^js row] (.-test2 row))}]
                          :data        (clj->js [{:test1 "1", :test2 "2"} {:test1 "3", :test2 "4"}])}]))



(e/defn Main [ring-request]
        (e/client
          (binding [dom/node js/document.body]
            (dom/h1 (dom/text "Hello from Electric Clojure"))
            (with-reagent hiccup-test)
            (dom/h2 (dom/text "DataTable Example"))
            (with-reagent table-test))))