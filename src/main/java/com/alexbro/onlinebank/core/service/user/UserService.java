package com.alexbro.onlinebank.core.service.user;
import com.alexbro.onlinebank.core.entity.user.User;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);
}
