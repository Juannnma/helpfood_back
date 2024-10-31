package com.juan.helpfood.services.impl;

import com.juan.helpfood.dtos.TagsDTOs.TagDTO;
import com.juan.helpfood.entities.Tag;
import com.juan.helpfood.repositories.TagRepository;
import com.juan.helpfood.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getName()))
                .toList();
    }

    @Override
    public TagDTO createTag(TagDTO tag) {
        Tag newTag = new Tag();
        newTag.setName(tag.getName());
        tagRepository.save(newTag);
        return new TagDTO(newTag.getId(), newTag.getName());
    }

    @Override
    public TagDTO updateTag(Integer id, TagDTO tag) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent()) {
            Tag tagToUpdate = tagOptional.get();
            tagToUpdate.setName(tag.getName());
            tagRepository.save(tagToUpdate);
            return new TagDTO(tagToUpdate.getId(), tagToUpdate.getName());
        }else {
            return null;
        }
    }

    @Override
    public TagDTO getTagById(Integer id) {
        return tagRepository.findById(id)
                .map(tag -> new TagDTO(tag.getId(), tag.getName()))
                .orElse(null);
    }

    @Override
    public boolean deleteTag(Integer id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
