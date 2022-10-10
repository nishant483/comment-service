-- DROP TABLE IF EXISTS SOCIAL_USERS;
CREATE TABLE IF NOT EXISTS `SOCIAL_USERS` (
                         `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `name` varchar(256) NOT NULL,
                         `email` varchar(256) NOT NULL,
                         `created_timestamp` timestamp NOT NULL
);

-- DROP TABLE IF EXISTS Post;
CREATE TABLE IF NOT EXISTS `Post` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `link_url` VARCHAR(1000) NOT NULL ,
                                    `post_type` VARCHAR(256) NOT NULL , `text` VARCHAR(1000) NULL ,
                                        `created_timestamp` TIMESTAMP NOT NULL , `user_id` VARCHAR NOT NULL
                                        );

-- DROP TABLE IF EXISTS `reaction`;
CREATE TABLE IF NOT EXISTS `reaction` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                 `reaction_type` enum('LIKE','DISLIKE') default NULL,
                  `comment_id` VARCHAR(256) NOT NULL ,
                   `post_id` VARCHAR(1000) NOT NULL ,
                   `created_timestamp` TIMESTAMP NOT NULL ,
                    `user_id` VARCHAR(1000) NOT NULL
                     );

-- DROP TABLE IF EXISTS `reaction_counts`;
CREATE TABLE IF NOT EXISTS `reaction_counts` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `post_id` VARCHAR NOT NULL,
                                              `comment_id` INT NOT NULL,
                                              `reaction_type` enum('LIKE','DISLIKE') default NULL,
                                              `reaction_count` INT NOT NULL
                                            );

-- DROP TABLE IF EXISTS `Comment`;
CREATE TABLE IF NOT EXISTS `Comment` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `comment_text` LONGTEXT NOT NULL ,
                      `created_timestamp` TIMESTAMP NOT NULL , `user_id` VARCHAR NOT NULL , `post_id` VARCHAR NOT NULL,
                                       `parent_id` INT NOT NULL);

-- DROP TABLE IF EXISTS `Comment_Closure`;
CREATE TABLE IF NOT EXISTS `Comment_Closure` ( `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `ancestor_id` INT NOT NULL,
                            `descendent_id` INT NOT NULL ,
                             `depth` INT NOT NULL);