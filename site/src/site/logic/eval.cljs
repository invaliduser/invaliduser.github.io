(ns site.logic.eval
  (:require   [rum.core :as rum]
              [cljs.tools.reader :refer [read-string]]
              [cljs.js :refer [empty-state eval js-eval]]
              [cljs.env :refer [*compiler*]]
              [cljs.pprint :refer [pprint]]
              [clojure.string :as cljstr]))


(defn eval-form-from-string! [s]
  (eval (empty-state)
        (read-string s)
        {:eval       js-eval
         :source-map true
         :context    :expr}
        (fn [result] result)))
(def effs! eval-form-from-string!)


; want to do a couple things---create atoms, set init state
#_(defn settable-local [kws]
  {:init (fn [state props]
           
           (for [k kws]
             (assoc state k (atom v))))}); i think i want to assoc from (state :rum/args) to atoms in state
                                        ;but  i want to know what I'm getting in props




(rum/defc parit < rum/reactive [code]
  [:div
   [:textarea {:value (rum/react code)  :on-change #(reset! code (.. % -target -value))}]
   [:button {:type "button" :on-click #(effs! @code)} "Eval"]])

(rum/defcs visible-parit < (rum/local nil :result) rum/reactive [state code]
  (let [{:keys [result]} state]
    [:div  {:style {:width "50%"}}
     [:div {:style {:display :flex}}
      [:div {:style {:flex 1}}
       [:textarea {:style {:width "100%"}
                   :value (rum/react code)
                   :on-change #(reset! code (.. % -target -value))}]
       [:div {:style {:display "flex" :justify-content "center" :align-items "center"}}

        [:button {:on-click #(reset! result (effs! @code))} "Eval"]]]
      [:div {:style {:flex 1}}
       [:div [:span  (let [s (str (:value (rum/react result)))]
                       (if (cljstr/blank? s)
                         ""
                         (str "=> " s)))]]]]]))


