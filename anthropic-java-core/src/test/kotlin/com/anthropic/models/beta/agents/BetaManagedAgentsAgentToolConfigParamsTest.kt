// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofBash(bash)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).contains(bash)
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).isEmpty
    }

    @Test
    fun ofBashRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofBash(
                BetaManagedAgentsBashToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsEditToolConfigParams.Type.EDIT)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofEdit(edit)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).contains(edit)
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).isEmpty
    }

    @Test
    fun ofEditRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofEdit(
                BetaManagedAgentsEditToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsReadToolConfigParams.Type.READ)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofRead(read)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).contains(read)
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).isEmpty
    }

    @Test
    fun ofReadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofRead(
                BetaManagedAgentsReadToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsWriteToolConfigParams.Type.WRITE)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWrite(write)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).contains(write)
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).isEmpty
    }

    @Test
    fun ofWriteRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWrite(
                BetaManagedAgentsWriteToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsGlobToolConfigParams.Type.GLOB)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGlob(glob)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).contains(glob)
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).isEmpty
    }

    @Test
    fun ofGlobRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGlob(
                BetaManagedAgentsGlobToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsGrepToolConfigParams.Type.GREP)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGrep(grep)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).contains(grep)
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).isEmpty
    }

    @Test
    fun ofGrepRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofGrep(
                BetaManagedAgentsGrepToolConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsWebFetchToolConfigParams.Type.WEB_FETCH)
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWebFetch(webFetch)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).contains(webFetch)
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).isEmpty
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
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
                    .type(BetaManagedAgentsWebFetchToolConfigParams.Type.WEB_FETCH)
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
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsWebSearchToolConfigParams.Type.WEB_SEARCH)
                .userLocation(
                    BetaManagedAgentsUserLocation.builder()
                        .city("x")
                        .country("country")
                        .region("x")
                        .timezone("x")
                        .build()
                )
                .build()

        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.ofWebSearch(webSearch)

        assertThat(betaManagedAgentsAgentToolConfigParams.bash()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.edit()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.read()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.write()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.glob()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.grep()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webFetch()).isEmpty
        assertThat(betaManagedAgentsAgentToolConfigParams.webSearch()).contains(webSearch)
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
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
                    .type(BetaManagedAgentsWebSearchToolConfigParams.Type.WEB_SEARCH)
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

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
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
    }
}
