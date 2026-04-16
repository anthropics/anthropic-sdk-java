// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserProfileTrustGrantTest {

    @Test
    fun create() {
        val betaUserProfileTrustGrant =
            BetaUserProfileTrustGrant.builder()
                .status(BetaUserProfileTrustGrant.Status.ACTIVE)
                .build()

        assertThat(betaUserProfileTrustGrant.status())
            .isEqualTo(BetaUserProfileTrustGrant.Status.ACTIVE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileTrustGrant =
            BetaUserProfileTrustGrant.builder()
                .status(BetaUserProfileTrustGrant.Status.ACTIVE)
                .build()

        val roundtrippedBetaUserProfileTrustGrant =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileTrustGrant),
                jacksonTypeRef<BetaUserProfileTrustGrant>(),
            )

        assertThat(roundtrippedBetaUserProfileTrustGrant).isEqualTo(betaUserProfileTrustGrant)
    }
}
