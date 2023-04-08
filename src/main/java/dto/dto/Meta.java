
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
    "prevPage",
    "nextPage",
    "count"
})
@Data
@Generated("jsonschema2pojo")
public class Meta {

    @JsonProperty("prevPage")
    private Long prevPage;
    @JsonProperty("nextPage")
    private Long nextPage;
    @JsonProperty("count")
    private Long count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public Meta() {
    }

    @JsonProperty("prevPage")
    public Long getPrevPage() {
        return prevPage;
    }

    @JsonProperty("prevPage")
    public void setPrevPage(Long prevPage) {
        this.prevPage = prevPage;
    }

    @JsonProperty("nextPage")
    public Long getNextPage() {
        return nextPage;
    }

    @JsonProperty("nextPage")
    public void setNextPage(Long nextPage) {
        this.nextPage = nextPage;
    }

    @JsonProperty("count")
    public Long getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Long count) {
        this.count = count;
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
