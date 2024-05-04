class Product
  attr_reader :name, :description, :price, :created_at, :updated_at

  # def initialize(id, name, description, price)
  #   @id = attributes[:id]
  #   @name = attributes[:name]
  #   @description = attributes[:description]
  #   @price = attributes[:quantity]
  #   @created_at = attributes[:created_at]
  #   @updated_at = attributes[:updated_at]
  # end

  def initialize(name, description, price)
    @name = name
    @description = description
    @price = price
    @created_at = Time.now
    @updated_at = Time.now
  end
end
