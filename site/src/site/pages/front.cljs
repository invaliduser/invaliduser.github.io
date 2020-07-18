(ns site.pages.front
  (:require [rum.core :as rum]
            [site.utils :refer [mth]]
            [site.logic.eval :refer [parit]]
            ))

  (mth "Thought I'd write a bit here")

(def links {:hn ["hn" "https://news.ycombinator.com/threads?id=invalidOrTaken"]
            :twitter ["twitter" "https://twitter.com/DanBell"]
            :github ["github" "https://github.com/invaliduser/"]
            :tao ["overwatch/sc2/hots" "https://calmongames.wordpress.com"]})

(def links-snippet
  [:div (for [[k [s a]] links]
          [:span {:key k} " " [:a {:href a} s]])])

(def byline 
  (mth "software guy (clojure).  likes: abstraction, clarity.  dislikes: local maxima, [Moloch](https://web.archive.org/web/20200618191519/https://slatestarcodex.com/2014/07/30/meditations-on-moloch/)"))







(rum/defc main []
  [:div links-snippet
   byline])
