package com.porunit.l4.controllers;

import com.porunit.l4.data.Shot;
import com.porunit.l4.data.ShotInputDTO;
import com.porunit.l4.data.ShotOutputDTO;
import com.porunit.l4.services.ShotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hit")
public class ShotController {

    private final ShotService shotService;

    @GetMapping("/all")
    public ResponseEntity<List<Shot>> getAll() {
        return ResponseEntity.ok(shotService.getShots());
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(){
        shotService.deleteAll();
        return ResponseEntity.ok("chetko");
    }

    @PostMapping("/add")
    public ResponseEntity<ShotOutputDTO> processShot(@RequestBody @Valid ShotInputDTO shotInputDTO) {
        boolean isHit = shotService.process(shotInputDTO);
        return ResponseEntity.ok(new ShotOutputDTO(isHit));
    }
}
