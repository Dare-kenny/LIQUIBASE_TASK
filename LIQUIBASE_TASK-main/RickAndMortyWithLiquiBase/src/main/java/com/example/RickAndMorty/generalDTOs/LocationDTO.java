package com.example.RickAndMorty.generalDTOs;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Map;
import java.util.Objects;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "location_name")),
        @AttributeOverride(name = "url", column = @Column(name = "location_url"))

})
public class LocationDTO {

    private String name;
    private String url;

    public LocationDTO (){};

    public LocationDTO(Map<String, Objects> mapping){
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
