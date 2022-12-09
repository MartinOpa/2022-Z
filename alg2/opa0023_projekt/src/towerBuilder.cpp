#include "towerBuilder.h"

/// @brief funkce pro porovnávání kostek
/// @param a kostka a
/// @param b kostka b
/// @return porovnává podstavy kostek, v případě stejné podstavy porovnává jejich výšku
bool largerBase(Cube a, Cube b) {
    if (a.IsSameBase(b)) {
        return a.IsTaller(b);
    }
    return (a.IsLargerBase(b));
}

/// @brief samotná funkce stavějící věž
/// @param cubesInput nesetřízený vektor obsahující kostky s parametry:
/// @param a = strana a
/// @param b = strana b
/// @param c = strana c / výška
/// @return setřízený vektor kostek formující nejvyšší možnou věž
vector< Cube > buildTower(vector< Cube > cubesInput) {
    vector< Cube > tower;

    /// @brief setřízení vektoru kostek cubesInput podle podstavy, syntaxe std::sort() s vlastní podmínkou čerpána zde https://www.geeksforgeeks.org/sort-c-stl/
    /// @param cubesInput 
    /// @return setřízený vektor použitelných kostek
    sort(cubesInput.begin(), cubesInput.end(), largerBase);

    /// @brief položení první kostky ze setřízeného vektoru kostek cubesInput
    /// @param cubesInput setřízený vektor všech kostek
    /// @param tower výsledný vektor
    /// @return základní kostka pro nejvyšší věž
    tower.push_back(cubesInput[0]);

    /// @brief následná iterace přes setřízený vektor
    /// @param cubesInput setřízený vektor všech kostek
    /// @param tower vektor na začátku obsahující největší kostku
    /// @return výsledný vektor tower hledaných kostek pro nejvyšší věž
    for (size_t i = 1; i < cubesInput.size(); i++) {
    if (tower.back().CanBePlacedOnTop(cubesInput[i])) {
            tower.push_back(cubesInput[i]);
        }
    }

    return tower;
}
