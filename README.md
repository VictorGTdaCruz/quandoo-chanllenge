# Quandoo-Challenge

This is a basic project, created as a technical task at Quandoo.

## Overview

This project assumes the following development settings:

- Android Studio `3.3.x` (Latest Stable)
- Kotlin Plugin `1.3.x` (Latest Stable)
- MinSDK = `19` and TargetSDK = `28` 

## Setup

The project should be ready to be executed after cloned.

## Knowledge Stack

The project uses some concepts and tecnologies below

* Kotlin
* Android SDK
* Dependency Injection using [Kodein](http://kodein.org/Kodein-DI/)
* Multi Module 
* Business components
* Generic Adapters Pattern
* Separation of concerns

The goal of multi-module build is to strongly improve cacheable tasks as well parallel tasks execution.

The views present in the project's activities were implemented as custom views. These custom views have it's own viewmodels that contains the business logic. The approach was chosen to ilustrate how components can be attached to a activity and reused easily, anywhere. This could help build new apps or organize current apps fast, using the already coded business rules. A new component dedicated project could be built and imported in many company's applications to avoid possible misscoded business rules and have a unified design.