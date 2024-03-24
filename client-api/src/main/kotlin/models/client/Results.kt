package models.client

class Results {
    class ClientListResult(
        val resources: MutableList<Client>?,
        val success: Boolean,
        val message: String?
    )
    class ClientResult(
        val resource: Client?,
        val success: Boolean,
        val message: String?
    )
}