package org.example.mainservice.dao;

import org.example.mainservice.dto.TagAndBookDto;
import org.example.mainservice.entity.Book;

import java.util.List;

public interface TagDao {
    TagAndBookDto findBookByTagTwoContainToSelected(String tagName);
}
