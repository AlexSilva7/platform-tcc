class ProductRepository
    @@products = {}
  
    def self.select_all
      @@products
    end

    def self.select_by_id(product_id)
      @@products[product_id]
    end
    
    def self.add(product_id, attributes)
      @@products[product_id] ||= []
      @@products[product_id] << attributes
    end

    def self.remove(product_id)
        @@products.delete(product_id)
    end

    def self.update(product_id, new_attributes)
      @@products[product_id] = new_attributes
    end
  end
  