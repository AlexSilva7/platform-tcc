package models.generics
object Extensions {
    fun GenericResult.toResponse(): GenericResponse {
        return GenericResponse(
            message = this.message,
            success = this.success
        )
    }
}