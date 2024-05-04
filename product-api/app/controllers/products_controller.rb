class ProductsController < ApplicationController
  
  def get_all
    @products = ProductService.get_all
    render json: @products, status: :ok
  end

  def create
    required_attributes = [:name, :description, :price]
    if params[:product].present? && required_attributes.all? { |attr| params[:product].key?(attr) && params[:product][attr].present?}
      ProductService.create(params.require(:product).permit(*required_attributes))
      render json: { message: "User Creadted Succesfully" }, status: :ok
    else
      render json: { message: "Bad Request :: Missing or Invalid Parameters" }, status: :bad_request
    end
  end

  def update
    required_attributes = [:id, :name, :description, :price]
    if params[:product].present? && required_attributes.all? { |attr| params[:product].key?(attr) && params[:product][attr].present?}
      ProductService.update(params.require(:product).permit(*required_attributes))
      render json: { message: "User Updated Succesfully" }, status: :ok
    else
      render json: { message: "Bad Request :: Missing or Invalid Parameters" }, status: :bad_request
    end
  end

end


# ProductService.create(params.require(:product).permit(*required_attributes))
    # if params.require(required_attributes).permit(*required_attributes).present?
    # if params.require(:product).permit(*required_attributes).present?
      # ProductService.create(params.permit(:name, :description, :price))