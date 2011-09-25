Genetic Algorithms in Clojure
-----------------------------

This project contains a simple genetic algorithm implemented in Clojure. We
started work on it during the [September 14][1] meeting, and will be continuing
it at future meetings..

The algorithm is very simple:

* Given a integer, find an expression that evaluates to that value.
* A valid expression meets the following grammar (numbers in {braces} are
  repeats, and portions in \[brackets\] are optional):

<pre>
    expression  -> digit op digit \[op digit\]{3}
    digit       -> '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
    op          -> plus | minus | multiply | divide
    plus        -> '+'
    minus       -> '-'
    multiply    -> '*'
    divide      -> '/'
</pre>

* Operations are evaluated from left to right; there is no operator precedence.

[Come to the Dojo][2] and play with us!.

[1] http://austincodedojo.github.com/2011/09/14/Meeting-Notes-September-14-2011.html "Meeting notes from the September 14 meeting."
[2] http://austincodedojo.github.com "The Austin Code Dojo homepage"
