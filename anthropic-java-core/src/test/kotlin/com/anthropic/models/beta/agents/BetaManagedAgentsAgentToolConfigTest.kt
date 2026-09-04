// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofBash(bash)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).contains(bash)
        assertThat(betaManagedAgentsAgentToolConfig.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).isEmpty
    }

    @Test
    fun ofBashRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofBash(
                BetaManagedAgentsBashToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
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
    fun ofEdit() {
        val edit =
            BetaManagedAgentsEditToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofEdit(edit)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.edit()).contains(edit)
        assertThat(betaManagedAgentsAgentToolConfig.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).isEmpty
    }

    @Test
    fun ofEditRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofEdit(
                BetaManagedAgentsEditToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
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
    fun ofRead() {
        val read =
            BetaManagedAgentsReadToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofRead(read)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.read()).contains(read)
        assertThat(betaManagedAgentsAgentToolConfig.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).isEmpty
    }

    @Test
    fun ofReadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofRead(
                BetaManagedAgentsReadToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
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
    fun ofWrite() {
        val write =
            BetaManagedAgentsWriteToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofWrite(write)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.write()).contains(write)
        assertThat(betaManagedAgentsAgentToolConfig.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).isEmpty
    }

    @Test
    fun ofWriteRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWrite(
                BetaManagedAgentsWriteToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
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
    fun ofGlob() {
        val glob =
            BetaManagedAgentsGlobToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofGlob(glob)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.glob()).contains(glob)
        assertThat(betaManagedAgentsAgentToolConfig.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).isEmpty
    }

    @Test
    fun ofGlobRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofGlob(
                BetaManagedAgentsGlobToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
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
    fun ofGrep() {
        val grep =
            BetaManagedAgentsGrepToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofGrep(grep)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.grep()).contains(grep)
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).isEmpty
    }

    @Test
    fun ofGrepRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofGrep(
                BetaManagedAgentsGrepToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
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
    fun ofWebFetch() {
        val webFetch =
            BetaManagedAgentsWebFetchToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .maxContentTokens(0)
                .build()

        val betaManagedAgentsAgentToolConfig = BetaManagedAgentsAgentToolConfig.ofWebFetch(webFetch)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).contains(webFetch)
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).isEmpty
    }

    @Test
    fun ofWebFetchRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWebFetch(
                BetaManagedAgentsWebFetchToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("country")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWebSearch(webSearch)

        assertThat(betaManagedAgentsAgentToolConfig.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfig.webSearch()).contains(webSearch)
    }

    @Test
    fun ofWebSearchRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.ofWebSearch(
                BetaManagedAgentsWebSearchToolConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .userLocation(
                        BetaManagedAgentsUserLocation.builder()
                            .city("x")
                            .country("country")
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
}
