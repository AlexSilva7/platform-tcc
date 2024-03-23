package models.client
import java.time.LocalDate

class Client(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val birthdate: LocalDate
)