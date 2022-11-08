import Data.List
import Data.Ord
type Result = [String]

pp :: Result -> IO ()
pp x = putStr (concat (map (++"\n") x))

sampleInput = [('u',5),('r',5),('d',5),('l',10),('d',5),('r',5),('u',5)]
-- [('u',5),('r',5),('d',5),('l',10),('d',5),('r',5),('u',5)]

result :: Result
result = []

draw :: [(Char, Integer)] -> Result
draw [] = []
draw input = markTiles (createGrid input) input

markTiles :: Result -> [(Char, Integer)] -> Result
markTiles grid coordinates = let
    coordinates' = tail (mapCoords coordinates [(0,0)])
    get x | elem x coordinates' = 'X'
          | otherwise = ' '
    a' = fst (minimumBy (comparing fst) (getGrid coordinates))
    b' = snd (minimumBy (comparing snd) (getGrid coordinates))
    in reverse [ [ get (ri, ci) | (ci, ch)<- zip [b',b'+1..] row ] | (ri, row)<- zip [a', a'+1..] grid ]

createGrid :: [(Char, Integer)] -> Result
createGrid input = let
        a = abs(fst (minimumBy (comparing fst) (getGrid input))) + abs(fst (maximumBy (comparing fst) (getGrid input))) + 1
        b = abs(snd (minimumBy (comparing snd) (getGrid input))) + abs(snd (maximumBy (comparing snd) (getGrid input))) + 1    
    in replicate (fromInteger a) (replicate (fromInteger b) ' ')

getGrid :: [(Char, Integer)] -> [(Integer, Integer)]
getGrid input = let
    result = tail (mapCoords input [(0,0)])
    in (minimumBy (comparing fst) result) : (minimumBy (comparing snd) result) : (maximumBy (comparing fst) result) : (maximumBy (comparing snd) result) : []

mapCoords :: [(Char, Integer)] -> [(Integer, Integer)] -> [(Integer, Integer)]
mapCoords [] result = result
mapCoords ((a, b) : abs ) result = let
    result' = move (a, b) result
    in mapCoords abs result'

move :: (Char, Integer) -> [(Integer, Integer)] -> [(Integer, Integer)]
move (_, 0) result = result
move instruction result = let
  step = (do_step (last result) (fst instruction))
  in (move ((fst instruction), (snd instruction) - 1) (result ++ [step]))

do_step :: (Integer, Integer) -> Char -> (Integer, Integer)
do_step coordinate direction
    | direction == 'u' = (((fst coordinate) + 1), (snd coordinate))
    | direction == 'r' = ((fst coordinate), ((snd coordinate) + 1))
    | direction == 'd' = (((fst coordinate) - 1), (snd coordinate))
    | direction == 'l' = ((fst coordinate), ((snd coordinate) - 1))


{-
[
"     XXXXXX",
"     X    X",
"     X    X",
"     X    X",
"     X    X",
"XXXXXXXXXXX",
"X    X",
"X    X",
"X    X",
"X    X",
"XXXXXX"
]  
-}