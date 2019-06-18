(ns validator-lib.test-runner
  (:require [validator-lib.core-test-cljs]
            [doo.runner :refer-macros [doo-tests doo-all-tests]]))

(enable-console-print!)

(doo-tests
  'validator-lib.core-test-cljs)

