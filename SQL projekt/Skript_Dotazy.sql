--SKUPINA 1
/*1;1;304;
Výpis všech záznamů tabulky complaint seřazených podle data podání.*/
SELECT *
FROM complaint
ORDER BY complaintdate

/*1;2;1803;
Výpis seznamu všech rezervací seřazených sestupně podle ceny se započtenou slevou.*/
SELECT bill*(1-(discount/100)) AS cena_po_sleve, bill, discount, id, rt_datetime, user_id, vehicle_vin,
       task, taskdescription, apxduration, useremail, payment, paid
FROM reservation
ORDER BY cena_po_sleve DESC

/*1;3;1;
Výpis průměrné ceny všech rezervací před slevou.*/
SELECT AVG(bill) AS prumerna_cena
FROM reservation

/*1;4;1;
Výpis nejnizší ceny ze všech rezervací před slevou.*/
SELECT MIN(bill) AS nejnizsi_cena
FROM reservation

--SKUPINA 2
/*2;1;25;
Výpis rezervací na časy pozdější než v měsíci srpnu, které nestojí více než 3500 a méně než 29000.*/
SELECT id, rt_datetime, bill
FROM reservation
WHERE MONTH(rt_datetime) > 7 AND (bill <= 3500 OR bill >= 29000)

/*2;2;83;
Výpis všech značek a modelů, které nejsou značky Honda, Škoda, Fiat, Nissan nebo BMW*/
SELECT DISTINCT manufacturer, model
FROM vehicle
WHERE manufacturer NOT IN ('Honda', 'Škoda', 'Fiat', 'Nissan', 'BMW')
ORDER BY manufacturer


/*2;3;8;
Výpis příjmení začínajících na 'N' a končících na 'ová'.*/
SELECT DISTINCT lastName
FROM "user"
WHERE lastName LIKE 'N%ová'
ORDER BY lastName

/*2;4;178;
Výpis rezervací na časy pozdější než v měsíci srpnu, které nebyly 8:30, 10:30, 12:30 nebo 14:30.*/
SELECT id, cast(rt_datetime AS TIME) AS cas_rezervace,rt_datetime, bill
FROM reservation
WHERE MONTH(rt_datetime) > 7 AND cast(rt_datetime AS TIME) NOT IN ('8:30', '10:30', '12:30', '14:30')

--SKUPINA 3
/*3;1;38;Výpis všech uživatelů, kteří si nikdy nevytvořili rezervaci*/
SELECT id
FROM "user"
EXCEPT (SELECT user_id FROM reservation)

/*3;2;38;Výpis všech uživatelů, kteří si nikdy nevytvořili rezervaci*/
SELECT id
FROM "user" u
WHERE NOT EXISTS (SELECT user_id FROM reservation r WHERE r.user_id = u.id)

/*3;3;38;Výpis všech uživatelů, kteří si nikdy nevytvořili rezervaci*/
SELECT id
FROM "user" u
WHERE id != ALL (SELECT user_id FROM reservation r)

/*3;4;38;Výpis všech uživatelů, kteří si nikdy nevytvořili rezervaci*/
SELECT id
FROM "user" u
WHERE id NOT IN (SELECT user_id FROM reservation r)

--SKUPINA 4
/*4;1;648;
Výpis počtu klientů na jednotlivé časy rezervace*/
SELECT count(user_id) AS pocet_klientu, rt_datetime
FROM reservation
GROUP BY rt_datetime
ORDER BY rt_datetime

/*4;2;1001;
Výpis jména a příjmení uživatelů a počet vozidel, které zaregistrovali do systému.*/
SELECT u.firstName, u.lastName, COUNT(*) AS pocet_vozidel
FROM "user" u
JOIN vehicle v ON v.user_id = u.id
GROUP BY v.user_id, u.firstname, u.lastname

/*4;3;711;
Výpis všech časů rezervací a procentuální podíl počtu vytvořených rezervací vůči maximální stanovené kapacitě (4).
Výpis je seřazen podle tohoto podílu vzestupně.*/
SELECT rt.datetime, ROUND((COUNT(r.rt_datetime) / CAST(4 AS FLOAT)),2)*100 AS obsazenost_terminu
FROM reservationtimes rt
LEFT JOIN reservation r ON rt.datetime = r.rt_datetime
GROUP BY rt.datetime, rt.capacity
ORDER BY obsazenost_terminu ASC

/*4;4;670;
Výpis id, jména a příjmení uživatelů a celkový počet vozidel, které zaregistrovali do systému, pokud zaregistrovali více než jedno vozidlo.
Výpis je seřazen podle tohoto počtu sestupně.*/
SELECT u.id, u.firstname, u.lastname, COUNT(v.vin) AS pocet_vozidel
FROM "user" u
JOIN vehicle v ON u.id = v.user_id
GROUP BY u.id, u.firstname, u.lastname
HAVING COUNT(v.vin) > 1
ORDER BY COUNT(v.vin) DESC

--SKUPINA 5
/*5;1;648;
Výpis všech rezervačních termínů, kdy se rezervoval alespoň 1 uživatel.*/
SELECT DISTINCT rt.datetime
FROM reservationtimes rt
JOIN reservation r ON rt.datetime = r.rt_datetime

/*5;2;648;
Výpis všech rezervačních termínů, kdy se rezervoval alespoň 1 uživatel.*/
SELECT rt.datetime
FROM reservationtimes rt
WHERE rt.datetime IN (SELECT r.rt_datetime FROM reservation r)

/*
5;3;711;
Výpis všech termínů rezervace a počtu rezervací na konkrétní termín.
Výpis bude obsahovat i termíny, na které se nikdo nerezervoval.*/
SELECT rt.datetime, COUNT(r.rt_datetime) AS pocet_rezervaci
FROM reservationtimes rt
LEFT JOIN reservation r ON rt.datetime = r.rt_datetime
GROUP BY rt.datetime, rt.capacity

/*5;4;56;
Výpis uživatelů a jejich počet vozidel s rokem výroby mladším než 2014
U tohoto dotazu jsem měl AND v.year > 2014 aby byli zobrazeni všichni uživatelé, ale dbedu mi dotaz označilo jako chybné kvůli chybějícího WHERE*/
SELECT u.id, COUNT(v.vin) AS pocet_vozidel
FROM "user" u
LEFT JOIN vehicle v ON v.user_id = u.id
WHERE v.year > 2014
GROUP BY u.id
ORDER BY pocet_vozidel DESC

--SKUPINA 6
/*6;1;4;
Výpis jmen a příjmení uživatelů, počtu jejich rezervací a celkovou částkou za všechny rezervace uživatelů,
kteří nikdy neplatili více než 8000, ale měli alespoň 3 rezervace.*/
SELECT u.firstname, u.lastname, count(r.id) AS pocet_rezervaci, sum(r.bill) AS castka_celkem
FROM "user" u
JOIN reservation r ON u.id = r.user_id
WHERE NOT EXISTS (
    SELECT 1 FROM reservation r2
    WHERE r2.user_id = u.id
    AND r2.bill > 8000
    GROUP BY r2.user_id
)
GROUP BY u.firstname, u.lastname
HAVING COUNT(DISTINCT r.id) > 2

/*6;2;415;
Výpis jmen, příjmení a celkové částky za rezervace pro uživatele, kteří mají větši průměrnou útratu než jiní*/
SELECT u.firstname, u.lastname, sum(bill) AS celkova_castka
FROM "user" u
JOIN reservation r ON u.id = r.user_id
GROUP BY u.firstname, u.lastname
HAVING SUM(bill) > ALL (
    SELECT AVG(t.celkova_castka) prumer
    FROM (
        SELECT sum(bill) celkova_castka
        FROM reservation r2
        GROUP BY r2.user_id
         ) t
    )
ORDER BY SUM(bill) DESC
