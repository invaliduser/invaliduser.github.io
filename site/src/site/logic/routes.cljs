(ns site.logic.routes
  (:require [reitit.frontend.easy :as rfe]
            [reitit.frontend :as rf]
            [site.core]
            [site.pages.clojure-intro :refer [finished-tutorial]]))



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
    (fn [m] (reset! site.core/holder m))
    ;; set to false to enable HistoryAPI
    {:use-fragment true}))

(init!)
