DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NULL DEFAULT '' comment 'row_key',
  `age` int unsigned NULL DEFAULT 0 comment 'value',
  `school` varchar(64) NULL DEFAULT '' comment '媒体名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci comment 'tbl_user';

DROP TABLE IF EXISTS `tbl_other`;
CREATE TABLE `tbl_other` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NULL DEFAULT '' comment 'row_key',
  `age` int unsigned NULL DEFAULT 0 comment 'value',
  `school` varchar(64) NULL DEFAULT '' comment '媒体名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci comment 'tbl_other';