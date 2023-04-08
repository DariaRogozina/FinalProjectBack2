
package dto.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "cdnUrl"
})
@Generated("jsonschema2pojo")
@Data
public class MainImage {

    @JsonProperty("id")
    private Object id;
    @JsonProperty("cdnUrl")
    private String cdnUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public MainImage() {
    }

    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    @JsonProperty("cdnUrl")
    public String getCdnUrl() {
        return cdnUrl;
    }

    @JsonProperty("cdnUrl")
    public void setCdnUrl(String cdnUrl) {
        this.cdnUrl = cdnUrl;
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
