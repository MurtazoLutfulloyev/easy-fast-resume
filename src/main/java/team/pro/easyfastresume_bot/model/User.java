package team.pro.easyfastresume_bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long chatId;

    private  String firstName;

    private String lastName;

    private String telegramFirstName;

    private String telegramLastName;

    private String userName;

    private String certificateLink;

    private String userLanguage;

    private String userPhoneNumber;


    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinColumn(name = "users_languages")
    private List<Language> language;

    @ManyToMany
    @JoinColumn(name = "users_educations")
    private List<Education> education;

    @OneToOne
    private AttachmentContent attachmentContent;

    @Transient
    private MultipartFile file;

    @Transient
    private int round;




}
