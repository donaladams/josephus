For this assignment, we would like you to implement code that solves a counting out problem. In these problems, you are in a circle. From some starting point, X you count k people. If you are standing in that kth spot, you are ‘out’. The counting continues until there is one person standing. A concrete, but morbid, example of this is the Josephus Problem (https://en.wikipedia.org/wiki/Josephus_problem).

For this exercise...

Create an application that takes in 3 parameters: the number of people in the circle (n), the step rate (k), and the starting position (i).  For example, if k is the step rate and you are starting at i, then you skip I + k-1 people and eliminate the kth person.  The output of the program should be the place you need to stand in the circle to be the last person left.

The assignment must...

1. Use Scala or Java
2. Build and run using SBT (http://www.scala-sbt.org/).  After submission, we should be able to run sbt test and all tests should pass.  Also, we will execute the application using sbt “run n k” (where n and k are the parameters).
3. Be submitted via Github
4. Have a readme.md explaining how to run the application (simple, no need to be overly verbose)
5. Have tests associated with the assignment.  One potential framework you can use is Specs2 (https://etorreborre.github.io/specs2/), but feel free to use any test framework you want.  Make sure your tests cover the following scenarios
    a. Invalid parameters
    b. What happens if n = k?
    c. What happens if n = 3 and k = 2?
    d. What happens if n is very large, but k =2?
    e. Choose a couple of other cases to test

Once completed, please submit the link to the Github repo.
