package services.contracts
import models.client.Client
import models.client.Results.ClientListResult
import models.client.Results.ClientResult
import models.generics.GenericResult

interface IClientService {
    suspend fun createClient(client: Client): GenericResult
    suspend fun updateClient(client: Client): GenericResult
    suspend fun removeClient(id: String): GenericResult
    suspend fun getClientById(id: String): ClientResult
    suspend fun getAllClients(): ClientListResult
}

