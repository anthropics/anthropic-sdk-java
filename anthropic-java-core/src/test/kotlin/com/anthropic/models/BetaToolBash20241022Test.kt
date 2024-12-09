// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolBash20241022Test {

    @Test
    fun createBetaToolBash20241022() {
        val betaToolBash20241022 =
            BetaToolBash20241022.builder()
                .name(BetaToolBash20241022.Name.BASH)
                .type(BetaToolBash20241022.Type.BASH_20241022)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                        .build()
                )
                .build()
        assertThat(betaToolBash20241022).isNotNull
        assertThat(betaToolBash20241022.name()).isEqualTo(BetaToolBash20241022.Name.BASH)
        assertThat(betaToolBash20241022.type()).isEqualTo(BetaToolBash20241022.Type.BASH_20241022)
        assertThat(betaToolBash20241022.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .type(BetaCacheControlEphemeral.Type.EPHEMERAL)
                    .build()
            )
    }
}
