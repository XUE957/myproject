-- 创建数据库
drop database if exists blog;

create database blog DEFAULT CHARACTER SET utf8;
-- 使⽤数据库
use blog;
-- 创建[⽤户表]
drop table if exists userinfo;
create table userinfo(
   id int primary key auto_increment,
   username varchar(100) not null unique, --  alter table userinfo add unique(username);
   password varchar(100) not null,
   photo varchar(500) default '',
   createtime timestamp default current_timestamp,
--   updatetime timestamp default current_timestamp(),
   `state` int default 1
) default charset 'utf8mb4';
-- 创建[⽂章表]
drop table if exists articleinfo;
create table articleinfo(
   id int primary key auto_increment,
   title varchar(100) not null,
   content text not null,
   createtime timestamp default current_timestamp,
--   updatetime timestamp default current_timestamp,
   uid int not null,
   rcount int not null default 1,
   `state` int default 1
) default charset 'utf8mb4';
---- 创建视频表
--drop table if exists videoinfo;
--create table videoinfo(
--vid int primary key,
--`title` varchar(250),
--`url` varchar(1000),
--createtime datetime default now(),
--updatetime datetime default now(),
--uid int
--);

-- 添加⼀个⽤户信息
INSERT INTO `userinfo` (`username`, `password`, `photo`,
`createtime`, `updatetime`, `state`) VALUES
('admin', 'admin', '', '2022-12-06 17:10:48', '2022-12-06 17:10:48', 1);
-- ⽂章添加测试数据
insert into articleinfo(title,content,uid)
values('Java', 'Java正⽂', 1);
---- 添加视频
--insert into videoinfo(vid,title,url,uid) values(1,'java
--title','http://www.baidu.com',1);