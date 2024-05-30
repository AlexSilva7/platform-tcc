class ProductsController < ApplicationController
  
  def get_all
    @products = ProductService.get_all
    render json: @products, status: :ok
  end

  def create
    required_attributes = [:name, :description, :price]
    if params[:product].present? && required_attributes.all? { |attr| params[:product].key?(attr) && params[:product][attr].present?}
      ProductService.create(params.require(:product).permit(*required_attributes))
      render json: { message: "Product Creadted Succesfully" }, status: :ok
    else
      render json: { message: "Bad Request :: Missing or Invalid Parameters" }, status: :bad_request
    end
  end

  def update
    required_attributes = [:id, :name, :description, :price]
    if params[:product].present? && required_attributes.all? { |attr| params[:product].key?(attr) && params[:product][attr].present?}
      id = params[:id]
      product = ProductService.get_product_by_id(id)
      if product
        ProductService.update(params.require(:product).permit(*required_attributes))
        render json: { message: "Product Updated Succesfully" }, status: :ok
      else
        render json: { message: 'Product not found' }, status: :not_found
      end
    else
      render json: { message: "Bad Request :: Missing or Invalid Parameters" }, status: :bad_request
    end
  end

  def delete
    product_id = params[:id]
    product = ProductService.get_product_by_id(product_id)
    if product
      ProductService.delete(product_id)
      render json: { message: 'Product deleted successfully' }, status: :ok
    else
      render json: { message: 'Product not found' }, status: :not_found
    end 
  end

end


# ProductService.create(params.require(:product).permit(*required_attributes))
    # if params.require(required_attributes).permit(*required_attributes).present?
    # if params.require(:product).permit(*required_attributes).present?
      # ProductService.create(params.permit(:name, :description, :price))