package code

import org.scalatest._

class IntTreeSpec extends FlatSpec with Matchers {
  // These methods are useful because
  // they return type "Tree" instead of "Leaf" or "Node".
  // This can make type inference easier in some cases.

  val leaf: IntTree =
    IntLeaf

  def node(n: Int, l: IntTree = IntLeaf, r: IntTree = IntLeaf): IntTree =
    IntNode(n, l, r)

  "size" should "return the number of nodes in a tree" in {
    leaf.size should equal(0)
    node(1).size should equal(1)
    node(1, node(2)).size should equal(2)
    node(1, r = node(2)).size should equal(2)
    node(1, node(2), node(3)).size should equal(3)
  }

  "addToEach" should "add to every node in the tree" in {
    leaf.addToEach(10) should equal(leaf)
    node(1).addToEach(10) should equal(node(11))
    node(1, node(2)).addToEach(10) should equal(node(11, node(12)))
    node(1, r = node(2)).addToEach(10) should equal(node(11, r = node(12)))
    node(1, node(2), node(3)).addToEach(10) should equal(node(11, node(12), node(13)))
  }

  "sum" should "add together the nodes in the tree" in {
    leaf.sum should equal(0)
    node(1).sum should equal(1)
    node(1, node(2)).sum should equal(3)
    node(1, r = node(2)).sum should equal(3)
    node(1, node(2), node(3)).sum should equal(6)
  }

  "exists" should "find nodes" in {
    leaf.exists(n => n > 1) should equal(false)
    node(1).exists(n => n > 1) should equal(false)
    node(1, node(2)).exists(n => n > 1) should equal(true)
    node(1, r = node(2)).exists(n => n > 1) should equal(true)
    node(1, node(2), node(3)).exists(n => n > 1) should equal(true)
    node(1, node(2), node(3)).exists(n => n > 5) should equal(false)
  }

  "find" should "find one node" in {
    leaf.find(n => n > 1) should equal(None)
    node(1).find(n => n > 1) should equal(None)
    node(1, node(2)).find(n => n > 1) should equal(Some(2))
    node(1, r = node(2)).find(n => n > 1) should equal(Some(2))
    node(1, node(2), node(3)).find(n => n > 2) should equal(Some(3))
    node(1, node(2), node(3)).find(n => n > 5) should equal(None)
  }

  "depth" should "calculate depth" in {
    leaf.depth should equal(0)
    node(1).depth should equal(1)
    node(1, node(2)).depth should equal(2)
    node(1, r = node(2)).depth should equal(2)
    node(1, node(2), node(3, node(4))).depth should equal(3)
  }

  "width" should "calculate width" in {
    leaf.width should equal(0)
    node(1).width should equal(1)
    node(1, node(2)).width should equal(1)
    node(1, r = node(2)).width should equal(1)
    node(1, node(2), node(3)).width should equal(2)
    node(1, node(2), node(3, node(4))).width should equal(2)
  }

  "widths" should "calculate the widths of each layer in the tree" in {
    // Note: These tests verify a helper method I used for width.
    // Don't worry about them if you implemented width in a different way

    leaf.widths should equal(Nil)
    node(1).widths should equal(List(1))
    node(1, node(2)).widths should equal(List(1, 1))
    node(1, r = node(2)).widths should equal(List(1, 1))
    node(1, node(2), node(3)).widths should equal(List(1, 2))
    node(1, node(2), node(3, node(4))).widths should equal(List(1, 2, 1))
  }

  "filter" should "filter by predicate" in {
    // Note: Don' treat these tests as canonical.
    // My implementation of filter is extremely rudimentary:
    // if we remove a node from the tree,
    // we attach its right subtree to the rightmost point on its left subtree.
    // We don't rebalance the tree, which leads to very lopsided results.

    val tree1 =
      node(
        1,
        node(2, node(4), node(5)),
        node(3, node(6), node(7)))

    val tree2 =
      node(2, node(4), node(5, r = node(3, node(6), node(7))))

    val tree3 =
      node(4, r = node(5, r = node(6, r = node(7))))

    tree1.filter(n => n > 1) should equal(tree2)
    tree1.filter(n => n > 3) should equal(tree3)
  }
}
