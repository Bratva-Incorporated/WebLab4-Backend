package com.porunit.l4.services;

import com.porunit.l4.AreaChecker;
import com.porunit.l4.data.Shot;
import com.porunit.l4.data.ShotInputDTO;
import com.porunit.l4.repositories.ShotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShotService {

    private final ShotRepository shotRepository;
    private final AreaChecker areaChecker;

    public List<Shot> getShots() {
        return shotRepository.findAll();
    }

    public boolean process(ShotInputDTO dto) {
        boolean isHit = areaChecker.checkHit(dto);
        shotRepository.save(
                Shot.builder()
                .x(dto.getX())
                .y(dto.getY())
                .r(dto.getR())
                .isHit(isHit)
                .build()
        );
        return isHit;
    }

    public void deleteAll(){
        shotRepository.deleteAll();
    }
}
