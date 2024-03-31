package services.client
import common.LoggerFactory
import models.client.Client
import models.client.Results.ClientListResult
import models.client.Results.ClientResult
import models.generics.GenericResult
import repositorys.contracts.IClientRepository
import services.contracts.IClientService

class ClientService: IClientService {
    private val _clientRepository: IClientRepository
    private val _logger: LoggerFactory
    constructor(clientRepository: IClientRepository){
        _clientRepository = clientRepository
        _logger = LoggerFactory.getInstance(org.slf4j.LoggerFactory.getLogger(ClientService::class.java))
    }
    override suspend fun createClient(client: Client): GenericResult {
        try{
            _logger.info("ClientService :: createClient :: " +
                "${client.id}, ${client.name}, ${client.surname}, ${client.email}, ${client.birthdate}")

            _clientRepository.insertClient(client)

            _logger.info("ClientService :: createClient :: User created successfully :: " +
                "${client.id}, ${client.name}, ${client.surname}, ${client.email}, ${client.birthdate}")

            return GenericResult("User created successfully", true)

        }catch (ex: Exception) {
            _logger.error("ClientService :: createClient :: $ex")
            throw Exception("We were unable to process your request")

        }
    }
    override suspend fun updateClient(client: Client): GenericResult {
        try{
            _logger.info("ClientService :: updateClient :: " +
                "${client.id}, ${client.name}, ${client.surname}, ${client.email}, ${client.birthdate}")

            _clientRepository.selectClient(client.id)
            _clientRepository.updateClient(client)

            _logger.info("ClientService :: updateClient :: Data updated successfully :: " +
                    "${client.id}, ${client.name}, ${client.surname}, ${client.email}, ${client.birthdate}")

            return GenericResult("Data updated successfully", success = true)

        }catch (ex: NoSuchElementException){
            _logger.info("ClientService :: updateClient :: Customer with ID ${client.id} not found")
            return GenericResult("Customer with ID ${client.id} not found",
                false)

        }catch (ex: Exception){
            _logger.error("ClientService :: updateClient :: $ex")
            throw Exception("We were unable to process your request")
        }
    }
    override suspend fun removeClient(id: String): GenericResult {
        try{
            _logger.info("ClientService :: removeClient :: $id")

            _clientRepository.selectClient(id)
            _clientRepository.deleteClient(id)

            _logger.info("ClientService :: removeClient :: Client removed successfully :: $id")

            return GenericResult("Client removed successfully", success = true)

        }catch (ex: NoSuchElementException){
            _logger.info("ClientService :: removeClient :: Customer with ID $id not found")
            return GenericResult("Customer with ID $id not found",
                false)

        }catch (ex: Exception){
            _logger.error("ClientService :: removeClient :: $ex")
            throw Exception("We were unable to process your request")
        }
    }
    override suspend fun getClientById(id: String): ClientResult {
        try{
            _logger.info("ClientService :: getClientById :: $id")

            var client = _clientRepository.selectClient(id)

            _logger.info("ClientService :: getClientById :: " +
                "${client.id}, ${client.name}, ${client.surname}, ${client.email}, ${client.birthdate}")

            return ClientResult(
                resource = client,
                message = null,
                success = true
            )

        }catch (ex: NoSuchElementException){
            _logger.info("ClientService :: getClientById :: Customer with ID $id not found")
            return ClientResult(
                resource = null,
                message = "Customer with ID $id not found",
                success = false)

        }catch (ex: Exception){
            _logger.error("ClientService :: getClientById :: $ex")
            throw Exception("We were unable to process your request")
        }
    }
    override suspend fun getAllClients(): ClientListResult{
        try{
            _logger.info("ClientService :: getAllClients")

            val clients = _clientRepository.selectClients()

            _logger.info("ClientService :: getAllClients :: " +
                    "${clients.size}")

            return ClientListResult(
                resources = clients,
                message = null,
                success = true
            )

        }catch (ex: Exception) {
            _logger.error("ClientService :: getAllClients :: $ex")

            return ClientListResult(null,
                message = "Unable to retrieve customer list",
                success = false
            )
        }
    }
}