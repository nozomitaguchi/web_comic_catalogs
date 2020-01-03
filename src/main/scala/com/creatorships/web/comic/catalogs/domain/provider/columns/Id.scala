package com.creatorships.web.comic.catalogs.domain.provider.columns

case class Id(toInt: Int) extends AnyVal {

  override def toString: String = toInt.toString

}
