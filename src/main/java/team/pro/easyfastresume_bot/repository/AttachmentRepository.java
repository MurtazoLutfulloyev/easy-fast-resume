package team.pro.easyfastresume_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pro.easyfastresume_bot.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
