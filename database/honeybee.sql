
CREATE TABLE `user_info` (
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(128) NOT NULL COMMENT '用户名',
  `user_password` varchar(100) NOT NULL COMMENT '用户密码',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '用户电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';



CREATE TABLE `user_account` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `total_money` double(20,0) DEFAULT NULL COMMENT '用户总金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';


CREATE TABLE `user_account_details` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `money` double(10,0) DEFAULT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户流水表';

