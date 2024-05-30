class StockRepository
    @@Stock = {}

    def self.select_quantity(product_id)
      @@Stock[product_id]
    end

    def self.update_quantity(product_id, quantity)
      @@Stock[product_id] = quantity
    end

    def self.remove_object(product_id)
      @@Stock.delete(product_id)
    end
  end
  