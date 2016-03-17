(ns re-frame-name-game.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
  :employees
  (fn [db]
    (reaction (:employees @db))))

(re-frame/register-sub
  :guesses
  (fn [db]
    (reaction (:guesses @db))))

