package ua.com.alevel;

public final class Reverse {
    private Reverse() {
    }

    public static String reverse(String src, boolean fullString) {
        StringBuilder stringBuilder = new StringBuilder();
        if (fullString == true) {
            return reverseSubstring(src);
        } else {
            String substring[] = src.split(" ");
            for (int i = 0; i < substring.length; i++) {
                stringBuilder.append(reverseSubstring(substring[i])).append(" ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
    }


    private static String reverseSubstring(String input) {
        char symbols[] = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            symbols[i] = input.charAt(input.length() - i - 1);
        }
        return new String(symbols);
    }

    public static String reverse(String src, String dest) {

        StringBuilder stringBuilder = new StringBuilder(src);
        stringBuilder.insert(src.indexOf(dest), reverseSubstring(dest));
        stringBuilder.delete(stringBuilder.indexOf(dest), stringBuilder.indexOf(dest) + dest.length());
        return stringBuilder.toString();
    }

    public static String reverse(String src, int firstIndex, int
            lastIndex) {
        StringBuilder stringBuilder = new StringBuilder(src);
        String insert = reverse(stringBuilder.substring(firstIndex - 1, lastIndex), false);
        stringBuilder.delete(firstIndex - 1, lastIndex);
        stringBuilder.insert(firstIndex - 1, insert);
        return stringBuilder.toString();
    }


}
