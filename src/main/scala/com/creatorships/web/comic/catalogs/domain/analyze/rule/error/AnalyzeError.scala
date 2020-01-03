package com.creatorships.web.comic.catalogs.domain.analyze.rule.error

import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.format.name.Format
import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.{Attribute, Selector}

sealed trait AnalyzeError extends Throwable

object AnalyzeError {

  case class TitleNotFound(error: AnalyzeError) extends AnalyzeError {
    override val toString: String = s"Not found title. error = $error"
  }

  case class AttributeNotFound(attribute: Attribute) extends AnalyzeError {
    override val toString: String = s"Not found attribute($attribute)"
  }

  case class SelectorNotFound(selector: Selector) extends AnalyzeError {
    override val toString: String = s"Not found selector($selector)"
  }

  case class IllegalGeneration(generation: Int) extends AnalyzeError {
    override val toString: String = s"Illegal generation($generation)"
  }

  case class IllegalTitleFormat(format: Option[Format]) extends AnalyzeError {
    override val toString: String = s"Illegal title format($format)"
  }

}
