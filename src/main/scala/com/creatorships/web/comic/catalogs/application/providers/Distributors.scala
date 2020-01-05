package com.creatorships.web.comic.catalogs.application.providers

import cats.Monad
import cats.effect.Bracket
import cats.implicits._
import com.creatorships.web.comic.catalogs.application.providers
import com.creatorships.web.comic.catalogs.application.providers.distributors.{Coordinators, Publishers}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRules
import com.creatorships.web.comic.catalogs.infrastructure.{AnalyzeRulesRepository, DistributorsRepository}
import doobie.util.transactor.Transactor

case class Distributors(coordinators: Coordinators, publishers: Publishers, analyzeRules: AnalyzeRules) {

  def scrape[F[_]: Monad]: F[Comics] =
    for {
      publishersFromCoordinator <- coordinators.scrape[F](analyzeRules.publishers)
      publishersAll = publishers.add(publishersFromCoordinator)
      comics <- publishersAll.scrape[F](analyzeRules.comics)
    } yield comics

}

object Distributors {

  def fetch[F[_]: Monad](xa: Transactor[F])(implicit bracket: Bracket[F, Throwable]): F[Distributors] = {
    val distributorRepository = DistributorsRepository[F](xa)
    val analyzeRulesRepository = AnalyzeRulesRepository[F](xa)
    for {
      coordinators <- distributorRepository.findCoordinators
      publishers <- distributorRepository.findPublishers
      analyzeRules <- analyzeRulesRepository.findAll
    } yield {
      providers.Distributors(coordinators, publishers, analyzeRules)
    }
  }

}
