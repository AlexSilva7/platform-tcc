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
import models.client.Requests.ClientUpdateRequest
import models.generics.Extensions.toResponse
import models.generics.GenericResponse
import services.contracts.IClientService

fun Route.ClientController(clientService: IClientService){

    route("/api/v1/"){
        post("create-client"){
            try {
                val clientRequest = call.receive<ClientRequest>()
                val result = clientService.createClient(clientRequest.toDomain())
                val response = result.toResponse()
                call.respond(response)

            }catch (ex: BadRequestException) {
                call.respond(HttpStatusCode.BadRequest,
                    GenericResponse("Verificar campos obrigatórios", false)
                )

            }catch(ex: Exception){
                call.respond(HttpStatusCode.InternalServerError,
                    GenericResponse(ex.message.toString(), false)
                )
            }
        }

        put("update-client"){
            try{
                val clientRequest = call.receive<ClientUpdateRequest>()
                val result = clientService.updateClient(clientRequest.toDomain())

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest,
                        GenericResponse(result.message, result.success)
                    )
                }

                val response = result.toResponse()
                call.respond(response)

            }catch(ex: Exception){
                call.respond(HttpStatusCode.InternalServerError,
                    GenericResponse(ex.message.toString(), false)
                )
            }
        }

        delete("remove-client/{id}") {
            try{
                val id = call.parameters["id"] ?:
                    return@delete call.respond(HttpStatusCode.BadRequest,
                        GenericResponse("Id do cliente deve ser informado por parâmetro",
                            false)
                    )

                val result = clientService.removeClient(id)

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest,
                        GenericResponse(result.message, result.success)
                    )
                }

                val response = result.toResponse()
                call.respond(response)

            }catch(ex: Exception){
                call.respond(HttpStatusCode.InternalServerError,
                    GenericResponse(ex.message.toString(), false)
                )
            }
        }

        get("get-client/{id}"){
            try {
                val id = call.parameters["id"] ?:
                return@get call.respond(HttpStatusCode.BadRequest,
                    GenericResponse("Id do cliente deve ser informado por parâmetro",
                        false)
                )

                val result = clientService.getClientById(id)

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest,
                        GenericResponse(result.message, false)
                    )
                }

                val response = result.toResponse()
                call.respond(response)

            }catch(ex: Exception){
                call.respondText(ex.message.toString())

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