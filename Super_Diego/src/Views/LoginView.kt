package Views

import Controllers.AuthenticationController

class LoginView {

    fun AttemptLoginView(){
        println(" ~~~ BIENVENIDO AL SISTEMA SUPER DIEGO ~~~")
        println("     ~~~ INGRESE SUS CREDENCIALES ~~~")

        var loggedIn = false
        do {
            print("Usuario: ")
            val username = readLine()
            print("Contraseña: ")
            val password = readLine()

            loggedIn = AuthenticationController().login(username.toString(), password.toString())

            if (!loggedIn) {
                println("Login failed. Invalid username or password.")
            }
        } while (!loggedIn)

        if(AuthenticationController.currentUser?.role == "Admin"){
            AdminMenu()
        }else if(AuthenticationController.currentUser?.role == "Vendedor"){
            VendedorMenu()
        }else if (AuthenticationController.currentUser?.role == "Invitado"){
            InvitadoMenu()
        }else{
            //Invitado
            println("Usted no posee un rol conocido, Es por ello que se le asigno rol Invitado !!!")
            InvitadoMenu()
        }
    }

    private fun AdminMenu(){
        println("BIENVENIDO , ${AuthenticationController.currentUser?.username}!")
        println(" ¿Que Desea Hacer ?")
        do{
            println("1) Venta Nueva")
            println("2) Consultar Inventario")
            println("3) Salir ")

            val opcion = readLine()?.toIntOrNull()

            when (opcion) {
                1 -> PurchaseView().purchaseIndex()
                2 -> InventoryView().inventoryIndex()
                3 -> logOut()
                else -> println("Opción no válida. Inténtelo nuevamente.")
            }
        }while (opcion != 3)
    }

    private fun VendedorMenu(){
        println("BIENVENIDO , ${AuthenticationController.currentUser?.username}!")
        println(" ¿Que Desea Hacer ?")
        do{
            println("1) Venta Nueva")
            println("2) Consultar Inventario")
            println("3) Salir ")

            val opcion = readLine()?.toIntOrNull()

            when (opcion) {
                1 -> PurchaseView().purchaseIndex()
                2 -> InventoryView().inventoryIndex()
                3 -> logOut()
                else -> println("Opción no válida. Inténtelo nuevamente.")
            }
        }while (opcion != 3)
    }

    private fun InvitadoMenu(){
        println("BIENVENIDO , ${AuthenticationController.currentUser?.username}!")
        println(" ¿Que Desea Hacer ?")
        do{
            println("1) Consultar Inventario")
            println("2) Salir ")

            val opcion = readLine()?.toIntOrNull()

            when (opcion) {
                1 -> InventoryView().inventoryIndex()
                2 -> logOut()
                else -> println("Opción no válida. Inténtelo nuevamente.")
            }
        }while (opcion != 2)
    }

    private fun logOut(){
        println("Hasta Luego !!!")
    }

}