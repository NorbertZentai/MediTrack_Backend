package hu.project.MediTrack.modules.user.service;

import hu.project.MediTrack.modules.user.entity.User;
import hu.project.MediTrack.modules.user.enums.UserRole;
import hu.project.MediTrack.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service réteg: itt írhatjuk le az üzleti logikát,
 * pl. regisztráció, jelszókezelés, szerepkörváltás stb.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Visszaadja az összes felhasználót.
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Megkeresi a felhasználót ID alapján.
     */
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Megkeresi a felhasználót email alapján.
     */
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Létrehoz vagy frissít egy felhasználót.
     */
    public User saveUser(User user) {
        if (user.getRegistration_date() == null) {
            user.setRegistration_date(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    /**
     * Törli a felhasználót ID alapján (vagy Soft Delete).
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * Példa egy szerepkör-módosító metódusra.
     */
    public User updateUserRole(Integer userId, UserRole newRole) {
        return userRepository.findById(userId)
                .map(u -> {
                    u.setRole(newRole);
                    return userRepository.save(u);
                })
                .orElse(null);
    }

    // További metódusok:
    // - jelszóváltás
    // - inaktiválás
    // - stb.
}
