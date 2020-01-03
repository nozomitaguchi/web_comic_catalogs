package com.creatorships.web.comic.catalogs.infrastructure

import cats.Monad
import cats.effect.Bracket
import com.creatorships.web.comic.catalogs.domain.analyze.rule.AnalyzeRules
import com.creatorships.web.comic.catalogs.infrastructure.analyze.url.record.{Record, Records}
import doobie.implicits._
import doobie.util.fragment.Fragment
import doobie.util.transactor.Transactor

case class AnalyzeRulesRepository[F[_]: Monad](transactor: Transactor[F])(implicit bracket: Bracket[F, Throwable]) {

  def findAll: F[AnalyzeRules] =
    selectAll
      .query[Record]
      .to[List]
      .map(Records(_).analyzeRules)
      .transact(transactor)

  // レコードを非正規化.
  private def selectAll: Fragment =
    sql"""SELECT
         | rules.distributor_id,
         | rules.analyze_rule_type,
         | rules.catalog_selector,
         | MAX(IF(styles.analyze_rule_style_type = 'name', styles.selector, '')) as name_selector,
         | MAX(IF(styles.analyze_rule_style_type = 'name', attributes.attribute, NULL)) as name_attribute,
         | MAX(IF(styles.analyze_rule_style_type = 'name', styles.generation, 0)) as name_generation,
         | MAX(IF(styles.analyze_rule_style_type = 'name', formats.format, NULL)) as name_format,
         | MAX(IF(styles.analyze_rule_style_type = 'url', styles.selector, '')) as url_selector,
         | MAX(IF(styles.analyze_rule_style_type = 'url', attributes.attribute, NULL)) as url_attribute,
         | MAX(IF(styles.analyze_rule_style_type = 'url', styles.generation, 0)) as url_generation,
         | MAX(IF(styles.analyze_rule_style_type = 'url', urls.url, NULL)) as url_url,
         | MAX(IF(styles.analyze_rule_style_type = 'image_url', styles.selector, '')) as image_url_selector,
         | MAX(IF(styles.analyze_rule_style_type = 'image_url', attributes.attribute, NULL)) as image_url_attribute,
         | MAX(IF(styles.analyze_rule_style_type = 'image_url', styles.generation, 0)) as image_url_generation,
         | MAX(IF(styles.analyze_rule_style_type = 'image_url', urls.url, NULL)) as image_url_url
         |FROM
         | analyze_rules rules
         |INNER JOIN analyze_rule_styles styles
         | ON rules.distributor_id = styles.distributor_id
         | AND rules.analyze_rule_type = styles.analyze_rule_type
         |LEFT OUTER JOIN analyze_rule_style_attributes attributes
         | ON rules.distributor_id = attributes.distributor_id
         | AND rules.analyze_rule_type = attributes.analyze_rule_type
         | AND styles.analyze_rule_style_type = attributes.analyze_rule_style_type
         |LEFT OUTER JOIN analyze_rule_style_formats formats
         | ON rules.distributor_id = formats.distributor_id
         | AND rules.analyze_rule_type = formats.analyze_rule_type
         | AND styles.analyze_rule_style_type = formats.analyze_rule_style_type
         |LEFT OUTER JOIN analyze_rule_style_urls urls
         | ON rules.distributor_id = urls.distributor_id
         | AND rules.analyze_rule_type = urls.analyze_rule_type
         | AND styles.analyze_rule_style_type = urls.analyze_rule_style_type
         |GROUP BY distributor_id, analyze_rule_type
         |ORDER BY
         | distributor_id, analyze_rule_type;
       """.stripMargin

}
