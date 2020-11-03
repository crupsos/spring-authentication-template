package com.crupsos.restapi.template.services;

import com.crupsos.restapi.template.models.RoleModel;
import com.crupsos.restapi.template.models.UserModel;
import com.crupsos.restapi.template.repositories.RoleRepository;
import com.crupsos.restapi.template.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServiceUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public UserModel findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email);

        if(userModel != null) {
            List<GrantedAuthority> authorityList = userAuthority(userModel.getRoles());
            return buildUserForAuthentication(userModel, authorityList);
        } else {
            throw new UsernameNotFoundException("username was not found");
        }
    }

    private List<GrantedAuthority> userAuthority(Set<RoleModel> roleModels) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roleModels.forEach(roleModel -> {
            roles.add(new SimpleGrantedAuthority(roleModel.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(UserModel userModel, List<GrantedAuthority> authorities) {
        return new User(userModel.getEmail(), userModel.getPassword(), authorities);
    }
}
