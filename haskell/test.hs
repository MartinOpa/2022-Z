-- odds "ABCDEF"
odds :: String -> String
odds [] = []
odds [y] = []
odds [x, y] = [y]
odds (_:y:ys) = y : odds ys

-- check "((()))()"
-- check "(()"
check :: String -> Bool
check x = if (check' x == 0) then True else False

check' :: String -> Int
check' [] = 0
check' x = if head x == '(' then 1 + check' (tail x) else -1 + check' (tail x)

-- accounts [("A", 100), ("B", -1), ("C", 10)]
accounts :: [(String, Int)] -> [String]
accounts [] = []
accounts ((x, y) : xys) | y > 0 = x : accounts xys
    | otherwise = accounts xys


