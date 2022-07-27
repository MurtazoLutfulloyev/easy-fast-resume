package team.pro.easyfastresume_bot.constants;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;



@NoArgsConstructor
@Data
@Service
public class UserMessagesImpl implements UserMessages {



    @Override
    public String enResponse(int round) {
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
                return "";


            default:
                return "";
        }
    }

}
