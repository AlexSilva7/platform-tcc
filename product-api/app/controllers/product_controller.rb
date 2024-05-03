class ProductController < ApplicationController
  def create
    render json: { message: "Product created successfully" }, status: :created
  end

  def index
    render json: { message: "Listing all products" }
  end
end