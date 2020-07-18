(ns site.logic.routes
  (:require [reitit.frontend.easy :as rfe]
            [reitit.frontend :as rf]
            [site.pages.front :as front]
            [site.pages.clojure-intro :refer [finished-tutorial]]))

(def holder (atom front/splash))

(def routes
  [["/"
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
    (fn [m] (reset! holder m))

    ;; set to false to enable HistoryAPI
    {:use-fragment false}))
