package com.anthropic.helpers

import java.util.Optional

/**
 * A memory tool handler enables Claude to store and retrieve information across conversations
 * through a filesystem-like API. Claude can create, read, update, and delete "files" that persist
 * between sessions, allowing it to build knowledge over time without keeping everything in the
 * context window.
 *
 * While filesystem terminology is used throughout this interface, implementations are free to use
 * any underlying storage (filesystem, memory, database, cloud storage, etc.).
 *
 * Each method of this interface corresponds to a memory tool _command_ that may be included in a
 * Claude message response. Each method handles the operation corresponding to one of those
 * commands.
 *
 * For more details on memory tools in general, and on the specific formats of the inputs, outputs,
 * and error messages for each command, consult the Claude
 * [Memory tool](https://platform.claude.com/docs/en/agents-and-tools/tool-use/memory-tool)
 * documentation.
 *
 * All functions return a string, which may be an error message to inform Claude of any problem
 * encountered when attempting to execute a memory tool command. Where line numbers are requested or
 * reported back, the first line is numbered 1.
 */
interface BetaMemoryToolHandler {
    /**
     * Creates a new file.
     *
     * @param path Path to where the file should be created.
     * @param fileText Content to write to the file.
     * @return A response indicating successful creation of the file, or an error message if no file
     *   was created. See the Claude
     *   [Memory tool](https://platform.claude.com/docs/en/agents-and-tools/tool-use/memory-tool)
     *   documentation for details on the expected format of the response.
     */
    fun create(path: String, fileText: String): String

    /**
     * Shows directory contents or file contents with optional line ranges.
     *
     * @param path Path to the directory or file to view.
     * @param viewRange Optional two-element list `[startLine, endLine]` (1-based, inclusive) for
     *   viewing a specific range of lines. If empty, all lines will be viewed.
     * @return A response listing the directory or file contents, or an error message if a problem
     *   occurred. See the Claude
     *   [Memory tool](https://platform.claude.com/docs/en/agents-and-tools/tool-use/memory-tool)
     *   documentation for details on the expected format of the response.
     */
    fun view(path: String, viewRange: Optional<List<Long>>): String

    /**
     * Replaces text in a file.
     *
     * @param path Path to the file.
     * @param oldStr The existing text to be replaced with [newStr].
     * @param newStr The text to replace instances of [oldStr].
     * @return A response confirming the successful replacement, or an error message if a problem
     *   occurred. See the Claude
     *   [Memory tool](https://platform.claude.com/docs/en/agents-and-tools/tool-use/memory-tool)
     *   documentation for details on the expected format of the response.
     */
    fun strReplace(path: String, oldStr: String, newStr: String): String

    /**
     * Inserts text at a specific line.
     *
     * @param path Path to the file.
     * @param insertLine The number of the line after which the inserted text should appear. To
     *   insert before the first line (counted as line 1), use `0`.
     * @param insertText The text to insert at the [insertLine].
     * @return A response confirming the successful insertion, or an error message if a problem
     *   occurred. See the Claude
     *   [Memory tool](https://platform.claude.com/docs/en/agents-and-tools/tool-use/memory-tool)
     *   documentation for details on the expected format of the response.
     */
    fun insert(path: String, insertLine: Long, insertText: String): String

    /**
     * Deletes a file or directory. If a directory, deletes the directory and all its contents
     * recursively.
     *
     * @param path Path to the file or directory to delete.
     * @return A response confirming the successful deletion, or an error message if a problem
     *   occurred. See the Claude
     *   [Memory tool](https://platform.claude.com/docs/en/agents-and-tools/tool-use/memory-tool)
     *   documentation for details on the expected format of the response.
     */
    fun delete(path: String): String

    /**
     * Renames or moves a file or directory.
     *
     * @param oldPath The path to the file or directory to be renamed (or moved).
     * @param newPath The new path to that file or directory.
     * @return A response confirming the successful renaming, or an error message if a problem
     *   occurred. See the Claude
     *   [Memory tool](https://platform.claude.com/docs/en/agents-and-tools/tool-use/memory-tool)
     *   documentation for details on the expected format of the response.
     */
    fun rename(oldPath: String, newPath: String): String
}
