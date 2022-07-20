package mainpackage.service;

import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.model.SignupResponse;
import mainpackage.model.User;
import mainpackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class UserService {

     @Value("${pepper}")
     String pepper;

    @Autowired
    UserRepository userRepository;


    public LoginResponse authenticate(LoginRequest loginRequest){
        User user =userRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse=new LoginResponse();
        if(user==null){
            loginResponse.setStatus(false);
            loginResponse.setMessage("user not found ,invalid email");

        }else if(BCrypt.hashpw(loginRequest.getPassword(),user.getSalt()+pepper).equals(user.getPassword())) {
            loginResponse.setStatus(true);
            loginResponse.setMessage("login successful");
        }else {
            loginResponse.setStatus(false);
            loginResponse.setMessage("invalid password");
        }
        return loginResponse;
    }


    public  SignupResponse register(User user){

        User currerntUser =userRepository.findByEmail(user.getEmail());
        SignupResponse signupResponse=new SignupResponse();
        if(currerntUser !=null)
        { signupResponse.setStatus(false);
            signupResponse.setMessage("user already exist");
            return signupResponse;

        } else {
            String salt= BCrypt.gensalt();
            String hasedPassword=BCrypt.hashpw(user.getPassword(),salt+pepper);
            user.setPassword(hasedPassword);
            user.setSalt(salt);
            userRepository.save(user);

        signupResponse.setStatus(true);
        signupResponse.setMessage("signup successful!");
        return  signupResponse;
    }
    }

}
