package ru.ifmo.solvers.euler;

import ru.ifmo.lang.EqualityParameters;
import ru.ifmo.lang.Solver;
import ru.ifmo.lang.Tuple;

import java.util.ArrayList;

public class EulerImplicitSolver implements Solver {
	@Override
	public ArrayList<Tuple> solve(Tuple init, int n, double step, EqualityParameters params) {
		FunctionTuple[] functions = new FunctionTuple[]{
				(tuple) -> params.sigma * (tuple.y - tuple.x),
				(tuple) -> -tuple.x * tuple.z + params.r * tuple.x - tuple.y,
				(tuple) -> tuple.x * tuple.y - params.b * tuple.z
		};
		FunctionTuple[] derivativeFun = new FunctionTuple[]{
				(tuple) -> -params.sigma,
				(tuple) -> -1,
				(tuple) -> -params.b
		};
		ArrayList<Tuple> list = new ArrayList<>();
		list.add(init);
		for (int i = 0; i < n; i++) {
			Tuple last = list.get(list.size() - 1);
			double[] d = new double[3];
			for (int j = 0; j < d.length; j++)
				d[j] = (functions[j].calc(last) * step) / (1 - derivativeFun[j].calc(last) * step);
			list.add(new Tuple(last.x + d[0], last.y + d[1], last.z + d[2]));
		}
		return list;
	}

	@FunctionalInterface
	interface FunctionTuple {
		double calc(Tuple tuple);
	}
}
