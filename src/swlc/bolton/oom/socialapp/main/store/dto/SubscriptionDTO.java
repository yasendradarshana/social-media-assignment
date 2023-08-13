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
public class SubscriptionDTO extends SuperDTO{
    
    private long subscribeId;
    private long subscriberUserId;
    private long subscribedBy;

    public SubscriptionDTO(long subscribeId, long subscriberUserId, long subscribedBy) {
        this.subscribeId = subscribeId;
        this.subscriberUserId = subscriberUserId;
        this.subscribedBy = subscribedBy;
    }

    public SubscriptionDTO(long subscriberUserId, long subscribedBy) {
        this.subscriberUserId = subscriberUserId;
        this.subscribedBy = subscribedBy;
    }

    public long getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(long subscribeId) {
        this.subscribeId = subscribeId;
    }

    public long getSubscriberUserId() {
        return subscriberUserId;
    }

    public void setSubscriberUserId(long subscriberUserId) {
        this.subscriberUserId = subscriberUserId;
    }

    public long getSubscribedBy() {
        return subscribedBy;
    }

    public void setSubscribedBy(long subscribedBy) {
        this.subscribedBy = subscribedBy;
    }

    @Override
    public String toString() {
        return "SubscriberDTO{" + "subscribeId=" + subscribeId + ", subscriberUserId=" + subscriberUserId + ", subscribedBy=" + subscribedBy + '}';
    }    

}
