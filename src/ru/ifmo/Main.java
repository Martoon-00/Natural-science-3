package ru.ifmo;

import ru.ifmo.lang.EqualityParameters;
import ru.ifmo.lang.Solver;
import ru.ifmo.lang.Tuple;
import ru.ifmo.solvers.rungekutta.RungeKutta;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        Solver solver = new RungeKutta();
        final ArrayList<Tuple> solution = solver.solve(new Tuple(5, 10, 7), 1000, 1e-3, new EqualityParameters(0.5, 10, 8.0 / 3));

        for (int i = 0; i < solution.size(); i++){
            System.out.println(solution.get(i));
        }

    }
}
