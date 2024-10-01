package functional.tests

class Garage {

    String owner

    // GORM Inheritance not working in Groovy 4
    //static hasMany = [vehicles: Vehicle]

}
