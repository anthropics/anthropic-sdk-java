package io.petstore.models

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Order(
  public val id: Long? = null,
  @SerialName("petId")
  public val petid: Long? = null,
  public val quantity: Int? = null,
  @SerialName("shipDate")
  public val shipdate: String? = null,
  /**
   * Order Status
   */
  public val status: String? = null,
  public val complete: Boolean? = null,
)
