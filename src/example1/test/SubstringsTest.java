package example1.test;

import example1.main.Substrings;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SubstringsTest {

    @Test
    void strIsNullOrEmpty(){

        assertThat(Substrings.substringsBetween(null, "a", "b")).isEqualTo(null);
        assertThat(Substrings.substringsBetween("", "a", "b")).isEqualTo(new String[]{});

    }

    @Test
    void openIsNullOrEmpty(){
        assertThat(Substrings.substringsBetween("a", null, "b")).isEqualTo(null);
        assertThat(Substrings.substringsBetween("a", "", "b")).isEqualTo(null);
    }

    @Test
    void closeIsNullOrEmpty(){
        assertThat(Substrings.substringsBetween("a", "a", null)).isEqualTo(null);
        assertThat(Substrings.substringsBetween("a", "b", "")).isEqualTo(null);
    }

    // teste 11 ao 15

    @Test
    void strHasNoOpenClose(){
        assertThat(Substrings.substringsBetween("xyz", "a", "b")).isEqualTo(null);
    }

    @Test
    void strHasOpenNoClose(){
        assertThat(Substrings.substringsBetween("xyz", "x", "b")).isEqualTo(null);
    }

    @Test
    void strHasCloseNoOpen(){
        assertThat(Substrings.substringsBetween("xyz", "a", "z")).isEqualTo(null);
    }

    @Test
    void strHasOpenClose(){
        assertThat(Substrings.substringsBetween("xyz", "x", "z")).isEqualTo(new String[]{"y"});
    }

    @Test
    void strHasTagsManyTimes(){
        assertThat(Substrings.substringsBetween("xyzxbzxaz", "x", "z")).isEqualTo(new String[]{"y", "b", "a"});
    }

    // teste 16 ao 21

    @Test
    void strHasNoOpenCloseMoreOne(){
        assertThat(Substrings.substringsBetween("xyz", "aa", "bb")).isEqualTo(null);
    }

    @Test
    void strHasOpenNoCloseMoreOne(){
        assertThat(Substrings.substringsBetween("xxyz", "xx", "bb")).isEqualTo(null);
    }

    @Test
    void strHasCloseNoOpenMoreOne(){
        assertThat(Substrings.substringsBetween("xyzz", "aa", "zz")).isEqualTo(null);
    }


    @Test
    void strHasOpenCloseMoreOne(){
        assertThat(Substrings.substringsBetween("xxyzz", "xx", "zz")).isEqualTo(new String[]{"y"});
    }

    @Test
    void strHasTagsManyTimesMoreOne(){
        assertThat(Substrings.substringsBetween("xxazzxxbzzxxczz", "xx", "zz"))
                .isEqualTo(new String[]{"a", "b", "c"});
    }

    @Test
    void strHasTagsManyTimesMoreOneSpace(){
        assertThat(Substrings.substringsBetween("xxa zzxxbzzxxczz", "xx", "zz"))
                .isEqualTo(new String[]{"a ", "b", "c"});
    }

    @Test
    void strHasTagsNoOpenCloe(){
        assertThat(Substrings.substringsBetween("xxzz", "xx", "zz")).isEqualTo(new String[]{""});
    }

}
