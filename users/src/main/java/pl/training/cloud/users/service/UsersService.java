package pl.training.cloud.users.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.training.cloud.users.entity.User;
import pl.training.cloud.users.repository.ResultPage;
import pl.training.cloud.users.repository.UsersRepository;

public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void addUser(User user) {
        user.setActive(true);
        usersRepository.saveAndFlush(user);
    }

    public ResultPage<User> getUsers(int pageNumber, int pageSize) {
        Page<User> usersPage = usersRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(usersPage.getContent(), usersPage.getNumber(), usersPage.getTotalPages());
    }

}
