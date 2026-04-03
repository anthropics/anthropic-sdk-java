#!/usr/bin/env python3
"""Remove java.util.Optional from model files, replacing with nullable Kotlin types."""
import os, re, sys

MODEL_DIR = os.path.join(os.path.dirname(__file__), '..', 'anthropic-java-core', 'src', 'main', 'kotlin', 'com', 'anthropic', 'models')

def transform_file(path):
    with open(path) as f:
        content = f.read()
    original = content

    # Remove Optional imports
    content = re.sub(r'^import java\.util\.Optional\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import kotlin\.jvm\.optionals\.getOrNull\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import com\.anthropic\.core\.getOptional\n', '', content, flags=re.MULTILINE)

    # Pattern: fun foo(): Optional<Type> = field.getOptional("name")
    content = re.sub(
        r'fun (\w+)\(\): Optional<([^>]+)> = (\w+)\.getOptional\("([^"]+)"\)',
        r'fun \1(): \2? = \3.getNullable("\4")',
        content
    )

    # Pattern: fun foo(): Optional<Type> = field.value.getOptional("name")
    content = re.sub(
        r'fun (\w+)\(\): Optional<([^>]+)> = (\w+)\.value\.getOptional\("([^"]+)"\)',
        r'fun \1(): \2? = \3.value.getNullable("\4")',
        content
    )

    # Pattern: fun foo(): Optional<Type> = body.foo()  (delegating)
    # These return what body.foo() returns. If body.foo() was converted to T?, this is now T?
    content = re.sub(
        r'fun (\w+)\(\): Optional<([^>]+)> = body\.(\w+)\(\)',
        r'fun \1(): \2? = body.\3()',
        content
    )

    # Pattern: fun foo(): Optional<Type> = body().foo()  (delegating via method)
    content = re.sub(
        r'fun (\w+)\(\): Optional<([^>]+)> = body\(\)\.(\w+)\(\)',
        r'fun \1(): \2? = body().\3()',
        content
    )

    # Builder pattern: fun foo(foo: Optional<Type>) = foo(foo.orElse(null))
    # Remove these Optional overload methods entirely (including preceding Alias comment)
    content = re.sub(
        r'\n\s*/\*\* Alias for calling \[Builder\.\w+\].*?\*/\n\s*fun \w+\(\w+: Optional<[^>]*>\).*?\n',
        '\n',
        content, flags=re.DOTALL
    )
    # Simpler pattern without doc comment
    content = re.sub(
        r'\n\s*fun (\w+)\(\1: Optional<[^>]*>\)\s*=\s*\1\(\1\.(orElse|getOrNull)\([^)]*\)\)\s*\n',
        '\n',
        content
    )

    # Pattern: Optional.ofNullable(x) → x (in builder methods)
    content = re.sub(r'Optional\.ofNullable\((\w+)\)', r'\1', content)

    # Pattern: Optional.empty() → null
    content = re.sub(r'Optional\.empty\(\)', 'null', content)

    # Pattern: Optional.of(x) → x
    content = re.sub(r'Optional\.of\((\w+)\)', r'\1', content)

    # Pattern: .orElse(null) → (nothing, already nullable)
    content = re.sub(r'\.orElse\(null\)', '', content)

    # Pattern: .ifPresent { consumer } → ?.let { consumer }
    content = re.sub(r'\.ifPresent\s*\{', '?.let {', content)

    # Pattern: .isPresent → != null  (already done in previous batch)

    # Pattern: .map { transform } → ?.let { transform }
    content = re.sub(r'\.map\s*\{\s*(\w+)\s*->', r'?.let { \1 ->', content)

    # Pattern: .flatMap { transform } → ?.let { transform }
    content = re.sub(r'\.flatMap\s*\{\s*(\w+)\s*->', r'?.let { \1 ->', content)

    # Clean up any remaining bare Optional references we can handle
    # fun foo(): Optional<Type> = ... anything else ...
    # Convert return type but leave the body
    content = re.sub(
        r'fun (\w+)\(\): Optional<([^>]+)>(\s*=)',
        r'fun \1(): \2?\3',
        content
    )

    # Builder method: fun foo(foo: Optional<Type>) = ...
    # These should already be removed above, but catch stragglers
    content = re.sub(
        r'\n\s*fun (\w+)\(\1: Optional<[^>]*>\)[^\n]*\n',
        '\n',
        content
    )

    if content != original:
        with open(path, 'w') as f:
            f.write(content)
        return True
    return False

def main():
    model_dir = os.path.abspath(MODEL_DIR)
    changed = 0
    total = 0
    for root, dirs, files in os.walk(model_dir):
        for fname in files:
            if fname.endswith('.kt'):
                total += 1
                if transform_file(os.path.join(root, fname)):
                    changed += 1
    print(f"Transformed {changed}/{total} model files")

if __name__ == '__main__':
    main()
