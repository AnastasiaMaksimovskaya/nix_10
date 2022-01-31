package ua.com.alevel.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.CubeIndex;
import ua.com.alevel.service.ElasticBookSearchService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticBookSearchServiceImpl implements ElasticBookSearchService {

    private static final String CUBE_INDEX = "cubeindex";

    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticBookSearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<String> searchCubeName(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name", "*" + query.toLowerCase() + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        SearchHits<CubeIndex> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        CubeIndex.class,
                        IndexCoordinates.of(CUBE_INDEX));
        final List<String> suggestions = new ArrayList<>();
        searchSuggestions.getSearchHits().forEach(searchHit-> suggestions.add(searchHit.getContent().getName()));
        return suggestions;
    }
}
