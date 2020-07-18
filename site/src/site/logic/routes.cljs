(ns site.logic.routes
  (:require [reitit.frontend.easy :as rfe]
            [reitit.frontend :as rf]
            [site.pages.front :as front]
            [site.pages.clojure-intro :refer [finished-tutorial]]))

(def page-atom (atom front/splash))

(def routes
  [#_["/index.html"
    {:name ::def
     :view finished-tutorial}]
   ["/"
    {:name ::frontpage
     :view site.pages.front/splash}]

   ["/clj-tutorial"
    {:name ::clj-tutorial
     :view finished-tutorial}]

   #_["/item/:id"
    {:name ::item
     :view item-page
     :parameters {:path {:id int?}
                  :query {(ds/opt :foo) keyword?}}}]])



(defn init! []
  (rfe/start!
    (rf/router routes)
    (fn [m]
      (println "THIS IS THE m:" m)
      (reset! page-atom (or m front/splash)))

    ;; set to false to enable HistoryAPI
    {:use-fragment false}))
