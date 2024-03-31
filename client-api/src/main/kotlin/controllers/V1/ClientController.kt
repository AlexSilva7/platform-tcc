package controllers.V1
import common.LoggerFactory
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

fun Route.ClientController(clientService: IClientService, logger: LoggerFactory){

    route("/api/v1/"){
        post("create-client"){
            try {
                logger.info("ClientController :: create-client")

                val clientRequest = call.receive<ClientRequest>()
                logger.info("ClientController :: create-client :: Request: $clientRequest")

                val result = clientService.createClient(clientRequest.toDomain())
                val response = result.toResponse()

                logger.info("ClientController :: create-client :: Response: $response")

                call.respond(response)

            }catch (ex: BadRequestException) {
                logger.info("ClientController :: create-client :: BadRequest: ${ex.message}")

                call.respond(HttpStatusCode.BadRequest,
                    GenericResponse("Check required fields", false)
                )

            }catch(ex: Exception){
                logger.error("ClientController :: create-client :: $ex")

                call.respond(HttpStatusCode.InternalServerError,
                    GenericResponse(ex.message.toString(), false)
                )
            }
        }

        put("update-client"){
            try{
                logger.info("ClientController :: update-client")

                val clientRequest = call.receive<ClientUpdateRequest>()
                logger.info("ClientController :: update-client :: Request: $clientRequest")

                val result = clientService.updateClient(clientRequest.toDomain())
                val response = result.toResponse()

                logger.info("ClientController :: get-client :: Response: $response")

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest,
                        GenericResponse(result.message, result.success)
                    )
                }

                call.respond(response)

            }catch(ex: Exception){
                logger.error("ClientController :: update-client :: $ex")

                call.respond(HttpStatusCode.InternalServerError,
                    GenericResponse(ex.message.toString(), false)
                )
            }
        }

        delete("remove-client/{id}") {
            try{
                logger.info("ClientController :: remove-client/{id}")
                val id = call.parameters["id"] ?:
                    return@delete call.respond(HttpStatusCode.BadRequest,
                        GenericResponse("Customer ID must be provided by parameter",
                            false)
                    )

                logger.info("ClientController :: remove-client :: RequestId: $id")

                val result = clientService.removeClient(id)
                val response = result.toResponse()

                logger.info("ClientController :: remove-client :: Response: $response")

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest,
                        GenericResponse(result.message, result.success)
                    )
                }

                call.respond(response)

            }catch(ex: Exception){
                logger.error("ClientController :: remove-client :: $ex")

                call.respond(HttpStatusCode.InternalServerError,
                    GenericResponse(ex.message.toString(), false)
                )
            }
        }

        get("get-client/{id}"){
            try {
                logger.info("ClientController :: get-client/{id}")
                val id = call.parameters["id"] ?:
                return@get call.respond(HttpStatusCode.BadRequest,
                    GenericResponse("Customer ID must be provided by parameter",
                        false)
                )

                logger.info("ClientController :: get-client :: RequestId: $id")

                val result = clientService.getClientById(id)
                val response = result.toResponse()

                logger.info("ClientController :: get-client :: Response: $response")

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest,
                        GenericResponse(result.message, false)
                    )
                }

                call.respond(response)

            }catch(ex: Exception){
                logger.error("ClientController :: get-client :: $ex")

                call.respond(HttpStatusCode.InternalServerError,
                    GenericResponse(ex.message.toString(), false)
                )
            }
        }

        get("get-all-client"){
            try {
                logger.info("ClientController :: get-all-client")

                val result = clientService.getAllClients()
                val response = result.toResponse()

                logger.info("ClientController :: get-client :: Response: $response")

                if(!result.success){
                    call.respond(HttpStatusCode.BadRequest, response)
                }

                call.respond(response)

            }catch(ex: Exception){
                logger.error("ClientController :: get-all-client :: $ex")
                call.respondText(ex.message.toString())

            }
        }
    }
}