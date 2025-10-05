-- Definir la función maxiguala
maxiguala :: (Eq a) => [a] -> a -> Int
maxiguala xs y = maxigualaAux xs y 0 0

-- Función auxiliar recursiva con acumuladores
maxigualaAux :: (Eq a) => [a] -> a -> Int -> Int -> Int
maxigualaAux [] _ maxActual maxTotal = max maxActual maxTotal
maxigualaAux (x:xs) y maxActual maxTotal
    | x == y    = maxigualaAux xs y (maxActual + 1) maxTotal
    | otherwise = maxigualaAux xs y 0 (max maxActual maxTotal)

-- Definir la función cantla
cantla :: String -> Int
cantla [] = 0  -- Caso base: lista vacía
cantla [_] = 0  -- Caso base: lista con un solo carácter
cantla ('l':'a':resto) = 1 + cantla resto  -- Si encontramos "la", contar y seguir
cantla (_:resto) = cantla resto  -- Si no encontramos "la", seguir con el resto

-- Definir la función maxigual
maxigual :: (Eq a) => [a] -> Int
maxigual [] = 0
maxigual (x:xs) = maxigualAux xs x 1 1

-- Función auxiliar recursiva con acumuladores
maxigualAux :: (Eq a) => [a] -> a -> Int -> Int -> Int
maxigualAux [] _ maxActual maxTotal = max maxActual maxTotal
maxigualAux (x:xs) prev maxActual maxTotal
    | x == prev = maxigualAux xs x (maxActual + 1) maxTotal
    | otherwise = maxigualAux xs x 1 (max maxActual maxTotal)

-------------------------------------------------------------
import Data.List (group)

-- Definir la función maxigual
maxigual :: (Eq a) => [a] -> Int
maxigual xs = maximum $ map (uncurry maxLength) (split3 xs)

-- Función auxiliar que calcula la longitud máxima de una sublista de elementos iguales
maxLength :: (Eq a) => [a] -> [a] -> Int
maxLength [] ys = 0
maxLength xs [] = 0
maxLength xs ys = max (maxSublistLength xs) (maxSublistLength ys)

-- Función que calcula la longitud de la sublista más larga de elementos iguales
maxSublistLength :: (Eq a) => [a] -> Int
maxSublistLength = maximum . map length . group

-- Definir la función split2
split2 :: [a] -> [([a], [a])]
split2 xs = [(take i xs, drop i xs) | i <- [0..length xs]]

-- Definir la función split3
split3 :: [a] -> [([a], [a], [a])]
split3 xs = [(ys, zs1, zs2) | (ys, zs) <- split2 xs, (zs1, zs2) <- split2 zs]

