package com.example.RickAndMorty.requestBody;

import com.example.RickAndMorty.enum_values.ApprovalStatus;
import com.example.RickAndMorty.generalDTOs.LocationDTO;
import com.example.RickAndMorty.generalDTOs.OriginDTO;
import com.example.RickAndMorty.schemas.CharacterEntity;

import java.util.List;

public class RequestDTOStructure { // from user input to database

    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginDTO origin;
    private ApprovalStatus approvalStatus = ApprovalStatus.APPROVED;
    private LocationDTO location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    public RequestDTOStructure() {}

    public RequestDTOStructure(String name, String status, String species, String type, String gender, OriginDTO origin, ApprovalStatus approvalStatus, LocationDTO location, String image, List<String> episode, String url, String created) {

        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.approvalStatus = approvalStatus;
        this.location = location;
        this.image = image;
        this.episode = episode;
        this.url = url;
        this.created = created;
    }

    public RequestDTOStructure(CharacterEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.status = entity.getStatus();
        this.type = entity.getType();
        this.gender = entity.getGender();
        this.origin = entity.getOrigin();
        this.approvalStatus = entity.getApprovalStatus();
        this.location = entity.getLocation();
        this.image = entity.getImage();
        this.episode = entity.getEpisode();
        this.url = entity.getUrl();
        this.created = entity.getCreated();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public OriginDTO getOrigin() {
        return origin;
    }

    public void setOrigin(OriginDTO origin) {
        this.origin = origin;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(List<String> episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
