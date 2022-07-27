package team.pro.easyfastresume_bot.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte[] data;

    @OneToOne
    private Attachment attachment;


    public AttachmentContent(byte[] data, Attachment attachment) {
        this.data = data;
        this.attachment = attachment;
    }
}
