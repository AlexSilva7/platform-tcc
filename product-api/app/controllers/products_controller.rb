class ProductsController < ApplicationController
  def get_all
    @products = ProductRepository.all
    # product_params = { name: "Produto 1", description: "Descrição do Produto 1", quantity: 10 }
    # ProductRepository.create(product_params)
    # products = ProductRepository.all
    render json: @products, status: :ok
  end

  def create
    # product_params = params.permit(:name, :description, :quantity)
    # product_params = { name: "Produto 1", description: "Descrição do Produto 1", quantity: 10 }
    # ProductRepository.create(product_params)

    product_params = { name: "Produto 1", description: "Descrição do Produto 1", quantity: 10 }
    # product = Product.new(product_params)
    # ProductRepository.create(product)
    render json: product_params, status: :ok
      
    # products = ProductRepository.all
    # render json: @products, status: :ok

    # @products = ProductRepository.all
    # render json: @products, status: :ok

    # redirect_to action: "get_all"
  end
end
