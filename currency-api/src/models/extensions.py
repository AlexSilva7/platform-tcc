from flask import jsonify
from models.currency_price import CurrencyPrice

class Extensions:

    @staticmethod
    def toResponse(input: list[CurrencyPrice]):
        response = [
            {
                'currency': obj.currency, 
                'price': obj.price,
                'updated_at': obj.created_at.strftime("%Y-%m-%d %H:%M:%S")
            } for obj in input]
        
        if(len(response) == 1): return jsonify(response[0])
        else:  return jsonify(response)