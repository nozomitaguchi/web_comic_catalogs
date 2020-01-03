package com.creatorships.web.comic.catalogs.domain.analyze.rule

import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.{Attribute, Generation, Selector}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError.SelectorNotFound
import net.ruippeixotog.scalascraper.model.Element

trait AnalyzeRuleStyle {

  val selector: Selector

  val attribute: Option[Attribute]

  val generation: Generation

  protected def apply(element: Element): Either[AnalyzeError, String] =
    for {
      revertedElement <- generation.revert(element)
      foundTag <- findTag(revertedElement)
      text <- attrOrText(foundTag)
    } yield text

  private def findTag(element: Element): Either[AnalyzeError, Element] = {
    val maybeElement = element.select(selector.toString).headOption
    Either.fromOption(maybeElement, SelectorNotFound(selector))
  }

  /**
    * @param tag セレクターで指定したタグ.
    * @return attribute が設定されていれば, attr. そうでなければ tag.text を返却.
    */
  private def attrOrText(tag: Element): Either[AnalyzeError, String] =
    attribute.map(_.findBy(tag)).getOrElse(tag.text.asRight)

}
