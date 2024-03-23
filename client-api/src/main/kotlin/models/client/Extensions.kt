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

    fun
    fun Results.ClientListResult.toResponse(): Responses.ClientListResponse {
        return Responses.ClientListResponse(
            resources = this.resources,
            message = this.message,
            success = this.success
        )
    }
}