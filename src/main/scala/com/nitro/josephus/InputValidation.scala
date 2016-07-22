package com.nitro.josephus

import com.nitro.josephus.Josephus.ProblemInput

import scala.util.Try

/**
  * Functions for handling and validating command line input
  */
object InputValidation {

  import scalaz._
  import Scalaz._

  /**
    * Convert the command line arguments into a ProblemInput
    *
    * @param args an array of strings representing the command line arguments
    * @return either the valid ProblemInput or a list of errors
    */
  def argsToProblemInput(args: Array[String]): ValidationNel[String, ProblemInput] = args.toList match {
    case n :: k :: tail if tail.size <= 1 =>
      val numberOfPeople: Int = parseIntOrInvalidValue(n)
      val stepSize: Int = parseIntOrInvalidValue(k)
      val startingPosition: Int = tail.headOption.map(parseIntOrInvalidValue(_)).getOrElse(0)
      val input = ProblemInput(numberOfPeople, stepSize, startingPosition)
      validate(input)
    case _ => "Incorrect number of arguments.".failureNel
  }

  /**
    * Try to convert a string to an integer. If this fails, return an invalid value for
    * a ProblemInput parameter
    * @param arg a string potentially representing an int
    * @return
    */
  private def parseIntOrInvalidValue(arg: String): Int = Try(arg.toInt).getOrElse(Int.MinValue)

  /**
    * Perform validation on the inputs using Scalaz's ValidationNel
    *
    * @param input the ProblemInput derived from the command line args
    * @return either the valid ProblemInput or a list of errors
    */
  private[josephus] def validate(input: ProblemInput): ValidationNel[String, ProblemInput] = {
    def validateN: ValidationNel[String, Int] =
      if (input.n >= 1) {
        input.n.successNel
      }
      else {
        "n must be an integer >= 1".failureNel
      }

    def validateK: ValidationNel[String, Int] =
      if (input.k >= 1) {
        input.k.successNel
      }
      else {
        "k must be an integer >= 1".failureNel
      }

    def validateI: ValidationNel[String, Int] =
      if (input.i >= 0 && input.i < input.n) {
        input.i.successNel
      }
      else {
        "i must be an integer in the range 0 <= i < n".failureNel
      }

    // If all validations pass, the valid Problem input is returned
    // If any fail, the errors are accumulated in a list and returned
    // Ugly but useful :)
    (validateN |@| validateK |@| validateI)(ProblemInput.apply)
  }
}
