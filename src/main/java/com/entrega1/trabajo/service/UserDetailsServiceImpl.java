package com.entrega1.trabajo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entrega1.trabajo.model.Usuario;
import com.entrega1.trabajo.repository.UserRepository;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.repository.RefereeRepository;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private final RefereeRepository refereeRepository;

    public UserDetailsServiceImpl(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        Set<GrantedAuthority> grantList = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.getRole());
        grantList.add(grantedAuthority);

        UserDetails user = new User(username, appUser.getPassword(), grantList);

        return user;
    }


    public Referee getRefereeByUsername(String username) {
        Usuario appUser = userRepository.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        List<Referee> referees = new ArrayList<>();

        referees = refereeRepository.findAll();

        for (Referee referee : referees) {
            if (referee.getName().equals(appUser.getUsername())) {
                return referee;
            }
        }

        return null;
    
    }
}
