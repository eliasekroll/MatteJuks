package com.example.demo;

public class Pytagoras {

    float finnHypotenus (float katet1, float katet2){

        float K1Kvadr = kvadrer(katet1);

        float K2Kvadr = kvadrer(katet2);

        float summert = K1Kvadr + K2Kvadr;

        float Hypotenus = Rot(summert);

        return Hypotenus;
    }
    private float kvadrer(float tall) {
        return tall * tall;
    }

    float Rot(float Verdi) {

        double resultat = Math.sqrt(Verdi);

        return (float)resultat;
        }

    float finnKatet (float hypotenus, float katet) {

        float HKvadr = kvadrer(hypotenus);

        float KKvadr = kvadrer(katet);

        float summert = HKvadr - KKvadr;

        float Katet = Rot(summert);

        return Katet;
    }

}
