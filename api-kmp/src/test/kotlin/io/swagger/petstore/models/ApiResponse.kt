package io.swagger.petstore.models

import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ApiResponse(
  public val code: Int? = null,
  public val type: String? = null,
  public val message: String? = null,
)
