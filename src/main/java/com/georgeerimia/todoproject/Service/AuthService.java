package com.georgeerimia.todoproject.Service;

import com.georgeerimia.todoproject.dtos.LoginDTO;
import com.georgeerimia.todoproject.dtos.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);
}
