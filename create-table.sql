CREATE TABLE `user` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,  -- 用户ID，自增长整数类型
  `username` VARCHAR(255) NOT NULL,  -- 用户名，不可为空，最大长度为255
  `email` VARCHAR(255) NOT NULL,  -- 邮箱，不可为空，最大长度为255
  `password` VARCHAR(255) NOT NULL,  -- 密码，不可为空，最大长度为255
  `first_name` VARCHAR(255) DEFAULT NULL,  -- 名字，最大长度为255，可为空
  `last_name` VARCHAR(255) DEFAULT NULL,  -- 姓氏，最大长度为255，可为空
  `avatar` VARCHAR(255) DEFAULT NULL,  -- 头像，最大长度为255，可为空
  `phone_number` VARCHAR(20) DEFAULT NULL,  -- 电话号码，最大长度为20，可为空
  `gender` ENUM('M','F','O') DEFAULT NULL,  -- 性别，枚举类型，可选值为'M'、'F'、'O'，可为空
  `date_of_birth` DATE DEFAULT NULL,  -- 出生日期，日期类型，可为空
  `location` VARCHAR(255) DEFAULT NULL,  -- 地址，最大长度为255，可为空
  `bio` TEXT DEFAULT NULL,  -- 简介，文本类型，可为空
  `is_active` BOOLEAN NOT NULL DEFAULT TRUE,  -- 是否激活，布尔类型，不可为空，默认值为TRUE
  `is_admin` BOOLEAN NOT NULL DEFAULT FALSE,  -- 是否管理员，布尔类型，不可为空，默认值为FALSE
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 创建时间，时间戳类型，不可为空，默认值为当前时间
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间，时间戳类型，不可为空，更新时自动更新
  PRIMARY KEY (`user_id`),  -- 设置主键为user_id
  UNIQUE KEY `unique_username` (`username`),  -- 设置唯一键为username
  UNIQUE KEY `unique_email` (`email`)  -- 设置唯一键为email
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;  -- 使用InnoDB引擎，设置字符集为utf8mb4