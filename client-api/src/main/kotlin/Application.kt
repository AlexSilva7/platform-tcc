
import controllers.V1.ClientController
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import repositorys.contracts.ClientRepository
import services.client.ClientService

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
/*
    install(RequestValidation) {
        validate<Requests.ClientRequest> { client ->
            if (client.birthdate == null)
                ValidationResult.Invalid("A customer ID should be greater than 0")
            else ValidationResult.Valid
        }
    }
*/
    routing {
        swaggerUI(path = "openapi")
        ClientController(ClientService<Any>(ClientRepository()))
    }
}
