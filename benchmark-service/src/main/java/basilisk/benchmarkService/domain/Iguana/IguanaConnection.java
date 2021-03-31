package basilisk.benchmarkService.domain.Iguana;


import basilisk.benchmarkService.domain.BaseEntity;

/**
 * @author Fakhr Shaheen
 */
public class IguanaConnection extends BaseEntity {

    private String name;
    private String endpoint;
    private String updateEndpoint;

    private static IguanaConnectionBuilder builder;

    public IguanaConnection(String name, String endpoint, String updateEndpoint) {
        this.name = name;
        this.endpoint = endpoint;
        this.updateEndpoint=updateEndpoint;
    }

    public static IguanaConnectionBuilder builder()
    {
            return  builder;
    }

    private static class IguanaConnectionBuilder {

        private String name;
        private String endpoint;
        private String updateEndpoint;
        private String user;
        private String password;

        public IguanaConnectionBuilder(String name, String endpoint) {
            this.name = name;
            this.endpoint = endpoint;
        }

        public IguanaConnectionBuilder setUpdateEndpoint(String updateEndpoint)
        {
            this.updateEndpoint=updateEndpoint;
            return this;
        }

        public IguanaConnectionBuilder setUser(String user)
        {
            this.user=user;
            return this;
        }

        public IguanaConnectionBuilder setPassword(String password)
        {
            this.password=password;
            return this;
        }

        public IguanaConnection build()
        {
            if(user!=null)
                return new SecuredIguanaConnection(name,endpoint,updateEndpoint,user,password);
            else
                return new IguanaConnection(name,endpoint,updateEndpoint);

        }


    }
}
