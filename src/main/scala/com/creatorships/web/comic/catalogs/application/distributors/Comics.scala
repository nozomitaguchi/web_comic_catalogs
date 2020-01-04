package com.creatorships.web.comic.catalogs.application.distributors

import cats.Monad
import cats.implicits._
import cats.effect.Bracket
import com.creatorships.web.comic.catalogs.domain.provider.Provider.Comic
import doobie.util.transactor.Transactor

case class Comics(toSeq: Seq[Comic]) extends AnyVal {

  // TODO: impl
  def register[F[_]: Monad](xa: Transactor[F])(implicit bracket: Bracket[F, Throwable]): F[Unit] =
    toSeq.foreach(println).pure[F]

}
