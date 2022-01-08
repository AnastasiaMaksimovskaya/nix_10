package ua.com.alevel.util;

public final class Parser {
    private Parser(){ }
    public static Double convertFromKopeyka(Long kop){
        return kop/100D;
    }
    public static Long convertToKopeyka(Double gryvna){
        return (long)(gryvna*100);
    }
}
