/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.store.dto;

/**
 *
 * @author Yasendra Darshana
 */
public class SubscribeUserDTO extends UserDTO{
    
    private UserDTO userDTO;
    private boolean isSubscribe;

    public SubscribeUserDTO() {
   
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    
    public boolean isIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

}
