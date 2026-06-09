// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsSessionResourceConfigTest {

    @Test
    fun ofGitHubRepository() {
        val githubRepository =
            BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                .type(BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY)
                .url("url")
                .branchCheckout("main")
                .mountPath("mount_path")
                .build()

        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofGitHubRepository(githubRepository)

        assertThat(betaManagedAgentsSessionResourceConfig.githubRepository())
            .contains(githubRepository)
        assertThat(betaManagedAgentsSessionResourceConfig.file()).isEmpty
        assertThat(betaManagedAgentsSessionResourceConfig.memoryStore()).isEmpty
    }

    @Test
    fun ofGitHubRepositoryRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofGitHubRepository(
                BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                    .type(BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY)
                    .url("url")
                    .branchCheckout("main")
                    .mountPath("mount_path")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionResourceConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionResourceConfig),
                jacksonTypeRef<BetaManagedAgentsSessionResourceConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResourceConfig)
            .isEqualTo(betaManagedAgentsSessionResourceConfig)
    }

    @Test
    fun ofFile() {
        val file =
            BetaManagedAgentsFileResourceConfig.builder()
                .fileId("file_id")
                .type(BetaManagedAgentsFileResourceConfig.Type.FILE)
                .mountPath("mount_path")
                .build()

        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofFile(file)

        assertThat(betaManagedAgentsSessionResourceConfig.githubRepository()).isEmpty
        assertThat(betaManagedAgentsSessionResourceConfig.file()).contains(file)
        assertThat(betaManagedAgentsSessionResourceConfig.memoryStore()).isEmpty
    }

    @Test
    fun ofFileRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofFile(
                BetaManagedAgentsFileResourceConfig.builder()
                    .fileId("file_id")
                    .type(BetaManagedAgentsFileResourceConfig.Type.FILE)
                    .mountPath("mount_path")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionResourceConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionResourceConfig),
                jacksonTypeRef<BetaManagedAgentsSessionResourceConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResourceConfig)
            .isEqualTo(betaManagedAgentsSessionResourceConfig)
    }

    @Test
    fun ofMemoryStore() {
        val memoryStore =
            BetaManagedAgentsMemoryStoreResourceConfig.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaManagedAgentsMemoryStoreResourceConfig.Type.MEMORY_STORE)
                .access(BetaManagedAgentsMemoryStoreResourceConfig.Access.READ_WRITE)
                .instructions("instructions")
                .build()

        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofMemoryStore(memoryStore)

        assertThat(betaManagedAgentsSessionResourceConfig.githubRepository()).isEmpty
        assertThat(betaManagedAgentsSessionResourceConfig.file()).isEmpty
        assertThat(betaManagedAgentsSessionResourceConfig.memoryStore()).contains(memoryStore)
    }

    @Test
    fun ofMemoryStoreRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofMemoryStore(
                BetaManagedAgentsMemoryStoreResourceConfig.builder()
                    .memoryStoreId("memory_store_id")
                    .type(BetaManagedAgentsMemoryStoreResourceConfig.Type.MEMORY_STORE)
                    .access(BetaManagedAgentsMemoryStoreResourceConfig.Access.READ_WRITE)
                    .instructions("instructions")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionResourceConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionResourceConfig),
                jacksonTypeRef<BetaManagedAgentsSessionResourceConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionResourceConfig)
            .isEqualTo(betaManagedAgentsSessionResourceConfig)
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
        val betaManagedAgentsSessionResourceConfig =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsSessionResourceConfig>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsSessionResourceConfig.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
