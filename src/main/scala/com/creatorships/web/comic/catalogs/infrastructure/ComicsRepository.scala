package com.creatorships.web.comic.catalogs.infrastructure

import cats.Monad
import cats.effect.Bracket
import cats.implicits._
import com.creatorships.web.comic.catalogs.application.distributors.Comics
import com.creatorships.web.comic.catalogs.domain.provider.Provider.Comic
import doobie.implicits._
import doobie.util.fragment.Fragment
import doobie.util.transactor.Transactor
import doobie.util.update.Update

case class ComicsRepository[F[_]: Monad](xa: Transactor[F])(implicit bracket: Bracket[F, Throwable]) {

  type Record = (Int, String, String, String)

  def findAll: F[Comics] = selectAll.query[Comic].to[List].map(Comics.apply).transact(xa)

  def insert(comics: Comics): F[Int] = insertAll(comics.distinct).transact(xa)

  private def selectAll: Fragment =
    sql"""SELECT
         | distributor_id,
         | name,
         | url,
         | image_url
         |FROM
         | comics;
       """.stripMargin

  private def insertAll(comics: Comics): doobie.ConnectionIO[Int] = {
    val sql = "INSERT INTO comics (distributor_id, name, url, image_url) VALUES (?, ?, ?, ?);"
    Update[Record](sql).updateMany(comics.tuples)
  }

}
