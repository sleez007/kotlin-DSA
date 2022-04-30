package linked_list_ds

// A linked list is a collection of values arranged in a linear, unidirectional sequence. A linked list has several theoretical advantages over contiguous storage options such as the Kotlin Array or ArrayList:
//• Constant time insertion and removal from the front of the list.
//• Reliable performance characteristics.

// LinkedList is a chain of nodes. Nodes have two responsibilities
// i) Holds a value
// ii) Holds a reference to the next node. The absence of a reference to the next node, null, marks the end of the linked list;

//The push method of the linked list class is used to add a new value to the front of the list. This is also known as head first insertion and has a constant time operation

//The append method adds an element to the end of the list. This is also known as a tail-end insertion and has a runtime complexity of constant time operation

//The insert method adds a value at a particular place in the list.This method usually requires two steps, find a particular node in the list and inserts the new node after that node. This operation has a constant time execution

//The pop method of the linkedList removes the value at the front of the linkedList and has a constant time runtime complexity

//The removeLast method requires we traverse the list and as such has a runtime complexity of linear time O(n)

//The removeAfter method is used to remove a node after the node passed in as an argument. The time complexity of this operation is constant time

class LinkedListIterator<T>(private val list: LinkedListDs<T>) : Iterator<T>, MutableIterator<T> {
    private var index = 0
    private var lastNode: LinkedListDs.Node<T>? = null

    // Iterator Overrides
    override fun next(): T {
        if( index >= list.size) throw java.lang.IndexOutOfBoundsException()

        lastNode = if (index == 0){
            list.nodeAt(0)
        }else{
            lastNode?.next
        }
        index++
        return  lastNode!!.value
    }

    // Iterator Overrides
    override fun hasNext(): Boolean {
        return index < list.size
    }

    // MutableIterator Overrides
    override fun remove() {
        if (index == 1) {
            list.pop()
        } else {
            val prevNode = list.nodeAt(index - 2) ?: return
            // 3
            list.removeAfter(prevNode)
            lastNode = prevNode
        }
        index--
    }
}



class LinkedListDs<T> : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size: Int = 0
        private set

    override fun isEmpty():Boolean = size == 0

    //Method overrides for the iterable interface
    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements) {
            result = remove(item) || result
        }
        return result
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            // 3
            if (item == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    //Method overrides for the iterable interface
//    override fun iterator(): Iterator<T> {
//        return LinkedListIterator(this)
//    }

    //As you’d probably guess, this is an inefficient method, it’s O(n^2). But if the Collection interface requires it, you need to provide it.
    // Method overrides for the collection interface
    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched)) return false
        }
        return true
    }

    // Method overrides for the collection interface
    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }
        return false
    }


    override fun toString(): String {
        return if (isEmpty()){
            "Empty List"
        }else{
            head.toString()
        }
    }

    fun push(value: T): LinkedListDs<T>{
        head = Node(value, next = head)
        if(tail == null){
            tail  = head
        }
        size++
        return this
    }

    fun append(value : T): LinkedListDs<T>{
        if(isEmpty()){
            push(value)
            return this
        }

        //assign the new node to the current tail next value
        tail?.next = Node(value)

        //set tail to the value of the current tail.next which is the value that was just added
        tail = tail?.next

        size++
        return  this
    }

    fun nodeAt(index: Int): Node<T>?{
        var currentNode = head
        var currentIndex = 0
        while (currentNode !=null &&  currentIndex < index){
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T>{

        if (tail == afterNode) {
            append(value)
            return tail!!
        }

        val newNode = Node(value = value, next = afterNode.next)

        afterNode.next = newNode
        size++
        return newNode
    }

    fun pop(): T?{
        if(!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()){
            tail = null
        }
        return result
    }

    fun removeLast(): T?{
        val head = head ?: return null

        if(head.next == null) return  pop()

        size--

        var prev = head
        var current = head
        var next = current.next

        while(next != null){
            prev = current
            current = next
            next  = current.next
        }

        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if(node.next == tail) tail = node

        if( node.next != null ) size--

        node.next = node.next?.next
        return result
    }

    data class Node<T>(val value : T, var next : Node<T>? = null){
        override fun toString(): String {
            return  if (next !=null){
                "$value -> ${next.toString()}"
            }else{
                "$value"
            }
        }
    }
}


fun main() {
    val link: LinkedListDs<Int> = LinkedListDs()
    link.push(1).push(5).push(8).append(66).push(87)
    var middleNode = link.nodeAt(1)!!
    for (i in 1..3) {
        middleNode = link.insert(-1 * i, middleNode)
    }
    println( link)
    val index = 1
    val node = link.nodeAt(index - 1)!!
    val removedValue = link.removeAfter(node)
    println("After removing at index $index: $link")
    println("Removed value: $removedValue")
    println(link)

    // After implementing the iterable interface
    for (item in link) {
        println("Double: ${item * 2}")
    }
//    val poppedValue = link.pop()
//    println(poppedValue)
//    val removed = link.removeLast()
//    println("removed last value is: $removed")
//    println( link)
}