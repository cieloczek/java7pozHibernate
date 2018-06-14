package sda.pl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Banner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String name;
    String url;
    @Lob
    byte[] image;

    boolean isForAll;


    @ManyToMany(mappedBy = "bannerSet",cascade = CascadeType.ALL)
    Set<User> userSet;

}
