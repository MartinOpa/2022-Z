#pragma once
#include <vector>
#include <iostream>

using namespace std;

class Cube
{
private:
    int a;
    int b;
    int c;
public:
    Cube();
    Cube(int a, int b, int c); ///základní konstruktor kostky s parametry a, b, c - stran kostky

    void Report();///napsáno panem docentem Dvorským jakožto návrh printující funkce
    bool CanBePlacedOnTop(Cube other);///vrací true pokud se kostka other dá položit na kostku this
    bool IsLargerBase(Cube other);///vrací true pokud je podstava kostky other menší než kostky this
    bool IsSameBase(Cube other);///vrací true pokud jsou podstavy stejné
    bool IsTaller(Cube other);///vrací true pokud je výška kostky other menší než kostky this

    int getA();///jednoduchý getter pro atribut a
    int getB();///jednoduchý getter pro atribut b
    int getC();///jednoduchý getter pro atribut c
};
