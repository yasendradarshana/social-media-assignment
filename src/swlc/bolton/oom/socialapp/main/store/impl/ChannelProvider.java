/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.store.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import swlc.bolton.oom.socialapp.main.controller.SubcriptionController;
import swlc.bolton.oom.socialapp.main.enums.ObserverTypes;
import swlc.bolton.oom.socialapp.main.store.dto.PostDTO;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;
import swlc.bolton.oom.socialapp.main.view.viewPost;

/**
 *
 * @author Yasendra Darshana
 */
public class ChannelProvider implements ChannelSubject<Object>{
    
    private final Set<ChannelObserver> setOfChannelObservers;
    private final SubcriptionController subscriptionController;

    public ChannelProvider() {
        setOfChannelObservers = new HashSet<>();
        subscriptionController = new SubcriptionController();
    }

    @Override
    public void addObserver(ChannelObserver observer) {
        setOfChannelObservers.add(observer);
    }

    @Override
    public void removeObserver(ChannelObserver observer) {
        setOfChannelObservers.remove(observer);
    }

    @Override
    public void sendNotification(Object obj, ObserverTypes observerType) {
        for (ChannelObserver observer : setOfChannelObservers) {
            viewPost home = (viewPost) observer;

            switch (observerType) {
                case CREATE_ACCOUNT:
                    observer.notifyAccountCreated((UserDTO) obj); // call notify method
                    break;

                case REMOVE_ACCOUNT:
                    observer.notifyAccountRemoved((UserDTO) obj); // call notify method
                    break;

                case PUBLISHED_POST:
                    PostDTO postDTO = (PostDTO) obj;
                    if (postDTO.getSharedUser().getId() == home.getLoggedUserObj().getId()) {
                        observer.notifyPost(postDTO); // call notify method
                    } else {
                        CommonResponse subscribers = subscriptionController.getSubscribersIDHandler(postDTO.getSharedUser().getId());
                        if (subscribers.isSuccess()) {
                            ArrayList<Long> subsIds = (ArrayList<Long>) subscribers.getBody();
                            subsIds.forEach(id -> {
                                if (home.getLoggedUserObj().getId() == id) {
                                    observer.notifyPost(postDTO); // call notify method
                                }
                            });
                        }
                    }
                    break;

                case SUBSCRIBED_COUNT:
                    UserDTO userDTO = (UserDTO) obj;
                    if (userDTO.getId() == home.getLoggedUserObj().getId()) {
                        CommonResponse subscribers = subscriptionController.getSubscribedCountHandler(userDTO.getId());
                        if (subscribers.isSuccess()) {
                            long subsCount = (long) subscribers.getBody();
                            observer.notifySubscribers(subsCount); // call notify method
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
