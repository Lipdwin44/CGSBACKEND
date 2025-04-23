package com.cgs.student.cgsstudentproject.config;



import com.cgs.student.cgsstudentproject.models.studentdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Userprinciples implements UserDetails {

    @Autowired
    private studentdetails studentdetails;

    public Userprinciples(studentdetails studentdetails)
    {
        this.studentdetails = studentdetails;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return studentdetails.getPassword();
    }

    @Override
    public String getUsername() {
        return studentdetails.getStudentname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


