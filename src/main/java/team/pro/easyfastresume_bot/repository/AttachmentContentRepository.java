package team.pro.easyfastresume_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pro.easyfastresume_bot.model.AttachmentContent;


public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    AttachmentContent findByAttachmentId(Integer attachment_id);

}
