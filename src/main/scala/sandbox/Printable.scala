package sandbox


trait Printable[A] {

  def format(value: A): String

}

object Printable {
  def format[A](input: A)(implicit p: Printable[A]): String = p.format(input)
  def print[A](input: A)(implicit p: Printable[A]): Unit = println(p.format(input))
}

object PrinterInstances {

  implicit val stringPrintable: Printable[String] = {
    new Printable[String] {
      def format(input: String): String = s"formatted:$input"
    }
  }

  implicit val intPrintable: Printable[Int] = {
    new Printable[Int] {
      def format(input: Int): String = s"formatted:${input.toString}"
    }
  }

  implicit val catPrintable: Printable[Cat] = {
    new Printable[Cat] {
      override def format(value: Cat): String = s"${value.name} is a ${value.age.toString} year-old ${value.color} cat."
    }
  }

}

object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def toFormat(implicit p: Printable[A]) = p.format(value)
    def toPrint(implicit p: Printable[A]) = println(toFormat(p))
  }

}
