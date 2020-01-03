package com.creatorships.web.comic.catalogs.domain.analyze.rule.columns

import cats.data.ValidatedNel
import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRuleStyle
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.format.name.Format
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.{Attribute, Generation, Selector}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError.{IllegalTitleFormat, TitleNotFound}
import com.creatorships.web.comic.catalogs.domain.provider.columns
import net.ruippeixotog.scalascraper.model.Element

case class Name(
  selector: Selector,
  attribute: Option[Attribute],
  generation: Generation,
  format: Option[Format]
) extends AnalyzeRuleStyle {

  def analyze(element: Element): ValidatedNel[AnalyzeError, columns.Name] =
    (for {
      name <- apply(element).leftMap(TitleNotFound)
      formatted <- format
        .map(_.find(name).toRight(IllegalTitleFormat(format)))
        .getOrElse(Right(name))
    } yield columns.Name(formatted)).toValidatedNel

}
