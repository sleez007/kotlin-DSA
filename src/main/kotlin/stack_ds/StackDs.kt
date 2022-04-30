package stack_ds

// Stacks are useful, and also exceedingly simple. The main goal of building a stack is to enforce how you access your data. If you had a tough time with the linked list concepts, you’ll be glad to know that stacks are comparatively trivial.
//There are only two essential operations for a stack:
// • push: Adding an element to the top of the stack.
// • pop: Removing the top element of the stack.

//This means that you can only add or remove elements from one side of the data structure.In computer science, a stack is known as the LIFO (last-in first-out) data structure. Elements that are pushed in last are the first ones to be popped out.

// You can implement your Stack interface in different ways and choosing the right storage type is important. The ArrayList is an obvious choice since it offers constant time insertions and deletions at one end via add and removeAt with the last index as a parameter. Usage of these two operations will facilitate the LIFO nature of stacks.

interface StackFacade<Element> {
    fun push(element: Element)

    fun pop() : Element?

    fun peek(): Element?

    val count: Int

    val isEmpty: Boolean
        get() = count == 0
}

class StackDs<T: Any>: StackFacade<T> {

    companion object {
        fun <T: Any>create(items: Iterable<T>): StackDs<T> {
            val stack = StackDs<T>()
            for (item in items) {  stack.push(item)
            }
            return stack
        }
    }

    private val storage = arrayListOf<T>()
    override fun push(element: T) {
        storage.add(element)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }
    override val count: Int
        get() = storage.size

    override fun pop(): T? {
        if (isEmpty) {
            return null
        }
        return storage.removeLast()
    }

    override fun toString() = buildString {
        appendLine("----top----")
        storage.asReversed().forEach {
            appendLine("$it")
        }
        appendLine("-----------")
    }
}

fun <T: Any> stackOf(vararg elements: T): StackDs<T>
{
    return StackDs.create(elements.asList())
}

fun main() {
    val stk = StackDs<Int>().apply {
        push(1)
        push(2)
        push(3)
        push(4)
    }
    println(stk)

    val poppedElement = stk.pop()
    if (poppedElement != null) {
        println("Popped: $poppedElement")
    }
    print(stk)

    val list = listOf("A", "B", "C", "D")
    val stack = StackDs.create(list)
    print(stack)
    println("Popped: ${stack.pop()}")

    val st = stackOf(1.0, 2.0, 3.0, 4.0)
    print(st)
    println("Popped: ${st.pop()}")

    println("(hello world".checkParentheses())
}


fun String.checkParentheses(): Boolean {
    val stack = StackDs<Char>()
    for (character in this) {
        when (character) {
            '(' -> stack.push(character)
            ')' -> if (stack.isEmpty) {
                return false
            } else {
                stack.pop()
            }
        } }
    return stack.isEmpty
}