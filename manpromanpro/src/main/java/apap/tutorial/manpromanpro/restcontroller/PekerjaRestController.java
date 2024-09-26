package apap.tutorial.manpromanpro.restcontroller;

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

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdatePekerjaRequestDTO;
import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.restservice.PekerjaRestService;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/pekerja")
public class PekerjaRestController {

    @Autowired
    PekerjaRestService pekerjaService;

    @GetMapping("/viewall")
    public ResponseEntity<?> listPekerja() {
        var baseResponseDTO = new BaseResponseDTO<List<PekerjaResponseDTO>>();
        List<PekerjaResponseDTO> listPekerja = pekerjaService.getAllPekerja();

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(listPekerja);
        baseResponseDTO.setMessage(String.format("Berhasil mendapatkan data-data pekerja"));
        baseResponseDTO.setTimestamp(new Date());
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> detailPekerja(@RequestParam Long id) {
        var baseResponseDTO = new BaseResponseDTO<PekerjaResponseDTO>();

        PekerjaResponseDTO pekerja = pekerjaService.getPekerjaById(id);
        if (pekerja == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Data pekerja tidak ditemukan"));
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(pekerja);
        baseResponseDTO.setMessage("Berhasil mendapatkan data pekerja");
        baseResponseDTO.setTimestamp(new Date());
        
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPekerja(@Valid @RequestBody AddPekerjaRequestDTO pekerjaDTO,
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

        PekerjaResponseDTO pekerja = pekerjaService.addPekerja(pekerjaDTO);
        if (pekerja == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Data pekerja tidak ditemukan"));
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
        }

        baseResponseDTO.setStatus(HttpStatus.CREATED.value());
        baseResponseDTO.setData(pekerja);
        baseResponseDTO.setMessage("Berhasil menambahkan data pekerja");
        baseResponseDTO.setTimestamp(new Date());

        return new ResponseEntity<>(baseResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePekerja(@Valid @RequestBody UpdatePekerjaRequestDTO pekerjaDTO,
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

        PekerjaResponseDTO pekerja = pekerjaService.updatePekerja(pekerjaDTO);
        if (pekerja == null) {
            baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            baseResponseDTO.setMessage(String.format("Data pekerja tidak ditemukan"));
            baseResponseDTO.setTimestamp(new Date());
            return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
        }

        baseResponseDTO.setStatus(HttpStatus.OK.value());
        baseResponseDTO.setData(pekerja);
        baseResponseDTO.setMessage("Berhasil mengubah data pekerja");
        baseResponseDTO.setTimestamp(new Date());

        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }
}
