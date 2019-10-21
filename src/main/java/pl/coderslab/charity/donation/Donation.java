package pl.coderslab.charity.donation;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donations")
@Setter
@Getter
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Range(min = 1, max = 1000)
    private Integer quantity;

    @NotEmpty
    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Institution institution;

    @NotBlank
    @Pattern(regexp = "^(\\S.+)\\s+(\\d+|\\d+[A-Z]+)$")
    private String street;

    @NotBlank
    private String city;

    @Pattern(regexp = "^(\\d{2}-\\d{3})$")
    private String zipCode;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @Pattern(regexp = "^[0-9]{9}$")
    private String phoneNumber;

    private String pickUpComment;

    @ManyToOne
    private User user;
    //może być null (niezalogowany użytkownik)

    @NotNull
    private Integer status;

    ///tylko jeden update, w którym użytkownik oznaczy, że przesyłka została odebrana
    @Column(name = "received_on")
    private LocalDateTime receivedOn;

    @PreUpdate
    public void preUpdate() {
        receivedOn = LocalDateTime.now();
    }

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }
}
