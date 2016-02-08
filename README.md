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
in case your persistent mechanism (not only storage) remains the same. For example, you often don't need to change the
persistence provider in a customer project, but if you're going to build a software component that can be used across
different and arbitrary customer projects you're probably within the area of product, solution or framework development.

## Baseline Architecture

[site/img/overview.png]

## Target Architecture




- Differenciation between 'product' and 'solution' and 'project development'