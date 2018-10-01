package code

// Exercise:
//
// Implement an ADT called IntList!
//
// Tip: Implement the methods below one at a time,
// checking them against the unit tests before moving on to the next
//
// Tip: Don't worry about making your methods tail recursive
// (unless you get to the end and have time to spare)
//
// Implement the following methods:
//
// - length
//    - takes no parameters
//    - returns the length of the list
//
//  - contains
//    - takes an Int parameter
//    - checks whether the Int is in the list
//
//  - addToEach
//    - takes an Int parameter n
//    - adds n to every element in the list, returning a new list
//
//  - sum
//    - takes no parameters
//    - adds up all the elements in the list
//
//  - exists
//    - takes a predicate as a parameter
//    - returns true if the predicate applies to any item in the list
//
//  - filter
//    - takes a predicate as a parameter
//    - returns a list of all items for which the predicate returns true
//
//  - (Optional) reverse
//    - takes no parameters
//    - reverses the list
//
//  - (Optional) find
//    - takes a predicate as a parameter
//    - returns the first item for which it returns true
//    - Note:
//      - how can you handle the case where we can't find the relevant item?
//      - either use an Option to wrap the result, or introduce your own IntOption ADT
//
//  - (Optional, Harder) — make as many of your methods tail recursive as possible
//    - Fork your code by copying the file if you do this!
//    - We'll be working with this code again and, ultimately, it'll be easier to not have the extra complexity.


sealed trait IntList
// etc...