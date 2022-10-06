package aulas.example10.test;

import aulas.example10.main.PasswordEncoder;
import aulas.example10.main.User;
import aulas.example10.main.UserRepository;
import aulas.example10.main.UserService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    //----------------------------------------------
    //---------------->SENHA VÁLIDA<----------------
    //--> 6 caracteres alfanumericos
    //--> 1 letra maiuscula
    //--> 1 caracter especial
    //----------------------------------------------
    //----------------------------------------------

    // T1 -> Exercite senhas válidas de 6 caracteres (pesquise dicas para senhas fortes).
    @ParameterizedTest
    @CsvSource({"@U332s", "k@C825", "*m6U41", "cX014$", "32@M5f", "R0j9%3"})
    void validPassword(String validPassword){
        when(passwordEncoder.encode(validPassword)).thenReturn("1");
        passwordEncoder.encode(validPassword);
        verify(passwordEncoder).encode(validPassword);
    }

    // T2 -> Exercite senhas inválidas.
    @ParameterizedTest
    @CsvSource({ "abc", ".", "12a", "123as", "asd21", "1" })
    void invalidPassword(String invalidPassword){
        when(passwordEncoder.encode(invalidPassword)).thenReturn("0");
        passwordEncoder.encode(invalidPassword);
        verify(passwordEncoder).encode(invalidPassword);
    }

    // T3 -> Verifique as interações com a interface PasswordEncoder, para garantir que cada senha seja verificada uma vez (Dica: times()).
    @ParameterizedTest
    @CsvSource({ "@U332s", "k@C825", "*m6U41", "cX014$", "32@M5f", "R0j9%3" })
    void verifyInteractions(String password) {
        passwordEncoder.encode(password);
        verify(passwordEncoder, times(1)).encode(password);
    }

    // T4 -> Verifique as interações com a interface PasswordEncoder para testar argumentos nas interações (Dica: org.mockito.ArgumentCaptor).
    @ParameterizedTest
    @CsvSource({ "@U332s", "k@C825", "*m6U41", "cX014$", "32@M5f", "R0j9%3" })
    void verifyArguments(String password) {
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        passwordEncoder.encode(password);
        verify(passwordEncoder).encode(passwordCaptor.capture());

        String generatedString = passwordCaptor.getValue();

        assertThat(password).isEqualTo(generatedString);
    }
}
