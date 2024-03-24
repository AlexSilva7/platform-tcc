package services.contracts
import models.client.Client
import models.client.Results.ClientListResult
import models.client.Results.ClientResult
import models.generics.GenericResult

interface IClientService {
    fun createClient(client: Client): GenericResult
    fun updateClient(client: Client): GenericResult
    fun removeClient(id: String): GenericResult
    fun getClientById(id: String): ClientResult
    fun getAllClients(): ClientListResult
}

