package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/areal")
public class ArealService {

    private Areal areal = new Areal();

    @RequestMapping(value = "/finnareal")
    public float RegnutAreal(@RequestParam("L1") float L1, @RequestParam("L2") float L2) {
        return areal.finnAreal(L1, L2);

    }

    @RequestMapping(value = "/finnlengde")
    public float RegnutLengde(@RequestParam("areal") float A, @RequestParam("lengde") float L){
        return areal.finnLengde(A, L);
    }
}
