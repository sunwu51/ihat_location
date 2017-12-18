package top.microfrank.ihat_location.util;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
    public double[] getPos(double x1, double y1, double x2, double y2, double x3, double y3, double d1, double d2, double xk, double yk) {
        double result[] = {0, 0};
        double xkk = 0, ykk = 0;
        double fx, fy, gx, gy, f, g;
        double a = 0, b = 0;
        int count = 0;
        do {
            fx = (xk - x1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
                    - (xk - x2) / Math.sqrt((xk - x2) * (xk - x2) + (yk - y2) * (yk - y2));
            fy = (yk - y1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
                    - (yk - y2) / Math.sqrt((xk - x2) * (xk - x2) + (yk - y2) * (yk - y2));
            gx = (xk - x1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
                    - (xk - x3) / Math.sqrt((xk - x3) * (xk - x3) + (yk - y3) * (yk - y3));
            gy = (yk - y1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
                    - (yk - y3) / Math.sqrt((xk - x3) * (xk - x3) + (yk - y3) * (yk - y3));
            f = Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
                    - Math.sqrt((xk - x2) * (xk - x2) + (yk - y2) * (yk - y2)) - d1;
            g = Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
                    - Math.sqrt((xk - x3) * (xk - x3) + (yk - y3) * (yk - y3)) - d2;

            xkk = xk + (f * gy - g * fy) / (gx * fy - fx * gy);
            ykk = yk + (g * fx - f * gx) / (gx * fy - fx * gy);

            count++;
            a = Math.abs(xkk - xk);
            b = Math.abs(ykk - yk);

            xk = xkk;
            yk = ykk;
        } while (count <= 50);
        result[0] = xkk;
        result[1] = ykk;
        return result;
    }
}