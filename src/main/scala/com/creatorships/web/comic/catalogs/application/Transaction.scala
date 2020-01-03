package com.creatorships.web.comic.catalogs.application

import cats.effect.{Async, Blocker, ContextShift, Resource}
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts
import doobie.util.transactor.Transactor

object Transaction {

  def apply[F[_]: Async: ContextShift]: Resource[F, Transactor[F]] =
    for {
      connectEC <- ExecutionContexts.fixedThreadPool[F](100)
      transactEC <- ExecutionContexts.cachedThreadPool[F]
      transactor <- HikariTransactor.newHikariTransactor[F](
        "com.mysql.cj.jdbc.Driver",
        "jdbc:mysql://localhost:3307/web_comic",
        "root",
        "password",
        connectEC,
        Blocker.liftExecutionContext(transactEC)
      )
    } yield transactor

}
