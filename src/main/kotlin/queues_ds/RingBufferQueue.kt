package queues_ds

import array_ds.RingBuffer

// A ring buffer, also known as a circular buffer, is a fixed-size array. This data structure strategically wraps around to the beginning when there are no more items to remove at the end.

// The enqueue operation has a runtime complexity of O(1)

// Removing an element from the front of the queue is an O(1) operation.
/////////////////////////////////////////////////////////////////////
//  Operations               Best Case               Worst Case    //
//  enqueue                  O(1)                    O(1)          //
//  dequeue                  O(1)                    O(1)          //
//  Space Complexity         O(n)                    O(n)          //
/////////////////////////////////////////////////////////////////////

//The ring-buffer-based queue has the same time complexity for enqueue and dequeue as the linked-list implementation. The only difference is the space complexity. The ring buffer has a fixed size, which means that enqueue can fail.

class RingBufferQueue<T>(size: Int) : Queue<T> {
    private val ringBuffer: RingBuffer<T> = RingBuffer(size)
    override val count: Int
        get() = ringBuffer.count
    override fun peek(): T? = ringBuffer.first

    override fun enqueue(element: T): Boolean = ringBuffer.write(element)

    override fun dequeue(): T? = if (isEmpty) null else ringBuffer.read()

    override fun toString(): String = ringBuffer.toString()
}

fun main() {
    val queue = RingBufferQueue<String>(10).apply {
        enqueue("Ray")
        enqueue("Brian")
        enqueue("Eric")
    }
    println(queue)
    queue.dequeue()
    println(queue)
    println("Next up: ${queue.peek()}")
}