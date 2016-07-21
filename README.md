# Count Out

A command-line application for solving the Josephus Problem.

# Usage

The application can be run using SBT and takes three parameters:

* `n` - the number of people in the circle
* `k` - the step size
* `i` - the starting position. This is zero-indexed and optional, defaulting to 0.

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
