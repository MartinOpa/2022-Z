#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <chrono>

using namespace std;

int main() {
    string word;
    vector<string> words;
    ifstream file;
    ofstream output;
    vector<string> U;
    bool unique = false;

    file.open("WordsLowercase.txt");

    copy(istream_iterator<string>(file), istream_iterator<string>(), back_inserter(words));

    file.close();

    chrono::steady_clock::time_point begin = chrono::steady_clock::now();

    U.push_back(words[0]);
    for (int i = 1; i < words.size(); i++) {
        for (int j = 0; j < U.size(); j++) {
            if (words[i] == U[j]) {
                unique = false;
                break;
            } else unique = true;
        }
        if (unique) {
            U.push_back(words[i]);
        }
    }

    chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

    output.open("Result.txt");

    copy(U.begin(),U.end(),ostream_iterator<string>(output,"\n"));

    output.close();

    cout << "Počet slov před odstraněním duplikátů: " << words.size() << "\nPočet slov po odstranění duplikátů: " << U.size()
 << "\nDoba trvání algoritmu: " << chrono::duration_cast<chrono::milliseconds> (end - begin).count() << "[ms]" << endl;

    return 0;
}