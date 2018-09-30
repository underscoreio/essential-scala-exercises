package code

import org.scalatest._

class FilmographyPart1Spec extends FlatSpec with Matchers {
  import FilmographyPart1._
  import FilmTestData._

  "namesOfFilms" should "do something sensible" in {
    namesOfFilms(films) should contain theSameElementsAs List(
      "Predator",
      "Die Hard",
      "The Hunt for Red October",
      "The Thomas Crown Affair",
      "Memento",
      "Dark Knight",
      "Inception",
      "High Plains Drifter",
      "The Outlaw Josey Wales",
      "Unforgiven",
      "Gran Torino",
      "Invictus"
    )
  }

  "filmsReleasedAfter" should "do something sensible" in {
    filmsReleasedAfter(films, 1995) should contain theSameElementsAs List(
      thomasCrownAffair,
      memento,
      darkKnight,
      inception,
      granTorino,
      invictus,
    )
  }

  "namesOfFilmsReleasedAfter" should "do something sensible" in {
    namesOfFilmsReleasedAfter(films, 1995) should contain theSameElementsAs List(
      "The Thomas Crown Affair",
      "Memento",
      "Dark Knight",
      "Inception",
      "Gran Torino",
      "Invictus"
    )
  }

  "filmsByDirector" should "do something sensible" in {
    filmsByDirector(eastwood) should contain theSameElementsAs List(
      highPlainsDrifter,
      outlawJoseyWales,
      unforgiven,
      granTorino,
      invictus,
    )

    filmsByDirector(someGuy) should equal(Nil)
  }

  "namesOfFilmsByDirector" should "do something sensible" in {
    namesOfFilmsByDirector(eastwood) should contain theSameElementsAs List(
      "High Plains Drifter",
      "The Outlaw Josey Wales",
      "Unforgiven",
      "Gran Torino",
      "Invictus")
  }

  "directorsWithFilmsReleasedAfter" should "do something sensible" in {
    directorsWithFilmsReleasedAfter(directors, 2005) should contain theSameElementsAs List(
      nolan,
      eastwood
    )

    directorsWithFilmsReleasedAfter(directors, 2009) should equal(List(nolan))
  }
}
