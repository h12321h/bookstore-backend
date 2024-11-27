package org.example.mainservice.controller;

import org.example.mainservice.dto.TagAndBookDto;
import org.example.mainservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;
    
    @GetMapping("/getBooksByTag")
    public TagAndBookDto getBooksByTag(@RequestParam String tagName) {
        return tagService.findBookByTagTwoContainToSelected(tagName);
    }
}
