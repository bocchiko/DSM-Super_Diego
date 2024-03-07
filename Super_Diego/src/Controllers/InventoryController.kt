package Controllers

import Models.Product

class InventoryController : Controller() {

    private lateinit var products: List<Product>

    init {
        getProducts()
    }

    fun getProducts(): List<Product> {
        try {
            products = super.getAllProducts().toMutableList()
            return products
        } catch (e: Exception) {
            logError("Error al intentar obtener productos: ", e)
            return emptyList()
        }
    }

    fun getInventory(command: String): List<Product> {
        try {
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
        } catch (e: Exception) {
            logError("Error al intentar obtener inventario: ", e)
            return emptyList()
        }
    }
}