
fun main(){
    val arr = arrayOf(12, 14, 56, 78, "Hii")
    for(i in arr.indices){
        println(arr[i])
    }
    println(arr[2])
    println(arr.indices)

    arr.forEach { elements-> println(elements) }
}

