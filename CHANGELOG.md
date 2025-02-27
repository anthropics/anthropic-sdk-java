# Changelog

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
