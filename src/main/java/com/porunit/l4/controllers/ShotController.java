package com.porunit.l4.controllers;

import com.porunit.l4.data.Shot;
import com.porunit.l4.data.ShotInputDTO;
import com.porunit.l4.data.ShotOutputDTO;
import com.porunit.l4.services.AuthService;
import com.porunit.l4.services.ShotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hit")
@CrossOrigin("*")
public class ShotController {

    private final ShotService shotService;
    private final AuthService authService;

    @GetMapping("/all")
    public ResponseEntity<List<Shot>> getAll(@RequestHeader("Authorization") String token) {
        String username = authService.getUsernameFromHeader(token);
        return ResponseEntity.ok(shotService.getShots(username));
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(@RequestHeader("Authorization") String token){
        String username = authService.getUsernameFromHeader(token);
        shotService.deleteAll(username);
        return ResponseEntity.ok("chetko");
    }

    @PostMapping("/add")
    public ResponseEntity<ShotOutputDTO> processShot(@RequestHeader("Authorization") String token, @RequestBody @Valid ShotInputDTO shotInputDTO) {
        String username = authService.getUsernameFromHeader(token);
        boolean isHit = shotService.process(username, shotInputDTO);
        return ResponseEntity.ok(new ShotOutputDTO(isHit));
    }
}
