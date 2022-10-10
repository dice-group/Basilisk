package org.dicegroup.basilisk.repositoryService.model.repo.docker.api;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "architecture",
        "features",
        "variant",
        "digest",
        "os",
        "os_features",
        "os_version",
        "size",
        "status",
        "last_pulled",
        "last_pushed"
})

public class DockerApiImage {

    @JsonProperty("architecture")
    private String architecture;
    @JsonProperty("features")
    private String features;
    @JsonProperty("variant")
    private Object variant;
    @JsonProperty("digest")
    private String digest;
    @JsonProperty("os")
    private String os;
    @JsonProperty("os_features")
    private String osFeatures;
    @JsonProperty("os_version")
    private Object osVersion;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("status")
    private String status;
    @JsonProperty("last_pulled")
    private String lastPulled;
    @JsonProperty("last_pushed")
    private Date lastPushed;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("architecture")
    public String getArchitecture() {
        return architecture;
    }

    @JsonProperty("architecture")
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    @JsonProperty("features")
    public String getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(String features) {
        this.features = features;
    }

    @JsonProperty("variant")
    public Object getVariant() {
        return variant;
    }

    @JsonProperty("variant")
    public void setVariant(Object variant) {
        this.variant = variant;
    }

    @JsonProperty("digest")
    public String getDigest() {
        return digest;
    }

    @JsonProperty("digest")
    public void setDigest(String digest) {
        this.digest = digest;
    }

    @JsonProperty("os")
    public String getOs() {
        return os;
    }

    @JsonProperty("os")
    public void setOs(String os) {
        this.os = os;
    }

    @JsonProperty("os_features")
    public String getOsFeatures() {
        return osFeatures;
    }

    @JsonProperty("os_features")
    public void setOsFeatures(String osFeatures) {
        this.osFeatures = osFeatures;
    }

    @JsonProperty("os_version")
    public Object getOsVersion() {
        return osVersion;
    }

    @JsonProperty("os_version")
    public void setOsVersion(Object osVersion) {
        this.osVersion = osVersion;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("last_pulled")
    public String getLastPulled() {
        return lastPulled;
    }

    @JsonProperty("last_pulled")
    public void setLastPulled(String lastPulled) {
        this.lastPulled = lastPulled;
    }

    @JsonProperty("last_pushed")
    public Date getLastPushed() {
        return lastPushed;
    }

    @JsonProperty("last_pushed")
    public void setLastPushed(Date lastPushed) {
        this.lastPushed = lastPushed;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}