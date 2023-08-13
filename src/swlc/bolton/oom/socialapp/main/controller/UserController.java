/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.controller;

import swlc.bolton.oom.socialapp.main.store.dto.SubscriptionDTO;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;
import swlc.bolton.oom.socialapp.main.store.impl.UserStore;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;

/**
 *
 * @author Yasendra Darshana
 */
public class UserController implements SuperController{
    
    private final UserStore userStore;
    private final SubcriptionController subscriptionController;

    public UserController() {
        userStore = new UserStore();
        subscriptionController = new SubcriptionController();
    }
    

    public CommonResponse registerHandler(UserDTO userDTO) {
        return userStore.reserve(userDTO);
    }
    
    public CommonResponse loginHandler(UserDTO userDTO) {
        CommonResponse checkAvailability = userStore.retrieveData(userDTO);
        
        if(checkAvailability.isSuccess()) {
            UserDTO retrieveUserObj = (UserDTO) checkAvailability.getBody();
            if(!userDTO.getPassword().equalsIgnoreCase(retrieveUserObj.getPassword())){
                checkAvailability.setSuccess(false);
            }
        }
        return checkAvailability;
    }
    
    public CommonResponse removeAccountHandler(UserDTO userDTO) {
        CommonResponse release = userStore.release(userDTO);
        // remove subscriptions
        if(release.isSuccess()) subscriptionController.removeSubscriptionHandler(new SubscriptionDTO(0, userDTO.getId()));
        return release;
    }
}

