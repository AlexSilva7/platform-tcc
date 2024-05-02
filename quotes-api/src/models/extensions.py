from flask import jsonify
from models.quotation_price import QuotationPrice

class Extensions:

    @staticmethod
    def toResponse(input: list[QuotationPrice]):
        response = [
            {
                'currency': obj.currency, 
                'price': obj.price,
                'updated_at': obj.created_at.strftime("%Y-%m-%d %H:%M:%S")
            } for obj in input]
        
        if(len(response) == 1): return jsonify(response[0])
        else:  return jsonify(response)