package ru.ifmo.lang;

import java.util.*;

public interface Solver {
    ArrrayList<Tuple> solve(Tuple init, int n, double step, EqualityParameters params);
}
