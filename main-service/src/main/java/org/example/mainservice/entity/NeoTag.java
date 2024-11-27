package org.example.mainservice.entity;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node("Tag")
public class NeoTag {
    @Id
    @GeneratedValue
    private String id;
    private String name;

    @Relationship(type = "CONTAIN")
    private Set<NeoTag> tags;

    public NeoTag() {
    }

    public NeoTag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NeoTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
