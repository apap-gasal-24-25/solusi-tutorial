package apap.tutorial.manpromanpro.restcontroller;

import apap.tutorial.manpromanpro.restdto.request.AddPekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.request.UpdatePekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.restdto.response.BaseResponseDTO;
import apap.tutorial.manpromanpro.restdto.response.PekerjaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import apap.tutorial.manpromanpro.restservice.PekerjaRestService;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/pekerja")
public class PekerjaRestController {

    @Autowired
    PekerjaRestService pekerjaRestService;

    @GetMapping("/viewall")
    public ResponseEntity<?> listPekerja() {
        var baseResponseDTO = new BaseResponseDTO<List<PekerjaResponseDTO>>();
        List<PekerjaResponseDTO> listPekerja = pekerjaRestService.getAllPekerja();

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(listPekerja);
        baseResponseDTO.setMessage(String.format("List pekerja berhasil ditemukan"));
        baseResponseDTO.setTimestamp(new Date());
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> detailPekerja(@RequestParam("id") Long id) {
        var baseResponseDTO = new BaseResponseDTO<PekerjaResponseDTO>();

        PekerjaResponseDTO pekerja = pekerjaRestService.getPekerjaById(id);
        if (pekerja == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Data pekerja tidak ditemukan"));
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(pekerja);
        baseResponseDTO.setMessage(String.format("Pekerja dengan ID %s berhasil ditemukan", pekerja.getId()));
        baseResponseDTO.setTimestamp(new Date());
        
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPekerja(@Valid @RequestBody AddPekerjaRequestRestDTO pekerjaDTO,
                                           BindingResult bindingResult) {
        var baseResponseDTO = new BaseResponseDTO<PekerjaResponseDTO>();

        if (bindingResult.hasFieldErrors()) {
            String errorMessages = "";
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages += error.getDefaultMessage() + "; ";
            }

            baseResponseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
            baseResponseDTO.setMessage(errorMessages);
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.BAD_REQUEST);
        }

        PekerjaResponseDTO pekerja = pekerjaRestService.addPekerja(pekerjaDTO);
        if (pekerja == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Data pekerja tidak ditemukan"));
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
        }

        baseResponseDTO.setStatus(HttpStatus.CREATED.value());
        baseResponseDTO.setData(pekerja);
        baseResponseDTO.setMessage(String.format("Pekerja dengan ID %s berhasil ditambahkan", pekerja.getId()));
        baseResponseDTO.setTimestamp(new Date());

        return new ResponseEntity<>(baseResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePekerja(@Valid @RequestBody UpdatePekerjaRequestRestDTO pekerjaDTO,
                                           BindingResult bindingResult) {
        var baseResponseDTO = new BaseResponseDTO<PekerjaResponseDTO>();

        if (bindingResult.hasFieldErrors()) {
            String errorMessages = "";
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages += error.getDefaultMessage() + "; ";
            }

            baseResponseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
            baseResponseDTO.setMessage(errorMessages);
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.BAD_REQUEST);
        }

        PekerjaResponseDTO pekerja = pekerjaRestService.updatePekerjaRest(pekerjaDTO);
        if (pekerja == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Data pekerja tidak ditemukan"));
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(pekerja);
        baseResponseDTO.setMessage(String.format("Pekerja dengan ID %s berhasil diubah", pekerja.getId()));
        baseResponseDTO.setTimestamp(new Date());

        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }
}
