package array_ds

// Big O notation is a mathematical notation that describes the limiting behaviour of a function where the argument tends
// towards a particular value of infinity.
//We use Big O to describe the performance of an algorithm

// Constant time algorithm takes same time and does not directly correlate with the number of input

// Linear complexity O(n) grows linearly and in direct correlation to the number of input

// Quadratic Complexity O(n^2) time algorithms are pretty much slower in comparison to linear complexity algorithm

// Logarithm complexity O(log n) time algorithm is more efficient than linear time or quadratic time algorithm. This algorithm is mainly implemented in binary search

//Exponential Time complexity O(2^n) this is the inverse of the logarithm growth and as such can be extremely slow


//space complexity is the additional space that should be assigned relative to the size of the input. we prefer to focus on runtime complexity as this is more crucial


// Arrays

/** It is crucial to remember than integers in java takes 4 byte of memory **/

// Looking up items by their index in an array has a constant time complexity irrespective of the index or size of the array

// Looking up items by value has a time complexity of O(n)

// Insertion into arrays in java is an O(n) operation
//deleting an item from an array has a complexity of O(n) because we need to shift elements to fill the vacuum

// It is important to know that the kotlin ArrayList is well optimized and has all the cool stuff implemented here built in

class ArrayDs<T>  constructor(private val arraySize: Int){
    private var items: ArrayList<T> = ArrayList(arraySize) // ArrayList(arraySize)

    private var count: Int = 0


    fun print(){
        println(items.size)
        for (index in 0 until count){
            println(items[index])
        }
    }


    fun insert(item : T){
        // if the array is full, it needs to be resized
        if(items.size == count){

            //create a new array (twice the size)
            val newItems : ArrayList<T> = ArrayList(count * 2)

            // copy all the existing items
            newItems.addAll(items)

            // set items to this new array
            items = newItems
        }

        // add the new item to the end of the current array
        items.add(count++, item)
    }

    fun removeAt(index: Int){

        // Validate the index
        if(index < 0 || index >= count){
            throw java.lang.IllegalArgumentException()
        }

        //Shift the item to the left to fill the hole
        for(i in index until  count-1){

            items[i] = items[i + 1]
        }
        count--
    }

    fun indexOf(item: T): Int{
        // if we find it, return index
        //otherwise, return -1
        for(i in 0 until count){
            if(items[i] == item){
                return i
            }
        }
        return -1;
    }

    fun max(): T{
        if(count == 0) throw java.lang.IllegalArgumentException()
        var current = items[0]
        for( i in 0 until count){
            if( (current as Int) < (items[i] as Int)){
                current = items[i];
            }
        }
        return  current;
    }


    fun reverse(){
        var start = count-1

        //create a new array
        val newItems : ArrayList<T> = ArrayList(count)
        while (start >= 0){

            newItems.add(items[start])
            start--
        }

        println(newItems.toString())
    }

//    fun insertAt(item: T,  index: Int){
//        var currentArray : ArrayList<T> = ArrayList<T>(count).apply { addAll(items) }
//
//        for(i in index until )
//
//    }

}



fun main() {
    val inst = ArrayDs<Int>(2)
    inst.insert(5)
    inst.insert(6)
    inst.insert(9)
    inst.insert(2)
    inst.removeAt(0)
    inst.reverse()
    println("Maximum number is ${inst.max()}")
    println("Index of 9 is ${inst.indexOf(9)}" )
    inst.print()
}

