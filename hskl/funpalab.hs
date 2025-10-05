import Data.Char (toLower, isAlphaNum,isSpace)
import Data.List (sortBy)

type Palabra = [Char]
type Texto = [Char]

{-takeWord :: [Char] -> Palabra
takeWord [] = []
takeWord (x:xs) = 
    if isAlphaNum x then toLower x : takeWord xs else takeWord xs

palabras :: Texto -> [Palabra]  
palabras xs =
    foldr filtrar [] $ words xs 
    where
        filtrar x acc = if takeWord x /= [] then takeWord x : acc else acc

palabras :: Texto -> [Palabra]
palabras xs = words xs-}


 {-

-- Función principal que procesa el texto y guarda solo las palabras entre espacios
palabrasEntreEspacios :: String -> [String]
palabrasEntreEspacios = filter (not . null) . recogerPalabrasEntreEspacios

-- Función auxiliar que recoge las palabras entre espacios
recogerPalabrasEntreEspacios :: String -> [String]
recogerPalabrasEntreEspacios [] = []
recogerPalabrasEntreEspacios s = 
  let (preEspacio, resto) = break isSpace s
      (espacios, restoPalabra) = span isSpace resto
  in if null restoPalabra 
     then []
     else preEspacio : recogerPalabrasEntreEspacios (dropWhile (not . isSpace) restoPalabra)

-}


-- Función principal que procesa el texto y guarda solo las palabras entre espacios
palabrasEntreEspacios :: String -> [String]
palabrasEntreEspacios = recogerPalabrasEntreEspacios . dropWhile isSpace

-- Función auxiliar que recoge las palabras entre espacios
recogerPalabrasEntreEspacios :: String -> [String]
recogerPalabrasEntreEspacios [] = []
recogerPalabrasEntreEspacios s =
  let (palabra, resto) = span (not . isSpace) s
      restoSinEspacios = dropWhile isSpace resto
  in if null palabra || null restoSinEspacios
     then []
     else if isSpace (head resto) && (null (dropWhile isSpace restoSinEspacios) || not (isSpace (head (dropWhile isSpace restoSinEspacios))))
            then palabra : recogerPalabrasEntreEspacios restoSinEspacios
            else recogerPalabrasEntreEspacios restoSinEspacios

aMinusculas :: Palabra -> Palabra
aMinusculas palabra = map toLower palabra
palabras2 :: [Palabra] -> [Palabra]
palabras2 [] = []
palabras2 (x:xs) = aMinusculas x : palabras2 xs


ordenarPalabras :: [Palabra] -> [Palabra]
ordenarPalabras palabras = sortBy ordenAlfabetico palabras
    where 
        ordenAlfabetico :: String -> String -> Ordering
        ordenAlfabetico [] [] = EQ
        ordenAlfabetico [] _ = LT
        ordenAlfabetico _ [] = GT
        ordenAlfabetico (x:xs) (y:ys) 
            | x == y = ordenAlfabetico xs ys
            | x < y = LT
            | x > y = GT

contarPalabrasAdyacentes :: [Palabra] -> [(Int, Palabra)]
contarPalabrasAdyacentes [] = []
contarPalabrasAdyacentes (x:xs) = contar x 1 xs
  where
    contar p n [] = [(n, p)]
    contar p n (y:ys)
      | p == y = contar p (n + 1) ys
      | otherwise    = (n, p) : contar y 1 ys

ordenarPorRepeticion :: [(Int, Palabra)] -> [(Int, Palabra)]
ordenarPorRepeticion = sortBy repeticion
    where
        repeticion :: (Int, Palabra) -> (Int, Palabra) -> Ordering
        repeticion (a, _) (b, _)
            | a == b = EQ
            | a < b = GT
            | a > b = LT

palabrasComunes :: [Int] -> Texto -> [Palabra]
palabrasComunes pos ss = (snd . unzip) $ palabrasComunes' pos ss

palabrasComunes' :: [Int] -> Texto -> [(Int,Palabra)]
palabrasComunes' _ [] = []
palabrasComunes' [] _ = []
palabrasComunes' (x:xs) ss
    | x > (length $ words ss) = palabrasComunes' xs ss
    | otherwise =
        if x <= length ys
            then ys !! (x-1) : palabrasComunes' xs ss
            else []

        where
            ys = (ordenarPorRepeticion . contarPalabrasAdyacentes . ordenarPalabras . palabras2 . palabrasEntreEspacios) ss

