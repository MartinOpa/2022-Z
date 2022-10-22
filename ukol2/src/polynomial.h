#pragma once
#include <vector>
#include <iostream>

using namespace std;

class Polynomial
{
private:
    vector<double> polynomialVec;
    int degree;
public:
    Polynomial();
    Polynomial(double a0);
    Polynomial(double a0, double a1);
    Polynomial(double a0, double a1, double a2);
    Polynomial(vector<double>);

    double getCoefficient(int);
    int getDegree();
    double hornerMethod(double x0);
    Polynomial* Add(Polynomial Other);
    void printPolynomial();
};