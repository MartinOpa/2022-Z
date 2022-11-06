#include "ccs.h"

int * comparisonCountingSort(int arr[], int length) {
    int count[length];
    int S[length];
    for (int i = 0; i < length; i++) {
        count[i] = 0;
    }

    for (int i = 0; i < length-1; i++) {
        for (int j = i+1; j < length; j++) {
            if (arr[i] < arr[j]) {
                count[j] = count[j]+1;
            } else count[i] = count[i]+1;
        }
    }

    for (int i = 0; i < length; i++) {
        S[count[i]] = arr[i];
    }

    cout << "\nPřed tříděním:\n";

    for (int i = 0; i < length; i++) {
        if ( i % 10 == 0 && i != 0 ) {
            cout << "\n";
        }
        cout << arr[i] << " ";
    }

    cout << "\nComparison counting sort: \n";

    for (int i = 0; i < length; i++) {
        if ( i % 10 == 0 && i != 0 ) {
            cout << "\n";
        }
        cout << S[i] << " ";
    }

    cout << "\n";

    return S;
}