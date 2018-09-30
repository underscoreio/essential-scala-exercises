package code

trait Exercise {
  private def exerciseName: String =
    getClass.getSimpleName.filterNot(_ == '$')

  println(
    s"""
    |Started $exerciseName
    |""".trim.stripMargin
  )

  def main(args: Array[String]): Unit =
    println(
      s"""
      |
      |Finished $exerciseName
      |""".trim.stripMargin
    )
}
