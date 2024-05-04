Rails.application.routes.draw do
  # resources :products, only: [:index, :create]
  get '/api/get-all-products', to: 'products#get_all'
  post '/api/create-product', to: 'products#create'
  put '/api/update-product', to: 'products#update'
end
