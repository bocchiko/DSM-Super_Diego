package Controllers
import Models.User


class AuthenticationController : Controller() {
    companion object {
        var currentUser: User? = null
    }

    fun login(username: String, password: String): Boolean {
        val user = super.getAllUsers().find { it.username == username && it.password == password }
        if (user != null) {
            currentUser = user
            return true
        }
        return false
    }

}