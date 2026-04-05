package io.swagger.petstore.models

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Tag(
  public val id: Long? = null,
  public val name: String? = null,
)
