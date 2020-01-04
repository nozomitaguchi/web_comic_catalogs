package com.creatorships.web.comic.catalogs.application.distributors

import cats.Monad
import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze
import com.creatorships.web.comic.catalogs.domain.provider.Provider.Publisher

case class Publishers(toSeq: Seq[Publisher]) extends AnyVal {

  def scrape[F[_]: Monad](rules: analyze.rule.Comics): F[Comics] =
    (for {
      publisher <- toSeq.toList
      rule <- rules.findBy(publisher).toList
    } yield publisher.contents(rule).pure[F]).sequence.map(comicsList => Comics(comicsList.flatten))

  def add(other: Publishers): Publishers = Publishers(toSeq ++ other.toSeq)

}
