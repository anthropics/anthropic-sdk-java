// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.files.BetaDeletedFile
import com.anthropic.models.beta.files.BetaFileMetadata
import com.anthropic.models.beta.files.FileDeleteParams
import com.anthropic.models.beta.files.FileDownloadParams
import com.anthropic.models.beta.files.FileListPage
import com.anthropic.models.beta.files.FileListParams
import com.anthropic.models.beta.files.FileRetrieveMetadataParams
import com.anthropic.models.beta.files.FileUploadParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface FileService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileService

    /** List Files */
    fun list(): FileListPage = list(FileListParams.none())

    /** @see list */
    fun list(
        params: FileListParams = FileListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FileListPage

    /** @see list */
    fun list(params: FileListParams = FileListParams.none()): FileListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): FileListPage =
        list(FileListParams.none(), requestOptions)

    /** Delete File */
    fun delete(fileId: String): BetaDeletedFile = delete(fileId, FileDeleteParams.none())

    /** @see delete */
    fun delete(
        fileId: String,
        params: FileDeleteParams = FileDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedFile = delete(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see delete */
    fun delete(
        fileId: String,
        params: FileDeleteParams = FileDeleteParams.none(),
    ): BetaDeletedFile = delete(fileId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: FileDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedFile

    /** @see delete */
    fun delete(params: FileDeleteParams): BetaDeletedFile = delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(fileId: String, requestOptions: RequestOptions): BetaDeletedFile =
        delete(fileId, FileDeleteParams.none(), requestOptions)

    /** Download File */
    @MustBeClosed
    fun download(fileId: String): HttpResponse = download(fileId, FileDownloadParams.none())

    /** @see download */
    @MustBeClosed
    fun download(
        fileId: String,
        params: FileDownloadParams = FileDownloadParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse = download(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see download */
    @MustBeClosed
    fun download(
        fileId: String,
        params: FileDownloadParams = FileDownloadParams.none(),
    ): HttpResponse = download(fileId, params, RequestOptions.none())

    /** @see download */
    @MustBeClosed
    fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    /** @see download */
    @MustBeClosed
    fun download(params: FileDownloadParams): HttpResponse = download(params, RequestOptions.none())

    /** @see download */
    @MustBeClosed
    fun download(fileId: String, requestOptions: RequestOptions): HttpResponse =
        download(fileId, FileDownloadParams.none(), requestOptions)

    /** Get File Metadata */
    fun retrieveMetadata(fileId: String): BetaFileMetadata =
        retrieveMetadata(fileId, FileRetrieveMetadataParams.none())

    /** @see retrieveMetadata */
    fun retrieveMetadata(
        fileId: String,
        params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaFileMetadata =
        retrieveMetadata(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see retrieveMetadata */
    fun retrieveMetadata(
        fileId: String,
        params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
    ): BetaFileMetadata = retrieveMetadata(fileId, params, RequestOptions.none())

    /** @see retrieveMetadata */
    fun retrieveMetadata(
        params: FileRetrieveMetadataParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaFileMetadata

    /** @see retrieveMetadata */
    fun retrieveMetadata(params: FileRetrieveMetadataParams): BetaFileMetadata =
        retrieveMetadata(params, RequestOptions.none())

    /** @see retrieveMetadata */
    fun retrieveMetadata(fileId: String, requestOptions: RequestOptions): BetaFileMetadata =
        retrieveMetadata(fileId, FileRetrieveMetadataParams.none(), requestOptions)

    /** Upload File */
    fun upload(params: FileUploadParams): BetaFileMetadata = upload(params, RequestOptions.none())

    /** @see upload */
    fun upload(
        params: FileUploadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaFileMetadata

    /** A view of [FileService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/files?beta=true`, but is otherwise the same as
         * [FileService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<FileListPage> = list(FileListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: FileListParams = FileListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FileListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: FileListParams = FileListParams.none()): HttpResponseFor<FileListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<FileListPage> =
            list(FileListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/files/{file_id}?beta=true`, but is otherwise
         * the same as [FileService.delete].
         */
        @MustBeClosed
        fun delete(fileId: String): HttpResponseFor<BetaDeletedFile> =
            delete(fileId, FileDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            fileId: String,
            params: FileDeleteParams = FileDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedFile> =
            delete(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            fileId: String,
            params: FileDeleteParams = FileDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedFile> = delete(fileId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedFile>

        /** @see delete */
        @MustBeClosed
        fun delete(params: FileDeleteParams): HttpResponseFor<BetaDeletedFile> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            fileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedFile> =
            delete(fileId, FileDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/files/{file_id}/content?beta=true`, but is
         * otherwise the same as [FileService.download].
         */
        @MustBeClosed
        fun download(fileId: String): HttpResponse = download(fileId, FileDownloadParams.none())

        /** @see download */
        @MustBeClosed
        fun download(
            fileId: String,
            params: FileDownloadParams = FileDownloadParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = download(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see download */
        @MustBeClosed
        fun download(
            fileId: String,
            params: FileDownloadParams = FileDownloadParams.none(),
        ): HttpResponse = download(fileId, params, RequestOptions.none())

        /** @see download */
        @MustBeClosed
        fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see download */
        @MustBeClosed
        fun download(params: FileDownloadParams): HttpResponse =
            download(params, RequestOptions.none())

        /** @see download */
        @MustBeClosed
        fun download(fileId: String, requestOptions: RequestOptions): HttpResponse =
            download(fileId, FileDownloadParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/files/{file_id}?beta=true`, but is otherwise the
         * same as [FileService.retrieveMetadata].
         */
        @MustBeClosed
        fun retrieveMetadata(fileId: String): HttpResponseFor<BetaFileMetadata> =
            retrieveMetadata(fileId, FileRetrieveMetadataParams.none())

        /** @see retrieveMetadata */
        @MustBeClosed
        fun retrieveMetadata(
            fileId: String,
            params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaFileMetadata> =
            retrieveMetadata(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see retrieveMetadata */
        @MustBeClosed
        fun retrieveMetadata(
            fileId: String,
            params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
        ): HttpResponseFor<BetaFileMetadata> =
            retrieveMetadata(fileId, params, RequestOptions.none())

        /** @see retrieveMetadata */
        @MustBeClosed
        fun retrieveMetadata(
            params: FileRetrieveMetadataParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaFileMetadata>

        /** @see retrieveMetadata */
        @MustBeClosed
        fun retrieveMetadata(
            params: FileRetrieveMetadataParams
        ): HttpResponseFor<BetaFileMetadata> = retrieveMetadata(params, RequestOptions.none())

        /** @see retrieveMetadata */
        @MustBeClosed
        fun retrieveMetadata(
            fileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaFileMetadata> =
            retrieveMetadata(fileId, FileRetrieveMetadataParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/files?beta=true`, but is otherwise the same as
         * [FileService.upload].
         */
        @MustBeClosed
        fun upload(params: FileUploadParams): HttpResponseFor<BetaFileMetadata> =
            upload(params, RequestOptions.none())

        /** @see upload */
        @MustBeClosed
        fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaFileMetadata>
    }
}
