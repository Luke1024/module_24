package com.kodilla.hibernate.manytomany.facade;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO {
    private List<Response> responses = new ArrayList<>();
    public List<Response> getResponses() {
        return responses;
    }
}
