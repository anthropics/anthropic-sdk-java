package io.petstore.components

import com.anthropic.core.component.Component
import com.anthropic.core.component.PatchEvent
import com.anthropic.core.component.PatchOperation
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.sse.sse
import io.ktor.client.request.`get`
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.petstore.models.User
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

public class UserComponent(
  private val client: HttpClient,
  private val baseUrl: String,
  private val json: Json = Json { ignoreUnknownKeys = true },
) : Component<User> {
  override val name: String
    get() = "user"

  override suspend fun `get`(id: String): User = client.get("$baseUrl/user/$id").body()

  override suspend fun list(params: Map<String, String>): List<User> = client.get("$baseUrl/user") { params.forEach { (k, v) -> parameter(k, v) } }.body()

  override suspend fun add(entity: User): PatchEvent<User> = client.post("$baseUrl/user") { contentType(ContentType.Application.Json); setBody(entity) }.body()

  override suspend fun replace(id: String, entity: User): PatchEvent<User> = client.put("$baseUrl/user/$id") { contentType(ContentType.Application.Json); setBody(entity) }.body()

  override suspend fun patch(id: String, ops: List<PatchOperation>): PatchEvent<User> = client.patch("$baseUrl/user/$id") { setBody(ops) }.body()

  override suspend fun remove(id: String): PatchEvent<User> = client.delete("$baseUrl/user/$id").body()

  override fun changes(): Flow<PatchEvent<User>> = kotlinx.coroutines.flow.flow {
      client.sse("$baseUrl/user/changes") {
          incoming.collect { event ->
              event.data?.let { emit(json.decodeFromString(it)) }
          }
      }
  }
}
