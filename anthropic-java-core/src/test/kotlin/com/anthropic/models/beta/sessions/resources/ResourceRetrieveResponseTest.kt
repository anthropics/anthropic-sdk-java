// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ResourceRetrieveResponseTest {

    @Test
    fun ofGitHubRepository() {
        val githubRepository =
            BetaManagedAgentsGitHubRepositoryResource.builder()
                .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .mountPath("/workspace/example-repo")
                .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .url("https://github.com/example-org/example-repo")
                .branchCheckout("main")
                .build()

        val resourceRetrieveResponse = ResourceRetrieveResponse.ofGitHubRepository(githubRepository)

        assertThat(resourceRetrieveResponse.githubRepository()).contains(githubRepository)
        assertThat(resourceRetrieveResponse.file()).isEmpty
        assertThat(resourceRetrieveResponse.memoryStore()).isEmpty
    }

    @Test
    fun ofGitHubRepositoryRoundtrip() {
        val jsonMapper = jsonMapper()
        val resourceRetrieveResponse =
            ResourceRetrieveResponse.ofGitHubRepository(
                BetaManagedAgentsGitHubRepositoryResource.builder()
                    .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .mountPath("/workspace/example-repo")
                    .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .url("https://github.com/example-org/example-repo")
                    .branchCheckout("main")
                    .build()
            )

        val roundtrippedResourceRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(resourceRetrieveResponse),
                jacksonTypeRef<ResourceRetrieveResponse>(),
            )

        assertThat(roundtrippedResourceRetrieveResponse).isEqualTo(resourceRetrieveResponse)
    }

    @Test
    fun ofFile() {
        val file =
            BetaManagedAgentsFileResource.builder()
                .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .mountPath("/uploads/receipt.pdf")
                .type(BetaManagedAgentsFileResource.Type.FILE)
                .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val resourceRetrieveResponse = ResourceRetrieveResponse.ofFile(file)

        assertThat(resourceRetrieveResponse.githubRepository()).isEmpty
        assertThat(resourceRetrieveResponse.file()).contains(file)
        assertThat(resourceRetrieveResponse.memoryStore()).isEmpty
    }

    @Test
    fun ofFileRoundtrip() {
        val jsonMapper = jsonMapper()
        val resourceRetrieveResponse =
            ResourceRetrieveResponse.ofFile(
                BetaManagedAgentsFileResource.builder()
                    .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .mountPath("/uploads/receipt.pdf")
                    .type(BetaManagedAgentsFileResource.Type.FILE)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedResourceRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(resourceRetrieveResponse),
                jacksonTypeRef<ResourceRetrieveResponse>(),
            )

        assertThat(roundtrippedResourceRetrieveResponse).isEqualTo(resourceRetrieveResponse)
    }

    @Test
    fun ofMemoryStore() {
        val memoryStore =
            BetaManagedAgentsMemoryStoreResource.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaManagedAgentsMemoryStoreResource.Type.MEMORY_STORE)
                .access(BetaManagedAgentsMemoryStoreResource.Access.READ_WRITE)
                .description("description")
                .instructions("instructions")
                .mountPath("mount_path")
                .name("name")
                .build()

        val resourceRetrieveResponse = ResourceRetrieveResponse.ofMemoryStore(memoryStore)

        assertThat(resourceRetrieveResponse.githubRepository()).isEmpty
        assertThat(resourceRetrieveResponse.file()).isEmpty
        assertThat(resourceRetrieveResponse.memoryStore()).contains(memoryStore)
    }

    @Test
    fun ofMemoryStoreRoundtrip() {
        val jsonMapper = jsonMapper()
        val resourceRetrieveResponse =
            ResourceRetrieveResponse.ofMemoryStore(
                BetaManagedAgentsMemoryStoreResource.builder()
                    .memoryStoreId("memory_store_id")
                    .type(BetaManagedAgentsMemoryStoreResource.Type.MEMORY_STORE)
                    .access(BetaManagedAgentsMemoryStoreResource.Access.READ_WRITE)
                    .description("description")
                    .instructions("instructions")
                    .mountPath("mount_path")
                    .name("name")
                    .build()
            )

        val roundtrippedResourceRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(resourceRetrieveResponse),
                jacksonTypeRef<ResourceRetrieveResponse>(),
            )

        assertThat(roundtrippedResourceRetrieveResponse).isEqualTo(resourceRetrieveResponse)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val resourceRetrieveResponse =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ResourceRetrieveResponse>())

        val e = assertThrows<AnthropicInvalidDataException> { resourceRetrieveResponse.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
