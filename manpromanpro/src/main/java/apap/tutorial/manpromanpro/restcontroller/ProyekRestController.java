package apap.tutorial.manpromanpro.restcontroller;

import apap.tutorial.manpromanpro.dto.request.AddProyekRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProyekRequestDTO;
import apap.tutorial.manpromanpro.dto.response.BaseResponseDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.restservice.ProyekRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proyek")
public class ProyekRestController {

    @Autowired
    ProyekRestService proyekRestService;

    @PostMapping("/add")
    public ResponseEntity<?> addProyek(@Valid @RequestBody AddProyekRequestDTO proyekDTO, BindingResult bindingResult) {
        var baseResponseDTO = new BaseResponseDTO<Proyek>();
        if (bindingResult.hasFieldErrors()) {
            String errorMessages = "";
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages += error.getDefaultMessage() + "; ";
            }

            baseResponseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
            baseResponseDTO.setMessage(errorMessages);
            baseResponseDTO.setTimestamp(new Date());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseDTO);
        }

        var proyek = proyekRestService.addProyek(proyekDTO);
        baseResponseDTO.setStatus(HttpStatus.CREATED.value());
        baseResponseDTO.setData(proyek);
        baseResponseDTO.setMessage(String.format("Proyek dengan ID %s berhasil ditambahkan", proyek.getId()));
        baseResponseDTO.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(baseResponseDTO);
    }

    @GetMapping("/{idProyek}")
    public ResponseEntity<?> getProyek(@PathVariable UUID idProyek) {
        var baseResponseDTO = new BaseResponseDTO<Proyek>();
        var proyek = proyekRestService.getProyekById(idProyek);
        if (proyek == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Proyek dengan ID %s tidak ditemukan", idProyek));
            baseResponseDTO.setTimestamp(new Date());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponseDTO);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(proyek);
        baseResponseDTO.setMessage(String.format("Proyek dengan ID %s berhasil ditemukan", proyek.getId()));
        baseResponseDTO.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProyek(@Valid @RequestBody UpdateProyekRequestDTO proyekDTO, BindingResult bindingResult) {
        var baseResponseDTO = new BaseResponseDTO<Proyek>();
        if (bindingResult.hasFieldErrors()) {
            String errorMessages = "";
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages += error.getDefaultMessage() + "; ";
            }

            baseResponseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
            baseResponseDTO.setMessage(errorMessages);
            baseResponseDTO.setTimestamp(new Date());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseDTO);
        }

        var proyek = proyekRestService.updateProyek(proyekDTO);
        if (proyek == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Proyek dengan ID %s tidak ditemukan", proyek.getId()));
            baseResponseDTO.setTimestamp(new Date());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponseDTO);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(proyek);
        baseResponseDTO.setMessage(String.format("Proyek dengan ID %s berhasil diubah", proyek.getId()));
        baseResponseDTO.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDTO);
    }

    @DeleteMapping("/{idProyek}/delete")
    public ResponseEntity<?> deleteProyek(@PathVariable UUID idProyek) {
        var baseResponseDTO = new BaseResponseDTO<Proyek>();
        var proyek = proyekRestService.deleteProyek(idProyek);
        if (proyek == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Proyek dengan ID %s tidak ditemukan", idProyek));
            baseResponseDTO.setTimestamp(new Date());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponseDTO);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setMessage(String.format("Proyek dengan ID %s berhasil dihapus", proyek.getId()));
        baseResponseDTO.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.OK).body(baseResponseDTO);
    }
}
