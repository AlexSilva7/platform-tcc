package services.contracts
import models.Client
interface IClientService {
    fun createClient(client: Client)
    fun updateClient(client: Client)
    fun removeClient(id: String)
    fun getClientById(id: String): Client
    fun getAllClients(): MutableList<Client>
}