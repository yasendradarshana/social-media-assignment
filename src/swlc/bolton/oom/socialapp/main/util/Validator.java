/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.util;

import java.util.regex.Pattern;
import swlc.bolton.oom.socialapp.main.enums.ValidateTypes;

/**
 *
 * @author Yasendra Darshana
 */
public class Validator {
    
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean regexHandler(String value, ValidateTypes validateType) {
        switch(validateType) {
            case EMAIL:
                 return VALID_EMAIL_ADDRESS_REGEX.matcher(value).find();
            case PASSWORD:
                return VALID_PASSWORD.matcher(value).find();
        }
       return false;
    }
}
