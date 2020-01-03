package com.creatorships.web.comic.catalogs.domain.analyze.rule

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule.Comic
import com.creatorships.web.comic.catalogs.domain.provider.Provider.Publisher

case class Comics(toSeq: Seq[Comic]) extends AnyVal {

  def findBy(publisher: Publisher): Option[Comic] = toSeq.find(_.id == publisher.id)

}
