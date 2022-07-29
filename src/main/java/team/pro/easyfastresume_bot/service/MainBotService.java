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
                        executeButtons(buttonService.inputLastName(), null, null);
                        round.put(chatId, 2);
                        back.put(chatId, 1);
                        break;
                    case 2:
                        map.put("lastName", message.getText());
                        globalMap.put(chatId, map);
                        executeButtons(buttonService.inputLastName(), null, null);
                        round.put(chatId, 3);
                        back.put(chatId, 1);
                        break;

                    /*** round 4 shareContact *** round 5 chooseITStudy***/
                    case 3:
                        map.put("regions", message.getText());
                        globalMap.put(chatId, map);
                        executeButtons(null, buttonService.chooseRegions(), null);
                        round.put(chatId, 4);
                        back.put(chatId, 2);
                        break;
                    case 6:
                        map.put("direction", message.getText());
                        globalMap.put(chatId, map);
                        round.put(chatId, 7);
                        executeButtons(buttonService.chooseInformation(), null, null);
                        back.put(chatId, 6);
                        break;
                    case 7:
                        if (message.getText().equals("Bachelor")) {
                            map.put("information", message.getText());
                            globalMap.put(chatId, map);
                            round.put(chatId, 8);
                            executeButtons(buttonService.chooseEducation(), null, null);
                            back.put(chatId, 7);
                            break;
                        }
                        if (message.getText().equals("Secondary education")) {
                            map.put("information", message.getText());
                            globalMap.put(chatId, map);
                            round.put(chatId, 8);
                            executeButtons(buttonService.chooseEducation(), null, null);
                            back.put(chatId, 7);
                            break;
                        }
                    case 8:
                        map.put("education", message.getText());
                        globalMap.put(chatId, map);
                        round.put(chatId, 9);
                        executeButtons(buttonService.chooseEducationDirection(), null, null);
                        back.put(chatId, 8);
                        break;
                    case 9:
                        map.put("educationDirection", message.getText());
                        globalMap.put(chatId, map);
                        round.put(chatId, 10);
                        executeButtons(buttonService.chooseCourse(map.get("information")), null, null);
                        back.put(chatId, 8);
                        break;
                    case 10:
                        if (message.getText().equals("1") || message.getText().equals("2")) {
                            map.put("startDate", message.getText());
                            globalMap.put(chatId, map);
                            round.put(chatId, 11);
                            executeButtons(buttonService.inputStartDate(), null, "bachelor");
                            back.put(chatId, 9);
                            break;
                        } else if (message.getText().equals("i finished")) {
                            map.put("finishedEducation", message.getText());
                            globalMap.put(chatId, map);
                            round.put(chatId, 11);
                            executeButtons(buttonService.inputMasterEducation(), null, "finished");
                            back.put(chatId, 9);
                            break;
                        }
                    case 11:
                        map.put("endDate", message.getText());
                        globalMap.put(chatId, map);
                        round.put(chatId, 12);
                        executeButtons(buttonService.inputEndDate(), null, null);
                        back.put(chatId, 10);
                        break;
                    default:
                        userMessage = "Nothing not found !";
                }

            }

            if (update.getMessage().hasContact()) {
                round.put(update.getMessage().getChatId(), 5);
                executeButtons(null, buttonService.chooseITStudy(), null);

            }

        }
        /*************************************************InlineKeyboards**********************************************/
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            if (round.get(user.getChatId()) == 4) {
                switch (callbackQuery.getData()) {
                    case "Andizhan":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Andizhan");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Bukhara":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Bukhara");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Fergana":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Fergana");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Jizzakh":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Jizzakh");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Namangan":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Namangan");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Navoi":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Navoi");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Surkhandarya":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Surkhandarya");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Kashkadarya":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Kashkadarya");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Syrdarya":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Syrdarya");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Samarkand":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Samarkand");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Khorezm":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Khorezm");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Karakalpakstan":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Karakalpakstan");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Tashkent Region":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Tashkent Region");
                        globalMap.put(user.getChatId(), map);
                        break;
                    case "Tashkent":
                        shareContact(null);
                        round.put(user.getChatId(), 5);
                        map.put("getRegion", "Tashkent");
                        globalMap.put(user.getChatId(), map);
                        break;
                    default:
                        userMessage = "Nothing not found !";

                }
            } else if (round.get(user.getChatId()) == 5) {
                switch (callbackQuery.getData()) {
                    case "PDP ACADEMY":
                        map.put("direction", update.getCallbackQuery().getData());
                        globalMap.put(user.getChatId(), map);
                        round.put(user.getChatId(), 6);
                        executeButtons(buttonService.inputDirection(), null, null);
                        back.put(user.getChatId(), 5);
                        break;
                }
            }
        }

        /***********************************************Contact********************************************************/


    }


    private void executeButtons(ReplyKeyboardMarkup replyKeyboardMarkup, InlineKeyboardMarkup inlineKeyboardMarkup, String data) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.getChatId()));
        sendMessage.setText(userMessages.enResponse(round.get(user.getChatId()), data));
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


    public void shareContact(String data) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(user.getChatId()));
        sendMessage.setText(userMessages.enResponse(round.get(user.getChatId()), data));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(userMessages.enResponse(round.get(user.getChatId()), data));
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
