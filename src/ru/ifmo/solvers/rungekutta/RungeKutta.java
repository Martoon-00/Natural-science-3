package ru.ifmo.solvers.rungekutta;

import ru.ifmo.lang.EqualityParameters;
import ru.ifmo.lang.Solver;
import ru.ifmo.lang.Tuple;

import java.util.ArrayList;

public class RungeKutta implements Solver {

    private double sigma;
    private double r;
    private double b;

    private double f(double x, double y) {
        return -sigma * x + sigma * y;
    }

    private double g(double x, double y, double z) {
        return -x * z + r * x - y;
    }

    private double h(double x, double y, double z) {
        return x * y - b * z;
    }

    @Override
    public ArrayList<Tuple> solve(Tuple init, int n, double step, EqualityParameters params) {
        ArrayList<Tuple> list = new ArrayList<>();
        sigma = params.sigma;
        b = params.b;
        r = params.r;

        double x0 = init.x;
        double y0 = init.y;
        double z0 = init.z;

        for (int i = 0; i < n; i++) {
            double kx0 = f(x0, y0);
            double ky0 = g(x0, y0, z0);
            double kz0 = h(x0, y0, z0);

            double kx1 = f(x0 + kx0 * step / 2, y0 + ky0 * step / 2);
            double ky1 = g(x0 + step / 2, y0 + ky0 * step / 2, z0 + kz0 * step / 2);
            double kz1 = h(x0 + step / 2, y0 + ky0 * step / 2, z0 + kz0 * step / 2);

            double kx2 = f(x0 + kx1 * step / 2, y0 + ky1 * step / 2);
            double ky2 = g(x0 + kx1 * step / 2, y0 + ky1 * step / 2, z0 + kz1 * step / 2);
            double kz2 = h(x0 + kx1 * step / 2, y0 + ky1 * step / 2, z0 + kz1 * step / 2);

            double kx3 = f(x0 + kx2 * step, y0 + ky2 * step);
            double ky3 = g(x0 + kx2 * step, y0 + ky2 * step, z0 + kz2 * step);
            double kz3 = h(x0 + kx2 * step, y0 + ky2 * step, z0 + kz2 * step);

            x0 += step * (kx0 + 2 * kx1 + 2 * kx2 + kx3) / 6;
            y0 += step * (ky0 + 2 * ky1 + 2 * ky2 + ky3) / 6;
            z0 += step * (kz0 + 2 * kz1 + 2 * kz2 + kz3) / 6;
            list.add(new Tuple(x0, y0, z0));
        }

        return list;
    }
}
