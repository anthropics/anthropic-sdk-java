package io.swagger.petstore.models

import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class User(
  public val id: Long? = null,
  public val username: String? = null,
  @SerialName("firstName")
  public val firstname: String? = null,
  @SerialName("lastName")
  public val lastname: String? = null,
  public val email: String? = null,
  public val password: String? = null,
  public val phone: String? = null,
  /**
   * User Status
   */
  @SerialName("userStatus")
  public val userstatus: Int? = null,
)
