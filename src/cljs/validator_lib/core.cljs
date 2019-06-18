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
    (let [validity (.-validity
                     element)
          element-type (.-type
                         element)]
      (when (.-badInput
              validity)
        (reset!
          validator-message
          (get-label 38))
        (when bad-input
          (reset!
            validator-message
            bad-input))
       )
      (when (.-customError
              validity)
        (reset!
          validator-message
          "")
        (when custom-error
          (reset!
            validator-message
            custom-error))
       )
      (when (.-patternMismatch
              validity)
        (when-not (contains?
                    #{"text"
                      "textarea"
                      "email"
                      "password"}
                    element-type)
          (reset!
            validator-message
            (get-label 40))
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
      (when (.-rangeOverflow
              validity)
        (let [max-value (.-max
                          element)]
          (when-not (contains?
                      #{"number"}
                      element-type)
            (reset!
              validator-message
              (get-label 41))
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
      (when (.-rangeUnderflow
              validity)
        (let [min-value (.-min
                          element)]
          (when-not (contains?
                      #{"number"}
                      element-type)
            (reset!
              validator-message
              (get-label 42))
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
      (when (.-stepMismatch
              validity)
        (when-not (contains?
                    #{"number"}
                    element-type)
          (reset!
            validator-message
            (get-label 43))
         )
        (when (contains?
                #{"number"}
                element-type)
          (let [validation-message (.-validationMessage
                                     element)
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
      (when (.-tooLong
              validity)
        (let [text-length (.-textLength
                            element)
              max-length (.-maxLength
                           element)]
          (reset!
            validator-message
            (get-label 44))
         )
        (when too-long
          (reset!
            validator-message
            too-long))
       )
      (when (.-tooShort
              validity)
        (let [text-length (.-textLength
                            element)
              min-length (.-minLength
                           element)]
          (when-not (contains?
                      #{"text"
                        "textarea"
                        "email"
                        "password"}
                      element-type)
            (reset!
              validator-message
              (get-label 45))
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
      (when (.-typeMismatch
              validity)
        (when-not (contains?
                    #{"email"}
                    element-type)
          (reset!
            validator-message
            (get-label 46))
         )
        (when (contains?
                #{"email"}
                element-type)
          (reset!
            validator-message
            (get-label 52))
         )
        (when type-mismatch
          (reset!
            validator-message
            type-mismatch))
       )
      (when (.-valueMissing
              validity)
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
            (get-label 47))
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
            (get-label 48))
         )
        (when (contains?
                #{"number"}
                element-type)
          (reset!
            validator-message
            (get-label 49))
         )
        (when (contains?
                #{"radio"}
                element-type)
          (reset!
            validator-message
            (get-label 50))
         )
        (when (contains?
                #{"select-one"
                  "select-multiple"}
                element-type)
          (reset!
            validator-message
            (get-label 51))
         )
        (when (contains?
                #{"file"}
                element-type)
          (reset!
            validator-message
            (get-label 65))
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
    (let [element-type (.-type
                         element)
          span-element (atom nil)]
      (if (= element-type
             "radio")
        (let [selection-items (.closest
                                element
                                ".selection-items")
              element-name (.-name
                             element)
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
            (.-nextElementSibling
              selection-items))
         )
        (do
          (reset!
            span-element
            (.-nextElementSibling
              element))
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
    (let [element-type (.-type
                         element)
          validity (.-validity
                     element)
          validator-message (atom "")]
      (generate-validation-message
        element
        validator-message
        evt-p)
      (when-not (.-valid
                  validity)
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
                (.-nextElementSibling
                  selection-items))
             )
            (reset!
              span-element
              (.-nextElementSibling
                element))
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
      (let [validity (.-validity
                       input-element)
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
        (when-not (.-valid
                    validity)
          (.setCustomValidity
            input-element
            @validator-message-a)
          (let [span-element (atom nil)]
            (if (= (.-type
                     input-element)
                   "radio")
              (let [selection-items (.closest
                                      input-element
                                      ".selection-items")]
                (reset!
                  span-element
                  (.-nextElementSibling
                    selection-items))
               )
              (reset!
                span-element
                (.-nextElementSibling
                  input-element))
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
        (when (.-valid
                validity)
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
            (.-valid
              validity))
       )
      (catch js/Error e
        (.error
          js/console
          (.-message
            e))
       ))
   ))

