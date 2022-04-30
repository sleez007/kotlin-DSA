package queues_ds

import linked_list_ds.DoublyLinkedList

//  A doubly linked list is simply a linked list in which nodes also contain a reference to the previous node.

// The enqueue operation has a runtime complexity of O(1)

// Removing an element from the front of the queue is an O(1) operation.

/////////////////////////////////////////////////////////////////////
//  Operations               Best Case               Worst Case    //
//  enqueue                  O(1)                    O(1)          //
//  dequeue                  O(1)                    O(1)          //
//  Space Complexity         O(n)                    O(n)          //
/////////////////////////////////////////////////////////////////////

// The main weakness with LinkedListQueue is not apparent from the table. Despite O(1) performance, it suffers from high overhead. Each element has to have extra storage for the forward and back reference. Moreover, every time you create a new element, it requires a relatively expensive dynamic allocation. By contrast, ArrayListQueue does bulk allocation, which is faster.

class LinkedListQueue<T> : Queue<T> {
    private val list = DoublyLinkedList<T>()

    private var size = 0
    override val count: Int
        get() = size

    override fun peek(): T? = list.first?.value

    override fun enqueue(element: T): Boolean {
        list.append(element)
        size++
        return true
    }

    override fun dequeue(): T? {
        val firstNode = list.first ?: return null
        size--
        return list.remove(firstNode)
    }

    override fun toString(): String = list.toString()
}

fun main() {
    val queue = LinkedListQueue<String>().apply {
        enqueue("Ray")
        enqueue("Brian")
        enqueue("Eric")
    }

    println(queue)
    queue.dequeue()
    println(queue)
    println("Next up: ${queue.peek()}")
}