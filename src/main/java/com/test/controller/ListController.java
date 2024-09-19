package com.test.controller;

import com.test.model.Lists;
import com.test.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lists")
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping("/all")
    public List<Lists> getAllLists() {
        return listService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lists> getListById(@PathVariable Integer id) {
        Optional<Lists> list = listService.findById(id);
        return list.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Lists createList(@RequestBody Lists list) {
        return listService.save(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lists> updateList(@PathVariable Integer id, @RequestBody Lists list) {
        if (!listService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        list.setId(id);
        Lists updatedList = listService.save(list);
        return ResponseEntity.ok(updatedList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Integer id) {
        if (!listService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        listService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
