[![Build Status](https://travis-ci.org/epileptic-fish/josephus.svg?branch=master)](https://travis-ci.org/epileptic-fish/josephus)

# Josephus

A command-line application for solving the Josephus Problem.

# Usage

The application can be run using SBT and takes three parameters:

* `n` - the number of people in the circle. n>=1.
* `k` - the step size. k>=1.
* `i` - the starting position. This is zero-indexed and optional, defaulting to 0. 0<=i<n

It can be run as follows:

```sh
sbt run n k i
```

Here is a concrete example, where the circle has ten people, the step size is three and the starting is two:

```sh
sbt run 10 3 2
```

# Tests

SBT is also used to run the tests, as follows:

```sh
sbt test
```
