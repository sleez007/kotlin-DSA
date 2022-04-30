package queues_ds

// We’re all familiar with waiting in line. Whether you’re in line to buy tickets to your favorite movie or waiting for a printer to print a file, these real-life scenarios mimic the queue data structure.
// Queues use FIFO or first in, first out ordering, meaning the first element that was added will always be the first one removed. Queues are handy when you need to maintain the order of your elements to process later.
// The core operations for a queue are:
//• enqueue: Inserts an element at the back of the queue and returns true if the operation is successful.
//• dequeue: Removes the element at the front of the queue and returns it. • isEmpty: Checks if the queue is empty using the count property
//• peek: Returns the element at the front of the queue without removing it.

//Notice that the queue only cares about removal from the front and insertion at the back. You don’t need to know what the contents are in between. If you did, you’d presumably use an array instead of a Queue.

// In the following sections, you’ll learn to create a queue in four different ways:
//• Using an array based list
//• Using a doubly linked list
//• Using a ring buffer
//• Using two stacks

interface Queue<T> {
    fun enqueue(element: T): Boolean
    fun dequeue(): T?
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0
    fun peek(): T?
}
