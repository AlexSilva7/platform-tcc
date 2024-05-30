Rails.application.routes.draw do
  get '/api/get-all-products', to: 'products#get_all'
  post '/api/create-product', to: 'products#create'
  put '/api/update-product', to: 'products#update'
  delete '/api/delete-product/:id', to: 'products#delete'
  get '/api/get-product-stock/:id', to: 'stocks#get'
  put '/api/updates-stock', to: 'stocks#update'
end
