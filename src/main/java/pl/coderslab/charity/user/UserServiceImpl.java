package pl.coderslab.charity.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    ///////////////////////////////////////////////////////////////
    public void blockUser(User user) {
        user.setEnabled(0);
        userRepository.save(user);
    }

    public void unblockUser(User user) {
        user.setEnabled(1);
        userRepository.save(user);
    }

    ////////////////////////////////////////////////////////////////
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void saveUserSimpleVersion(User user) {

        userRepository.save(user);
    }

    public void saveUserSimpleVersionWithPasswordHash(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }
    ///////////////////////////////////////////////////////////////

    public List<User> findUsersWhoAreAdmins() {
        List<User> all = userRepository.findAll();
        List<User> admins = new ArrayList<>();

        all.forEach(u -> {
            int[] counter = {0};
            Set<Role> roles = u.getRoles();
            roles.forEach(r -> {
                if ("ROLE_ADMIN".equals(r.getName())) {
                    counter[0]++;
                }
            });
            if (counter[0] == 1) {
                admins.add(u);
            }
        });
        return admins;
    }

    public List<User> findUsersWhoAreUsers() {
        List<User> all = userRepository.findAll();
        List<User> users = new ArrayList<>();

        all.forEach(u -> {
            int[] counter = {0};
            Set<Role> roles = u.getRoles();
            roles.forEach(r -> {
                if ("ROLE_USER".equals(r.getName())) {
                    counter[0]++;
                }
            });
            if (counter[0] == 1) {
                users.add(u);
            }
        });
        return users;
    }

    public User findUserById(Long id) {
        User user = userRepository.findByIdNum(id);
        return user;
    }

    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }
}
