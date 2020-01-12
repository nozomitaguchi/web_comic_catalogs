package com.creatorships.web.comic.catalogs.infrastructure.analyze.rule.record

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRules

case class Records(toSeq: Seq[Record]) {
  // enum に予想外のパラメータが飛んでくる場合のエラーハンドリングは一旦しない.
  val analyzeRules: AnalyzeRules = AnalyzeRules(toSeq.flatMap(_.toAnalyzeRule))

}
