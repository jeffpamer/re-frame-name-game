(ns re-frame-name-game.handlers
    (:require [re-frame.core :as re-frame]
              [re-frame-name-game.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
  :update-employees
  (fn [db [_ employees]]
    (merge db employees)))

(re-frame/register-handler
  :shuffle-employees
  (fn [db]
    (merge db {:employees (shuffle (:employees db))}
              {:guesses []})))

(re-frame/register-handler
  :guess
  (fn [db [_ guess]]
    (assoc db :guesses (conj (:guesses db) guess))))
