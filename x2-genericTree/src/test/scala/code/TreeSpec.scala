package code

import org.scalatest._

class TreeSpec extends FlatSpec with Matchers {
  // These methods are useful because
  // they return type "Tree" instead of "Leaf" or "Node".
  // This can make type inference easier in some cases.

  // def leaf[A]: Tree[A] =
  //   Leaf()

  // def node[A](n: A, l: Tree[A] = leaf[A], r: Tree[A] = leaf[A]): Tree[A] =
  //   Node(n, l, r)

  "size" should "return the number of nodes in a tree" in {
    pending
    // leaf.size should equal(0)
    // node(1).size should equal(1)
    // node(1, node(2)).size should equal(2)
    // node(1, r = node(2)).size should equal(2)
    // node(1, node(2), node(3)).size should equal(3)
  }

  "addToEach" should "add to every node in the tree" in {
    pending
    // leaf.addToEach(10) should equal(leaf)
    // node(1).addToEach(10) should equal(node(11))
    // node(1, node(2)).addToEach(10) should equal(node(11, node(12)))
    // node(1, r = node(2)).addToEach(10) should equal(node(11, r = node(12)))
    // node(1, node(2), node(3)).addToEach(10) should equal(node(11, node(12), node(13)))
  }

  "sum" should "add together the nodes in the tree" in {
    pending
    // leaf.sum should equal(0)
    // node(1).sum should equal(1)
    // node(1, node(2)).sum should equal(3)
    // node(1, r = node(2)).sum should equal(3)
    // node(1, node(2), node(3)).sum should equal(6)
  }

  "exists" should "find nodes" in {
    pending
    // leaf[Int].exists(n => n > 1) should equal(false)
    // node(1).exists(n => n > 1) should equal(false)
    // node(1, node(2)).exists(n => n > 1) should equal(true)
    // node(1, r = node(2)).exists(n => n > 1) should equal(true)
    // node(1, node(2), node(3)).exists(n => n > 1) should equal(true)
    // node(1, node(2), node(3)).exists(n => n > 5) should equal(false)

    // leaf[String].exists(n => n != "1") should equal(false)
    // node("1").exists(n => n != "1") should equal(false)
    // node("1", node("2")).exists(n => n != "1") should equal(true)
    // node("1", r = node("2")).exists(n => n != "1") should equal(true)
    // node("1", node("2"), node("3")).exists(n => n != "1") should equal(true)
    // node("1", node("2"), node("3")).exists(n => n == "5") should equal(false)
  }

  "find" should "find one node" in {
    pending
    // leaf[Int].find(n => n > 1) should equal(None)
    // node(1).find(n => n > 1) should equal(None)
    // node(1, node(2)).find(n => n > 1) should equal(Some(2))
    // node(1, r = node(2)).find(n => n > 1) should equal(Some(2))
    // node(1, node(2), node(3)).find(n => n > 2) should equal(Some(3))
    // node(1, node(2), node(3)).find(n => n > 5) should equal(None)

    // leaf[String].find(n => n != "1") should equal(None)
    // node("1").find(n => n != "1") should equal(None)
    // node("1", node("2")).find(n => n != "1") should equal(Some("2"))
    // node("1", r = node("2")).find(n => n != "1") should equal(Some("2"))
    // node("1", node("2"), node("3")).find(n => n != "1") should equal(Some("2"))
    // node("1", node("2"), node("3")).find(n => n == "5") should equal(None)
  }

  "filter" should "filter by predicate" in {
    pending
    // Note: Don' treat these tests as canonical.
    // My implementation of filter is extremely rudimentary:
    // if we remove a node from the tree,
    // we attach its right subtree to the rightmost point on its left subtree.
    // We don't rebalance the tree, which leads to very lopsided results.

    // val tree1 =
    //   node(
    //     1,
    //     node(2, node(4), node(5)),
    //     node(3, node(6), node(7)))

    // val tree2 =
    //   node(2, node(4), node(5)) ++ node(3, node(6), node(7))

    // val tree3 =
    //   node(4) ++ node(5) ++ node(6) ++ node(7)

    // tree1.filter(n => n > 1) should equal(tree2)
    // tree1.filter(n => n > 3) should equal(tree3)

    // val tree4 =
    //   node(
    //     "1",
    //     node("2", node("4"), node("5")),
    //     node("3", node("6"), node("7")))

    // val tree5 =
    //   node("2", node("4"), node("5")) ++ node("3", node("6"), node("7"))

    // val tree6 =
    //   node("4") ++ node("5") ++ node("6") ++ node("7")

    // tree4.filter(n => n > "1") should equal(tree5)
    // tree4.filter(n => n > "3") should equal(tree6)
  }

  "++" should "append trees" in {
    pending
    // Note: These tests verify a helper method I used for filter.
    // Don't worry about them if you wrote filter in a different way.

    // node(1) ++ node(2) should equal(node(1, r = node(2)))
    // node(1, r = node(2)) ++ node(3) should equal(node(1, r = node(2, r = node(3))))
  }
}
