package com.creatorships.web.comic.catalogs.infrastructure

import cats.Monad
import cats.effect.Bracket
import com.creatorships.web.comic.catalogs.application.distributors.{Coordinators, Publishers}
import com.creatorships.web.comic.catalogs.domain.provider.Provider.{Coordinator, Publisher}
import com.creatorships.web.comic.catalogs.infrastructure.analyze.url.DistributorType
import doobie.implicits._
import doobie.util.transactor.Transactor

case class DistributorsRepository[F[_]: Monad](transactor: Transactor[F])(implicit bracket: Bracket[F, Throwable]) {

  def findCoordinators: F[Coordinators] =
    selectBy(DistributorType.Coordinator).query[Coordinator].to[List].map(Coordinators).transact(transactor)

  def findPublishers: F[Publishers] =
    selectBy(DistributorType.Publisher).query[Publisher].to[List].map(Publishers).transact(transactor)

  private def selectBy(distributorType: DistributorType) =
    sql"""SELECT
         | id,
         | name,
         | url,
         | image_url,
         | content_path
         |FROM
         | distributors
         |WHERE
         | distributor_type = ${distributorType.toString};
       """.stripMargin

}
