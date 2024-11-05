package org.example.authorservice.service;

import java.util.List;

public interface BookService {
    List<String> getAuthorsByTitle(String title);
}
