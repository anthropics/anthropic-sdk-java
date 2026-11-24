package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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
                .url("url")
                .branchCheckout("main")
                .mountPath("mount_path")
                .build()

        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofGitHubRepository(githubRepository)

        assertThat(presentVariants(betaManagedAgentsSessionResourceConfig))
            .isEqualTo(mapOf("githubRepository" to Optional.of(githubRepository)))
    }

    @Test
    fun ofGitHubRepositoryRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofGitHubRepository(
                BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
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
                .mountPath("mount_path")
                .build()

        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofFile(file)

        assertThat(presentVariants(betaManagedAgentsSessionResourceConfig))
            .isEqualTo(mapOf("file" to Optional.of(file)))
    }

    @Test
    fun ofFileRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofFile(
                BetaManagedAgentsFileResourceConfig.builder()
                    .fileId("file_id")
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
                .access(BetaManagedAgentsMemoryStoreResourceConfig.Access.READ_WRITE)
                .instructions("instructions")
                .build()

        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofMemoryStore(memoryStore)

        assertThat(presentVariants(betaManagedAgentsSessionResourceConfig))
            .isEqualTo(mapOf("memoryStore" to Optional.of(memoryStore)))
    }

    @Test
    fun ofMemoryStoreRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionResourceConfig =
            BetaManagedAgentsSessionResourceConfig.ofMemoryStore(
                BetaManagedAgentsMemoryStoreResourceConfig.builder()
                    .memoryStoreId("memory_store_id")
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

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsSessionResourceConfig =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "mount_path" to "mount_path")
                    ),
                    jacksonTypeRef<BetaManagedAgentsSessionResourceConfig>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsSessionResourceConfig.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsSessionResourceConfig.mountPath()).contains("mount_path")

        val mismatchedBetaManagedAgentsSessionResourceConfig =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "mount_path" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaManagedAgentsSessionResourceConfig>(),
                )

        assertThat(mismatchedBetaManagedAgentsSessionResourceConfig.mountPath()).isEmpty
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

        assertThat(betaManagedAgentsSessionResourceConfig.mountPath()).isEmpty
    }

    private fun presentVariants(
        betaManagedAgentsSessionResourceConfig: BetaManagedAgentsSessionResourceConfig
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "githubRepository" to betaManagedAgentsSessionResourceConfig.githubRepository(),
                "file" to betaManagedAgentsSessionResourceConfig.file(),
                "memoryStore" to betaManagedAgentsSessionResourceConfig.memoryStore(),
            )
            .filterValues { it.isPresent }
}
