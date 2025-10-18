package com.catelog.demo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDocument {
    @Id
    private Long id;
    private String name;
    private String description;

//    public ProductDocument(Long id, String name, String description) {
//    }
}


