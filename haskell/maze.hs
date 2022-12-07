import qualified Data.Map as Map

type Result = [String]
type Coord = (Int, Int)

sampleInput1 = ["*********",
                "*s*   *e*",
                "* *   * *",
                "* *   * *",
                "*       *",
                "******* *",
                "        *",
                "*********"]
sampleInput2 = ["*********",
                "*s*   * *",
                "* *   * *",
                "* *   * *",
                "*       *",
                "******* *",
                "e       *",
                "*********"]

createCoordMap :: Result -> Map.Map(Coord) Char
createCoordMap maze = Map.fromList [((x, y), c) | (y, row) <- zip [0..] maze, (x, c) <- zip [0..] row]

findStartEnd :: Map.Map(Coord) Char -> (Coord, Coord)
findStartEnd coordMap =
    (head [coord | coord <- Map.keys coordMap, coordMap Map.!coord == 's'],
    head [coord | coord <- Map.keys coordMap, coordMap Map.!coord == 'e'])

getValidMoves :: Coord -> (Coord -> Char) -> [Coord]
getValidMoves coord charAt = let
    up = (fst coord, snd coord + 1)
    down = (fst coord, snd coord - 1)
    left = (fst coord - 1, snd coord)
    right = (fst coord + 1, snd coord)
    in [x | x <- [up, down, left, right], charAt x /= '*']

getVisitedCoords :: Result -> [Coord]
getVisitedCoords maze = let
    coordMap = createCoordMap maze
    (start, end) = findStartEnd coordMap
    charAt coord = Map.findWithDefault '*' coord coordMap
    validMoves coord = getValidMoves coord charAt
    bfs paths visited
        | null paths = []
        | end `elem` (map fst paths) = reverse (end : (snd (head (filter (\(c, _) -> c == end) paths))))
        | otherwise = bfs [(nextCoord, coord : path) | (coord, path) <- paths,
                        nextCoord <- validMoves coord, not (nextCoord `elem` visited)]
                        (visited ++ (map fst paths))
    in bfs [(start, [])] []

solveMaze :: Result -> Int
solveMaze maze = length ( tail (getVisitedCoords maze))
