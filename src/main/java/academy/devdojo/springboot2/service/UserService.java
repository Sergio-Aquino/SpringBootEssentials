package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService /*implements UserDetailsService*/ {

    private final UserRepository userRepository;

    /*
    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
     */

}