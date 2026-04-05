package io.petstore.models

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Pet(
  public val id: Long? = null,
  public val name: String,
  public val category: Category? = null,
  @SerialName("photoUrls")
  public val photourls: List<String>,
  public val tags: List<Tag>? = null,
  /**
   * pet status in the store
   */
  public val status: String? = null,
)
