package ru.ifmo.server;

import ru.ifmo.lang.EqualityParameters;
import ru.ifmo.lang.Solver;
import ru.ifmo.lang.Tuple;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;

@Path("/solve")
public class SolutionResource {
    @GET
    @Path("/{method}")
    public String solve(
            @PathParam("method") String method,
            @QueryParam("r") double r,
            @QueryParam("sigma") double sigma,
            @QueryParam("b") double b,
            @QueryParam("x0") double x0,
            @QueryParam("y0") double y0,
            @QueryParam("z0") double z0,
            @QueryParam("step") double step,
            @QueryParam("n") int n,
            @QueryParam("id") int id
    ) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        final Solver solver = (Solver) Class.forName(method).newInstance();
        final ArrayList<Tuple> solution = solver.solve(new Tuple(x0, y0, z0), n, step, new EqualityParameters(r, sigma, b));

        final StringBuilder result = new StringBuilder().append(id).append("\n");
        for (Tuple tuple : solution) {
            result.append(tuple).append("\n");
        }
        return result.toString();
    }
}
