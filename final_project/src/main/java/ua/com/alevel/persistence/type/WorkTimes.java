package ua.com.alevel.persistence.type;

public enum WorkTimes {
    NINE ("9:00"),
    TEN ("10:00"),
    ELEVEN ("11:00"),
    TWELVE ("12:00"),
    THIRTEEN ("13:00"),
    FOURTEEN ("14:00"),
    FIFTEEN  ("15:00"),
    SIXTEEN  ("16:00"),
    SEVENTEEN  ("17:00"),
    EIGHTEEN  ("18:00"),
    NINETEEN  ("19:00"),
    TWENTY  ("20:00");

    private String time;

    WorkTimes(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
