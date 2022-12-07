#include "fileOpener.h"
/// @brief otevře soubor jména fileName
/// @param fileName jméno souboru pro fstream
/// @return načtený nesetřízený vektor všech kostek
vector< Cube > getInput(string fileName) {
    vector< Cube > cubesInput;
    fstream file;
    file.open(fileName,ios::in);

    string line;

    while(getline(file, line)) {
        int a;
        int b;
        int c;

        file >> a >> b >> c;

        cubesInput.push_back(Cube(a,b,c));
        cubesInput.push_back(Cube(b,c,a));
        cubesInput.push_back(Cube(a,c,b));
    }

    /// @brief odstraňuje duplicity na konci vektoru vzniklé nedostatkem while(getline(file,line)) který čte i poslední prázdný řádek
    /// @param cubesInput 
    cubesInput.pop_back();
    cubesInput.pop_back();
    cubesInput.pop_back();

    file.close();

    return cubesInput;
}


