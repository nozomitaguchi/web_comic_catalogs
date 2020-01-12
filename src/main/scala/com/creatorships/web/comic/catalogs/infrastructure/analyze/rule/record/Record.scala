package com.creatorships.web.comic.catalogs.infrastructure.analyze.rule.record

import com.creatorships.web.comic.catalogs.domain.analyze.rule
import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRuleType
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.{Attribute, Generation, Selector}
import com.creatorships.web.comic.catalogs.domain.provider._
import com.creatorships.web.comic.catalogs.domain.provider.columns.Id

case class Record(
  id: Id,
  analyzeRuleType: Option[AnalyzeRuleType],
  catalogSelector: Selector,
  name: Name,
  urlRecord: Record.Url,
  imageUrlRecord: Record.Url
) {

  val isPublisher: Boolean = analyzeRuleType.exists(_.isPublisher)

  val url: Url = urlRecord.convert

  val imageUrl: Url = imageUrlRecord.convert
}

object Record {

  case class Url(
    selector: Selector,
    maybeAttribute: Option[Attribute],
    generation: Generation,
    maybeUrl: Option[columns.Url]
  ) {

    def convert: rule.columns.Url = rule.columns.Url.of(selector, maybeAttribute, generation, maybeUrl)

  }

}
