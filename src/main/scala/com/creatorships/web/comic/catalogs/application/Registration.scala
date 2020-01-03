package com.creatorships.web.comic.catalogs.application

import cats.Monad
import cats.effect.Bracket
import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.provider.Comics
import doobie.util.transactor.Transactor

object Registration {

  // TODO: implement
  def apply[F[_]: Monad](comics: Comics)(xa: Transactor[F])(implicit bracket: Bracket[F, Throwable]): F[Unit] =
    comics.toSeq.foreach(println).pure[F]

}
