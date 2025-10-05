--                Practica 3
--ejercicio 1

unir :: [Int] -> [Int] -> [Int]
unir [] ys = ys
unir xs [] = xs
unir (x:xs) (y:ys)
    | x <= y    = x : unir xs (y:ys)
    | otherwise = y : unir (x:xs) ys

--ejercicio 2
--Obtenido del teorico 
qsort :: Ord a => [a] -> [a]
qsort [] = []
qsort (x:xs) = qsort left ++ [x] ++ qsort right
             where
             left = [a | a <- xs, a <= x ]
             right = [b | b <- xs, b > x ]
             
--ejercicio 3
dosAl :: Int -> Int
dosAl 0 = 1
dosAl n = 2 * dosAl (n-1) 

--ejercicio 4
binario :: Int -> [Int]
binario 0 = [0]
binario 1 = [1]
binario n = binario (div n 2) ++ [mod n 2]

--ejercicio 5*
biPar :: [Int] -> Bool
biPar xs = last xs == 0
--ejercicio 6
--ejercicio 7
{-Define la función que, dado un número natural, decida si el mismo es un
cuadrado perfecto o no.-}
esPerfecto :: Int -> Bool
esPerfecto n = [x | x <- [0..n], x * x ==n] /= []

--ejercicio 8 (replicate)
{-Define la función repetidos de forma tal que dado un elemento z y un entero
n; z aparece n veces.-}
repetidos :: Int -> a -> [a]
repetidos n x
  | n <= 0    = []
  | otherwise = x : repetidos (n-1) x

--ejercicio 9 (!!)
{-Define la función nelem tal que nelem xs n es elemento en–ésimo de xs,
empezando a numerar desde el 0.-}
--ejercicio 10
{-Define la función posicionesC tal que posicionesC xs c es la lista de
la posiciones del caracter c en la cadena xs.-}
--ejercicio 11
{-Define la función compact, dada una lista retorna la lista sin los elementos
repetidos consecutivos.-}
compact :: Eq a => [a] -> [a]
compact [] = []
compact [x] = [x]
compact (x:y:xs)
  | x == y    = compact (y:xs)
  | otherwise = x : compact (y:xs)





