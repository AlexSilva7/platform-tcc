import datetime
from repositorys.currency_repository import CurrencyPriceRepository
from models.currency_price import CurrencyPrice
import requests

class QuotationPriceService:
    def __init__(self, repository: CurrencyPriceRepository):
        self.repository = repository

    def getQuotePriceList(self):
        quotation_list = self.repository.selectQuotePriceList()

        if(len(quotation_list) == 0 or quotation_list[0].created_at.date() < datetime.date.today()):
            return self.updateNewQuotesAndReturn()
        
        return quotation_list
    
    def getQuotePrice(self, currency: str) -> CurrencyPrice:
        quotation_list = self.getQuotePriceList()

        result = [quotation_price 
                  for quotation_price in quotation_list 
                  if quotation_price.currency == currency]
        
        return result
            
    def updateNewQuotesAndReturn(self) -> list[CurrencyPrice]:
        quotations_price: list[CurrencyPrice] = []
        url = "https://economia.awesomeapi.com.br/all"

        response = requests.get(url)
        json_data = dict(response.json())

        data = {key: value for key, value in json_data.items() if key in ['USD', 'EUR', 'GBP', 'CNY']}
        for key, value in data.items():
            quotations_price.append(CurrencyPrice(key, value['bid']))

        self.repository.insertNewQuotes(quotations_price)
        return quotations_price

    
        

