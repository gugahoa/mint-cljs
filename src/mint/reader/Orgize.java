package mint.reader;

public class Orgize {
    private static native String parseOrgRust(String unit);

    static {
        System.loadLibrary("orgize_jni");
    }

    public static String parseOrg(String unit) throws java.io.IOException {
        String output = parseOrgRust(unit);
        return output;
    }
}
