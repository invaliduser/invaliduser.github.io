(ns site.pages.clojure-intro
  (:require [rum.core :as rum]
            [site.logic.eval :refer [visible-parit]]
            [clojure.string :as s]
            [site.utils :refer [mth]]))



(def cl-begin "%clb%")

(def cl-end "%cle%")


(defn clojurify [bs]
  (let [[f & beginnings] (s/split bs #"%clb%")
        endings (->> beginnings (mapcat (fn [frag]
                                       (let [[code prose] (s/split frag #"%cle%")]
                                         [{:code code} prose]))))]
    (cons f endings)))



(def bs "
Clojure for Non-Programmers

Clojure *can* be really hard and complicated if you *want* it to be.

But at its core, it's really not hard.

Today you're going to learn it!  Or at least make a good start.



So let's dive in.

  ###FUNCTIONS

The last time you dealt with functions was probably in high school or college math; something like this:

`f(x) = 5x + 2`

A function can be thought of as a machine---it takes something in, and puts something out.  This one takes a number, multiples it by five, and adds 2.

There's nothing magical about the letter `f`; it's just a letter.  Mathematicians tend to use f, g, or h.  But you can even use letters from other languages--- or words

`mult-by-five-and-add-2(x) = 5x + 2`

The thing to remember about math is that it's just notation, it would be just as \"correct\" to write

`mult-by-five-and-add-2(x) = \"Take the number, multiply it by five, then add 2.\"`

A function is like a little machine, that takes inputs and puts out an output, but it doesn't have to be just *one* input:

`f(a,b) = a - b`

Inputs don't have to be numbers!

`capital-letter(letter) = \"Whatever the letter is, output the capital version of that.\"`

`First-of-grocery-list (grocery-list) = \"Output the first item on the grocery list\"`


  ###CLOJURE



So far the notation we've used for functions has been: the name of the function before some parentheses, then the names of the inputs inside the parentheses, and then an equals sign, and then a rule for creating the output.

Clojure has different notation.

First, we have the notation for *calling* the function, equivalent to feeding inputs into the machine.

The notation is: a pair of parentheses, with some stuff inside.  The first thing is the name of the function, everything after that is an input

  So instead of writing f(a,b), we'd write `(f a b)`

It turns out the \"operators\" you learned about in math---+, - / * --- are all functions

  %clb%(+ 2 3)%cle%
  
  %clb%(* 5 2)%cle%
  
  %clb%(- 2 3)%cle%

  There's also a simple function we'll use for examples called `inc`.  `inc` stands for increment; it takes a number and returns the same number plus one.

  %clb%(inc 3)%cle%

  %clb%(inc 4)%cle%

You can nest function calls.  Clojure evaluates the interior parens first: 

  
`(+ 2 (* 5 3))    =>   (+ 2 15) => 17`


If you *don't* want Clojure to evaluate something in parens, you just add a single quote in front of it.  This just returns the parens and everything inside:

  %clb%'(+ 2 3)%cle%

  The first item is the `+` function, the next item is `2`, the third item is `3`.

It is a little confusing that nearly the same notation is used for lists and for evaluation; there are reasons for this that don't matter right now.

What's more important is the idea of \"lists\" and \"functions\".  Remember how before we were feeding numbers, letters, and grocery store lists as inputs into functions?

Well, Clojure functions can take lists as inputs---Clojure functions can even take other functions as inputs!

Going back to our earlier notation, you might have a function like:

  
  ```
  increment-each-number-by-one(a-list-of-numbers) = \"Return the same list, except that every number is increased by one\"
  ```
If we had that function in clojure (it doesn't exist, but let's pretend it did), we could call

`(increase-each-number-by-one '(1 2 3 34))        =>           (2 3 4 35)`

Okay, that's an example of feeding a list as an input to a function.  What about feeding a function to a function?

Well, there's a function in clojure called map.  It takes a function and a list as inputs.  What it does, is apply the function fed to it to every member of the list.  Then it returns the list.

  %clb%(map inc '(1 2 3 4))%cle%

It's important to think of functions as *things*, rather than \"commands\".  The default state of functions is to sit there and do nothing.   Only when you call them by putting them as the first item in parentheses, or by feeding them to another function that calls them, do they \"activate.\"


  %clb%4%cle%         `=>   4                      doesn't do anything, just returns itself`
  %clb%inc%cle%       `=>   inc                    doesn't do anything, just returns itself`
  %clb%(inc 3)%cle%   `=>   4                      ;wowza!  it's alive!`
  %clb%'(inc 3)%cle%  `=>   (inc 3)                ;the single quote stops anything from happening`
  %clb%(map inc '(1 2 3))%cle%  `=> (2 3 4)        ;we call map by putting it first in the parens, and we feed it inc and '(1 2 3), it applies inc to every member of the list`




Clojure has a lot of functions that take lists and/or functions as inputs---

  `reverse` just takes a list and reverses it

  %clb%(reverse '(1 2 3))%cle%  => `(3 2 1)`

  `filter` takes a function and a list, applies the function to every member of the list, and filters for the items that \"pass\" the input function

  %clb%(even? 3)%cle%      `=> false`
  %clb%(even? 4)%cle%      `=>  true`

  %clb%(filter even? '(1 2 3 4 5 6 7 8))%cle%       `=>     (2 4 6 8)`

  %clb%(filter odd?  '(1 2 3 4 5 6 7 8))%cle%       `=>     (1 3 5 7)`


  `take-while` takes a function and a list, and returns \"everything in the list, up until the first one that \"fails\" the input function\"

  %clb%(take-while even?  '(2 4 6 8 10 11 12 14 16 18))%cle%   => (2 4 6 8 10)

There's a bunch of these, as you can imagine ----

	`drop-while` (opposite of `take-while`),

	`rest` (takes a list and returns everything but the first item),

	`remove` (opposite of `filter`---takes a function and a list, and removes everything that *does* pass, rather than everything that doesn't),

	`repeat` (takes literally anything and returns an infinite list of just that thing, repeated),

	`range` (takes a number and returns a list of all the numbers up until that number, starting from 0), `interleave` (takes TWO lists, and returns one list that alternates)




  %clb%(range 10)%cle% => `(0 1 2 3 4 5 6 7 8 9)`

  %clb%(reverse (rest (map inc (range 10))))%cle%  =>  can you guess what this does?    Remember---Clojure works inside-out!





One last bit---we've seen how to *call* functions, by putting them as the first item in parentheses or by feeding them to another function that calls them.  But how do we make our own functions?

The thing to remember is that a function is just a thing, an inanimate object.  We can describe it like this
  ```
(fn [a b]
    (+ a b))
  ```
The fn at the beginning changes the rules a bit, and tells the computer that we're defining a function.  The things in square brackets are the names of the arguments---after that, is what we do to the arguments.

  Remember `inc`?  We could write it like this:

  ```
(fn [n]
    (+ n 1))
  ```

If we substitute this in, it works just the same:

  %clb%(range 10)%cle%                         =>    (0 1 2 3 4 5 6 7 8 9)

  %clb%(map       inc        (range 10))%cle%  =>    (1 2 3 4 5 6 7 8 9 10)

  %clb%(map (fn [n] (+ n 1)) (range 10))%cle%  =>    (1 2 3 4 5 6 7 8 9 10)


  What if we want to give our function a name so we can use it later?  That's when we use `defn`
  ```
(defn plus [a b]
  (+ a b))
  ```
  `defn` stands for \"define function.\"  `defn` is just like `fn`, except that it takes a name first, and records our function under that name.  Then we can use it later:

  `(plus 3 5)   =>    8`

These are all really simple pieces, but you can do a lot by combining simple pieces!

  `(remove odd? relatives)  => alright, I'll be going now!`


")

(def parsed (clojurify bs))


(rum/defc tutorial [v]
  [:div (for [frag v]
          [:div (if (map? frag)
                  (visible-parit (atom (:code frag)))

                  (mth frag))])])

(defn finished-tutorial [] (tutorial parsed))
