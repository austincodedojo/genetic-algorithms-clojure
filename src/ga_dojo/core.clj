(ns ga-dojo.core)

(defn separate [symbols]
  (defn classify [symbol]
    (if (number? symbol) :digits :operators))
  (group-by classify symbols))

(defn accumulate-expression 
  ([symbols accumulate] 
    (let [separated-symbols (separate symbols)
          digits (separated-symbols :digits)
          operators (separated-symbols :operators)]
      (accumulate-expression (rest digits) operators (first digits) accumulate)))
  ([digits operators result accumulate]
    (if (or (empty? digits) (empty? operators)) 
      result
      (recur (rest digits) (rest operators) (accumulate (first operators) (list (first digits) result)) accumulate))))

(defn evaluate-expression [symbols]
  (accumulate-expression symbols apply))

(defn format-expression [symbols]
  (defn clojure-op-to-symbol [operator]
    ({ + '+ - '- * '* / '/} operator))
  (accumulate-expression 
    symbols 
    (fn [operator operands]
			 (let [lhs (second operands)
			       rhs (first operands)
			       op (clojure-op-to-symbol operator)]
			 (if (coll? lhs)
			   (conj lhs op rhs)
			   [lhs op rhs])))))
  
          
      
      
