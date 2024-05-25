class Car {
    var name: String = ""
    var color: String = ""
    var price: Int = 0


    fun getCarInfo(): String {
        return "$name, $color, $price"
    }

}

fun main(){
    var myCar1 = Car()
    myCar1.name = "BMW"
    myCar1.color = "Black"
    myCar1.price = 67000

    var myCar2 = Car()
    myCar2.name = "Audi"
    myCar2.color = "White"
    myCar2.price = 80000


    var myCar3 = Car()
    myCar3.name = "SUV"
    myCar3.color = "Blue"
    myCar3.price = 40000

    var allCar = arrayOf(myCar1, myCar2, myCar3)

    var comparator = compareBy<Car> {it.price}
    var getCarByPrice = allCar.asList().stream().min(comparator).get()
    println(getCarByPrice.getCarInfo())

}



