package trabalho.trabalho1.main;



public class Trabalho {

    public static void main(String[] args) {
        System.out.println(rotate("abc", -2));
    }

    public static String rotate(final String str, final int shift) {

        if (str == null) {
            return null;
        }

        final int strLen = str.length();

        if (shift == 0 || strLen == 0 || shift % strLen == 0) {
            return str;
        }

        final StringBuilder builder = new StringBuilder(strLen);

        final int offset = - (shift % strLen);

        builder.append(str.substring(offset));

        builder.append(str, 0, offset);

        return builder.toString();

    }


}
