import java.util.*

fun main(){

    println("Enter the number")
    var sc = Scanner(System.`in`)
    var num = sc.nextInt();

    for (i in 1..10)
        println("$num * $i = ${num.times(i)}")
}
