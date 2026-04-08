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

internal class BetaManagedAgentsSessionResourceTest {

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

        val betaManagedAgentsSessionResource =
            BetaManagedAgentsSessionResource.ofGitHubRepository(githubRepository)

        assertThat(betaManagedAgentsSessionResource.githubRepository()).contains(githubRepository)
        assertThat(betaManagedAgentsSessionResource.file()).isEmpty
    }

    @Test
    fun ofGitHubRepositoryRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResource =
            BetaManagedAgentsSessionResource.ofGitHubRepository(
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

        val roundtrippedBetaManagedAgentsSessionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionResource),
                jacksonTypeRef<BetaManagedAgentsSessionResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResource)
            .isEqualTo(betaManagedAgentsSessionResource)
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

        val betaManagedAgentsSessionResource = BetaManagedAgentsSessionResource.ofFile(file)

        assertThat(betaManagedAgentsSessionResource.githubRepository()).isEmpty
        assertThat(betaManagedAgentsSessionResource.file()).contains(file)
    }

    @Test
    fun ofFileRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResource =
            BetaManagedAgentsSessionResource.ofFile(
                BetaManagedAgentsFileResource.builder()
                    .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                    .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .mountPath("/uploads/receipt.pdf")
                    .type(BetaManagedAgentsFileResource.Type.FILE)
                    .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionResource),
                jacksonTypeRef<BetaManagedAgentsSessionResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResource)
            .isEqualTo(betaManagedAgentsSessionResource)
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
        val betaManagedAgentsSessionResource =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsSessionResource>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsSessionResource.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
