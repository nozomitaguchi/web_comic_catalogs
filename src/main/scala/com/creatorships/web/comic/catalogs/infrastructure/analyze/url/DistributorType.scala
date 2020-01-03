package com.creatorships.web.comic.catalogs.infrastructure.analyze.url

sealed abstract class DistributorType(override val toString: String)

object DistributorType {

  case object Coordinator extends DistributorType("coordinator")

  case object Publisher extends DistributorType("publisher")

}
