package day01;

public class Mat {

    // My first program

    public static void main(String[] args) {

        Integer[][] mat = new Integer[4][3];
        mat[0][0] = 10;
        System.out.printf("rows: %d\n", mat.length);
        System.out.printf("cols: %d\n", mat[0].length);
        System.out.printf("value at 0,0: %d\n", mat[0][0]);
    }
}
