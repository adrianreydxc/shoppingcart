package com.ricardo.persistence;

import com.ricardo.models.School;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchoolsRepository {
    private Set<School> escuelas = new HashSet<>();

    public SchoolsRepository() {
        escuelas.add(new School(1L, "Mariposa"));
    }
}
