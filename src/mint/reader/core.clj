(ns mint.reader.core (:require
                      [clojure.java.io :as io]
                      [clojure.string :as str]))

(def directory
  "A File object of our `/posts` folder"
  (io/file "./posts"))

(def files
  "A sequence of files inside our posts directory"
  (filter #(.isFile %) (file-seq directory)))

(defn- file-name
  "Accepts a File object and return its name"
  [file]
  (->> file
       .toPath
       .getFileName
       str))

(defn- raw-properties
  "Everything before the first empty line is considered a raw property"
  [reader]
  (take-while not-empty reader))

(defn- clean-raw-property
  "A raw property is a string with the following format `#+PROPERTY: name value`.
  If given the above value as input, this function will return [:name \"value\"]"
  [raw-property]
  (-> raw-property
      (str/split #":")
      second
      str/trim
      (str/split #" " 2)
      (update-in [0] keyword)))

(defn- build-properties
  "This functions take a reader and return a map mapping a property name to its value"
  [rdr]
  (->> (line-seq rdr)
       raw-properties
       (map clean-raw-property)
       (into {})))

(defn- read-properties [file]
  (with-open [rdr (io/reader file)]
    (-> rdr
        build-properties
        (assoc :id (file-name file)))))

(defmacro posts-index
  "A macro that returns a list of posts"
  []
  (mapv read-properties files))
