package com.nitro.josephus

import com.nitro.josephus.Josephus.ProblemInput
import org.specs2.mutable._


/**
  * Unit tests for handling and validation of input arguments
  */
class JosephusSpec extends Specification {

  "Josephus(n, k)" should {

    // c. What happens if n = 3 and k = 2?
    "give 2 where n=3 and k=2" in {
      val input = ProblemInput(n = 3, k = 2)
      Josephus(input) must_== 2
    }

    // b. What happens if n = k?
    "give 0 where n=k=1" in {
      val input = ProblemInput(n = 1, k = 1)
      Josephus(input) must_== 0
    }

    // b. What happens if n = k?
    "give 0 where n=k=2" in {
      val input = ProblemInput(n = 2, k = 2)
      Josephus(input) must_== 0
    }

    // b. What happens if n = k?
    "give 1 where n=k=3" in {
      val input = ProblemInput(n = 3, k = 3)
      Josephus(input) must_== 1
    }

    // b. What happens if n = k?
    "give 1 where n=k=4" in {
      val input = ProblemInput(n = 4, k = 4)
      Josephus(input) must_== 1
    }

    // b. What happens if n = k?
    "give 1 where n=k=5" in {
      val input = ProblemInput(n = 5, k = 5)
      Josephus(input) must_== 1
    }

    // b. What happens if n = k?
    "give 3 where n=k=6" in {
      val input = ProblemInput(n = 6, k = 6)
      Josephus(input) must_== 3
    }

    // d. What happens if n is very large, but k =2?
    "give 475712 where n=500000 and k = 2 and agree with the general solution" in {
      val input = ProblemInput(n = 500000, k = 2)
      val survivor = Josephus(input)
      survivor must_== 475712
      Josephus.generalSolution(input) must_== survivor
    }

  }

  // Tests for offsetting starting position
  "Josephus(n, k, i)" should {

    "be equal to (Josephus(n, k, 0) + i) mod n where i=1" in {
      val i = 1
      val inputAtZero = ProblemInput(n = 10, k = 3, i = 0)
      val inputAtI = ProblemInput(n = 10, k = 3, i = i)
      Josephus(inputAtI) must_== (Josephus(inputAtZero) + i) % 10
    }

    "be equal to (Josephus(n, k, 0) + i) mod n where i=2" in {
      val i = 2
      val inputAtZero = ProblemInput(n = 10, k = 3, i = 0)
      val inputAtI = ProblemInput(n = 10, k = 3, i = i)
      Josephus(inputAtI) must_== (Josephus(inputAtZero) + i) % 10
    }

  }

   ///Tests for deciding which strategy to use based on input
  "Josepehus.strategy" should {

    "Provide the k=1 algorithm when ProblemInput.k=1" in {
      val input = ProblemInput(n = 100, k = 1, i = 3)
      Josephus.strategy(input) must_== Josephus.kEqualsOneSolution
    }

    "Provide k=2 algorithm when ProblemInput.k=2" in {
      val input = ProblemInput(n = 100, k = 2, i = 3)
      Josephus.strategy(input) must_== Josephus.kEqualsTwoSolution
    }

    "Provide the general algorithm when ProblemInput.k > 2" in {
      val input = ProblemInput(n = 100, k = 3, i = 3)
      Josephus.strategy(input) must_== Josephus.generalSolution
    }
  }

  // Check different strategies against each other to
  // verify that they are consistent
  "Different Josepehus strategies" should {

    "Agree when k=1" in {
      val inputs = inputRange(1 to 100, 1)
      val generalSolutions = inputs.map(Josephus.generalSolution)
      val kEqualsOneSolutions = inputs.map(Josephus.kEqualsOneSolution)
      val simulatorSolution = inputs.map(Josephus.simulatorSolution)
      generalSolutions must_== kEqualsOneSolutions
      generalSolutions must_== simulatorSolution
    }

    "Agree when k=2" in {
      val inputs = inputRange(1 to 100, 2)
      val generalSolutions = inputs.map(Josephus.generalSolution)
      val kEqualsTwoSolutions = inputs.map(Josephus.kEqualsTwoSolution)
      val simulatorSolution = inputs.map(Josephus.simulatorSolution)
      generalSolutions must_== kEqualsTwoSolutions
      generalSolutions must_== simulatorSolution
    }

    "Agree when k>=3" in {
      val kRange = 3 to 100
      val inputs = kRange.flatMap(inputRange(1 to 100, _))
      for {
        input <- inputs
        generalSolution = Josephus.generalSolution(input)
        simulatorSolution = Josephus.simulatorSolution(input)
      } yield generalSolution must_== simulatorSolution
    }
  }

  def inputRange(nRange: Range, k: Int, i: Int = 0): Seq[ProblemInput] =
    nRange.map(ProblemInput(_, k, i))
}
