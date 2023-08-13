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
public class PostDTO extends SuperDTO{
    
    private UserDTO sharedUser;
    private String timestamp;
    private String post;

    public PostDTO() {
    }

    public PostDTO(UserDTO sharedUser, String timestamp, String post) {
        this.sharedUser = sharedUser;
        this.timestamp = timestamp;
        this.post = post;
    }

    public UserDTO getSharedUser() {
        return sharedUser;
    }

    public void setSharedUser(UserDTO sharedUser) {
        this.sharedUser = sharedUser;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    
    
}
