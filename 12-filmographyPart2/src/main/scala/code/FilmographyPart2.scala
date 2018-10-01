package code

// Exercise:
//
// Complete the methods below
//
// The source code for Director and Film,
// and the FilmTestData used in the unit tests,
// are in the "utilities" project.

object FilmographyPart2 extends Exercise {

  def directorsWithBackCatalogOfSize(directors: List[Director], numberOfFilms: Int): List[Director] =
    ???

  def directorsBornBefore(directors: List[Director], year: Int): List[Director] =
    ???

  def directorsBornBeforeWithBackCatalogOfSize(directors: List[Director], year: Int, numberOfFilms: Int): List[Director] =
    ???

  def findDirectorWithName(directors: List[Director], lastName: String): Option[Director] =
    ???

  def findDirectorWithBackCatalogOfSize(directors: List[Director], numberOfFilms: Int): Option[Director] =
    ???

  def findDirectorBornBefore(directors: List[Director], year: Int): Option[Director] =
    ???

  def findDirectorBornBeforeWithBackCatalogOfSize(directors: List[Director], year: Int, numberOfFilms: Int): Option[Director] =
    ???

  def allFilms(directors: List[Director]): List[Film] =
    ???

  def namesOfAllFilms(directors: List[Director]): List[String] =
    ???

  def allFilmsWithRatingOver(directors: List[Director], imdbRating: Double): List[Film] =
    ???

  def allFilmsByDirector(directors: List[Director], lastName: String): List[Film] =
    ???

  def namesOfAllFilmsByDirector(directors: List[Director], lastName: String): List[String] =
    ???

  def totalImdbRating(films: List[Film]): Double =
    ???

  def averageImdbRating(films: List[Film]): Double =
    ???

  def directorsSortedByAge(directors: List[Director], ascending: Boolean = true): List[Director] =
    ???

  def directorWithHighestAverageImdbRating(directors: List[Director]): Option[Director] =
    ???

  def earliestFilmByAnyDirector(directors: List[Director]): Option[Film] =
    ???

  def earliestFilmsByAllDirectors(directors: List[Director]): Map[Director, Option[Film]] =
    ???

}
