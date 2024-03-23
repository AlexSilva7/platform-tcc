package controllers.V1
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.client.Extensions.toDomain
import models.client.Extensions.toResponse
import models.client.Requests.ClientRequest
import models.generics.Extensions.toResponse
import services.contracts.IClientService

fun Route.ClientController(clientService: IClientService){
    route("/api/v1/"){
        post("create-client"){
            try {
                val clientRequest = call.receive<ClientRequest>()
                val result = clientService.createClient(clientRequest.toDomain())
                val response = result.toResponse()

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest, response)
                }

                call.respond(response)

            }catch (e: BadRequestException) {
                call.respond(HttpStatusCode.BadRequest, "Erro de desserialização: ${e.message}")

            }catch(e: Exception){
                call.respond(HttpStatusCode.InternalServerError, e.message.toString())

            }
        }
        get("get-all-client"){
            try {
                val result = clientService.getAllClients()
                val response = result.toResponse()

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest, response)
                }

                call.respond(response)
            }catch(ex: Exception){
                call.respondText(ex.message.toString())
            }
        }
    }
}