package com.creatorships.web.comic.catalogs.infrastructure.analyze.rule.record

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule.{Comic, Publisher}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.Selector
import com.creatorships.web.comic.catalogs.domain.analyze.rule.{AnalyzeRule, AnalyzeRuleType}
import com.creatorships.web.comic.catalogs.domain.provider.columns.Id

case class Record(
  id: Id,
  analyzeRuleType: Option[AnalyzeRuleType],
  catalogSelector: Selector,
  name: Name,
  url: Url,
  imageUrl: Url
) {

  def toAnalyzeRule: Option[AnalyzeRule[_]] = analyzeRuleType.map { ruleType =>
    if (ruleType.isPublisher) {
      Publisher(id, Catalog(catalogSelector), name, url, imageUrl)
    } else {
      Comic(id, Catalog(catalogSelector), name, url, imageUrl)
    }
  }

}
