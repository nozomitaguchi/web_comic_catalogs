package com.creatorships.web.comic.catalogs.infrastructure.analyze.url.record

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRuleType
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.Url.{AbsoluteUrl, RelativeUrl}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.format.name.Format
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.{Attribute, Generation, Selector}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.{Name, Url}
import com.creatorships.web.comic.catalogs.domain.provider._
import com.creatorships.web.comic.catalogs.domain.provider.columns.Id

case class Record(
  id: Id,
  analyzeRuleType: String,
  catalogSelector: Selector,
  name: Name,
  urlRecord: Record.Url,
  imageUrlRecord: Record.Url
) {

  private val maybeAnalyzeRuleType: Option[AnalyzeRuleType] = AnalyzeRuleType.of(analyzeRuleType)

  val isPublisher: Boolean = maybeAnalyzeRuleType.exists(_.isPublisher)

  val url: Url = urlRecord.convert

  val imageUrl: Url = imageUrlRecord.convert
}

object Record {

  case class Url(
    selector: Selector,
    maybeAttribute: Option[Attribute],
    generation: Generation,
    maybeFormat: Option[Format],
    maybeUrl: Option[columns.Url]
  ) {

    def convert: com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.Url =
      maybeUrl
        .map(RelativeUrl(selector, maybeAttribute, generation, _))
        .getOrElse(AbsoluteUrl(selector, maybeAttribute, generation))

  }

}
