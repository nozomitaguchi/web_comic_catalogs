package com.creatorships.web.comic.catalogs.infrastructure

import cats.Monad
import cats.effect.Bracket
import com.creatorships.web.comic.catalogs.application.providers.distributors.{Coordinators, Publishers}
import com.creatorships.web.comic.catalogs.domain.provider.Provider.{Coordinator, Publisher}
import com.creatorships.web.comic.catalogs.infrastructure.analyze.rule.DistributorType
import doobie.implicits._
import doobie.util.fragment.Fragment
import doobie.util.transactor.Transactor

case class DistributorsRepository[F[_]: Monad](xa: Transactor[F])(implicit bracket: Bracket[F, Throwable]) {

  def findCoordinators: F[Coordinators] =
    selectBy(DistributorType.Coordinator).query[Coordinator].to[List].map(Coordinators).transact(xa)

  def findPublishers: F[Publishers] =
    selectBy(DistributorType.Publisher).query[Publisher].to[List].map(Publishers).transact(xa)

  private def selectBy(distributorType: DistributorType): Fragment =
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
