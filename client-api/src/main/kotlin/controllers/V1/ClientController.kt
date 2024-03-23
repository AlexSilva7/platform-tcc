package controllers.V1
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.Client
import services.contracts.IClientService
fun Route.ClientController(clientService: IClientService){
    route("/clients"){
        get{
            try {
                val pessoa = Client(
                    id = "aa",
                    name = "Alex",
                    surname = "",
                    email = "",
                    birthdate = null
                )
                call.respond(pessoa)
            }catch(ex: Exception){
                call.respondText(ex.message.toString())
            }
        }
    }
}