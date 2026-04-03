#!/usr/bin/env python3
"""
Remove ALL java.util.Optional usage from the codebase, replacing with nullable T?.

Targets:
- anthropic-java-core/src/commonMain/**/*.kt
- anthropic-java-core/src/test/**/*.kt
- anthropic-java-client-okhttp/src/main/**/*.kt
- anthropic-java-aws/src/main/**/*.kt
"""

import os
import re
import glob

BASE = "/home/user/anthropic-sdk-java"

def find_kt_files():
    patterns = [
        os.path.join(BASE, "anthropic-java-core/src/commonMain/**/*.kt"),
        os.path.join(BASE, "anthropic-java-core/src/test/**/*.kt"),
        os.path.join(BASE, "anthropic-java-client-okhttp/src/main/**/*.kt"),
        os.path.join(BASE, "anthropic-java-aws/src/main/**/*.kt"),
    ]
    files = set()
    for p in patterns:
        files.update(glob.glob(p, recursive=True))
    return sorted(files)


def replace_optional_type(match_text):
    """Given text containing `Optional<SomeType>`, return with `SomeType?`.
    Handles nested generics by counting angle brackets."""
    prefix = "Optional<"
    if prefix not in match_text:
        return match_text

    idx = match_text.index(prefix)
    before = match_text[:idx]
    rest = match_text[idx + len(prefix):]

    depth = 1
    i = 0
    while i < len(rest) and depth > 0:
        if rest[i] == '<':
            depth += 1
        elif rest[i] == '>':
            depth -= 1
        i += 1

    if depth != 0:
        return match_text

    inner = rest[:i - 1]
    after = rest[i:]
    inner = inner.replace("@UnsafeVariance ", "")
    return before + inner + "?" + after


def replace_optional_types_in_line(line):
    while "Optional<" in line:
        new_line = replace_optional_type(line)
        if new_line == line:
            break
        line = new_line
    return line


def is_optional_overload_line(stripped):
    """Check if a line is an Optional overload that delegates to another method.
    Patterns:
    - fun foo(foo: Optional<Type>) = foo(foo.getOrNull())
    - fun foo(foo: Optional<Type>) = foo(foo.orElse(null))
    - fun foo(foo: Optional<Type>) = apply { ... foo.getOrNull() ... }
    - fun foo(foo: Optional<Type>) = apply { ... foo.orElse(null) ... }
    """
    if 'Optional<' not in stripped:
        return False
    if not stripped.startswith('fun '):
        return False
    if '.getOrNull()' in stripped or '.orElse(null)' in stripped:
        return True
    return False


def delete_optional_overloads(content):
    """Delete builder Optional overloads and their doc comments."""
    lines = content.split('\n')
    new_lines = []
    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()

        # Single-line: /** Alias... */ on prev line, fun with Optional on current
        if is_optional_overload_line(stripped):
            # Remove preceding doc comment if it's an Alias comment
            while new_lines and new_lines[-1].strip() in ('', ):
                new_lines.pop()
            if new_lines and ('Alias for' in new_lines[-1] or '/** Alias' in new_lines[-1]):
                new_lines.pop()
            # Remove trailing blank lines we may have just exposed
            while new_lines and new_lines[-1].strip() == '':
                pass  # keep them for now
                break
            i += 1
            # Skip trailing blank line
            if i < len(lines) and lines[i].strip() == '':
                i += 1
            continue

        # Multi-line alias comment + function:
        # /** Alias for calling [Builder.foo] with `foo.orElse(null)`. */
        # fun foo(foo: Optional<Type>) =
        #     foo(foo.getOrNull())
        if stripped.startswith('/** Alias for') and 'orElse(null)' in stripped:
            if i + 1 < len(lines) and 'Optional<' in lines[i + 1]:
                next_stripped = lines[i + 1].strip()
                if next_stripped.endswith('='):
                    # Body is on the line after
                    if i + 2 < len(lines) and ('.getOrNull()' in lines[i + 2] or '.orElse(null)' in lines[i + 2]):
                        i += 3
                        if i < len(lines) and lines[i].strip() == '':
                            i += 1
                        continue
                elif '.getOrNull()' in next_stripped or '.orElse(null)' in next_stripped:
                    i += 2
                    if i < len(lines) and lines[i].strip() == '':
                        i += 1
                    continue

        # Multi-line function with Optional param that spans lines
        # fun foo(foo: Optional<Type>) =
        #     foo(foo.getOrNull())
        if 'Optional<' in stripped and stripped.startswith('fun ') and stripped.endswith('='):
            if i + 1 < len(lines) and ('.getOrNull()' in lines[i + 1] or '.orElse(null)' in lines[i + 1]):
                # Check for preceding alias comment
                while new_lines and new_lines[-1].strip() == '':
                    new_lines.pop()
                if new_lines and ('Alias for' in new_lines[-1] or '/** Alias' in new_lines[-1]):
                    new_lines.pop()
                i += 2
                if i < len(lines) and lines[i].strip() == '':
                    i += 1
                continue

        # Multi-line: fun foo(\n    foo: Optional<Type>,\n) = ...
        # Let's also handle apply { ... } multi-line
        if 'Optional<' in stripped and stripped.startswith('fun ') and '= apply {' in stripped:
            if '.getOrNull()' in stripped or '.orElse(null)' in stripped:
                # Single line apply
                while new_lines and new_lines[-1].strip() == '':
                    new_lines.pop()
                if new_lines and ('Alias for' in new_lines[-1] or '/** Alias' in new_lines[-1]):
                    new_lines.pop()
                i += 1
                if i < len(lines) and lines[i].strip() == '':
                    i += 1
                continue

        # Multi-line apply block:
        # fun foo(foo: Optional<Type>) = apply {
        #     ...getOrNull()...
        # }
        if 'Optional<' in stripped and stripped.startswith('fun ') and stripped.endswith('= apply {'):
            # Look ahead for getOrNull/orElse in the block
            j = i + 1
            brace_depth = 1
            block_lines = [line]
            has_optional_unwrap = False
            while j < len(lines) and brace_depth > 0:
                block_lines.append(lines[j])
                brace_depth += lines[j].count('{') - lines[j].count('}')
                if '.getOrNull()' in lines[j] or '.orElse(null)' in lines[j]:
                    has_optional_unwrap = True
                j += 1

            if has_optional_unwrap:
                while new_lines and new_lines[-1].strip() == '':
                    new_lines.pop()
                if new_lines and ('Alias for' in new_lines[-1] or '/** Alias' in new_lines[-1]):
                    new_lines.pop()
                i = j
                if i < len(lines) and lines[i].strip() == '':
                    i += 1
                continue

        new_lines.append(line)
        i += 1

    return '\n'.join(new_lines)


def remove_duplicate_methods(content):
    """After Optional->nullable transformation, some methods now have identical
    signatures (the original nullable overload and the transformed Optional overload).
    Remove the second occurrence."""
    lines = content.split('\n')
    new_lines = []
    seen_sigs = {}  # signature -> line number

    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()

        # Match fun declarations
        sig_match = re.match(r'\s+fun (\w+\([^)]*\))', line)
        if sig_match:
            sig = sig_match.group(1)
            # Normalize whitespace in signature for comparison
            norm_sig = re.sub(r'\s+', ' ', sig)

            if norm_sig in seen_sigs:
                # This is a duplicate - find where this method ends
                j = i
                brace_depth = line.count('{') - line.count('}')
                j += 1
                while j < len(lines) and brace_depth > 0:
                    brace_depth += lines[j].count('{') - lines[j].count('}')
                    j += 1

                # Also remove the doc comment above (already in new_lines)
                while new_lines and new_lines[-1].strip().startswith('/**') or (new_lines and new_lines[-1].strip().startswith('*')):
                    if new_lines[-1].strip().startswith('/**'):
                        new_lines.pop()
                        break
                    new_lines.pop()
                # Remove blank lines we may have exposed
                while new_lines and new_lines[-1].strip() == '' and len(new_lines) > 1:
                    if len(new_lines) >= 2 and new_lines[-2].strip() == '':
                        new_lines.pop()
                    else:
                        break

                i = j
                if i < len(lines) and lines[i].strip() == '':
                    i += 1
                continue
            else:
                seen_sigs[norm_sig] = i

        new_lines.append(line)
        i += 1

    return '\n'.join(new_lines)


def transform_source(content, filepath):
    is_test = '/src/test/' in filepath

    # Step 1: Delete alias Optional overloads (before type replacement)
    content = delete_optional_overloads(content)

    # Step 2: Remove imports
    content = re.sub(r'^import java\.util\.Optional\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import kotlin\.jvm\.optionals\.getOrNull\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import kotlin\.jvm\.optionals\.asSequence\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import kotlin\.jvm\.optionals\.getOrElse\n', '', content, flags=re.MULTILINE)
    content = re.sub(r'^import com\.anthropic\.core\.getOptional\n', '', content, flags=re.MULTILINE)

    # Step 3: Line-by-line transformations
    lines = content.split('\n')
    new_lines = []
    for line in lines:
        # Replace Optional<T> types
        line = replace_optional_types_in_line(line)

        # Replace .getOptional("name") with .getNullable("name")
        line = line.replace('.getOptional(', '.getNullable(')

        # Replace Optional.ofNullable(x) -> x
        line = re.sub(r'Optional\.ofNullable\(([^)]+)\)', r'\1', line)

        # Replace Optional.of(x) -> x
        line = re.sub(r'Optional\.of\(([^)]+)\)', r'\1', line)

        # Replace Optional.empty() -> null
        line = line.replace('Optional.empty()', 'null')

        # Replace .orElse(null) -> remove
        line = line.replace('.orElse(null)', '')

        # Replace .orElse(defaultVal) -> ?: defaultVal
        line = re.sub(r'\.orElse\(([^)]+)\)', r' ?: \1', line)

        # Replace .orElseThrow { Exception() } -> ?: throw Exception()
        line = re.sub(r'\.orElseThrow\s*\{\s*(.+?)\s*\}', r' ?: throw \1', line)

        # Replace .isPresent -> != null
        line = re.sub(r'\.isPresent\b', ' != null', line)

        # Replace .get() at end of chain -> !! (for Optional.get())
        # But be careful not to replace .get() on maps/etc
        # We'll handle specific known cases

        # Replace .getOrNull() -> remove (already nullable)
        line = line.replace('.getOrNull()', '')

        # Replace .ifPresent { action } -> ?.let { action }
        line = re.sub(r'\.ifPresent\s*\{', '?.let {', line)

        # Replace .filter { pred } -> ?.takeIf { pred }
        line = re.sub(r'\.filter\s*\{', '?.takeIf {', line)

        if is_test:
            # .hasValue(x) -> .isEqualTo(x)
            line = re.sub(r'\.hasValue\(', '.isEqualTo(', line)
            # assertThat(x.method()).contains(value) on Optional -> assertThat(x).isEqualTo(y)
            line = re.sub(r'(assertThat\([^)]+\(\)\))\.contains\(', r'\1.isEqualTo(', line)

        new_lines.append(line)

    content = '\n'.join(new_lines)

    # Handle test .isEmpty for Optional (property without parens at end of line)
    if is_test:
        # assertThat(...).isEmpty  at end of line (no parens) = Optional property
        content = re.sub(
            r'(assertThat\(.+\))\.isEmpty\s*$',
            r'\1.isNull()',
            content,
            flags=re.MULTILINE
        )

    # Step 4: Remove duplicate methods
    content = remove_duplicate_methods(content)

    return content


def transform_utils(content):
    """Remove getOptional function from Utils.kt."""
    content = re.sub(
        r'\nfun <T : Any> JsonField<T>\.getOptional\(name: String\):.*\n.*\n',
        '\n',
        content
    )
    return content


def main():
    files = find_kt_files()
    print(f"Found {len(files)} .kt files to process")

    modified = 0
    for filepath in files:
        with open(filepath, 'r') as f:
            original = f.read()

        content = original

        if filepath.endswith('/core/Utils.kt'):
            content = transform_utils(content)

        content = transform_source(content, filepath)

        if content != original:
            with open(filepath, 'w') as f:
                f.write(content)
            modified += 1

    print(f"Modified {modified} files")

    # Verify
    import subprocess
    result = subprocess.run(
        ['grep', '-rl', 'java.util.Optional', os.path.join(BASE, 'anthropic-java-core/src/commonMain/')],
        capture_output=True, text=True
    )
    remaining = result.stdout.strip().split('\n') if result.stdout.strip() else []
    print(f"Files still containing java.util.Optional in commonMain: {len(remaining)}")
    for f in remaining[:10]:
        print(f"  {f}")

    # Check for remaining getOptional import
    result2 = subprocess.run(
        ['grep', '-rl', 'import com.anthropic.core.getOptional', os.path.join(BASE, 'anthropic-java-core/')],
        capture_output=True, text=True
    )
    remaining2 = result2.stdout.strip().split('\n') if result2.stdout.strip() else []
    print(f"Files still importing getOptional: {len(remaining2)}")


if __name__ == '__main__':
    main()
