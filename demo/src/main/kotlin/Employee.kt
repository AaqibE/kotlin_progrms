class Employee(var name: String, var salary: Double, var department: String)

class EmployeeManager {
    val employees = mutableListOf<Employee>()

    fun addEmployee(name: String, salary: Double, department: String) {
        val newEmployee = Employee(name, salary, department)
        employees.add(newEmployee)
    }

    fun deleteEmployee(name: String) {
        val employeeToRemove = employees.find { it.name == name }
        employeeToRemove?.let { employees.remove(it) }
        println("Employee list after Deleted ${name}", )

    }

    fun updateEmployee(name: String, newSalary: Double, newDepartment: String) {
        println("Employee List after updated employee ${name}")
        val employeeToUpdate = employees.find { it.name == name }
        employeeToUpdate?.apply {
            salary = newSalary
            department = newDepartment
        }
    }
}

fun main() {
    val manager = EmployeeManager()

    manager.addEmployee("Aaqib", 50000.0, "HR")
    manager.addEmployee("Aaryan", 60000.0, "Trainer")
    manager.addEmployee("Binit", 55000.0, "Marketing")

    manager.deleteEmployee("Aaqib")

    manager.updateEmployee("Binit", 52000.0, "HR")

    manager.employees.forEach {
        println("Name: ${it.name}, Salary: ${it.salary}, Department: ${it.department}")
    }
}
