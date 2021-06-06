package basilisk.benchmarkService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


/**
 * @author Fakhr Shaheen
 */
@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
public class BaseEntity {
    private Long id;
}
