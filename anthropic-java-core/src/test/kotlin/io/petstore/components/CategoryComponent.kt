package io.petstore.components

import kotlinx.kmp.util.core.component.Component
import kotlinx.kmp.util.core.component.PatchEvent
import kotlinx.kmp.util.core.component.PatchOperation
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
import io.petstore.models.Category
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

public class CategoryComponent(
  private val client: HttpClient,
  private val baseUrl: String,
  private val json: Json = Json { ignoreUnknownKeys = true },
) : Component<Category> {
  override val name: String
    get() = "category"

  override suspend fun `get`(id: String): Category = client.get("$baseUrl/category/$id").body()

  override suspend fun list(params: Map<String, String>): List<Category> = client.get("$baseUrl/category") { params.forEach { (k, v) -> parameter(k, v) } }.body()

  override suspend fun add(entity: Category): PatchEvent<Category> = client.post("$baseUrl/category") { contentType(ContentType.Application.Json); setBody(entity) }.body()

  override suspend fun replace(id: String, entity: Category): PatchEvent<Category> = client.put("$baseUrl/category/$id") { contentType(ContentType.Application.Json); setBody(entity) }.body()

  override suspend fun patch(id: String, ops: List<PatchOperation>): PatchEvent<Category> = client.patch("$baseUrl/category/$id") { setBody(ops) }.body()

  override suspend fun remove(id: String): PatchEvent<Category> = client.delete("$baseUrl/category/$id").body()

  override fun changes(): Flow<PatchEvent<Category>> = kotlinx.coroutines.flow.flow {
      client.sse("$baseUrl/category/changes") {
          incoming.collect { event ->
              event.data?.let { emit(json.decodeFromString(it)) }
          }
      }
  }
}
