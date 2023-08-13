/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.store.impl;

import swlc.bolton.oom.socialapp.main.store.dto.PostDTO;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;

/**
 *
 * @author Yasendra Darshana
 */
public interface ChannelObserver {
    
    public void notifyPost(PostDTO postObj);
    public void notifySubscribers(long subsCount);
    public void notifyAccountRemoved(UserDTO userDTO);
    public void notifyAccountCreated(UserDTO userDTO);
}
