package pl.training.cloud.users.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pl.training.cloud.users.config.Role;
import pl.training.cloud.users.entity.Authority;
import pl.training.cloud.users.entity.User;
import pl.training.cloud.users.repository.AuthoritiesRepository;
import pl.training.cloud.users.repository.ResultPage;
import pl.training.cloud.users.repository.UsersRepository;

@Transactional
public class UsersService {

    private UsersRepository usersRepository;
    private AuthoritiesRepository authoritiesRepository;
    private PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, AuthoritiesRepository authoritiesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {
        user.setActive(true);
        encodeUserPassword(user);
        setDefaultAuthority(user);
        usersRepository.saveAndFlush(user);
    }

    public ResultPage<User> getUsers(int pageNumber, int pageSize) {
        Page<User> usersPage = usersRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(usersPage.getContent(), usersPage.getNumber(), usersPage.getTotalPages());
    }

    private void encodeUserPassword(User user) {
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
    }

    private void setDefaultAuthority(User user) {
        Authority authority = new Authority(user.getLogin(), Role.ROLE_ADMIN);
        authoritiesRepository.saveAndFlush(authority);
    }

}
