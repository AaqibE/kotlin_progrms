class Transaction(val id: Int, var amount: Double, val date: String, var category: String)

fun getTransactionDetails(transactions: List<Transaction>, category: String) {
    println("Transactions for category: $category")
    transactions.filter { it.category == category }.forEach { transaction ->
        println("ID: ${transaction.id}, Amount: ${transaction.amount}, Date: ${transaction.date}")
    }
}

fun deleteTransaction(transactions: MutableList<Transaction>, transactionId: Int) {
    transactions.removeAll { it.id == transactionId }
    println("Transactions after deletion:")
    transactions.forEach { println("ID: ${it.id}, Amount: ${it.amount}, Date: ${it.date}, Category: ${it.category}") }
}

fun updateTransaction(transactions: MutableList<Transaction>, transactionId: Int, newAmount: Double, newCategory: String) {
    transactions.find { it.id == transactionId }?.let { transaction ->
        transaction.amount = newAmount
        transaction.category = newCategory
    }
    println("Transactions after update:")
    transactions.forEach { println("ID: ${it.id}, Amount: ${it.amount}, Date: ${it.date}, Category: ${it.category}") }
}

fun main() {
    val transactions = mutableListOf(
        Transaction(1, 20.0, "2024-05-16", "Food"),
        Transaction(2, 200.0, "2024-05-16", "Entertainment"),
        Transaction(3, 70.0, "2024-05-17", "Utilities"),
        Transaction(4, 30.0, "2024-05-18", "Food")
    )

    println("Initial Transactions:")
    transactions.forEach { println("ID: ${it.id}, Amount: ${it.amount}, Date: ${it.date}, Category: ${it.category}") }

    // Delete a transaction by ID
//    deleteTransaction(transactions, 2)  // Deletes the transaction with ID 2

    // Update a transaction by ID
//    updateTransaction(transactions, 3, 100.0, "Healthcare")  // Updates the transaction with ID 3

    // Displaying the current state of transactions
    println("Updated Transactions:")
    transactions.forEach { println("ID: ${it.id}, Amount: ${it.amount}, Date: ${it.date}, Category: ${it.category}") }
}