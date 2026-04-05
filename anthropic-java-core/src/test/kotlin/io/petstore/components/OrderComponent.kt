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
import io.petstore.models.Order
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

public class OrderComponent(
  private val client: HttpClient,
  private val baseUrl: String,
  private val json: Json = Json { ignoreUnknownKeys = true },
) : Component<Order> {
  override val name: String
    get() = "order"

  override suspend fun `get`(id: String): Order = client.get("$baseUrl/order/$id").body()

  override suspend fun list(params: Map<String, String>): List<Order> = client.get("$baseUrl/order") { params.forEach { (k, v) -> parameter(k, v) } }.body()

  override suspend fun add(entity: Order): PatchEvent<Order> = client.post("$baseUrl/order") { contentType(ContentType.Application.Json); setBody(entity) }.body()

  override suspend fun replace(id: String, entity: Order): PatchEvent<Order> = client.put("$baseUrl/order/$id") { contentType(ContentType.Application.Json); setBody(entity) }.body()

  override suspend fun patch(id: String, ops: List<PatchOperation>): PatchEvent<Order> = client.patch("$baseUrl/order/$id") { setBody(ops) }.body()

  override suspend fun remove(id: String): PatchEvent<Order> = client.delete("$baseUrl/order/$id").body()

  override fun changes(): Flow<PatchEvent<Order>> = kotlinx.coroutines.flow.flow {
      client.sse("$baseUrl/order/changes") {
          incoming.collect { event ->
              event.data?.let { emit(json.decodeFromString(it)) }
          }
      }
  }
}
