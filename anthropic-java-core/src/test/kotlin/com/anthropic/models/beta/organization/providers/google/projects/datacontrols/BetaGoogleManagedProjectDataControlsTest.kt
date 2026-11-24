package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectDataControlsTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectDataControls =
            BetaGoogleManagedProjectDataControls.builder()
                .disabledCmek(
                    listOf(
                        BetaGoogleManagedProjectRetiredKey.builder()
                            .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                            .region("us")
                            .build()
                    )
                )
                .projectNumber("123456789012")
                .disabledStorage(
                    listOf(
                        BetaGoogleManagedProjectRetiredBucket.builder()
                            .bucketName("bucket_name")
                            .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .projectNumber("project_number")
                            .region("us")
                            .build()
                    )
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaGoogleManagedProjectDataControls.cmek())
            .isEqualTo(
                BetaGoogleManagedProjectCmekControl.ofDisabled(
                    BetaGoogleManagedProjectCmekDisabled.builder()
                        .addRetiredKey(
                            BetaGoogleManagedProjectRetiredKey.builder()
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                                .region("us")
                                .build()
                        )
                        .build()
                )
            )
        assertThat(betaGoogleManagedProjectDataControls.projectNumber()).isEqualTo("123456789012")
        assertThat(betaGoogleManagedProjectDataControls.storage())
            .isEqualTo(
                BetaGoogleManagedProjectStorageControl.ofDisabled(
                    BetaGoogleManagedProjectStorageDisabled.builder()
                        .addRetiredBucket(
                            BetaGoogleManagedProjectRetiredBucket.builder()
                                .bucketName("bucket_name")
                                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .projectNumber("project_number")
                                .region("us")
                                .build()
                        )
                        .build()
                )
            )
        assertThat(betaGoogleManagedProjectDataControls.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectDataControls =
            BetaGoogleManagedProjectDataControls.builder()
                .disabledCmek(
                    listOf(
                        BetaGoogleManagedProjectRetiredKey.builder()
                            .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                            .region("us")
                            .build()
                    )
                )
                .projectNumber("123456789012")
                .disabledStorage(
                    listOf(
                        BetaGoogleManagedProjectRetiredBucket.builder()
                            .bucketName("bucket_name")
                            .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .projectNumber("project_number")
                            .region("us")
                            .build()
                    )
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaGoogleManagedProjectDataControls =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectDataControls),
                jacksonTypeRef<BetaGoogleManagedProjectDataControls>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectDataControls)
            .isEqualTo(betaGoogleManagedProjectDataControls)
    }
}
