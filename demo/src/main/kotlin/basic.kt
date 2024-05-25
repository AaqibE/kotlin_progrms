import java.util.*

fun main(){
    print("Enter the day number: ")
    var sc = Scanner(System.`in`)
    val day = sc.nextInt();
    val result = when (day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Day not found"
    }
    println(result)
}