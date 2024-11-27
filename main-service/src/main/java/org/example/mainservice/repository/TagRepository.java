package org.example.mainservice.repository;

import org.example.mainservice.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findTagById(Integer id);

    Tag findByName(String name);
}
