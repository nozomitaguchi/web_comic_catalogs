package com.creatorships.web.comic.catalogs.domain.provider.columns

case class Path(underlying: String) extends AnyVal {

  override def toString: String = if (underlying.headOption.contains('/')) underlying.tail else underlying

  def absoluteWith(url: Url): Url = Url(url.toString + toString)

}

object Path {

  val empty: Path = Path("")

}
