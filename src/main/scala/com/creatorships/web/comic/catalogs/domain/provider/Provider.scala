package com.creatorships.web.comic.catalogs.domain.provider

import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRule
import com.creatorships.web.comic.catalogs.domain.provider.columns.{Id, Name, Path, Url}
import io.circe._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.auto._
import io.circe.syntax._
import shapeless.Unwrapped

sealed trait Provider {

  val name: Name

  val url: Url

  val image: Url

  implicit protected val jsonConfig: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit protected def encodeAnyVal[W <: AnyVal, U](
    implicit unwrapped: Unwrapped.Aux[W, U],
    encoderUnwrapped: Encoder[U]
  ): Encoder[W] =
    Encoder.instance[W](v => encoderUnwrapped(unwrapped.unwrap(v)))

  def json: Json = this.asJson

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
