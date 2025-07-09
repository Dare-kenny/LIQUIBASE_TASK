package com.example.RickAndMorty.responseBody;

import java.util.List;

public class ResponseDTO {

    private List<RickAndMortyDTO> results;

    public List<RickAndMortyDTO> getResults() {
        return results;
    }

    public void setResults(List<RickAndMortyDTO> results) {
        this.results = results;
    }
}
