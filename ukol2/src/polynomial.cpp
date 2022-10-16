#include "polynomial.h"

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

    Polynomial::Polynomial() {
        this->polynomialVec = {0};
        this->degree = 0;
    }

    Polynomial::Polynomial(double a0) {
        this->polynomialVec = {a0};
        this->degree = 0;
    }

    Polynomial::Polynomial(double a0, double a1) {
        this->polynomialVec = {a0, a1};
        this->degree = 1;
    }

    Polynomial::Polynomial(double a0, double a1, double a2) {
        this->polynomialVec = {a0, a1, a2};
        this->degree = 2;
    }

    Polynomial::Polynomial(vector<double> ax) {
        this->polynomialVec = ax;
        this->degree = ax.size() - 1;
    }

    double Polynomial::getCoefficient(int i) {
        return this->polynomialVec[i-1];
    }

    int Polynomial::getDegree() {
        return this->degree;
    }

    double Polynomial::hornerMethod(double x0) {
        int result = this->polynomialVec[degree];
        for (int i = 0; i < this->degree; i++) {
            result = result * x0 + this->polynomialVec[this->degree-i-1];
        }

        return result;
    }

    Polynomial* Polynomial::Add(Polynomial other) {
        Polynomial *newPoly = new Polynomial();
        newPoly->polynomialVec.pop_back();
        int sVector = this->polynomialVec.size();
        int lVector = other.polynomialVec.size();
        bool swap = false;

        if (other.polynomialVec.size() < this->polynomialVec.size()) {
            sVector = other.polynomialVec.size();
            lVector = this->polynomialVec.size();
            swap = true;
        }

        for (int i = 0; i < sVector; i++) {
            newPoly->polynomialVec.push_back(other.polynomialVec[i] + this->polynomialVec[i]);
        }

        for (int i = sVector; i < lVector; i++) {
            if (swap) {
                newPoly->polynomialVec.push_back(this->polynomialVec[i]);
                continue;
            }
            newPoly->polynomialVec.push_back(other.polynomialVec[i]);
        }

        newPoly->degree = newPoly->polynomialVec.size() - 1;

        return newPoly;
    }

    //Není to úplně pěkné řešení, ale ošetřuje všechny možné vstupy polynomu, které mě napadly,
    //tím zajišťuje správný formát ve výstupu, rychlost by nikdy neměla být problém
    void Polynomial::printPolynomial() {
        if (this->degree == 0) {
            cout << this->polynomialVec[0] << endl;
        }
        if (this->degree == 1) {
            if (this->polynomialVec[1] == 0) {
                cout << "x";
            } else cout << this->polynomialVec[1] << "x";
            if (this->polynomialVec[0] == 0) {
                cout << endl;
            }
            if (this->polynomialVec[0] < 0) {
                cout << " - " << -1*this->polynomialVec[0] << endl;
            }
            if (this->polynomialVec[0] != 0) {
                cout << " + " << this->polynomialVec[0] << endl;
            }
        }
        if (this->degree > 1) {
            cout << this->polynomialVec[this->degree] << "x^" << this->degree;
            for (int i = this->degree-1; i > 1; i--) {
                if (polynomialVec[i] < 0) {
                    if (polynomialVec[i] == -1) {
                    cout << " - x^" << i;
                    } continue;
                    cout << " - " << -1*this->polynomialVec[i] << "x^" << i;
                    continue;
                }
                if (polynomialVec[i] == 1) {
                    cout << " + x^" << i;
                    continue;
                }
                if (polynomialVec[i] != 0) {
                    cout << " + " << this->polynomialVec[i] << "x^" << i;
                    continue;
                }
            }
            if (polynomialVec[1] == 1) {
                cout << " + " << "x";
            }
            if (polynomialVec[1] < 0) {
                cout << " - " << -1*this->polynomialVec[1] << "x";
            }
            if (polynomialVec[1] != 0) {
                cout << " + " << this->polynomialVec[1] << "x";
            }
            if (polynomialVec[0] == 0) {
                cout << endl;
            }
            if (polynomialVec[0] < 0) {
                cout << " + " << -1*this->polynomialVec[0] << endl;
            }
            if (polynomialVec[0] != 0) {
                cout << " + " << this->polynomialVec[0] << endl;
            }
        }
    }
