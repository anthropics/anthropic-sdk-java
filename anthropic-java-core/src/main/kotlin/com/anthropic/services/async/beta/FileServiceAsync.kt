// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.files.DeletedFile
import com.anthropic.models.beta.files.FileDeleteParams
import com.anthropic.models.beta.files.FileDownloadParams
import com.anthropic.models.beta.files.FileListPageAsync
import com.anthropic.models.beta.files.FileListParams
import com.anthropic.models.beta.files.FileMetadata
import com.anthropic.models.beta.files.FileRetrieveMetadataParams
import com.anthropic.models.beta.files.FileUploadParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface FileServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync

    /** List Files */
    fun list(): CompletableFuture<FileListPageAsync> = list(FileListParams.none())

    /** @see [list] */
    fun list(
        params: FileListParams = FileListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<FileListPageAsync>

    /** @see [list] */
    fun list(params: FileListParams = FileListParams.none()): CompletableFuture<FileListPageAsync> =
        list(params, RequestOptions.none())

    /** @see [list] */
    fun list(requestOptions: RequestOptions): CompletableFuture<FileListPageAsync> =
        list(FileListParams.none(), requestOptions)

    /** Delete File */
    fun delete(fileId: String): CompletableFuture<DeletedFile> =
        delete(fileId, FileDeleteParams.none())

    /** @see [delete] */
    fun delete(
        fileId: String,
        params: FileDeleteParams = FileDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedFile> =
        delete(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see [delete] */
    fun delete(
        fileId: String,
        params: FileDeleteParams = FileDeleteParams.none(),
    ): CompletableFuture<DeletedFile> = delete(fileId, params, RequestOptions.none())

    /** @see [delete] */
    fun delete(
        params: FileDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedFile>

    /** @see [delete] */
    fun delete(params: FileDeleteParams): CompletableFuture<DeletedFile> =
        delete(params, RequestOptions.none())

    /** @see [delete] */
    fun delete(fileId: String, requestOptions: RequestOptions): CompletableFuture<DeletedFile> =
        delete(fileId, FileDeleteParams.none(), requestOptions)

    /** Download File */
    fun download(fileId: String): CompletableFuture<HttpResponse> =
        download(fileId, FileDownloadParams.none())

    /** @see [download] */
    fun download(
        fileId: String,
        params: FileDownloadParams = FileDownloadParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<HttpResponse> =
        download(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see [download] */
    fun download(
        fileId: String,
        params: FileDownloadParams = FileDownloadParams.none(),
    ): CompletableFuture<HttpResponse> = download(fileId, params, RequestOptions.none())

    /** @see [download] */
    fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<HttpResponse>

    /** @see [download] */
    fun download(params: FileDownloadParams): CompletableFuture<HttpResponse> =
        download(params, RequestOptions.none())

    /** @see [download] */
    fun download(fileId: String, requestOptions: RequestOptions): CompletableFuture<HttpResponse> =
        download(fileId, FileDownloadParams.none(), requestOptions)

    /** Get File Metadata */
    fun retrieveMetadata(fileId: String): CompletableFuture<FileMetadata> =
        retrieveMetadata(fileId, FileRetrieveMetadataParams.none())

    /** @see [retrieveMetadata] */
    fun retrieveMetadata(
        fileId: String,
        params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<FileMetadata> =
        retrieveMetadata(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see [retrieveMetadata] */
    fun retrieveMetadata(
        fileId: String,
        params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
    ): CompletableFuture<FileMetadata> = retrieveMetadata(fileId, params, RequestOptions.none())

    /** @see [retrieveMetadata] */
    fun retrieveMetadata(
        params: FileRetrieveMetadataParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<FileMetadata>

    /** @see [retrieveMetadata] */
    fun retrieveMetadata(params: FileRetrieveMetadataParams): CompletableFuture<FileMetadata> =
        retrieveMetadata(params, RequestOptions.none())

    /** @see [retrieveMetadata] */
    fun retrieveMetadata(
        fileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<FileMetadata> =
        retrieveMetadata(fileId, FileRetrieveMetadataParams.none(), requestOptions)

    /** Upload File */
    fun upload(params: FileUploadParams): CompletableFuture<FileMetadata> =
        upload(params, RequestOptions.none())

    /** @see [upload] */
    fun upload(
        params: FileUploadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<FileMetadata>

    /** A view of [FileServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/files?beta=true`, but is otherwise the same as
         * [FileServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<FileListPageAsync>> =
            list(FileListParams.none())

        /** @see [list] */
        fun list(
            params: FileListParams = FileListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<FileListPageAsync>>

        /** @see [list] */
        fun list(
            params: FileListParams = FileListParams.none()
        ): CompletableFuture<HttpResponseFor<FileListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see [list] */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<FileListPageAsync>> =
            list(FileListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/files/{file_id}?beta=true`, but is otherwise
         * the same as [FileServiceAsync.delete].
         */
        fun delete(fileId: String): CompletableFuture<HttpResponseFor<DeletedFile>> =
            delete(fileId, FileDeleteParams.none())

        /** @see [delete] */
        fun delete(
            fileId: String,
            params: FileDeleteParams = FileDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedFile>> =
            delete(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see [delete] */
        fun delete(
            fileId: String,
            params: FileDeleteParams = FileDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<DeletedFile>> =
            delete(fileId, params, RequestOptions.none())

        /** @see [delete] */
        fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedFile>>

        /** @see [delete] */
        fun delete(params: FileDeleteParams): CompletableFuture<HttpResponseFor<DeletedFile>> =
            delete(params, RequestOptions.none())

        /** @see [delete] */
        fun delete(
            fileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedFile>> =
            delete(fileId, FileDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/files/{file_id}/content?beta=true`, but is
         * otherwise the same as [FileServiceAsync.download].
         */
        fun download(fileId: String): CompletableFuture<HttpResponse> =
            download(fileId, FileDownloadParams.none())

        /** @see [download] */
        fun download(
            fileId: String,
            params: FileDownloadParams = FileDownloadParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponse> =
            download(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see [download] */
        fun download(
            fileId: String,
            params: FileDownloadParams = FileDownloadParams.none(),
        ): CompletableFuture<HttpResponse> = download(fileId, params, RequestOptions.none())

        /** @see [download] */
        fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponse>

        /** @see [download] */
        fun download(params: FileDownloadParams): CompletableFuture<HttpResponse> =
            download(params, RequestOptions.none())

        /** @see [download] */
        fun download(
            fileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            download(fileId, FileDownloadParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/files/{file_id}?beta=true`, but is otherwise the
         * same as [FileServiceAsync.retrieveMetadata].
         */
        fun retrieveMetadata(fileId: String): CompletableFuture<HttpResponseFor<FileMetadata>> =
            retrieveMetadata(fileId, FileRetrieveMetadataParams.none())

        /** @see [retrieveMetadata] */
        fun retrieveMetadata(
            fileId: String,
            params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<FileMetadata>> =
            retrieveMetadata(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see [retrieveMetadata] */
        fun retrieveMetadata(
            fileId: String,
            params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
        ): CompletableFuture<HttpResponseFor<FileMetadata>> =
            retrieveMetadata(fileId, params, RequestOptions.none())

        /** @see [retrieveMetadata] */
        fun retrieveMetadata(
            params: FileRetrieveMetadataParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<FileMetadata>>

        /** @see [retrieveMetadata] */
        fun retrieveMetadata(
            params: FileRetrieveMetadataParams
        ): CompletableFuture<HttpResponseFor<FileMetadata>> =
            retrieveMetadata(params, RequestOptions.none())

        /** @see [retrieveMetadata] */
        fun retrieveMetadata(
            fileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<FileMetadata>> =
            retrieveMetadata(fileId, FileRetrieveMetadataParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/files?beta=true`, but is otherwise the same as
         * [FileServiceAsync.upload].
         */
        fun upload(params: FileUploadParams): CompletableFuture<HttpResponseFor<FileMetadata>> =
            upload(params, RequestOptions.none())

        /** @see [upload] */
        fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<FileMetadata>>
    }
}
