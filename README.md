# spring-data-generic

## Terms and Definitions (shortened)

### Product

The product has it's own lifecycle, not bound to any customer needs. The requirements of a product are almost all collected
from the experience of former customer projects, but as well as from market analysis and demanded customer needs.

### Solution

The solution is often understood as customer project, but in this context we call a solution a piece of software that
builds on top of a product that has it's own product lifecycle but cannot run without the product underneath. Similar to
a product, a solution can be applied in arbitrary customer projects.

### Project

At the end, the project is the customer project that consists of products, solutions and custom code. It's only pursue is
to fulfill customer needs without the need for other projects.

## Why to wrap an abstraction?

Spring Data already tries to abstract from abstractions - like from JPA. Though usually you can go with Spring Data,
in case your persistent mechanism (not only storage) remains the same, for example, when you often don't need to change
the persistence provider in a customer project. But when you start building a software component that can be used across
different and arbitrary customer projects you probably end up in the area of product, solution or framework development.

And that's obviously where you need to abstract the abstraction. I don't like abstractions of a framework
at all, but this needs to be driven from a business perspective and not from a technical point of view.


## Target Architecture

Let's assume we would like to build an architecture like here. At top we have any kind of
business application that needs to fetch data from one or more data sources underneath. The kind of data, that needs to
be fetched is structured data containing binary documents and document metadata that comes from an arbitrary document
store. Several types of document store exist

- A DMS (Document Management System)
- A solution built with a NoSQL document store (like MongoDB) to store the document metadata and the document binaries
- A solution with a relational database that keeps the document metadata stored in tables

The latter two solutions may keep binary files on the filesystem or in the database. This depends on the current project
requirements.

![Overview]

### Abstract the ECM

A lot of commercial as well as open-source solutions exist in the area of `Document Management Systems`.
The DMS stores binary content on a proprietary file system along with metadata that's needed to retrieve the physical
content again. Each DMS has it's own interfaces exposed in several technologies, like webservices, Java API, RMI. So here
is our first level of abstraction (Integration Layer). An adapter implementation exists for all supported solutions:

- DMS Adapter for each supported DMS
- NoSQL Adapter for the MongoDB solution
- RDBMS Adapter for relational databases

An adapter has to implement a generic set of CRUD operation. The `Ameba Data DMS` component uses one or more adapter
implementations to provide high-level functionality to the business application.

### Building Dossiers of Documents

Building a structured dossier of documents is one of the high-level functions that `Ameba Data DMS` provides. What is
the key aspect of a structured dossier? Is it different to a set of documents? A dossier contains folders where each folder
may contain documents and where a document may exist in multiple folders. The dossier itself has metadata assigned, like
a folder or document has.

![RIM]

A dossier of documents can have multiple presentation. For example it may look like a flat list of documents, or as
tree-like structure.

## Component Design

### NoSQL Adapter

This component aims to use MongoDB as metadata and binary content store. It comes with a `Filesystem Adapter` as
`DocumentStore`. If a projects pulls the `Filesystem Adapter` onto the classpath, all documents were stored on the
filesystem, otherwise `GridFS Store` is used to store documents in MongoDB.

![nosql_adapter]

The adapter implementation uses Spring Data MongoDB to ease access to MongoDB.


[Overview]: site/img/overview.png
[RIM]: site/img/RIM.png
[nosql_adapter]: site/img/nosql_adapter.png


