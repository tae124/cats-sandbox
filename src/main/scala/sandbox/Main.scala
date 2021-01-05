package sandbox
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.show._
import cats.syntax.eq._
import cats.syntax.option._
import java.util.Date
import cats.{ Show, Eq }

object Main extends App {

  // cats built-in
  val shownInt = 123.show
  val shownString = "abc".show

  // custom instances
  /*
  implicit val dateShow: Show[Date] =
    new Show[Date] {
      def show(date: Date): String =
        s"${date.getTime}ms since the epoch."
    }
  */
  // use Show's constructor
  implicit val dateShow: Show[Date] =
    Show.show(date => s"${date.getTime}ms since the epoch.")

  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat.")

  val shownDate = new Date().show

  val shownCat = Cat("Mike", 10, "White").show
  println(shownInt)
  println(shownString)
  println(shownDate)
  println(shownCat)

  // not use Eq
  //val myList = List(1, 2, 3).map(Option(_)).filter(item => item == 1) -> compile error

  // use Eq
  val eqInt = Eq[Int]
  println(eqInt.eqv(123, 123))
  println(eqInt.eqv(123, 234))
  //println(eqInt.eqv(123, "123")) -> compile error

  // use Eq syntax
  println(123 === 123)
  println(123 === 234)
  println(123 =!= 234)
  //println(123 === "123") -> compile error

  // use Eq syntax for Option
  println(Option(1) === None)
  println(Option(1) === Option.empty[Int])
  println(1.some === Option(1))
  println(1.some =!= none[Int])

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
        cat1.name === cat2.name &&
        cat1.age === cat2.age &&
        cat1.color === cat2.color
    }

  val eqCat = Eq[Cat]
  println(eqCat.eqv(cat1, cat2))
  println(cat1 === cat2)
  println(cat1 =!= cat2)

  // wrap option
  println(Option(cat1) === None) // false
  println(Option(cat1) === Option(cat2)) // false
  println(cat1.some === Option(cat1)) // true
  println(cat1.some =!= none[Cat]) //true

  // covariance(共変)


}
