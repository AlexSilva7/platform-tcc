from flask import Flask, abort, jsonify
from services.currency_service import QuotationPriceService
from repositorys.currency_repository import CurrencyPriceRepository
from models.extensions import Extensions
from logger.logger_factory import LoggerFactory
from setup import setup

setup()
app = Flask(__name__)

repository = CurrencyPriceRepository()
service = QuotationPriceService(repository)

@app.route('/api/get-currency-quote/<currency>', methods=['GET'])
def get_currency_quote(currency: str):
    try:
        if(currency not in ['USD', 'EUR', 'GBP', 'CNY']):
            abort(400, 'Invalid Currency. Available Currencies (USD, EUR, GBP, CNY)')

        result = service.getQuotePrice(currency)
        response = Extensions.toResponse(result)
        return response
    except Exception as ex:
        LoggerFactory.error(f"Route :: get_currency_quote :: {ex}")
        return jsonify({'message': 'We were unable to process your request'}), 500

@app.route('/api/get-quote-for-all-currencies', methods=['GET'])
def get_quote_for_all_currencies():
    try:
        LoggerFactory.info("Route :: get_quote_for_all_currencies")
        result = service.getQuotePriceList()
        response = Extensions.toResponse(result)
        return response
    except Exception as ex:
        LoggerFactory.error(f"Route :: get_quote_for_all_currencies :: {ex}")
        return jsonify({'message': 'We were unable to process your request'}), 500

if __name__ == '__main__':
    app.run(debug=True, port=7000)
