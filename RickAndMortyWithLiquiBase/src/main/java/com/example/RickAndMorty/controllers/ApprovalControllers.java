package com.example.RickAndMorty.controllers;

import com.example.RickAndMorty.services.ApprovalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApprovalControllers {

    @Autowired
    private ApprovalServices approvalService;

    @PostMapping("/submit/{id}") //submit the id of the entity and get a temporary blobId that can be used for blobOperations
    public String retrieveBlobId(@PathVariable Integer id){
        return approvalService.generateBlobId(id);
    }

    @GetMapping("/retrieve/{blobId}") // show the details of the entity using the blobId , this is done to show that the lobId is connected to the appropriate entity
    public ResponseEntity<?> retrieveCharacter(@PathVariable String blobId){
        return approvalService.retrieveCharacterByBlobId(blobId);
    }

    @PutMapping("/approve/{blobId}") // updates the approval status of the entity
    public ResponseEntity<String> approveCharacter(@PathVariable String blobId){
        return approvalService.approveCharacterById(blobId);
    }
}
