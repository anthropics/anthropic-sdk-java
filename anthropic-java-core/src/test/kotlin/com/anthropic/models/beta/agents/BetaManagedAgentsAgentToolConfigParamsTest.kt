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

internal class BetaManagedAgentsAgentToolConfigParamsTest {

    @Test
    fun ofBash() {
        val bash =
            BetaManagedAgentsBashToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofBash(bash)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("bash" to Optional.of(bash)))
    }

    @Test
    fun ofBashRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofBash(
                BetaManagedAgentsBashToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun ofEdit() {
        val edit =
            BetaManagedAgentsEditToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsEditToolConfigParams.Type.EDIT)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofEdit(edit)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("edit" to Optional.of(edit)))
    }

    @Test
    fun ofEditRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofEdit(
                BetaManagedAgentsEditToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsEditToolConfigParams.Type.EDIT)
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun ofRead() {
        val read =
            BetaManagedAgentsReadToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsReadToolConfigParams.Type.READ)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofRead(read)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("read" to Optional.of(read)))
    }

    @Test
    fun ofReadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofRead(
                BetaManagedAgentsReadToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsReadToolConfigParams.Type.READ)
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun ofWrite() {
        val write =
            BetaManagedAgentsWriteToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsWriteToolConfigParams.Type.WRITE)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWrite(write)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("write" to Optional.of(write)))
    }

    @Test
    fun ofWriteRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWrite(
                BetaManagedAgentsWriteToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsWriteToolConfigParams.Type.WRITE)
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun ofGlob() {
        val glob =
            BetaManagedAgentsGlobToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsGlobToolConfigParams.Type.GLOB)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGlob(glob)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("glob" to Optional.of(glob)))
    }

    @Test
    fun ofGlobRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGlob(
                BetaManagedAgentsGlobToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsGlobToolConfigParams.Type.GLOB)
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun ofGrep() {
        val grep =
            BetaManagedAgentsGrepToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsGrepToolConfigParams.Type.GREP)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGrep(grep)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("grep" to Optional.of(grep)))
    }

    @Test
    fun ofGrepRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGrep(
                BetaManagedAgentsGrepToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsGrepToolConfigParams.Type.GREP)
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun ofWebFetch() {
        val webFetch =
            BetaManagedAgentsWebFetchToolConfigParams.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .enabled(true)
                .maxContentTokens(0)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsWebFetchToolConfigParams.Type.WEB_FETCH)
                .urlSources(
                    BetaManagedAgentsWebFetchUrlSourcesParams.builder()
                        .clientToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                        .serverToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                        .userInput(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                        .build()
                )
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWebFetch(webFetch)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("webFetch" to Optional.of(webFetch)))
    }

    @Test
    fun ofWebFetchRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWebFetch(
                BetaManagedAgentsWebFetchToolConfigParams.builder()
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .enabled(true)
                    .maxContentTokens(0)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsWebFetchToolConfigParams.Type.WEB_FETCH)
                    .urlSources(
                        BetaManagedAgentsWebFetchUrlSourcesParams.builder()
                            .clientToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                            .serverToolResults(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                            .userInput(BetaManagedAgentsWebFetchUrlSourceShorthand.ALL)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun ofWebSearch() {
        val webSearch =
            BetaManagedAgentsWebSearchToolConfigParams.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsWebSearchToolConfigParams.Type.WEB_SEARCH)
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("SE")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWebSearch(webSearch)

        assertThat(presentVariants(betaManagedAgentsAgentToolConfigParams))
            .isEqualTo(mapOf("webSearch" to Optional.of(webSearch)))
    }

    @Test
    fun ofWebSearchRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWebSearch(
                BetaManagedAgentsWebSearchToolConfigParams.builder()
                    .addAllowedDomain("string")
                    .addBlockedDomain("string")
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .type(BetaManagedAgentsWebSearchToolConfigParams.Type.WEB_SEARCH)
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

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsAgentToolConfigParams =
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
                    jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsAgentToolConfigParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsAgentToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsAgentToolConfigParams.allowedDomains().getOrNull())
            .containsExactly("string")
        assertThat(betaManagedAgentsAgentToolConfigParams.blockedDomains().getOrNull())
            .containsExactly("string")

        val mismatchedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "enabled" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
                )

        assertThat(mismatchedBetaManagedAgentsAgentToolConfigParams.enabled()).isEmpty
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
        val betaManagedAgentsAgentToolConfigParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsAgentToolConfigParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsAgentToolConfigParams.enabled()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.allowedDomains()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.blockedDomains()).isEmpty
    }

    private fun presentVariants(
        betaManagedAgentsAgentToolConfigParams: BetaManagedAgentsAgentToolConfigParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "bash" to betaManagedAgentsAgentToolConfigParams.bash(),
                "edit" to betaManagedAgentsAgentToolConfigParams.edit(),
                "read" to betaManagedAgentsAgentToolConfigParams.read(),
                "write" to betaManagedAgentsAgentToolConfigParams.write(),
                "glob" to betaManagedAgentsAgentToolConfigParams.glob(),
                "grep" to betaManagedAgentsAgentToolConfigParams.grep(),
                "webFetch" to betaManagedAgentsAgentToolConfigParams.webFetch(),
                "webSearch" to betaManagedAgentsAgentToolConfigParams.webSearch(),
            )
            .filterValues { it.isPresent }
}
