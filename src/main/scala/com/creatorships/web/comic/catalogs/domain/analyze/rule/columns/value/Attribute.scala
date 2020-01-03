package com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value

import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError.AttributeNotFound
import net.ruippeixotog.scalascraper.model.Element

case class Attribute(override val toString: String) extends AnyVal {

  def findBy(element: Element): Either[AnalyzeError, String] =
    Either.catchNonFatal(element.attr(toString)).leftMap(_ => AttributeNotFound(this))

}
