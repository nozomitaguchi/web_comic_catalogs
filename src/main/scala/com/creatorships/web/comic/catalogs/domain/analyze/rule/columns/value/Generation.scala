package com.creatorships.web.comic.catalogs.domain.analyze.rule.columns.value

import cats.implicits._
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError
import com.creatorships.web.comic.catalogs.domain.analyze.rule.error.AnalyzeError.IllegalGeneration
import net.ruippeixotog.scalascraper.model.Element

case class Generation(toInt: Int) extends AnyVal {

  @scala.annotation.tailrec
  final def revert(element: Element, generation: Int = toInt): Either[AnalyzeError, Element] =
    if (generation == 0) {
      element.asRight
    } else {
      element.parent match {
        case None => IllegalGeneration(toInt).asLeft
        case Some(parent) => revert(parent, generation - 1)
      }
    }

  override def toString: String = toInt.toString

}

object Generation {

  val current: Generation = Generation(0)

}
