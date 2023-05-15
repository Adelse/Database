package org.example;

import static java.lang.Math.abs;

public class Chess {
    public static  String solve(int kingX, int kingY, int rookX, int rookY, int bishopX, int bishopY) {

        //     Проверяем, угрожает ли королю ладья или слон
        boolean threatenedByRook = (kingX == rookX) || (kingY == rookY);
        boolean threatenedByBishop = (abs(kingX - bishopX) == abs(kingY - bishopY));

        // Выводим результат
        if (threatenedByRook && threatenedByBishop) {
            return "both";
        } else if (threatenedByRook) {
            return "rook";
        } else if (threatenedByBishop) {
            return "bishop";
        } else {
            return "none";
        }
    }
}


