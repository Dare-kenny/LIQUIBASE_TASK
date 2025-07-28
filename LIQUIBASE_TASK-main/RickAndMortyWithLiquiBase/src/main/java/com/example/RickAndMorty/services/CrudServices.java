package com.example.RickAndMorty.services;

import com.example.RickAndMorty.enum_values.ApprovalStatus;
import com.example.RickAndMorty.generalDTOs.LocationDTO;
import com.example.RickAndMorty.generalDTOs.OriginDTO;
import com.example.RickAndMorty.repositories.CharacterRepository;
import com.example.RickAndMorty.requestBody.RequestDTOStructure;
import com.example.RickAndMorty.schemas.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CrudServices { // Service for saving,deleting and creating Characters

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveCharacter(CharacterEntity entities){
        repository.save(entities);
    }

    public String createCharacter( @RequestBody RequestDTOStructure dto){
        CharacterEntity entity = new CharacterEntity(dto); // map dto to character
        entity.setApprovalStatus(ApprovalStatus.NOT_APPROVED); // The new/created Characters have their approval status to be saved as "NOT_APPROVED" at default
        repository.save(entity);
        return entity.getName()+" has been created and added to database";
    }

    public String deleteCharacter(Integer id){

        Optional<CharacterEntity> deletedEntity = repository.findById(id); // I used Optional for null-value containment

        if (deletedEntity.isPresent()){
            repository.deleteById(id);
            return deletedEntity.get().getName()+" has been removed from database";
        }else{
            return "Character with ID "+id+" not found";
        }
    }

    public Optional<CharacterEntity> findById(Integer id){
        return repository.findById(id);
    }

    public List<CharacterEntity> getAllFromDatabase(){
        return repository.findAll();
    }

    public void updateCharacter(Integer id, Map<String, Object> updates) {
        Optional<CharacterEntity> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isEmpty()) {
            throw new RuntimeException("Character not found in database.");
        }

        CharacterEntity character = optionalCharacter.get();
        RequestDTOStructure dto = new RequestDTOStructure();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field.toLowerCase()) {
                case "name":
                    dto.setName(String.valueOf(value));
                    break;
                case "status":
                    dto.setStatus(String.valueOf(value));
                    break;
                case "species":
                    dto.setSpecies(String.valueOf(value));
                    break;
                case "type":
                    dto.setType(String.valueOf(value));
                    break;
                case "gender":
                    dto.setGender(String.valueOf(value));
                    break;
                case "image":
                    dto.setImage(String.valueOf(value));
                    break;
                case "url":
                    dto.setUrl(String.valueOf(value));
                    break;
                case "created":
                    dto.setCreated(String.valueOf(value));
                    break;
                case "episode":
                    dto.setEpisode((List<String>) value);
                    break;

                case "origin":
                    if (value instanceof Map<?, ?>) {
                        Map<?, ?> originMap = (Map<?, ?>) value;
                        OriginDTO origin = dto.getOrigin() != null ? dto.getOrigin() : new OriginDTO();
                        if (originMap.containsKey("name")) {
                            origin.setName(String.valueOf(originMap.get("name")));
                        }
                        if (originMap.containsKey("url")) {
                            origin.setUrl(String.valueOf(originMap.get("url")));
                        }
                        dto.setOrigin(origin);
                    } else {
                        throw new RuntimeException("Invalid format for 'origin'. Expected an object.");
                    }
                    break;

                case "location":
                    if (value instanceof Map<?, ?>) {
                        Map<?, ?> locationMap = (Map<?, ?>) value;
                        LocationDTO location = dto.getLocation() != null ? dto.getLocation() : new LocationDTO();
                        if (locationMap.containsKey("name")) {
                            location.setName(String.valueOf(locationMap.get("name")));
                        }
                        if (locationMap.containsKey("url")) {
                            location.setUrl(String.valueOf(locationMap.get("url")));
                        }
                        dto.setLocation(location);
                    } else {
                        throw new RuntimeException("Invalid format for 'location'. Expected an object.");
                    }
                    break;

                case "approvalstatus":
                case "id":
                    throw new RuntimeException("Action not authorized: cannot update '" + field + "'.");

                default:
                    throw new RuntimeException("Unknown field '" + field + "' entered.");
            }
        }


        // Apply DTO values to the entity without overwriting unspecified nested fields

        if (dto.getName() != null) character.setName(dto.getName());
        if (dto.getStatus() != null) character.setStatus(dto.getStatus());
        if (dto.getSpecies() != null) character.setSpecies(dto.getSpecies());
        if (dto.getType() != null) character.setType(dto.getType());
        if (dto.getGender() != null) character.setGender(dto.getGender());
        if (dto.getImage() != null) character.setImage(dto.getImage());
        if (dto.getEpisode() != null) character.setEpisode(dto.getEpisode());
        if (dto.getUrl() != null) character.setUrl(dto.getUrl());
        if (dto.getCreated() != null) character.setCreated(dto.getCreated());


        if (dto.getOrigin() != null) {
            var existingOrigin = character.getOrigin() != null ? character.getOrigin() : new OriginDTO();
            if (dto.getOrigin().getName() != null) existingOrigin.setName(dto.getOrigin().getName());
            if (dto.getOrigin().getUrl() != null) existingOrigin.setUrl(dto.getOrigin().getUrl());
            character.setOrigin(existingOrigin);
        }

        if (dto.getLocation() != null) {
            var existingLocation = character.getLocation() != null ? character.getLocation() : new LocationDTO();
            if (dto.getLocation().getName() != null) existingLocation.setName(dto.getLocation().getName());
            if (dto.getLocation().getUrl() != null) existingLocation.setUrl(dto.getLocation().getUrl());
            character.setLocation(existingLocation);
        }

        repository.save(character);
    }


}
