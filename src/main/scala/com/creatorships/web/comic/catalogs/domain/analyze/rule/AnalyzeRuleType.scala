package com.creatorships.web.comic.catalogs.domain.analyze.rule

sealed abstract class AnalyzeRuleType(override val toString: String) {

  def isPublisher: Boolean

}

object AnalyzeRuleType {

  case object Publisher extends AnalyzeRuleType("publisher") {
    override def isPublisher: Boolean = true
  }

  case object Comic extends AnalyzeRuleType("comic") {
    override def isPublisher: Boolean = false
  }

  private val typeByName: Map[String, AnalyzeRuleType] = Seq(Publisher, Comic).map(t => t.toString -> t).toMap

  def of(name: String): Option[AnalyzeRuleType] = typeByName.get(name)

}
