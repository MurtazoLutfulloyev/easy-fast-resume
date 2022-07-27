package team.pro.easyfastresume_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pro.easyfastresume_bot.model.Location;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
