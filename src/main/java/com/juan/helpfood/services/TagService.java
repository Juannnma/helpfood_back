package com.juan.helpfood.services;

import com.juan.helpfood.dtos.TagsDTOs.TagDTO;

import java.util.List;

public interface TagService {
    public List<TagDTO> getAllTags();
    public TagDTO createTag(TagDTO tag);
    TagDTO updateTag(Integer id,TagDTO tag);
    TagDTO getTagById(Integer id);
    boolean deleteTag(Integer id);
}
