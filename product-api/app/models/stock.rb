class Stock
  attr_reader :product_id, :qty

  def initialize(product_id, qty)
    @product_id = product_id
    @qty = qty
  end
end
