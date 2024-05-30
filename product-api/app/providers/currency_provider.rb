require 'net/http'
require 'uri'
require 'json'

class CurrencyProvider
    def self.get_currency_quotes()
        uri = URI.parse("http://localhost:7000/api/get-quote-for-all-currencies")
        response = Net::HTTP.get_response(uri)
        data = JSON.parse(response.body)
        return data
    end
end
  