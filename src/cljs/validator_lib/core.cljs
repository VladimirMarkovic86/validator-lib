(ns validator-lib.core
  (:require [language-lib.core :refer [get-label]]
            [js-lib.core :as md]))

(defn generate-validation-message
  "Generate validation message"
  [element
   validator-message]
  (let [validity (.-validity
                   element)
        element-type (.-type
                       element)]
    (when (.-badInput
            validity)
      (reset!
        validator-message
        (get-label 38))
     )
    (when (.-customError
            validity)
      (reset!
        validator-message
        ""))
    (when (.-patternMismatch
            validity)
      (when-not (contains?
                  #{"text"
                    "email"}
                  element-type)
        (reset!
          validator-message
          (get-label 40))
       )
      (when (contains?
              #{"text"
                "email"}
              element-type)
        (reset!
          validator-message
          (get-label
            57))
       ))
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
       ))
    (when (.-tooShort
            validity)
      (let [text-length (.-textLength
                          element)
            min-length (.-minLength
                         element)]
        (when-not (contains?
                    #{"text"
                      "email"
                      "password"}
                    element-type)
          (reset!
            validator-message
            (get-label 45))
         )
        (when (contains?
                #{"text"
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
       ))
    (when (.-valueMissing
            validity)
      (when-not (contains?
                  #{"text"
                    "email"
                    "password"
                    "date"
                    "number"
                    "radio"
                    "select-one"
                    "select-multiple"}
                  element-type)
        (reset!
          validator-message
          (get-label 47))
       )
      (when (contains?
              #{"text"
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
       ))
   ))

(defn validate-input
  "Validate input that happened on field"
  [evt-p
   element
   event]
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
  (let [validity (.-validity
                   element)
        validator-message (atom "")]
    (generate-validation-message
      element
      validator-message)
    (when-not (.-valid
                validity)
      (.setCustomValidity
        element
        @validator-message)
      (let [span-element (.-nextElementSibling
                           element)]
        (aset
          span-element
          "innerHTML"
          @validator-message)
        (md/add-class
          (.closest
            span-element
            "label")
          "error"))
     ))
 )

(defn validate-field
  "Read validation attributes of particular fields
  
  validator-fn
    (fn [input-elem]
      (let [input-value (md/get-value-as-number
                          input-elem)]
        (if (= input-value
               888)
          \"\"
          \"Number is not 888\"))
     )"
  [input-element
   is-valid
   & [validator]]
  (when validator
    (let [validator-message (validator
                              input-element)]
      (.setCustomValidity
        input-element
        validator-message))
   )
  (when-not validator
    (let [validity (.-validity
                     input-element)
          validator-message (atom "")]
      (generate-validation-message
        input-element
        validator-message)
      (when-not (.-valid
                  validity)
        (.setCustomValidity
          input-element
          @validator-message)
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
            @validator-message)
          (md/add-class
            (.closest
              @span-element
              "label")
            "error"))
       )
      (swap!
        is-valid
        (fn [a-val
             new-val]
          (and a-val
               new-val))
          (.-valid
            validity))
     ))
 )

