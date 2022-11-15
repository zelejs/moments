SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_end_user_blacklist`;
CREATE TABLE `t_end_user_blacklist`(
`id` BIGINT(20) auto_increment,
`user_id` BIGINT(20) NOT NULL COMMENT'用户id',
`state` TINYINT(4) DEFAULT 0 COMMENT '是否被拉黑',
`org_id` BIGINT(20) NULL COMMENT '社区范围拉黑',
`appid` VARCHAR(50) NULL COMMENT '软件范围拉黑',
`user_range` TINYINT(4) NULL COMMENT '是否拉黑整个账号',
`create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
UNIQUE(`user_id`,`org_id`),
UNIQUE(`user_id`,`appid`),
UNIQUE(`user_id`,`user_range`),
PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;