Functional Programming Exercise 1

Using the code provided as a starting point, your mission
is to create a replacement piece of software achieving the
same results in a manner that is "as functional as you can
make it".

The primary goal is thinking and problem solving, rather
than a fully finished result (though the kudos points are
higher the more finished your result is, naturally). So,
if, for example, you've changed one part to be functional
but don't finish changing another part, being ready to
discuss what you would have done is still a step forward.
Similarly, if there's something you just don't see how to
achieve, make a note of that, and be ready to discuss the
problem you perceive.

So, consider the ideas we discussed in class:

Separate unrelated concerns, and computation versus
handling storage are effectively separate concerns.

Pure functions:
 - are idempotent, they have no "effect" other than their
   return value
 - create a return value that depends only on their
   arguments
 - are total (they always produce a result for any input)
   and only throw exceptions to indicate a design or
   implementation error (i.e. a bug!)

Functional programming does not mutate data, although it
must be noted that initialization (of object contents or
of variables) is in fact a form of initialization, but this
cannot be avoided in a von Neumann computing architecture
(or perhaps more precisely any architecture that uses
memory!)

Bonus kudos points: Evaluate the relative ease of writing
tests for the two code bases.
