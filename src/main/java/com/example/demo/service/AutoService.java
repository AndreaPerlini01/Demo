package com.example.demo.service;

import com.example.demo.entities.Auto;
import com.example.demo.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AutoService {

    @Autowired
    AutoRepository repository;

    @Transactional
    public Auto doIt(Auto auto) {
        return repository.save(auto);
    }
}
