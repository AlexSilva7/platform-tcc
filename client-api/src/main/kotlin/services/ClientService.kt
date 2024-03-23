package services

import models.Client
import repositorys.contracts.IClientRepository
import services.contracts.IClientService
class ClientService : IClientService {
    val _clientRepository: IClientRepository
    constructor(clientRepository: IClientRepository){
        _clientRepository = clientRepository
    }
    override fun createClient(client: Client) {
        _clientRepository.insertClient(client)
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
    override fun getAllClients(): MutableList<Client> {
        return _clientRepository.selectClients()
    }
}