package services.client
import models.client.Client
import models.client.Results.ClientListResult
import models.client.Results.ClientResult
import models.generics.GenericResult
import org.slf4j.LoggerFactory
import repositorys.contracts.IClientRepository
import services.contracts.IClientService

class ClientService: IClientService {
    val _clientRepository: IClientRepository
    private val _logger = LoggerFactory.getLogger(ClientService::class.java)
    constructor(clientRepository: IClientRepository){
        _clientRepository = clientRepository
    }
    override fun createClient(client: Client): GenericResult {
        try{
            _clientRepository.insertClient(client)
            _logger.info("xxx")
            return GenericResult("Usuário criado com sucesso", true)

        }catch (ex: Exception) {
            throw Exception("Não foi possivel processar sua solicitação")

        }
    }
    override fun updateClient(client: Client): GenericResult {
        try{
            _clientRepository.selectClient(client.id)
            _clientRepository.updateClient(client)
            return GenericResult("Dados atualizados com sucesso", success = true)

        }catch (ex: NoSuchElementException){
            return GenericResult("Cliente com ID ${client.id} não encontrado",
                false)

        }catch (ex: Exception){
            throw Exception("Não foi possivel processar sua solicitação")
        }
    }
    override fun removeClient(id: String): GenericResult {
        try{
            _clientRepository.selectClient(id)
            _clientRepository.deleteClient(id)
            return GenericResult("Cliente removido com sucesso", success = true)

        }catch (ex: NoSuchElementException){
            return GenericResult("Cliente com ID $id não encontrado",
                false)

        }catch (ex: Exception){
            throw Exception("Não foi possivel processar sua solicitação")
        }
    }
    override fun getClientById(id: String): ClientResult {
        try{
            return ClientResult(
                resource = _clientRepository.selectClient(id),
                message = null, success = true
            )

        }catch (ex: NoSuchElementException){
            return ClientResult(resource = null,
                message = "Cliente com ID $id não encontrado",
                success = false)

        }catch (ex: Exception){
            throw Exception("Não foi possivel processar sua solicitação")
        }
    }
    override fun getAllClients(): ClientListResult{
        try{
            val clients = _clientRepository.selectClients()
            return ClientListResult(
                resources = clients,
                message = null,
                success = true
            )

        }catch (ex: Exception) {
            return ClientListResult(null,
                message = "Não foi possivel recuperar a lista de clientes",
                success = false
            )
        }
    }
}