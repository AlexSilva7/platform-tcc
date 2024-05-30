from datetime import datetime
import os
import logging
from logger.logger_factory import LoggerFactory

def setup():
    script_path = os.path.dirname(os.path.abspath(__file__))
    root_path = os.path.dirname(script_path)
    # current_day = datetime.now().strftime("%Y%m%d")

    logging.basicConfig(
        filename = f"{root_path}\\logs\\CURRENCY_API.log", 
        level = logging.INFO, 
        format = '%(asctime)s - %(levelname)s - %(message)s',
        encoding='UTF-8'
    )

    LoggerFactory.configureLogger(logging)
    LoggerFactory.info("Main :: Iniciando aplicação")

if __name__ == "__main__":
    setup()
