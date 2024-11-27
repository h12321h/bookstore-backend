package org.example.mainservice.repository;

import org.example.mainservice.entity.NeoTag;
import org.example.mainservice.entity.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface NeoTagRepository extends Neo4jRepository<NeoTag, String> {
    @Query(value = "MATCH (s:Tag) " +
            "WHERE s.name = $tagName " +
            "OPTIONAL MATCH (s)-[:CONTAIN]->(tag1:Tag) " +
            "OPTIONAL MATCH (tag1)-[:CONTAIN]->(tag2:Tag) " +
            "WITH s, collect(tag1) AS tag1s, collect(tag2) AS tag2s " +
            "UNWIND tag1s + tag2s + s AS allTags " +
            "RETURN DISTINCT allTags")
    List<NeoTag> findTagTwoContainToSelected(String tagName);

}
