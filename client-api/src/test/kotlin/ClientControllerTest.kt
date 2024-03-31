
import common.LoggerFactory
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import models.client.Client
import org.junit.Test
import org.mockito.Mockito.mock
import org.slf4j.Logger
import repositorys.contracts.FakeClientRepository
import services.client.ClientService
import java.time.LocalDate
import java.util.*

class ClientServiceTest {
    val mockLogger: Logger
    val loggerFactory: LoggerFactory
    val service: ClientService
    constructor(){
        mockLogger = mock(Logger::class.java)
        loggerFactory = LoggerFactory.getInstance(mockLogger)
        service = ClientService(FakeClientRepository())
    }

    @Test
    fun returnTrueWhenCreatingTheClient() {
        runBlocking {
            val result = service.createClient(
                Client(
                    id = UUID.randomUUID().toString(),
                    name = "Alex",
                    surname = "Silva",
                    email = "alexsaraujo92@gmail.com",
                    birthdate = LocalDate.of(1992, 12, 28)
                )
            )

            assertEquals(result.success, true)
            assertEquals(result.message, "User created successfully")
        }
    }

    @Test
    fun returnFalseWhenUpdatingClientWithAnIdThatDoesNotExist() {
        runBlocking {
            val result = service.updateClient(
                Client(
                    id = UUID.randomUUID().toString(),
                    name = "Joao",
                    surname = "Silva",
                    email = "alexsaraujo92@gmail.com",
                    birthdate = LocalDate.of(1992, 12, 28)
                )
            )

            assertEquals(result.success, false)
            assertEquals(result.message?.contains("not found"), true)
        }
    }

    @Test
    fun returnTrueWhenUpdatingClientWithAnExistingId() {
        runBlocking {
            service.createClient(
                Client(
                    id = UUID.randomUUID().toString(),
                    name = "Alex",
                    surname = "Silva",
                    email = "alexsaraujo92@gmail.com",
                    birthdate = LocalDate.of(1992, 12, 28)
                )
            )

            val resource = service.getAllClients().resources
            val result = service.updateClient(
                Client(
                    id = resource?.get(0)?.id.toString(),
                    name = "Alex",
                    surname = "Silva",
                    email = "alexaraujo_rj@yahoo.com.br",
                    birthdate = LocalDate.of(1992, 12, 28)
                )
            )

            assertEquals(result.success, true)
            assertEquals(result.message?.contains("Data updated successfully"), true)
        }
    }

    @Test
    fun returnFalseWhenRemovingClientWithAnIdThatDoesNotExist() {
        runBlocking {
            val result = service.removeClient(UUID.randomUUID().toString())
            assertEquals(result.success, false)
            assertEquals(result.message?.contains("not found"), true)
        }
    }

    @Test
    fun returnTrueWhenRemovingClientWithAnExistingId() {
        runBlocking {
            service.createClient(
                Client(
                    id = UUID.randomUUID().toString(),
                    name = "Alex",
                    surname = "Silva",
                    email = "alexsaraujo92@gmail.com",
                    birthdate = LocalDate.of(1992, 12, 28)
                )
            )

            val resource = service.getAllClients().resources
            val result = service.removeClient(resource?.get(0)?.id.toString())

            assertEquals(result.success, true)
            assertEquals(result.message?.contains("Client removed successfully"), true)
        }
    }
}
