package com.juan.helpfood.controllers;

import com.juan.helpfood.dtos.TagsDTOs.TagDTO;
import com.juan.helpfood.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;
    @Autowired
    public TagController(final TagService tagService) {
        this.tagService = tagService;
    }
    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Integer id) {
        TagDTO tagDTO = tagService.getTagById(id);
        if (tagDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tagDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        return new ResponseEntity<>(tagService.createTag(tagDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Integer id, @RequestBody TagDTO tagDTO) {
        TagDTO updatedTagDTO = tagService.updateTag(id, tagDTO);
        if (updatedTagDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTagDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
        if (tagService.deleteTag(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
