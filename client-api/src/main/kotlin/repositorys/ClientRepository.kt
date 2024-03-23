package repositorys.contracts

import models.Client

class ClientRepository : IClientRepository {

    val _clients: MutableList<Client> = mutableListOf()

    override fun insertClient(client: Client) {
        _clients.add(client)
    }

    override fun updateClient(client: Client) {
        val index = _clients.indexOfFirst { it.id == client.id }
        _clients[index] = client
    }

    override fun deleteClient(id: String) {
        _clients.removeIf { it.id == id }
    }

    override fun selectClient(id: String): Client {
        return _clients.find { it.id == id }
            ?: throw NoSuchElementException("Cliente com ID $id não encontrado")
    }

    override fun selectClients(): MutableList<Client> {
        return _clients
    }
}