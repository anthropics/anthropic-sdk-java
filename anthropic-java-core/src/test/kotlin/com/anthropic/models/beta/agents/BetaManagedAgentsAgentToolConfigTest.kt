package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsAgentToolConfigTest {

    @Test
    fun ofBash() {
        val bash =
            BetaManagedAgentsBashToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofBash(bash)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("bash" to Optional.of(bash)))
    }

    @Test
    fun ofBashRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofBash(
                BetaManagedAgentsBashToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun ofEdit() {
        val edit =
            BetaManagedAgentsEditToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofEdit(edit)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("edit" to Optional.of(edit)))
    }

    @Test
    fun ofEditRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofEdit(
                BetaManagedAgentsEditToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun ofRead() {
        val read =
            BetaManagedAgentsReadToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofRead(read)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("read" to Optional.of(read)))
    }

    @Test
    fun ofReadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofRead(
                BetaManagedAgentsReadToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun ofWrite() {
        val write =
            BetaManagedAgentsWriteToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofWrite(write)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("write" to Optional.of(write)))
    }

    @Test
    fun ofWriteRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWrite(
                BetaManagedAgentsWriteToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun ofGlob() {
        val glob =
            BetaManagedAgentsGlobToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofGlob(glob)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("glob" to Optional.of(glob)))
    }

    @Test
    fun ofGlobRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofGlob(
                BetaManagedAgentsGlobToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun ofGrep() {
        val grep =
            BetaManagedAgentsGrepToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofGrep(grep)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("grep" to Optional.of(grep)))
    }

    @Test
    fun ofGrepRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofGrep(
                BetaManagedAgentsGrepToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun ofWebFetch() {
        val webFetch =
            BetaManagedAgentsWebFetchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .urlSources(
                    BetaManagedAgentsWebFetchUrlSources.builder()
                        .clientToolResults(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                        .serverToolResults(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                        .userInput(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                        .build()
                )
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .maxContentTokens(0)
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofWebFetch(webFetch)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("webFetch" to Optional.of(webFetch)))
    }

    @Test
    fun ofWebFetchRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWebFetch(
                BetaManagedAgentsWebFetchToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .urlSources(
                        BetaManagedAgentsWebFetchUrlSources.builder()
                            .clientToolResults(
                                BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
                            )
                            .serverToolResults(
                                BetaManagedAgentsWebFetchUrlSourceAll.builder().build()
                            )
                            .userInput(BetaManagedAgentsWebFetchUrlSourceAll.builder().build())
                            .build()
                    )
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .maxContentTokens(0)
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun ofWebSearch() {
        val webSearch =
            BetaManagedAgentsWebSearchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("SE")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWebSearch(webSearch)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfig))
            .isEqualTo(mapOf("webSearch" to Optional.of(webSearch)))
    }

    @Test
    fun ofWebSearchRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWebSearch(
                BetaManagedAgentsWebSearchToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .userLocation(
                        BetaManagedAgentsUserLocation.builder()
                            .city("x")
                            .country("SE")
                            .region("x")
                            .timezone("x")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsAgentToolConfig =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "enabled" to true,
                            "allowed_domains" to listOf("string"),
                            "blocked_domains" to listOf("string"),
                        )
                    ),
                    jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsAgentToolConfig.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsAgentToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsAgentToolConfig.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsAgentToolConfig.blockedDomains().getOrNull())
            .containsExactly("string")

        val mismatchedBetaManagedAgentsAgentToolConfig =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "enabled" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
                )

        assertThrows<AnthropicInvalidDataException> {
            mismatchedBetaManagedAgentsAgentToolConfig.enabled()
        }
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
        val betaManagedAgentsAgentToolConfig =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsAgentToolConfig>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsAgentToolConfig.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaManagedAgentsAgentToolConfig.enabled() }
        assertThat(betaManagedAgentsAgentToolConfig.allowedDomains()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.blockedDomains()).isEmpty
    }

    private fun presentVariants(
        betaManagedAgentsAgentToolConfig: BetaManagedAgentsAgentToolConfig
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "bash" to betaManagedAgentsAgentToolConfig.bash(),
                "edit" to betaManagedAgentsAgentToolConfig.edit(),
                "read" to betaManagedAgentsAgentToolConfig.read(),
                "write" to betaManagedAgentsAgentToolConfig.write(),
                "glob" to betaManagedAgentsAgentToolConfig.glob(),
                "grep" to betaManagedAgentsAgentToolConfig.grep(),
                "webFetch" to betaManagedAgentsAgentToolConfig.webFetch(),
                "webSearch" to betaManagedAgentsAgentToolConfig.webSearch(),
            )
            .filterValues { it.isPresent }
}
