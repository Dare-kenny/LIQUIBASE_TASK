package com.example.RickAndMorty.requestBody;

import java.util.List;

public class RequestDTO {

    private List<RequestDTOStructure> results;

    public List<RequestDTOStructure> getResults() {
        return results;
    }

    public void setResults(List<RequestDTOStructure> results) {
        this.results = results;
    }
}
