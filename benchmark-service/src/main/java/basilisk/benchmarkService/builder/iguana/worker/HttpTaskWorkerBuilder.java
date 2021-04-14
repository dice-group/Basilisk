package basilisk.benchmarkService.builder.iguana.worker;


/**
 * @author Fakhr Shaheen
 */
public abstract class HttpTaskWorkerBuilder extends TaskWorkerBuilder {

    protected String parameterName="query";
    protected String responseType="application/sparql-results+json";
    protected String language="lang.SPARQL";



    public HttpTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
    }

    public HttpTaskWorkerBuilder setParameterName(String parameterName)
    {
        this.parameterName=parameterName;
        return this;
    }

    public HttpTaskWorkerBuilder setResponseType(String responseType)
    {
        this.responseType=responseType;
        return this;
    }

    public HttpTaskWorkerBuilder setLanguage(String language)
    {
        this.language=language;
        return this;
    }

}
