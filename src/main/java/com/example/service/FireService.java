package com.example.service;/*package hello.spaproject.service;

import hello.spaproject.dto.FireDTO;
import hello.spaproject.entity.FireEntity;
import hello.spaproject.repository.FireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FireService {
    @Autowired
    private static FireRepository fireRepository;
    public static final String COLLECTION_FIRE_NAME="demo";

    public Long save(FireDTO fireDTO){
        FireEntity fireEntity = FireEntity.tofireEntity(fireDTO);

        FireEntity entity = fireRepository.save(fireEntity);
        return entity.getId();
    }
}
*/