ALTER TABLE `t_pano_proj`
ADD COLUMN `snow_type`  varchar(50) NULL COMMENT '下雪类型' AFTER `logo_url`,
ADD COLUMN `is_fps`  char(1) NULL DEFAULT '0' COMMENT '显示fps0否1是' AFTER `snow_type`;

ALTER TABLE `t_pano_scene`
ADD COLUMN `sound_src`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场景音乐' AFTER `vlookat`,
ADD COLUMN `video_src`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场景视频解说' AFTER `sound_src`;

ALTER TABLE `t_pano_spots`
ADD COLUMN `is_onclick`  char(1) NULL DEFAULT '0' COMMENT '可否点击0否1是' AFTER `url`;
