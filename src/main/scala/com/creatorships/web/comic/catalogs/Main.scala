package com.creatorships.web.comic.catalogs

import cats.effect.ExitCode
import com.creatorships.web.comic.catalogs.application.Transaction
import com.creatorships.web.comic.catalogs.application.distributors.Distributors
import monix.eval.{Task, TaskApp}

object Main extends TaskApp {

  override def run(args: List[String]): Task[ExitCode] =
    Transaction[Task].use { xa =>
      for {
        distributors <- Distributors.fetch(xa)
        comics <- distributors.scrape
        _ <- comics.register(xa)
      } yield ExitCode.Success
    }

}
