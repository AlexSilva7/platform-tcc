package services.contracts
import models.client.Client
import models.client.Results.ClientListResult
import models.generics.GenericResult

interface IClientService {
    fun createClient(client: Client): GenericResult
    fun updateClient(client: Client)
    fun removeClient(id: String)
    fun getClientById(id: String): Client
    fun getAllClients(): ClientListResult
}

