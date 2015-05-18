package ru.ifmo.solvers.explicitEuler;

import ru.ifmo.lang.EqualityParameters;
import ru.ifmo.lang.Solver;
import ru.ifmo.lang.Tuple;

import java.util.ArrayList;

public class ExplicitEulerSolver implements Solver {
    private double r;
    private double sigma;
    private double b;

    // Thanks Lena =)
    private double f(double x, double y) {
        return -sigma * x + sigma * y;
    }
    private double g(double x, double y, double z) {
        return -x * z + r * x - y;
    }
    private double h(double x, double y, double z) {
        return x * y - b * z;
    }

    public ArrayList<Tuple> solve(Tuple init, int n, double step, EqualityParameters params) {
        r = params.r;
        sigma = params.sigma;
        b = params.b;

        ArrayList<Tuple> res = new ArrayList<>(n);
        Tuple cur = new Tuple(init.x, init.y, init.z);

        for (int i = 0; i < n; ++i)
        {
            double x = cur.x + step * f(cur.x, cur.y);
            double y = cur.y + step * g(cur.x, cur.y, cur.z);
            double z = cur.z + step * h(cur.x, cur.y, cur.z);

            cur = new Tuple(x, y, z);
            res.add(cur);
        }

        return res;
    }
}
