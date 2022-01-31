package ua.com.alevel.persistence.type;

public enum CubeCategory {
    TWO ("2x2"),
    THREE ("3x3"),
    FOUR ("4x4"),
    FIVE ("5x5"),
    SIX ("6x6"),
    SEVEN ("7x7");

    private String name;

    CubeCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
