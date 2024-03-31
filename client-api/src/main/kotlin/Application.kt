
import com.typesafe.config.ConfigFactory
import controllers.V1.ClientController
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import services.client.ClientService

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val config = HoconApplicationConfig(ConfigFactory.load())
    val dependencyResolver = DependencyResolver(config)
    val loggerFactory = common.LoggerFactory.getInstance(LoggerFactory.getLogger(Application::class.java))

    routing {
        swaggerUI(path = "openapi")
        ClientController(ClientService(dependencyResolver._clientRepository), loggerFactory)
    }
}
