(ns re-frame-name-game.views
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :refer [subscribe dispatch]]))

; helper for finding item in collection
(defn in? 
  "true if coll contains elm"
  [coll elm]  
  (some #(= elm %) coll))

(defn employee-item
  []
  (fn [{:keys [name url]} active selected]
    [:li.employee
      [:img {:src url
             :on-click #(dispatch [:guess name])}]]))

(defn employee-list
  []
  (fn [employees active-employee guesses]
    (let [active-employee @active-employee
          guesses @guesses]
      [:ul.employee-list
        (for [employee @employees]
          ^{:key (:name employee)} [employee-item employee 
                                                  (= active-employee (:name employee))
                                                  (in? guesses (:name employee))])])))

(defn correct-message
  []
  (rand-nth ["Good Job!" "Booyah!" "Success!" "You Win!" "Awesome!" "Radical!" "Excellent!"]))

(defn incorrect-message
  []
  (rand-nth ["Wrong!" "Nope!" "LOL Nope!" "You Lose!" "NO!" "Try Again!" "No Way!" "Oh Please" "You Joking?!" "Whatever"]))

(defn guess-message
  [correct guess]
  [:div.guess-message {:class (and correct "correct") 
                       :style {:transform (str "rotate(" ((rand-nth [+ -]) 0 (rand 33)) "deg)")}
                       :key guess}
                      (if correct (correct-message) (incorrect-message))])

(defn name-game []
  (let [employees (subscribe [:employees])
        active-employees (reaction (take 5 @employees))
        active-employee (reaction (:name (rand-nth @active-employees)))
        guesses (subscribe [:guesses])]
    (fn []
      [:div.container
        [:h1 "THE NAME GAME"]

        (when-not (empty? @employees)
          [:div
            [employee-list active-employees active-employee guesses]
            [:h2.active-employee (str "Who is " @active-employee "?")]])

        (when-not (empty? @guesses)
          (let [correct (= @active-employee (last @guesses))]
            (when correct
              (js/setTimeout #(dispatch [:shuffle-employees]) 2000))
            [guess-message correct (last @guesses)]))])))
