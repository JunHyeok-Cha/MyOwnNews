package project.MyOwnNews.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.MyOwnNews.dto.UserDto;
import project.MyOwnNews.entity.User;
import project.MyOwnNews.repository.UserRepository;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;
    public UserService(UserRepository userRepository, UserPasswordEncoder userPasswordEncoder) {
        this.userRepository = userRepository;
        this.userPasswordEncoder = userPasswordEncoder;
    }

    public User join(UserDto userdto){
        if(userdto.getUsername() == null){
            throw new IllegalArgumentException("username은 반드시 입력해야 합니다.");
        }
        if(userdto.getPassword() == null){
            throw new IllegalArgumentException("password은 반드시 입력해야 합니다.");
        }
        if(userRepository.findByUsername(userdto.getUsername()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(userPasswordEncoder.encode(userdto.getPassword()));
        user.setNickname(userdto.getNickname());
        user.setInterests(userdto.getInterests());
        return userRepository.save(user);
    }


}
