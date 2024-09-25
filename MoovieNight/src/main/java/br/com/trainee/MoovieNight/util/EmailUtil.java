package br.com.trainee.MoovieNight.util;


import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailUtil {
    public boolean isValidEmail(String email) {
        // Expressão regular para validação de e-mail
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(email);

        return matcher.matches();
    }
}
