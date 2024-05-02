from flask import Flask, abort
from services.quotation_service import QuotationPriceService
from repositorys.quotation_repository import QuotationPriceRepository
from models.extensions import Extensions
from logger.logger_factory import LoggerFactory
from setup import setup

setup()
app = Flask(__name__)

repository = QuotationPriceRepository()
service = QuotationPriceService(repository)

@app.route('/api/get-currency-quote/<currency>', methods=['GET'])
def get_currency_quote(currency: str):
    if(currency not in ['USD', 'EUR', 'GBP', 'CNY']):
        abort(400, 'Invalid Currency. Available Currencies (USD, EUR, GBP, CNY)')

    result = service.getQuotePrice(currency)
    response = Extensions.toResponse(result)
    return response

@app.route('/api/get-quote-for-all-currencies', methods=['GET'])
def get_quote_for_all_currencies():
    LoggerFactory.info("Route :: get_quote_for_all_currencies")
    result = service.getQuotePriceList()
    response = Extensions.toResponse(result)
    return response

if __name__ == '__main__':
    app.run(debug=True, port=7000)
