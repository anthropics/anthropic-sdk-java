// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResourceListPageResponseTest {

    @Test
    fun create() {
        val resourceListPageResponse =
            ResourceListPageResponse.builder()
                .addData(
                    BetaManagedAgentsFileResource.builder()
                        .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .mountPath("/uploads/receipt.pdf")
                        .type(BetaManagedAgentsFileResource.Type.FILE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .addData(
                    BetaManagedAgentsGitHubRepositoryResource.builder()
                        .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .mountPath("/workspace/example-repo")
                        .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .url("https://github.com/example-org/example-repo")
                        .branchCheckout("main")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        assertThat(resourceListPageResponse.data())
            .containsExactly(
                BetaManagedAgentsSessionResource.ofFile(
                    BetaManagedAgentsFileResource.builder()
                        .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .mountPath("/uploads/receipt.pdf")
                        .type(BetaManagedAgentsFileResource.Type.FILE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                ),
                BetaManagedAgentsSessionResource.ofGitHubRepository(
                    BetaManagedAgentsGitHubRepositoryResource.builder()
                        .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .mountPath("/workspace/example-repo")
                        .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .url("https://github.com/example-org/example-repo")
                        .branchCheckout("main")
                        .build()
                ),
            )
        assertThat(resourceListPageResponse.nextPage())
            .contains("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val resourceListPageResponse =
            ResourceListPageResponse.builder()
                .addData(
                    BetaManagedAgentsFileResource.builder()
                        .id("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .mountPath("/uploads/receipt.pdf")
                        .type(BetaManagedAgentsFileResource.Type.FILE)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .build()
                )
                .addData(
                    BetaManagedAgentsGitHubRepositoryResource.builder()
                        .id("sesrsc_011CZkZCKr6eXyl0gWMOdQiu")
                        .createdAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .mountPath("/workspace/example-repo")
                        .type(BetaManagedAgentsGitHubRepositoryResource.Type.GITHUB_REPOSITORY)
                        .updatedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                        .url("https://github.com/example-org/example-repo")
                        .branchCheckout("main")
                        .build()
                )
                .nextPage("page_MjAyNS0wNS0xNFQwMDowMDowMFo=")
                .build()

        val roundtrippedResourceListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(resourceListPageResponse),
                jacksonTypeRef<ResourceListPageResponse>(),
            )

        assertThat(roundtrippedResourceListPageResponse).isEqualTo(resourceListPageResponse)
    }
}
