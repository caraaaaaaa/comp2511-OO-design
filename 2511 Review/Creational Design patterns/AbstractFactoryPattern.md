# <center> Abstract Factory Pattern </center>

## Intent
Abstract Factory is a creational design pattern that lets you p**roduce families of related objects** without specifying their concrete classes.

## Problem
Imagine that you’re creating a furniture shop simulator. Your code consists of classes that

## represent
- A family of related products, say: Chair + Sofa + CoffeeTable.
- Several variants of this family.
- For example, products Chair + Sofa + CoffeeTable are available in these variants:

## Abstract Factory Pattern: Structure
1. Abstract Products declare interfaces for a set of distinct but related products which make up a product family.
2. Concrete Products are various implementations of abstract products, grouped by variants. Each abstract product
(chair/sofa) must be implemented in all given variants (Victorian/Modern).
3. The Abstract Factory interface declares a set of methods for creating each of the abstract products.
4. Concrete Factories implement creation methods of the abstract factory. Each concrete factory corresponds to a specific
variant of products and creates only those product variants.
5. The Client can work with any concrete factory/product variant, as long as it communicates with their objects via abstract
interfaces.