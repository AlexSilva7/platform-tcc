package common
import org.slf4j.Logger

class LoggerFactory private constructor(private val logger: Logger) {
    companion object {
        @Volatile
        private var instance: LoggerFactory? = null

        fun getInstance(logger: Logger): LoggerFactory {
            return instance ?: synchronized(this) {
                instance ?: LoggerFactory(logger).also { instance = it }
            }
        }
    }

    fun info(message: String) {
        logger.info(message)
    }

    fun error(message: String) {
        logger.error(message)
    }
}


/*
open class LoggerFactory(private val _logger: Logger){
    fun info(message: String){
        _logger.info(message)
    }

    fun error(message: String){
        _logger.error(message)
    }
}
*/
