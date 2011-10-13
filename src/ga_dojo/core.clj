(ns ga-dojo.core)

(defn evaluate-expression [symbols]
  (defn classify [symbol]
    (if (number? symbol) :digits :operators))
  (defn separate [symbols]
    (group-by classify symbols))
  (defn legalize-expression [symbols]
    (defn drop-trailing-operators [expression]
      (reverse (drop-while #(= :operators (classify %)) (reverse expression))))
    (let [separated-symbols (separate symbols)
          legalish-expression (cons
                                (first (separated-symbols :digits))
                                (interleave (separated-symbols :operators) (next(separated-symbols :digits))))]
      legalish-expression))
  (defn infix-to-prefix [expression] 
    (if (<= (count expression) 2)
      (first expression)
      (list (second expression) (infix-to-prefix (nnext expression)) (first expression)))) 
  (eval (infix-to-prefix (reverse (legalize-expression symbols)))))
          
      
      
