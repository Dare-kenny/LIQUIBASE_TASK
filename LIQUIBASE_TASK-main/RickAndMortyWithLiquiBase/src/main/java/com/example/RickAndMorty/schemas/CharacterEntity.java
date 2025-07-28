package com.example.RickAndMorty.schemas;

import com.example.RickAndMorty.enum_values.ApprovalStatus;
import com.example.RickAndMorty.generalDTOs.LocationDTO;
import com.example.RickAndMorty.generalDTOs.OriginDTO;
import com.example.RickAndMorty.requestBody.RequestDTOStructure;
import com.example.RickAndMorty.responseBody.ResponseDTOStructure;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "Character_data")
public class CharacterEntity {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String status;

    @Column
    private String species;

    @Column
    private String type;

    @Column
    private String gender;

    @Embedded
    private OriginDTO origin;

    @Enumerated(EnumType.STRING)
    @Column
    private ApprovalStatus approvalStatus;

    @Embedded
    private LocationDTO location;

    @Column(name = "image_url")
    private String image;

    @Column(name = "episode_url")
    @CollectionTable(name = "character_episodes",joinColumns = @JoinColumn(name = "id"))
    @ElementCollection
    private List<String> episode;

    @Column(name = "char_url")
    private String url;

    @Column(name = "created")
    private String created;

    public CharacterEntity() {
    }

    public CharacterEntity(RequestDTOStructure requestdata) {
        this.id = requestdata.getId();
        this.name = requestdata.getName();
        this.status = requestdata.getStatus();
        this.species = requestdata.getSpecies();
        this.type = requestdata.getType();
        this.gender = requestdata.getGender();
        this.origin = requestdata.getOrigin();
        this.approvalStatus = requestdata.getApprovalStatus();
        this.location = requestdata.getLocation();
        this.image = requestdata.getImage();
        this.episode = requestdata.getEpisode();
        this.url = requestdata.getUrl();
        this.created = requestdata.getCreated();
    }

    public CharacterEntity(ResponseDTOStructure responsedata) {
        this.id = responsedata.getId();
        this.name = responsedata.getName();
        this.status = responsedata.getStatus();
        this.species = responsedata.getSpecies();
        this.type = responsedata.getType();
        this.gender = responsedata.getGender();
        this.origin = responsedata.getOrigin();
        this.approvalStatus = responsedata.getApprovalStatus();
        this.location = responsedata.getLocation();
        this.image = responsedata.getImage();
        this.episode = responsedata.getEpisode();
        this.url = responsedata.getUrl();
        this.created = responsedata.getCreated();
    }

    public CharacterEntity(String name, String status, String species, String type, String gender, OriginDTO origin, ApprovalStatus approvalStatus, LocationDTO location, String image, List<String> episode, String url, String created) {

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
