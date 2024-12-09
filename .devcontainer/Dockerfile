# syntax=docker/dockerfile:1
FROM debian:bookworm-slim

RUN apt-get update && apt-get install -y --no-install-recommends \
  libxkbcommon0 \
  ca-certificates \
  ca-certificates-java \
  make \
  curl \
  git \
  openjdk-17-jdk-headless \
  unzip \
  libc++1 \
  vim \
  && apt-get clean autoclean

# Ensure UTF-8 encoding
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

WORKDIR /workspace

COPY . /workspace
