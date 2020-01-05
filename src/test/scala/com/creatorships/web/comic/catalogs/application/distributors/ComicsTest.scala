package com.creatorships.web.comic.catalogs.application.distributors

import com.creatorships.web.comic.catalogs.domain.provider.Provider.Comic
import com.creatorships.web.comic.catalogs.domain.provider.columns.{Id, Name, Url}
import org.scalatest.WordSpec

class ComicsTest extends WordSpec {

  "#distinct" should {

    "return comics distinct" in {
      Comics.of(
        Comic(Id(1), Name("test"), Url("https://creatorships.com/test"), Url("image_url")),
        Comic(Id(2), Name("test"), Url("https://creatorships.com/test"), Url("image_url")),
        Comic(Id(1), Name("test2"), Url("https://creatorships.com/test"), Url("image_url")),
        Comic(Id(1), Name("test"), Url("https://creatorships.com/test2"), Url("image_url")),
        Comic(Id(1), Name("test"), Url("https://creatorships.com/test"), Url("image_url2"))
      ).distinct == Comics.of(
        Comic(Id(1), Name("test"), Url("https://creatorships.com/test"), Url("image_url")),
        Comic(Id(2), Name("test"), Url("https://creatorships.com/test"), Url("image_url")),
        Comic(Id(1), Name("test2"), Url("https://creatorships.com/test"), Url("image_url")),
        Comic(Id(1), Name("test"), Url("https://creatorships.com/test2"), Url("image_url"))
      )

    }

  }

}
