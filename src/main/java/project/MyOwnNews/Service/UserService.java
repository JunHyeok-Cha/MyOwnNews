package project.MyOwnNews.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.MyOwnNews.dto.UserDto;
import project.MyOwnNews.entity.User;
import project.MyOwnNews.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        User user = new User(userdto.getUsername(), passwordEncoder.encode(userdto.getPassword()), userdto.getNickname());
        user.setInterests(userdto.getInterests());
        return userRepository.save(user);
    }

    public void check(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        log.info("input username = "+ userDto.getUsername());
        log.info("DB username = "+ userOptional.get().getUsername());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 id입니다.");
        }

        User userDB = userOptional.get();
        log.info("input password = "+ userDto.getPassword());
        log.info("input password = " + passwordEncoder.encode(userDto.getPassword()));
        log.info("DB password = "+ userDB.getPassword());
        if (!passwordEncoder.matches(userDto.getPassword(), userDB.getPassword())) {
            throw new IllegalArgumentException("password가 올바르지 않습니다.");
        }
    }

}
