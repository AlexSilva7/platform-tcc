class ProductRepository
    @@products = []
    @@id_counter = 1
  
    def self.all
      @@products
    end
  
    def self.create(attributes)
      product = {
        id: @@id_counter,
        name: attributes[:name],
        description: attributes[:description],
        quantity: attributes[:quantity],
        created_at: Time.now,
        updated_at: Time.now
      }
      @@products << product
      @@id_counter += 1
    end
  end
  