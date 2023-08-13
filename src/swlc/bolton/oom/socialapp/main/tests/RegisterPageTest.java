/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlc.bolton.oom.socialapp.main.tests;

import java.util.Properties;
import javax.validation.constraints.AssertTrue;
import swlc.bolton.oom.socialapp.main.conifig_email.EmailConfigUtility;
import swlc.bolton.oom.socialapp.main.conifig_email.Emailutility;
import swlc.bolton.oom.socialapp.main.constants.Constants;
import swlc.bolton.oom.socialapp.main.controller.ControllerFactory;
import swlc.bolton.oom.socialapp.main.controller.UserController;
import swlc.bolton.oom.socialapp.main.enums.ControllerTypes;
import swlc.bolton.oom.socialapp.main.store.dto.UserDTO;
import swlc.bolton.oom.socialapp.main.store.json.CommonResponse;

/**
 *
 * @author Yasendra Darshana
 */
public class RegisterPageTest {
    
    public final long TEST_ID = 1;
    public final String TEST_EMAIL = "yasendradarshana@gmail.com";
    public final String TEST_PASSWORD = "yasendra@123";
    public final String TEST_NAME = "darshana";
    private UserController userController;

    @BeforeEach
    public void init() {
        userController = (UserController) ControllerFactory.getInstance().getController(ControllerTypes.USER.USER);
    }
    
    @Test
    @DisplayName("Test register new user with valid conditions")
    public void testRegisterNewUserWithValidConditions() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD));
        AssertTrue(regResponse.isSuccess());
    }
    
    @Test
    @DisplayName("Test register new user with already registered email")
    public void testRegisterNewUserWithAlreadyRegisteredEmail() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD));
        assertEquals("Added email already exist.",regResponse.getMessage());
    }
    
    @Test
    @DisplayName("Test send email function")
    public void testSendEmailToTestUser() {
        try {
            Properties smtpProperties = new EmailConfigUtility().loadProperties();
            boolean isSend = Emailutility.sendEmail(smtpProperties, TEST_EMAIL, Constants.EMAIL_REG_SUBJECT, String.format(Constants.EMAIL_REG_BODY, TEST_NAME), null);
            assertTrue(isSend);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
  
    @Test
    @DisplayName("Test register another new user with valid details")
    public void testRegisterAnotherNewUserWithValidConditions() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(2, "Test", "test@gmail.com", "test@123"));
        assertTrue(regResponse.isSuccess());
    }
}
