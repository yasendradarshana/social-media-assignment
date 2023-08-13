/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.exception;

/**
 *
 * @author Yasendra Darshana
 */
public class NotifyException extends RuntimeException{
    
    public NotifyException() {
        super ("Something went wrong");
    }
    public NotifyException(String message) {
        super (message);
    }
}
