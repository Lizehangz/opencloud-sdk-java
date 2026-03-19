# Changelog

## 0.1.0-SNAPSHOT

- Added an OkHttp-based OpenCloud SDK scaffold for LibreGraph, OCS, and WebDAV.
- Added typed Graph resource APIs for `me`, `users`, `drives`, `groups`, `invitations`, `applications`, `education`, `tags`, and `activities`.
- Added strong typed POJO models and builder-style request objects for common OpenCloud resources.
- Added structured exception parsing for Graph OData errors and OCS meta errors.
- Added MockWebServer-based unit tests for Graph, OCS, exception parsing, and WebDAV.
- Added Maven Shade plugin configuration to build a dependency-inclusive `-all.jar`.
