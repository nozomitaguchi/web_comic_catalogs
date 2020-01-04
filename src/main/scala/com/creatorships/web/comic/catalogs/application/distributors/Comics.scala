package com.creatorships.web.comic.catalogs.application.distributors

import cats.Monad
import cats.effect.Bracket
import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.provider.Provider.Comic
import com.creatorships.web.comic.catalogs.infrastructure.ComicsRepository
import doobie.util.transactor.Transactor

case class Comics(toSeq: Seq[Comic]) extends AnyVal {

  def register[F[_]: Monad](xa: Transactor[F])(implicit bracket: Bracket[F, Throwable]): F[Unit] = {
    val comicsRepository = new ComicsRepository[F](xa)
    for {
      registered <- comicsRepository.findAll
      newSeries = notIncluded(registered)
      count <- comicsRepository.insert(newSeries)
    } yield { println(s"Insert $count comics") }
  }

  def distinct: Comics = Comics(toSeq.distinctBy(comic => (comic.distributorId, comic.name, comic.url)))

  def notIncluded(other: Comics): Comics = Comics(toSeq.filterNot(comic => other.toSeq.exists(_.isMatched(comic))))

  def tuples: List[(Int, String, String, String)] =
    toSeq
      .map(comic => (comic.distributorId.toInt, comic.name.toString, comic.url.toString, comic.image.toString))
      .toList

}
