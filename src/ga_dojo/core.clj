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

(defn decode-gene-type [gene]
      ({'("0" "0" "0" "0") :number
      '("0" "0" "0" "1") :number
      '("0" "0" "1" "0") :number
      '("0" "0" "1" "1") :number
      '("0" "1" "0" "0") :number
      '("0" "1" "0" "1") :number
      '("0" "1" "1" "0") :number
      '("0" "1" "1" "1") :number
      '("1" "0" "0" "0") :number
      '("1" "0" "0" "1") :number
      '("1" "0" "1" "0") :op
      '("1" "0" "1" "1") :op
      '("1" "1" "0" "0") :op
      '("1" "1" "0" "1") :op
      '("1" "1" "1" "0") :invalid
      '("1" "1" "1" "1") :invalid}
      gene))


(defn decode-reversed-chromosome [chromosome] 
  (if (<= (count chromosome) 1)
    (first chromosome)
    (list (second chromosome) (decode-reversed-chromosome (nnext chromosome)) (first chromosome))))

(defn decode-chromosome [chromosome]
  (decode-reversed-chromosome (reverse (map decode-gene chromosome))))

(declare advance-to-next-number)
(declare advance-to-next-op)

(defn not-a-number [gene]
  (not (= :number (decode-gene-type gene))))

(defn not-an-op [gene]
  (not (= :op (decode-gene-type gene))))

(defn advance-to-next-number [chromosome]
  (cons advance-to-next-op (drop-while not-a-number chromosome)))

(defn advance-to-next-op [chromosome]
  (cons advance-to-next-number (drop-while not-an-op chromosome)))

(defn error-correct-chromosome-recursively [chromosome advance]
  (if (= (count chromosome) 0) 
    ()
    (let [advance-result (apply advance [chromosome])
          next-advance (first advance-result)
          advanced-chromosome (next advance-result)
         ]
      (cons (first advanced-chromosome) (error-correct-chromosome-recursively (next advanced-chromosome) next-advance)))))

(defn drop-trailing-operators [chromosome]
  (reverse (drop-while not-a-number (reverse chromosome))))

(defn error-correct-chromosome [chromosome]
  (let [corrected-chromosome (error-correct-chromosome-recursively chromosome advance-to-next-number)]
    (drop-trailing-operators corrected-chromosome)))

(defn find-answer-for 
  "Run a genetic algorith searching for expressions that result in the number"
  [number]
  (let [population (generate-population 100)]
  (println population) 
  ))
