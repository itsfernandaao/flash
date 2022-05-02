(ns flash-video.core
  (:gen-class))

(def db-accounts (atom '({:id 0 :name "fefa"})))
(def db-movies '())
(def db-rents '())

(defn bye-bye
  []
  (println "bye bye"))

(defn header
  []
  (println "############################")
  (println "# FLASH VIDEO LOCADORA S/A #")
  (println "############################"))

(defn menu-options
  []
  (println "[1] To make a rent")
  (println "[2] To access admin area")
  (println "[0] To close the app")
  (println "option: ")
  (read-line))

(defn menu-admin
  []
  (println "ACCOUNTS")
  (println "[1.1] list accounts")
  (println "[1.2] add account")
  (println "[1.3] search account")
;;  (println "MOVIES")
;;  (println "[2.1] list movies")
;;  (println "[2.2] add movie")
;;  (println "[2.3] search movie")
;;  (println "RENTS")
;;  (println "[3.1] list rents")
;;  (println "[3.2] search rent")
  (println)
  (println "[0] to return")
  (println "option: ")
  (read-line))

(defn rent-a-video [] (println ".:RENT A VIDEO HERE:."))

(defn list-accounts
  []
  (println @db-accounts))

(defn ask
  [attribute]
  (println attribute ": ")
  (read-line))

(defn new-account
  [id]
  (let [name (ask "name")]
    {:id id
     :name name}))

(defn add-account
  []
  (swap! db-accounts conj (new-account (count @db-accounts))))

(defn search-account
  []
  (let [q (ask "term")]
    (println (filter #(or (= (:name %) q) (= (str (:id %)) q)) @db-accounts)))
  )
(defn go-to-admin
  []
  (loop [option (menu-admin)]
    (cond
      (= option "1.1") (list-accounts)
      (= option "1.2") (add-account)
      (= option "1.3") (search-account)
      :else (println "please choose wisely"))
    (if (not= option "0")
      (recur (menu-admin)))))

(defn menu []
  (header)
  (loop [option (menu-options)]
    (cond
      (= option "1") (rent-a-video)
      (= option "2") (go-to-admin)
      (= option "0") (println "BYE")
      :else (println "please choose wisely"))
    (if (not= option "0")
      (recur (menu-options)))))

(defn -main
  []
  (menu))

