    /* 
    Myslel jsem, že by bylo časově efektivnější rovnou mazat v původním vektoru, avšak není to pravda.

    for (int i = 1; i < words.size(); i++) {
        if (words[i] == words[i-1]) {
            words.erase(words.begin() + i);
        }

    Zároveň jsem si dovolil přidat std::chrono pro měření času
    }*/

#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <algorithm>
#include <chrono>

using namespace std;

int main() {
    string word;
    vector<string> words;
    ifstream file;
    ofstream output;
    vector<string> U;

    file.open("WordsLowercase.txt");

    copy(istream_iterator<string>(file), istream_iterator<string>(), back_inserter(words));

    file.close();

    chrono::steady_clock::time_point begin = chrono::steady_clock::now();

    sort(words.begin(), words.end());

    U.push_back(words[0]);
    for (int i = 1; i < words.size(); i++) {
        if (words[i] != words[i-1]) {
            U.push_back(words[i]);
        }
    }

    chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

    output.open("Result2.txt");

    copy(U.begin(),U.end(),ostream_iterator<string>(output,"\n"));

    output.close();

    cout << "Počet slov před odstraněním duplikátů: " << words.size() << "\nPočet slov po odstranění duplikátů: " << U.size()
<< "\nDoba trvání algoritmu: " << chrono::duration_cast<chrono::milliseconds> (end - begin).count() << "[ms]" << endl;

    return 0;
}