package hu.project.MediTrack.modules.user.controller;

import hu.project.MediTrack.modules.user.entity.User;
import hu.project.MediTrack.modules.user.enums.UserRole;
import hu.project.MediTrack.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * A UserController a REST végpontokat (endpoints) valósítja meg
 * a User entitáshoz kapcsolódó műveletekhez.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET /api/users
     * Az összes felhasználó lekérése.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * GET /api/users/{id}
     * Egy felhasználó lekérése ID alapján.
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findUserById(id);
        return user.orElse(null);
    }

    /**
     * POST /api/users
     * Új felhasználó létrehozása.
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Fejlesztéskor megnézheted, nincs-e már ilyen email
        return userService.saveUser(user);
    }

    /**
     * PUT /api/users/{id}
     * Létező felhasználó frissítése.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updated) {
        Optional<User> existing = userService.findUserById(id);
        if (existing.isPresent()) {
            User user = existing.get();
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
            user.setGender(updated.getGender());
            user.setDate_of_birth(updated.getDate_of_birth());
            user.setAddress(updated.getAddress());
            user.setPhone_number(updated.getPhone_number());
            user.setProfile_picture(updated.getProfile_picture());
            user.setRole(updated.getRole());
            user.setIs_active(updated.getIs_active());
            user.setLanguage(updated.getLanguage());
            user.setDeleted_at(updated.getDeleted_at());
            // stb. - attól függ, miket engedsz frissíteni
            return userService.saveUser(user);
        } else {
            return null; // vagy dobj exception-t
        }
    }

    /**
     * DELETE /api/users/{id}
     * Felhasználó törlése ID alapján.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    /**
     * PUT /api/users/{id}/role
     * Példa a szerepkör frissítésére.
     */
    @PutMapping("/{id}/role")
    public User updateUserRole(@PathVariable Integer id, @RequestParam("role") String role) {
        // Példa: a "role" param a query paraméterből jön: ?role=ADMIN
        return userService.updateUserRole(id, UserRole.valueOf(role.toUpperCase()));
    }
}
