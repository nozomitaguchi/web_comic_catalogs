package com.creatorships.web.comic.catalogs.domain.analyze.rule

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule.Publisher
import com.creatorships.web.comic.catalogs.domain.provider.Provider.Coordinator

case class Publishers(toSeq: Seq[Publisher]) extends AnyVal {

  def findBy(coordinator: Coordinator): Option[Publisher] = toSeq.find(_.id == coordinator.id)

}
