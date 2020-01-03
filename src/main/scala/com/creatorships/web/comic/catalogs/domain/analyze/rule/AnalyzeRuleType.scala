package com.creatorships.web.comic.catalogs.domain.analyze.rule

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRuleType.Publisher

sealed abstract class AnalyzeRuleType(override val toString: String) {

  def isPublisher: Boolean = toString == Publisher.toString

}

object AnalyzeRuleType {

  case object Publisher extends AnalyzeRuleType("publisher")

  case object Comic extends AnalyzeRuleType("comic")

  private val typeByName: Map[String, AnalyzeRuleType] = Seq(Publisher, Comic).map(t => t.toString -> t).toMap

  def of(name: String): Option[AnalyzeRuleType] = typeByName.get(name)

}
