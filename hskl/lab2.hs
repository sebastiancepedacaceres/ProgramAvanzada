--                  Practica 2
--hd :: [a] -> a
--hd l = l !! 0

tl :: [a] -> [a]
tl (x:xs) = xs

hd :: [a] -> a
hd (x:xs) = x

lt :: [a] -> a
lt [x] = x
lt (x:xs) = lt xs

it :: [a] -> [a]
it [x]= []
it (x:xs)= x: it xs

--ejercicio 3
maxTres :: Int -> Int -> Int -> Int
maxTres i j z 
    | i <= j && z <= j = j 
    | j <= i && z <= i = i
    | i <= z && j <= z = z
                
--ejercicio 4
concatenate :: [Int] -> [Int] -> [Int]
concatenate [] ys = ys
concatenate (x:xs) ys = x:(concatenate xs ys)
--tomar
--tirar
-- <-


--ejercicio 5
absoluto :: Int -> Int
absoluto n = if n < 0 then -n else n

--ejercicio 6*
edad :: (Nat,Nat,Nat) -> (Nat,Nat,Nat) -> Int
edad (dia1, mes1, anio1) (dia2, mes2, anio2)
    | mes1 < mes2 || (mes1 == mes2 && dia1 <= dia2) = anio2 - anio1
    | otherwise = anio2 - anio1 - 1

--ejercicio 7
xor :: Bool -> Bool -> Bool
xor True False = True
xor False True = True
xor True True = False 
xor False False = False 

--7*
xor2 :: Bool -> Bool -> Bool
xor2 True False = True
xor2 False True = True
xor2 _ _        = False 

--otra version 7*
xor3 :: Bool -> Bool -> Bool
xor3 b1 b2 = (b1 && (not b2)) || ((not b1) && b2)

--ejercicio 8
esPrimo :: Int -> Bool
esPrimo n
    | n <= 1    = False
    | otherwise = esPrimoAux n 2

esPrimoAux :: Int -> Int -> Bool
esPrimoAux n divisor
    | divisor * divisor > n    = True
    | n `mod` divisor == 0     = False
    | otherwise                = esPrimoAux n (divisor + 1)

--ejercicio 9*
primosMenoresQue :: Int -> [Int]
primosMenoresQue n = filter esPrimo [2..n-1]

--ejercicio 10
reverse1 :: [Int] -> [Int]
reverse1 [] = []
reverse1 (x:xs) = (reverse1 xs)++[x]

--ejercicio 11
primosLista :: [Int] -> [Int]
primosLista lista = filter esPrimo lista

--ejercicio 12*
esPalindromo :: Eq a => [a] -> Bool
esPalindromo lista = lista == reverse lista

--ejercicio 13
cantidadRaices :: Float -> Float -> Float -> Int
cantidadRaices a b c
    | discriminante > 0  = 2   -- Dos raíces reales distintas
    | discriminante == 0 = 1   -- Una raíz real doble
    | otherwise          = 0   -- No hay raíces reales
    where discriminante = b * b - 4 * a * c

