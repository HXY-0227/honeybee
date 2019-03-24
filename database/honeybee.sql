CREATE TABLE `team_info` (
  `team_id` int(10) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(15) NOT NULL COMMENT '团队名',
  `user_name` varchar(15) NOT NULL COMMENT '用户名',
  `captial` float DEFAULT NULL COMMENT '资金',
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团队信息表';

CREATE TABLE `user_info` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(15) NOT NULL COMMENT '用户名',
  `user_password` varchar(30) NOT NULL COMMENT '用户密码',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '用户电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE `detail_info` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `team_name` varchar(15) NOT NULL COMMENT '团队名',
  `user_name` varchar(15) NOT NULL COMMENT '用户名',
  `detail_type` char(1) NOT NULL COMMENT '明细类型',
  `captial` float DEFAULT NULL COMMENT '消费金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='明细信息表';