iguales = (2^29)/(2^9) ==(2^20)

l1 = "hola mundo"
x = [1,2,3]

segElemento = head (tail l1)

ultElemento l = head (reverse l)

esPar l = (ultElemento l) mod 2 == 0

paress=[ x*2 | x <- [0..] ]
imparess=[ x*2+1 | x <- [0..] ]
sumita=[ paress !! i + impares !! i | i <- [0..] ]
