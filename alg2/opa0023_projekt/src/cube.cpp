#include "cube.h"

    Cube::Cube() {
        this->a = 0;
        this->b = 0;
        this->c = 0;
    }
  
    Cube::Cube(int a, int b, int c) {
        this->a = a;
        this->b = b;
        this->c = c;
    }

    int Cube::getA() {
        return this->a;
    }

    int Cube::getB() {
        return this->b;
    }

    int Cube::getC() {
        return this->c;
    }

    void Cube::Report() {
        cout << this->getA() << " x " << this->getB() << " x " << this->getC() << "\n";
    }

    bool Cube::CanBePlacedOnTop(Cube other) {
        return ((this->getA() > other.getA() && this->getB() > other.getB()) || (this->getA() > other.getB() && this->getB() > other.getA()));
    }

    bool Cube::IsLargerBase(Cube other) {
        return (this->getA()*this->getB() > other.getA()*other.getB());
    }

    bool Cube::IsSameBase(Cube other) {
        return ((this->getA() == other.getA() && this->getB() == other.getB()) || (this->getB() == other.getA() && this->getA() == other.getB()));
    }

    bool Cube::IsTaller(Cube other) {
        return this->getC() > other.getC();
    }
