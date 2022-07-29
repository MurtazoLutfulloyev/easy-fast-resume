package team.pro.easyfastresume_bot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import team.pro.easyfastresume_bot.constants.UserMessagesImpl;
import team.pro.easyfastresume_bot.model.User;
import team.pro.easyfastresume_bot.serviceInterface.BotButtonService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BotButtonServiceImpl implements BotButtonService {

    @Autowired
    User user;

    @Autowired
    UserMessagesImpl userMessages;

    @Override
    public ReplyKeyboardMarkup createMarkupButtons(List<String> buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = makeReplyMarkup();

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        int rowCount = 2;

        for (int i = 0; i < buttons.size(); i++) {
            keyboardButtons.add(buttons.get(i));
            rowCount--;

            if ((rowCount == 0 || i == buttons.size() - 1)) {
                rowList.add(keyboardButtons);
                keyboardButtons = new KeyboardRow();
                rowCount = 2;
            }
        }
        replyKeyboardMarkup.setKeyboard(rowList);
        return replyKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup createInlineKeyboardButton(List<String> buttons, int column) {
        List<InlineKeyboardButton> buttonRow = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        int rowCount = column;
        for (int i = 0; i < buttons.size(); i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttons.get(i));
            button.setCallbackData(buttons.get(i));
            buttonRow.add(button);
            rowCount--;
            if ((rowCount == 0 || i == buttons.size() - 1)) {
                rowList.add(buttonRow);
                buttonRow=new ArrayList<>();
                rowCount = column;
            }
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    @Override
    public ReplyKeyboardMarkup makeReplyMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        return replyKeyboardMarkup;
    }

    @Override
    public InlineKeyboardMarkup makeInlineMarkup() {
        return null;
    }

    @Override
    public ReplyKeyboardMarkup inputFirstName() {
        return createMarkupButtons(List.of("Back"));
    }


    @Override
    public ReplyKeyboardMarkup inputLastName() {
      return createMarkupButtons(List.of("Back"));
    }
    @Override
    public InlineKeyboardMarkup chooseITStudy() {
   return createInlineKeyboardButton(List.of("PDP ACADEMY","Najot Ta'lim","Astrum","Bobir Akilkhanov Academy", "Proweb", "Not on this list", "I prepared independently"),2);
    }

    @Override
    public ReplyKeyboardMarkup inputDirection() {
        return createMarkupButtons(List.of("Back"));
    }

    @Override
    public ReplyKeyboardMarkup chooseInformation() {
        return createMarkupButtons(List.of("Bachelor", "Secondary education", "Main menu", "back"));
    }

    @Override
    public ReplyKeyboardMarkup chooseEducation() {
       return createMarkupButtons(List.of("Main menu", "back"));
    }

    @Override
    public ReplyKeyboardMarkup chooseEducationDirection() {
        return createMarkupButtons(List.of("Main menu", "back"));
    }

    @Override
    public ReplyKeyboardMarkup chooseCourse(String information) {
        if(information.equals("Bachelor")){
            return createMarkupButtons(List.of("1","2","3","4","i finished","Main menu", "back"));
        }
        return createMarkupButtons(List.of("Main menu", "back"));
    }

    @Override
    public ReplyKeyboardMarkup inputStartDate() {
        return createMarkupButtons(List.of("Main menu", "back"));
    }

    @Override
    public ReplyKeyboardMarkup inputEndDate() {
        return createMarkupButtons(List.of("Present","Main menu", "back"));

    }

    @Override
    public ReplyKeyboardMarkup inputMasterEducation() {
        return createMarkupButtons(List.of("Main menu", "back"));

    }

    @Override
    public InlineKeyboardMarkup chooseRegions() {
        return createInlineKeyboardButton(List.of("Andizhan","Bukhara","Fergana","Jizzakh", "Khorezm", "Namangan", "Navoi", "Surkhandarya", "Syrdarya", "Kashkadarya", "Samarkand", "Tashkent Region", "Karakalpakstan", "Tashkent"),3);
    }
}
