
CREATE TABLE `git_repo`
(
    `id`          integer NOT NULL AUTO_INCREMENT ,
    `name`        varchar(100) NOT NULL ,
    `owner`       varchar(100) NOT NULL ,
    `is_private`  bit NOT NULL ,
    `oAuth_token` varchar(200) NULL ,
    `branch`      varchar(100) NULL ,

    PRIMARY KEY (`id`)
);

CREATE TABLE `git_hook`
(
    `id`                   integer NOT NULL AUTO_INCREMENT ,
    `type`                 enum('release','pullrequest','branchcommit') NOT NULL ,
    `commit_url`           varchar(100) NOT NULL ,
    `commit_sha1`          varchar(40) NOT NULL ,
    `commit_creation_date` datetime NOT NULL ,
    `repo_d`               integer NOT NULL ,

    PRIMARY KEY (`id`),
    KEY `fkIdx_28` (`repo_d`),
    CONSTRAINT `FK_27` FOREIGN KEY `fkIdx_28` (`repo_d`) REFERENCES `git_repo` (`id`)
);








