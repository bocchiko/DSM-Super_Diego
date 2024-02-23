package Controllers

import Models.Product

class InventoryController : Controller() {

    private lateinit var products: List<Product>

    init {
        getProducts()
    }

    fun getProducts(): List<Product> {
        products = super.getAllProducts().toMutableList()
        return products
    }

    fun getInventory(command: String): List<Product> {
        if(command == "TODOS"){
            return products
        }else {
            val product = products.find { it.id.toString() == command || it.name == command }
            return if (product != null) {
                listOf(product)
            } else {
                emptyList() // Return an empty list if product not found
            }
        }
    }
}