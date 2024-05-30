class StockService
    def self.get_quantity(product_id)
        return StockRepository.select_quantity(product_id)
    end

    def self.update_quantity(product_id, quantity)
        StockRepository.update_quantity(product_id, quantity)
    end
end
  