package org.example.mainservice.service;

import org.example.mainservice.dto.TagAndBookDto;
import org.springframework.web.bind.annotation.RestController;

public interface TagService {
    public TagAndBookDto findBookByTagTwoContainToSelected(String tagName);
}
