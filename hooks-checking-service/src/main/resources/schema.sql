
CREATE TABLE `git_repo`
(
    `id`          integer NOT NULL AUTO_INCREMENT ,
    `name`        varchar(100) NOT NULL ,
    `owner`       varchar(100) NOT NULL ,
    `is_private`  bit NOT NULL ,
    `oAuth_token` varchar(200) NULL ,
    `branch`      varchar(100) NULL ,
    `is_branch`   varchar(3) NOT NULL ,
    `type`    enum('release','pull_request','branch') NOT NULL ,

    PRIMARY KEY (`id`)
);

-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `git_hook`

CREATE TABLE `git_hook`
(
    `id`                   integer NOT NULL AUTO_INCREMENT ,
    `commit_url`           varchar(100) NOT NULL ,
    `commit_sha1`          varchar(40) NOT NULL ,
    `commit_creation_date` datetime NOT NULL ,
    `repo_id`               integer NOT NULL ,

    PRIMARY KEY (`id`),
    CONSTRAINT s1 FOREIGN KEY  (`repo_id`) REFERENCES `git_repo` (`id`)
);


-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `docker_repo`

CREATE TABLE `docker_repo`
(
    `id`         integer NOT NULL AUTO_INCREMENT ,
    `name`       varchar(45) NOT NULL ,
    `owner`      varchar(45) NULL ,
    `is_private` bit NOT NULL ,

    PRIMARY KEY (`id`)
);

-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `docker_hook`

CREATE TABLE `docker_hook`
(
    `id`                  integer NOT NULL AUTO_INCREMENT ,
    `tag`                 varchar(100) NOT NULL ,
    `digest`              varchar(64) NOT NULL ,
    `url`                 varchar(100) NOT NULL ,
    `image_creation_date` datetime NOT NULL ,
    `repo_id`             integer NOT NULL ,

    PRIMARY KEY (`id`),
    CONSTRAINT s2 FOREIGN KEY (`repo_id`) REFERENCES `docker_repo` (`id`)
);

















