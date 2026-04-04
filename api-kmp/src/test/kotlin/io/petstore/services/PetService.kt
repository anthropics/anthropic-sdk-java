package io.petstore.services

import io.petstore.models.ApiResponse
import io.petstore.models.Pet

public interface PetService {
  /**
   * Update an existing pet by Id.
   */
  public suspend fun updatePet(params: Pet): Pet

  /**
   * Add a new pet to the store.
   */
  public suspend fun addPet(params: Pet): Pet

  /**
   * Multiple status values can be provided with comma separated strings.
   */
  public suspend fun findPetsByStatus()

  /**
   * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
   */
  public suspend fun findPetsByTags()

  /**
   * Returns a single pet.
   */
  public suspend fun getPetById(): Pet

  /**
   * Updates a pet resource based on the form data.
   */
  public suspend fun updatePetWithForm(): Pet

  /**
   * Delete a pet.
   */
  public suspend fun deletePet()

  /**
   * Upload image of the pet.
   */
  public suspend fun uploadFile(): ApiResponse
}
