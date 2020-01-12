package com.creatorships.web.comic.catalogs.domain.analyze.rule.columns

import cats.data.ValidatedNel
import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRuleStyle
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.{Attribute, Generation, Selector}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError
import com.creatorships.web.comic.catalogs.domain.provider._
import com.creatorships.web.comic.catalogs.domain.provider.columns.Path
import net.ruippeixotog.scalascraper.model.Element

sealed trait Url extends AnalyzeRuleStyle {

  val selector: Selector

  val attribute: Option[Attribute]

  val generation: Generation

  def analyze(element: Element): ValidatedNel[AnalyzeError, columns.Url]

}

object Url {

  case class AbsoluteUrl(selector: Selector, attribute: Option[Attribute], generation: Generation) extends Url {

    def analyze(element: Element): ValidatedNel[AnalyzeError, columns.Url] =
      apply(element).map(columns.Url.apply).toValidatedNel

  }

  case class RelativeUrl(selector: Selector, attribute: Option[Attribute], generation: Generation, url: columns.Url)
      extends Url {

    def analyze(element: Element): ValidatedNel[AnalyzeError, columns.Url] =
      apply(element).map(Path(_).absoluteWith(url)).toValidatedNel

  }

  def of(
    selector: Selector,
    maybeAttribute: Option[Attribute],
    generation: Generation,
    maybeUrl: Option[columns.Url]
  ): Url =
    maybeUrl
      .map(RelativeUrl(selector, maybeAttribute, generation, _))
      .getOrElse(AbsoluteUrl(selector, maybeAttribute, generation))

}
