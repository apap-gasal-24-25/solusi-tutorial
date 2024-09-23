package apap.tutorial.manpromanpro.dto.request;

import java.util.List;

import apap.tutorial.manpromanpro.model.Pekerja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteMultiplePekerjaDTO {
    private List<Pekerja> listPekerja;
}

