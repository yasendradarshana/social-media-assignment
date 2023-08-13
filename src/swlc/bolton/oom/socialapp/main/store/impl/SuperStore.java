/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.store.impl;

import swlc.bolton.oom.socialapp.main.enums.StoreTypes;
import swlc.bolton.oom.socialapp.main.store.dto.SuperDTO;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;

/**
 *
 * @author Yasendra Darshana
 */
public interface SuperStore <T extends SuperDTO>{
    public CommonResponse reserve(T dto) throws Exception;
    public CommonResponse release(T dto) throws Exception;
    public CommonResponse retrieveListHandler() throws Exception;
    public CommonResponse retrieveData(T dto) throws Exception;
    public CommonResponse checkAvailability(T dto, StoreTypes store) throws Exception;
}
