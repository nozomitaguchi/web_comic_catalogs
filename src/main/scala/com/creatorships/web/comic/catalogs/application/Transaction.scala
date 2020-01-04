package com.creatorships.web.comic.catalogs.application

import cats.effect.{Async, Blocker, ContextShift, Resource}
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts
import doobie.util.transactor.Transactor
import io.circe.config.parser
import io.circe.generic.auto._

object Transaction {

  def apply[F[_]: Async: ContextShift]: Resource[F, Transactor[F]] =
    for {
      config <- Resource.liftF(parser.decodePathF[F, Database]("database"))
      connectEC <- ExecutionContexts.fixedThreadPool[F](config.size)
      transactEC <- ExecutionContexts.cachedThreadPool[F]
      transactor <- HikariTransactor.newHikariTransactor[F](
        config.driver,
        config.url,
        config.user,
        config.pass,
        connectEC,
        Blocker.liftExecutionContext(transactEC)
      )
    } yield transactor



}
