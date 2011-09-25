(ns ga-dojo.core)

;; comments look like this

(defn random-bit
  "add doc-string here"
  []
  "1"
  )

(defn random-gene
  "add comment here"
  []
  (repeat 4 (random-bit))
  )

(defn generate-chromosome 
  "We havne't really written this yet"
  [number]
    (repeat number (random-gene))
  )

(defn generate-population [size]
  (repeat size (generate-chromosome 9)))

(defn mutate-bit [bit] (if (< (Math/random) 0.5) (if (= "1" bit) "0" "1") bit))

(defn mutate-gene [gene] (map mutate-bit gene))

(defn mutate-chromosome [chromosome] (map mutate-gene chromosome))

(defn mutate-population [population] (map mutate-chromosome population))

(defn evaluate-chromosome [chromosome]
  1)

(defn decode-gene [gene]
    ({'("0" "0" "0" "0") 0
      '("0" "0" "0" "1") 1
      '("0" "0" "1" "0") 2
      '("0" "0" "1" "1") 3
      '("0" "1" "0" "0") 4
      '("0" "1" "0" "1") 5
      '("0" "1" "1" "0") 6
      '("0" "1" "1" "1") 7
      '("1" "0" "0" "0") 8
      '("1" "0" "0" "1") 9
      '("1" "0" "1" "0") +
      '("1" "0" "1" "1") -
      '("1" "1" "0" "0") *
      '("1" "1" "0" "1") /
      '("1" "1" "1" "0") nil
      '("1" "1" "1" "1") nil}
      gene))


(defn decode-reversed-chromosome [chromosome] 
  (if (= (count chromosome) 1)
    (first chromosome)
    (list (second chromosome) (decode-reversed-chromosome (nnext chromosome)) (first chromosome))))

(defn decode-chromosome [chromosome]
  (decode-reversed-chromosome (reverse (map decode-gene chromosome))))


(defn find-answer-for 
  "Run a genetic algorith searching for expressions that result in the number"
  [number]
  (let [population (generate-population 100)]
  (println population) 
  ))
