package ru.ifmo.solvers.rungekutta;

//import com.company.Tuple;
import ru.ifmo.lang.Tuple;

public class RungeKutta {

    private double sigma;
    private double r;
    private double b;

    public RungeKutta(double sigma, double r, double b) {
        this.sigma = sigma;
        this.r = r;
        this.b = b;
    }

    public Tuple solve(double x0, double y0, double z0, double delta,int steps) {

        for (int i = 0; i < steps; i++ ) {
            double kx0 = f(x0, y0);
            double ky0 = g(x0, y0, z0);
            double kz0 = h(x0, y0, z0);

            double kx1 = f(x0 + kx0 * delta/2, y0 + ky0 * delta / 2);
            double ky1 = g(x0 + delta / 2, y0 + ky0 * delta / 2, z0 + kz0 * delta / 2);
            double kz1 = h(x0 + delta / 2, y0 + ky0 * delta / 2, z0 + kz0 * delta / 2);

            double kx2 = f(x0 + kx1 * delta/2, y0 + ky1 * delta / 2);
            double ky2 = g(x0 + kx1 * delta / 2, y0 + ky1 * delta / 2, z0 + kz1 * delta / 2);
            double kz2 = h(x0 + kx1 * delta / 2, y0 + ky1 * delta / 2, z0 + kz1 * delta / 2);

            double kx3 = f(x0 + kx2 * delta, y0 + ky2 * delta);
            double ky3 = g(x0 + kx2 * delta, y0 + ky2 * delta, z0 + kz2 * delta);
            double kz3 = h(x0 + kx2 * delta, y0 + ky2 * delta, z0 + kz2 * delta);

            x0 = kx3;
            y0 = ky3;
            z0 = kz3;
        }

        return new Tuple(x0, y0, z0);
    }

    private double f(double x, double y) {
        return - sigma * x + sigma * y;
    }

    private double g(double x, double y, double z) {
        return -x * z + r * x - y;
    }

    private double h(double x, double y, double z) {
        return x * y - b * z;
    }
}
