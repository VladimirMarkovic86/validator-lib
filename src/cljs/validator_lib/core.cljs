(ns validator-lib.core
  (:require [language-lib.core :refer [get-label]]
            [js-lib.core :as md]))

(defn generate-validation-message
  "Generate validation message"
  [element
   validator-message
   & [{bad-input :bad-input
       custom-error :custom-error
       pattern-mismatch :pattern-mismatch
       range-overflow :range-overflow
       range-underflow :range-underflow
       step-mismatch :step-mismatch
       too-long :too-long
       too-short :too-short
       type-mismatch :type-mismatch
       value-missing :value-missing}]]
  (when (and element
             (md/html?
               element)
             validator-message
             (instance?
               Atom
               validator-message))
    (let [validity (aget
                     element
                     "validity")
          element-type (aget
                         element
                         "type")]
      (when (aget
              validity
              "badInput")
        (reset!
          validator-message
          (get-label
            38))
        (when bad-input
          (reset!
            validator-message
            bad-input))
       )
      (when (aget
              validity
              "customError")
        (reset!
          validator-message
          "")
        (when custom-error
          (reset!
            validator-message
            custom-error))
       )
      (when (aget
              validity
              "patternMismatch")
        (when-not (contains?
                    #{"text"
                      "textarea"
                      "email"
                      "password"}
                    element-type)
          (reset!
            validator-message
            (get-label
              40))
         )
        (when (contains?
                #{"text"
                  "textarea"
                  "email"
                  "password"}
                element-type)
          (reset!
            validator-message
            (get-label
              57))
         )
        (when pattern-mismatch
          (reset!
            validator-message
            pattern-mismatch))
       )
      (when (aget
              validity
              "rangeOverflow")
        (let [max-value (aget
                          element
                          "max")]
          (when-not (contains?
                      #{"number"}
                      element-type)
            (reset!
              validator-message
              (get-label
                41))
           )
          (when (contains?
                  #{"number"}
                  element-type)
            (reset!
              validator-message
              (get-label
                56
                nil
                max-value))
           ))
        (when range-overflow
          (reset!
            validator-message
            range-overflow))
       )
      (when (aget
              validity
              "rangeUnderflow")
        (let [min-value (aget
                          element
                          "min")]
          (when-not (contains?
                      #{"number"}
                      element-type)
            (reset!
              validator-message
              (get-label
                42))
           )
          (when (contains?
                  #{"number"}
                  element-type)
            (reset!
              validator-message
              (get-label
                55
                nil
                min-value))
           ))
        (when range-underflow
          (reset!
            validator-message
            range-underflow))
       )
      (when (aget
              validity
              "stepMismatch")
        (when-not (contains?
                    #{"number"}
                    element-type)
          (reset!
            validator-message
            (get-label
              43))
         )
        (when (contains?
                #{"number"}
                element-type)
          (let [validation-message (aget
                                     element
                                     "validationMessage")
                validation-message (.substring
                                     validation-message
                                     62
                                     (count
                                       validation-message))
                [from-value
                 to-value] (.split
                             validation-message
                             " and ")
                 to-value (.substring
                            to-value
                            0
                            (dec
                              (count
                                to-value))
                           )]
            (reset!
              validator-message
              (get-label
                54
                nil
                [from-value
                 to-value]))
           ))
        (when step-mismatch
          (reset!
            validator-message
            step-mismatch))
       )
      (when (aget
              validity
              "tooLong")
        (let [text-length (aget
                            element
                            "textLength")
              max-length (aget
                           element
                           "maxLength")]
          (reset!
            validator-message
            (get-label
              44))
         )
        (when too-long
          (reset!
            validator-message
            too-long))
       )
      (when (aget
              validity
              "tooShort")
        (let [text-length (aget
                            element
                            "textLength")
              min-length (aget
                           element
                           "minLength")]
          (when-not (contains?
                      #{"text"
                        "textarea"
                        "email"
                        "password"}
                      element-type)
            (reset!
              validator-message
              (get-label
                45))
           )
          (when (contains?
                  #{"text"
                    "textarea"
                    "email"
                    "password"}
                  element-type)
            (reset!
              validator-message
              (get-label
                53
                nil
                [min-length
                 text-length]))
           ))
        (when too-short
          (reset!
            validator-message
            too-short))
       )
      (when (aget
              validity
              "typeMismatch")
        (when-not (contains?
                    #{"email"}
                    element-type)
          (reset!
            validator-message
            (get-label
              46))
         )
        (when (contains?
                #{"email"}
                element-type)
          (reset!
            validator-message
            (get-label
              52))
         )
        (when type-mismatch
          (reset!
            validator-message
            type-mismatch))
       )
      (when (aget
              validity
              "valueMissing")
        (when-not (contains?
                    #{"text"
                      "textarea"
                      "email"
                      "password"
                      "date"
                      "number"
                      "radio"
                      "select-one"
                      "select-multiple"
                      "file"}
                    element-type)
          (reset!
            validator-message
            (get-label
              47))
         )
        (when (contains?
                #{"text"
                  "textarea"
                  "email"
                  "password"
                  "date"}
                element-type)
          (reset!
            validator-message
            (get-label
              48))
         )
        (when (contains?
                #{"number"}
                element-type)
          (reset!
            validator-message
            (get-label
              49))
         )
        (when (contains?
                #{"radio"}
                element-type)
          (reset!
            validator-message
            (get-label
              50))
         )
        (when (contains?
                #{"select-one"
                  "select-multiple"}
                element-type)
          (reset!
            validator-message
            (get-label
              51))
         )
        (when (contains?
                #{"file"}
                element-type)
          (reset!
            validator-message
            (get-label
              65))
         )
        (when value-missing
          (reset!
            validator-message
            value-missing))
       ))
   ))

(defn validate-input
  "Validate input that happened on field"
  [evt-p
   element
   event]
  (when (and element
             (md/html?
               element))
    (let [element-type (aget
                         element
                         "type")
          span-element (atom nil)]
      (if (= element-type
             "radio")
        (let [selection-items (.closest
                                element
                                ".selection-items")
              element-name (aget
                             element
                             "name")
              radio-buttons (md/query-selector-all-on-element
                              ".entity"
                              (str
                                "input[name='"
                                element-name
                                "']"))]
          (doseq [radio-button radio-buttons]
            (.setCustomValidity
              radio-button
              ""))
          (reset!
            span-element
            (aget
              selection-items
              "nextElementSibling"))
         )
        (do
          (reset!
            span-element
            (aget
              element
              "nextElementSibling"))
          (.setCustomValidity
            element
            ""))
       )
      (md/remove-class
        (.closest
          @span-element
          "label")
        "error")
      (aset
        @span-element
        "innerHTML"
        ""))
    (let [element-type (aget
                         element
                         "type")
          validity (aget
                     element
                     "validity")
          validator-message (atom "")]
      (generate-validation-message
        element
        validator-message
        evt-p)
      (when-not (aget
                  validity
                  "valid")
        (.setCustomValidity
          element
          @validator-message)
        (let [span-element (atom nil)]
          (if (= element-type
                 "radio")
            (let [selection-items (.closest
                                    element
                                    ".selection-items")]
              (reset!
                span-element
                (aget
                  selection-items
                  "nextElementSibling"))
             )
            (reset!
              span-element
              (aget
                element
                "nextElementSibling"))
           )
          (aset
            @span-element
            "innerHTML"
            @validator-message)
          (md/add-class
            (.closest
              @span-element
              "label")
            "error"))
       ))
   ))

(defn custom-validator
  "Validates field with custom validator"
  [input-element
   validator-predicate
   custom-validator-message
   validator-message-a]
  (when (and input-element
             (md/html?
               input-element)
             validator-message-a
             (instance?
               Atom
               validator-message-a))
    (reset!
      validator-message-a
      (if validator-predicate
        custom-validator-message
        ""))
    (.setCustomValidity
      input-element
      @validator-message-a))
 )

(defn validate-field
  "Read validation attributes of particular fields"
  [input-element
   is-valid
   & [custom-validator-message
      validator-predicate]]
  (when (and input-element
             (md/html?
               input-element)
             (instance?
               Atom
               is-valid))
    (try
      (let [validity (aget
                       input-element
                       "validity")
            validator-message-a (atom "")]
        (if custom-validator-message
          (custom-validator
            input-element
            validator-predicate
            custom-validator-message
            validator-message-a)
          (generate-validation-message
            input-element
            validator-message-a))
        (when-not (aget
                    validity
                    "valid")
          (.setCustomValidity
            input-element
            @validator-message-a)
          (let [span-element (atom nil)]
            (if (= (aget
                     input-element
                     "type")
                   "radio")
              (let [selection-items (.closest
                                      input-element
                                      ".selection-items")]
                (reset!
                  span-element
                  (aget
                    selection-items
                    "nextElementSibling"))
               )
              (reset!
                span-element
                (aget
                  input-element
                  "nextElementSibling"))
             )
            (aset
              @span-element
              "innerHTML"
              @validator-message-a)
            (md/add-class
              (.closest
                @span-element
                "label")
              "error"))
         )
        (when (aget
                validity
                "valid")
          (validate-input
            nil
            input-element
            nil))
        (swap!
          is-valid
          (fn [a-val
               new-val]
            (and a-val
                 new-val))
          (aget
            validity
            "valid"))
       )
      (catch js/Error e
        (.error
          js/console
          (aget
            e
            "message"))
       ))
   ))

