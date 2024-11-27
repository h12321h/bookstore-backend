package org.example.mainservice.serviceimpl;

import org.example.mainservice.dao.TagDao;
import org.example.mainservice.dto.TagAndBookDto;
import org.example.mainservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    
    @Override
    public TagAndBookDto findBookByTagTwoContainToSelected(String tagName) {
        return tagDao.findBookByTagTwoContainToSelected(tagName);
    }
}
