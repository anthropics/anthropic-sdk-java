package io.petstore.services

import io.petstore.models.Order

public interface StoreService {
  /**
   * Returns a map of status codes to quantities.
   */
  public suspend fun getInventory()

  /**
   * Place a new order in the store.
   */
  public suspend fun placeOrder(params: Order): Order

  /**
   * For valid response try integer IDs with value <= 5 or > 10. Other values will generate exceptions.
   */
  public suspend fun getOrderById(): Order

  /**
   * For valid response try integer IDs with value < 1000. Anything above 1000 or non-integers will generate API errors.
   */
  public suspend fun deleteOrder()
}
