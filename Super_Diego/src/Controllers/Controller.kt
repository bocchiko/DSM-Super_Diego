package Controllers
import Models.User
import Models.Product
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class Controller {

    fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()
        val usersTxt = File("src/utils/users.txt")
        usersTxt.forEachLine { line ->
            val (id, name, password, role) = line.split(",")
            val user = User(id.toInt(),name,password,role)
            users.add(user)
        }
        return users
    }
    fun getAllProducts(): List<Product>{
        val products = mutableListOf<Product>()
        val productsTxt = File("src/utils/products.txt")
        productsTxt.forEachLine { line ->
            val (id, name, quantity, unitPrice) = line.split(",")
            val product = Product(id.toInt(), name, quantity.toInt(), unitPrice.toDouble())
            products.add(product)
        }
        return products
    }

    fun getCurrentDateTimeAsString(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        return LocalDateTime.now().format(formatter)
    }
}