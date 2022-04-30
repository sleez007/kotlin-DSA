package queues_ds

// The count and peek operation has a runtime complexity of O(1)

// The enqueue operation has a runtime complexity of O(1)

// Removing an element from the front of the queue is an O(n) operation. To dequeue, you remove the element from the beginning of the list. This is always a linear time operation because it requires all of the remaining elements in the list to be shifted in memory.

/////////////////////////////////////////////////////////////////////
//  Operations               Best Case               Worst Case    //
//  enqueue                  O(1)                    O(1)          //
//  dequeue                  O(n)                    O(n)          //
//  Space Complexity         O(n)                    O(n)          //
/////////////////////////////////////////////////////////////////////

class ArrayListQueue<T>: Queue<T> {
    private val list = arrayListOf<T>()

    override val count: Int
        get() = list.size

    override fun peek(): T? = list.getOrNull(0)

    override fun enqueue(element: T): Boolean {
        list.add(element)
        return true
    }

    override fun dequeue(): T? = if (isEmpty) null else list.removeAt(0)

    override fun toString(): String = list.toString()
}

fun main() {
    val queue = ArrayListQueue<String>().apply {
        enqueue("Ray")
        enqueue("Brian")
        enqueue("Eric")
    }
    println(queue)
    queue.dequeue()
    println(queue)
    println("Next up: ${queue.peek()}")

}