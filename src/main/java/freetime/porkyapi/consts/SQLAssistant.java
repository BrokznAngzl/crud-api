package freetime.porkyapi.consts;

public class SQLAssistant {
    public static String likeAll(String str) {
        return "%".concat(str).concat("%");
    }

    public static String likeBefore(String str) {
        return "%".concat(str);
    }

    public static String likeAfter(String str) {
        return str.concat("%");
    }

    public static String Str(String str) {
        return "'".concat(str).concat("'");
    }
}
