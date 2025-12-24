package com.example.androidreview

import android.R
import java.util.Stack

fun main() {
    val list = listOf(1, 2, 3, 4, 5,2,3,6,4)
   // println(newLists(list))
    val arr = intArrayOf(1,2,3,6,6,7)
   // getConcatenation(arr)

    val s = "   fly me   to   the moon  "
  // println(lengthOfLastWord(s))
    val myArr = intArrayOf(1,2,2,3)
    val secArr= intArrayOf(6,5,4,4)
    val thirdArr= intArrayOf(1,3,2,4)

//    println(isMonotonic(myArr))
//    println(isMonotonic(secArr))
//    println(isMonotonic(thirdArr))

    var test = "{[()}"
    val solution = Solution()
   // println(solution.isValid(test))

    var word = "abbaca"
    var myWord = "aazxz"
    val solution2 = Solution2()
    //println(solution2.removeDuplicates(word))
   //
// println(solution2.removeDuplicates(myWord))

    val nums= intArrayOf(1,2,1,5,10,9,6,3)
    val numss= intArrayOf(1,2,3,4,3,8)
    val solution3 = Solution3()

  //  println(solution3.nextGreaterElements(nums).joinToString(", "))
    //println(solution3.nextGreaterElements(numss).joinToString(", "))

   // println(sellectionSort(numss).joinToString(", "))
    //println(bubbleSort(nums).joinToString (", "))


    val solution4 = Solution4()
    solution4.duplicateZeros(intArrayOf(1,0,2,3,0,4,5,0))

}







////////////////////////////////////////////////
fun newLists(basicList: List<Int>): Boolean{
    if(basicList.size%2 !=0){
        return false
    }
    else {
        val freq = HashMap<Int, Int>()
        for (num in basicList) {
            freq[num] = 1 + freq.getOrDefault(num, 0)
            if (freq[num]!! > 2) {
                return false
            }

        }
        return true
    }
}

fun getConcatenation(nums: IntArray): IntArray {
    val n = nums.size
    val result = IntArray(2*n)
    for(i in result.indices){
        if (i < n){
            result[i]= nums[i]
        }
        if(i>=n){
            result[i]= nums[i-n]

        }
    }

    return result

}
fun lengthOfLastWord(s: String): Int {
    val text = s.split(' ').filter { it.isNotEmpty() }
    val size = text.size
    val myWard =text[size-1]
    return myWard.length

}

fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0

    for (i in haystack.indices) {
        // Check if we have enough characters remaining
        if (i + needle.length > haystack.length) {
            break
        }

        // Check if substring starting at i matches needle
        var match = true
        for (j in needle.indices) {
            if (haystack[i + j] != needle[j]) {
                match = false
                break
            }
        }

        if (match) {
            return i
        }
    }

    return -1  // Return -1 if not found (standard convention)
}

fun isMonotonic(nums: IntArray): Boolean {

    var inc = true
    var dec = true
//    var inc = false
//    var dec = false

    for(i in 0 until nums.size-1){
//        if(nums[i]<=nums[i+1]) {
//            inc = true
//        }
//        if (nums[i]>=nums[i+1]) {
//            dec = true
//        }
        if(nums[i]>nums[i+1]) {
            inc = false
        }
        if (nums[i]<nums[i+1]) {
            dec = false
        }
    }
    return inc||dec

}

class Solution {
    fun isValid(s: String): Boolean {

        if (s.length%2 != 0)
            return false
        val stack = Stack<Char>()
        if(s.isEmpty())
           return true
        for(char in s){
             if (char == ('(') || char == ('{') || char == ('[')){
                stack.push(char)
            } else{
                if(stack.isEmpty())
                    return false
                else {
                    val top = stack.pop()
                    if (char == ')' && top != '('
                        || char == '}' && top != '{'
                        || char == ']' && top != '['
                    ) {
                         return false
                    }
                }

            }

        }
        if(stack.isEmpty())
            return true
        else return false

    }
}

class Solution2 {
    val myStack = Stack<Char>()
    fun removeDuplicates(s: String): String {
        for(char in s ){
            if(char == myStack.lastOrNull()){
                myStack.pop()
            } else {
                myStack.push(char)

            }
        }
        val result = myStack.joinToString("")

  return  result
    }
}

class Solution3 {
    fun nextGreaterElements(nums: IntArray): IntArray {
        val myArr = mutableListOf<Int>()

        for (i in nums.indices) {
            val key = nums[i]
            var found = false

            for (j in  1 until nums.size) {
                //control the step to move to accept forward and circular
                val k = (i+j)% nums.size
                if (nums[k] > key) {
                    myArr.add(nums[k])
                    found = true
                    break
                }
            }
            if (!found) {
                myArr.add(-1)

            }
        }
        return myArr.toIntArray()
    }
}





fun sellectionSort(arr: IntArray): IntArray {
    for (i in 0 until arr.size - 1){
        var minIndex = i
         for(j in i+1 until arr.size){
             if(arr[minIndex] > arr[j]){
               minIndex=j
             }
         }
        var temp = arr[i]
        arr[i]= arr[minIndex]
        arr[minIndex]= temp
    }
    return arr
}

fun bubbleSort(arr: IntArray): IntArray {
    for (i in 0 until arr.size - 1){
        for(j in 0 until arr.size -i - 1){
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
    return arr
}

class Solution4 {
    fun duplicateZeros(arr: IntArray): Unit {

      val myStack = Stack<Int>()
            for(i in arr){
                if (myStack.size<arr.size){
                    myStack.push(i)
                }
                if(i==0 && myStack.size<arr.size){
                    myStack.push(0)
                }

            }
        for(i in arr.size -1 downTo 0){
            arr[i]= myStack.pop()
        }



    }
}