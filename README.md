# Relational Database Loader

![GitHub Release](https://img.shields.io/github/v/release/opensnowcat/opensnowcat-rdb-loader?link=https%3A%2F%2Fgithub.com%2Fopensnowcat%2Fopensnowcat-rdb-loader%2Freleases)
[![main](https://github.com/opensnowcat/opensnowcat-rdb-loader/actions/workflows/test.yml/badge.svg?branch=main)](https://github.com/opensnowcat/opensnowcat-rdb-loader/actions/workflows/ci.yml)
[![License][license-image]][license]


## Introduction

The OpenSnowcat RDB loader is an open-source fork of Snowplow RDB Loader following the license changes in 2023 and early 2024. This project contains applications required to load Snowplow data into various data warehouses.

It consists of two types of applications: Transformers and Loaders

### Transformers

Transformers read Snowplow enriched events, transform them to a format ready to be loaded to a data warehouse, then write them to respective blob storage.

There are two types of Transformers: Batch and Streaming

#### Stream Transformer

Stream Transformers read enriched events from respective stream service, transform them, then write transformed events to specified blob storage path.
They write transformed events in periodic windows.

There are two different Stream Transformer applications: Transformer Kinesis and Transformer Pubsub. As one can predict, they are different variants for GCP and AWS.


#### Batch Transformer

It is a [Spark][spark] job. It only works with AWS services. It reads enriched events from a given S3 path, transforms them, then writes transformed events to a specified S3 path.


### Loaders

Transformers send a message to a message queue after they are finished with transforming some batch and writing it to blob storage.
This message contains information about transformed data such as where it is stored and what it looks like.

Loaders subscribe to the message queue. After a message is received, it is parsed, and necessary bits are extracted to load transformed events to the destination.
Loaders construct necessary SQL statements to load transformed events then they send these SQL statements to the specified destination.

At the moment, we have loader applications for Redshift, Databricks and Snowflake.

## Copyright and License

Copyright OpenSnowcat Contributors. See [NOTICE](NOTICE.txt) for details.

Licensed under the **[Apache License, Version 2.0][license]** (the "License");
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Trademark

OpenSnowcat includes certain Apache-licensed Snowplow code from Snowplow Ltd. and other source code. Snowplow Ltd. is not the source of that other source code. SNOWPLOW is a registered trademark of Snowplow Ltd.

[license-image]: http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat
[license]: http://www.apache.org/licenses/LICENSE-2.0