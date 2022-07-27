package team.pro.easyfastresume_bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import team.pro.easyfastresume_bot.constants.UserMessagesImpl;
import team.pro.easyfastresume_bot.model.User;
import team.pro.easyfastresume_bot.service.impl.BotButtonServiceImpl;
import team.pro.easyfastresume_bot.service.impl.BotServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MainBotService extends TelegramLongPollingBot {

    @Autowired
    BotButtonServiceImpl buttonService;

    @Autowired
    UserMessagesImpl userMessages;

    @Autowired
    BotServiceImpl service;

    @Autowired
    User user;

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    private final Map<Long, Integer> back = new HashMap<>();

    private final Map<Long, String> lang = new HashMap<>();

    private final Map<Long, Integer> round = new HashMap<>();

    private final HashMap<Long, HashMap<String, String>> globalMap = new HashMap<Long, HashMap<String, String>>();

    private final HashMap<String, String> map = new HashMap<String, String>();

    private String userMessage;

    @Value("${telegram.bot.username}")
    private String username;

    @Value("${telegram.bot.token}")
    private String token;

//    @Value("${telegram.bot.groupChatId}")
//    private String groupChatId;


    @Override
    public String getBotUsername() {
        return this.username;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        user.setChatId(service.getUserChatId(update));
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = service.getUserChatId(update);
            if ("/start".equals(message.getText())) {
                round.put(chatId, 1);
            }
            if (update.hasMessage() && message.hasText()) {
                if (message.getText().equals("Main menu")) {
                    message.setText("/start");
                    onUpdateReceived(update);

                }
                switch (round.get(chatId)) {
                    case 1:
                        map.put("firstName", message.getText());
                        globalMap.put(chatId, map);
                        executeButtons(buttonService.inputLastName(), null);
                        round.put(chatId, 2);
                        back.put(chatId, 1);
                        break;
                    case 2:
                        map.put("lastName", message.getText());
                        globalMap.put(chatId, map);
                        executeButtons(buttonService.inputLastName(), null);
                        round.put(chatId, 3);
                        back.put(chatId, 1);
                        break;

                    /*** round 4 shareContact *** round 5 chooseITStudy***/
                    case 3:
                        map.put("regions", message.getText());
                        globalMap.put(chatId, map);
                        executeButtons(null, buttonService.chooseRegions());
                        round.put(chatId, 4);
                        back.put(chatId, 2);
                        break;
                    default:
                        userMessage = "Nothing not found !";
                }

            }

        }
        /*************************************************InlineKeyboards******************************************/
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            if (round.get(user.getChatId()) == 4) {
                switch (callbackQuery.getData()) {
                    case "Andizhan":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Andizhan");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Bukhara":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Bukhara");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Fergana":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Fergana");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Jizzakh":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Jizzakh");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Namangan":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Namangan");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Navoi":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Navoi");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Surkhandarya":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Surkhandarya");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Kashkadarya":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Kashkadarya");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Syrdarya":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Syrdarya");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Samarkand":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Samarkand");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Khorezm":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Khorezm");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Karakalpakstan":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Karakalpakstan");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Tashkent Region":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Tashkent Region");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Tashkent":
                        round.put(user.getChatId(), 4);
                        shareContact();
                        map.put("getRegion", "Tashkent");
                        globalMap.put(user.getChatId(), map);
                        break;
                    default:
                        userMessage = "Nothing not found !";

                }
            } else if (round.get(user.getChatId()) == 4) {
                switch (callbackQuery.getData()) {
                    case "PDP ACADEMY":
                        break;
                }
            }
        }

        /***********************************************Contact********************************************************/
        if (update.getMessage().hasContact()) {
            executeButtons(null, buttonService.chooseITStudy());

        }


    }


    private void executeButtons(ReplyKeyboardMarkup replyKeyboardMarkup, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.getChatId()));
        sendMessage.setText(userMessages.enResponse(round.get(user.getChatId())));
        sendMessage.enableHtml(true);
        if (replyKeyboardMarkup != null)
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        else if (inlineKeyboardMarkup != null)
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void shareContact() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.getChatId()));
        sendMessage.setText(userMessages.enResponse(round.get(user.getChatId())));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(userMessages.enResponse(round.get(user.getChatId())));
        keyboardButton.setRequestContact(true);
        keyboardFirstRow.add(keyboardButton);
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
