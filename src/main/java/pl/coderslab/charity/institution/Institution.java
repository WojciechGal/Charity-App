package pl.coderslab.charity.institution;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "institutions")
@Setter
@Getter
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "institution")
    private List<Donation> donations = new ArrayList<>();
}
