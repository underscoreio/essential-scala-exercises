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
    directors.filter(d => d.films.length >= numberOfFilms)

  def directorsBornBefore(directors: List[Director], year: Int): List[Director] =
    directors.filter(d => d.yearOfBirth < year)

  def directorsBornBeforeWithBackCatalogOfSize(directors: List[Director], year: Int, numberOfFilms: Int): List[Director] =
    directorsBornBefore(directorsWithBackCatalogOfSize(directors, numberOfFilms), year)

  def findDirectorWithName(directors: List[Director], lastName: String): Option[Director] =
    directors.find(_.lastName equalsIgnoreCase lastName)

  def findDirectorWithBackCatalogOfSize(directors: List[Director], numberOfFilms: Int): Option[Director] =
    directors.find(d => d.films.length >= numberOfFilms)

  def findDirectorBornBefore(directors: List[Director], year: Int): Option[Director] =
    directors.find(d => d.yearOfBirth < year)

  def findDirectorBornBeforeWithBackCatalogOfSize(directors: List[Director], year: Int, numberOfFilms: Int): Option[Director] =
    findDirectorBornBefore(directorsWithBackCatalogOfSize(directors, numberOfFilms), year)

  def allFilms(directors: List[Director]): List[Film] =
    directors.flatMap(d => d.films)

  def namesOfAllFilms(directors: List[Director]): List[String] =
    directors.flatMap(d => d.films.map(f => f.name))

  def allFilmsWithRatingOver(directors: List[Director], imdbRating: Double): List[Film] =
    directors.flatMap(d => d.films.filter(f => f.imdbRating > imdbRating))

  def allFilmsByDirector(directors: List[Director], lastName: String): List[Film] =
    findDirectorWithName(directors, lastName).fold[List[Film]](Nil)(d => d.films)

  def namesOfAllFilmsByDirector(directors: List[Director], lastName: String): List[String] =
    directors
      .find(d => d.lastName == lastName)
      .fold[List[String]](Nil)(d => d.films.map(f => f.name))

  def totalImdbRating(films: List[Film]): Double =
    films.foldLeft(0.0)((score, film) => score + film.imdbRating)

  def averageImdbRating(films: List[Film]): Double =
    if(films.isEmpty) 0.0 else totalImdbRating(films) / films.length

  def directorsSortedByAge(directors: List[Director], ascending: Boolean = true): List[Director] =
    directors.sortBy(
      if(ascending) {
        d => d.yearOfBirth
      } else {
        d => -d.yearOfBirth
      }
    )

  def directorWithHighestAverageImdbRating(directors: List[Director]): Option[Director] =
    directors.sortBy(d => -averageImdbRating(d.films)).headOption

  def earliestFilmByAnyDirector(directors: List[Director]): Option[Film] =
    allFilms(directors).sortWith((a, b) => a.yearOfRelease < b.yearOfRelease).headOption

  def earliestFilmsByAllDirectors(directors: List[Director]): Map[Director, Option[Film]] =
    directors.map(d => d -> earliestFilmByAnyDirector(List(d))).toMap

}
