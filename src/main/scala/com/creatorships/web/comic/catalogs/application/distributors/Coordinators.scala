package com.creatorships.web.comic.catalogs.application.distributors

import cats.Monad
import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze
import com.creatorships.web.comic.catalogs.domain.provider.Provider.Coordinator

case class Coordinators(toSeq: Seq[Coordinator]) extends AnyVal {

  def scrape[F[_]: Monad](rules: analyze.rule.Publishers): F[Publishers] =
    (for {
      coordinator <- toSeq.toList
      rule <- rules.findBy(coordinator).toList
      publisher <- coordinator.contents(rule)
    } yield publisher.pure[F]).sequence.map(Publishers)

}
