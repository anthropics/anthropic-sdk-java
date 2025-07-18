# Changelog

## 2.2.0 (2025-07-18)

Full Changelog: [v2.1.0...v2.2.0](https://github.com/anthropics/anthropic-sdk-java/compare/v2.1.0...v2.2.0)

### Features

* **api:** add support for Search Result Content Blocks ([c36527b](https://github.com/anthropics/anthropic-sdk-java/commit/c36527b940b14dcf0e7b54d5dd042725a7e467e1))
* **client:** add a `withOptions` method ([27a82a5](https://github.com/anthropics/anthropic-sdk-java/commit/27a82a51cd4841ddfeed831649fb9266ad2dafbc))
* **client:** implement per-endpoint base URL support ([4e8302c](https://github.com/anthropics/anthropic-sdk-java/commit/4e8302c8362e02c4e50508fba63870a9c38e520a))
* **vertex:** support global region endpoint ([44119cb](https://github.com/anthropics/anthropic-sdk-java/commit/44119cbbf8047ef0f4c450592cfd329a6d2fc9ba))


### Bug Fixes

* **ci:** release-doctor — report correct token name ([6c97d93](https://github.com/anthropics/anthropic-sdk-java/commit/6c97d93df2dd7b9d0616e727fdcd704b134e34ff))
* **client:** bump max requests per host to max requests (5 -&gt; 64) ([f2abb48](https://github.com/anthropics/anthropic-sdk-java/commit/f2abb48aee3ce30911e9311ea7fdaea56d6adf44))
* **client:** don't close client on `withOptions` usage when original is gc'd ([76af310](https://github.com/anthropics/anthropic-sdk-java/commit/76af310809c0fff30be805241a08fc6443c46148))
* **client:** ensure error handling always occurs ([9a56f4f](https://github.com/anthropics/anthropic-sdk-java/commit/9a56f4f2cf3944d945e0d7e4e52e1576d5dda148))
* compilation errors ([8ad3163](https://github.com/anthropics/anthropic-sdk-java/commit/8ad31632d2f9f07c70edf6232a6c03d17d0265f5))


### Chores

* **api:** mark claude-3-opus-20240229 as deprecated ([cfa37e8](https://github.com/anthropics/anthropic-sdk-java/commit/cfa37e82a3ddc24890efe835c44b55a0aeccbfa4))
* **api:** update BetaCitationSearchResultLocation ([3bdbf82](https://github.com/anthropics/anthropic-sdk-java/commit/3bdbf821f3bae852952cc96dfd80585a5c97818d))
* **ci:** bump `actions/setup-java` to v4 ([ab43021](https://github.com/anthropics/anthropic-sdk-java/commit/ab43021a2b1e306f90d687f5ffe25e23d352b045))
* **ci:** enable for pull requests ([9e47921](https://github.com/anthropics/anthropic-sdk-java/commit/9e479210fa6d69829e431134f3e35c86bc2130f9))
* **ci:** ensure docs generation always succeeds ([8dbfb13](https://github.com/anthropics/anthropic-sdk-java/commit/8dbfb13bb82998e7801c761d79f058e18752a702))
* **ci:** only run for pushes and fork pull requests ([82647dd](https://github.com/anthropics/anthropic-sdk-java/commit/82647dda97b2ee03ca22ceb74ba88972e0bdcdf1))
* delete bin/ ([7f2f39b](https://github.com/anthropics/anthropic-sdk-java/commit/7f2f39b856fcf7a372e8fe842c83353d477b83b6))
* **internal:** add breaking change detection ([cc6c869](https://github.com/anthropics/anthropic-sdk-java/commit/cc6c8693ae8186cdf59876f98291006a862d4bbe))
* **internal:** allow running specific example from cli ([4b19506](https://github.com/anthropics/anthropic-sdk-java/commit/4b1950630184cf95fd2266b0a9ff5e0802e8aa0a))


### Documentation

* model in examples ([24d79a1](https://github.com/anthropics/anthropic-sdk-java/commit/24d79a1bb92a46929c1924b96f479f4052b5bca2))
* update models and non-beta ([cb49393](https://github.com/anthropics/anthropic-sdk-java/commit/cb49393985980e849bae611f08f149f75908631f))


### Refactors

* **internal:** minor `ClientOptionsTest` change ([b28dade](https://github.com/anthropics/anthropic-sdk-java/commit/b28dade407979c521a10ed35f1335be500e6740c))

## 2.1.0 (2025-06-11)

Full Changelog: [v2.0.0...v2.1.0](https://github.com/anthropics/anthropic-sdk-java/compare/v2.0.0...v2.1.0)

### Features

* **api:** api update ([823032b](https://github.com/anthropics/anthropic-sdk-java/commit/823032be6cb8d06ad6a106b78672f7df0f0fdb37))
* **api:** manual updates ([6156bcb](https://github.com/anthropics/anthropic-sdk-java/commit/6156bcb1d6d21041b6e34d8bab9f5e75e7a085fa))
* **client:** add support for fine-grained-tool-streaming-2025-05-14 ([37aa2a1](https://github.com/anthropics/anthropic-sdk-java/commit/37aa2a1d82cddde6ff7f1b3e38085bf658971e1d))
* **client:** add support for new text_editor_20250429 tool ([f85d1a2](https://github.com/anthropics/anthropic-sdk-java/commit/f85d1a233b58b7c926dddb64131de7122716d169))
* **client:** more `toParam()` methods ([151e181](https://github.com/anthropics/anthropic-sdk-java/commit/151e181c1521dab983e9fb03a9bfb2430dca88f9))


### Bug Fixes

* **client:** `toParam` methods ([2632693](https://github.com/anthropics/anthropic-sdk-java/commit/26326938e574f4265c3c940fa5a85a143ee75f7f))
* **client:** deprecate BetaBase64PDFBlock in favor of BetaRequestDocumentBlock ([e83424b](https://github.com/anthropics/anthropic-sdk-java/commit/e83424bb384c33f677cccd2174d779d344cfe916))
* **client:** handle server tool use delta and extract to better type ([3d2f428](https://github.com/anthropics/anthropic-sdk-java/commit/3d2f4289a8195a9c9faf97ef48904aa769383157))
* **client:** remove `@MustBeClosed` for future returning methods ([b9b63ca](https://github.com/anthropics/anthropic-sdk-java/commit/b9b63ca942178bc390a1c8a95deea4817edb9b60))


### Chores

* **docs:** adjust MCP example to show use of beta header ([7d1758a](https://github.com/anthropics/anthropic-sdk-java/commit/7d1758a00b49c9fbe7ccbf2cc1bc294e1257a58a))
* **internal:** fix release workflows ([a5287dc](https://github.com/anthropics/anthropic-sdk-java/commit/a5287dc4ffaad0e02fed166b63e4635448666247))
* **tests:** add test for fine-grained-tool-streaming-2025-05-14 logic ([2b39131](https://github.com/anthropics/anthropic-sdk-java/commit/2b391311176cc7fbdc4325ee5777818014d10ca1))

## 2.0.0 (2025-05-22)

Full Changelog: [v1.4.0...v2.0.0](https://github.com/anthropics/anthropic-sdk-java/compare/v1.4.0...v2.0.0)

### ⚠ BREAKING CHANGES

* **client:** improve some class names
* **client:** extract auto pagination to shared classes
* **client:** **Migration:** - If you were referencing the `AutoPager` class on a specific `*Page` or `*PageAsync` type, then you should instead reference the shared `AutoPager` and `AutoPagerAsync` types, under the `core` package
    - `AutoPagerAsync` now has different usage. You can call `.subscribe(...)` on the returned object instead to get called back each page item. You can also call `onCompleteFuture()` to get a future that completes when all items have been processed. Finally, you can call `.close()` on the returned object to stop auto-paginating early
    - If you were referencing `getNextPage` or `getNextPageParams`:
       - Swap to `nextPage()` and `nextPageParams()`
       - Note that these both now return non-optional types (use `hasNextPage()` before calling these, since they will throw if it's impossible to get another page)

### Features

* **api:** add claude 4 models, files API, code execution tool, MCP connector and more ([6deae45](https://github.com/anthropics/anthropic-sdk-java/commit/6deae4569ee39707aea8750305cbc5962d90998c))
* **client:** allow providing some params positionally ([f05e741](https://github.com/anthropics/anthropic-sdk-java/commit/f05e74180f6b8ebb5e2aeaf6f4834753df3359e2))
* **client:** extract auto pagination to shared classes ([b7d354b](https://github.com/anthropics/anthropic-sdk-java/commit/b7d354b75a9f3a15918dab2df570eac8880cf84c))


### Bug Fixes

* **internal:** fix message usage accumulation ([7427fdb](https://github.com/anthropics/anthropic-sdk-java/commit/7427fdb8bb715a4471b8a8b4534dc8c6cf9e368f))
* **tests:** update MessageAccumulator tests to match implementation ([ed8ff39](https://github.com/anthropics/anthropic-sdk-java/commit/ed8ff39d2290526486a52c52852c7f346a28f925))


### Chores

* **internal:** codegen related update ([8d45948](https://github.com/anthropics/anthropic-sdk-java/commit/8d459486743063641b2413fd013cfac8e4e92449))
* **internal:** fix custom code ([88c1572](https://github.com/anthropics/anthropic-sdk-java/commit/88c1572fc92d95b60bd2e5083d79d9ab9d0644f5))
* **internal:** fix custom code ([b0985c6](https://github.com/anthropics/anthropic-sdk-java/commit/b0985c6076b602a83a496c6cef416cc019ddde14))


### Documentation

* add security warning for overriding parameters ([#504](https://github.com/anthropics/anthropic-sdk-java/issues/504)) ([0100532](https://github.com/anthropics/anthropic-sdk-java/commit/0100532f23e2630085a03c76f47db93a2f30c65e))


### Refactors

* **client:** improve some class names ([aa1f7b7](https://github.com/anthropics/anthropic-sdk-java/commit/aa1f7b795ede291665ca81600ac819a25a003843))

## 1.4.0 (2025-05-07)

Full Changelog: [v1.3.1...v1.4.0](https://github.com/anthropics/anthropic-sdk-java/compare/v1.3.1...v1.4.0)

### Features

* **api:** adds web search capabilities to the Claude API ([6a873d4](https://github.com/anthropics/anthropic-sdk-java/commit/6a873d402f513166712df7dec070649bb66c3987))


### Chores

* disable configuration cache for releases ([c12dfb7](https://github.com/anthropics/anthropic-sdk-java/commit/c12dfb7026ca022d462dc759c22f8e98fa1e280d))
* **internal:** remove flaky `-Xbackend-threads=0` option ([af5fd05](https://github.com/anthropics/anthropic-sdk-java/commit/af5fd05f87a476591db299359d9c1c16916435f2))

## 1.3.1 (2025-05-02)

Full Changelog: [v1.3.0...v1.3.1](https://github.com/anthropics/anthropic-sdk-java/compare/v1.3.0...v1.3.1)

### Chores

* **internal:** update java toolchain ([dda0371](https://github.com/anthropics/anthropic-sdk-java/commit/dda037179a0e0badd639b035a92ea97398a50d40))

## 1.3.0 (2025-04-30)

Full Changelog: [v1.2.0...v1.3.0](https://github.com/anthropics/anthropic-sdk-java/compare/v1.2.0...v1.3.0)

### Features

* **api:** manual updates ([12bfce7](https://github.com/anthropics/anthropic-sdk-java/commit/12bfce7441bf947232c4fca91f7cdc160b238dbf))
* **client:** make pagination robust to missing data ([fafa963](https://github.com/anthropics/anthropic-sdk-java/commit/fafa96367750b83c4568de88827a1e015bb991f3))
* **client:** support setting base URL via env var ([#244](https://github.com/anthropics/anthropic-sdk-java/issues/244)) ([b0a125a](https://github.com/anthropics/anthropic-sdk-java/commit/b0a125a1e6e089b42f280a321e1176b53490b6e7))


### Bug Fixes

* **client:** bump to better jackson version ([#246](https://github.com/anthropics/anthropic-sdk-java/issues/246)) ([6d17012](https://github.com/anthropics/anthropic-sdk-java/commit/6d1701215b19d365a9f96711a4215bd99f9a1c61))


### Performance Improvements

* **internal:** improve compilation+test speed ([6c86a79](https://github.com/anthropics/anthropic-sdk-java/commit/6c86a79fe843282d3330877ce61586c03de1f24a))


### Chores

* **ci:** only use depot for staging repos ([199e191](https://github.com/anthropics/anthropic-sdk-java/commit/199e191d7ddcc9e3c7cfd63ab008d610d11799a6))
* **ci:** run on more branches and use depot runners ([316271f](https://github.com/anthropics/anthropic-sdk-java/commit/316271f64cd84d222df5567ba4d0a80580b91c06))
* **internal:** codegen related update ([ed9783e](https://github.com/anthropics/anthropic-sdk-java/commit/ed9783e2d1ee40cf62df320099180eda92b959cf))
* **internal:** codegen related update ([13676d4](https://github.com/anthropics/anthropic-sdk-java/commit/13676d417fa40bd587f64761555035c23a64a03e))
* **internal:** delete flaky/unnecessary tests ([5e3d901](https://github.com/anthropics/anthropic-sdk-java/commit/5e3d9019562cdca84ecb73e1673d7894d2ffa5e6))
* **internal:** expand CI branch coverage ([#245](https://github.com/anthropics/anthropic-sdk-java/issues/245)) ([be51e0c](https://github.com/anthropics/anthropic-sdk-java/commit/be51e0c5d5b577b10387e8147bc55a56f40d7173))
* **internal:** isolate backend tests that use env ([189eebd](https://github.com/anthropics/anthropic-sdk-java/commit/189eebd9905dced1cb0e34b8322faa2d7d91785a))
* **internal:** java 17 -&gt; 21 on ci ([adbb816](https://github.com/anthropics/anthropic-sdk-java/commit/adbb81659b976b4c27fc2d96f6b789d547e7da67))
* **internal:** reduce CI branch coverage ([90e1fe1](https://github.com/anthropics/anthropic-sdk-java/commit/90e1fe1568a2a505178f01a7f7c1b6bd999df695))


### Documentation

* add comments for page methods ([fafa963](https://github.com/anthropics/anthropic-sdk-java/commit/fafa96367750b83c4568de88827a1e015bb991f3))
* **client:** update jackson compat error message ([9c4f68f](https://github.com/anthropics/anthropic-sdk-java/commit/9c4f68f2638b8a159258dcc8449ee72adb32d23e))
* explain http client customization ([c97180b](https://github.com/anthropics/anthropic-sdk-java/commit/c97180be2571a82cb4a138348258a1c8cb404857))
* explain jackson compat in readme ([21448fa](https://github.com/anthropics/anthropic-sdk-java/commit/21448fa491540a9d0ef9322032f16322ca7c5e32))
* update documentation links to be more uniform ([0022d67](https://github.com/anthropics/anthropic-sdk-java/commit/0022d67018004b631e6797934c986cabf75b7ca1))


### Refactors

* **client:** deduplicate page response classes ([#247](https://github.com/anthropics/anthropic-sdk-java/issues/247)) ([fafa963](https://github.com/anthropics/anthropic-sdk-java/commit/fafa96367750b83c4568de88827a1e015bb991f3))
* **client:** migrate pages to builder pattern ([#248](https://github.com/anthropics/anthropic-sdk-java/issues/248)) ([162fe98](https://github.com/anthropics/anthropic-sdk-java/commit/162fe9873d8274739d3c315ff2a564e99bb6cc04))

## 1.2.0 (2025-04-15)

Full Changelog: [v1.1.0...v1.2.0](https://github.com/anthropics/anthropic-sdk-java/compare/v1.1.0...v1.2.0)

### Features

* **api:** manual updates ([c787a06](https://github.com/anthropics/anthropic-sdk-java/commit/c787a06a14d941aa9ed9ed49a3ebdd8dbe4cb985))


### Bug Fixes

* **client:** handle empty tool arg JSON in accumulator [#249](https://github.com/anthropics/anthropic-sdk-java/issues/249) ([#252](https://github.com/anthropics/anthropic-sdk-java/issues/252)) ([dbb87e2](https://github.com/anthropics/anthropic-sdk-java/commit/dbb87e2f4d392c3aa455cd3a4aba2eb01c869c9e))
* **client:** return `Optional&lt;T&gt;` instead of `Optional<? extends T>` ([#237](https://github.com/anthropics/anthropic-sdk-java/issues/237)) ([a73ed0c](https://github.com/anthropics/anthropic-sdk-java/commit/a73ed0c031454b60e205120fc03c9012942c9103))
* **client:** translate streaming `IOException` into custom exception ([#233](https://github.com/anthropics/anthropic-sdk-java/issues/233)) ([8b925b9](https://github.com/anthropics/anthropic-sdk-java/commit/8b925b935b69136d717d009a37bacc94757feb43))


### Chores

* **internal:** codegen related update ([#235](https://github.com/anthropics/anthropic-sdk-java/issues/235)) ([3816ae3](https://github.com/anthropics/anthropic-sdk-java/commit/3816ae3055497217e4dccdfe5513895d30c47395))
* **internal:** swap from `getNullable` to `getOptional` ([#236](https://github.com/anthropics/anthropic-sdk-java/issues/236)) ([0b97285](https://github.com/anthropics/anthropic-sdk-java/commit/0b97285e45995a768a2188a8e6e893aa6d8d7997))


### Documentation

* add comments to `JsonField` classes ([a73ed0c](https://github.com/anthropics/anthropic-sdk-java/commit/a73ed0c031454b60e205120fc03c9012942c9103))
* document how to forcibly omit required field ([6da7748](https://github.com/anthropics/anthropic-sdk-java/commit/6da77484058c3e38ad83bef023ef7c9724fc5e30))
* swap examples used in readme ([#238](https://github.com/anthropics/anthropic-sdk-java/issues/238)) ([6da7748](https://github.com/anthropics/anthropic-sdk-java/commit/6da77484058c3e38ad83bef023ef7c9724fc5e30))

## 1.1.0 (2025-04-03)

Full Changelog: [v1.0.0...v1.1.0](https://github.com/anthropics/anthropic-sdk-java/compare/v1.0.0...v1.1.0)

### Features

* **api:** extract ContentBlockDelta events into their own schemas ([#226](https://github.com/anthropics/anthropic-sdk-java/issues/226)) ([49a83cf](https://github.com/anthropics/anthropic-sdk-java/commit/49a83cfdf734c0ea731aac552239220e2e924708))
* **api:** manual updates ([d5afb44](https://github.com/anthropics/anthropic-sdk-java/commit/d5afb441237efd1e745df09812f251221d58a240))
* **api:** manual updates ([93d6364](https://github.com/anthropics/anthropic-sdk-java/commit/93d636449c2bfcdcd3988ced2be7706a6f2da30f))
* **client:** add enum validation method ([1ce5a58](https://github.com/anthropics/anthropic-sdk-java/commit/1ce5a587331d6cc7adfb8c3f8c2935cdf17c8bd7))
* **client:** expose request body setter and getter ([#229](https://github.com/anthropics/anthropic-sdk-java/issues/229)) ([fb26c1f](https://github.com/anthropics/anthropic-sdk-java/commit/fb26c1ff8addd08277727c6047f88144fb00f2ed))
* **client:** make datetime deserialization more lenient ([#228](https://github.com/anthropics/anthropic-sdk-java/issues/228)) ([52b871d](https://github.com/anthropics/anthropic-sdk-java/commit/52b871d8c9e1240610236514e52a81c6cac69846))
* **client:** make union deserialization more robust ([#227](https://github.com/anthropics/anthropic-sdk-java/issues/227)) ([1ce5a58](https://github.com/anthropics/anthropic-sdk-java/commit/1ce5a587331d6cc7adfb8c3f8c2935cdf17c8bd7))


### Bug Fixes

* **client:** compilation errors ([c8e47fd](https://github.com/anthropics/anthropic-sdk-java/commit/c8e47fd24f0680de1ded21c6a45ac7f48c31b479))
* **client:** limit json deserialization coercion ([#223](https://github.com/anthropics/anthropic-sdk-java/issues/223)) ([a5987f6](https://github.com/anthropics/anthropic-sdk-java/commit/a5987f6de5776dec0b68a740aa4baac692a21c45))


### Performance Improvements

* **client:** cached parsed type in `HttpResponseFor` ([#230](https://github.com/anthropics/anthropic-sdk-java/issues/230)) ([377c532](https://github.com/anthropics/anthropic-sdk-java/commit/377c5323439d3ff086e15b5b29734c5c9eeb233b))


### Chores

* **client:** remove unnecessary json state from some query param classes ([1ce5a58](https://github.com/anthropics/anthropic-sdk-java/commit/1ce5a587331d6cc7adfb8c3f8c2935cdf17c8bd7))
* **internal:** add invalid json deserialization tests ([1ce5a58](https://github.com/anthropics/anthropic-sdk-java/commit/1ce5a587331d6cc7adfb8c3f8c2935cdf17c8bd7))
* **internal:** add json roundtripping tests ([1ce5a58](https://github.com/anthropics/anthropic-sdk-java/commit/1ce5a587331d6cc7adfb8c3f8c2935cdf17c8bd7))


### Documentation

* fix examples ([a4c0a53](https://github.com/anthropics/anthropic-sdk-java/commit/a4c0a53ed2b596741565f39187c0db4eb435a9c3))

## 1.0.0 (2025-03-31)

Full Changelog: [v0.9.2...v1.0.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.9.2...v1.0.0)

### Bug Fixes

* **client:** don't call `validate()` during deserialization if we don't have to ([#219](https://github.com/anthropics/anthropic-sdk-java/issues/219)) ([061bd9b](https://github.com/anthropics/anthropic-sdk-java/commit/061bd9b2d503ef32cf180c6bbaea2b9b0427c604))


### Chores

* **internal:** add back release workflow ([be2b431](https://github.com/anthropics/anthropic-sdk-java/commit/be2b431d0fcab85feb9573a163e2582c0365f9ea))
* **internal:** remove CI condition ([#218](https://github.com/anthropics/anthropic-sdk-java/issues/218)) ([e73c68c](https://github.com/anthropics/anthropic-sdk-java/commit/e73c68c8cb9261f872736903d15c38ed027cd8c7))
* **internal:** update config ([#215](https://github.com/anthropics/anthropic-sdk-java/issues/215)) ([f12e3fe](https://github.com/anthropics/anthropic-sdk-java/commit/f12e3fefd6dde0bc9e0520ebf42532b14dcd46ff))


### Documentation

* add comment on response header methods ([#220](https://github.com/anthropics/anthropic-sdk-java/issues/220)) ([9bc3401](https://github.com/anthropics/anthropic-sdk-java/commit/9bc34018f2a88fa7ba06bc2aea5b6aaeb0d26a94))
* document request IDs ([d083d5c](https://github.com/anthropics/anthropic-sdk-java/commit/d083d5c0e11aefccf850b3a278c50e6aef5199e1))
* remove beta readme note ([9d00d0f](https://github.com/anthropics/anthropic-sdk-java/commit/9d00d0f1eeec1132968f61e6d00b9d4ea69a9d99))

## 0.9.2 (2025-03-27)

Full Changelog: [v0.9.1...v0.9.2](https://github.com/anthropics/anthropic-sdk-java/compare/v0.9.1...v0.9.2)

### Bug Fixes

* **client:** deduplicate stop reason type ([d0c9ef4](https://github.com/anthropics/anthropic-sdk-java/commit/d0c9ef4d8978272bdc49b85c50a4f393168157ff))
* **client:** deduplicate stop reason type ([#212](https://github.com/anthropics/anthropic-sdk-java/issues/212)) ([19ed57d](https://github.com/anthropics/anthropic-sdk-java/commit/19ed57da116f6818f21bc8a979ccb3a7d4ac5f7e))
* **client:** map deserialization bug ([658fd88](https://github.com/anthropics/anthropic-sdk-java/commit/658fd888485427e0de1d03a3c24ba6336dc1d18b))


### Chores

* add hash of OpenAPI spec/config inputs to .stats.yml ([#210](https://github.com/anthropics/anthropic-sdk-java/issues/210)) ([8d3cbd4](https://github.com/anthropics/anthropic-sdk-java/commit/8d3cbd4bc4e34375cb020553a3ec3c051cd8ad6f))
* **internal:** delete unused methods and annotations ([#213](https://github.com/anthropics/anthropic-sdk-java/issues/213)) ([658fd88](https://github.com/anthropics/anthropic-sdk-java/commit/658fd888485427e0de1d03a3c24ba6336dc1d18b))

## 0.9.1 (2025-03-25)

Full Changelog: [v0.9.0...v0.9.1](https://github.com/anthropics/anthropic-sdk-java/compare/v0.9.0...v0.9.1)

### Bug Fixes

* **client:** incorrect property value in helper method ([#209](https://github.com/anthropics/anthropic-sdk-java/issues/209)) ([f670f18](https://github.com/anthropics/anthropic-sdk-java/commit/f670f186d055fc58c88f98f7f3b9d35b2a1cd639))


### Documentation

* add image example ([1a33058](https://github.com/anthropics/anthropic-sdk-java/commit/1a33058e3f840477d35052351f9f2b981b2aff65))

## 0.9.0 (2025-03-25)

Full Changelog: [v0.8.0...v0.9.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.8.0...v0.9.0)

### Features

* **client:** add convenience methods for some response headers ([#196](https://github.com/anthropics/anthropic-sdk-java/issues/196)) ([a6bad98](https://github.com/anthropics/anthropic-sdk-java/commit/a6bad98ad22e4078e2a790b042275481b0664c20))
* **client:** add message accumulator helper ([6031ac3](https://github.com/anthropics/anthropic-sdk-java/commit/6031ac3de776a7b83818a13a983c4155dfa81bff))
* **client:** add sse exception type ([#197](https://github.com/anthropics/anthropic-sdk-java/issues/197)) ([ecb7c02](https://github.com/anthropics/anthropic-sdk-java/commit/ecb7c029af66c01dcd3e5929ed651ccf4b123348))
* **client:** support a lower jackson version ([#201](https://github.com/anthropics/anthropic-sdk-java/issues/201)) ([cd51a6d](https://github.com/anthropics/anthropic-sdk-java/commit/cd51a6d6b3ba354e512b473ec77b4a49176b542b))
* **client:** throw on incompatible jackson version ([cd51a6d](https://github.com/anthropics/anthropic-sdk-java/commit/cd51a6d6b3ba354e512b473ec77b4a49176b542b))


### Chores

* **client:** delete unused enums ([#190](https://github.com/anthropics/anthropic-sdk-java/issues/190)) ([8385e32](https://github.com/anthropics/anthropic-sdk-java/commit/8385e32d00d6f49ecb1e7396c216aa91e84a392c))
* **internal:** add generated comment ([#181](https://github.com/anthropics/anthropic-sdk-java/issues/181)) ([ba94b91](https://github.com/anthropics/anthropic-sdk-java/commit/ba94b91162eddc432b37683872ebdfa8f357e553))
* **internal:** add some tests for union classes ([#188](https://github.com/anthropics/anthropic-sdk-java/issues/188)) ([ba17044](https://github.com/anthropics/anthropic-sdk-java/commit/ba1704417557f069cb18e55bc4d4b372d7d55427))
* **internal:** add tests for `_headers()` ([#186](https://github.com/anthropics/anthropic-sdk-java/issues/186)) ([4d7c8b0](https://github.com/anthropics/anthropic-sdk-java/commit/4d7c8b04bee7e43b8e0352d1ba66190dbcbed41e))
* **internal:** delete duplicate tests ([03c696c](https://github.com/anthropics/anthropic-sdk-java/commit/03c696c530ad131290300b582d148e9146588a13))
* **internal:** fix example formatting ([#194](https://github.com/anthropics/anthropic-sdk-java/issues/194)) ([fb9fedc](https://github.com/anthropics/anthropic-sdk-java/commit/fb9fedce66ceb96e0112f4e082c241544f42c82b))
* **internal:** generate more tests ([fec918b](https://github.com/anthropics/anthropic-sdk-java/commit/fec918b389692cf51bbe76f2a6f2e1931bb9fa59))
* **internal:** make multipart assertions more robust ([9799aee](https://github.com/anthropics/anthropic-sdk-java/commit/9799aee94846a5daca1967d2c8e9fd3f047cf3f4))
* **internal:** make test classes internal ([#180](https://github.com/anthropics/anthropic-sdk-java/issues/180)) ([c7df54f](https://github.com/anthropics/anthropic-sdk-java/commit/c7df54fe0c94faad36d9be82125bf21e53c35484))
* **internal:** refactor query param serialization impl and tests ([#184](https://github.com/anthropics/anthropic-sdk-java/issues/184)) ([71df800](https://github.com/anthropics/anthropic-sdk-java/commit/71df800c076b9b267af28258ce7e9cdb8d5542ad))
* **internal:** refactor some test assertions ([03c696c](https://github.com/anthropics/anthropic-sdk-java/commit/03c696c530ad131290300b582d148e9146588a13))
* **internal:** reformat some tests ([#187](https://github.com/anthropics/anthropic-sdk-java/issues/187)) ([fec918b](https://github.com/anthropics/anthropic-sdk-java/commit/fec918b389692cf51bbe76f2a6f2e1931bb9fa59))
* **internal:** remove unnecessary `assertNotNull` calls ([9799aee](https://github.com/anthropics/anthropic-sdk-java/commit/9799aee94846a5daca1967d2c8e9fd3f047cf3f4))
* **internal:** remove unnecessary import ([a6bad98](https://github.com/anthropics/anthropic-sdk-java/commit/a6bad98ad22e4078e2a790b042275481b0664c20))
* **internal:** rename `getPathParam` ([#185](https://github.com/anthropics/anthropic-sdk-java/issues/185)) ([03c696c](https://github.com/anthropics/anthropic-sdk-java/commit/03c696c530ad131290300b582d148e9146588a13))
* **internal:** reorder some params methodsc ([03c696c](https://github.com/anthropics/anthropic-sdk-java/commit/03c696c530ad131290300b582d148e9146588a13))


### Documentation

* add `build` method comments ([#182](https://github.com/anthropics/anthropic-sdk-java/issues/182)) ([90be423](https://github.com/anthropics/anthropic-sdk-java/commit/90be4238d274d449a9c6bb0f59d23804d4f4ce6f))
* deduplicate and refine comments ([#176](https://github.com/anthropics/anthropic-sdk-java/issues/176)) ([709270e](https://github.com/anthropics/anthropic-sdk-java/commit/709270ea812864cb9b611986848507a4447b530e))
* fix streaming helpers code snippet ([#200](https://github.com/anthropics/anthropic-sdk-java/issues/200)) ([7e90def](https://github.com/anthropics/anthropic-sdk-java/commit/7e90deff6596f1e52b2229815e1026f234516f2c))
* minor readme tweak ([#198](https://github.com/anthropics/anthropic-sdk-java/issues/198)) ([4bfd861](https://github.com/anthropics/anthropic-sdk-java/commit/4bfd861ebb8d43dd54bd436581b0476a1c8c1ab4))
* refine comments on multipart params ([#192](https://github.com/anthropics/anthropic-sdk-java/issues/192)) ([9799aee](https://github.com/anthropics/anthropic-sdk-java/commit/9799aee94846a5daca1967d2c8e9fd3f047cf3f4))
* update readme exception docs ([ecb7c02](https://github.com/anthropics/anthropic-sdk-java/commit/ecb7c029af66c01dcd3e5929ed651ccf4b123348))

## 0.8.0 (2025-03-13)

Full Changelog: [v0.7.0...v0.8.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.7.0...v0.8.0)

### ⚠ BREAKING CHANGES

* **client:** move classes into subpackages and shorten names ([#173](https://github.com/anthropics/anthropic-sdk-java/issues/173))

### Chores

* **client:** move classes into subpackages and shorten names ([#173](https://github.com/anthropics/anthropic-sdk-java/issues/173)) ([f5edb0c](https://github.com/anthropics/anthropic-sdk-java/commit/f5edb0cbba8fc3601d83fdb7f9c6483abdd1b121))
* **internal:** add `.kotlin` to `.gitignore` ([#165](https://github.com/anthropics/anthropic-sdk-java/issues/165)) ([31f4b52](https://github.com/anthropics/anthropic-sdk-java/commit/31f4b5243a27de6b785a951f93829048752b81ad))
* **internal:** don't use `JvmOverloads` in interfaces ([1193767](https://github.com/anthropics/anthropic-sdk-java/commit/11937673640ce034896912881ae8db89087e4b19))
* **internal:** make `regionMissing` test more robust ([23989ce](https://github.com/anthropics/anthropic-sdk-java/commit/23989ce7eb7c43a2458d8c25513c5adb2a0529f8))
* **internal:** reenable warnings as errors ([#169](https://github.com/anthropics/anthropic-sdk-java/issues/169)) ([1193767](https://github.com/anthropics/anthropic-sdk-java/commit/11937673640ce034896912881ae8db89087e4b19))
* **internal:** remove extra empty newlines ([#175](https://github.com/anthropics/anthropic-sdk-java/issues/175)) ([a372a5f](https://github.com/anthropics/anthropic-sdk-java/commit/a372a5f06586959948d8a6bb47b38aff484cce86))
* **internal:** use `getOrNull` instead of `orElse(null)` ([#167](https://github.com/anthropics/anthropic-sdk-java/issues/167)) ([898c39b](https://github.com/anthropics/anthropic-sdk-java/commit/898c39b10a7c9491ddd7284ee59cd65572f0642a))


### Documentation

* document `JsonValue` construction in readme ([#172](https://github.com/anthropics/anthropic-sdk-java/issues/172)) ([19de052](https://github.com/anthropics/anthropic-sdk-java/commit/19de0521dda6504c49836014bb46dd05aa353826))
* revise readme docs about nested params ([#171](https://github.com/anthropics/anthropic-sdk-java/issues/171)) ([96d79ec](https://github.com/anthropics/anthropic-sdk-java/commit/96d79ecc315efb94fd23734ffec1713c910870c4))
* update examples ([61a5d0b](https://github.com/anthropics/anthropic-sdk-java/commit/61a5d0b996f588d48919930f52d7ac214827146f))

## 0.7.0 (2025-03-06)

Full Changelog: [v0.6.0...v0.7.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.6.0...v0.7.0)

### Features

* **client:** accept `InputStream` and `Path` for file params ([#162](https://github.com/anthropics/anthropic-sdk-java/issues/162)) ([803e738](https://github.com/anthropics/anthropic-sdk-java/commit/803e7389c5704943e3b2092e6a9bdd678455d97c))
* **client:** detect binary incompatible jackson versions ([#164](https://github.com/anthropics/anthropic-sdk-java/issues/164)) ([2bb461b](https://github.com/anthropics/anthropic-sdk-java/commit/2bb461b2a3017796fca523765e35b9a63636cc86))


### Chores

* **ci:** actually run more examples ([d3bd8ef](https://github.com/anthropics/anthropic-sdk-java/commit/d3bd8ef397ef6e6965cf0887bcb59f74f2fe6adc))
* **ci:** run more examples ([25cdaa0](https://github.com/anthropics/anthropic-sdk-java/commit/25cdaa0360b9fa79f628a93b6ec8db63a641f344))
* **client:** expose `Optional`, not nullable, from `ClientOptions` ([#158](https://github.com/anthropics/anthropic-sdk-java/issues/158)) ([2b96d8e](https://github.com/anthropics/anthropic-sdk-java/commit/2b96d8e549e9aaad15c3bac5a0ced8df00b75aff))


### Documentation

* note required fields in `builder` javadoc ([#156](https://github.com/anthropics/anthropic-sdk-java/issues/156)) ([f3b9086](https://github.com/anthropics/anthropic-sdk-java/commit/f3b9086ae4239fe98b24a7c54885257b90e55176))
* remove extra example stuff ([c21759b](https://github.com/anthropics/anthropic-sdk-java/commit/c21759ba68a032fb8f9ba3977904b5d169698c7f))

## 0.6.0 (2025-03-05)

Full Changelog: [v0.5.0...v0.6.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.5.0...v0.6.0)

### ⚠ BREAKING CHANGES

* **client:** refactor multipart formdata impl ([#152](https://github.com/anthropics/anthropic-sdk-java/issues/152))

### Features

* **api:** add support for disabling tool calls ([#147](https://github.com/anthropics/anthropic-sdk-java/issues/147)) ([584abcf](https://github.com/anthropics/anthropic-sdk-java/commit/584abcfc4c6695e2f7181bbe8970e3d90b912bfe))
* **client:** add Bedrock and Vertex support ([#111](https://github.com/anthropics/anthropic-sdk-java/issues/111)) ([e637f76](https://github.com/anthropics/anthropic-sdk-java/commit/e637f760d15e30f209464b3ae160bf22de586b82))
* **client:** allow configuring timeouts granularly ([#149](https://github.com/anthropics/anthropic-sdk-java/issues/149)) ([273e179](https://github.com/anthropics/anthropic-sdk-java/commit/273e179a2835e8cd6821f3004d5285fa8aa93783))
* **client:** support raw response access ([#151](https://github.com/anthropics/anthropic-sdk-java/issues/151)) ([2c2ad9b](https://github.com/anthropics/anthropic-sdk-java/commit/2c2ad9beef9d5ab7f2fb7360808d0acaf96b7b01))


### Chores

* **client:** refactor multipart formdata impl ([#152](https://github.com/anthropics/anthropic-sdk-java/issues/152)) ([b6af3af](https://github.com/anthropics/anthropic-sdk-java/commit/b6af3af6435016bfe38168a17253e7b92a400e0c))
* **internal:** fix bedrock test when AWS_REGION env is set ([06bd99e](https://github.com/anthropics/anthropic-sdk-java/commit/06bd99e9b6bd3b6436c31aafd886748808e0bf71))
* **internal:** refactor `ErrorHandlingTest` ([#148](https://github.com/anthropics/anthropic-sdk-java/issues/148)) ([d6973c0](https://github.com/anthropics/anthropic-sdk-java/commit/d6973c082319a36ed6311ca3385be9dc444db16f))
* **internal:** use `assertNotNull` in tests ([b049ff4](https://github.com/anthropics/anthropic-sdk-java/commit/b049ff4eddb328af821ecf86730f6135c896419c))


### Documentation

* add raw response readme documentation ([#153](https://github.com/anthropics/anthropic-sdk-java/issues/153)) ([17144cc](https://github.com/anthropics/anthropic-sdk-java/commit/17144cc8f0b0857dea653061005df5bfb0029e5d))
* update URLs from stainlessapi.com to stainless.com ([#146](https://github.com/anthropics/anthropic-sdk-java/issues/146)) ([562878a](https://github.com/anthropics/anthropic-sdk-java/commit/562878aeb3e03ebecc6d40ce9163e5844735589f))

## 0.5.0 (2025-02-27)

Full Changelog: [v0.4.0...v0.5.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.4.0...v0.5.0)

### Features

* **api:** add URL source blocks for images and PDFs ([#143](https://github.com/anthropics/anthropic-sdk-java/issues/143)) ([1664d96](https://github.com/anthropics/anthropic-sdk-java/commit/1664d96aca824d3272c4b26e3b5701beec98142b))
* **client:** allow omitting params object when none required ([#138](https://github.com/anthropics/anthropic-sdk-java/issues/138)) ([bb32e01](https://github.com/anthropics/anthropic-sdk-java/commit/bb32e01917891afa85bf68776708f25873f7c510))


### Bug Fixes

* **api:** support `.list()` without arguments. ([#131](https://github.com/anthropics/anthropic-sdk-java/issues/131)) ([617a61e](https://github.com/anthropics/anthropic-sdk-java/commit/617a61e3a0720d99f281a964cd1e01ead95c2ae4))
* **client:** add missing `@JvmStatic` ([#139](https://github.com/anthropics/anthropic-sdk-java/issues/139)) ([22911b2](https://github.com/anthropics/anthropic-sdk-java/commit/22911b210b10cfa1f4dd157aa1fe659ddf76113c))


### Chores

* **client:** use deep identity methods for primitive array types ([#141](https://github.com/anthropics/anthropic-sdk-java/issues/141)) ([b63432c](https://github.com/anthropics/anthropic-sdk-java/commit/b63432cc1dbb0184004f4033ac676276787a42f8))
* **internal:** add async service tests ([#140](https://github.com/anthropics/anthropic-sdk-java/issues/140)) ([4cea171](https://github.com/anthropics/anthropic-sdk-java/commit/4cea17133eaa308f2ff5fb47db0e2842ebbf1975))
* **internal:** improve sync service tests ([4cea171](https://github.com/anthropics/anthropic-sdk-java/commit/4cea17133eaa308f2ff5fb47db0e2842ebbf1975))
* **internal:** refactor `ServiceParamsTest` ([#142](https://github.com/anthropics/anthropic-sdk-java/issues/142)) ([382a795](https://github.com/anthropics/anthropic-sdk-java/commit/382a795d6907951674a9d52002da8aacb0350552))
* **internal:** update spec ([#134](https://github.com/anthropics/anthropic-sdk-java/issues/134)) ([5fd7122](https://github.com/anthropics/anthropic-sdk-java/commit/5fd71229e9b996af7178a0ee2e332780d97e0c4b))


### Documentation

* add source file links to readme ([#137](https://github.com/anthropics/anthropic-sdk-java/issues/137)) ([2fbdb1c](https://github.com/anthropics/anthropic-sdk-java/commit/2fbdb1c0fe3c6d775528b0645b5335e219267319))
* add thinking examples ([eea6f93](https://github.com/anthropics/anthropic-sdk-java/commit/eea6f9327aa80e2e8323a8a0a6f5a50e1d8b4c26))
* readme parameter tweaks ([4cea171](https://github.com/anthropics/anthropic-sdk-java/commit/4cea17133eaa308f2ff5fb47db0e2842ebbf1975))

## 0.4.0 (2025-02-24)

Full Changelog: [v0.3.0...v0.4.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.3.0...v0.4.0)

### Features

* **api:** add claude-3.7 + support for thinking ([ff25a37](https://github.com/anthropics/anthropic-sdk-java/commit/ff25a379cd981af94be9858c55eb293f06519639))

## 0.3.0 (2025-02-21)

Full Changelog: [v0.2.0...v0.3.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.2.0...v0.3.0)

### Features

* **client:** add an `AsyncStreamResponse#onCompleteFuture()` method ([#126](https://github.com/anthropics/anthropic-sdk-java/issues/126)) ([36473c8](https://github.com/anthropics/anthropic-sdk-java/commit/36473c86ebc32d83273e4fd92b136a9a4d81d90f))
* **client:** get rid of annoying checked exceptions ([#124](https://github.com/anthropics/anthropic-sdk-java/issues/124)) ([da9d44d](https://github.com/anthropics/anthropic-sdk-java/commit/da9d44d1d704ad2fabea8f5c3664e21a0002e88d))
* **client:** support `JsonField#asX()` for known values ([#118](https://github.com/anthropics/anthropic-sdk-java/issues/118)) ([f3eb6d4](https://github.com/anthropics/anthropic-sdk-java/commit/f3eb6d43c4f9a0ec1024322192155936e6eb7c47))
* **client:** update enum `asX` methods ([#117](https://github.com/anthropics/anthropic-sdk-java/issues/117)) ([d7ce356](https://github.com/anthropics/anthropic-sdk-java/commit/d7ce356315b40e869851292bf74c9cbee76d10c5))


### Bug Fixes

* **client:** add missing `streamHandlerExecutor` method ([#121](https://github.com/anthropics/anthropic-sdk-java/issues/121)) ([d7eee9f](https://github.com/anthropics/anthropic-sdk-java/commit/d7eee9f2dc066deb0bde222bfa35aa83007aa1f9))
* **client:** prevent `IOException` when closing stream early ([#128](https://github.com/anthropics/anthropic-sdk-java/issues/128)) ([25405b3](https://github.com/anthropics/anthropic-sdk-java/commit/25405b3562a71a0a0d3e62194d8e37d7464cd6c7))


### Chores

* **ci:** update gradle actions to v4 ([#123](https://github.com/anthropics/anthropic-sdk-java/issues/123)) ([1d0d249](https://github.com/anthropics/anthropic-sdk-java/commit/1d0d249137c44dc4c7fa4d35da3bd2e57d9cbd56))
* **docs:** add faq to readme ([#127](https://github.com/anthropics/anthropic-sdk-java/issues/127)) ([8c74fb4](https://github.com/anthropics/anthropic-sdk-java/commit/8c74fb4d06c8e9bcd5301f499524a65439401ede))
* **docs:** add streaming info to readme ([d7eee9f](https://github.com/anthropics/anthropic-sdk-java/commit/d7eee9f2dc066deb0bde222bfa35aa83007aa1f9))
* **docs:** reorganize readme ([#119](https://github.com/anthropics/anthropic-sdk-java/issues/119)) ([64dc7b9](https://github.com/anthropics/anthropic-sdk-java/commit/64dc7b9395ad81b90057e45ec5bed74b64cdafd2))
* **internal:** get rid of configuration cache ([#122](https://github.com/anthropics/anthropic-sdk-java/issues/122)) ([632b4fd](https://github.com/anthropics/anthropic-sdk-java/commit/632b4fd1f2b918167db20568fba861a367dadbfd))
* **internal:** make body class constructors private ([ac8128c](https://github.com/anthropics/anthropic-sdk-java/commit/ac8128c92f8d849ec7954a808866399f6b48bbf6))
* **internal:** make body classes for multipart requests ([ac8128c](https://github.com/anthropics/anthropic-sdk-java/commit/ac8128c92f8d849ec7954a808866399f6b48bbf6))
* **internal:** misc formatting changes ([ac8128c](https://github.com/anthropics/anthropic-sdk-java/commit/ac8128c92f8d849ec7954a808866399f6b48bbf6))
* **internal:** optimize build and test perf ([d14a7b6](https://github.com/anthropics/anthropic-sdk-java/commit/d14a7b6b2b157fb7533e7db1359eff116563c83d))
* **internal:** refactor `PhantomReachableClosingAsyncStreamResponse` impl ([#112](https://github.com/anthropics/anthropic-sdk-java/issues/112)) ([a87231c](https://github.com/anthropics/anthropic-sdk-java/commit/a87231c5eef48120b28d3de4e3c1b12f78bafec0))
* **internal:** rename internal body classes ([ac8128c](https://github.com/anthropics/anthropic-sdk-java/commit/ac8128c92f8d849ec7954a808866399f6b48bbf6))
* **internal:** update formatter ([#115](https://github.com/anthropics/anthropic-sdk-java/issues/115)) ([d14a7b6](https://github.com/anthropics/anthropic-sdk-java/commit/d14a7b6b2b157fb7533e7db1359eff116563c83d))
* **internal:** update some formatting in `Values.kt` ([f3eb6d4](https://github.com/anthropics/anthropic-sdk-java/commit/f3eb6d43c4f9a0ec1024322192155936e6eb7c47))
* **internal:** use better test example values ([#116](https://github.com/anthropics/anthropic-sdk-java/issues/116)) ([ac8128c](https://github.com/anthropics/anthropic-sdk-java/commit/ac8128c92f8d849ec7954a808866399f6b48bbf6))


### Documentation

* add immutability explanation to readme ([#130](https://github.com/anthropics/anthropic-sdk-java/issues/130)) ([81915c1](https://github.com/anthropics/anthropic-sdk-java/commit/81915c1df9c17192f6940dd7982325c7cc6c38d0))
* add more documentation to `AsyncStreamResponse` ([36473c8](https://github.com/anthropics/anthropic-sdk-java/commit/36473c86ebc32d83273e4fd92b136a9a4d81d90f))
* add more phantom reachability docs ([a87231c](https://github.com/anthropics/anthropic-sdk-java/commit/a87231c5eef48120b28d3de4e3c1b12f78bafec0))
* add stream cancellation example ([520fe0c](https://github.com/anthropics/anthropic-sdk-java/commit/520fe0c6a86cb0576585a0f71015d4943a4b6075))
* add token counting example ([9d029a3](https://github.com/anthropics/anthropic-sdk-java/commit/9d029a305c02f4010ede74007215515607cb03d5))
* remove unnecessary catch clauses in readme ([#125](https://github.com/anthropics/anthropic-sdk-java/issues/125)) ([a49f55a](https://github.com/anthropics/anthropic-sdk-java/commit/a49f55ae97273da3373d8d490cd356072ee72f7e))
* remove unnecessary checked exception signatures ([5bb9829](https://github.com/anthropics/anthropic-sdk-java/commit/5bb98291b390ff3708467413ed0428acc4d050f1))
* use `onCompleteFuture()` in examples ([8c45c54](https://github.com/anthropics/anthropic-sdk-java/commit/8c45c54d3382cdc02e6e90f7cf1af358ce403f1a))

## 0.2.0 (2025-02-12)

Full Changelog: [v0.1.0...v0.2.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0...v0.2.0)

### Features

* **client:** remove unnecessary enums ([899302d](https://github.com/anthropics/anthropic-sdk-java/commit/899302d66120d87a16a0593b198097d547348215))
* **client:** remove unnecessary enums ([#110](https://github.com/anthropics/anthropic-sdk-java/issues/110)) ([9f62353](https://github.com/anthropics/anthropic-sdk-java/commit/9f6235372e5401c553d95d3541c5eff4b15c483d))
* **client:** send client-side timeout headers ([#106](https://github.com/anthropics/anthropic-sdk-java/issues/106)) ([b3d911a](https://github.com/anthropics/anthropic-sdk-java/commit/b3d911ad983b308c9be9c51b5e0fc4cd37046148))


### Bug Fixes

* **api:** add missing `@MustBeClosed` annotations ([#109](https://github.com/anthropics/anthropic-sdk-java/issues/109)) ([1200422](https://github.com/anthropics/anthropic-sdk-java/commit/1200422e7992c22b57de03a48b10430964d33112))
* **api:** switch `CompletableFuture&lt;Void&gt;` to `CompletableFuture<Void?>` ([1200422](https://github.com/anthropics/anthropic-sdk-java/commit/1200422e7992c22b57de03a48b10430964d33112))
* **client:** add missing validation calls on response ([1200422](https://github.com/anthropics/anthropic-sdk-java/commit/1200422e7992c22b57de03a48b10430964d33112))
* **client:** always provide a body for `PATCH` methods ([1200422](https://github.com/anthropics/anthropic-sdk-java/commit/1200422e7992c22b57de03a48b10430964d33112))


### Chores

* **internal:** minor formatting/style changes ([1200422](https://github.com/anthropics/anthropic-sdk-java/commit/1200422e7992c22b57de03a48b10430964d33112))
* **internal:** rename some tests ([1200422](https://github.com/anthropics/anthropic-sdk-java/commit/1200422e7992c22b57de03a48b10430964d33112))


### Documentation

* add tools example ([7183a54](https://github.com/anthropics/anthropic-sdk-java/commit/7183a5495f50bb9e004329e06e2d34a0517b4bf1))
* fix typo in example ([55662e9](https://github.com/anthropics/anthropic-sdk-java/commit/55662e9c447fffe7a712227e605afcb9d139e84f))

## 0.1.0 (2025-01-31)

Full Changelog: [v0.1.0-alpha.11...v0.1.0](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.11...v0.1.0)

### Features

* **client:** helpers for discriminated union variants with one required prop ([#98](https://github.com/anthropics/anthropic-sdk-java/issues/98)) ([03c6ff6](https://github.com/anthropics/anthropic-sdk-java/commit/03c6ff6ea48d331cabf5633b6bce664f3d3b04dc))


### Bug Fixes

* **client:** don't leak responses when retrying ([#103](https://github.com/anthropics/anthropic-sdk-java/issues/103)) ([052894b](https://github.com/anthropics/anthropic-sdk-java/commit/052894b2a757a4436194266fe41677239b6fa17a))


### Chores

* **api:** update openapi spec url ([#104](https://github.com/anthropics/anthropic-sdk-java/issues/104)) ([eabbf47](https://github.com/anthropics/anthropic-sdk-java/commit/eabbf47bb7844afe3c0f5573dd7533da6a07d612))
* **internal:** simplify object construction ([#99](https://github.com/anthropics/anthropic-sdk-java/issues/99)) ([df51ab4](https://github.com/anthropics/anthropic-sdk-java/commit/df51ab48521b81e544fe4c5abe2d059852335110))


### Documentation

* fix incorrect additional properties info ([#105](https://github.com/anthropics/anthropic-sdk-java/issues/105)) ([535ea7e](https://github.com/anthropics/anthropic-sdk-java/commit/535ea7e20d76541ca974f40d78822a7ca89d7927))
* format examples ([4b73f0a](https://github.com/anthropics/anthropic-sdk-java/commit/4b73f0a5856c5d02e121c142428264cb5103b631))
* update examples to use shorthand ([52ddf54](https://github.com/anthropics/anthropic-sdk-java/commit/52ddf5497f53f2cbfc705acade9811ebc392c41d))
* update to beta ([cfba991](https://github.com/anthropics/anthropic-sdk-java/commit/cfba9919e160107ce3c3876265c41c4976694f54))

## 0.1.0-alpha.11 (2025-01-29)

Full Changelog: [v0.1.0-alpha.10...v0.1.0-alpha.11](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.10...v0.1.0-alpha.11)

### Chores

* **internal:** improve `RetryingHttpClientTest` ([#97](https://github.com/anthropics/anthropic-sdk-java/issues/97)) ([314215c](https://github.com/anthropics/anthropic-sdk-java/commit/314215cf62e2e6c6b65cfb71d48747056d6091cb))


### Documentation

* update feedback link ([51c5b70](https://github.com/anthropics/anthropic-sdk-java/commit/51c5b703d9c79ace259ff02a3ff7dae333f5438c))

## 0.1.0-alpha.10 (2025-01-29)

Full Changelog: [v0.1.0-alpha.9...v0.1.0-alpha.10](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.9...v0.1.0-alpha.10)

### ⚠ BREAKING CHANGES

* **client:** better union variant method and variable names ([#82](https://github.com/anthropics/anthropic-sdk-java/issues/82))

### Features

* **api:** add citations ([#78](https://github.com/anthropics/anthropic-sdk-java/issues/78)) ([f206f8a](https://github.com/anthropics/anthropic-sdk-java/commit/f206f8a2b3334714f8dfde4d4a4fc602a8233272))
* **client:** add `_queryParams` and `_headers` methods ([#94](https://github.com/anthropics/anthropic-sdk-java/issues/94)) ([6387b49](https://github.com/anthropics/anthropic-sdk-java/commit/6387b498b486af6a669f103fb2144431a7a40670))
* **client:** add `close` method ([#87](https://github.com/anthropics/anthropic-sdk-java/issues/87)) ([2ef94d1](https://github.com/anthropics/anthropic-sdk-java/commit/2ef94d196ba402fd7af3db48aed98e7f57afca90))
* **client:** better union variant method and variable names ([#82](https://github.com/anthropics/anthropic-sdk-java/issues/82)) ([f961513](https://github.com/anthropics/anthropic-sdk-java/commit/f9615133f292297ce37019cfb2db40b08c3e4c39))


### Bug Fixes

* **client:** async streaming flakiness ([#85](https://github.com/anthropics/anthropic-sdk-java/issues/85)) ([fbc58f3](https://github.com/anthropics/anthropic-sdk-java/commit/fbc58f3931c1d11f16cd1095818d13c7e8f47e28))
* **client:** make some classes and constructors non-public ([#90](https://github.com/anthropics/anthropic-sdk-java/issues/90)) ([803559d](https://github.com/anthropics/anthropic-sdk-java/commit/803559d4b43e90543f700080d768a81a831aa9b8))
* examples ([0a7cd8c](https://github.com/anthropics/anthropic-sdk-java/commit/0a7cd8c951078ecbee42c71b84ea0b03b33c6fd7))


### Chores

* add max retries to test ([#83](https://github.com/anthropics/anthropic-sdk-java/issues/83)) ([8b96530](https://github.com/anthropics/anthropic-sdk-java/commit/8b96530c976f3ebdc48c7832e1cab7a6b9e98c99))
* **api:** deprecate some models ([#93](https://github.com/anthropics/anthropic-sdk-java/issues/93)) ([e88f509](https://github.com/anthropics/anthropic-sdk-java/commit/e88f509fbe355f54053efb516b00fe8ce5438deb))
* **docs:** updates ([#81](https://github.com/anthropics/anthropic-sdk-java/issues/81)) ([1c064b5](https://github.com/anthropics/anthropic-sdk-java/commit/1c064b5fdd4e5c8708f7c50a8d1a1737f9b55704))
* **internal:** fix release ([#86](https://github.com/anthropics/anthropic-sdk-java/issues/86)) ([122923d](https://github.com/anthropics/anthropic-sdk-java/commit/122923d1a3006b052966d0ba5226a1a7091f9174))
* **internal:** remove some unnecessary `constructor` keywords ([803559d](https://github.com/anthropics/anthropic-sdk-java/commit/803559d4b43e90543f700080d768a81a831aa9b8))
* **internal:** shorten `model` method impl ([#92](https://github.com/anthropics/anthropic-sdk-java/issues/92)) ([10c45b2](https://github.com/anthropics/anthropic-sdk-java/commit/10c45b28377ac8ebb84f474c09792b031056bf6d))
* **internal:** swap `checkNotNull` to `checkRequired` ([#80](https://github.com/anthropics/anthropic-sdk-java/issues/80)) ([7bc70d7](https://github.com/anthropics/anthropic-sdk-java/commit/7bc70d70d4900dd481300a9fdce2b7c95d23150e))


### Documentation

* `async` and `sync` method comments ([#89](https://github.com/anthropics/anthropic-sdk-java/issues/89)) ([b7dbed7](https://github.com/anthropics/anthropic-sdk-java/commit/b7dbed7759c78c9bd9ba6b660afe5718b995e5a0))
* add client documentation ([#88](https://github.com/anthropics/anthropic-sdk-java/issues/88)) ([ac678e4](https://github.com/anthropics/anthropic-sdk-java/commit/ac678e4e238b634faed95a9e52018a5443d9e29b))
* builder, enum, and union comments ([#91](https://github.com/anthropics/anthropic-sdk-java/issues/91)) ([e8ad558](https://github.com/anthropics/anthropic-sdk-java/commit/e8ad558347d04ffea57863568453fb824289ca82))
* many more examples ([#84](https://github.com/anthropics/anthropic-sdk-java/issues/84)) ([52f73db](https://github.com/anthropics/anthropic-sdk-java/commit/52f73dbe747e42ea9dda87283db7073d2a59570e))


### Refactors

* **internal:** extract request preparation logic ([6387b49](https://github.com/anthropics/anthropic-sdk-java/commit/6387b498b486af6a669f103fb2144431a7a40670))

## 0.1.0-alpha.9 (2025-01-22)

Full Changelog: [v0.1.0-alpha.8...v0.1.0-alpha.9](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.8...v0.1.0-alpha.9)

### Features

* **client:** make message roundtripping more robust ([#75](https://github.com/anthropics/anthropic-sdk-java/issues/75)) ([3153b8b](https://github.com/anthropics/anthropic-sdk-java/commit/3153b8b4512d8e97daa3a80907c8c246dbf6778c))
* **client:** support results endpoint ([#73](https://github.com/anthropics/anthropic-sdk-java/issues/73)) ([361a2d2](https://github.com/anthropics/anthropic-sdk-java/commit/361a2d21e12329600447b0703fa5a863ab725d1f))


### Bug Fixes

* **client:** add missing default headers ([#72](https://github.com/anthropics/anthropic-sdk-java/issues/72)) ([4bfb8c5](https://github.com/anthropics/anthropic-sdk-java/commit/4bfb8c5bc3f2d7372243d727937bec1cedd468df))
* **client:** make service impl constructors internal ([#70](https://github.com/anthropics/anthropic-sdk-java/issues/70)) ([77f8873](https://github.com/anthropics/anthropic-sdk-java/commit/77f88730bd9066a91d667f9a432beef9e47b0e9e))


### Chores

* **internal:** add file comment ([#74](https://github.com/anthropics/anthropic-sdk-java/issues/74)) ([70def92](https://github.com/anthropics/anthropic-sdk-java/commit/70def920ea6c7e358f9b464411417408770ac627))
* **internal:** move `StreamResponse` method ([#65](https://github.com/anthropics/anthropic-sdk-java/issues/65)) ([083a7b5](https://github.com/anthropics/anthropic-sdk-java/commit/083a7b5746329527eac1d12fbe6e10a6ca522035))
* **internal:** refactor streaming implementation ([#64](https://github.com/anthropics/anthropic-sdk-java/issues/64)) ([02dcd90](https://github.com/anthropics/anthropic-sdk-java/commit/02dcd9031064cfd7e7a6a9fc25b14d7b3adddc78))
* **internal:** remove space ([#63](https://github.com/anthropics/anthropic-sdk-java/issues/63)) ([b3a86b3](https://github.com/anthropics/anthropic-sdk-java/commit/b3a86b36ff3c4c7135931125a0958e960986ada8))
* **internal:** upgrade kotlin compiler and gradle ([#66](https://github.com/anthropics/anthropic-sdk-java/issues/66)) ([8685797](https://github.com/anthropics/anthropic-sdk-java/commit/86857974a752405318ca92fe4d593af757742743))


### Documentation

* add javadoc.io badge ([#68](https://github.com/anthropics/anthropic-sdk-java/issues/68)) ([9853408](https://github.com/anthropics/anthropic-sdk-java/commit/98534089861eb8f3bbd78863bcbc1ed69bcf80dc))
* add more documentation ([#67](https://github.com/anthropics/anthropic-sdk-java/issues/67)) ([c179f7c](https://github.com/anthropics/anthropic-sdk-java/commit/c179f7cf2648d8df5d110df8e2cee2e658d1a739))
* generate merged core + okhttp docs ([#61](https://github.com/anthropics/anthropic-sdk-java/issues/61)) ([c2802d1](https://github.com/anthropics/anthropic-sdk-java/commit/c2802d114cf04ebf49d9d3988c094732f9ab5d3f))
* more enum documentation ([#71](https://github.com/anthropics/anthropic-sdk-java/issues/71)) ([21c6ef2](https://github.com/anthropics/anthropic-sdk-java/commit/21c6ef237d88a506915eb7cb0eb4a79d6b603528))
* move up requirements section ([#77](https://github.com/anthropics/anthropic-sdk-java/issues/77)) ([a3243cb](https://github.com/anthropics/anthropic-sdk-java/commit/a3243cb562af8897792c0d3e51327d6f7230605f))
* update readme ([#76](https://github.com/anthropics/anthropic-sdk-java/issues/76)) ([fe53290](https://github.com/anthropics/anthropic-sdk-java/commit/fe5329055b49ef2dcd57bb4b307ec0614c538551))

## 0.1.0-alpha.8 (2025-01-15)

Full Changelog: [v0.1.0-alpha.7...v0.1.0-alpha.8](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.7...v0.1.0-alpha.8)

### Chores

* **internal:** add and tweak check functions ([#55](https://github.com/anthropics/anthropic-sdk-java/issues/55)) ([16f8002](https://github.com/anthropics/anthropic-sdk-java/commit/16f80028849f54f8feab7314e33d557e4a923581))
* **internal:** remove unused gradle task ([#60](https://github.com/anthropics/anthropic-sdk-java/issues/60)) ([f503120](https://github.com/anthropics/anthropic-sdk-java/commit/f50312015016e33fefb08a07365d01bf9e11d07d))
* **internal:** tweak client options nullability handling ([16f8002](https://github.com/anthropics/anthropic-sdk-java/commit/16f80028849f54f8feab7314e33d557e4a923581))
* simplify examples involving lists ([#57](https://github.com/anthropics/anthropic-sdk-java/issues/57)) ([b9f1145](https://github.com/anthropics/anthropic-sdk-java/commit/b9f114543501938c386f659417e312844900dd56))
* simplify examples involving unions ([#58](https://github.com/anthropics/anthropic-sdk-java/issues/58)) ([e800907](https://github.com/anthropics/anthropic-sdk-java/commit/e800907343dfa88baf644eafa3953098072fcda0))


### Documentation

* don't mention a non-existent SDK ([#59](https://github.com/anthropics/anthropic-sdk-java/issues/59)) ([421a9ab](https://github.com/anthropics/anthropic-sdk-java/commit/421a9ab64779d1bb34fb10cd7ad014defe2660df))

## 0.1.0-alpha.7 (2025-01-14)

Full Changelog: [v0.1.0-alpha.6...v0.1.0-alpha.7](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.6...v0.1.0-alpha.7)

### ⚠ BREAKING CHANGES

* **client:** switch query params objects to use `QueryParams` ([#31](https://github.com/anthropics/anthropic-sdk-java/issues/31))

### Features

* **api:** add message batch delete endpoint ([#29](https://github.com/anthropics/anthropic-sdk-java/issues/29)) ([4cfead7](https://github.com/anthropics/anthropic-sdk-java/commit/4cfead7e723bf66c870ed2bc13d1f7778e572de9))
* **client:** add more builder convenience methods for lists ([#46](https://github.com/anthropics/anthropic-sdk-java/issues/46)) ([40210f9](https://github.com/anthropics/anthropic-sdk-java/commit/40210f90ff6c1c700042efdbe2649921c061bd78))
* **client:** add various convenience setters to models ([#44](https://github.com/anthropics/anthropic-sdk-java/issues/44)) ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))
* **client:** allow passing null or optional for nullable fields ([#40](https://github.com/anthropics/anthropic-sdk-java/issues/40)) ([f1594bf](https://github.com/anthropics/anthropic-sdk-java/commit/f1594bf97bfcba21ae6258622f15b1d1db8a07b3))
* **client:** allow setting arbitrary JSON for top-level body params ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))
* **client:** expose getters for `JsonField` of body params ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))
* **client:** put body field in params, add more convenience methods, and add missing docs ([#37](https://github.com/anthropics/anthropic-sdk-java/issues/37)) ([bc9974b](https://github.com/anthropics/anthropic-sdk-java/commit/bc9974b330f8f6032a69a984f9d59b15e7c43af6))


### Bug Fixes

* **client:** add some missing `validate()` calls ([#49](https://github.com/anthropics/anthropic-sdk-java/issues/49)) ([3030d05](https://github.com/anthropics/anthropic-sdk-java/commit/3030d057d53c7dd5dfd7c566204876e0903ff1bb))
* **client:** consistently throw on omitting required fields ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))
* **client:** convert `JsonField` containing list type to mutable in builder ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))


### Chores

* **internal:** add some missing newlines between methods ([#48](https://github.com/anthropics/anthropic-sdk-java/issues/48)) ([67e7461](https://github.com/anthropics/anthropic-sdk-java/commit/67e74612794995bf05acce426224752d03466455))
* **internal:** extract a `checkRequired` function ([#54](https://github.com/anthropics/anthropic-sdk-java/issues/54)) ([1562284](https://github.com/anthropics/anthropic-sdk-java/commit/1562284ae76888974318e1623fceee61ae3f2880))
* **internal:** fix up root `build.gradle.kts` formatting ([#50](https://github.com/anthropics/anthropic-sdk-java/issues/50)) ([b766f44](https://github.com/anthropics/anthropic-sdk-java/commit/b766f44dfaa6b7c67cd6ed620cd6db4256de8edd))
* **internal:** refactor `validate` methods ([3030d05](https://github.com/anthropics/anthropic-sdk-java/commit/3030d057d53c7dd5dfd7c566204876e0903ff1bb))
* **internal:** remove unused Gradle imports ([#51](https://github.com/anthropics/anthropic-sdk-java/issues/51)) ([acd68e0](https://github.com/anthropics/anthropic-sdk-java/commit/acd68e0e9fcfc99232ac53f709965b0d1bb4f2f0))
* **internal:** remove unused or unnecessary Gradle imports ([#52](https://github.com/anthropics/anthropic-sdk-java/issues/52)) ([5c2320d](https://github.com/anthropics/anthropic-sdk-java/commit/5c2320dfd370d3952e11ee8382e9f860e54b7bca))
* **internal:** update examples ([#41](https://github.com/anthropics/anthropic-sdk-java/issues/41)) ([8dc8b63](https://github.com/anthropics/anthropic-sdk-java/commit/8dc8b63e1f5f8f98e2c590e549bbb4bfc1c319a2))


### Documentation

* add params class javadocs ([#43](https://github.com/anthropics/anthropic-sdk-java/issues/43)) ([6f0ce7b](https://github.com/anthropics/anthropic-sdk-java/commit/6f0ce7b62d8f8f985d06f52881ccaed66911adb1))
* add some missing javadocs ([#38](https://github.com/anthropics/anthropic-sdk-java/issues/38)) ([49a9056](https://github.com/anthropics/anthropic-sdk-java/commit/49a90567fc27cbadabf13cb0dc3a95125501d27a))
* publish dokka javadoc ([#53](https://github.com/anthropics/anthropic-sdk-java/issues/53)) ([8bd0316](https://github.com/anthropics/anthropic-sdk-java/commit/8bd03162f0e6800a0b8e03d854eaf69088b5948e))
* **readme:** fix misplaced period ([#45](https://github.com/anthropics/anthropic-sdk-java/issues/45)) ([bcdf8cd](https://github.com/anthropics/anthropic-sdk-java/commit/bcdf8cd51de09e2b5c54788bd22d54c88f532339))
* update some builder method javadocs ([#47](https://github.com/anthropics/anthropic-sdk-java/issues/47)) ([94a426b](https://github.com/anthropics/anthropic-sdk-java/commit/94a426ba16cf5fc852751c4fce67d4a8ef80267d))


### Styles

* **internal:** explicitly add some method return types ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))
* **internal:** move headers and query params setters below others ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))
* **internal:** simplify existing convenience setters on params ([2588011](https://github.com/anthropics/anthropic-sdk-java/commit/25880112453645c2bb9d7d333bede61835f22410))
* **internal:** sort fields ([#39](https://github.com/anthropics/anthropic-sdk-java/issues/39)) ([de172c3](https://github.com/anthropics/anthropic-sdk-java/commit/de172c38c727faeb76e602e970fb4f290b2cbd8f))


### Refactors

* **client:** switch query params objects to use `QueryParams` ([#31](https://github.com/anthropics/anthropic-sdk-java/issues/31)) ([29a101a](https://github.com/anthropics/anthropic-sdk-java/commit/29a101a34d1e2a33867fdc3f946dffb10c030aa4))
* **internal:** use constructor to deserialize json ([#32](https://github.com/anthropics/anthropic-sdk-java/issues/32)) ([6f08181](https://github.com/anthropics/anthropic-sdk-java/commit/6f0818132c13db29fb76800258820b8785607bc5))

## 0.1.0-alpha.6 (2024-12-28)

Full Changelog: [v0.1.0-alpha.5...v0.1.0-alpha.6](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.5...v0.1.0-alpha.6)

### Chores

* bump testing data uri ([#27](https://github.com/anthropics/anthropic-sdk-java/issues/27)) ([3261fbe](https://github.com/anthropics/anthropic-sdk-java/commit/3261fbe5818d21b3423873472243bccb899cb469))
* **docs:** add example project ([#25](https://github.com/anthropics/anthropic-sdk-java/issues/25)) ([9ba2956](https://github.com/anthropics/anthropic-sdk-java/commit/9ba2956a29bb23f00d2bb36fbf7563b552b56ede))
* **docs:** fix code block language ([#28](https://github.com/anthropics/anthropic-sdk-java/issues/28)) ([a686284](https://github.com/anthropics/anthropic-sdk-java/commit/a686284937af4e0c8f9b936899464c8e7c89f302))
* **docs:** update readme ([#24](https://github.com/anthropics/anthropic-sdk-java/issues/24)) ([d0891a2](https://github.com/anthropics/anthropic-sdk-java/commit/d0891a22de46658b1e82a3ebbe406f30ad69c387))
* format example ([729ca6a](https://github.com/anthropics/anthropic-sdk-java/commit/729ca6a49260477a2846d1d3d6b214499ef97e1d))
* update parameter examples in tests and docs ([#22](https://github.com/anthropics/anthropic-sdk-java/issues/22)) ([22fdc9c](https://github.com/anthropics/anthropic-sdk-java/commit/22fdc9c74f61fb6f634f2da28b0a6526e08652bd))


### Documentation

* add note about feedback ([79fc77b](https://github.com/anthropics/anthropic-sdk-java/commit/79fc77bb42a65585f8478e00736470f5008384fc))

## 0.1.0-alpha.5 (2024-12-17)

Full Changelog: [v0.1.0-alpha.4...v0.1.0-alpha.5](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.4...v0.1.0-alpha.5)

### Features

* **api:** general availability updates ([#21](https://github.com/anthropics/anthropic-sdk-java/issues/21)) ([a44228a](https://github.com/anthropics/anthropic-sdk-java/commit/a44228a42e6a591fe547cd1ea10eb8e2a98d9bf8))


### Chores

* **api:** update spec version ([#19](https://github.com/anthropics/anthropic-sdk-java/issues/19)) ([ecefc79](https://github.com/anthropics/anthropic-sdk-java/commit/ecefc796d89d2d1559d32729d801e46a8e4e271a))
* **internal:** remove unused and expand used wildcard imports ([#17](https://github.com/anthropics/anthropic-sdk-java/issues/17)) ([7a6d445](https://github.com/anthropics/anthropic-sdk-java/commit/7a6d445935d5f614b5dbb2755c0b339ad1879478))
* **internal:** remove unused and expand used wildcard imports ([#18](https://github.com/anthropics/anthropic-sdk-java/issues/18)) ([54bb13a](https://github.com/anthropics/anthropic-sdk-java/commit/54bb13af76d5628748be0d746c3dd43e3f9040f4))
* **internal:** remove unused imports ([#13](https://github.com/anthropics/anthropic-sdk-java/issues/13)) ([d7dcdbc](https://github.com/anthropics/anthropic-sdk-java/commit/d7dcdbc3b45e1942dc1cc9390bebd569321b788e))
* **internal:** update spec ([#20](https://github.com/anthropics/anthropic-sdk-java/issues/20)) ([bbe65de](https://github.com/anthropics/anthropic-sdk-java/commit/bbe65dece430c970d6e99ebf02b9dede955bfb0d))
* **test:** remove unused imports ([#15](https://github.com/anthropics/anthropic-sdk-java/issues/15)) ([9315104](https://github.com/anthropics/anthropic-sdk-java/commit/93151040179a609dfd362659e1d9c44a5d482339))
* **test:** use `JsonValue` instead of `JsonString` ([#16](https://github.com/anthropics/anthropic-sdk-java/issues/16)) ([13429e3](https://github.com/anthropics/anthropic-sdk-java/commit/13429e38d5a569e2f479283f24ebee08eecea88c))

## 0.1.0-alpha.4 (2024-12-11)

Full Changelog: [v0.1.0-alpha.3...v0.1.0-alpha.4](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.3...v0.1.0-alpha.4)

### Chores

* update example values in tests and docs ([#9](https://github.com/anthropics/anthropic-sdk-java/issues/9)) ([5f50cbb](https://github.com/anthropics/anthropic-sdk-java/commit/5f50cbb47bdec4c97f5839359735898a0b9d2857))


### Styles

* **internal:** make enum value definitions less verbose ([#11](https://github.com/anthropics/anthropic-sdk-java/issues/11)) ([cd0507b](https://github.com/anthropics/anthropic-sdk-java/commit/cd0507b1b6af02fb3c20ce9c4785f48a6de31af0))
* **internal:** move enum identity methods to bottom of class ([#12](https://github.com/anthropics/anthropic-sdk-java/issues/12)) ([804f3ec](https://github.com/anthropics/anthropic-sdk-java/commit/804f3ecac30aab1156d204df4d289a6553eb1a18))

## 0.1.0-alpha.3 (2024-12-10)

Full Changelog: [v0.1.0-alpha.2...v0.1.0-alpha.3](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.2...v0.1.0-alpha.3)

### Features

* docs: add example code ([#8](https://github.com/anthropics/anthropic-sdk-java/issues/8)) ([13e3a83](https://github.com/anthropics/anthropic-sdk-java/commit/13e3a83147fda14ff732481553d2e071487a453d))


### Documentation

* use latest sonnet in example snippets ([#6](https://github.com/anthropics/anthropic-sdk-java/issues/6)) ([a8f9175](https://github.com/anthropics/anthropic-sdk-java/commit/a8f91754a7ad26a8328a20dbbc4ee0051ae8aa06))

## 0.1.0-alpha.2 (2024-12-10)

Full Changelog: [v0.1.0-alpha.1...v0.1.0-alpha.2](https://github.com/anthropics/anthropic-sdk-java/compare/v0.1.0-alpha.1...v0.1.0-alpha.2)

### Chores

* **internal:** configure sonatype ([#4](https://github.com/anthropics/anthropic-sdk-java/issues/4)) ([d626be5](https://github.com/anthropics/anthropic-sdk-java/commit/d626be512503da86d9172cf1eb7ea952edeea120))


### Documentation

* add note about alpha to readme ([bda246c](https://github.com/anthropics/anthropic-sdk-java/commit/bda246c273b0c2308f15919f2ef45ee85e915a78))

## 0.1.0-alpha.1 (2024-12-09)

Full Changelog: [v0.0.1-alpha.0...v0.1.0-alpha.1](https://github.com/anthropics/anthropic-sdk-java/compare/v0.0.1-alpha.0...v0.1.0-alpha.1)

### Features

* **java:** initial commit ([#2](https://github.com/anthropics/anthropic-sdk-java/issues/2)) ([213302e](https://github.com/anthropics/anthropic-sdk-java/commit/213302eb35e90468977cb397df6bfe2d0c37ece5))
