package ru.ifmo.solvers.euler;

import ru.ifmo.lang.EqualityParameters;
import ru.ifmo.lang.Solver;
import ru.ifmo.lang.Tuple;

import java.util.ArrayList;

public class EulerPCSolver implements Solver {
    private EqualityParameters parameters;
    private double step;


    @Override
    public ArrayList<Tuple> solve(Tuple init, int n, double step, EqualityParameters params) {
        parameters = params;
        this.step = step;
        ArrayList<Tuple> answer = new ArrayList<>();
        answer.add(init);
        for (int i = 0; i < n; i++) {
            Tuple last = answer.get(answer.size() - 1);
            double
                    x = newX(last.x, last.x, last.y),
                    y = newY(last.y, last.x, last.y, last.z),
                    z = newZ(last.z, last.x, last.y, last.z);
            double newX = newX(last.x, x, y),
                    newY = newY(last.y, x, y, z),
                    newZ = newZ(last.z, x, y, z);
            answer.add(new Tuple(newX,newY,newZ));
        }

        return answer;
    }

    private double newX(double x, double fX, double fY) {
        return x + step * parameters.sigma * (fY - fX);
    }

    private double newY(double y, double fX, double fY, double fZ) {
        return y + step * (-fX * fZ + parameters.r * fX - fY);
    }

    private double newZ(double z, double fX, double fY, double fZ) {
        return z + step * (fX * fY - parameters.b * fZ);
    }

}
