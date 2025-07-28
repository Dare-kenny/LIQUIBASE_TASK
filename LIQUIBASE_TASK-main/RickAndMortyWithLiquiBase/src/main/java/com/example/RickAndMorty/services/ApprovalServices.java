package com.example.RickAndMorty.services;

import com.example.RickAndMorty.enum_values.ApprovalStatus;
import com.example.RickAndMorty.requestBody.RequestDTO;
import com.example.RickAndMorty.requestBody.RequestDTOStructure;
import com.example.RickAndMorty.responseBody.ResponseDTO;
import com.example.RickAndMorty.responseBody.ResponseDTOStructure;
import com.example.RickAndMorty.schemas.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ApprovalServices {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrudServices crudService;

    private String JSONBLOB_URL = "https://jsonblob.com/api/jsonBlob";
    String url = JSONBLOB_URL;

    public String generateBlobId(Integer id){
        Optional<CharacterEntity> requestedCharacter = crudService.findById(id);
        if(requestedCharacter.isEmpty()){
            return "Character not found";
        }

        CharacterEntity character = requestedCharacter.get();

        // Convert Entity to DTO
        RequestDTOStructure dto = new RequestDTOStructure(character);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RequestDTOStructure> request = new HttpEntity<>(dto, header);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        String blobUrl = response.getHeaders().getLocation().toString();
        String blobId = blobUrl.substring(blobUrl.lastIndexOf("/") + 1);

        return "BlobId: " + blobId;
    }



    public ResponseEntity<?> retrieveCharacterByBlobId(String blobId){
        String mainurl = url + "/" +blobId;

        try{
            ResponseEntity<ResponseDTOStructure> response = restTemplate.exchange(mainurl,HttpMethod.GET,null, ResponseDTOStructure.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character Not found");
        }
    }

    public ResponseEntity<String> approveCharacterById(String blobId){
        String mainurl = url + "/"+blobId;

        try{
            ResponseEntity<ResponseDTOStructure> response = restTemplate.exchange(mainurl,HttpMethod.GET,null, ResponseDTOStructure.class);
            ResponseDTOStructure dto = response.getBody();
            if(dto == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found");
            }

            CharacterEntity approvableCharacter = new CharacterEntity(dto);

            if(approvableCharacter.getId() > 20){
                approvableCharacter.setApprovalStatus(ApprovalStatus.APPROVED);
                crudService.saveCharacter(approvableCharacter);

                return ResponseEntity.ok(approvableCharacter.getName()+" has been approved");
            }else{
                return ResponseEntity.ok("Character is already approved");
            }
        }catch (RestClientException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found");
        }
    }
}
