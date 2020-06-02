package in.aprilfish.service;

import in.aprilfish.mapper.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findById(int id){
        User user=new User();
        user.setId(1L);
        user.setName("may");

        return user;
    }

}
