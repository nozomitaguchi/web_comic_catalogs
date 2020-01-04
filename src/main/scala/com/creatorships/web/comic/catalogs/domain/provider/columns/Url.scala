package com.creatorships.web.comic.catalogs.domain.provider.columns

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}

case class Url(underlying: String) extends AnyVal {

  override def toString: String = if (underlying.lastOption.contains('/')) underlying.init else underlying

  def analyze[T](path: Path)(analyzeRule: AnalyzeRule[T]): Seq[T] = {
    val document = Url.browser.get(toString + path.toString)
    analyzeRule.apply(document)
  }

  def isMatched(other: Url): Boolean = toString == other.toString

}

object Url {

  val browser: Browser = JsoupBrowser()

  val empty: Url = Url("")

}
