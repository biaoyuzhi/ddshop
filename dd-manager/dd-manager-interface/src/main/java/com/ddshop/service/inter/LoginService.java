package com.ddshop.service.inter;

import com.ddshop.dto.MessageResult;

public interface LoginService {
    MessageResult userLogin(String username, String password);
}
