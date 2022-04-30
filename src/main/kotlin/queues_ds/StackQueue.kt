package queues_ds

import stack_ds.StackDs

// The idea behind using two stacks is simple. Whenever you enqueue an element, it goes in the right stack.

//When you need to dequeue an element, you reverse the right stack and place it in the left stack so that you can retrieve the elements using FIFO order.

/////////////////////////////////////////////////////////////////////
//  Operations               Best Case               Worst Case    //
//  enqueue                  O(1)                    O(1)          //
//  dequeue                  O(1)                    O(1)          //
//  Space Complexity         O(n)                    O(n)          //
/////////////////////////////////////////////////////////////////////

class StackQueue<T : Any> : Queue<T> {
    private val leftStack = StackDs<T>()
    private val rightStack = StackDs<T>()

    override val isEmpty: Boolean
        get() = leftStack.isEmpty && rightStack.isEmpty

    override val count: Int
        get() = leftStack.count + rightStack.count

    private fun transferElements() {
        var nextElement = rightStack.pop()
        while (nextElement != null) {
            leftStack.push(nextElement)
            nextElement = rightStack.pop()
        }
    }

    override fun peek(): T? {
        if (leftStack.isEmpty) {
            transferElements()
        }
        return leftStack.peek()
    }

    override fun enqueue(element: T): Boolean {
        rightStack.push(element)
        return true
    }

    override fun dequeue(): T? {
        if (leftStack.isEmpty) { // 1
            transferElements() // 2
        }
        return leftStack.pop() // 3
    }

    override fun toString(): String {
        return "Left stack: \n$leftStack \n Right stack: \n$rightStack"
    }
}


fun main() {
    val queue = StackQueue<String>().apply {
        enqueue("Ray")
        enqueue("Brian")
        enqueue("Eric")
    }
    println(queue)
    queue.dequeue()
    println(queue)
    println("Next up: ${queue.peek()}")
}