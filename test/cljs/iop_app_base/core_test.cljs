(ns iop-app-base.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [iop-app-base.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
