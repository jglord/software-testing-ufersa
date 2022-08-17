package atividades.atividade2.main;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.print(fib(10));
    }
/* --------------------------------------------------------
* PARTIÇÔES
*
* Entrada:
*  - Tem que ser >= 0;
*  - Não pode ser < 0;
*
* -------------------------------------------------------- */
    public static int fib(int n){
        if (n < 0 ){
            throw new IllegalArgumentException("N < 0");
        }
        if( n < 2 )
            return n;
        else {
            return fib(n - 1 ) + fib( n - 2);
        }
    }
}
