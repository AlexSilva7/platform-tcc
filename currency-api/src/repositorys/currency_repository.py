from datetime import datetime
from models.currency_price import CurrencyPrice

class CurrencyPriceRepository:
    def __init__(self):
        self.quotation_price_list : list[CurrencyPrice] = []

    def selectQuotePriceList(self):
        return self.quotation_price_list
            
    def insertNewQuotes(self, quotations_price: list[CurrencyPrice]):
        self.quotation_price_list.clear()
        self.quotation_price_list = quotations_price

