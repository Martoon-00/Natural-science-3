package ru.ifmo.lang;

public abstract class Equation {
    public abstract double f(Tuple t, EqualityParameters params); // for x
    public abstract double g(Tuple t, EqualityParameters params); // for y
    public abstract double h(Tuple t, EqualityParameters params); // for z
}
