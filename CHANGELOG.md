# Changelog

### 0.1.0
* [BC-1] Initial version Ecommerce recorder API
  - GET /price endpoint Obtains the retail price of the product (product_id) for the interval that matches
    the provided execution date. In case multiple applicable prices are found, the one with the highest
    numerical priority should be applied.