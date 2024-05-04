class Product
  attr_reader :id, :name, :description, :quantity, :created_at, :updated_at

  def initialize(attributes = {})
    @id = attributes[:id]
    @name = attributes[:name]
    @description = attributes[:description]
    @quantity = attributes[:quantity]
    @created_at = attributes[:created_at]
    @updated_at = attributes[:updated_at]
  end
end
