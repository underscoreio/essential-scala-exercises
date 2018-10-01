package code

// Exercise:
//
// Complete the methods below
//
// The source code for Director and Film,
// and the FilmTestData used in the unit tests,
// are in the "utilities" project.

object FilmographyPart1 extends Exercise {

  def namesOfFilms(films: List[Film]): List[String] =
    films.map(film => film.name)

  def filmsReleasedAfter(films: List[Film], year: Int): List[Film] =
    films.filter(film => film.yearOfRelease > year)

  def namesOfFilmsReleasedAfter(films: List[Film], year: Int): List[String] =
    namesOfFilms(filmsReleasedAfter(films, year))
    // OR:
    //   films
    //     .filter(film => film.yearOfRelease > year)
    //     .map(film => film.name)

  def filmsByDirector(director: Director): List[Film] =
    director.films // trick question :)

  def namesOfFilmsByDirector(director: Director): List[String] =
    director.films.map(film => film.name)

  def directorsWithFilmsReleasedAfter(directors: List[Director], year: Int): List[Director] =
    directors.filter(director => filmsReleasedAfter(director.films, year).nonEmpty)

  // For convenience, here are some relevant methods
  // from Film, Director, and List:
  //
  // Film:
  //
  // Film.name                     => String
  // Film.yearOfRelease            => Int
  // Film.imdbRating               => Double
  //
  // Director:
  //
  // Director.firstName            => String
  // Director.lastName             => String
  // Director.name                 => String
  // Director.yearOfBirth          => Int
  // Director.films                => List[Film]
  //
  // List:
  //
  // List[A].head                  => A
  // List[A].tail                  => List[A]
  // List[A].length                => Int
  // List[A].isEmpty               => Boolean
  // List[A].nonEmpty              => Boolean
  //
  // List[A].exists(A => Boolean)  => Boolean
  // List[A].forall(A => Boolean)  => Boolean
  //
  // List[A].filter(A => Boolean)  => List[A]
  // List[A].map(A => B)           => List[B]
  //
  // List[A] ++ List[A]            => List[A]

}
