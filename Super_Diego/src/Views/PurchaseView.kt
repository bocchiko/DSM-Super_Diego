package Views

import Controllers.PurchaseController
import java.util.*

class PurchaseView {

    fun purchaseIndex(){
        println("Productos en Venta: ")

        for (product in PurchaseController().getProducts()) {
            println("${product.id} - ${product.name} - ${product.pricePerUnit}")
        }
        print("Ingrese su Compra (id,cantidad), (ej: 1,2,4,1): ")
        val operation = readlnOrNull()

        val createPurchase = PurchaseController().createPurchase(operation.toString())

        if(!createPurchase){
            println("Un Producto no posee la Cantidad que usted digito, Intente Nuevamente !")
        }
        val s = Scanner(System.`in`)
        print("Presione cualquier tecla para continuar . . . ")
        s.nextLine()
    }
}