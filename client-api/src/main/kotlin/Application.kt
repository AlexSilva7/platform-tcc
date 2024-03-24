
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

//    System.setProperty("logback.configurationFile", "logback.xml")
//    val _logger = LoggerFactory.getLogger(Application::class.java)
//    _logger.info("Iniciando aplicação")

    routing {
        swaggerUI(path = "openapi")
        ClientController(ClientService(ClientRepository()))
    }
}
