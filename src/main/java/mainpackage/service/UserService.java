package mainpackage.service;

import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.model.SignupResponse;
import mainpackage.model.User;
import mainpackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public LoginResponse authenticate(LoginRequest loginRequest){
        User user =userRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse=new LoginResponse();
        if(user==null){
            loginResponse.setStatus(false);
            loginResponse.setMessage("user not found ,invalid email");

        }else if(user.getPassword().equals(loginRequest.getPassword())) {
            loginResponse.setStatus(true);
            loginResponse.setMessage("login successful");
        }else {
            loginResponse.setStatus(false);
            loginResponse.setMessage("invalid password");
        }
        return loginResponse;
    }


    public  SignupResponse register(User user){
     User newUser= userRepository.save(user);
        SignupResponse signupResponse=new SignupResponse();
        if(userRepository.findByEmail(newUser.getEmail())==null)
        {   signupResponse.setStatus(false);
            signupResponse.setMessage("signup failed");

        }else {
            signupResponse.setStatus(true);
            signupResponse.setMessage("signup success");
        }
        return signupResponse;
    }

}
