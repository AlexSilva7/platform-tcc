from datetime import datetime
from models.quotation_price import QuotationPrice

class QuotationPriceRepository:
    def __init__(self):
        self.quotation_price_list : list[QuotationPrice] = []

    def selectQuotePriceList(self):
        return self.quotation_price_list
            
    def insertNewQuotes(self, quotations_price: list[QuotationPrice]):
        self.quotation_price_list.clear()
        self.quotation_price_list = quotations_price

