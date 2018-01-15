(ns iop-app-base.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [iop-app-base.core-test]))

(doo-tests 'iop-app-base.core-test)
