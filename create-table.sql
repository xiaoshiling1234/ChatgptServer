drop database security;
create database security;
use security;
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
  `remaining_views` INT NOT NULL DEFAULT 10, -- 剩余查看次数，整数类型，不可为空，默认值为10
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 创建时间，时间戳类型，不可为空，默认值为当前时间
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间，时间戳类型，不可为空，更新时自动更新
  PRIMARY KEY (`user_id`),  -- 设置主键为user_id
  UNIQUE KEY `unique_username` (`username`),  -- 设置唯一键为username
  UNIQUE KEY `unique_email` (`email`)  -- 设置唯一键为email
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;  -- 使用InnoDB引擎，设置字符集为utf8mb4


CREATE TABLE roles (
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE user_roles (
  user_id INT,
  role_id INT,
  expiration_date DATE DEFAULT NULL,  -- 截止时间，日期类型，可为空
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
);

INSERT INTO user (username, email, password, first_name, last_name, avatar, phone_number, gender, date_of_birth, location, bio, is_active, is_admin) VALUES
('user1', 'user1@example.com', 'password1', 'John', 'Doe', NULL, '1234567890', 'M', '1990-01-01', 'New York', 'I am user1', TRUE, FALSE),
('user2', 'user2@example.com', 'password2', 'Jane', 'Doe', NULL, '0987654321', 'F', '1995-05-05', 'Los Angeles', 'I am user2', TRUE, FALSE),
('user3', 'user3@example.com', 'password3', 'Bob', 'Smith', NULL, '1111111111', 'M', '1985-12-25', 'Chicago', 'I am user3', TRUE, FALSE),
('user4', 'user4@example.com', 'password4', 'Alice', 'Johnson', NULL, '2222222222', 'F', '1992-09-15', 'Houston', 'I am user4', TRUE, FALSE),
('user5', 'user5@example.com', 'password5', 'David', 'Lee', NULL, '3333333333', 'M', '1988-06-30', 'San Francisco', 'I am user5', TRUE, FALSE),
('user6', 'user6@example.com', 'password6', 'Emily', 'Wang', NULL, '4444444444', 'F', '1998-03-20', 'Toronto', 'I am user6', TRUE, FALSE),
('user7', 'user7@example.com', 'password7', 'Michael', 'Chen', NULL, '5555555555', 'M', '1993-11-11', 'Vancouver', 'I am user7', TRUE, FALSE),
('user8', 'user8@example.com', 'password8', 'Sophia', 'Zhang', NULL, '6666666666', 'F', '1996-08-08', 'Beijing', 'I am user8', TRUE, FALSE);

INSERT INTO roles (id, name) VALUES
(1, '游客'),
(2, '周卡'),
(3, '月卡'),
(4, '年卡'),
(5, '永久');

INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 1),
(7, 2),
(8, 3);