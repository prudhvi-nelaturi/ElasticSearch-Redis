package com.catelog.demo.repository;

import com.catelog.demo.entity.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, Long> {
    List<ProductDocument> findByNameContaining(String keyword);
}
