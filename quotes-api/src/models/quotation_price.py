from datetime import datetime

class QuotationPrice:
    def __init__(self, currency: str, price: float):
        self.currency = currency
        self.price = price
        self.created_at = datetime.now()

