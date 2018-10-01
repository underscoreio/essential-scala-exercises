package code

// (Optional, Harder) Exercise: Design a data type for a binary tree of integers.
//
// Tip: Implement the methods below one at a time,
// checking them against the unit tests before moving on to the next
//
// Implement the following methods:
// - size — the number of nodes in the tree
// - addToEach — add a number to each node in the tree
// - sum — add together all of the nodes in the tree
// - exists
//   - takes a predicate as a parameter
//   - returns true if it applies to any node in the tree
// - filter
//   - takes a predicate as a parameter
//   - returns a tree of all nodes for which the predicate returns true
// - find
//   - takes a predicate as a parameter
//   - returns the first node (left to right) for which it returns true
// - (Optional) toList — linearise a list of all the nodes
// - (Optional) depth — the maximum distance from the root to a leaf
// - (Optional) width — number of nodes in the widest horizontal layer


sealed trait IntTree
// etc...