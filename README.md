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

And thats obviously the case to build another abstraction over an abstraction. I don't like abstractions of a framework
at all, but this has to be decided from a business value view and not from a technical view point.


## Demanded Architecture

Let's assume we would build an architecture like shown in the next overview picture. At top we have any kind of
business application that needs to fetch data from one or more underlying data sources. The kind of data, that needs to
be fetched is not of data coming from a relational database but more as of coming from a document store whereas
a DMS (Document Management System) takes care of storing and finding data. So this is the first layer of abstraction:
Instead of abstracting from a database we need to abstract from data sources. I could have invented a project called
`Spring Data DMS` with several sub-projects but this is: a) not my job and b) not my hobby.

Therefor we call this project 'Ameba Data DMS' instead of 'Spring Data DMS'.

Customers need to...

### Abstract from ECM

... Systems. A lot of commercial as well as open-source solutions exist in the area of `Document Management Systems`s.
An DMS stores binary content of a proprietary file system along with metadata needed to find and get back the physical
 content.

### Build Dossiers from

![Overview]



## Target Architecture


[Overview]: site/img/overview.png


