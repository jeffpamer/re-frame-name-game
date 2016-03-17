(ns re-frame-name-game.db)

(def default-db
  {:employees [{:name "Andrew Harris" :url "http://willowtreeapps.com/wp-content/uploads/2014/12/headshot_andrew_harris1.jpg"} 
               {:name "Abby Cook" :url "http://willowtreeapps.com/wp-content/uploads/2015/11/headshot_abby_cook.jpg"}
               {:name "Alborz Mesbahi" :url "http://willowtreeapps.com/wp-content/uploads/2015/11/headshot_alborz_mesbahi.jpg"}
               {:name "Alex Shafran" :url "http://willowtreeapps.com/wp-content/uploads/2014/12/headshot_alex_shafran.jpg"} 
               {:name "Andrew Morgan" :url "http://willowtreeapps.com/wp-content/uploads/2015/07/headshot_andrew_morgan.jpg"}
               {:name "Andrew Carter" :url "http://willowtreeapps.com/wp-content/uploads/2014/12/headshot_andrew_carter.jpg"}
               {:name "Angela Batton" :url "http://willowtreeapps.com/wp-content/uploads/2014/12/headshot_angela_batton.jpg"}
               {:name "Anne Frye" :url "http://willowtreeapps.com/wp-content/uploads/2015/11/headshot_anne_frye.jpg"}
               {:name "Ashby Bowles" :url "http://willowtreeapps.com/wp-content/uploads/2014/12/headshot_ashby_bowles.jpg"}
               {:name "Austen Lux" :url "http://willowtreeapps.com/wp-content/uploads/2014/03/headshot_austen_lux.jpg"}]
   :guesses []
   :active-employee "Andrew Harris"})
  
