package Controllers
import Models.User


class AuthenticationController : Controller() {
    companion object {
        var currentUser: User? = null
    }

    fun login(username: String, password: String): Boolean {
        try {
            val user = super.getAllUsers().find { it.username == username && it.password == password }
            if (user != null) {
                currentUser = user
                return true
            }
            return false
        } catch (e: Exception) {
            logError("Error al intentar realizar el login: ", e)
            return false
        }
    }
}