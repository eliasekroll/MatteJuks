package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pytagoras")
public class PythagorasService {

    private Pytagoras pytagoras = new Pytagoras();


    @RequestMapping(value = "/finnhypotenus")
    public float RegnutHypotenus(@RequestParam("k1") float K1, @RequestParam("k2") float K2) {
        return pytagoras.finnHypotenus(K1, K2);
    }

    @RequestMapping(value = "/finnkatet")
    public float RegnutKatet(@RequestParam("kat1") float K, @RequestParam("hyp") float H) {
        return pytagoras.finnKatet(H, K);
    }

}




