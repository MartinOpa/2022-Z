#include "main.h"

int main() {
    int ccsarray[] = { 74, 48, 91, 72, 95, 35, 76, 72, 20, 33, 18, 59, 55, 80, 5, 40, 91, 79, 3, 90, 9, 38, 33, 53, 28, 11, 79, 27, 69, 40 };
    int dcsarray[] = { 14, 15, 12, 16, 14, 13, 11, 12, 14, 11, 14, 13, 11, 11, 16, 17, 18, 14, 18, 16, 11, 17, 12, 14, 15, 15, 17, 12, 14, 16 };
    //testovací data vygenerována zde:
    //https://www.random.org/integers/?num=30&min=1&max=100&col=30&base=10&format=plain&rnd=new

    int ccslength = size(ccsarray);
    int dcslength = size(dcsarray);
    
    int* ccs = comparisonCountingSort(ccsarray, ccslength);
   
    int* dcs = distributionCountingSort(dcsarray, dcslength);

    return 0;
}