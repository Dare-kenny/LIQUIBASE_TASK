package com.example.RickAndMorty.generalDTOs;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Map;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "origin_name")),
        @AttributeOverride(name = "url", column = @Column(name = "origin_url"))

})
public class OriginDTO {

    private String name;
    private String url;

    public OriginDTO() {}

    public OriginDTO(Map<String,Object> mapping) {
        this.name = String.valueOf(mapping.get("name"));
        this.url = String.valueOf(mapping.get("url"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
