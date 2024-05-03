Rails.application.routes.draw do
  resources :product, only: [:create, :index]
end