# anthropic-java-detekt-rules

Custom detekt rules for the Java SDK house style: the "Kotlin source, Java callers" interop conventions that built-in detekt rules can't express.

## Iterating on a rule

```bash
./gradlew :anthropic-java-detekt-rules:test   # unit tests
./scripts/detekt-baseline                     # regenerate config/detekt/baseline-*.xml
./scripts/lint                                # ktfmt + detektMain + detektTest
```

After editing rule code, run `./scripts/detekt-baseline` to regenerate violations.

## Adding a rule

1. `src/main/kotlin/com/anthropic/detekt/YourRule.kt` — the rule class
2. `AnthropicRuleSetProvider.kt` — register it (alphabetical)
3. `src/main/resources/config/config.yml` — default `active: true` entry (alphabetical)
4. `config/detekt/detekt.yml` — enable it under `anthropic:` with any `excludes`
5. `AnthropicRulesTest.kt` — a test (use `compileAndLintWithContext(env, ...)` if `@RequiresTypeResolution`)

Then `./scripts/detekt-baseline` and commit the updated baselines.

## Suppressing a finding

- `@Suppress("RuleId")` at the declaration — for permanent, intentional exemptions (e.g. a reflection contract). Keeps the reason next to the code.
- Baseline (`config/detekt/baseline-*.xml`) — for pre-existing violations to fix later. Regenerated, not hand-edited.
