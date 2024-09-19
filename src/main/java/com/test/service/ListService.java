package com.test.service;

import com.test.model.Lists;
import com.test.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    public List<Lists> findAll() {
        return listRepository.findAll();
    }

    public Optional<Lists> findById(Integer id) {
        return listRepository.findById(id);
    }

    public Lists save(Lists list) {
        return listRepository.save(list);
    }

    public void deleteById(Integer id) {
        listRepository.deleteById(id);
    }
}
