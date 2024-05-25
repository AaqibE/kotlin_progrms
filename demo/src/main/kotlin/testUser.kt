class Trans(val id: Int, var amount: Double, val date: String, var category: String)

class User(val username: String,  val password: String,  val transactions: MutableList<Trans>) {
    fun login(inputUsername: String, inputPassword: String): Boolean {
        return username == inputUsername && password == inputPassword
    }


    //method for displaying user expenses after successfully login
    fun displayExpensesSummary() {
        println("Expenses Summary for $username:")
        transactions.groupBy { it.category }.forEach { (category, transactions) ->
            val total = transactions.sumOf { it.amount }
            println("$category: \$$total")
        }
    }
}

//method for fetching transaction details using higher order function
fun getTransactionDetails(transactions: List<Trans>, category: String) {
    println("Transactions for category: $category")
    transactions.filter { it.category == category }.forEach { transaction ->
        println("ID: ${transaction.id}, Amount: ${transaction.amount}, Date: ${transaction.date}")
    }
}


//method for deleting transaction using ID
fun deleteTransaction(transactions: MutableList<Trans>, transactionId: Int) {
    transactions.removeAll { it.id == transactionId }
    println("Transactions after deletion:")
    transactions.forEach { println("ID: ${it.id}, Amount: ${it.amount}, Date: ${it.date}, Category: ${it.category}") }
}

//method for updating transaction using ID
fun updateTransaction(transactions: MutableList<Trans>, transactionId: Int, newAmount: Double, newCategory: String) {
    transactions.find { it.id == transactionId }?.let { transaction ->
        transaction.amount = newAmount
        transaction.category = newCategory
    }
    println("Transactions after update:")
    transactions.forEach { println("ID: ${it.id}, Amount: ${it.amount}, Date: ${it.date}, Category: ${it.category}") }
}

fun main() {
    val transactions = mutableListOf(
        Trans(1, 20.0, "2024-05-16", "Food"),
        Trans(2, 200.0, "2024-05-16", "Entertainment"),
        Trans(3, 70.0, "2024-05-17", "Utilities"),
        Trans(4, 30.0, "2024-05-18", "Food")
    )

    val user = User("JohnDoe", "password123", transactions)

    if (user.login("JohnDoe", "password123")) {
        println("Login successful!")
        user.displayExpensesSummary()
    } else {
        println("Login failed!")
    }

    deleteTransaction(transactions, 2)  // Deletes the transaction with ID 2
    updateTransaction(transactions, 3, 100.0, "Healthcare")  // Updates the transaction with ID 3

    println("Updated Transactions:")
    transactions.forEach { println("ID: ${it.id}, Amount: ${it.amount}, Date: ${it.date}, Category: ${it.category}") }
}