package com.porunit.l4;

import com.porunit.l4.data.ShotInputDTO;
import org.springframework.stereotype.Component;

@Component
public class AreaChecker {

    public boolean checkHit(ShotInputDTO shot) {
        double x = shot.getX();
        double y = shot.getY();
        int r = shot.getR();
        if (x <= 0 && y >= 0 && y <= (x + r)) {
            return true;
        }

        // Проверка попадания в прямоугольник
        if (x >= 0 && y >= 0 && x <= (double) r / 2 && y <= r) {
            return true;
        }

        // Проверка попадания в полукруг
        return x <= 0 && y <= 0 && (x * x + y * y <= r * r);

        // Если ни одно из условий не выполнено, точка вне области
    }

    private boolean checkTriangle(double x, double y, int r) {
        return y >= 0 && x <= 0;
    }

    private boolean checkRectangle(double x, double y, int r) {
        return x > 0 && y > 0 && x < r && y < r;
    }

    private boolean checkCircle(double x, double y, int r) {
        return y < 0 && x > 0 && (x + y) < (r ^ 2);
    }
}
