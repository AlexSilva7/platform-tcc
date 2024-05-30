require 'securerandom'

class ProductService
    def self.enrich_product_with_prices(brl_price, exchange_rates)
        result = {}
        result["BRL"] = brl_price.to_f.round(2)
        exchange_rates.each do |rate|
            currency = rate['currency']
            price_in_currency = brl_price / rate['price'].to_f
            result["#{currency}"] = price_in_currency.round(2)
        end
        return result
    end

    def self.get_all
        product_result = []
        products = ProductRepository.select_all
        quotes = CurrencyProvider.get_currency_quotes

        products.each do |key, value|
            product = value.last 
            enriched_product = enrich_product_with_prices(product.price, quotes)

            product_result << {
              "id": key,
              "name": product.name,
              "description": product.description, 
              "price": enriched_product
            }
        end 
        return product_result
    end

    def self.get_product_by_id(id)
        return ProductRepository.select_by_id(id)
    end
  
    def self.create(attributes)
        id = generate_product_id()

        product = Product.new(
            attributes[:name], 
            attributes[:description], 
            attributes[:price]
            )

        ProductRepository.add(id, product)
        StockRepository.update_quantity(id, 0)
    end

    def self.delete(id)
        ProductRepository.remove(id)
        StockRepository.remove_object(id)
    end

    def self.update(attributes)
        product = Product.new(
            attributes[:name], 
            attributes[:description], 
            attributes[:price]
            )

        ProductRepository.update(attributes[:id], product)
    end

    def self.generate_product_id()
        SecureRandom.uuid
    end
end
  