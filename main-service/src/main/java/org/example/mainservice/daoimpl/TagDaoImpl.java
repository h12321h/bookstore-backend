package org.example.mainservice.daoimpl;

import org.example.mainservice.dao.TagDao;
import org.example.mainservice.dto.TagAndBookDto;
import org.example.mainservice.entity.Book;
import org.example.mainservice.entity.BookCover;
import org.example.mainservice.entity.NeoTag;
import org.example.mainservice.entity.Tag;
import org.example.mainservice.repository.BookCoverRepository;
import org.example.mainservice.repository.BookTagRepository;
import org.example.mainservice.repository.NeoTagRepository;
import org.example.mainservice.repository.TagRepository;
import org.example.mainservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Repository
public class TagDaoImpl implements TagDao {
    @Autowired
    private BookTagRepository bookTagRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private NeoTagRepository neoTagRepository;
    @Autowired
    private BookService bookService;

    @Override
    public TagAndBookDto findBookByTagTwoContainToSelected(String tagName) {
        List<NeoTag> tags=neoTagRepository.findTagTwoContainToSelected(tagName);
        if (tags == null) {
            return null;
        }
        TagAndBookDto tagAndBookDto=new TagAndBookDto();
        List<Tag> tagList=new ArrayList<>();
        List<Book> bookList=new ArrayList<>();
        for(NeoTag tag:tags){
            Tag t=tagRepository.findByName(tag.getName());
            if (t != null) {
                tagList.add(t);
                List<Integer> bookIds=bookTagRepository.findBookIdByTagId(t.getId());
                for(Integer bookId:bookIds){
                    Book book=bookService.findBookById(bookId);
                    if(book!=null){
                        bookList.add(book);
                    }
                }
            }
        }
        tagAndBookDto.setTags(tagList);

        tagAndBookDto.setBooks(new ArrayList<>(bookList.stream()
                .collect(Collectors.toMap(
                        Book::getId, // 以 book.id 作为键
                        book -> book, // 以 book 对象作为值
                        (existing, replacement) -> existing)) // 如果有重复，保留第一个
                .values()) // 转回 List
        );
        return tagAndBookDto;
    }
}
