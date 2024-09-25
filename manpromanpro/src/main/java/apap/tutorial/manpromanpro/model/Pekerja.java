package apap.tutorial.manpromanpro.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Size(max = 30)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "usia", nullable = false)
    private int usia;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Size(max = 30)
    @Column(name = "pekerjaan", nullable = false)
    private String pekerjaan;

    @NotNull
    @Column(name = "biografi", columnDefinition = "TEXT", nullable = false)
    private String biografi;

    @Column(name = "deleted_at")
    private Date deleted_at;

    @ManyToMany
    List<Proyek> listProyek;
}
