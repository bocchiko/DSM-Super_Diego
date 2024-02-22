package Controllers
import Models.User
import java.io.File

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

}