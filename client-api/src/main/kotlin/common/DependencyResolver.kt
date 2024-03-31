
import io.ktor.server.config.*
import repositorys.contracts.FakeClientRepository
import repositorys.contracts.IClientRepository

class DependencyResolver {
    val _clientRepository: IClientRepository
    constructor(config: HoconApplicationConfig){
        val db = config.property("ktor.application.database").getString()

        if (db == "fakeDb") {
            _clientRepository = FakeClientRepository()

        }else{
            throw IllegalArgumentException()
        }
    }
}