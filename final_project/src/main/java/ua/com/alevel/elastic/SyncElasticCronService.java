package ua.com.alevel.elastic;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.CubeIndex;
import ua.com.alevel.elastic.repository.CubeIndexRepository;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.repository.store.CubeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SyncElasticCronService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final CubeIndexRepository cubeIndexRepository;
    private final CubeRepository cubeRepository;

    public SyncElasticCronService(ElasticsearchOperations elasticsearchOperations, CubeIndexRepository cubeIndexRepository, CubeRepository cubeRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.cubeIndexRepository = cubeIndexRepository;
        this.cubeRepository = cubeRepository;
    }


    @Scheduled(fixedDelay = 600000)
    public void syncToSupplier() {
        elasticsearchOperations.indexOps(CubeIndex.class).refresh();
        cubeIndexRepository.deleteAll();
        cubeIndexRepository.saveAll(prepareDataset());
    }

    private Collection<CubeIndex> prepareDataset() {
        List<Cube> cubes = cubeRepository.findAll();
        List<CubeIndex> cubeIndices = new ArrayList<>();
        cubes.forEach(book -> {
            CubeIndex bookIndex = new CubeIndex();
            bookIndex.setName(book.getName());
            cubeIndices.add(bookIndex);
        });
        return cubeIndices;
    }
}
