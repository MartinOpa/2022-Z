--1
data Company = Company {
    name' :: String,
    employees :: Int,
    ownerOf :: [Company]
} deriving (Show)

testCompany :: Company
testCompany = Company "Apple" 30 []

testCompany2 :: Company
testCompany2 = Company "Microsoft" 42 []

testCompany3 :: Company
testCompany3 = Company "Lego" 100 [testCompany2, testCompany]

data Component = TextBox {name :: String, text :: String}
                | Button {name :: String, value :: String}
                | Container {name :: String, children :: [Component]} deriving (Show)

gui :: Component
gui = Container "My App" [
    Container "Menu" [
        Button "btn_new" "New",
        Button "btn_open" "Open",
        Button "btn_close" "Close"],
        Container "Body" [TextBox "textbox_1" "Some text goes here"],
        Container "Footer"[]]

--2
--countAllComponents gui = 8
countAllComponents :: Component -> Int
countAllComponents components = length (showAllComponents components)

showAllComponents :: Component -> [String]
showAllComponents (Button n _) = [n]
showAllComponents (TextBox n _) = [n]
showAllComponents (Container x n) = x : concatMap showAllComponents n

--3
removeEmptyComponents' :: Component -> [Component]
removeEmptyComponents' (Container x n) = concatMap removeEmptyComponents' n
removeEmptyComponents' (Button n t) = if not (null n) && not (null t) then [Button n t] else []
removeEmptyComponents' (TextBox n t) = if not (null n) && not (null t) then [TextBox n t] else []


--removeEmptyComponents :: Component -> Component
--removeEmptyComponents x = concat (removeEmptyComponents' x)
