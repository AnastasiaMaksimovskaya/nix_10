package ua.com.alevel.persistence.listener;

import ua.com.alevel.persistence.entity.store.Cube;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class ProductVisibleGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public static void generateCubeVisible(Cube cube) {
        cube.setVisible(cube.getAmount() != null &&
                cube.getAmount() > 0 &&
                cube.getPrice() != null &&
                cube.getPrice() > 0);
    }
}
