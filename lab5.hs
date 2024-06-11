--ej 1
unos :: [Int]
unos = 1 : unos
--ej2
natu :: Int -> [Int]
natu n = n : natu (n+1)
--ej3
prinatu :: Int ->[Int]
prinatu 0 = [0]
prinatu n = prinatu (n-1) ++ [n]
--ej4 esta funcion solicita listas inf, luego se sabe que tiene mas de 5 elementos
priCincoInf :: [Int] ->[Int]
priCincoInf (x1:x2:x3:x4:x5:xs) = x1:x2:x3:x4:x5:[]
--ej 11
factorial :: Integer -> Integer
factorial n = foldl (*) 1 [1..n]
