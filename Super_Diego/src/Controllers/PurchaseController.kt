package Controllers

import Models.Product
import java.io.File

class PurchaseController : Controller() {

    private var products: MutableList<Product> = mutableListOf()

    init {
        getProducts()
    }

    fun getProducts(): List<Product> {
        products = super.getAllProducts().toMutableList()
        return products
    }

    fun createPurchase(operation: String): Boolean{
        try {
            val operations = operation.split(",").chunked(2) { (productId, quantity) ->
                Pair(productId.toInt(), quantity.toInt())
            }
            for ((productId, quantity) in operations) {
                val product = products.find { it.id == productId } ?: return false
                if (product.quantity < quantity) {
                    // Not enough quantity available
                    return false
                }
            }
            for ((productId, quantity) in operations) {
                val product = products.find { it.id == productId } ?: return false
                product.quantity -= quantity
            }
            saveInFile(products)
            createTicket(operations)
            // Purchase successful
            return true
        } catch (e: Exception) {
            logError("Error al intentar crear una compra: ", e)
            return false
        }
    }

    //methods to execute
    private fun createTicket(operations: List<Pair<Int,Int>>){
        println("  ~~~ FACTURA DE COMPRA ~~~ ")
        println("     ~~~ SUPER DIEGO ~~~ ")

        var total = 0.0

        for ((productId, quantity) in operations) {
            val product = products.find { it.id == productId }
            if (product != null) {
                val subtotal = product.pricePerUnit * quantity
                println("${product.name} --- $quantity x $${"%.2f".format(product.pricePerUnit)} = $%.2f".format(subtotal))
                total += subtotal
            }
        }
        if (total > 20) {
            val discount = total * 0.03
            total -= discount
            println("Descuento (3%): $${"%.2f".format(discount)}")
        }

        println("Total de factura: $%.2f".format(total))
        saveTicketToFile(operations,"src/utils/tickets/Ticket_"+getCurrentDateTimeAsString()+".txt")
    }

    private fun saveInFile(products: List<Product>) {
        val file = File("src/utils/products.txt")
        file.bufferedWriter().use { writer ->
            products.forEach { product ->
                writer.write("${product.id},${product.name},${product.quantity},${product.pricePerUnit}\n")
            }
        }
    }

    private fun saveTicketToFile(operation: List<Pair<Int, Int>>, filename: String) {
        val file = File(filename)
        file.bufferedWriter().use { writer ->
            writer.write("  ~~~ FACTURA DE COMPRA ~~~\n")
            writer.write("     ~~~ SUPER DIEGO ~~~\n\n")

            var total = 0.0

            for ((productId, quantity) in operation) {
                val product = products.find { it.id == productId }
                if (product != null) {
                    val subtotal = product.pricePerUnit * quantity
                    writer.write("${product.name} --- $quantity x $${"%.2f".format(product.pricePerUnit)} = $%.2f\n".format(subtotal))
                    total += subtotal
                }
            }

            if (total > 20) {
                val discount = total * 0.03
                total -= discount
                writer.write("Descuento (3%): $${"%.2f".format(discount)}\n")
            }

            writer.write("Total de factura: $%.2f\n".format(total))
        }
    }
}