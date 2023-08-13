/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.store.impl;

import swlc.bolton.oom.socialapp.main.enums.ObserverTypes;

/**
 *
 * @author Yasendra Darshana
 */
public interface ChannelSubject<T> {
    
    public void addObserver(ChannelObserver weatherObserver);
    public void removeObserver(ChannelObserver weatherObserver);
    public void sendNotification(T obj, ObserverTypes observerType);
}
