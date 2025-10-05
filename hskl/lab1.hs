--
esPar :: Integer -> Bool
esPar x = mod x 2 == 0

esParLista :: [Integer] -> Bool
esParLista x = mod(head(reverse x)) 2 == 0

esImparLista :: [Integer] -> Bool
esImparLista x = mod (head(reverse x)) 2 /= 0

multTres :: [Integer] -> Bool
multTres x = mod (sum x) 3 == 0

multSeis :: [Integer] -> Bool
multSeis x = multTres x && esParLista x

numaLista :: Integer -> [Integer]
numaLista 0 = []
numaLista n = (numaLista (div n 10)) ++ [mod n 10]

cortar :: Int -> Int -> [Char] -> [Char]
cortar i j w = drop (i-1) (take j w)

{-
Para saber el número de pasos realizados por el intérprete GHCi, puedes usar el comando :step o :s. Por ejemplo, si quieres ver los pasos detallados de la evaluación de esta expresión, puedes escribir :step (head . (drop 3)) "0123456". Esto te dará una desglose de los pasos realizados por el intérprete durante la evaluación.
-}
main :: IO ()
main = do
print "a"
