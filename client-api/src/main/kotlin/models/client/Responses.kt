package models.client
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Responses {
    @Serializable
    data class ClientSerializableResponse(
        val id: String,
        val name: String,
        val surname: String,
        val email: String,
        @Serializable(with = LocalDateSerializer::class)
        val birthdate: LocalDate
    )

    @Serializer(forClass = LocalDate::class)
    class LocalDateSerializer : KSerializer<LocalDate> {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

        override fun serialize(encoder: Encoder, value: LocalDate) {
            encoder.encodeString(value.format(formatter))
        }
        override fun deserialize(decoder: Decoder): LocalDate {
            return LocalDate.parse(decoder.decodeString(), formatter)
        }
    }

    @Serializable
    data class ClientListResponse(
        val resources: MutableList<ClientSerializableResponse>?,
        val success: Boolean,
        val message: String?
    )

    @Serializable
    data class ClientResponse(
        val resource: ClientSerializableResponse?,
        val success: Boolean,
        val message: String?
    )
}