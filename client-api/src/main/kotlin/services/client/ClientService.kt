package services.client
import models.client.Client
import models.client.Results.ClientListResult
import models.generics.GenericResult
import repositorys.contracts.IClientRepository
import services.contracts.IClientService

class ClientService<UserListResult> : IClientService {
    val _clientRepository: IClientRepository
    constructor(clientRepository: IClientRepository){
        _clientRepository = clientRepository
    }
    override fun createClient(client: Client): GenericResult {
        try{
            _clientRepository.insertClient(client)
            return GenericResult("Usuário criado com sucesso", true)
        }catch (ex: Exception) {
            return GenericResult("Não foi possivel processar sua solicitação", false)
        }
    }
    override fun updateClient(client: Client) {
        _clientRepository.updateClient(client)
    }
    override fun removeClient(id: String) {
        _clientRepository.deleteClient(id)
    }
    override fun getClientById(id: String): Client {
        return _clientRepository.selectClient(id)
    }
    override fun getAllClients(): ClientListResult{
        try{
            val clients = _clientRepository.selectClients()
            return ClientListResult(resources = clients, success = true, message = "")
        }catch (ex: Exception) {
            return ClientListResult(null,
                false,
                message = "Não foi possivel recuperar a lista de clientes")
        }
    }
}