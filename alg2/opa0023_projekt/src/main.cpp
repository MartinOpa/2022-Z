#include "main.h"

int main(int argc, char *argv[]) {
    /// @brief vstupní parametry funkce main
    /// @param argv vstupní argument při spuštění
    /// @return vybere soubor SetOfBlocks<argv>
    string number = argv[1];

    /// @brief vektory pro ukládání kostek
    /// @param tower výsledný vektor
    /// @param cubes načtený, nesetřízený vektor
    vector< Cube > tower;
    vector< Cube > cubes;

    /// @param name jméno souboru pro fileOpener
    /// @param height výsledná výška věže
    string name = "SetOfBlocks" + number + ".txt";
    cubes = getInput(name);
    tower = buildTower(cubes);
    int height = 0;

    /// @param tower 
    /// @return sečtení výšky věže a výtisk této hodnoty
    for (int i = 0; i < tower.size(); i++) {
        height += tower[i].getC();
    }

    cout << "Celková výška věže: " << height << "\n";

    /// @param tower 
    /// @return výtisk výsledné věže na standardní výstup
    cout << "Výsledná věž:\n";
    for (int i = 0; i < tower.size(); i++) {
        tower[i].Report();
    }

    return 0;
}
