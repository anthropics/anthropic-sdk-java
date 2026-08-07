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
            BetaUserProfileTrustGrant.of(BetaUserProfileTrustGrant.Status.ACTIVE)

        assertThat(betaUserProfileTrustGrant.status())
            .isEqualTo(BetaUserProfileTrustGrant.Status.ACTIVE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileTrustGrant =
            BetaUserProfileTrustGrant.of(BetaUserProfileTrustGrant.Status.ACTIVE)

        val roundtrippedBetaUserProfileTrustGrant =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileTrustGrant),
                jacksonTypeRef<BetaUserProfileTrustGrant>(),
            )

        assertThat(roundtrippedBetaUserProfileTrustGrant).isEqualTo(betaUserProfileTrustGrant)
    }
}
