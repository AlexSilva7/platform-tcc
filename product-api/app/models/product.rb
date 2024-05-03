class Product < ApplicationRecord
    validates :name, presence: true
    validates :desc, presence: true
    validates :quantity, numericality: { only_integer: true, greater_than_or_equal_to: 0 }
  
    before_create :set_created_at
    before_save :set_updated_at
  
    private
  
    def set_created_at
      self.created_at = Time.now
    end
  
    def set_updated_at
      self.updated_at = Time.now
    end
  end
  