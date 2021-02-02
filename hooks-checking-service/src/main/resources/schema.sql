
CREATE TABLE `git-repo`
(
    `id`          integer NOT NULL AUTO_INCREMENT ,
    `name`        varchar(100) NOT NULL ,
    `owner`       varchar(100) NOT NULL ,
    `is-private`  bit NOT NULL ,
    `oAuth-token` varchar(200) NULL ,
    `branch`      varchar(100) NULL ,

    PRIMARY KEY (`id`)
);

CREATE TABLE `git-hook`
(
    `id`                   integer NOT NULL AUTO_INCREMENT ,
    `type`                 enum('release','pullrequest','branchcommit') NOT NULL ,
    `commit-url`           varchar(100) NOT NULL ,
    `commit-sha1`          varchar(40) NOT NULL ,
    `commit-creation-date` datetime NOT NULL ,
    `repo-id`              integer NOT NULL ,

    PRIMARY KEY (`id`),
    KEY `fkIdx_28` (`repo-id`),
    CONSTRAINT `FK_27` FOREIGN KEY `fkIdx_28` (`repo-id`) REFERENCES `git-repo` (`id`)
);










