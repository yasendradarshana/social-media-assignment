/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.store.impl;

import java.util.ArrayList;
import swlc.bolton.oom.socialapp.main.enums.StoreTypes;
import swlc.bolton.oom.socialapp.main.store.dto.SubscribeUserDTO;
import swlc.bolton.oom.socialapp.main.store.dto.SubscriptionDTO;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;

/**
 *
 * @author Yasendra Darshana
 */
public class SubscriptionStore implements SuperStore<SubscriptionDTO>{
    
      private static final ArrayList<SubscriptionDTO> subscribedList = new ArrayList<>();

    @Override
    public synchronized CommonResponse reserve(SubscriptionDTO subscriberDTO) {
        return checkAvailability(subscriberDTO, StoreTypes.RESERVE.RESERVE);
    }

    @Override
    public CommonResponse release(SubscriptionDTO dto) {
        return checkAvailability(dto, StoreTypes.RELEASE.RELEASE);
    }

    @Override
    public CommonResponse retrieveListHandler() {
        return new CommonResponse(true, subscribedList);
    }

    @Override
    public CommonResponse retrieveData(SubscriptionDTO dto) {
        return checkAvailability(dto, StoreTypes.RETRIEVE.RETRIEVE);
    }

    @Override
    public CommonResponse checkAvailability(SubscriptionDTO subscriberDTO, StoreTypes type) {
        try {
            switch (type) {
                case RELEASE:
                    for (int i = 0; i < subscribedList.size(); i++) {
                        SubscriptionDTO subscribedObj = subscribedList.get(i);
                        if ((subscriberDTO.getSubscribedBy() == subscribedObj.getSubscribedBy())) {
                            subscribedList.remove(subscribedObj);
                        }
                    }
                     return new CommonResponse(true, subscriberDTO);
                
                case RESERVE:
                    SubscriptionDTO availableObj = null;
                    for (SubscriptionDTO subscriptionDTO : subscribedList) {
                        if ((subscriberDTO.getSubscribedBy() == subscriptionDTO.getSubscribedBy()) && (subscriberDTO.getSubscriberUserId() == subscriptionDTO.getSubscriberUserId())) {
                            availableObj = subscriptionDTO;
                        }
                    }

                    if (availableObj == null) {
                        subscribedList.add(subscriberDTO);
                    } else {
                        subscribedList.remove(availableObj);
                    }
                    return new CommonResponse(true, subscriberDTO);

                case RETRIEVE:
                    ArrayList<SubscribeUserDTO> SubscribeUserDTO = new ArrayList<>();
                    CommonResponse resp = new UserStore().retrieveListHandler();
                    ArrayList<UserDTO> userList = (ArrayList<UserDTO>) resp.getBody();

                    userList.forEach(userDetail -> {
                        if (userDetail.getId() == subscriberDTO.getSubscribedBy()) {
                            return;
                        }
                        SubscribeUserDTO tempSubscribeUser = new SubscribeUserDTO();

                        boolean isFound = false;
                        UserDTO tempUserOb = null;

                        for (SubscriptionDTO subscribedObj : subscribedList) {
                            if ((subscriberDTO.getSubscribedBy() == subscribedObj.getSubscribedBy()) && (subscribedObj.getSubscriberUserId() == userDetail.getId())) {
                                isFound = true;
                                tempUserOb = userDetail;
                            }
                        }
                        tempSubscribeUser.setUserDTO(isFound ? tempUserOb : userDetail);
                        tempSubscribeUser.setIsSubscribe(isFound);
                        SubscribeUserDTO.add(tempSubscribeUser);
                    });
                    return new CommonResponse(true, SubscribeUserDTO);

                default:
                    break;
            }
            return new CommonResponse(false, USER_NOT_FOUND);
        } catch (Exception e) {
            return new CommonResponse(false, null, e.getMessage());
        }
    }

    public CommonResponse getSubscribers(long userId) {
        ArrayList<Long> subscribers = new ArrayList<>();
        for (SubscriptionDTO subscribedObj : subscribedList) {
            if (userId == subscribedObj.getSubscriberUserId()) {
                subscribers.add(subscribedObj.getSubscribedBy());
            }
        }
        return new CommonResponse(subscribers.size() > 0, subscribers);
    }

    public CommonResponse getSubscriberCount(long userId) {
        long count = 0;
        for (SubscriptionDTO subscriptionDTO : subscribedList) {
            if (userId == subscriptionDTO.getSubscriberUserId()) {
                ++count;
            }
        }
        return new CommonResponse(true, count);
    }

}
