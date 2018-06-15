/*
Navicat MySQL Data Transfer

Source Server         : 192.168.22.143
Source Server Version : 50720
Source Host           : 192.168.22.143:3306
Source Database       : ningmengcar

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-08 09:49:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car_agent
-- ----------------------------
DROP TABLE IF EXISTS `car_agent`;
CREATE TABLE `car_agent` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `car_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `agent_company_id` bigint(20) DEFAULT NULL COMMENT '代办公司id',
  `agent_company_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办公司名称',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='成交车辆代办表';

-- ----------------------------
-- Table structure for car_agent_company
-- ----------------------------
DROP TABLE IF EXISTS `car_agent_company`;
CREATE TABLE `car_agent_company` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `company_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '代办公司编码',
  `company_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办公司名称',
  `address` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办公司地址',
  `handler_id` bigint(20) DEFAULT NULL COMMENT '负责人id',
  `handler_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人名称',
  `handler_tel` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人联系电话',
  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `status` varchar(5) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '状态：1启用，2停用，3删除',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_person` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `del_person` bigint(20) DEFAULT NULL COMMENT '删除人',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') CHARACTER SET utf8 DEFAULT '0' COMMENT '删除状态  0 未删除  1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_index` (`company_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代办公司表';

-- ----------------------------
-- Table structure for car_agent_company_store
-- ----------------------------
DROP TABLE IF EXISTS `car_agent_company_store`;
CREATE TABLE `car_agent_company_store` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `company_id` bigint(20) DEFAULT NULL COMMENT '代办公司ID',
  `store_id` bigint(20) DEFAULT NULL COMMENT '4S店ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代办公司管理4S店绑定表';

-- ----------------------------
-- Table structure for car_agent_log
-- ----------------------------
DROP TABLE IF EXISTS `car_agent_log`;
CREATE TABLE `car_agent_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `agent_id` bigint(20) DEFAULT NULL COMMENT '代办主表id',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '议价状态：1等待处理；2确认过户事宜，3出票，4出牌，5交档，6提档，7手续回传，8回传审核不通过，9回传审核完成',
  `status_cn` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态名称ID',
  `log_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志详细说明',
  `up_doc` text COLLATE utf8mb4_bin COMMENT '上传资料内容',
  `up_time` timestamp NULL DEFAULT NULL COMMENT '提交时间',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `auth_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核意见',
  `auth_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核人',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `agent_user_id` bigint(20) DEFAULT NULL COMMENT '代办人',
  `agent_user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办人',
  `agent_user_mobil` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办人电话',
  `file` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='成交车辆代办日志表';

-- ----------------------------
-- Table structure for car_agent_user
-- ----------------------------
DROP TABLE IF EXISTS `car_agent_user`;
CREATE TABLE `car_agent_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `company_id` bigint(20) DEFAULT NULL COMMENT '所属代办公司',
  `user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆账号',
  `password` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `update_manager` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1可用，2停用',
  `login_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆账号',
  `phone` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `is_head` enum('1','2') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否负责人 1是2否',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代办公司员工表';

-- ----------------------------
-- Table structure for car_app_accord
-- ----------------------------
DROP TABLE IF EXISTS `car_app_accord`;
CREATE TABLE `car_app_accord` (
  `id` bigint(20) NOT NULL COMMENT '轮播图',
  `title` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '轮播图标题',
  `type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '协议类型：1协议、2认证常见问题、3关于我们、4委托价说明、5承诺细则、6违约说明，7获取卖家发车注意事项、8获取签约内容',
  `content` longtext COLLATE utf8mb4_bin COMMENT '协议内容',
  `describe` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简介描述',
  `author` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发布作者或部门,页面显示用',
  `pulish_date` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发布日期，页面显示用',
  `if_show` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否启用：1启用，2不启用，-1删除',
  `editor` bigint(20) DEFAULT NULL COMMENT '编辑人',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `code` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '协议编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='软件协议配置表';

-- ----------------------------
-- Table structure for car_app_info
-- ----------------------------
DROP TABLE IF EXISTS `car_app_info`;
CREATE TABLE `car_app_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `app_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用ID',
  `app_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用名称',
  `version` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本号',
  `about` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关于我们',
  `logo` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'logo地址',
  `editor` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编辑人',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户端类型：1卖家/中心，2买家，3代办',
  `update_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本升级地址',
  `tel` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='二手车app信息表';

-- ----------------------------
-- Table structure for car_app_slideshow
-- ----------------------------
DROP TABLE IF EXISTS `car_app_slideshow`;
CREATE TABLE `car_app_slideshow` (
  `id` bigint(20) NOT NULL COMMENT '轮播图',
  `title` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '轮播图标题',
  `open_type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '轮播图类型：1、拍卖场次，2车辆，3拍卖大厅，4问题描述，5关于我们，6网页地址,7图文',
  `img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '轮播图片',
  `describe` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `open_obj` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '打开对象内容，ID或http地址',
  `sort` int(3) DEFAULT NULL COMMENT '排序，大的在前',
  `if_show` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否启用：1启用，2不启用，3删除',
  `editor` bigint(20) DEFAULT NULL COMMENT '编辑人',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型（现场播报1，轮播图2）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='app轮播图设置';

-- ----------------------------
-- Table structure for car_app_version
-- ----------------------------
DROP TABLE IF EXISTS `car_app_version`;
CREATE TABLE `car_app_version` (
  `id` bigint(20) NOT NULL,
  `app_name` varchar(255) DEFAULT NULL COMMENT 'app名称',
  `new_app_size` double DEFAULT NULL COMMENT '新app大小',
  `new_app_version_code` varchar(20) DEFAULT NULL COMMENT 'app版本号',
  `new_app_version_name` varchar(50) DEFAULT NULL COMMENT '版本名称',
  `new_app_update_desc` varchar(500) DEFAULT NULL COMMENT '更新描述',
  `new_app_release_time` varchar(50) DEFAULT NULL COMMENT 'app发布时间',
  `new_app_url` varchar(500) DEFAULT NULL COMMENT '下载更新地址',
  `is_force_update` varchar(1) DEFAULT '1' COMMENT '是否强制更新:1不强制，2强制',
  `app_type` varchar(1) DEFAULT NULL COMMENT 'app类型：1android买家端，2android卖家端\r\n3、iOS卖家端',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car_auction_bid_record
-- ----------------------------
DROP TABLE IF EXISTS `car_auction_bid_record`;
CREATE TABLE `car_auction_bid_record` (
  `id` bigint(20) NOT NULL COMMENT '拍卖活动表',
  `auction_id` bigint(20) DEFAULT NULL COMMENT '拍卖活动id',
  `auction_car_id` bigint(20) DEFAULT NULL COMMENT '现场竞拍场次车辆id',
  `car_id` bigint(20) DEFAULT NULL COMMENT '拍卖车辆ID',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '出价客户ID',
  `bid_fee` decimal(11,0) DEFAULT NULL COMMENT '出价金额（累计后）',
  `add_fee` decimal(11,0) DEFAULT NULL COMMENT '本次加价金额',
  `bid_time` timestamp NULL DEFAULT NULL COMMENT '竞价时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖车辆出价记录明细表';

-- ----------------------------
-- Table structure for car_auction_fare_setting
-- ----------------------------
DROP TABLE IF EXISTS `car_auction_fare_setting`;
CREATE TABLE `car_auction_fare_setting` (
  `id` bigint(20) NOT NULL COMMENT '拍卖出价ID',
  `fare_price` decimal(11,0) DEFAULT NULL COMMENT '拍卖出价',
  `edit_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1正常，2停用',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆拍卖出价设置';

-- ----------------------------
-- Table structure for car_auction_setting
-- ----------------------------
DROP TABLE IF EXISTS `car_auction_setting`;
CREATE TABLE `car_auction_setting` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `keep_time` int(5) DEFAULT NULL COMMENT '拍卖持续时间，单位秒',
  `deposit` decimal(10,0) DEFAULT NULL COMMENT '保证金金额',
  `end_keep_time` int(5) DEFAULT NULL COMMENT '再次延长的拍卖时长',
  `delayed_time` int(5) DEFAULT NULL COMMENT '延时加价时间',
  `server_fee` decimal(10,0) DEFAULT NULL,
  `agent_fee` decimal(10,0) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  `status` varchar(5) CHARACTER SET utf8mb4 DEFAULT '1' COMMENT '是否启用：1正常，2不启用',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '操作人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `update_person` bigint(20) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖全局配置信息表';

-- ----------------------------
-- Table structure for car_auto
-- ----------------------------
DROP TABLE IF EXISTS `car_auto`;
CREATE TABLE `car_auto` (
  `id` bigint(20) NOT NULL COMMENT '车辆id',
  `publish_user_id` bigint(20) DEFAULT NULL COMMENT '发布者ID',
  `publish_user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发布人姓名',
  `publish_user_mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发布人手机号',
  `auto_info_name` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆名称=品牌+车系+车型',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺4S店id',
  `store_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名称',
  `source_type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车',
  `views` bigint(11) DEFAULT NULL COMMENT '浏览次数',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `report_score` decimal(11,0) DEFAULT NULL COMMENT '报告评分',
  `report_colligation_ranks` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '质检报告综合等级：A,B,C..',
  `report_servicing_ranks` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '质检报告整备等级：++，+，-，--',
  `report_time` timestamp NULL DEFAULT NULL COMMENT '检测报告时间',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：0删除、 1草稿、2审核中、3审核不通过、4撤回审核中、5等待上拍、6等待开拍、7正在竞拍、8成交-等待付款、9议价处理中、10付款信息待审核、11已付款、12过户中、14争议处理中、15手续回传确认中、16交易完成、17待评价、18交易关闭、19流拍\r\n\r\n\r\n',
  `car_auto_no` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '车辆编号',
  `main_photo` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆首图',
  `if_new` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否新车：1二手车，2新车',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `auction_num` int(5) DEFAULT NULL COMMENT '上拍次数',
  `region_id` bigint(20) DEFAULT NULL COMMENT '地区区域id',
  `auto_auction_id` bigint(20) DEFAULT NULL COMMENT '竞拍信息id',
  PRIMARY KEY (`car_auto_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1519 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆发布表';

-- ----------------------------
-- Table structure for car_auto_auction
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_auction`;
CREATE TABLE `car_auto_auction` (
  `id` bigint(20) NOT NULL COMMENT '拍卖信息id',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '拍卖车辆ID',
  `owner_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车主姓名',
  `owner_mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车主电话',
  `link_man_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '看车联系人姓名',
  `link_man_mobile` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '看车联系人电话',
  `auction_start_time` timestamp NULL DEFAULT NULL COMMENT '开拍时间',
  `expected_feedback_time` date DEFAULT NULL COMMENT '期望反馈时间',
  `auction_end_default_time` datetime DEFAULT NULL,
  `auction_end_time` datetime DEFAULT NULL COMMENT '竞拍结束时间(线上)',
  `if_agent` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否需要代办：1需要，2不需要',
  `starting_price` decimal(11,2) DEFAULT NULL COMMENT '起拍价',
  `reserve_price` decimal(11,2) DEFAULT NULL COMMENT '保留价',
  `service_price` decimal(11,2) DEFAULT NULL COMMENT '服务费',
  `agent_price` decimal(11,2) DEFAULT NULL COMMENT '代办费用',
  `owner_price` decimal(11,2) DEFAULT NULL COMMENT '车主意向价',
  `auction_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '拍卖类型（1线上、2线下)',
  `open_limit_cn` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开放范围，空则全部开放',
  `open_limit` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开放范围id拼接',
  `car_location` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆当前所在地',
  `remark` text COLLATE utf8mb4_bin COMMENT '备注',
  `top_pricer_id` bigint(20) DEFAULT NULL COMMENT '最高出价人',
  `top_bid_price` decimal(11,2) DEFAULT NULL COMMENT '最高出价金额',
  `top_bid_time` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最高出价时间',
  `bids_count` int(11) DEFAULT '0' COMMENT '总出价次数',
  `biders_count` int(11) DEFAULT '0' COMMENT '总竞价人数',
  `status` enum('1','2','3','4') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '1待开拍，2拍卖中，3流拍，4，已成交',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_person` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `del_person` bigint(20) DEFAULT NULL COMMENT '删除人',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') CHARACTER SET utf8 DEFAULT '0' COMMENT '删除状态  0 未删除  1 已删除',
  `move_to_where` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '前往何处：1本市外迁均可，2只能外迁，3只能本市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆拍卖信息表';

-- ----------------------------
-- Table structure for car_auto_conf_detail
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_conf_detail`;
CREATE TABLE `car_auto_conf_detail` (
  `id` bigint(20) NOT NULL,
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `conf_title_id` bigint(20) DEFAULT NULL COMMENT '配置标题id',
  `conf_title_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆配置名称',
  `conf_option` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置选项：1有，2无，3加装',
  `conf_option_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置选项：有，无，加装',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆配置明细';

-- ----------------------------
-- Table structure for car_auto_conf_title
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_conf_title`;
CREATE TABLE `car_auto_conf_title` (
  `id` bigint(20) NOT NULL,
  `title` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置标题',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建人',
  `create_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '状态：1可用，2停用',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `option_size` int(1) DEFAULT '2' COMMENT '选项个数：2个、3个',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆配置标题';

-- ----------------------------
-- Table structure for car_auto_copy_1
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_copy_1`;
CREATE TABLE `car_auto_copy_1` (
  `id` bigint(20) NOT NULL COMMENT '车辆id',
  `pulish_user_id` bigint(20) DEFAULT NULL COMMENT '发布者ID',
  `pulish_user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发布人姓名',
  `pulish_user_mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发布人手机号',
  `auto_info_name` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆名称=品牌+车系+车型',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺4S店id',
  `source_type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆来源：1个人车源，2公务车，3 4S店置换，4店铺车，5试乘试驾车',
  `views` bigint(11) DEFAULT NULL COMMENT '浏览次数',
  `pulish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `report_score` decimal(11,0) DEFAULT NULL COMMENT '报告评分',
  `report_colligation_ranks` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '质检报告综合等级：A,B,C..',
  `report_servicing_ranks` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '质检报告整备等级：++，+，-，--',
  `report_time` timestamp NULL DEFAULT NULL COMMENT '检测报告时间',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：0草稿、1待审核，2审核通过，3审核不通过，4上拍，5流拍、6成交 、8待议价 、7待付款 、9已付款、10违约、11正在过户、12交易完成，13交易关闭，14争议中',
  `car_auto_no` varchar(0) COLLATE utf8mb4_bin DEFAULT NULL,
  `main_photo` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆首图',
  `if_new` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否新车：1二手车，2新车',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `auction_num` int(5) DEFAULT NULL COMMENT '上拍次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆发布表';

-- ----------------------------
-- Table structure for car_auto_detection_class
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_detection_class`;
CREATE TABLE `car_auto_detection_class` (
  `id` bigint(20) NOT NULL COMMENT '分组id',
  `class_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '检测分类名称',
  `p_id` bigint(20) DEFAULT NULL COMMENT '上级检测类ID：0顶级',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `is_close` varchar(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否关闭：1开启，2关闭',
  `is_del` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否删除：1不，2删除',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '修改人',
  `edit_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `sort` int(11) DEFAULT NULL COMMENT '排序号，小的在前',
  `class_no` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '检测项编号：对应客户端报告描点',
  `position_type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部位：1前部，2后部',
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_name` (`class_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆检测分类表';

-- ----------------------------
-- Table structure for car_auto_detection_class_options
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_detection_class_options`;
CREATE TABLE `car_auto_detection_class_options` (
  `id` bigint(20) NOT NULL COMMENT '分组id',
  `class_id` bigint(20) DEFAULT NULL COMMENT '检测项类别ID',
  `is_default` varchar(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否默认：1非默认，2默认',
  `options_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '显示名称',
  `is_close` varchar(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否关闭：1开启，2关闭',
  `is_del` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否删除：1不，2删除',
  `sort` int(11) DEFAULT NULL COMMENT '排序号，大的在前',
  `deduct_marks` decimal(11,0) DEFAULT '0' COMMENT '扣分大小：默认0',
  `if_must_photo` varchar(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否必须拍照：1不需要，2需要',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '修改人',
  `edit_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `detection_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '检测类型：1骨架损伤、2钣金修复、3外观修复、4泡水/火烧、5机械检测、6内饰检测、7电器检测',
  `detection_type_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '检测类型名称',
  `damaged_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '损坏类型:1轻微损伤、2一般损伤、3严重损伤',
  `damaged_type_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '损坏类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆检测分类选项明细表';

-- ----------------------------
-- Table structure for car_auto_detection_data
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_detection_data`;
CREATE TABLE `car_auto_detection_data` (
  `id` bigint(20) NOT NULL COMMENT '检测异常id',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '检测车辆ID',
  `options_id` bigint(20) DEFAULT NULL COMMENT '检测项id',
  `problem_description` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '问题描述',
  `decreased_score` decimal(11,0) DEFAULT NULL COMMENT '扣分大小',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '修改人',
  `edit_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `class_id` bigint(20) DEFAULT NULL COMMENT '检测结果所属分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆检测数据表';

-- ----------------------------
-- Table structure for car_auto_detection_data_photo
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_detection_data_photo`;
CREATE TABLE `car_auto_detection_data_photo` (
  `id` bigint(20) NOT NULL COMMENT '检测异常id',
  `class_id` bigint(20) DEFAULT NULL COMMENT '检测项类别ID',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '检测车辆ID',
  `photo_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '照片组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆检测照片表';

-- ----------------------------
-- Table structure for car_auto_family
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_family`;
CREATE TABLE `car_auto_family` (
  `id` bigint(20) NOT NULL COMMENT '主键Id',
  `name` varchar(512) DEFAULT NULL COMMENT '车系名称',
  `jyid` varchar(512) DEFAULT NULL COMMENT '精友ID',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌ID',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `brand_make_id` bigint(20) DEFAULT NULL COMMENT '品牌厂家ID',
  `brand_make_name` varchar(255) DEFAULT NULL COMMENT '品牌厂家',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `family_photo` varchar(512) DEFAULT NULL COMMENT '车系图片',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态 0未删除  1已删除',
  `is_loop_play` enum('0','1') DEFAULT '0' COMMENT '是否轮播‘0’ 是轮播  ‘1’是不轮播',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车型车系表';

-- ----------------------------
-- Table structure for car_auto_info_detail
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_info_detail`;
CREATE TABLE `car_auto_info_detail` (
  `id` bigint(20) NOT NULL COMMENT '车辆id',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `vin` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆vin码',
  `auto_brand` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆品牌',
  `auto_brand_cn` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌名称',
  `auto_style` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车型id',
  `auto_style_cn` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车型名称',
  `auto_series` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车系id',
  `auto_series_cn` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车系名称',
  `engine_capacity` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆排量',
  `engine_capacity_unit` varchar(1) COLLATE utf8mb4_bin DEFAULT '' COMMENT '汽车排量单位T、L',
  `environment` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '环保标准：国二、国三、国四、国五',
  `environment_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '排放标准名称',
  `oil_supply_system` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '供油系统：汽油、柴油',
  `oil_supply_system_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '供油系统中文',
  `transmission` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '变速器',
  `transmission_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '变速器中文',
  `vehicle_driver` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆驱动形式：前置后置',
  `vehicle_driver_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驱动形式中文',
  `mileage` decimal(11,0) DEFAULT NULL COMMENT '里程表里程',
  `color` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆颜色',
  `color_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆颜色中文',
  `color_changed` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否改装过颜色|1:改装 0:未改装',
  `manufacture_date` date DEFAULT NULL COMMENT '出厂日期',
  `begin_register_date` date DEFAULT NULL COMMENT '初次上牌时间',
  `vehicle_attribution_province_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属省份中文',
  `vehicle_attribution_province` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属省份',
  `vehicle_attribution_city` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属城市',
  `vehicle_attribution_city_cn` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属城市中文',
  `license_number` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌',
  `car_nature` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆性质 字典编码',
  `car_nature_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆性质-中文',
  `use_nature` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆用途 字典编码',
  `use_nature_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆用途中文',
  `is_modification` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否改装|1:改装 0:未改装',
  `original_price` decimal(11,0) DEFAULT NULL COMMENT '新车指导价',
  `remark` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注信息',
  `intake_method` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '进气方式',
  `intake_method_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '进气方式中文',
  `remark_photo` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注图片',
  `car_shape` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆外形：suv 跑车等，字典数据',
  `car_shape_cn` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车型：跑车、suv',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auto_id` (`auto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆信息详情表';

-- ----------------------------
-- Table structure for car_auto_log
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_log`;
CREATE TABLE `car_auto_log` (
  `id` bigint(20) NOT NULL COMMENT '日志ID',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆操作说明',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态： 1草稿、2审核中、3审核不通过、4撤回审核中、5等待上拍、6等待开拍、7正在竞拍、8成交-等待付款、9议价处理中、10付款信息待审核、11已付款、12过户中、14争议处理中、15手续回传确认中、16交易完成、17待评价、18交易关闭、19流拍\r\n',
  `time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `user_type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '''操作人类型  1买家 2管理员 3代办'',',
  `user_id` bigint(20) DEFAULT NULL COMMENT '操作用户ID',
  `user_mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人手机号',
  `user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆变更log表';

-- ----------------------------
-- Table structure for car_auto_photo
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_photo`;
CREATE TABLE `car_auto_photo` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `photo_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '照片',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `photo_type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '照片类型：1左前方、2左侧、3右后方、4前排座椅、5仪表盘、6后排座椅、7中控板、8车尾、9后备箱底板、10发动机舱、11出厂铭牌、12行驶证、13其他',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `create_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆照片表';

-- ----------------------------
-- Table structure for car_auto_photo_bak
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_photo_bak`;
CREATE TABLE `car_auto_photo_bak` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `photo_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '照片',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `create_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆照片表';

-- ----------------------------
-- Table structure for car_auto_procedures
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_procedures`;
CREATE TABLE `car_auto_procedures` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `purchase_tax` decimal(11,0) DEFAULT NULL COMMENT '购置税：1已征收，2未征收，3无',
  `driving_license` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否有行驶证：1有，2没有',
  `registration_certificate` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否有登记证：1有，2没有',
  `instruction` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '说明书：1有，2没有',
  `compulsory_insurance` date DEFAULT NULL COMMENT '抢险到期时间',
  `business_insurance` date DEFAULT NULL COMMENT '商业险到期日期',
  `transfer_number` int(11) DEFAULT NULL COMMENT '过户次数',
  `new_car_invoice` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '新车发票:1有，2没有',
  `quality_guarantee` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '新车质保：1保内，2已过保',
  `car_keys` int(2) DEFAULT NULL COMMENT '钥匙数量',
  `maintenance_manual` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '保养手册 1有，2没有',
  `year_insurance` date DEFAULT NULL COMMENT '年检到期时间',
  `ticket_of_transfer` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '过户票：1有，2没有',
  `move_to_where` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '前往何处：1本市外迁均可，2只能外迁，3只能本市',
  `cost_price` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手续补办费用：1买家承担，2卖家承担',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `illegal_price` decimal(10,0) DEFAULT NULL COMMENT '违章罚款数',
  `illegal_score` varchar(3) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违章扣分数',
  `illegal_who` enum('1','2') COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违章承担方：1买家，2卖家',
  `un_illegal` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否违章 1有 2没有',
  `freight_who` enum('1','2') COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运费承担方：1买家，2卖家',
  `mention_fee_who` enum('1','2') COLLATE utf8mb4_bin DEFAULT NULL COMMENT '提档费承担方：1买家，2卖家',
  `transfer_fee` enum('1','2') COLLATE utf8mb4_bin DEFAULT NULL COMMENT '过户费：1买家承担，2卖家承担',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auto_id` (`auto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆手续信息表';

-- ----------------------------
-- Table structure for car_auto_style
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_style`;
CREATE TABLE `car_auto_style` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `jyid` varchar(255) DEFAULT NULL COMMENT '精友ID',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌ID',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `brand_logo` varchar(512) DEFAULT NULL COMMENT '品牌LOGO',
  `brand_make_id` bigint(20) DEFAULT NULL COMMENT '品牌厂家Id',
  `brand_make_name` varchar(255) DEFAULT NULL COMMENT '品牌厂家名称',
  `auto_family_id` bigint(20) DEFAULT NULL COMMENT '车系ID',
  `auto_family_name` varchar(255) DEFAULT NULL COMMENT '车型名称',
  `group_id` varchar(255) DEFAULT NULL COMMENT '车身结构',
  `group_name` varchar(255) DEFAULT NULL COMMENT '车身特征',
  `vehicle_name` varchar(255) DEFAULT NULL COMMENT '车型全称',
  `engine_desc` varchar(255) DEFAULT NULL COMMENT '排量',
  `engine_model` varchar(255) DEFAULT NULL COMMENT '发动机类型',
  `gearbox_name` varchar(255) DEFAULT NULL COMMENT '变数器类型',
  `year_pattern` varchar(255) DEFAULT NULL COMMENT '年款',
  `market_date` varchar(255) DEFAULT NULL COMMENT '上市年月',
  `cfg_level` varchar(255) DEFAULT NULL COMMENT '上市年月',
  `seat` varchar(255) DEFAULT NULL COMMENT '座位数',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `alias` varchar(50) DEFAULT NULL COMMENT '车型别名',
  `year_pattern_id` bigint(20) DEFAULT NULL COMMENT '年款id',
  `alias_id` bigint(20) DEFAULT NULL COMMENT '别名id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车型表';

-- ----------------------------
-- Table structure for car_auto_transfer
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_transfer`;
CREATE TABLE `car_auto_transfer` (
  `id` bigint(20) NOT NULL COMMENT '车辆代办主表id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '检测车辆ID',
  `handle_user_id` bigint(20) DEFAULT NULL COMMENT '办理人人ID',
  `handle_user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '办理人姓名',
  `handle_user_mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '办理人手机号',
  `commission_photo` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办移交资料拍照',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '移交时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '移交人',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办状态：1过户事宜确定中、2出牌确认中、3交档确认中、4提档确认中、5手续上传中、6手续回传确认中、7手续回传不通过、8代办完结、9争议处理中、10.交易关闭',
  `move_to_where` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '前往何处：1本市外迁均可，2外迁，3本市',
  `move_address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '迁往具体地址',
  `transfer_doc` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '移交资料内容',
  `auth_manager` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `auth_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核意见',
  `is_breach` enum('1','2') COLLATE utf8mb4_bin DEFAULT '2' COMMENT '是否违约 1 是 2否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆过户主表';

-- ----------------------------
-- Table structure for car_auto_transfer_log
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_transfer_log`;
CREATE TABLE `car_auto_transfer_log` (
  `id` bigint(20) NOT NULL COMMENT '车辆代办主表id',
  `transfer_id` bigint(20) DEFAULT NULL COMMENT '过户主表ID',
  `type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '代办状态：1过户事宜确定中、2出牌确认中、3交档确认中、4提档确认中、5手续上传中、6手续回传确认中、7手续回传不通过、8代办完结、9争议处理中、10.交易关闭',
  `time` timestamp NULL DEFAULT NULL COMMENT '执行时间',
  `handle_time` timestamp NULL DEFAULT NULL COMMENT '办理时间',
  `file_type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文件类型 1 图片 2附件',
  `file_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '回传的照片',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `handle_person` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆过户日志主表';

-- ----------------------------
-- Table structure for car_brand
-- ----------------------------
DROP TABLE IF EXISTS `car_brand`;
CREATE TABLE `car_brand` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(25) DEFAULT NULL COMMENT '品牌名称',
  `logo` varchar(1024) DEFAULT NULL COMMENT '品牌logo',
  `jyid` varchar(512) DEFAULT NULL COMMENT '精友对应的ID',
  `pinyin_initials` varchar(10) DEFAULT NULL COMMENT '拼音首字母',
  `sort` int(5) DEFAULT '0' COMMENT '排序',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标示 0:未删除 1:已删除',
  `is_open` int(1) DEFAULT NULL COMMENT '是否启用 0启用  1未启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务品牌表';

-- ----------------------------
-- Table structure for car_brand_make
-- ----------------------------
DROP TABLE IF EXISTS `car_brand_make`;
CREATE TABLE `car_brand_make` (
  `id` bigint(20) NOT NULL COMMENT '主键Id',
  `name` varchar(255) DEFAULT NULL COMMENT '品牌厂家名称',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌Id',
  `brand_name` varchar(255) DEFAULT NULL,
  `jyid` varchar(255) DEFAULT NULL COMMENT '精友ID',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态 0未删除  1已删除',
  `is_open` int(1) DEFAULT NULL COMMENT '是否启用 0启用  1未启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_center
-- ----------------------------
DROP TABLE IF EXISTS `car_center`;
CREATE TABLE `car_center` (
  `id` bigint(20) NOT NULL COMMENT '二手车中心ID',
  `center_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '二手车中心编号',
  `center_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '二手车中心名称',
  `address` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '二手车中心地址',
  `center_tel` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1启用，2停用，3删除',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_person` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `del_person` bigint(20) DEFAULT NULL COMMENT '修改人',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_index` (`center_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='二手车中心表';

-- ----------------------------
-- Table structure for car_center_store
-- ----------------------------
DROP TABLE IF EXISTS `car_center_store`;
CREATE TABLE `car_center_store` (
  `id` bigint(20) NOT NULL,
  `center_id` bigint(20) DEFAULT NULL COMMENT '二手车中心ID',
  `store_id` bigint(20) DEFAULT NULL COMMENT '4S店id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='二手车中心管理店铺关联表';

-- ----------------------------
-- Table structure for car_customer_auth
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_auth`;
CREATE TABLE `car_customer_auth` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `address` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '通讯地址',
  `identity_number` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证号',
  `identity_photo_front` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证正面照片',
  `identity_photo_back` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证背面',
  `identity_photo_hand` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手持身份证',
  `auth_status` varchar(3) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核状态：1待审核，2审核通过，-1审核不通过',
  `auth_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核留言',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `auth_manager` bigint(20) DEFAULT NULL COMMENT '审核人',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `sign_pact` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '签约状态：1未签约，2已经签约，3签约失效',
  `province` bigint(20) DEFAULT NULL COMMENT '省份',
  `region` bigint(20) DEFAULT NULL COMMENT '地区市',
  `county` bigint(20) DEFAULT NULL COMMENT '县',
  `city_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省市县拼接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户认证表';

-- ----------------------------
-- Table structure for car_customer_breach
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_breach`;
CREATE TABLE `car_customer_breach` (
  `id` bigint(20) NOT NULL COMMENT '违约主键',
  `initiator` bigint(20) DEFAULT NULL COMMENT '发起人',
  `breach_obj_type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违约对象类型：1买家违约，2卖家违约，3车辆状况类问题，4手续类问题',
  `initiat_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发起人姓名',
  `initiat_mobile` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发起人电话',
  `initiat_time` timestamp NULL DEFAULT NULL COMMENT '发起时间',
  `initiat_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发起违约留言',
  `initiat_auth_manager` bigint(20) DEFAULT NULL COMMENT '发起审核人',
  `initiat_auth_time` timestamp NULL DEFAULT NULL COMMENT '发起审核时间',
  `initiat_auth_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '对发起审核留言',
  `initial_auth_file` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '争议处理附件',
  `money` decimal(11,0) DEFAULT NULL COMMENT '支付违约金金额',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `pay_auth_manager` bigint(20) DEFAULT NULL,
  `pay_auth_time` timestamp NULL DEFAULT NULL COMMENT '支付审核时间',
  `pay_auth_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付审核留言',
  `pay_auth_file` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付确认附件',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1争议处理中、2.平退（退车款、车辆状态更改未流拍，订单状态更改为交易关闭）、3买家违约-需支付违约金（车辆状态更改为流拍，订单状态更改为违约金支付确认中，支付完成订单交易关闭）、4买家违约--不需要支付违约金（车辆继续成交）、5争议议价--修改待付车款（订单状态变为等待支付，车辆状态变为成交-等待付款）、6卖家赔付（车辆继续成交）',
  `breach_source` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违约来源：1超时支付，2过户超时，3其他',
  `order_id` bigint(20) DEFAULT NULL COMMENT '违约订单',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '违约车辆',
  `breach_order_status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '争议订单状态',
  `breach_auto_status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '争议车辆状态',
  `breach_transfer_status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违约之前过户的状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户争议违约表';

-- ----------------------------
-- Table structure for car_customer_deposit
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_deposit`;
CREATE TABLE `car_customer_deposit` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `deposit_amount` decimal(11,2) DEFAULT NULL COMMENT '保证金',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '保证金时间',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `auth_manager` bigint(20) DEFAULT NULL,
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '保证金审核状态，1待审核，2通过，-1不通过',
  `auth_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核留言',
  `freeze_time` timestamp NULL DEFAULT NULL,
  `freeze_flag` varchar(2) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '是否冻结：-1冻结，其他不冻结',
  `deposit_no` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '保证金流水号',
  `use_status` enum('0','1') COLLATE utf8mb4_bin DEFAULT '0' COMMENT '保证金的使用状态 0 可用 1不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户保证金表';

-- ----------------------------
-- Table structure for car_customer_deposit_log
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_deposit_log`;
CREATE TABLE `car_customer_deposit_log` (
  `id` bigint(20) NOT NULL,
  `deposit_id` bigint(20) DEFAULT NULL COMMENT '保证金主表id',
  `deposit_amount` decimal(11,0) DEFAULT NULL COMMENT '保证金金额',
  `file_url` varchar(500) DEFAULT NULL COMMENT '附件路径',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `user_type` varchar(2) DEFAULT NULL COMMENT '操作人类型  1买家 2管理员 3代办',
  `log_type` varchar(2) DEFAULT NULL COMMENT '操作类型 1冻结  2解冻  ',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car_customer_deposit_log_copy
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_deposit_log_copy`;
CREATE TABLE `car_customer_deposit_log_copy` (
  `id` bigint(20) NOT NULL,
  `deposit_id` bigint(20) DEFAULT NULL COMMENT '保证金主表id',
  `deposit_amount` decimal(11,0) DEFAULT NULL COMMENT '保证金金额',
  `file_url` varchar(500) DEFAULT NULL COMMENT '附件路径',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `user_type` varchar(2) DEFAULT NULL COMMENT '操作人类型  1买家 2管理员 3代办',
  `log_type` varchar(2) DEFAULT NULL COMMENT '操作类型 1冻结  2解冻  ',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car_customer_entrust_car
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_entrust_car`;
CREATE TABLE `car_customer_entrust_car` (
  `id` bigint(20) NOT NULL COMMENT '委托id',
  `car_id` bigint(20) DEFAULT NULL COMMENT '车辆id',
  `auto_auction_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `entrust_fee` decimal(11,0) DEFAULT NULL COMMENT '委托价',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1启用，2停用',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='买家委托车辆表';

-- ----------------------------
-- Table structure for car_customer_follow_auto
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_follow_auto`;
CREATE TABLE `car_customer_follow_auto` (
  `id` bigint(20) NOT NULL COMMENT '关注ID',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '关注车辆ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '关注人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '关注时间',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1关注，2取消',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auto_user_id_index` (`auto_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户关注车辆表';

-- ----------------------------
-- Table structure for car_customer_group
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_group`;
CREATE TABLE `car_customer_group` (
  `id` bigint(20) NOT NULL COMMENT '分组id',
  `group_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户分组名称',
  `code` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分组编号',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1启用，-1停用',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户分组表';

-- ----------------------------
-- Table structure for car_customer_group_detail
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_group_detail`;
CREATE TABLE `car_customer_group_detail` (
  `id` bigint(20) NOT NULL COMMENT '分组id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '客户分组id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户分组关联明细表';

-- ----------------------------
-- Table structure for car_customer_level
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_level`;
CREATE TABLE `car_customer_level` (
  `id` bigint(20) NOT NULL COMMENT '分组id',
  `level_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户等级名称',
  `code` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户等级编号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` bigint(20) DEFAULT NULL COMMENT '创建人',
  `deposit_money` decimal(11,2) DEFAULT NULL COMMENT '该等级所需缴纳保证金金额',
  `is_open` enum('1','2') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否开启 1开启 2未开启',
  `is_default` enum('1','2') COLLATE utf8mb4_bin DEFAULT '2' COMMENT '是否默认1默认 2不默认',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户级别表';

-- ----------------------------
-- Table structure for car_customer_level_detail
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_level_detail`;
CREATE TABLE `car_customer_level_detail` (
  `id` bigint(20) NOT NULL COMMENT '级别id',
  `level_id` bigint(20) DEFAULT NULL COMMENT '客户级别id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户级别明细表';

-- ----------------------------
-- Table structure for car_customer_level_price
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_level_price`;
CREATE TABLE `car_customer_level_price` (
  `id` bigint(20) NOT NULL COMMENT '分组id',
  `level_id` bigint(20) DEFAULT NULL COMMENT '客户分组id',
  `fare_id` bigint(11) DEFAULT NULL COMMENT '该分组可出价格',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户级别对应出价配置表';

-- ----------------------------
-- Table structure for car_customer_log
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_log`;
CREATE TABLE `car_customer_log` (
  `id` bigint(20) NOT NULL COMMENT '日志ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '  1未实名认证、2认证未审核、3已认证-未签约、4已签约-未缴纳保证金、5签约审核中、6签约审核不通过、7签约审核通过、8冻结会员 9调整分组，10调整级别,11实名认证审核不通过 12退会 13 解冻会员',
  `msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作说明',
  `edit_user_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `edit_type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人类型  1买家 2管理员 3代办',
  `file` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '附件',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '时间',
  `log_id` bigint(20) DEFAULT NULL COMMENT '业务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户变更日志表';

-- ----------------------------
-- Table structure for car_customer_price_client
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_price_client`;
CREATE TABLE `car_customer_price_client` (
  `id` bigint(20) NOT NULL COMMENT '会员加价器关联ID',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `price_client_id` bigint(20) DEFAULT NULL COMMENT ' 加价器id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1绑定，-1解绑',
  `update_manager` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='会员加价器关联表';

-- ----------------------------
-- Table structure for car_customer_role
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_role`;
CREATE TABLE `car_customer_role` (
  `id` bigint(20) NOT NULL COMMENT '会员角色设置ID',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  `code` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1可用，-1停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户角色表';

-- ----------------------------
-- Table structure for car_customer_role_detail
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_role_detail`;
CREATE TABLE `car_customer_role_detail` (
  `id` bigint(20) NOT NULL COMMENT '分组id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '客户角色id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户角色分配明细表';

-- ----------------------------
-- Table structure for car_customer_sign
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_sign`;
CREATE TABLE `car_customer_sign` (
  `id` bigint(20) NOT NULL COMMENT '签约主键id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '签约客户',
  `status` varchar(2) DEFAULT NULL COMMENT '签约状态：1客户签字，2签约审核通过，3审核失败作废',
  `signature_time` timestamp NULL DEFAULT NULL COMMENT '签字时间',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `auth_manager` varchar(20) DEFAULT NULL COMMENT '审核人',
  `auth_msg` varchar(255) DEFAULT NULL COMMENT '签约审核留言',
  `is_delete` enum('0','1') DEFAULT '0' COMMENT '是否删除,0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户签约表';

-- ----------------------------
-- Table structure for car_customer_sign_log
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_sign_log`;
CREATE TABLE `car_customer_sign_log` (
  `id` bigint(20) NOT NULL COMMENT '签约主键id',
  `sign_id` bigint(20) DEFAULT NULL COMMENT '签约主表id',
  `log` varchar(500) DEFAULT NULL COMMENT '签约日志',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `type` varchar(1) DEFAULT NULL COMMENT '合同类型：1签字未盖章，2盖章生效',
  `pdf_file_url` varchar(255) DEFAULT NULL COMMENT '生成的pdf合同地址',
  `pic_file_url` varchar(255) DEFAULT NULL COMMENT '生成的图片合同地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户签约表';

-- ----------------------------
-- Table structure for car_customer_viewed_auto
-- ----------------------------
DROP TABLE IF EXISTS `car_customer_viewed_auto`;
CREATE TABLE `car_customer_viewed_auto` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '浏览人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '浏览时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户浏览车辆表';

-- ----------------------------
-- Table structure for car_data
-- ----------------------------
DROP TABLE IF EXISTS `car_data`;
CREATE TABLE `car_data` (
  `id` int(10) NOT NULL,
  `store_name` varchar(255) DEFAULT NULL,
  `license_number` varchar(255) DEFAULT NULL,
  `vin` varchar(255) DEFAULT NULL,
  `auto_brand_cn` varchar(255) DEFAULT NULL,
  `manufacture_date` varchar(255) DEFAULT NULL,
  `begin_register_date` varchar(255) DEFAULT NULL,
  `engine_capacity` varchar(255) DEFAULT NULL,
  `color_cn` varchar(255) DEFAULT NULL,
  `mileage` varchar(255) DEFAULT NULL,
  `if_agent` varchar(255) DEFAULT NULL,
  `reserve_price` varchar(255) DEFAULT NULL,
  `year_insurance` varchar(255) DEFAULT NULL,
  `use_nature_cn` varchar(255) DEFAULT NULL,
  `driving_license` varchar(255) DEFAULT NULL,
  `registration_certificate` varchar(255) DEFAULT NULL,
  `transfer_number` varchar(255) DEFAULT NULL,
  `purchase_tax` varchar(255) DEFAULT NULL,
  `business_insurance` varchar(255) DEFAULT NULL,
  `compulsory_insurance` varchar(255) DEFAULT NULL,
  `bxgs` varchar(255) DEFAULT NULL,
  `un_illegal` varchar(255) DEFAULT NULL,
  `sfz` varchar(255) DEFAULT NULL,
  `sfzzt` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `remark1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `car_evaluate`;
CREATE TABLE `car_evaluate` (
  `id` bigint(20) NOT NULL,
  `evaluate_type_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评价类型名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` timestamp NULL DEFAULT NULL,
  `modify_person` bigint(20) DEFAULT NULL COMMENT '修改人',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `if_show` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for car_evaluate_data
-- ----------------------------
DROP TABLE IF EXISTS `car_evaluate_data`;
CREATE TABLE `car_evaluate_data` (
  `id` bigint(20) NOT NULL,
  `auto_id` bigint(20) DEFAULT NULL COMMENT '车辆id',
  `content` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评价内容',
  `evaluate_type_id` bigint(20) DEFAULT NULL COMMENT '评价类型id',
  `star_level` int(1) DEFAULT NULL COMMENT '星级',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '评价时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '评价人',
  `role` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评价人角色',
  `tags` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评价标签中文组合',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for car_evaluate_data_tag
-- ----------------------------
DROP TABLE IF EXISTS `car_evaluate_data_tag`;
CREATE TABLE `car_evaluate_data_tag` (
  `id` bigint(20) NOT NULL,
  `evaluate_data_id` bigint(20) DEFAULT NULL COMMENT '评价主表id',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '评价标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for car_evaluate_setting
-- ----------------------------
DROP TABLE IF EXISTS `car_evaluate_setting`;
CREATE TABLE `car_evaluate_setting` (
  `id` bigint(20) NOT NULL,
  `evaluate_type_id` bigint(20) DEFAULT NULL COMMENT '评价类型ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` timestamp NULL DEFAULT NULL,
  `modify_person` bigint(20) DEFAULT NULL COMMENT '修改人',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '评价标签id',
  `target` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '被评价目标类型',
  `user_type` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评价人类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='评价项配置表';

-- ----------------------------
-- Table structure for car_evaluate_tag
-- ----------------------------
DROP TABLE IF EXISTS `car_evaluate_tag`;
CREATE TABLE `car_evaluate_tag` (
  `id` bigint(20) NOT NULL,
  `evaluate_Type_id` bigint(20) DEFAULT NULL COMMENT '评论类型id',
  `tag_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评论标签Id',
  `is_good` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签好坏 0描述好的标签，1描述坏的标签',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `modify_person` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for car_finance_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `car_finance_pay_log`;
CREATE TABLE `car_finance_pay_log` (
  `id` bigint(20) NOT NULL COMMENT '支付日志流水ID',
  `log_no` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付流水号',
  `type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付类型：1保证金，2违约款，3车款',
  `pay_fee` decimal(11,2) DEFAULT NULL COMMENT '付款金额',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付状态：1支付成功，2支付失败，3退款',
  `bank_order_no` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付银行流水号',
  `bank_order_log` text COLLATE utf8mb4_bin COMMENT '支付银行订单日志',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `pay_type` enum('1','2') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '支付方式：1线上，2线下',
  `pay_evidence` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '线下支付凭证',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `pay_way` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方式  1支付方式  现金，2银行卡，3支付宝，4微信，5其他''',
  `create_person` bigint(20) DEFAULT NULL,
  `create_person_type` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1买家，2卖家',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付日志表';

-- ----------------------------
-- Table structure for car_finance_platform_record
-- ----------------------------
DROP TABLE IF EXISTS `car_finance_platform_record`;
CREATE TABLE `car_finance_platform_record` (
  `id` bigint(20) NOT NULL COMMENT '变更记录ID',
  `pay_id` bigint(20) DEFAULT NULL COMMENT '支付流水id',
  `accumulated_income` decimal(11,0) DEFAULT NULL COMMENT '资金累计收入',
  `accumulated_outlay` decimal(11,0) DEFAULT NULL COMMENT '累计支出金额',
  `change_amount` decimal(11,0) DEFAULT NULL COMMENT '变更金额',
  `change_time` timestamp NULL DEFAULT NULL COMMENT '变更时间',
  `type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '变更类型：1保证金、2违约金、3车款、4其他',
  `pay_way` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方式  现金，2银行卡，3支付宝，4微信，5其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台资金变更记录表';

-- ----------------------------
-- Table structure for car_finance_record_store
-- ----------------------------
DROP TABLE IF EXISTS `car_finance_record_store`;
CREATE TABLE `car_finance_record_store` (
  `id` bigint(20) NOT NULL COMMENT '支付日志流水ID',
  `log_no` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付流水号',
  `type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付类型：1保证金，2违约款，3车款',
  `pay_fee` decimal(11,0) DEFAULT NULL COMMENT '付款金额',
  `pay_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '付款时间',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付状态：1支付成功，2支付失败，3退款',
  `bank_order_no` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付银行流水号',
  `bank_order_log` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付银行订单日志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付日志表';

-- ----------------------------
-- Table structure for car_finance_store_record
-- ----------------------------
DROP TABLE IF EXISTS `car_finance_store_record`;
CREATE TABLE `car_finance_store_record` (
  `id` bigint(20) NOT NULL COMMENT '变更记录ID',
  `pay_id` bigint(20) DEFAULT NULL COMMENT '支付流水id',
  `accumulated_income` decimal(11,0) DEFAULT NULL COMMENT '资金累计收入',
  `accumulated_outlay` decimal(11,0) DEFAULT NULL COMMENT '累计支出金额',
  `change_amount` decimal(11,0) DEFAULT NULL COMMENT '变更金额',
  `change_time` timestamp NULL DEFAULT NULL COMMENT '变更时间',
  `type` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '变更类型：1保证金、2违约金、3车款、4其他',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺id，4S店',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台资金变更记录表';

-- ----------------------------
-- Table structure for car_locale_auction
-- ----------------------------
DROP TABLE IF EXISTS `car_locale_auction`;
CREATE TABLE `car_locale_auction` (
  `id` bigint(20) NOT NULL COMMENT '现场竞拍--场次id',
  `code` int(50) NOT NULL AUTO_INCREMENT COMMENT '现场竞拍--场次编号',
  `title` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现场竞拍--场次主题',
  `region_id` varchar(225) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现场竞拍--竞拍场次--看见范围（那些客户组可以看见,多选，用字符串拼接）',
  `city_id` bigint(20) DEFAULT NULL COMMENT '现场拍--竞拍场次--该场次所在城市',
  `poster` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现场竞拍--拍卖场次---场次封面图片',
  `address` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现场竞拍--拍卖场次--拍卖地址',
  `gps_longitude` decimal(10,7) DEFAULT NULL COMMENT 'gps经度坐标',
  `gps_latitude` decimal(10,7) DEFAULT NULL COMMENT 'gps纬度',
  `start_time` datetime DEFAULT NULL COMMENT '现场竞拍--场次编号--开拍时间',
  `corporate_agent` bigint(20) DEFAULT NULL COMMENT '现场竞拍--场次编号--代办公司ID',
  `status` enum('1','2','3','4') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '现场竞拍--拍卖场次--状态：1待上拍，2等待开拍，3正在竞拍，4竞拍结束',
  `see_car_man` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现场竞拍--竞拍场次--看车联系人',
  `see_car_phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `see_car_time` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现场竞拍--竞拍场次--看车时间(手动输入如：随时看车、2018年2月18号9:00)',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_person` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `del_person` bigint(20) DEFAULT NULL COMMENT '删除人',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `del_flag` enum('0','1') CHARACTER SET utf8 DEFAULT '0' COMMENT '删除状态  0 未删除  1 已删除',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=20048 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='现场拍的场次信息';

-- ----------------------------
-- Table structure for car_locale_auction_car
-- ----------------------------
DROP TABLE IF EXISTS `car_locale_auction_car`;
CREATE TABLE `car_locale_auction_car` (
  `id` bigint(20) NOT NULL COMMENT '拍卖活动表',
  `auction_id` bigint(20) DEFAULT NULL COMMENT '拍卖活动id',
  `car_id` bigint(20) DEFAULT NULL COMMENT '拍卖车辆ID',
  `auto_auction_id` bigint(20) DEFAULT NULL COMMENT '车辆竞拍信息',
  `auction_code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆场次编号',
  `auction_status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '拍卖状态：0等待开拍，1拍卖中，2已成交，3流拍,4已设置二拍',
  `sort` int(5) DEFAULT NULL COMMENT '顺序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖场次车辆明细主表';

-- ----------------------------
-- Table structure for car_locale_auction_city
-- ----------------------------
DROP TABLE IF EXISTS `car_locale_auction_city`;
CREATE TABLE `car_locale_auction_city` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '拍卖地区名称',
  `frozen_capital` decimal(11,0) DEFAULT NULL COMMENT '冻结资金',
  `service_fee` decimal(11,0) DEFAULT NULL COMMENT '服务费',
  `breach_day` int(2) DEFAULT NULL COMMENT '违约天数',
  `breach_time` time DEFAULT NULL COMMENT '违约时间',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `editor` varchar(0) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `if_del` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否删除：1正常，-1删除隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖地区配置表';

-- ----------------------------
-- Table structure for car_manager_page
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_page`;
CREATE TABLE `car_manager_page` (
  `id` bigint(20) NOT NULL COMMENT '页面ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `designation` varchar(50) DEFAULT NULL COMMENT '页面名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `characterization` varchar(255) DEFAULT NULL COMMENT '页面描述',
  `status` varchar(5) DEFAULT '1' COMMENT '1启用，2不启用',
  `level` varchar(5) DEFAULT '' COMMENT '菜单级别，1，2，3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '页面创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '页面修改时间',
  `create_person` bigint(20) DEFAULT NULL,
  `modify_person` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面';

-- ----------------------------
-- Table structure for car_manager_page_1
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_page_1`;
CREATE TABLE `car_manager_page_1` (
  `id` bigint(20) NOT NULL COMMENT '页面ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `designation` varchar(50) DEFAULT NULL COMMENT '页面名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `characterization` varchar(255) DEFAULT NULL COMMENT '页面描述',
  `status` varchar(5) DEFAULT '1' COMMENT '1启用，2不启用',
  `level` varchar(5) DEFAULT '' COMMENT '菜单级别，1，2，3',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '页面创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '页面修改时间',
  `create_person` bigint(20) DEFAULT NULL,
  `modify_person` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面';

-- ----------------------------
-- Table structure for car_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_role`;
CREATE TABLE `car_manager_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `role_type_id` bigint(20) DEFAULT NULL COMMENT '1平台，2中心，3经销店，4代办公司',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色说明',
  `role_status` varchar(5) DEFAULT NULL COMMENT '1启用，2停用',
  `read_type` varchar(5) DEFAULT '1' COMMENT '1读取全部数据，2读取自己数据',
  `write_type` varchar(5) DEFAULT '1' COMMENT '1可操作全部数据，2只能操作自己的数据',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='权限 ';

-- ----------------------------
-- Table structure for car_manager_role_page
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_role_page`;
CREATE TABLE `car_manager_role_page` (
  `id` bigint(20) NOT NULL COMMENT '权限页面关系表ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `page_id` bigint(20) DEFAULT NULL COMMENT '页面ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限页面关系表';

-- ----------------------------
-- Table structure for car_manager_role_path
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_role_path`;
CREATE TABLE `car_manager_role_path` (
  `id` bigint(20) NOT NULL COMMENT '权限页面关系表ID',
  `auth_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `page_path` varchar(20) DEFAULT NULL COMMENT '页面ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限页面关系表';

-- ----------------------------
-- Table structure for car_manager_role_type
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_role_type`;
CREATE TABLE `car_manager_role_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `type_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限 ';

-- ----------------------------
-- Table structure for car_manager_user
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_user`;
CREATE TABLE `car_manager_user` (
  `id` bigint(20) NOT NULL COMMENT '管理员ID',
  `user_name` varchar(255) DEFAULT NULL,
  `user_key` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `user_password` varchar(255) DEFAULT NULL,
  `user_code` varchar(64) DEFAULT NULL COMMENT '员工编码',
  `user_status` varchar(5) DEFAULT '1' COMMENT '1正常，2冻结，3删除',
  `user_phone` varchar(30) DEFAULT NULL COMMENT '管理员手机',
  `user_photo` varchar(255) DEFAULT '' COMMENT '管理员头像',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `role_type_id` bigint(20) DEFAULT NULL COMMENT '1平台，2中心，3经销店，4代办公司',
  `department_id` bigint(20) DEFAULT NULL COMMENT '如果是中心为centrt_id,如果是代办公司为company_id,如果是店铺为store_id',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '管理员最后登陆时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '管理员创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `modify_person` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '管理员修改信息时间',
  `del_time` timestamp NULL DEFAULT NULL,
  `del_person` bigint(20) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_manager_user_0
-- ----------------------------
DROP TABLE IF EXISTS `car_manager_user_0`;
CREATE TABLE `car_manager_user_0` (
  `id` bigint(20) NOT NULL COMMENT '管理员ID',
  `user_name` varchar(255) DEFAULT NULL,
  `user_key` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `user_password` varchar(255) DEFAULT NULL,
  `user_code` varchar(64) DEFAULT NULL COMMENT '员工编码',
  `user_status` varchar(5) DEFAULT '1' COMMENT '1正常，2冻结，3删除',
  `user_phone` varchar(30) DEFAULT NULL COMMENT '管理员手机',
  `user_photo` varchar(255) DEFAULT '' COMMENT '管理员头像',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `role_type_id` bigint(20) DEFAULT NULL COMMENT '1平台，2中心，3经销店，4代办公司',
  `department_id` bigint(20) DEFAULT NULL COMMENT '如果是中心为centrt_id,如果是代办公司为company_id,如果是店铺为store_id',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '管理员最后登陆时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '管理员创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `modify_person` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '管理员修改信息时间',
  `del_time` timestamp NULL DEFAULT NULL,
  `del_person` bigint(20) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_message
-- ----------------------------
DROP TABLE IF EXISTS `car_message`;
CREATE TABLE `car_message` (
  `id` bigint(20) NOT NULL COMMENT '消息id',
  `title` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题',
  `content` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息内容',
  `open_obj_id` bigint(20) DEFAULT NULL COMMENT '打开对象id',
  `open_obj_type` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '打开对象类型：1系统消息，2车辆，3网页地址，4订单、5拍卖场次、6签约界面、7认证界面、8保证金',
  `push_time` timestamp NULL DEFAULT NULL COMMENT '推送时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '接收人',
  `is_read` enum('1','2') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否已读:1未读，2已读',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '阅读时间',
  `user_type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户类型：1买家，2卖家/中心，3代办',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='二手车消息表';

-- ----------------------------
-- Table structure for car_model_jy
-- ----------------------------
DROP TABLE IF EXISTS `car_model_jy`;
CREATE TABLE `car_model_jy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `model_id` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车型ID',
  `brand_id` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌ID',
  `series_id` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车系ID',
  `brand_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌名称',
  `model_name` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车型名称',
  `series_name` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车系名称',
  `body_struct` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车身结构',
  `years` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '年款',
  `conf_level` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置等级',
  `model_name_full` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车型名称全称',
  `drive_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驱动',
  `output_volume` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '排量',
  `transmission` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '变速器',
  `guiding_price` decimal(11,2) DEFAULT NULL COMMENT '指导价格',
  `be_on_time` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上市时间',
  `emission_standard` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '排放标准',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36530 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for car_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `car_operation_log`;
CREATE TABLE `car_operation_log` (
  `op_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '日志编号op_id',
  `op_name` varchar(75) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作人op_name',
  `op_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间op_time',
  `op_ip` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作人IPop_ip',
  `op_code` varchar(125) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作关键字op_code',
  `op_content` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作内容op_content',
  `op_is_seller` enum('0','1') CHARACTER SET utf8 DEFAULT NULL COMMENT '第三方标示0否1是op_is_seller',
  `third_id` bigint(20) DEFAULT NULL COMMENT '第三方编号third_id',
  `create_id` bigint(10) DEFAULT NULL COMMENT '创建人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for car_order
-- ----------------------------
DROP TABLE IF EXISTS `car_order`;
CREATE TABLE `car_order` (
  `id` bigint(20) NOT NULL COMMENT '订单id',
  `auction_id` bigint(20) DEFAULT NULL COMMENT '拍卖活动id',
  `order_no` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编号',
  `car_id` bigint(20) DEFAULT NULL COMMENT '车辆id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `transaction_fee` decimal(11,0) DEFAULT NULL COMMENT '成交价',
  `bargain_fee` decimal(11,0) DEFAULT NULL COMMENT '争议车款',
  `service_fee` decimal(11,0) DEFAULT NULL COMMENT '服务费【不包含代办费】',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `pay_fee` decimal(11,0) DEFAULT NULL COMMENT '支付金额',
  `pay_auth_time` timestamp NULL DEFAULT NULL COMMENT '支付审核时间',
  `pay_auth_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付审核人',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '  1等待付款、2付款信息待审核、3过户处理中、4争议处理中、5违约金支付确认中、6手续回传待确认、7交易完成、8交易关闭',
  `lock_fee` decimal(11,0) DEFAULT NULL COMMENT '保留价',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `pay_end_time` timestamp NULL DEFAULT NULL COMMENT '支付截至时间',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `agent_fee` decimal(11,0) DEFAULT NULL COMMENT '代办费用',
  `amount_fee` decimal(11,0) DEFAULT NULL COMMENT '应付总金额',
  `auction_bid_record_id` bigint(11) DEFAULT NULL COMMENT '拍卖记录ID',
  `auto_auction_id` bigint(20) DEFAULT NULL COMMENT '竞拍信息id，car_auto_auction',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖订单主表';

-- ----------------------------
-- Table structure for car_order_bargain
-- ----------------------------
DROP TABLE IF EXISTS `car_order_bargain`;
CREATE TABLE `car_order_bargain` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `car_id` bigint(20) DEFAULT NULL COMMENT '车辆ID',
  `customer_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `transaction_fee` decimal(11,0) DEFAULT NULL COMMENT '初始成交价',
  `bargain_fee` decimal(11,0) DEFAULT NULL COMMENT '议价金额',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '议价状态：1待议价；2议价成功，3议价失败',
  `auth_manager` bigint(20) DEFAULT NULL COMMENT '处理人',
  `auth_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `auth_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '处理意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖订单议价表';

-- ----------------------------
-- Table structure for car_order_bargain_auth
-- ----------------------------
DROP TABLE IF EXISTS `car_order_bargain_auth`;
CREATE TABLE `car_order_bargain_auth` (
  `id` bigint(20) NOT NULL COMMENT '拍卖活动表',
  `order_id` bigint(20) DEFAULT NULL COMMENT '拍卖活动id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单编号',
  `car_id` bigint(20) DEFAULT NULL COMMENT '车辆id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `transaction_fee` decimal(11,0) DEFAULT NULL COMMENT '成交价',
  `service_fee` decimal(11,0) DEFAULT NULL COMMENT '服务费',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `pay_fee` decimal(11,0) DEFAULT NULL COMMENT '支付金额',
  `pay_auth_time` timestamp NULL DEFAULT NULL COMMENT '支付审核时间',
  `pay_auth_manager` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付审核人',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1待支付、2议价中、3支付待审核，4支付审核失败、5支付完成、6退款、7违约取消',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖订单议价审核表';

-- ----------------------------
-- Table structure for car_order_fee_change
-- ----------------------------
DROP TABLE IF EXISTS `car_order_fee_change`;
CREATE TABLE `car_order_fee_change` (
  `id` bigint(20) NOT NULL COMMENT '变更id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `transaction_fee` decimal(11,0) DEFAULT NULL COMMENT '成交价',
  `create_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人账号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `old_fee` decimal(11,0) DEFAULT NULL COMMENT '上一次价格',
  `new_fee` decimal(11,0) DEFAULT NULL COMMENT '修改后费用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拍卖订单费用变更记录表';

-- ----------------------------
-- Table structure for car_order_log
-- ----------------------------
DROP TABLE IF EXISTS `car_order_log`;
CREATE TABLE `car_order_log` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `status` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1等待付款、2付款信息待审核、3过户处理中、4争议处理中、5违约金支付确认中、6手续回传待确认、7交易完成、8交易关闭',
  `status_cn` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单状态描述',
  `log_msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '操作用户ID',
  `user_mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人手机号',
  `user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单轨迹表';

-- ----------------------------
-- Table structure for car_photo_temp
-- ----------------------------
DROP TABLE IF EXISTS `car_photo_temp`;
CREATE TABLE `car_photo_temp` (
  `id` int(11) DEFAULT NULL,
  `main_photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car_price_client
-- ----------------------------
DROP TABLE IF EXISTS `car_price_client`;
CREATE TABLE `car_price_client` (
  `id` bigint(20) NOT NULL COMMENT '加价器ID',
  `car_center_id` bigint(20) DEFAULT NULL COMMENT '所属二手车中心',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '加价器名称',
  `code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '加价器编号',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1可用，-1停用',
  `create_manager` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_manager` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='加价器设备';

-- ----------------------------
-- Table structure for car_question
-- ----------------------------
DROP TABLE IF EXISTS `car_question`;
CREATE TABLE `car_question` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `classify_id` bigint(20) DEFAULT NULL COMMENT '所属分类id',
  `content` text COLLATE utf8mb4_bin COMMENT '内容',
  `is_open` enum('1','2') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否开启，1开启 2未开启',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `phone` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  `description` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '问题描述',
  `code` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `show_index` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1首页展示，2首页不展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='问题描述';

-- ----------------------------
-- Table structure for car_question_classify
-- ----------------------------
DROP TABLE IF EXISTS `car_question_classify`;
CREATE TABLE `car_question_classify` (
  `id` bigint(20) NOT NULL,
  `classify_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `is_open` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否开启，1开启，2未开启',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='问题分类表';

-- ----------------------------
-- Table structure for car_region_serverfee_setting
-- ----------------------------
DROP TABLE IF EXISTS `car_region_serverfee_setting`;
CREATE TABLE `car_region_serverfee_setting` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `region_setting_id` bigint(20) DEFAULT NULL COMMENT '拍卖地区配置ID',
  `start_closing_price` decimal(10,0) DEFAULT NULL COMMENT '成交价',
  `end_closing_price` decimal(10,0) DEFAULT NULL COMMENT '成交价',
  `service_fee` decimal(11,0) DEFAULT NULL COMMENT '服务费',
  `show_text` varchar(255) DEFAULT NULL COMMENT '显示的服务费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='拍卖地区服务费配置表';

-- ----------------------------
-- Table structure for car_region_setting
-- ----------------------------
DROP TABLE IF EXISTS `car_region_setting`;
CREATE TABLE `car_region_setting` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `region_id` bigint(20) DEFAULT NULL COMMENT '竞拍城市ID',
  `region_name` varchar(20) DEFAULT NULL COMMENT '拍卖地区名称',
  `frozen_capital` decimal(11,0) DEFAULT NULL COMMENT '冻结资金',
  `pay_breach_day` int(10) DEFAULT NULL COMMENT '付款违约天数',
  `pay_breach_time` time DEFAULT NULL COMMENT '付款违约时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `create_person` bigint(20) DEFAULT NULL COMMENT '操作人',
  `status` varchar(2) DEFAULT NULL COMMENT '是否启用：1正常，2不启用',
  `transfer_breach_day` int(10) DEFAULT NULL COMMENT '过户违约天',
  `transfer_breach_time` time DEFAULT NULL COMMENT '过户违约时间',
  `agent_fee` decimal(10,0) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `update_person` bigint(20) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='拍卖地区配置表';

-- ----------------------------
-- Table structure for car_relate_status
-- ----------------------------
DROP TABLE IF EXISTS `car_relate_status`;
CREATE TABLE `car_relate_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(3) DEFAULT NULL COMMENT '状态码',
  `status_name` varchar(50) DEFAULT NULL COMMENT '状态名称',
  `type` varchar(1) CHARACTER SET ucs2 DEFAULT NULL COMMENT '类型  1 会员相关状态 2.车辆相关状态、3代办相关状态 4订单相关状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car_reservation_see_car
-- ----------------------------
DROP TABLE IF EXISTS `car_reservation_see_car`;
CREATE TABLE `car_reservation_see_car` (
  `id` bigint(20) NOT NULL,
  `reservation_id` bigint(20) DEFAULT NULL COMMENT '预约的车辆id或者场次id',
  `contact_phone` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '预约时间',
  `type` varchar(2) DEFAULT NULL COMMENT '类型 1车辆 2场次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预约看车记录表';

-- ----------------------------
-- Table structure for car_store
-- ----------------------------
DROP TABLE IF EXISTS `car_store`;
CREATE TABLE `car_store` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `code` varchar(50) DEFAULT NULL COMMENT '4s店编号',
  `name` varchar(50) DEFAULT NULL COMMENT '4s店名称',
  `simple_name` varchar(30) DEFAULT NULL COMMENT '4s店简称',
  `brand_name` varchar(20) DEFAULT NULL COMMENT '经营品牌名称(由各个品牌拼接而成)',
  `store_photo` varchar(512) DEFAULT NULL COMMENT '4s店照片',
  `area` varchar(255) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `service_tel` varchar(20) DEFAULT NULL COMMENT '服务电话',
  `latitude` double(40,28) DEFAULT NULL COMMENT '坐标维度',
  `longitude` double(40,28) DEFAULT NULL COMMENT '坐标经度',
  `status` varchar(5) DEFAULT '1' COMMENT '1正常,2停用，3删除',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_person` bigint(20) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_person` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='4s店铺信息表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `t_id` bigint(20) NOT NULL COMMENT '用户注册ID',
  `t_username` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名，默认同手机号',
  `t_password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `t_realname` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `t_address` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `t_city` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所在城市',
  `t_sex` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别：1男，2女',
  `t_phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `t_registTime` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `t_loginTime` timestamp NULL DEFAULT NULL COMMENT '最近登陆时间',
  `t_vipId` bigint(20) DEFAULT NULL COMMENT '会员等级id',
  `t_storeId` bigint(20) DEFAULT NULL COMMENT '所属店铺',
  `t_remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `t_updateTime` timestamp NULL DEFAULT NULL COMMENT '上次修改时间',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息';

-- ----------------------------
-- Table structure for wt_app_user
-- ----------------------------
DROP TABLE IF EXISTS `wt_app_user`;
CREATE TABLE `wt_app_user` (
  `id` bigint(20) NOT NULL COMMENT '用户注册ID',
  `user_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员编号',
  `user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名，默认同手机号',
  `password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `address` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '通信地址',
  `city` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所在城市',
  `head_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `sex` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别：1男，2女',
  `mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `regist_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '最近登陆时间',
  `login_ip` varchar(0) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆ip',
  `status` enum('1','2','3','4','5','6','7','8') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '1未实名认证、2认证未审核、3已认证-未签约、4已签约-未缴纳保证金、5签约审核中、6签约不通过-保证金完成、7签约审核通过',
  `user_num` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车商编号',
  `user_level_id` bigint(20) DEFAULT NULL COMMENT '会员等级id',
  `tel_1` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备用电话',
  `tel_2` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备用电话',
  `store_id` bigint(20) DEFAULT NULL COMMENT '所属店铺',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_share` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否共享 1  共享 2 不共享',
  `user_status` enum('1','2','3') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '1正常，2冻结，3删除,',
  `auction_plate_num` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '拍牌号',
  `manual_add` enum('1','2','0') COLLATE utf8mb4_bin DEFAULT '0' COMMENT ' 0 注册 1导入，2添加',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台对接各应用系统，统一用户登录注册';

-- ----------------------------
-- Table structure for wt_app_user_0
-- ----------------------------
DROP TABLE IF EXISTS `wt_app_user_0`;
CREATE TABLE `wt_app_user_0` (
  `id` bigint(20) NOT NULL COMMENT '用户注册ID',
  `user_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员编号',
  `user_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名，默认同手机号',
  `password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `address` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '通信地址',
  `city` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所在城市',
  `head_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `sex` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别：1男，2女',
  `mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `regist_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '最近登陆时间',
  `login_ip` varchar(0) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆ip',
  `status` enum('1','2','3','4','5','6','7','8') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '1未实名认证、2认证未审核、3已认证-未签约、4已签约-未缴纳保证金、5签约审核中、6签约不通过-保证金完成、7签约审核通过',
  `user_num` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车商编号',
  `user_level_id` bigint(20) DEFAULT NULL COMMENT '会员等级id',
  `tel_1` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备用电话',
  `tel_2` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备用电话',
  `store_id` bigint(20) DEFAULT NULL COMMENT '所属店铺',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_share` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否共享 1  共享 2 不共享',
  `user_status` enum('1','2','3') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '1正常，2冻结，3删除,',
  `auction_plate_num` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台对接各应用系统，统一用户登录注册';

-- ----------------------------
-- Table structure for wt_app_user_token
-- ----------------------------
DROP TABLE IF EXISTS `wt_app_user_token`;
CREATE TABLE `wt_app_user_token` (
  `id` bigint(20) NOT NULL COMMENT '用户注册ID',
  `user_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名，默认同手机号',
  `app_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用ID，其他平台需要与运通用户打通，需要创建应用并生成appId，例如二手车业务',
  `token` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆授权token信息',
  `open_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运通用户体系对外唯一标示',
  `update_time` timestamp NULL DEFAULT NULL COMMENT 'token刷新时间',
  `expiresIn` bigint(11) DEFAULT NULL COMMENT '凭证有效期时长：单位秒默认24小时',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台统一用户登陆注册，应用登陆授权使用';

-- ----------------------------
-- Table structure for wt_city
-- ----------------------------
DROP TABLE IF EXISTS `wt_city`;
CREATE TABLE `wt_city` (
  `city_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '城市ID ',
  `city_name` varchar(255) DEFAULT NULL COMMENT '城市名称',
  `city_sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省份ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8 COMMENT='地区城市表';

-- ----------------------------
-- Table structure for wt_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `wt_dictionary`;
CREATE TABLE `wt_dictionary` (
  `id` bigint(20) NOT NULL COMMENT '字典表id',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典名称',
  `code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典编号',
  `p_id` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `edit_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编辑人账号',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：1启用，2停用',
  `sort` int(5) DEFAULT NULL COMMENT '排序号',
  `value` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典项对应的值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典数据表';

-- ----------------------------
-- Table structure for wt_district
-- ----------------------------
DROP TABLE IF EXISTS `wt_district`;
CREATE TABLE `wt_district` (
  `district_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '区县ID',
  `district_name` varchar(255) DEFAULT NULL COMMENT '区县名称 ',
  `district_sort` bigint(10) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2999 DEFAULT CHARSET=utf8 COMMENT='地区区县表 ';

-- ----------------------------
-- Table structure for wt_province
-- ----------------------------
DROP TABLE IF EXISTS `wt_province`;
CREATE TABLE `wt_province` (
  `province_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '省份ID ',
  `province_name` varchar(255) DEFAULT NULL COMMENT '省份名称',
  `province_sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记 ',
  `short_name` varchar(5) DEFAULT NULL COMMENT '省份简称',
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='地区省份表';

-- ----------------------------
-- Table structure for wt_region
-- ----------------------------
DROP TABLE IF EXISTS `wt_region`;
CREATE TABLE `wt_region` (
  `id` bigint(20) NOT NULL COMMENT '区域ID',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区域名称',
  `code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区域编号',
  `edit_user` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编辑人',
  `edit_time` timestamp NULL DEFAULT NULL COMMENT '编辑时间',
  `sort` int(3) DEFAULT NULL COMMENT '排序',
  `status` enum('1','2') COLLATE utf8mb4_bin DEFAULT '1' COMMENT '状态：1可用，2停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='运通业务区域';
