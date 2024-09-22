package apap.tutorial.manpromanpro.restcontroller;

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
import java.util.List;


@RestController
@RequestMapping("/api/pekerja")
public class PekerjaRestController {

    @Autowired
    PekerjaRestService pekerjaService;

    @GetMapping("/viewall")
    public ResponseEntity<?> listPekerja() {
        List<Pekerja> listPekerja = pekerjaService.getAllPekerja();

        return ResponseEntity.status(HttpStatus.OK).body(listPekerja);
    }

    @GetMapping("")
    public ResponseEntity<?> detailPekerja(@RequestParam Long id) {
        Pekerja pekerja = pekerjaService.getPekerjaById(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(pekerja);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPekerja(@Valid @RequestBody AddPekerjaRequestDTO pekerjaDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            String errorMessages = "";
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages += error.getDefaultMessage() + "; ";
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }

        Pekerja pekerja = pekerjaService.addPekerja(pekerjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pekerja);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePekerja(@Valid @RequestBody UpdatePekerjaRequestDTO pekerjaDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            String errorMessages = "";
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessages += error.getDefaultMessage() + "; ";
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }

        Pekerja pekerja = pekerjaService.updatePekerja(pekerjaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pekerja);
    }
}
