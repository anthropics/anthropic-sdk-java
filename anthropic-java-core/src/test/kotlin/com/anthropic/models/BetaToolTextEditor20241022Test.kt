// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolTextEditor20241022Test {

    @Test
    fun createBetaToolTextEditor20241022() {
        val betaToolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .name(BetaToolTextEditor20241022.Name.STR_REPLACE_EDITOR)
                .type(BetaToolTextEditor20241022.Type.TEXT_EDITOR_20241022)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(betaToolTextEditor20241022).isNotNull
        assertThat(betaToolTextEditor20241022.name())
            .isEqualTo(BetaToolTextEditor20241022.Name.STR_REPLACE_EDITOR)
        assertThat(betaToolTextEditor20241022.type())
            .isEqualTo(BetaToolTextEditor20241022.Type.TEXT_EDITOR_20241022)
        assertThat(betaToolTextEditor20241022.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
    }
}
