package com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.format.name

import scala.util.matching.Regex

case class Format(override val toString: String) {

  val regex: Regex = toString.r

  def find(title: String): Option[String] = title match {
    case regex(t) => Some(t)
    case _ => None
  }

}
