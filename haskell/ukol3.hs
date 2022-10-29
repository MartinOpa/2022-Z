import Data.Char
import Data.List
type Crossword = [String]

pp :: Crossword -> IO ()
pp x = putStr (concat (map (++"\n") x))

crossword :: Crossword
crossword = ["...##....",
             ".#....##.",
             ".#.##....",
             "....#.#.#",
             "#.#...#.#",
             "#.#.#....",
             "....##.#.",
             ".##....#.",
             "....##.#."]

find_indexes :: String -> [Int]
find_indexes words = indexes_of_words (group words) 0 []

indexes_of_words :: [String] -> Int -> [Int] -> [Int]
indexes_of_words [] _ indexes = reverse indexes
indexes_of_words words offset indexes | (((words !! 0) !! 0) == '.') && ((length (words !! 0)) >= 2) = indexes_of_words (tail words) (offset + (length (words !! 0))) (offset : indexes)
                                      | otherwise = indexes_of_words (tail words) (offset + (length (words !! 0))) indexes

pair_coordinates :: (Int, [Int]) -> [(Int, Int)]
pair_coordinates (x, list_y) = [(x, y) | x <- [x], y <- list_y]

positions :: Crossword -> [(Int, Int)]
positions crossword = let
    posf = map find_indexes crossword
    zipPosf = zip [0..] posf
    in concat (map pair_coordinates zipPosf)
