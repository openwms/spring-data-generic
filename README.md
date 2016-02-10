# spring-data-generic

## Terms and Definitions (shortened)

### Product

The product has it's own lifecycle and is designed to fulfill common required customer demands. The requirements of a
product are almost all collected from the experience of former customer projects, but as well as from market analysis.

### Solution

The solution is often understood as customer project, but in this context we call a solution a software component that
is built on top of a product, has it's own lifecycle but cannot run without the product underneath. Similar to a product
, a solution can be applied in arbitrary customer projects.

### Project

The project is the customer project that consists of products, solutions and custom code. It's purpose is to fulfill
current customer requirements. It is dedicated for that specific need and is not intended to be used for other customers
.

## Why to wrap an abstraction?

Spring Data already tries to abstract from abstractions - like from JPA. Though usually you're fine with Spring Data,
because your persistence mechanism will not change too often. It is very unlikely that you have to change the persistence
provider (Hibernate, Eclipselink) in a customer project. But when you start building a software component that can be
used across arbitrary customer projects you probably end up in the area of product, solution or framework
development. In this case the JPA persistence provider may change or even better the whole persistence mechanism
may change (i.e. from relational to NoSQL databases).

And that's obviously where you need to abstract from an abstraction. I'm not a friend of framework abstractions,
but this needs to be seens from a business perspective and not from a technical point of view.

So what are we going to build here? In the following example we've a `DocumentServiceImpl` (a Spring managed Service) that
acts on `ServiceOperations`. In addition we could have `FindOperations` or `DeleteOperations` as well. Two possible implementations
of `SaveOperations` exist, both called `Save`, but put in different packages. Each `Save` interface implements the methods
of `SaveOperations` with Java 8 default implementations and delegates to the concrete repository. Looks like a lightweight
Generic DAO so far. An implementing class of `Save` is only responsible to provide the Spring Data repository needed to
perform the actions within the default methods.

![generic_dao]

Putting the Spring Data interface (`MongoDocumentRepository` and `JpaDocumentRepository`) inside the implementing class
of `Save` eliminates the need for additional class files. For each entity class we end up with just one custom repository
implementation that inherits the Generic DAO functionality.

## Target Architecture

Let's assume we would like to build an architecture like shown beneath. At top we have any kind of
business application that needs to fetch data from one or more data sources underneath. The kind of data, that needs to
be fetched is structured data containing binary documents and document metadata that comes from an arbitrary document
store. Several types of document store exist

- A DMS (Document Management System)
- A solution built with a NoSQL document store (like MongoDB) to store the document metadata and the document binaries
- A solution with a relational database that keeps the document metadata stored in tables

The latter two solutions may keep binary files on the filesystem or in the database. This depends on the project
requirements.

![Overview]

### Abstract the ECM

A lot of commercial as well as open-source solutions exist in the area of `Document Management Systems`.
The DMS stores binary content on a proprietary file system along with metadata that's needed to look up and retrieve the
physical content again. Each DMS has it's own interfaces exposed in several technologies, like webservices, Java API,
RMI. So here is our first level of abstraction (Integration Layer). An adapter implementation exists for all supported
solutions:

- DMS Adapter for every supported DMS
- NoSQL Adapter for the MongoDB solution
- RDBMS Adapter for relational databases

An adapter has to implement a generic set of CRUD operation. The `Interface21 Data DMS` component uses one or more adapter
implementations to provide high-level functionality to the business application. Saving and removing data in a DMS is
often straight forward and similar between the different DMS - but querying for document metadata is not. Every DMS has
it's own query language defined, so we'll need to build yet another query language above. Nevertheless a `#findBy(ID)`
and `#findAll()` are easy to implement for all of them.

### Building Dossiers of Documents

Building a structured dossier of documents is one of the high-level functions that `Interface21 Data DMS` provides. What is
the key aspect of a structured dossier? Is it different to a set of documents? A dossier contains folders where each folder
may contain documents and where a document may exist in multiple folders. The dossier itself has metadata assigned, like
a folder or document has.

![RIM]

A dossier of documents can have multiple presentation. For example it may look like a flat list of documents, or as
tree-like structure.

## Component Design

### NoSQL Adapter

This component aims to use MongoDB as metadata and binary content store. It comes with a `Filesystem Adapter` as
`DocumentStore`. If a projects pulls in the `Filesystem Adapter` onto the classpath, all documents were stored on the
filesystem, otherwise `GridFS Store` is used to store the binary document content in MongoDB.

![nosql_adapter]

The adapter implementation uses Spring Data MongoDB to ease access to MongoDB.


[Overview]: site/img/overview.png
[RIM]: site/img/RIM.png
[nosql_adapter]: site/img/nosql_adapter.png
[generic_dao]: site/img/generic_dao.png


