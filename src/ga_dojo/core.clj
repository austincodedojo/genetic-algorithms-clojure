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

(defn first-is-number? [chromosome]
  (number? (decode-gene (first chromosome))))

(declare error-correct-chromosome-expecting-number)
      
(defn error-correct-chromosome-expecting-op [chromosome]
  (condp = (decode-gene-type (first chromosome))
    :number (error-correct-chromosome-expecting-op (next chromosome))
    :op (cons (first chromosome) (error-correct-chromosome-expecting-number (next chromosome)))
    :invalid (error-correct-chromosome-expecting-op (next chromosome))
    nil))
;  (cond 
;        (< (count chromosome) 2) nil
;        (nil? (decode-gene (first chromosome))) (error-correct-chromosome-expecting-op (next chromosome))
;        (first-is-number? chromosome) (error-correct-chromosome-expecting-op (next chromosome))
;        (= 0 (count (error-correct-chromosome-expecting-number (next chromosome)))) nil
;    :default (cons (first chromosome) (error-correct-chromosome-expecting-number (next chromosome)))))


;(defn error-correct-chromosome-expecting-op-and-number [chromosome]
;  (let [new-chromosome (error-correct-chromosome-expecting-op chromosome)]
;    (if (> 1 (count new-chromosome)) new-chromosome
;      nil)))

(defn error-correct-chromosome-expecting-number [chromosome]
  (condp = (decode-gene-type (first chromosome))
    :number (cons (first chromosome) (error-correct-chromosome-expecting-op (next chromosome)))
    :op (error-correct-chromosome-expecting-number (next chromosome))
    :invalid (error-correct-chromosome-expecting-number (next chromosome))
    nil))

(defn error-correct-chromosome [chromosome]
  (error-correct-chromosome-expecting-number chromosome))

(defn find-answer-for 
  "Run a genetic algorith searching for expressions that result in the number"
  [number]
  (let [population (generate-population 100)]
  (println population) 
  ))
