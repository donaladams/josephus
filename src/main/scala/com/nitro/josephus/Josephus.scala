package com.nitro.josephus

import scala.collection.mutable

object Josephus {

  /**
    * Simple class representing input to the Josephus problem
 *
    * @param n the number of people in the circle
    * @param k the step size for killing
    * @param i the starting position in the circle, starting at 0
    */
  case class ProblemInput(n: Int, k: Int, i: Int = 0)

  /**
    * Function type of Solutions to the Josephus problem
    */
  type CountOut = ProblemInput => Int

  def apply(input: ProblemInput): Int = {
    val countOutStrategy: CountOut = strategy(input)
    countOutStrategy(input)
  }

  private[josephus] def strategy(problemInput: ProblemInput): CountOut = problemInput match {
    case ProblemInput(n, 1, i) => kEqualsOneSolution
    case ProblemInput(n, 2, i) => kEqualsTwoSolution
    case ProblemInput(n, k, i) => generalSolution
  }

  /**
    * O(n) dynamic programming solution for the general case
    */
  private[josephus] val generalSolution = new CountOut {
    override def apply(input: ProblemInput): Int = {
      def recur(nMinusOne: Int, n: Int): Int = (nMinusOne + input.k) % n
      val range = 2 to input.n
      val survivor = range.fold(0)(recur)
      withStartingPosition(input.n, input.i, survivor)
    }
  }

  /**
    * O(1) analytical solution for the case where k = 1
    */
  private[josephus] val kEqualsOneSolution = new CountOut {
    override def apply(input: ProblemInput): Int = input.n - 1
  }

  /**
    * O(1) analytical solution for the case where k = 2
    */
  private[josephus] val kEqualsTwoSolution = new CountOut {
    override def apply(input: ProblemInput): Int = {
      val l = input.n - Integer.highestOneBit(input.n)
      val survivor = 2 * l
      withStartingPosition(input.n, input.i, survivor)
    }
  }

  /**
    * O(n^2) procedural algorithm for the general case. This is used mainly for
    * verifying other approaches. According to the documentation for ListBuffer, the remove
    * method may be linear, which gives us the quadratic running time.
    */
  private[josephus] val simulatorSolution = new CountOut {
    override def apply(input: ProblemInput): Int = {
      val circle = mutable.ListBuffer.range(0, input.n)
      var position = input.i
      while (circle.size > 1) {
        position = (position + input.k - 1) % circle.size
        circle.remove(position)
      }
      circle.head
    }
  }

  /**
    * Helper function for transforming a solution where i=0 to one with an arbitrary starting position
    *
    * @param n the number of people in the circle
    * @param i the new starting position
    * @param originalSurvivor the solution where i=0
    * @return
    */
  private def withStartingPosition(n: Int, i: Int, originalSurvivor: Int): Int = (i + originalSurvivor) % n
}
