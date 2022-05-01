package trees

import queues_ds.StackQueue

// The tree is a data structure of profound importance. It’s used to tackle many recurring challenges in software development, such as:
//• Representing hierarchical relationships.
//• Managing sorted data.
//• Facilitating fast lookup operations.

// Like the linked list, trees are made up of nodes.
//Trees are viewed starting from the top and branching toward the bottom — just like a real tree, only upside-down.
// Every node, except for the first one, is connected to a single node above, which is referred to as a parent node. The nodes directly below and connected to the parent node are known as child nodes. In a tree, every child has exactly one parent. That’s what makes a tree, well, a tree.
// The topmost node in the tree is called the root of the tree. It’s the only node that has no parent:
// A node that has no children is called a leaf:

data class TreeNode<T>(val value : T, private val children: MutableList<TreeNode<T>> = mutableListOf()) {
    fun add(child: TreeNode<T>) = children.add(child)

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = StackQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }
        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }

    fun search(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null
        forEachLevelOrder {
            if (it.value == value) {
                result = it }
        }
        return result
    }
}


typealias Visitor<T> = (TreeNode<T>) -> Unit

fun makeBeverageTree(): TreeNode<String> {
    val tree = TreeNode("Beverages")
    val hot = TreeNode("hot")
    val cold = TreeNode("cold")
    val tea = TreeNode("tea")
    val coffee = TreeNode("coffee")
    val chocolate = TreeNode("cocoa")
    val blackTea = TreeNode("black")
    val greenTea = TreeNode("green")
    val chaiTea = TreeNode("chai")
    val soda = TreeNode("soda")
    val milk = TreeNode("milk")
    val gingerAle = TreeNode("ginger ale")
    val bitterLemon = TreeNode("bitter lemon")
    tree.add(hot)
    tree.add(cold)
    hot.add(tea)
    hot.add(coffee)
    hot.add(chocolate)
    cold.add(soda)
    cold.add(milk)
    tea.add(blackTea)
    tea.add(greenTea)
    tea.add(chaiTea)
    soda.add(gingerAle)
    soda.add(bitterLemon)
    return tree
}

fun main() {
//    val tree = makeBeverageTree()
//    tree.forEachDepthFirst { println(it.value) }

//    val tree = makeBeverageTree()
//    tree.forEachLevelOrder { println(it.value) }

    val tree = makeBeverageTree()
    tree.search("ginger ale")?.let {
        println("Found node: ${it.value}")
    }
    tree.search("WKD Blue")?.let {
        println(it.value)
    } ?:  println("Couldn't find WKD Blue")
}