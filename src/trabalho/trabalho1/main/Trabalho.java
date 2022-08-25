package trabalho.trabalho1.main;

public class Trabalho {

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

        if( offset > 0 ){
            builder.append(str.substring(offset));

            builder.append(str, 0, offset);
        }
        else if ( offset < 0 ){
            builder.append(str.substring((-offset) - 1));

            builder.append(str, 0, (-offset) - 1);
        }
        return builder.toString();
    }
}
