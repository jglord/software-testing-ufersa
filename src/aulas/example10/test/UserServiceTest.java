package aulas.example10.test;

import aulas.example10.main.PasswordEncoder;
import aulas.example10.main.User;
import aulas.example10.main.UserRepository;
import aulas.example10.main.UserService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
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

    String validPasswords[]     = {"@U332s", "k@C825", "*m6U41", "cX014$", "32@M5f", "R0j9%3"};
    String invalidPasswords[]   = {"abc", "", "12a", "123as", "asd21", "1"};
    UserRepository ur = new UserRepository() {
        @Override
        public User findById(String id) {
            return null;
        }
    };

    @Test // T1 -> Exercite senhas válidas de 6 caracteres (pesquise dicas para senhas fortes).
    void validPassword(){
        for(String validPassword : validPasswords) {
            when(passwordEncoder.encode(validPassword)).thenReturn("1");
            passwordEncoder.encode(validPassword);
            verify(passwordEncoder).encode(validPassword);
        }
    }

    @Test // T2 -> Exercite senhas inválidas.
    void invalidPassword(){
        for(String invalidPassword : invalidPasswords){
            when(passwordEncoder.encode(invalidPassword)).thenReturn("0");
            passwordEncoder.encode(invalidPassword);
            verify(passwordEncoder).encode(invalidPassword);
        }
    }

    @Test // T3 -> Verifique as interações com a interface PasswordEncoder, para garantir que cada senha seja verificada uma vez (Dica: times()).
    void verifyInteractions() {
        for(String validPassword : validPasswords) {
            passwordEncoder.encode(validPassword);
            verify(passwordEncoder, times(1)).encode(validPassword);
        }
    }

    @Test // T4 -> Verifique as interações com a interface PasswordEncoder para testar argumentos nas interações (Dica: org.mockito.ArgumentCaptor).
    void verifyArguments() {

        UserService us = new UserService(ur, passwordEncoder);
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        for(int i = 0; i < 6; i++){
            us.isValidUser(String.valueOf(i), validPasswords[i]);
            //passwordEncoder.encode(validPasswords[i]);
            verify(passwordEncoder).encode(passwordCaptor.capture());
            assertEquals(validPasswords[i], passwordCaptor.getValue());
        }
    }
}
