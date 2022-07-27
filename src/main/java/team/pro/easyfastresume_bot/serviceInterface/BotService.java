package team.pro.easyfastresume_bot.serviceInterface;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface BotService {

    Long getUserChatId(Update update);

    String getUserResponse(Update update);

}
