package com.rafiki.ilernaprojectbe.service;

import com.rafiki.ilernaprojectbe.model.User;
import com.rafiki.ilernaprojectbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    public Boolean existsByUsername(String username) throws UsernameNotFoundException {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) throws UsernameNotFoundException {
        return userRepository.existsByEmail(email);
    }


    public User save(User user) {
        return userRepository.save(user);
    }
}
