DROP TABLE IF EXISTS `wp_company`;
CREATE TABLE `wp_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `initials` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `wp_garage`;
CREATE TABLE `wp_garage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `initials` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9w5995ex2pxg26il2n034t9an` (`company_id`),
  CONSTRAINT `FK9w5995ex2pxg26il2n034t9an` FOREIGN KEY (`company_id`) REFERENCES `wp_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;