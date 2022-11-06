#include "dcs.h"

int findl(int *arr, int length) {
    int l = arr[0];
    for (int i = 1; i < length; i++) {
        if (arr[i] < l) {
            l = arr[i];
        }
    }

    return l;
}

int findu(int *arr, int length) {
    int u = arr[0];
    for (int i = 1; i < length; i++) {
        if (arr[i] > u) {
            u = arr[i];
        }
    }

    return u;
}

int * distributionCountingSort(int A[], int length) {
    int u = findu(A, length);
    int l = findl(A, length);
    int Dlength = u-l+1;

    int D[Dlength];
    int S[length];

    for (int i = 0; i < Dlength; i++) {
        D[i] = 0;
    }

    for (int i = 0; i < length; i++) {
        D[A[i]-l] = D[A[i]-l]+1;
    }

    for (int i = 1; i < Dlength; i++) {
        D[i] = D[i-1]+D[i];
    }

    for (int i = length-1; i >= 0; i--) {
        int j = A[i]-l;
        S[D[j]-1] = A[i];
        D[j] = D[j]-1;
    }

    cout << "\nPřed tříděním:\n";

    for (int i = 0; i < length; i++) {
        if ( i % 10 == 0 && i != 0 ) {
            cout << "\n";
        }
        cout << A[i] << " ";
    }

    cout << "\nDistribution counting sort: \n";

    for (int i = 0; i < length; i++) {
        if ( i % 10 == 0 && i != 0 ) {
            cout << "\n";
        }
        cout << S[i] << " ";
    }

    cout << "\n";

    return S;
}