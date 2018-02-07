package com.ddshop.service.inter;

import com.ddshop.dto.MessageResult;

public interface TokenService {
    MessageResult getUserByToken(String token);
}
