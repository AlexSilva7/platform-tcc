package repositorys.contracts
import models.Client
interface IClientRepository {
    fun insertClient(client: Client)
    fun updateClient(client: Client)
    fun deleteClient(id: String)
    fun selectClient(id: String): Client
    fun selectClients(): MutableList<Client>
}