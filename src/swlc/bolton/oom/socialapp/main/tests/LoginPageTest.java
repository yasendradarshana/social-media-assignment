/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.tests;

import javax.validation.constraints.AssertFalse;
import swlc.bolton.oom.socialapp.main.controller.UserController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import swlc.bolton.oom.socialapp.main.controller.ControllerFactory;
import swlc.bolton.oom.socialapp.main.enums.ControllerTypes;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;

/**
 *
 * @author Yasendra Darshana
 */
public class LoginPageTest {
    
    public final long TEST_ID = 1;
    public final String TEST_EMAIL = "yasendradarshana@gmail.com";
    public final String TEST_PASSWORD = "yasendra123";
    public final String TEST_NAME = "darshana";

    private UserController userController;

    @BeforeEach
    public void init() {
        userController = (UserController) ControllerFactory.getInstance().getController(ControllerTypes.USER);
    }

    @Test
    @DisplayName("with invalid credentials")
    public void testUserLoginWithInvalidCredentials() {
        CommonResponse loginResponse = userController.loginHandler(new UserDTO(TEST_EMAIL, "invalid@passwod"));
        AssertFalse(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("with valid credentials")
    public void testUserLoginWithValidCredentials() {
        CommonResponse loginResponse = userController.loginHandler(new UserDTO(TEST_EMAIL, TEST_PASSWORD));
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("Test user remove account")
    public void testUserRemoveAccount() {
        UserDTO loggedUserObj = new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD);
        CommonResponse removeResp = userController.removeAccountHandler(loggedUserObj);
        assertTrue(removeResp.isSuccess());
    }
}
