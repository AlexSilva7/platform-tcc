require 'securerandom'

class ProductService
    def self.get_all
        products = ProductRepository.select_all
        return products
    end
  
    def self.create(attributes)
        id = generate_product_id()

        product = Product.new(
            attributes[:name], 
            attributes[:description], 
            attributes[:price]
            )

        ProductRepository.add(id, product)
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
  