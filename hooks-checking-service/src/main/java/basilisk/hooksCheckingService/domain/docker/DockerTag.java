
package basilisk.hooksCheckingService.domain.docker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "creator",
        "id",
        "image_id",
        "images",
        "last_updated",
        "last_updater",
        "last_updater_username",
        "name",
        "repository",
        "full_size",
        "v2",
        "tag_status",
        "tag_last_pulled",
        "tag_last_pushed"
})
public class DockerTag {

    @JsonProperty("creator")
    private Integer creator;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("image_id")
    private Object imageId;
    @JsonProperty("images")
    private List<DockerImage> images = null;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("last_updater")
    private Integer lastUpdater;
    @JsonProperty("last_updater_username")
    private String lastUpdaterUsername;
    @JsonProperty("name")
    private String name;
    @JsonProperty("repository")
    private Integer repository;
    @JsonProperty("full_size")
    private Integer fullSize;
    @JsonProperty("v2")
    private Boolean v2;
    @JsonProperty("tag_status")
    private String tagStatus;
    @JsonProperty("tag_last_pulled")
    private String tagLastPulled;
    @JsonProperty("tag_last_pushed")
    private String tagLastPushed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("creator")
    public Integer getCreator() {
        return creator;
    }

    @JsonProperty("creator")
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("image_id")
    public Object getImageId() {
        return imageId;
    }

    @JsonProperty("image_id")
    public void setImageId(Object imageId) {
        this.imageId = imageId;
    }

    @JsonProperty("images")
    public List<DockerImage> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<DockerImage> images) {
        this.images = images;
    }

    @JsonProperty("last_updated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("last_updated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("last_updater")
    public Integer getLastUpdater() {
        return lastUpdater;
    }

    @JsonProperty("last_updater")
    public void setLastUpdater(Integer lastUpdater) {
        this.lastUpdater = lastUpdater;
    }

    @JsonProperty("last_updater_username")
    public String getLastUpdaterUsername() {
        return lastUpdaterUsername;
    }

    @JsonProperty("last_updater_username")
    public void setLastUpdaterUsername(String lastUpdaterUsername) {
        this.lastUpdaterUsername = lastUpdaterUsername;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("repository")
    public Integer getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(Integer repository) {
        this.repository = repository;
    }

    @JsonProperty("full_size")
    public Integer getFullSize() {
        return fullSize;
    }

    @JsonProperty("full_size")
    public void setFullSize(Integer fullSize) {
        this.fullSize = fullSize;
    }

    @JsonProperty("v2")
    public Boolean getV2() {
        return v2;
    }

    @JsonProperty("v2")
    public void setV2(Boolean v2) {
        this.v2 = v2;
    }

    @JsonProperty("tag_status")
    public String getTagStatus() {
        return tagStatus;
    }

    @JsonProperty("tag_status")
    public void setTagStatus(String tagStatus) {
        this.tagStatus = tagStatus;
    }

    @JsonProperty("tag_last_pulled")
    public String getTagLastPulled() {
        return tagLastPulled;
    }

    @JsonProperty("tag_last_pulled")
    public void setTagLastPulled(String tagLastPulled) {
        this.tagLastPulled = tagLastPulled;
    }

    @JsonProperty("tag_last_pushed")
    public String getTagLastPushed() {
        return tagLastPushed;
    }

    @JsonProperty("tag_last_pushed")
    public void setTagLastPushed(String tagLastPushed) {
        this.tagLastPushed = tagLastPushed;
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