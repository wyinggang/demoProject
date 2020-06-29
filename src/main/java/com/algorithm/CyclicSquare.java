package com.algorithm;


public class CyclicSquare {


    public static void main(String[] args) {
         print(4);

    }

    public static void print(int n){
        int mark = 0,minX = 0,minY = 0,maxX = n - 1,maxY = n - 1;
          int [][] result = new int[n][n];
          while (minX <= maxX){
              for (int y = minY;y <= maxY;y++){
                  result[y][minX] = mark;
                  mark++;
                  if (mark==10)
                      mark=0;
              }
              minX++;
              for (int x = minX;x <= maxX;x++){
                  result[maxY][x] = mark;
                  mark++;
                  if (mark==10)
                      mark=0;
              }
              maxY--;
              for (int y = maxY;y >= minY;y--){
                  result[y][maxX]=mark;
                  mark++;
                  if (mark==10)
                      mark=0;
              }
              maxX--;
              for (int x = maxX;x >= minX;x--){
                  result[minY][x]=mark;
                  mark++;
                  if (mark==10)
                      mark=0;
              }
              minY++;
          }

        System.out.println("顺时针打印：");
        for(int y = 0;y < n;y++) {
            for(int x = 0;x < n;x++) {
                //顺时针打印
                //System.out.print(String.format("%4d",result[y][x]));
                //逆时针打印
                System.out.print(String.format("%4d",result[x][y]));
            }
            System.out.println();
        }

    }


}
