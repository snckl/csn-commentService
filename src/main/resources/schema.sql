use commentdb;
CREATE TABLE IF NOT EXISTS `comment` (
                           `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           `content` varchar(1024) NOT NULL,
                           `created_at` datetime NOT NULL,
                           `created_by` varchar(255) NOT NULL,
                           `updated_at` datetime DEFAULT NULL,
                           `updated_by` varchar(255) DEFAULT NULL);