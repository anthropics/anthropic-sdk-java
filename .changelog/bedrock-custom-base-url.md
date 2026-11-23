feat(bedrock): support a custom base URL

`BedrockBackend` always sent requests to `https://bedrock-runtime.<region>.amazonaws.com`. Set `BedrockBackend.builder().baseUrl(...)`, or the `ANTHROPIC_BEDROCK_BASE_URL` environment variable with `fromEnv()`, to send them elsewhere, such as to a VPC interface endpoint. Requests are still signed for the configured region.
