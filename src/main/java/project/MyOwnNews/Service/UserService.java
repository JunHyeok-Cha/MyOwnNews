package project.MyOwnNews.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.MyOwnNews.dto.UserDto;
import project.MyOwnNews.entity.User;
import project.MyOwnNews.repository.UserRepository;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder = new UserPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(UserDto userdto){
        if(userRepository.findByUsername(userdto.getUsername()).isPresent()){
            throw new RuntimeException("이미 존재하는 ID입니다.");
        }
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(userPasswordEncoder.encode(user.getPassword()));
        user.setNickname(userdto.getNickname());
        user.setInterests(userdto.getInterests());
        return userRepository.save(user);
    }


}
