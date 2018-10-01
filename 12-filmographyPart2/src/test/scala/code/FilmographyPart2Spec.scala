package code

import org.scalatest._

class FilmographyPart2Spec extends FlatSpec with Matchers {
  import FilmographyPart2._
  import FilmTestData._

  "directorsWithBackCatalogOfSize" should "produce the right result" in {
    directorsWithBackCatalogOfSize(directors, 1) should contain theSameElementsAs List(eastwood, mcTiernan, nolan)
    directorsWithBackCatalogOfSize(directors, 4) should contain theSameElementsAs List(eastwood, mcTiernan)
    directorsWithBackCatalogOfSize(directors, 5) should contain theSameElementsAs List(eastwood)
  }

  "directorsBornBefore" should "produce the right result" in {
    directorsBornBefore(directors, 1930) should contain theSameElementsAs Nil
    directorsBornBefore(directors, 1931) should contain theSameElementsAs List(eastwood)
    directorsBornBefore(directors, 1951) should contain theSameElementsAs List(eastwood)
    directorsBornBefore(directors, 1952) should contain theSameElementsAs List(eastwood, mcTiernan)
  }

  "directorsBornBeforeWithBackCatalogOfSize" should "produce the right result" in {
    directorsBornBeforeWithBackCatalogOfSize(directors, 1931, 5) should contain theSameElementsAs List(eastwood)
    directorsBornBeforeWithBackCatalogOfSize(directors, 1931, 6) should contain theSameElementsAs Nil
    directorsBornBeforeWithBackCatalogOfSize(directors, 1930, 5) should contain theSameElementsAs Nil
  }

  "findDirectorWithName" should "produce the right result" in {
    findDirectorWithName(directors, "Nolan") should equal(Some(nolan))
    findDirectorWithName(directors, "NOLAN") should equal(Some(nolan))
    findDirectorWithName(directors, "Dijkstra") should equal(None)
  }

  "findDirectorWithBackCatalogOfSize" should "produce the right result" in {
    findDirectorWithBackCatalogOfSize(directors, 1) should contain oneElementOf List(eastwood, mcTiernan, nolan)
    findDirectorWithBackCatalogOfSize(directors, 4) should contain oneElementOf List(eastwood, mcTiernan)
    findDirectorWithBackCatalogOfSize(directors, 5) should contain oneElementOf List(eastwood)
    findDirectorWithBackCatalogOfSize(directors, 99) should equal(None)
  }

  "findDirectorBornBefore" should "produce the right result" in {
    findDirectorBornBefore(directors, 1930) should equal(None)
    findDirectorBornBefore(directors, 1931) should contain oneElementOf List(eastwood)
    findDirectorBornBefore(directors, 1951) should contain oneElementOf List(eastwood)
    findDirectorBornBefore(directors, 1952) should contain oneElementOf List(eastwood, mcTiernan)
  }

  "findDirectorBornBeforeWithBackCatalogOfSize" should "produce the right result" in {
    findDirectorBornBeforeWithBackCatalogOfSize(directors, 1931, 5) should equal(Some(eastwood))
    findDirectorBornBeforeWithBackCatalogOfSize(directors, 1931, 6) should equal(None)
    findDirectorBornBeforeWithBackCatalogOfSize(directors, 1930, 5) should equal(None)
  }

  "allFilms" should "produce the right result" in {
    allFilms(directors) should contain theSameElementsAs films
  }

  "namesOfAllFilms" should "produce the right result" in {
    namesOfAllFilms(directors) should contain theSameElementsAs films.map(_.name)
  }

  "allFilmsWithRatingOver" should "produce the right result" in {
    allFilmsWithRatingOver(directors, 8.0) should contain theSameElementsAs List(
      memento,
      darkKnight,
      inception,
      unforgiven,
      granTorino,
      dieHard,
    )

    allFilmsWithRatingOver(directors, 8.5) should contain theSameElementsAs List(
      darkKnight,
      inception,
    )
  }

  "allFilmsByDirector" should "produce the right result" in {
    allFilmsByDirector(directors, "NoLaN") should contain theSameElementsAs List(
      memento,
      darkKnight,
      inception,
    )

    allFilmsByDirector(directors, "Some Guy") should equal(Nil)
  }

  "totalImdbRating" should "produce the right result" in {
    totalImdbRating(nolan.films) should equal(26.3 +- 0.001)
    totalImdbRating(mcTiernan.films) should equal(30.6 +- 0.001)
    totalImdbRating(someGuy.films) should equal(0.0 +- 0.001)
  }

  "averageImdbRating" should "produce the right result" in {
    averageImdbRating(nolan.films) should equal(8.767 +- 0.001)
    averageImdbRating(mcTiernan.films) should equal(7.65 +- 0.001)
    averageImdbRating(someGuy.films) should equal(0.0 +- 0.001)
  }

  "directorsSortedByAge" should "produce the right result" in {
    directorsSortedByAge(directors, true) should equal(List(eastwood, mcTiernan, nolan, someGuy))
    directorsSortedByAge(directors, false) should equal(List(eastwood, mcTiernan, nolan, someGuy).reverse)
  }

  "directorWithHighestAverageImdbRating" should "produce the right result" in {
    directorWithHighestAverageImdbRating(directors) should equal(Some(nolan))
    directorWithHighestAverageImdbRating(List(eastwood, mcTiernan)) should equal(Some(eastwood))
    directorWithHighestAverageImdbRating(List(someGuy)) should equal(Some(someGuy))
    directorWithHighestAverageImdbRating(Nil) should equal(None)
  }

  "earliestFilmByAnyDirector" should "produce the right result" in {
    earliestFilmByAnyDirector(directors) should equal(Some(highPlainsDrifter))
  }

  "earliestFilmsByAllDirectors" should "produce the right result" in {
    earliestFilmsByAllDirectors(directors) should equal(Map(
      eastwood  -> Some(highPlainsDrifter),
      mcTiernan -> Some(predator),
      nolan     -> Some(memento),
      someGuy   -> None
    ))
  }
}