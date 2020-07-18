(ns site.logic.routes
  (:require [reitit.frontend.easy :as rfe]
            [reitit.frontend :as rf]
            [site.pages.front :as front]
            [site.pages.clojure-intro :refer [finished-tutorial]]
            [site.utils]))

(def page-atom (atom front/splash))

(def routes
  [["/index.html"
    {:name ::def
     :view finished-tutorial #_site.utils/test-page}]
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
      (reset! page-atom (or m front/splash)))

    ;; set to false to enable HistoryAPI
    #_{:use-fragment false}

    {:use-fragment (if (= js/window.location.hostname"invaliduser.github.io")
                     false
                     true)}
    ))
