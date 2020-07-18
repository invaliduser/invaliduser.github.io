(ns site.core
  (:require [rum.core :as rum]
            [site.pages.front :as front]))



(rum/mount (front/main) (js/document.querySelector "#app"))
