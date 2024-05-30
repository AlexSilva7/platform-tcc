class Product
  attr_reader :name, :description, :price, :created_at, :updated_at

  def initialize(name, description, price)
    @name = name
    @description = description
    @price = price
    @created_at = Time.now
    @updated_at = Time.now
  end
end
