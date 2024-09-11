package apap.tutorial.manpromanpro.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proyek")
public class Proyek {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @Size(max = 30)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "tanggal_mulai", nullable = false)
    private Date tanggalMulai;

    @NotNull
    @Column(name = "tanggal_selesai", nullable = false)
    private Date tanggalSelesai;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_developer", referencedColumnName = "id")
    private Developer developer;

    @ManyToMany
    @JoinTable(name = "pekerja_proyek", joinColumns = @JoinColumn(name = "id_proyek"),
            inverseJoinColumns = @JoinColumn(name = "id_pekerja"))
    List<Pekerja> listPekerja;
}
