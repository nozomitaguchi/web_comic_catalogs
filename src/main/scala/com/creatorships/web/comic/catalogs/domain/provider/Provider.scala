package com.creatorships.web.comic.catalogs.domain.provider

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule
import com.creatorships.web.comic.catalogs.domain.provider.columns.{Id, Name, Path, Url}

sealed trait Provider {

  val name: Name

  val url: Url

  val image: Url

}

object Provider {

  sealed trait Distributor[T] extends Provider {

    val id: Id

    val path: Path

    def contents(rule: AnalyzeRule[T]): Seq[T] = url.analyze(path)(rule)

  }

  case class Coordinator(id: Id, name: Name, url: Url, image: Url, path: Path) extends Distributor[Publisher]

  case class Publisher(id: Id, name: Name, url: Url, image: Url, path: Path) extends Distributor[Comic]

  case class Comic(distributorId: Id, name: Name, url: Url, image: Url) extends Provider

}
