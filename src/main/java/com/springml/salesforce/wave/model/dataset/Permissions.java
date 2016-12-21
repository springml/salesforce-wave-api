package com.springml.salesforce.wave.model.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "manage",
    "modify",
    "view"
})
public class Permissions {

    @JsonProperty("manage")
    private Boolean manage;
    @JsonProperty("modify")
    private Boolean modify;
    @JsonProperty("view")
    private Boolean view;

    @JsonProperty("manage")
    public Boolean getManage() {
        return manage;
    }

    @JsonProperty("manage")
    public void setManage(Boolean manage) {
        this.manage = manage;
    }

    @JsonProperty("modify")
    public Boolean getModify() {
        return modify;
    }

    @JsonProperty("modify")
    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    @JsonProperty("view")
    public Boolean getView() {
        return view;
    }

    @JsonProperty("view")
    public void setView(Boolean view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
