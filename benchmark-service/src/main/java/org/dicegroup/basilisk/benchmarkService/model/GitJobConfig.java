package org.dicegroup.basilisk.benchmarkService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GitJobConfig extends BaseEntity{

    private String url;
    private String commit_sha1;
}
