package sandbox
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._
import java.util.Date
import cats.Show

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

}
