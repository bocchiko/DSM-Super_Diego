package Views

import Controllers.InventoryController

import java.util.*

class InventoryView {

    fun inventoryIndex(){
        println("Productos en Inventario: ")

        for (product in InventoryController().getProducts()) {
            println("${product.id} - ${product.name}")
        }
        print("Ingrese ID o Nombre del Producto o la palabra TODOS: ")
        val command = readlnOrNull()

        var result = InventoryController().getInventory(command.toString())
        println("PRODUCTO    CANTIDAD:UNIDADES")

        for (product in result ){
            println("${product.id} - ${product.name} - ${product.quantity}")
        }

        val s = Scanner(System.`in`)
        print("Presione cualquier tecla para continuar . . . ")
        s.nextLine()
    }
}