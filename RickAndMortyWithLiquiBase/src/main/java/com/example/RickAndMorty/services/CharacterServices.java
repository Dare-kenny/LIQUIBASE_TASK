package com.example.RickAndMorty.services;

import com.example.RickAndMorty.requestBody.RequestDTO;
import com.example.RickAndMorty.requestBody.RequestDTOStructure;
import com.example.RickAndMorty.responseBody.ResponseDTO;
import com.example.RickAndMorty.responseBody.ResponseDTOStructure;
import com.example.RickAndMorty.schemas.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServices {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrudServices crudService;

    private String BASE_URL = "https://rickandmortyapi.com/api/character";
    String url = BASE_URL;

    public List<RequestDTOStructure> getBodyFromAPI(){ // Function to get all the values from the external API and put them into a DTO class , made this a function to reduce redundancy
        ResponseEntity<RequestDTO> response = restTemplate.exchange(url,HttpMethod.GET,null, RequestDTO.class); //sends get request to the external api

        return response.getBody().getResults(); // stores the JSON response in a Map<String,Object> format
        //maps each value as a RickAndMortyResponseBody object using mapper
    }

    public void saveCharaters(){

        List<RequestDTOStructure> valuesFromBody = getBodyFromAPI(); //Store the external api values into this DTO class
        for(RequestDTOStructure character : valuesFromBody){
            CharacterEntity entity = new CharacterEntity(character); // Map the DTO to the CharacterEntity
            crudService.saveCharacter(entity); //save the entity into the database
        }
    }

    // UPDATED PART

    public ResponseDTO fetchAllCharacters(){// Changed it to a ResponseDTO type which is a list of responseDTOStructured entities
        List<ResponseDTOStructure> allEntities = new java.util.ArrayList<>(List.of());
        ResponseDTO response = new ResponseDTO();
        List<CharacterEntity> allEntites = crudService.getAllFromDatabase(); //get all the values from the database
        for (CharacterEntity entity : allEntites){
            ResponseDTOStructure responseToUser = new ResponseDTOStructure(entity); //Map the values to the responseDTOStructure class
            allEntities.add(responseToUser);
            response.setResults(allEntities);// add the values to a list that takes mapped entites
        }
        return response;
    }

    public ResponseDTOStructure fetchCharacterById(Integer id){ // i'm returning just one value hence why i'm using responseDtoStructure and not ResponseDTO
        Optional<CharacterEntity> requestedCharacter = crudService.findById(id); // get the character from the database
        return new ResponseDTOStructure(requestedCharacter); // map that character to the dto class
    }

}
