/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.controller;

import swlc.bolton.oom.socialapp.main.store.dto.SubscriptionDTO;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;
import swlc.bolton.oom.socialapp.main.store.impl.SubscriptionStore;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;

/**
 *
 * @author Yasendra Darshana
 */
public class SubcriptionController implements SuperController{
    
    private final SubscriptionStore subscriptionStore;
    
    public SubcriptionController() {
        subscriptionStore = new SubscriptionStore();
    }
    /**
     * retrieve all registered accounts (channels)
     * @param userDTO related user details
     * @return CommonResponse
     */
    public CommonResponse retrieveAllSubscriptionHandler(UserDTO userDTO) {
        return subscriptionStore.retrieveData(new SubscriptionDTO(0, userDTO.getId()));
    }
    /**
     * subscribe channel
     * @param subscriberDTO subscription details
     * @return CommonResponse
     */
    public CommonResponse subscriptionUserHandler(SubscriptionDTO subscriberDTO) {
        return subscriptionStore.reserve(subscriberDTO);
    }
    /**
     * retrieve subs count for each channels
     * @param userId channel owner id
     * @return CommonResponse
     * */
    public CommonResponse getSubscribedCountHandler(long userId) {
        return subscriptionStore.getSubscriberCount(userId);
    }
    /**
     * retrieve subscribed user IDs
     * @param userId channel owner id
     * @return CommonResponse
     * */
    public CommonResponse getSubscribersIDHandler(long userId) {
        return subscriptionStore.getSubscribers(userId);
    }
    /**
     * un-subscribe channel
     * @return CommonResponse
     * */
    public CommonResponse removeSubscriptionHandler(SubscriptionDTO subscriberDTO) {
        return subscriptionStore.release(subscriberDTO);
    }

}
