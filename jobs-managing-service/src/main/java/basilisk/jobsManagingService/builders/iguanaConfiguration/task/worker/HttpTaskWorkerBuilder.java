package basilisk.jobsManagingService.builders.iguanaConfiguration.task.worker;


import org.springframework.beans.factory.annotation.Value;

/**
 * @author Fakhr Shaheen
 */
public abstract class HttpTaskWorkerBuilder extends TaskWorkerBuilder {

    @Value("${IguanaConfiguration.DefaultValues.Worker.ParameterName}")
    protected String parameterName;
    @Value("${IguanaConfiguration.DefaultValues.Worker.ResponseType}")
    protected String responseType;
    @Value("${IguanaConfiguration.DefaultValues.Worker.Language}")
    protected String language;



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
