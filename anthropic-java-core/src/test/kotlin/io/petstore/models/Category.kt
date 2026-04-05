package io.petstore.models

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Category(
  public val id: Long? = null,
  public val name: String? = null,
)
