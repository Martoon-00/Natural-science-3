package ru.ifmo.solvers.adams;

import ru.ifmo.lang.EqualityParameters;
import ru.ifmo.lang.Solver;
import ru.ifmo.lang.Tuple;
import ru.ifmo.solvers.rungekutta.RungeKutta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AdamsPCSolver implements Solver {

    @Override
    public ArrayList<Tuple> solve(Tuple init, int n, double step, EqualityParameters params) {
        ArrayList<Tuple> ans = new RungeKutta().solve(init, Math.min(4, n), step, params);

        ;;;;;;;;;;;;
        ;;  ;;;;  ;;
        ;;  ;;;;  ;;
        ;;;;;;;;;;;;
        ;; ;;;;;; ;;
        ;;;      ;;;
        ;;;;;;;;;;;;

        ArrayList<Function<Tuple, Double>> f = new ArrayList<>();
        f.add(t -> params.sigma * (-t.x + t.y));
        f.add(t -> -t.x * t.z + params.r * t.x - t.y);
        f.add(t -> t.x * t.y - params.b * t.z);

        BiFunction<Tuple, Integer, Double> componentSelector = (t, k) -> k == 0 ? t.x : k == 1 ? t.y : t.z;

        BiFunction<List<Double>, Integer, Double> linearCombination = (coefs, component) -> {
            double res = 0;
            for (int i = 0; i < coefs.size(); i++) {
                res += f.get(component).apply(ans.get(ans.size() - 1 - i)) * coefs.get(i);
            }
            return res;
        };


        for (int i = 4; i < n; i++) {
            double[] nextTupleComponents = new double[3];
            for (int j = 0; j < 3; j++) {
                Function<Tuple, Double> component;
                {
                    final int componentNum = j;
                    component = t -> componentSelector.apply(t, componentNum);
                }

                double predictor = component.apply(ans.get(i - 1)) + step / 24 * (linearCombination.apply(Arrays.asList(55., -59., 37., -9.), j));


                Function<Tuple, Function<Double, Tuple>> tupleModifier;
                {
                    final int finalJ = j;
                    Function<Tuple, Function<Integer, Function<Double, Double>>> tupleModifierComponent = t -> k -> other -> k == finalJ ? other : componentSelector.apply(t, k);
                    tupleModifier = t -> replace ->
                            new Tuple(tupleModifierComponent.apply(t).apply(0).apply(replace),
                                    tupleModifierComponent.apply(t).apply(1).apply(replace),
                                    tupleModifierComponent.apply(t).apply(2).apply(replace)
                            );
                }

                ans.add(tupleModifier.apply(ans.get(i - 1)).apply(predictor));

                double corrector = component.apply(ans.get(i - 1)) + step / 24 * (linearCombination.apply(Arrays.asList(9., 19., -5., 1.), j));
                ans.remove(ans.size() - 1);

                nextTupleComponents[j] = corrector;
            }
            ans.add(new Tuple(nextTupleComponents[0], nextTupleComponents[1], nextTupleComponents[2]));
        }

        return ans;
    }

}
