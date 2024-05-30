class StocksController < ApplicationController
  def try_parse_int(value)
    Integer(value) != nil rescue false
  end
  
  def update
    required_attributes = [:product_id, :quantity]
    if params[:stock].present? && required_attributes.all? { |attr| params[:stock].key?(attr) && params[:stock][attr].present?}
      request_qty = params[:quantity]
      product_id = params[:product_id]
      begin
        Integer(request_qty)
      rescue => e
        render json: { message: 'Price must be an integer' }, status: :unprocessable_entity
        return
      end
      qty = StockService.get_quantity(product_id)
      if qty
        # new_quantity = qty + request_qty
        StockService.update_quantity(product_id, request_qty)
        render json: { quantity: 'Quantity Updated Successfully' }, status: :ok
      else
        render json: { message: 'Product not found' }, status: :not_found
      end 
    else
      render json: { message: "Bad Request :: Missing or Invalid Parameters" }, status: :bad_request
    end
  end

  def get
    product_id = params[:id]
    qty = StockService.get_quantity(product_id)
    if qty
      render json: { quantity: qty }, status: :ok
    else
      render json: { message: 'Product not found' }, status: :not_found
    end 
  end
end