package apap.tutorial.manpromanpro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Lob
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Column(name = "tanggal_berdiri", nullable = false)
    private Date tanggalBerdiri;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Proyek> listProyek;
}
