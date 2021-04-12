package basilisk.benchmarkService.domain.Iguana;


import basilisk.benchmarkService.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Fakhr Shaheen
 */
@AllArgsConstructor
@NoArgsConstructor
public class Dataset extends BaseEntity {

    private String name;
    private String file;
}
