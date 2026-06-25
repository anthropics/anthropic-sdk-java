## Contributing to documentation

The documentation for this SDK lives at [platform.claude.com/docs/en/api/sdks/java](https://platform.claude.com/docs/en/api/sdks/java). To suggest changes, open an issue.

## Setting up the environment

This repository uses Gradle as its build tool.

To set up the repository, run:

```sh
$ ./gradlew build
```

This will install all the required dependencies and build the project.

## Modifying/Adding code

Most of the SDK is generated code. Modifications to code will be persisted between generations, but may
result in merge conflicts between manual patches and changes from the generator. The generator will never
modify the contents of the `anthropic-java-example/` directory.

## Adding and running examples

All files in the `anthropic-java-example/` directory are not modified by the generator and can be freely edited or added to.

```sh
# Run an example
$ ./gradlew :anthropic-java-example:run
```

## Running tests

Most tests require you to [set up a mock server](https://github.com/stoplightio/prism) against the OpenAPI spec to run the tests.

```sh
$ npx prism mock path/to/your/openapi.yml
```

```sh
$ ./scripts/test
```

The `anthropic-java-ecosystem-test` module verifies that the SDK still works for downstream
consumers: it runs ProGuard/R8 shrinking checks, executes a Java consumer on a real Java 8 runtime,
and compiles and runs a Kotlin consumer with the Kotlin 1.8.20 compiler. The Java 8 and Kotlin
checks select a JDK 8 via Gradle toolchains. Locally these checks are skipped if no JDK 8 is
installed, so `./gradlew test` still works without one; install any JDK 8 (for example, Temurin 8)
to run them locally. CI always provisions a JDK 8 and fails if it is missing, so the checks can
never be silently skipped there.

## Dependency lockfiles

Every module (and `buildSrc`) pins its resolved dependency graph to a checked-in `gradle.lockfile`.
Locking runs in strict mode, so the build fails if a configuration is resolved without matching
lock state — for example, after adding a dependency or bumping a version in
`gradle/libs.versions.toml`.

To regenerate the lockfiles after changing dependencies:

```sh
$ ./scripts/lock
```

Commit the updated `gradle.lockfile`s alongside the dependency change.

## Linting and formatting

This repository uses [ktfmt](https://github.com/facebook/ktfmt) for Kotlin formatting, [palantir-java-format](https://github.com/palantir/palantir-java-format) for Java formatting, and [detekt](https://detekt.dev) for static analysis. The detekt configuration is an allowlist at [`config/detekt/detekt.yml`](config/detekt/detekt.yml) plus custom Java-interop rules in [`anthropic-java-detekt-rules`](anthropic-java-detekt-rules); pre-existing findings are baselined per module under `config/detekt/`.

To lint:

```sh
$ ./scripts/lint
```

To format:

```sh
$ ./scripts/format
```

## Publishing and releases

Changes made to this repository via the automated release PR pipeline should publish to Maven Central automatically. If
the changes aren't made through the automated pipeline, you may want to make releases manually.
