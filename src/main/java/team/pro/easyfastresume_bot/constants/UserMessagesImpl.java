package team.pro.easyfastresume_bot.constants;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
@Data
@Service
public class UserMessagesImpl implements UserMessages {

    @Override
    public String enResponse(int round, String data) {
        switch (round) {
            case 1:
                return "Enter your name: ";
            case 2:
                return "Enter your surname";
            case 3:
                return "Where do you want to work ?";
            case 4:
                return "Please send your phone number";
            case 5:
                return "Choose your profession";
            case 6:
                return "input direction";
            case 7:
                return "Select your information";
            case 8:
                return "input education";
            case 9:
                return "input education direction";
            case 10:
                return "Choose course";
            case 11:
                if(data.equals("finished")){
               return "magistir";
                }else if (data.equals("bachelor"))
                return "input start date";
            case 12:
                return "input end date";
            default:
                return "";
        }
    }

}
