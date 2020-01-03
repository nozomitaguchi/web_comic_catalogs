package com.creatorships.web.comic.catalogs.domain.analyze.rule.columns

import com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value.Selector
import net.ruippeixotog.scalascraper.model.{Document, Element}

case class Catalog(selector: Selector) {

  def findBy(document: Document): Seq[Element] = document.body.select(selector.toString).iterator.toList

}
