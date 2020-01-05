package com.creatorships.web.comic.catalogs.infrastructure.analyze.rule.record

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule.{Comic, Publisher}
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.Catalog
import com.creatorships.web.comic.catalogs.domain.analyze.rule.{AnalyzeRules, Comics, Publishers}

case class Records(toSeq: Seq[Record]) {

  val analyzeRules: AnalyzeRules = AnalyzeRules(
    Publishers(toSeq.filter(_.isPublisher).map { record =>
      Publisher(record.id, Catalog(record.catalogSelector), record.name, record.url, record.imageUrl)
    }),
    Comics(toSeq.filterNot(_.isPublisher).map { record =>
      Comic(record.id, Catalog(record.catalogSelector), record.name, record.url, record.imageUrl)
    })
  )

}
