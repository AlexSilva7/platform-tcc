import datetime
from repositorys.quotation_repository import QuotationPriceRepository
from models.quotation_price import QuotationPrice
import requests

class QuotationPriceService:
    def __init__(self, repository: QuotationPriceRepository):
        self.repository = repository

    def getQuotePriceList(self):
        quotation_list = self.repository.selectQuotePriceList()

        if(len(quotation_list) == 0 or quotation_list[0].created_at.date() < datetime.date.today()):
            return self.updateNewQuotesAndReturn()
        
        return quotation_list
    
    def getQuotePrice(self, currency: str) -> QuotationPrice:
        quotation_list = self.getQuotePriceList()

        result = [quotation_price 
                  for quotation_price in quotation_list 
                  if quotation_price.currency == currency]
        
        return result
            
    def updateNewQuotesAndReturn(self) -> list[QuotationPrice]:
        quotations_price: list[QuotationPrice] = []
        url = "https://economia.awesomeapi.com.br/all"

        response = requests.get(url)
        json_data = dict(response.json())

        data = {key: value for key, value in json_data.items() if key in ['USD', 'EUR', 'GBP', 'CNY']}
        for key, value in data.items():
            quotations_price.append(QuotationPrice(key, value['bid']))

        self.repository.insertNewQuotes(quotations_price)
        return quotations_price

    
        

