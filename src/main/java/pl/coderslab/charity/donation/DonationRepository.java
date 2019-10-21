package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.user.User;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(quantity) from Donation d where d.status = 1")
    Long countAllQuantitiesSend();

    List<Donation> findAllByUser(User user);
}
