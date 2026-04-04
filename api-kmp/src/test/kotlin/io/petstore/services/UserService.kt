package io.petstore.services

import io.petstore.models.User

public interface UserService {
  /**
   * This can only be done by the logged in user.
   */
  public suspend fun createUser(params: User): User

  /**
   * Creates list of users with given input array.
   */
  public suspend fun createUsersWithListInput(): User

  /**
   * Log into the system.
   */
  public suspend fun loginUser()

  /**
   * Log user out of the system.
   */
  public suspend fun logoutUser()

  /**
   * Get user detail based on username.
   */
  public suspend fun getUserByName(): User

  /**
   * This can only be done by the logged in user.
   */
  public suspend fun updateUser(params: User)

  /**
   * This can only be done by the logged in user.
   */
  public suspend fun deleteUser()
}
