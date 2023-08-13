/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.store.impl;

import java.util.ArrayList;
import swlc.bolton.oom.socialapp.main.constants.Constants;
import swlc.bolton.oom.socialapp.main.enums.StoreTypes;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;

/**
 *
 * @author Yasendra Darshana
 */
public class UserStore implements SuperStore<UserDTO>{
    
    public static boolean isDevVersion = false; // for dev purposes
    private static final ArrayList<UserDTO> registeredUserList = new ArrayList<>(); // registered user list

    @Override
    public synchronized CommonResponse reserve(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreTypes.RESERVE);
    }

    @Override
    public CommonResponse release(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreTypes.RELEASE);
    }
    
    
    @Override
    public CommonResponse retrieveListHandler() {
        return new CommonResponse(true, registeredUserList);
    }
    
    @Override
    public CommonResponse retrieveData(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreTypes.RETRIEVE);
    }

    @Override
    public CommonResponse checkAvailability(UserDTO userDTO, StoreTypes store) {
        try {
            UserDTO availableObj = null;
            for (UserDTO dto : registeredUserList) {
                if (userDTO.getEmail().equals(dto.getEmail())) availableObj = dto;
            }
            switch (store) {
                case RESERVE:
                    if (availableObj == null) {
                        registeredUserList.add(userDTO);
                        return new CommonResponse(true, Constants.COMMON_SUCCESS_MSG, userDTO);
                    } else {
                        return new CommonResponse(false,  Constants.ADDED_EMAIL_ALREADY_EXIST);
                    }
                case RELEASE:
                    if (availableObj != null) {
                        registeredUserList.remove(availableObj);
                        return new CommonResponse(true, availableObj);
                    }   break;
                case RETRIEVE:
                    if (availableObj != null) return new CommonResponse(true, availableObj);
                    break;
                default:
                    break;
            }
            return new CommonResponse(false,  Constants.USER_NOT_FOUND);
        } catch (Exception e) {
            return new CommonResponse(false, null, e.getMessage());
        }
    }

}
