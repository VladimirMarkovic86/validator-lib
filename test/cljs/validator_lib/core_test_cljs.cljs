(ns validator-lib.core-test-cljs
  (:require [js-lib.core :as md]
            [clojure.test :refer-macros [deftest is testing]]
            [validator-lib.core :refer [generate-validation-message validate-input
                                        custom-validator validate-field]]))

(deftest test-generate-validation-message
  (testing "Test generate validation message"
    
    (let [element nil
          validator-message nil
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (nil?
          element)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "type"
                 "text")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           48)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "type"
                 "number")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           49)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "type"
                 "radio")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           50)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "select")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "type"
                 "select-one")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           51)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "type"
                 "file")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           65)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "type"
                 "checkbox")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           47)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "type"
                 "text")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (aset
                 element
                 "value"
                 "asd")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (string?
          @validator-message)
       )
      
      (is
        (empty?
          @validator-message)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "type"
                 "email")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (aset
                 element
                 "value"
                 "asd")
          validator-message (atom "")
          evt-p nil]
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           52)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "type"
                 "number")
          void (.setAttribute
                 element
                 "max"
                 "10")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "value"
                 "100")
          validator-message (atom "")
          evt-p nil]
      
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           56)
       )
      
     )
    
    (let [element (.createElement
                    js/document
                    "input")
          void (.setAttribute
                 element
                 "type"
                 "number")
          void (.setAttribute
                 element
                 "min"
                 "10")
          void (.setAttribute
                 element
                 "required"
                 "required")
          void (.setAttribute
                 element
                 "value"
                 "1")
          validator-message (atom "")
          evt-p nil]
      
      
      (generate-validation-message
        element
        validator-message
        evt-p)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (not
          (nil?
            @validator-message))
       )
      
      (is
        (= @validator-message
           55)
       )
      
     )
    
   ))

(deftest test-validate-input
  (testing "Test validate input"
    
    (let [evt-p nil
          element nil
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (nil?
          element)
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "text")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "48")
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "number")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "49")
       )
      
     )
    
    (let [selection-items-el (.createElement
                               js/document
                               "div")
          void (.setAttribute
                 selection-items-el
                 "class"
                 "selection-items")
          input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "radio")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 selection-items-el
                 input-el)
          void (.appendChild
                 element-container
                 selection-items-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "50")
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "select")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "select-one")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "51")
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "file")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "65")
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "checkbox")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "47")
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "text")
          void (aset
                 input-el
                 "value"
                 "asd")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (string?
          (aget
            span-element
            "innerHTML"))
       )
      
      (is
        (empty?
          (aget
            span-element
            "innerHTML"))
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "email")
          void (aset
                 input-el
                 "value"
                 "asd")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "52")
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "number")
          void (.setAttribute
                 input-el
                 "max"
                 "10")
          void (aset
                 input-el
                 "value"
                 "100")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "56")
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "number")
          void (.setAttribute
                 input-el
                 "min"
                 "10")
          void (aset
                 input-el
                 "value"
                 "1")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          evt-p nil
          element input-el
          event nil]
      
      (validate-input
        evt-p
        element
        event)
      
      (is
        (md/html?
          element)
       )
      
      (is
        (= (aget
             span-element
             "innerHTML")
           "55")
       )
      
     )
    
   ))

(deftest test-custom-validator
  (testing "Test custom validator"
    
    (let [input-element nil
          validator-predicate nil
          custom-validator-message nil
          validator-message-a nil
          result (custom-validator
                   input-element
                   validator-predicate
                   custom-validator-message
                   validator-message-a)]
      
      (is
        (nil?
          result)
       )
      
     )
    
    (let [input-element (.createElement
                          js/document
                          "input")
          void (.setAttribute
                 input-element
                 "type"
                 "text")
          void (.setAttribute
                 input-element
                 "required"
                 "required")
          validator-predicate nil
          custom-validator-message "custom validato message"
          validator-message-a (atom nil)]
      
      (custom-validator
        input-element
        validator-predicate
        custom-validator-message
        validator-message-a)
      
      (is
        (= (aget
             input-element
             "validationMessage")
           "Please fill out this field.")
       )
      
     )
    
    (let [input-element (.createElement
                          js/document
                          "input")
          void (.setAttribute
                 input-element
                 "type"
                 "text")
          void (.setAttribute
                 input-element
                 "required"
                 "required")
          validator-predicate true
          custom-validator-message "custom validato message"
          validator-message-a (atom nil)]
      
      (custom-validator
        input-element
        validator-predicate
        custom-validator-message
        validator-message-a)
      
      (is
        (= (aget
             input-element
             "validationMessage")
           custom-validator-message)
       )
      
     )
    
   ))

(deftest test-validate-field
  (testing "Test validate field"
    
    (let [input-element nil
          is-valid nil
          custom-validator-message nil
          validator-predicate nil
          result (validate-field
                   input-element
                   is-valid
                   custom-validator-message
                   validator-predicate)]
      
      (is
        (nil?
          result)
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "text")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          input-element input-el
          is-valid (atom true)
          custom-validator-message nil
          validator-predicate nil]
      
      (validate-field
        input-element
        is-valid
        custom-validator-message
        validator-predicate)
      
      (is
        (false?
          @is-valid)
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "text")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          input-element input-el
          is-valid (atom true)
          custom-validator-message "custom validator message"
          validator-predicate true]
      
      (validate-field
        input-element
        is-valid
        custom-validator-message
        validator-predicate)
      
      (is
        (false?
          @is-valid)
       )
      
      (is
        (= (aget
             input-element
             "validationMessage")
           custom-validator-message)
       )
      
     )
    
    (let [input-el (.createElement
                     js/document
                     "input")
          void (.setAttribute
                 input-el
                 "required"
                 "required")
          void (.setAttribute
                 input-el
                 "type"
                 "text")
          void (aset
                 input-el
                 "value"
                 "asd")
          span-element (.createElement
                         js/document
                         "span")
          element-container (.createElement
                              js/document
                              "div")
          void (.appendChild
                 element-container
                 input-el)
          void (.appendChild
                 element-container
                 span-element)
          input-element input-el
          is-valid (atom true)
          custom-validator-message nil
          validator-predicate nil]
      
      (validate-field
        input-element
        is-valid
        custom-validator-message
        validator-predicate)
      
      (is
        (true?
          @is-valid)
       )
      
     )
    
   ))

