package com.example.RickAndMorty.services;

import com.example.RickAndMorty.responseBody.ResponseDTO;
import com.example.RickAndMorty.responseBody.RickAndMortyDTO;
import com.example.RickAndMorty.schemas.CharacterEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CharacterServices {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrudServices crudService;

    private String BASE_URL = "https://rickandmortyapi.com/api/character";
    String url = BASE_URL;

    public List<RickAndMortyDTO> getBodyFromAPI(){ // Function to get all the values from the external API and put them into a DTO class , made this a function to reduce redundancy
        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url,HttpMethod.GET,null,ResponseDTO.class); //sends get request to the external api

        return response.getBody().getResults(); // stores the JSON response in a Map<String,Object> format
        //maps each value as a RickAndMortyResponseBody object using mapper
    }

    public void saveCharaters(){

        List<RickAndMortyDTO> valuesFromBody = getBodyFromAPI(); //Store the external api values into this DTO class
        for(RickAndMortyDTO character : valuesFromBody){
            CharacterEntity entity = new CharacterEntity(character); // Map the DTO to the CharacterEntity
            crudService.saveCharacter(entity); //save the entity into the database
        }
    }

    public List<CharacterEntity> fetchAllCharacters(){

        return crudService.getAllFromDatabase();
    }

    public Optional<CharacterEntity> fetchCharacterById(Integer id){
        return crudService.findById(id);
    }

}
