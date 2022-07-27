package team.pro.easyfastresume_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pro.easyfastresume_bot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByChatId(Long chatId);

}


