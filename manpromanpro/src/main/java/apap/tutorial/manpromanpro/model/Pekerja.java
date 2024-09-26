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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pekerja")
@SQLDelete(sql = "UPDATE pekerja SET deleted_at = NOW() WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
public class Pekerja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "usia", nullable = false)
    private int usia;

    @NotNull
    @Size(max = 30)
    @Column(name = "pekerjaan", nullable = false)
    private String pekerjaan;

    @Column(name = "biografi", columnDefinition = "TEXT")
    private String biografi;

    @Column(name = "deleted_at")
    private Date deleted_at;

    @JsonManagedReference
    @ManyToMany
    List<Proyek> listProyek;
}
