/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.controller;

/**
 *
 * @author Yasendra Darshana
 */
public class ControllerFactory {
      private static ControllerFactory controllerFactory;

    private ControllerFactory() {
    }

    public static ControllerFactory getInstance() {
        if (controllerFactory == null) {
            controllerFactory = new ControllerFactory();
        }
        return controllerFactory;
    }
    public SuperController getController(ControllerTypes controller) {
        switch (controller) {
            case USER:
                return new UserController();
            case SUBSCRIPTION:
                return new SubscriptionController();
         
            default:
                return null;
        }
    }
}
}
