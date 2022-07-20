package mainpackage.controller;

import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.model.SignupResponse;
import mainpackage.model.User;
import mainpackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;



    @PostMapping(value = "/loginreq",consumes = "application/json",produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = userService.authenticate(loginRequest);
            if (loginResponse.isStatus()) {
                return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
         LoginResponse loginResponse=null;
         return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping(value = "/signup",consumes = "application/json",produces = "application/json")
    public SignupResponse Signup(@RequestBody User user){
     SignupResponse signupResponse=userService.register(user);
     return signupResponse;
    }

}
