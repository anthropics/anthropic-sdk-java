package com.anthropic.example;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.helpers.BetaMemoryToolHandler;
import com.anthropic.helpers.BetaToolRunner;
import com.anthropic.models.beta.messages.BetaMemoryTool20250818;
import com.anthropic.models.beta.messages.BetaMessage;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.models.beta.messages.ToolRunnerCreateParams;
import com.anthropic.models.messages.Model;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * Example demonstrating the use of a filesystem-based memory tool with the tool runner.
 * </p>
 * <p>
 * <b>WARNING:</b> This example modifies the contents of the `memories_example` directory in the
 * home directory of the current user. If a directory with that name exists for other reasons,
 * protect it by changing the name of the directory in the code below (any name can be used).
 * </p>
 */
public final class BetaMemoryToolExample {
    /**
     * <p>
     * A simple demonstration of a filesystem-backed {@link BetaMemoryToolHandler}.
     * </p>
     * <ul>
     *   <li>Accepts input paths starting with the logical prefix {@code "/memories"} which maps to
     *   the provided sandbox root directory.</li>
     *   <li>The sandbox root directory may have any name on disk; the memory tool command path
     *   {@code "/memories"} is treated as its alias.</li>
     *   <li>Prevents path traversal: resolved paths are normalized and must remain within the
     *   sandbox, even symbolic links.</li>
     *   <li>Uses UTF-8 for file contents.</li>
     *   <li>Line numbers are 1-based.</li>
     * </ul>
     * <p>
     * This demonstration implementation does not limit file sizes or remove old files that are no
     * longer in use, and it reads files into memory, which assumes that the files are not too large
     * to fit. It is unlikely to be robust or performant enough for production use. However, it
     * demonstrates the format of the successful responses and error messages that other
     * implementations should follow.
     * </p>
     */
    public static class FileSystemMemoryToolHandler implements BetaMemoryToolHandler {

        private static final String ALIAS_PREFIX = "/memories";
        private final Path sandboxRoot;

        /**
         * @param sandboxRoot The filesystem directory where data will be stored. This corresponds
         *   to the memory tool path {@code /memories}, but it does not need to use that name.
         */
        public FileSystemMemoryToolHandler(Path sandboxRoot) {
            this.sandboxRoot = sandboxRoot;
            if (Files.exists(this.sandboxRoot)) {
                if (!Files.isDirectory(this.sandboxRoot)) {
                    throw new IllegalArgumentException(
                            "The provided sandbox root exists but is not a directory: " + sandboxRoot.toAbsolutePath());
                }
            } else {
                try {
                    Files.createDirectories(this.sandboxRoot);
                } catch (IOException e) {
                    throw new UncheckedIOException("Could not create sandbox root", e);
                }
            }
        }

        /**
         * Resolves and validates paths. Prevents directory traversal to stay within the sandbox
         * by resolving absolute real paths and verifying the sandbox prefix.
         *
         * @param memoryToolPath The memory tool path starting with {@code "/memories"}.
         * @return The {@code Path} within the sandbox that corresponds to the given memory tool
         *   path, or {@code null} if the path is invalid or outside the sandbox.
         */
        private Path resolvePath(String memoryToolPath) {
            if (!memoryToolPath.startsWith(ALIAS_PREFIX)) {
                return null;
            }

            if (!memoryToolPath.equals(ALIAS_PREFIX) && !memoryToolPath.startsWith(ALIAS_PREFIX + "/")) {
                return null;
            }

            String relativePath = memoryToolPath.substring(ALIAS_PREFIX.length());
            if (relativePath.startsWith("/")) {
                relativePath = relativePath.substring(1);
            }

            try {
                // 1. Get the absolute, normalized path of the sandbox root
                Path normalizedRoot = sandboxRoot.toAbsolutePath().normalize();

                // 2. Resolve the requested path
                Path resolvedPath = normalizedRoot.resolve(relativePath).normalize();

                // 3. Validation: If the file exists, check its real physical path. This stops
                // symlink-based escapes.
                if (Files.exists(resolvedPath)) {
                    Path realPath = resolvedPath.toRealPath();
                    if (!realPath.startsWith(normalizedRoot)) {
                        return null;
                    }
                    return realPath;
                }

                // 4. If the file doesn't exist yet (for `create`), verify the parent exists and is
                // within the sandbox.
                if (!resolvedPath.startsWith(normalizedRoot)) {
                    return null;
                }

                return resolvedPath;

            } catch (IOException e) {
                // If we can't resolve the real path (e.g. permission issues), treat as invalid
                return null;
            }
        }

        /**
         * Formats the size of a file in bytes to a more human-readable form.
         *
         * @param nBytes The size in bytes.
         * @return A formatted string with human-readable units (e.g., {@code "1.5k"})
         */
        private String formatSize(long nBytes) {
            if (nBytes < 1_000) {
                return String.valueOf(nBytes);
            }

            String suffixes = "kMGTPE";
            int exp = (int) (Math.log(nBytes) / Math.log(1_000));
            int suffixIdx = Math.min(exp - 1, suffixes.length() - 1);

            return String.format("%.1f%c", nBytes / Math.pow(1_000, exp), suffixes.charAt(suffixIdx));
        }

        @Override
        public String create(String path, String fileText) {
            Path file = resolvePath(path);

            if (file == null) {
                throw new IllegalArgumentException("Invalid path. All paths must start with " + ALIAS_PREFIX);
            }
            if (Files.exists(file)) {
                throw new IllegalArgumentException("File " + path + " already exists");
            }

            try {
                Path parent = file.getParent();

                if (parent != null) {
                    Files.createDirectories(parent);
                }
                Files.write(file, fileText.getBytes(UTF_8));

                return "File created successfully at: " + path;
            } catch (IOException e) {
                throw new UncheckedIOException("Could not create file at " + path, e);
            }
        }

        @Override
        public String view(String path, Optional<List<Long>> viewRange) {
            Path file = resolvePath(path);

            if (file == null || !Files.exists(file)) {
                throw new IllegalArgumentException(
                        "The path " + path + " does not exist. Please provide a valid path.");
            }

            if (Files.isDirectory(file)) {
                StringBuilder builder = new StringBuilder("Contents of directory " + path + ":\n");
                // Use try-with-resources to prevent "Too many open files" error
                try (java.util.stream.Stream<Path> stream = Files.list(file)) {
                    List<Path> files = stream.collect(Collectors.toList());
                    files.sort(Comparator.comparing(p -> p.getFileName().toString()));

                    for (Path f : files) {
                        String sizeInfo = Files.isDirectory(f) ? "DIR" : formatSize(Files.size(f));
                        builder.append(String.format(
                                "%-10s\t%s\n", sizeInfo, f.getFileName().toString()));
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException("Could not list directory " + path, e);
                }

                return builder.toString().trim();
            } else {
                try {
                    // Read lines as UTF-8.
                    List<String> lines = Files.readAllLines(file, UTF_8);

                    if (lines.size() > 999_999) {
                        throw new IllegalArgumentException(
                                "File " + path + " exceeds maximum line limit of 999,999 lines.");
                    }

                    StringBuilder builder =
                            new StringBuilder("Here's the content of " + path + " with line numbers:\n");

                    if (viewRange.isEmpty() || viewRange.get().isEmpty()) {
                        for (int i = 0; i < lines.size(); i++) {
                            builder.append(String.format("%6d\t%s\n", i + 1, lines.get(i)));
                        }
                    } else {
                        List<Long> range = viewRange.get();
                        int start = Math.max(1, range.get(0).intValue());
                        int end = range.size() > 1 ? range.get(1).intValue() : start;
                        if (end == -1) {
                            end = lines.size();
                        }
                        end = Math.min(lines.size(), end);
                        for (int i = start; i <= end; i++) {
                            builder.append(String.format("%6d\t%s\n", i, lines.get(i - 1)));
                        }
                    }

                    return builder.toString().trim();
                } catch (IOException e) {
                    throw new UncheckedIOException("Could not read file at " + path, e);
                }
            }
        }

        @Override
        public String strReplace(String path, String oldStr, String newStr) {
            Path file = resolvePath(path);

            if (file == null || !Files.exists(file) || Files.isDirectory(file)) {
                throw new IllegalArgumentException(
                        "The path " + path + " does not exist. Please provide a valid path.");
            }

            try {
                String content = Files.readString(file, UTF_8);
                List<Integer> occurrences = new ArrayList<>();

                int index = content.indexOf(oldStr);
                while (index != -1) {
                    int lineNo = (int) content.substring(0, index)
                                    .chars()
                                    .filter(ch -> ch == '\n')
                                    .count()
                            + 1;
                    occurrences.add(lineNo);
                    index = content.indexOf(oldStr, index + oldStr.length());
                }

                if (occurrences.isEmpty()) {
                    throw new IllegalArgumentException("No replacement was performed, old_str `" + oldStr
                            + "` did not appear verbatim in " + path + ".");
                }

                if (occurrences.size() > 1) {
                    String lineList = occurrences.stream().map(Object::toString).collect(Collectors.joining(", "));

                    throw new IllegalArgumentException("No replacement was performed. Multiple occurrences of old_str `"
                            + oldStr + "` in lines: " + lineList + ". Please ensure it is unique");
                }

                String newContent = content.replace(oldStr, newStr);
                Files.writeString(file, newContent, UTF_8);

                int lineNo = occurrences.get(0);
                List<Long> range = List.of((long) lineNo, (long) (lineNo + 5));
                String snippet = view(path, Optional.of(range));

                return "The memory file has been edited.\n" + snippet;
            } catch (IOException e) {
                throw new UncheckedIOException("File operation failed for " + path, e);
            }
        }

        @Override
        public String insert(String path, long insertLine, String insertText) {
            Path file = resolvePath(path);

            if (file == null || !Files.exists(file) || Files.isDirectory(file)) {
                throw new IllegalArgumentException("The path " + path + " does not exist");
            }

            try {
                List<String> lines = new ArrayList<>(Files.readAllLines(file, UTF_8));
                long nLines = lines.size();

                if (insertLine < 0 || insertLine > nLines) {
                    throw new IllegalArgumentException(String.format(
                            "Invalid `insert_line` parameter: %d. It should be within the range of lines of the"
                                    + " file: [0, %d]",
                            insertLine, nLines));
                }

                lines.add((int) insertLine, insertText);
                Files.write(file, String.join("\n", lines).getBytes(UTF_8));

                return "The file " + path + " has been edited.";
            } catch (IOException e) {
                throw new UncheckedIOException("Could not edit file at " + path, e);
            }
        }

        @Override
        public String delete(String path) {
            Path file = resolvePath(path);

            if (file == null || !Files.exists(file)) {
                throw new IllegalArgumentException("The path " + path + " does not exist");
            }

            try {
                deleteRecursively(file);
                return "Successfully deleted " + path;
            } catch (IOException e) {
                throw new UncheckedIOException("Could not delete " + path, e);
            }
        }

        private void deleteRecursively(Path path) throws IOException {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                    if (e != null) {
                        throw e;
                    }
                    // Delete the directory after ("post") deleting files within the directory.
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        @Override
        public String rename(String oldPath, String newPath) {
            Path oldFile = resolvePath(oldPath);
            Path newFile = resolvePath(newPath);

            if (oldFile == null || !Files.exists(oldFile)) {
                throw new IllegalArgumentException("The path " + oldPath + " does not exist");
            }
            if (newFile == null) {
                throw new IllegalArgumentException("Invalid destination path");
            }
            if (Files.exists(newFile)) {
                throw new IllegalArgumentException("The destination " + newPath + " already exists");
            }

            try {
                Path parent = newFile.getParent();
                if (parent != null) Files.createDirectories(parent);
                Files.move(oldFile, newFile);
                return "Successfully renamed " + oldPath + " to " + newPath;
            } catch (IOException e) {
                throw new UncheckedIOException("Could not rename " + oldPath, e);
            }
        }
    }

    private BetaMemoryToolExample() {}

    public static void main(String[] args) {
        System.out.println("---- Conversation 1 ----");
        converse("Remember that my favorite color is deep purple.");
        System.out.println("---- Conversation 2 ----");
        converse("What is my favorite color?");
    }

    private static void converse(String userMessage) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // Adding a memory tool tells Claude that you will support the handling of memory tool
        // commands in the model's responses.
        MessageCreateParams createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_5)
                .addTool(BetaMemoryTool20250818.builder().build())
                .maxTokens(2048)
                .addUserMessage(userMessage)
                .build();

        // Use `ToolRunnerCreateParams` to associate the `MessageCreateParams` with the instance of
        // the memory tool handler to be invoked to handle memory tools commands in the responses.
        ToolRunnerCreateParams toolRunnerParams = ToolRunnerCreateParams.builder()
                .betaMemoryToolHandler(
                        new FileSystemMemoryToolHandler(Path.of(System.getProperty("user.home"), "memories_example")))
                .initialMessageParams(createParams)
                .maxIterations(50)
                .build();

        BetaToolRunner toolRunner = client.beta().messages().toolRunner(toolRunnerParams);

        for (BetaMessage message : toolRunner) {
            System.out.println(message);
        }
    }
}
