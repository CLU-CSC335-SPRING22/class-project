package Client;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

public class Validation {
    private String username;
    private char[] password;
    private String email;
    private int usernameLength;
    private int passwordLength;
    private int emailLength;

    // Parameters
    int usernameMinLength = 1;
    int usernameMaxLength = 32;
    int passwordMinLength = 8;
    int passwordMaxLength = 128;
    char[] usernameInvalidChars = {'!', '"', '#', '$', '%', '&', '(', ')', '*', '+', ',', '/',
                                ':', ';', '<', '>', '=', '?', '@', '[', ']', ' ', '^', '_', '`',
                                '{', '}', '|', '\'', '\\'};

    Validation(String username, char[] password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public boolean usernameFormatCheck(){
        usernameLength = username.length();

        if(usernameLength >= usernameMinLength && usernameLength <= usernameMaxLength){
            for(int i = 0; i < usernameLength; i++){
                char character = username.charAt(i);
                for(int j = 0; j < usernameInvalidChars.length; j++){
                    if(character == usernameInvalidChars[j]){
                        return false;
                    }
                }
            }
        }else {
            return false;
        }
        return true;
    }

    public boolean emailFormatCheck(){
        emailLength = email.length();
        // Value Counters
        int atCounter = 0;
        int periodCounter = 0;

        for(int i =0; i < emailLength; i++){
            char character = email.charAt(i);

            if(character == '.'){
                periodCounter += 1;
            }else if(character == '@'){
                atCounter += 1;
            }

            if(atCounter == 1 && periodCounter >= 1){
                return true;
            }
        }
        return false;
    }

    public boolean passwordFormatCheck(){
        passwordLength = password.length;
        // Value Counters
        int letters = 0;
        int numbers = 0;

        if(passwordLength >= passwordMinLength && passwordLength <= passwordMaxLength){
            for(int i = 0; i < passwordLength; i++){
                char character = password[i];
                if(isDigit(character)) {
                    numbers += 1;
                } else if(isAlphabetic(character)){
                    letters += 1;
                }
            }
            if(letters >= 1 && numbers >= 1){
                return true;
            }
        }
        return false;
    }

    public boolean completeValidation() {
        if(usernameFormatCheck() && emailFormatCheck() && passwordFormatCheck()){
            return true;
        }else {
            return false;
        }
    }
}
