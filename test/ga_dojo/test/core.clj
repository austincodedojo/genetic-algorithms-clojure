(ns ga-dojo.test.core
  (:use [ga-dojo.core])
  (:use [clojure.test]))

(deftest test-evaluate-expression
  (testing "Expression evaluation\n"
           (testing "Some valid expressions"
                    (is (= 1 (evaluate-expression [1])))
                    (is (= 2 (evaluate-expression [2])))
                    (is (= 7 (evaluate-expression [3 + 4])))
                    (is (= 8 (evaluate-expression [3 + 4 + 1])))
                    (is (= 12 (evaluate-expression [1 + 2 * 4])))
)))

(deftest test-format-expression
  (testing "Expression formatting\n"
           (testing "Some valid expressions\n"
                    (is (= 1 (format-expression [1])))
                    (is (= 2 (format-expression [2])))
                    (is (= '(3 + 4) (format-expression [3 + 4])))
                    (is (= '(1 + 2 * 4) (format-expression [1 + 2 * 4])))
)))


  
