package com.example.RickAndMorty.responseBody;


import java.util.List;

public class ResponseDTO {

    private List<ResponseDTOStructure> results;

    public List<ResponseDTOStructure> getResults() {
        return results;
    }

    public void setResults(List<ResponseDTOStructure> results) {
        this.results = results;
    }
}
