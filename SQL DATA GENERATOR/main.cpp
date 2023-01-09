#include <iostream>
#include <fstream>
#include <vector>
#include <random>

using namespace std;

//randint
int ri(int min, int max) {
    return 0 + (min + rand() % static_cast<int>(max - min + 1));
}
//randchassis
string rc() {
    string a(1, 'A' + rand()%26);
    string b(1, 'A' + rand()%26);
    string c = a + b + to_string(ri(0,9));
    return c;
}

int main() {
    fstream file;
    string line;

    int currentvin = 1;
    int changes = 0;
    int day = 13;
    int month = 5;
    int year = 2022;
    int hour = 7;
    bool reviewposted;
    int revcomday;
    int revcommonth;
    int revcomyear;
    int resmade = 0;
    string revcomdate;

    vector<string> logins;
    vector<string> malenames;
    vector<string> malelastnames;
    vector<string> femalenames;
    vector<string> femalelastnames;
    vector<string> addresses;
    vector<string> towns;
    vector<string> plateletters;
    vector<string> brands;
    vector<string> models;
    vector<string> vins;
    vector<string> restexts;
    vector<string> posrevtexts;
    vector<string> negrevtexts;
    vector<string> posreptexts;
    vector<string> compltexts;
    vector<string> task;
    string negreakce = "Vaše zkušenost nás velice mrzí, využijte, prosím, reklamační formulář a my Vás kontaktujeme";
    
    //loginy
    file.open("logins.txt",ios::in);
    while(getline(file, line)) {
        string a;

        file >> a;

        logins.push_back(a);
    }
    file.close();
    //jména + příjmení
    file.open("malenames.txt",ios::in);
    while(getline(file, line)) {
        string a;
        string b;

        file >> a >> b;

        malenames.push_back(a);
        malelastnames.push_back(b);
    }
    file.close();

    //jména + příjmení
    file.open("femalenames.txt",ios::in);
    while(getline(file, line)) {
        string a;
        string b;

        file >> a >> b;

        femalenames.push_back(a);
        femalelastnames.push_back(b);
    }
    file.close();

    //adresy
    file.open("address.txt",ios::in);
    while(getline(file, line)) {
        addresses.push_back(line);
    }
    file.close();

    //města
    file.open("town.txt",ios::in);
    while(getline(file, line)) {
        towns.push_back(line);
    }
    file.close();

    //vin
    file.open("vins.txt",ios::in);
    while(getline(file, line)) {
        string a;

        file >> a;

        vins.push_back(a);
    }
    file.close();

    //značka + model
    file.open("cars.txt",ios::in);
    while(getline(file, line)) {
        string a;
        string b;

        file >> a >> b;

        brands.push_back(a);
        models.push_back(b);
    }
    file.close();

    //spz
    file.open("plates.txt",ios::in);
    while(getline(file, line)) {
        string a;

        file >> a;

        plateletters.push_back(a);
    }
    file.close();

    //pozitivní recenze
    file.open("positivereview.txt",ios::in);
    while(getline(file, line)) {
        posrevtexts.push_back(line);
    }
    file.close();

    //negativní recenze
    file.open("negativereview.txt",ios::in);
    while(getline(file, line)) {
        negrevtexts.push_back(line);
    }
    file.close();

    //pozitivní reakce
    file.open("posrep.txt",ios::in);
    while(getline(file, line)) {
        posreptexts.push_back(line);
    }
    file.close();

    //stížnosti
    file.open("complaints.txt",ios::in);
    while(getline(file, line)) {
        compltexts.push_back(line);
    }
    file.close();

    //popis rezervace
    file.open("task.txt",ios::in);
    while(getline(file, line)) {
        task.push_back(line);
    }
    file.close();

    file.open("data.txt",ios::out);

    file << "SET DATEFORMAT DMY\n";
    //tabulka user pro vedoucího
    file << "INSERT INTO [user] VALUES (0, 'opa0023', 'Martin', 'Opálka', 735058601, 'Adresní 9382/84, Český Těšín, 73701', 'martin.op@servis.com', 0, '4.1.1999')\n";
    file << "INSERT INTO [vehicle] VALUES ('SHHEP23903U101257', 0, '8T28436', 'Honda', 'Civic', 2003, 'EP2')\n";
    //tabulka user a vehicle pro zaměstnance
    for (int i = 1; i < 12; i++) {
        string newline = "\n";
        string startuser = "INSERT INTO [user] VALUES (";
        string end = ")";
        string comma = ", ";
        string tab = "  ";
        string domain = "@servis.com";
        string apostrophe = "'";
        string login = logins[ri(1, logins.size()-1)];
        int randchoice = ri(0,10);
        string firstName, lastName;
        firstName = randchoice % 2 == 0 ? malenames[ri(1, malenames.size()-1)] : femalenames[ri(1, femalenames.size()-1)];
        lastName = randchoice % 2 == 0 ? malelastnames[ri(1, malelastnames.size()-1)] : femalelastnames[ri(1, femalelastnames.size()-1)];
        string phonenum = to_string(ri(700000000, 799999999));
        string address = addresses[ri(1, addresses.size()-1)] + " " + to_string(ri(1000, 1900)) + "/" + to_string(ri(1,60));
        address = "N" + apostrophe + address + ", " + towns[ri(1, towns.size()-1)] + apostrophe;
        string email = "N" + apostrophe + firstName + "." + lastName + domain + apostrophe;
        string partnerdate = apostrophe + to_string(ri(1, 28)) + "." + to_string(ri(1, 12)) + "." + to_string(ri(1995, 2000)) + apostrophe;
        file << newline + startuser << i << comma << apostrophe + login + apostrophe + comma << "N" + apostrophe + firstName + apostrophe + comma;
        file << "N" + apostrophe + lastName + apostrophe + comma << phonenum + comma << address + comma;
        file << email + comma << 1 << comma << partnerdate << end + newline;

        string startvehicle = "INSERT INTO [vehicle] VALUES (";
        string null = "null";
        string vin = apostrophe + vins[currentvin] + apostrophe;
        currentvin += 1;
        string licenseplate = apostrophe + to_string(ri(0,9)) + plateletters[ri(1, plateletters.size()-1)] + to_string(ri(10000, 99999)) + apostrophe;
        int randcar = ri(0, brands.size()-1);
        string manufacturer = "N" + apostrophe + brands[randcar] + apostrophe;
        string model = apostrophe + models[randcar] + apostrophe;
        int vehyear = ri(1972, 2015);
        string chassis = i % 3 == 0 ? null : apostrophe + rc() + apostrophe;
        file << newline + startvehicle << vin + comma << i << comma << licenseplate + comma;
        file << manufacturer + comma << model + comma << vehyear << comma << chassis + end + newline;
    }

    for (int i = 12; i < 1001; i++) {//1601
    //tabulka user
    string restime;
    restime = to_string(day) + "." + to_string(month) + "." + to_string(year) + " " + to_string(hour) + ":30";
        string newline = "\n";
        string startuser = "INSERT INTO [user] VALUES (";
        string end = ")";
        string comma = ", ";
        string tab = "  ";
        string domain = "@gmail.com";
        string apostrophe = "'";
        string login = logins[ri(1, logins.size()-1)];
        int randchoice = ri(0,10);
        string firstName, lastName;
        firstName = randchoice % 2 == 0 ? malenames[ri(1, malenames.size()-1)] : femalenames[ri(1, femalenames.size()-1)];
        lastName = randchoice % 2 == 0 ? malelastnames[ri(1, malelastnames.size()-1)] : femalelastnames[ri(1, femalelastnames.size()-1)];
        string phonenum = to_string(ri(700000000, 799999999));
        string address = addresses[ri(1, addresses.size()-1)] + " " + to_string(ri(1000, 1900)) + "/" + to_string(ri(1,60));
        address = "N" + apostrophe + address + ", " + towns[ri(1, towns.size()-1)] + apostrophe;
        string email = "N" + apostrophe + firstName + "." + lastName + domain + apostrophe;
        int acctypeint;
        ri(0,19) % 5 == 0 ? acctypeint = 2 : acctypeint = 3;
        string acctype = to_string(acctypeint);
        string partnerdate;
        int lucky = ri(0,2);
        acctypeint == 2 ? partnerdate = apostrophe + to_string(day) + "." + to_string(month-4) + "." + to_string(year-lucky) + apostrophe : partnerdate = "null";
        file << newline + startuser << i << comma << apostrophe + login + apostrophe + comma << "N" + apostrophe + firstName + apostrophe + comma;
        file << "N" + apostrophe + lastName + apostrophe + comma << phonenum + comma << address + comma;
        file << email + comma << acctype + comma << partnerdate << end + newline;

    //tabulka vehicle
        int randomvehiclecount = ri(1, 3);
        for (int j = 0; j < randomvehiclecount; j++) {
        //tabulka reservationtimes
        reviewposted = false;
        if (resmade % 4 == 0 || ri(1,7) % 4 == 0) {//changes, day, month, year, hour
            changes += 1;
            hour += 1;
            if (changes % 8 == 0) {
                hour = 8;
                day += 1;
                if (day == 29) {
                    day = 1;
                    month += 1;
                    if (month == 13) {
                        month = 1;
                        year += 1;
                    }
                }
            }
        string startreservationtimes = "INSERT INTO [reservationtimes] VALUES (";
        restime = to_string(day) + "." + to_string(month) + "." + to_string(year) + " " + to_string(hour) + ":30";
        string reservationtimes = apostrophe + restime + apostrophe;
        int capacity = 4;

        file << newline << startreservationtimes << reservationtimes + comma << capacity << end + newline;
        }
        string startvehicle = "INSERT INTO [vehicle] VALUES (";
        string null = "null";
        string vin = apostrophe + vins[currentvin] + apostrophe;
        currentvin += 1;
        string licenseplate = apostrophe + to_string(ri(0,9)) + plateletters[ri(1, plateletters.size()-1)] + to_string(ri(10000, 99999)) + apostrophe;
        int randcar = ri(0, brands.size()-1);
        string manufacturer = "N" + apostrophe + brands[randcar] + apostrophe;
        string model = apostrophe + models[randcar] + apostrophe;
        int vehyear = ri(1972, 2015);
        string chassis = i % 3 == 0 ? null : apostrophe + rc() + apostrophe;
        file << newline + startvehicle << vin + comma << i << comma << licenseplate + comma;
        file << manufacturer + comma << model + comma << vehyear << comma << chassis + end + newline;
        
        //tabulka reservation
        if (ri(0,9) % 10 != 0) {
        string startreservationif = "IF ((select capacity from reservationtimes where datetime = '";
        string startreservationdate = restime;
        string startreservationendif = "') > 0)\nBEGIN\n    INSERT INTO [reservation] VALUES (";
        string startreservationupdate = ")\n    UPDATE [reservationtimes]\n    SET capacity = capacity-1\n    WHERE datetime = '";
        string startreservationendupdate = "'\nEND";
        string startreservationtext = "N" + apostrophe + task[ri(1, task.size()-1)] + apostrophe;
        string startreservationextra;
        ri(0,9) % 10 == 0 ? startreservationextra = "N" + apostrophe + task[ri(1, task.size()-1)] + apostrophe : startreservationextra = null;
        float discount;
        acctypeint == 2 ? discount = 0.5 + (2*lucky) : discount = 0;
        int price = ri(2500, 30000);
        int paymenttype = ri(0,3);
        int paid;
        ri(0,25000) % 5000 == 0 ? paid = 0 : paid = 1;
        file << newline + startreservationif << startreservationdate << startreservationendif << resmade << comma << apostrophe + startreservationdate;
        file << apostrophe + comma << i << comma << vin + comma << startreservationtext + comma << null + comma << null + comma;
        file << email + comma << discount << comma << price << comma << paymenttype << comma << paid;
        file << startreservationupdate << startreservationdate << startreservationendupdate + newline;
        resmade += 1;

        //tabulka review
        int reviewscore = ri(0,10);
        if (ri(0,2) % 2 == 0) {
            reviewposted = true;
            string startreview = "INSERT INTO [review] VALUES (";
            if (day > 15) {
                revcomday = day-ri(1, 14);
                if (month == 12) {
                    revcommonth = 1;
                    revcomyear = year+1;
                } else {
                    revcommonth = month;
                    revcomyear = year;
                }
            }
            if (day <= 15) {
                revcomday = day+ri(2, 7);
                revcommonth = month;
                revcomyear = year;
            }
            revcomdate = apostrophe + to_string(revcomday) + "." + to_string(revcommonth) + "." + to_string(revcomyear) + " " + to_string(ri(8, 14)) + ":30" + apostrophe;
            string reviewtext;
            reviewscore > 4 ? reviewtext = posrevtexts[ri(1, posrevtexts.size()-1)] : reviewtext = negrevtexts[ri(1, negrevtexts.size()-1)];
            file << newline + startreview << resmade-1 << comma << i << comma + "N" + apostrophe + firstName + apostrophe + comma << revcomdate + comma << null + comma;
            file << reviewscore << comma + "N" + apostrophe + reviewtext + apostrophe + end + newline;
        }
        
        //tabulka reply
        if (reviewposted == true && ri(0,2) % 2 == 0) {
            string startreply = "INSERT INTO [reply] VALUES (";
            string replytext;
            reviewscore > 4 ? replytext = posreptexts[ri(1, posreptexts.size()-1)] : replytext = negreakce;
            string randomreplydate = apostrophe + to_string(revcomday+1) + "." + to_string(revcommonth) + "." + to_string(revcomyear) + " " + to_string(ri(8, 14)) + ":30" + apostrophe;
            file << newline + startreply << "N" + apostrophe + replytext + apostrophe + comma << randomreplydate + comma << resmade-1 << end + newline;
        }     
        //tabulka complaint
        string startcomplaint = "INSERT INTO [complaint] VALUES (";
        string complainttext;
        if (reviewscore < 3 && ri(0,2) % 2 == 0) {
            complainttext = apostrophe + compltexts[ri(1, compltexts.size()-1)] + apostrophe;
            string randomcomplaintdate = apostrophe + to_string(revcomday) + "." + to_string(revcommonth) + "." + to_string(revcomyear) + " " + to_string(ri(8, 14)) + ":30" + apostrophe;
            int resolvedcomplaint;
            ri(0,2) % 2 == 0 ? resolvedcomplaint = 0 : resolvedcomplaint = 1;
            file << newline + startcomplaint << resmade-1 << comma + "N" + complainttext + comma << resolvedcomplaint;
            file << comma + randomcomplaintdate + end + newline;
        }
        }
    }

        i % 100 == 0 ? cout << i << "\n" : cout << "";
}

file.close();

}
