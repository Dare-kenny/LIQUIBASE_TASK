package com.example.RickAndMorty.controllers;

import com.example.RickAndMorty.responseBody.RickAndMortyDTO;
import com.example.RickAndMorty.schemas.CharacterEntity;
import com.example.RickAndMorty.services.CharacterServices;
import com.example.RickAndMorty.services.CrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CharacterControllers {

    @Autowired
    private CharacterServices characterService;

    @Autowired
    private CrudServices crudServices;

    @PostMapping("/saveAllCharacters") // saves All Characters (should be done just once)
    public String saveToDatabase(){
        characterService.saveCharaters();
        return "All characters saved to database";
    }

    @GetMapping("/characters") // Shows All characters in the database
    public List<CharacterEntity> showAllCharacters(){
        return characterService.fetchAllCharacters();
    }

    @GetMapping("/fetch/{id}") //shows character according to it's id
    public Optional<CharacterEntity> fetchCharacterById(@PathVariable Integer id){
        return characterService.fetchCharacterById(id);
    }

    @PostMapping("/create") //Creates and adds a new character to my database
    public String createCharacter(@RequestBody RickAndMortyDTO dto){
        return crudServices.createCharacter(dto);
    }

    @PutMapping("/update/{id}")
    public String updateCharacter(@RequestBody RickAndMortyDTO updatedDto,@PathVariable Integer id ){
        crudServices.updateCharacter(updatedDto , id);
        return updatedDto.getName()+" has been updated";
    }

    @GetMapping("/delete/{id}") // deletes a character according to its id
    public String deleteCharacterById(@PathVariable Integer id){
        return crudServices.deleteCharacter(id);
    }
}
