package locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Location, Long> {

    @Query("select l from Location l where lower(l.name) like :prefix")
    List<Location> findAllByPrefix(String prefix);
}
