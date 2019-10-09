package pl.coderslab.charity.user;

public interface UserService {

    User findByUserEmail(String email);

    void saveUser(User user);
}