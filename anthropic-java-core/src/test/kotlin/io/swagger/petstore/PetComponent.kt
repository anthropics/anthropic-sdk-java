package io.swagger.petstore

import kotlinx.kmp.util.core.component.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.sse.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.swagger.petstore.models.Pet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

/**
 * PetComponent — generated from Petstore OpenAPI spec.
 * One component, all protocols.
 */
class PetComponent(
    private val client: HttpClient,
    private val baseUrl: String,
    private val json: Json = Json { ignoreUnknownKeys = true },
) : Component<Pet> {

    override val name = "pet"

    override suspend fun get(id: String): Pet =
        client.get("$baseUrl/pet/$id").body()

    override suspend fun list(params: Map<String, String>): List<Pet> =
        client.get("$baseUrl/pet/findByStatus") {
            params.forEach { (k, v) -> parameter(k, v) }
        }.body()

    override suspend fun add(entity: Pet): PatchEvent<Pet> =
        client.post("$baseUrl/pet") {
            contentType(ContentType.Application.Json)
            setBody(entity)
        }.body()

    override suspend fun replace(id: String, entity: Pet): PatchEvent<Pet> =
        client.put("$baseUrl/pet/$id") {
            contentType(ContentType.Application.Json)
            setBody(entity)
        }.body()

    override suspend fun patch(id: String, ops: List<PatchOperation>): PatchEvent<Pet> =
        client.patch("$baseUrl/pet/$id") {
            contentType(ContentType("application", "json-patch+json"))
            setBody(ops)
        }.body()

    override suspend fun remove(id: String): PatchEvent<Pet> =
        client.delete("$baseUrl/pet/$id").body()

    override fun changes(): Flow<PatchEvent<Pet>> = flow {
        client.sse("$baseUrl/pet/changes") {
            incoming.collect { event ->
                event.data?.let { emit(json.decodeFromString<PatchEvent<Pet>>(it)) }
            }
        }
    }
}
