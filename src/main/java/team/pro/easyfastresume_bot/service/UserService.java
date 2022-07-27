package team.pro.easyfastresume_bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import team.pro.easyfastresume_bot.model.User;
import team.pro.easyfastresume_bot.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(Update update, String lang){
        Long chatId = update.getMessage().getChatId();
        Optional<User> getUserByChatIdFromDb = userRepository.findByChatId(chatId);
        User user =  new User();
        if (getUserByChatIdFromDb.isEmpty()) {

            user.setTelegramFirstName(update.getMessage().getFrom().getFirstName());
            user.setTelegramLastName(update.getMessage().getFrom().getLastName());
            user.setUserName(update.getMessage().getFrom().getUserName());
            user.setUserLanguage(lang);
            userRepository.save(user);
        }
    }
}
