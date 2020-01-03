package com.creatorships.web.comic.catalogs.domain.analyze.rule

import cats.data.Validated.{Invalid, Valid}
import cats.data.{Validated, ValidatedNel}
import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.{Catalog, Name, Url}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError
import com.creatorships.web.comic.catalogs.domain.provider.Provider
import com.creatorships.web.comic.catalogs.domain.provider.columns.{Id, Path}
import net.ruippeixotog.scalascraper.model.{Document, Element}

sealed trait AnalyzeRule[T] {

  val id: Id

  val catalog: Catalog

  val name: Name

  val url: Url

  val image: Url

  def apply(document: Document): Seq[T] =
    catalog
      .findBy(document)
      .flatMap(
        analyze(_) match {
          case Valid(a) => Some(a)
          case Invalid(e) => println(e); None
        }
      )

  def analyze(element: Element): ValidatedNel[AnalyzeError, T]

}

object AnalyzeRule {

  case class Publisher(id: Id, catalog: Catalog, name: Name, url: Url, image: Url)
      extends AnalyzeRule[Provider.Publisher] {

    override def analyze(element: Element): ValidatedNel[AnalyzeError, Provider.Publisher] =
      (
        Validated.validNel(id),
        name.analyze(element),
        url.analyze(element),
        image.analyze(element),
        Validated.validNel(Path.empty)
      ).mapN(Provider.Publisher.apply)
  }

  case class Comic(id: Id, catalog: Catalog, name: Name, url: Url, image: Url) extends AnalyzeRule[Provider.Comic] {

    override def analyze(element: Element): ValidatedNel[AnalyzeError, Provider.Comic] =
      (Validated.validNel(id), name.analyze(element), url.analyze(element), image.analyze(element))
        .mapN(Provider.Comic.apply)

  }

}
