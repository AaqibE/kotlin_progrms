fun categorizeTransaction(transactionType: String): String {
    return when (transactionType.toLowerCase()) {
        "food" -> "This transaction falls under the Food category."
        "utilities" -> "This transaction falls under the Utilities category."
        "entertainment" -> "This transaction falls under the Entertainment category."
        else -> "Unknown category for this transaction."
    }
}

fun main() {
    val transaction1 = "Food"
    val transaction2 = "Utilities"
    val transaction3 = "Entertainment"
    val transaction4 = "Shopping"

    println(categorizeTransaction(transaction1))
    println(categorizeTransaction(transaction2))
    println(categorizeTransaction(transaction3))
    println(categorizeTransaction(transaction4))
}
