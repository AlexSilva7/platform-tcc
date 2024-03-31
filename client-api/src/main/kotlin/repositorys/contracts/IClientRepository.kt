package repositorys.contracts
import models.client.Client
interface IClientRepository {
    suspend fun insertClient(client: Client)
    suspend fun updateClient(client: Client)
    suspend fun deleteClient(id: String)
    suspend fun selectClient(id: String): Client
    suspend fun selectClients(): MutableList<Client>
}