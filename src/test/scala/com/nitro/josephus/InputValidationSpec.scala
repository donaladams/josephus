package com.nitro.josephus

import com.nitro.josephus.Josephus.ProblemInput
import org.specs2.mutable.Specification
import scalaz._

class InputValidationSpec extends Specification {

  val goodInput = ProblemInput(n = 10, k = 2, i = 2)

  "InputValidation.validate" should {

    "return Success(input) when input is valid" in {
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(goodInput)
      result.isSuccess must beTrue
    }

    "return Failure when n=0" in {
      val badInput = goodInput.copy(n = 0)
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(badInput)
      result.isFailure must beTrue
    }

    "return Failure when k=0" in {
      val badInput = goodInput.copy(k = 0)
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(badInput)
      result.isFailure must beTrue
    }

    "return Failure when n<1" in {
      val badInput = goodInput.copy(n = -3)
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(badInput)
      result.isFailure must beTrue
    }

    "return Failure when k<1" in {
      val badInput = goodInput.copy(k = -4)
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(badInput)
      result.isFailure must beTrue
    }

    "return Failure when i<0" in {
      val badInput = goodInput.copy(i = -5)
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(badInput)
      result.isFailure must beTrue
    }

    "return Failure when i=n" in {
      val badInput = goodInput.copy(n = 6, i = 6)
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(badInput)
      result.isFailure must beTrue
    }

    "return Failure when i > n" in {
      val badInput = goodInput.copy(n = 6, i = 10)
      val result: ValidationNel[String, ProblemInput] = InputValidation.validate(badInput)
      result.isFailure must beTrue
    }

  }

  "Input.argsToProblemReport" should {

    "produce a ProblemInput when two ints are provided" in {
      val args = Array[String]("2", "2")
      val expected = ProblemInput(n = 2, k = 2, i = 0)
      val result: ValidationNel[String, ProblemInput] = InputValidation.argsToProblemInput(args)
      result.isSuccess must beTrue
      result.getOrElse(null) must_== expected
    }

    "produce a ProblemInput when three ints are provided" in {
      val args = Array[String]("2", "2", "1")
      val expected = ProblemInput(n = 2, k = 2, i = 1)
      val result: ValidationNel[String, ProblemInput] = InputValidation.argsToProblemInput(args)
      result.isSuccess must beTrue
      result.getOrElse(null) must_== expected
    }

    "return Failure when one int is provided" in {
      val args = Array[String]("2")
      val result: ValidationNel[String, ProblemInput] = InputValidation.argsToProblemInput(args)
      result.isFailure must beTrue
    }

    "return Failure when no args are provided" in {
      val args = Array[String]()
      val result: ValidationNel[String, ProblemInput] = InputValidation.argsToProblemInput(args)
      result.isFailure must beTrue
    }

    "return Failure when not all arguments are integers" in {
      val args = Array[String]("2", "Hey Joe!", "3")
      val result: ValidationNel[String, ProblemInput] = InputValidation.argsToProblemInput(args)
      result.isFailure must beTrue
    }

    "return Failure if too many arguments are provided" in {
      val args = Array[String]("2", "5", "3", "4")
      val result: ValidationNel[String, ProblemInput] = InputValidation.argsToProblemInput(args)
      result.isFailure must beTrue
    }

  }

}
