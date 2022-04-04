package org.dicegroup.basilisk.benchmarkService.domain.Iguana;

/**
 * @author Fakhr Shaheen
 */
public class SecuredIguanaConnection extends IguanaConnection {

    private String user;
    private String password;


    public SecuredIguanaConnection(String name, String endpoint, String updateEndpoint , String user, String password) {
        super(name,endpoint,updateEndpoint);
        this.user = user;
        this.password = password;
    }
}
