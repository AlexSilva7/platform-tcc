package models.generics
import kotlinx.serialization.Serializable
@Serializable
data class GenericResponse (
    val message: String?,
    val success: Boolean?
)