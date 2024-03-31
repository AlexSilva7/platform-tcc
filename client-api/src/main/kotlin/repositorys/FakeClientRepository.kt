package repositorys.contracts
import models.client.Client
class FakeClientRepository : IClientRepository {

    val _clients: MutableList<Client> = mutableListOf()

    suspend override fun insertClient(client: Client) {
        _clients.add(client)
    }

    suspend override fun updateClient(client: Client) {
        val index = _clients.indexOfFirst { it.id == client.id }
        _clients[index] = client
    }

    suspend override fun deleteClient(id: String) {
        _clients.removeIf { it.id == id }
    }

    suspend override fun selectClient(id: String): Client {
        return _clients.find { it.id == id }
            ?: throw NoSuchElementException()
    }

    suspend override fun selectClients(): MutableList<Client> {
        return _clients
    }
}