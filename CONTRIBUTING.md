## Contributing to documentation

The documentation for this SDK lives at [docs.anthropic.com/en/api/sdks/java](https://docs.anthropic.com/en/api/sdks/java). To suggest changes, hover over any paragraph on the docs site and click the pencil icon that appears to the right. You can then collaborate with Claude on an edit that we'll review and publish.

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

## Linting and formatting

This repository uses [ktfmt](https://github.com/facebook/ktfmt) for Kotlin and [palantir-java-format](https://github.com/palantir/palantir-java-format) for Java.

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
