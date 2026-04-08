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

internal class ResourceUpdateResponseTest {

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

        val resourceUpdateResponse = ResourceUpdateResponse.ofGitHubRepository(githubRepository)

        assertThat(resourceUpdateResponse.githubRepository()).contains(githubRepository)
        assertThat(resourceUpdateResponse.file()).isEmpty
    }

    @Test
    fun ofGitHubRepositoryRoundtrip() {
        val jsonMapper = jsonMapper()
        val resourceUpdateResponse =
            ResourceUpdateResponse.ofGitHubRepository(
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

        val roundtrippedResourceUpdateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(resourceUpdateResponse),
                jacksonTypeRef<ResourceUpdateResponse>(),
            )

        assertThat(roundtrippedResourceUpdateResponse).isEqualTo(resourceUpdateResponse)
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

        val resourceUpdateResponse = ResourceUpdateResponse.ofFile(file)

        assertThat(resourceUpdateResponse.githubRepository()).isEmpty
        assertThat(resourceUpdateResponse.file()).contains(file)
    }

    @Test
    fun ofFileRoundtrip() {
        val jsonMapper = jsonMapper()
        val resourceUpdateResponse =
            ResourceUpdateResponse.ofFile(
                BetaManagedAgentsFileResource.builder()
                    .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .mountPath("/uploads/receipt.pdf")
                    .type(BetaManagedAgentsFileResource.Type.FILE)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedResourceUpdateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(resourceUpdateResponse),
                jacksonTypeRef<ResourceUpdateResponse>(),
            )

        assertThat(roundtrippedResourceUpdateResponse).isEqualTo(resourceUpdateResponse)
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
        val resourceUpdateResponse =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ResourceUpdateResponse>())

        val e = assertThrows<AnthropicInvalidDataException> { resourceUpdateResponse.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
