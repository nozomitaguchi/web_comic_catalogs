package com.creatorships.web.comic.catalogs.domain.analyze.rule

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule.{Comic, Publisher}

case class AnalyzeRules(toSeq: Seq[AnalyzeRule[_]]) {

  val publishers: Publishers = Publishers(toSeq.collect { case rule: Publisher => rule })

  val comics: Comics = Comics(toSeq.collect { case rule: Comic => rule })

}
