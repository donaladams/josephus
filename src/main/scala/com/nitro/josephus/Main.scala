package com.nitro.josephus

import com.nitro.josephus.InputValidation._
import com.nitro.josephus.Josephus.ProblemInput

import scalaz.Scalaz._
import scalaz._

object Main extends App {

  val validatedInput: ValidationNel[String, ProblemInput] = argsToProblemInput(args)
  val result: String = validatedInput match {
    case Success(p) =>
      val survivor = Josephus(p)
      survivor.toString
    case Failure(f: NonEmptyList[String]) => toErrorMessage(f, args)
  }
  println(result)

  /**
    * Construct an error message to report to the user
    * @param validationErrors error messages from validation
    * @param args the command line args used
    * @return an error message string
    */
  def toErrorMessage(validationErrors: NonEmptyList[String], args: Array[String]): String = {
    val errors = validationErrors.toList mkString "\n "
    val given = args.toList mkString " "
    val errorMessage =
      s"""
         |Usage: sbt run n k [i]
         | n: the number of people in the circle. n>=1.
         | k: the skip size. k>=1.
         | i: the zero-indexed starting position. 0<=i<n.
         |
         |Errors:
         | $errors
         |
         |Given: sbt run $given
      """.stripMargin

    errorMessage
  }

}
