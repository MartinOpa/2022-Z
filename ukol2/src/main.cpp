#include "main.h"

int main() {
    int koeficient = 3; //výběr koeficientu pro getCoefficient(int)
    vector<double> A = {4, 5, -1, 7, 0, 3};
    Polynomial *P = new Polynomial(A);
    
    cout << "zobrazení polynomu na standardní výstup: ";
    P->printPolynomial();

    cout << "metoda pro přístup k jednotlivým koeficientům (koeficient " << koeficient << "): ";
    cout << P->getCoefficient(koeficient) << endl;

    cout << "metoda pro určení stupně polynomu: ";
    cout << P->getDegree() << endl;

    cout << "metoda pro výpočet hodnoty polynomu v bodě x0: ";
    cout << P->hornerMethod(5) << endl;

    vector<double> B = {-3, 1, 0, -1, 0, 0, 4};
    Polynomial *Q = new Polynomial(B);

    Polynomial *R = new Polynomial();
    R = P->Add(*Q);

    cout << "metoda pro součet dvou polynomů: ";
    R->printPolynomial();

    return 0;
}