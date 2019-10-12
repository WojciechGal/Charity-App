package pl.coderslab.charity.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public void saveDonation(Donation donation) {

        ///naprawa ręczna czasu UTC odejmującego od 1 do 2 godzin
        ///nie powinno być problematyczne, ponieważ local date przechowuje tylko datę
        donation.setPickUpDate(donation.getPickUpDate().plusDays(1));

        donationRepository.save(donation);
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation findDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public Long countAllQuantities() {
        return donationRepository.countAllQuantities();
    }
}
