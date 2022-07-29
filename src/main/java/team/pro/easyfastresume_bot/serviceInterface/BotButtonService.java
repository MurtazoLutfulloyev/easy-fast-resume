package team.pro.easyfastresume_bot.serviceInterface;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

public interface BotButtonService {

    ReplyKeyboardMarkup createMarkupButtons(List<String> buttons);

    ReplyKeyboardMarkup makeReplyMarkup();

    InlineKeyboardMarkup createInlineKeyboardButton(List<String> buttons, int column);

    InlineKeyboardMarkup makeInlineMarkup();


    ReplyKeyboardMarkup inputFirstName();

    ReplyKeyboardMarkup inputLastName();

    InlineKeyboardMarkup chooseRegions();
    /************************************************InlineKeyboards***************************************************/

    InlineKeyboardMarkup chooseITStudy();

    ReplyKeyboardMarkup inputDirection();

    ReplyKeyboardMarkup chooseInformation();

    ReplyKeyboardMarkup chooseEducation();

    ReplyKeyboardMarkup chooseEducationDirection();

    ReplyKeyboardMarkup chooseCourse(String information);

    ReplyKeyboardMarkup inputStartDate();

    ReplyKeyboardMarkup inputEndDate();

    ReplyKeyboardMarkup inputMasterEducation();
}
