package ru.ifmo.lang;

import java.util.ArrayList;

public interface Solver {
    ArrayList<Tuple> solve(Tuple init, int n, double step, EqualityParameters params);
}
