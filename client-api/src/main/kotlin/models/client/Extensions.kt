package models.client
import java.util.*

object Extensions {
    fun Requests.ClientRequest.toDomain(): Client {
        return Client(
            id = UUID.randomUUID().toString(),
            name = this.name,
            surname = this.surname,
            email = this.email,
            birthdate = this.birthdate
        )
    }

    fun Requests.ClientUpdateRequest.toDomain(): Client {
        return Client(
            id = this.id,
            name = this.name,
            surname = this.surname,
            email = this.email,
            birthdate = this.birthdate
        )
    }
    fun Client.toResponse(): Responses.ClientSerializableResponse {
        return Responses.ClientSerializableResponse(
            id = this.id,
            name = this.name,
            surname = this.surname,
            email = this.email,
            birthdate = this.birthdate
        )
    }

    fun Results.ClientListResult.toResponse(): Responses.ClientListResponse {
        return Responses.ClientListResponse(
            resources = this.resources?.map {
                    client -> client.toResponse()
            }?.toMutableList(),
            message = this.message,
            success = this.success
        )
    }
    fun Results.ClientResult.toResponse(): Responses.ClientResponse{
        return Responses.ClientResponse(
            resource = this.resource?.toResponse(),
            message = this.message,
            success = this.success
        )
    }
}