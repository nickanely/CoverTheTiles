import java.util.Random;
import java.util.Scanner;

public class CoverTheTiles  {


    private static Random rand;

    public static void outputTiles(boolean[] tiles) {
        StringBuilder sb = new StringBuilder("Open tiles: 1:");
        sb.append(tiles[0]);
        for (int i = 1; i < tiles.length; i++) {
            sb.append(" ").append(i + 1).append(":").append(tiles[i]);
        }
        System.out.println(sb.toString());
    }

    public static void setRandomWithSeed(int seed) throws IllegalAccessException {
        if (rand != null) {
            throw new IllegalAccessException("IllegalAccessException!");
        }
        rand = new Random(seed);
    }

    public static void setRandom() throws IllegalAccessException {
        setRandomWithSeed(144);
    }

    public static int randomInt(int minval, int maxval) {
        return minval + rand.nextInt(maxval - minval + 1);
    }

    public static int dice() throws IllegalAccessException {
        if (rand == null) {
            setRandom();
        }
        return randomInt(1, 6);
    }

    public static void main(String[] args) throws IllegalAccessException {
        Scanner scan = new Scanner(System.in);

        int sum1 = 0;
        int sum2 = 0;
        int last = 0;
        boolean[] arr = {true, true, true, true, true, true, true, true, true, true};
        for (int i = 0; i < 10; i++) {
            int x = dice();
            int y = dice();
            int z = dice();
            int p = dice();
            int tile1f, tile2f, tile1s, tile2s;
            do {
                System.out.println("Player 1 numbers:\n" + x + "\n" + y);
                outputTiles(arr);
                System.out.println("Which tiles to cover by player 1? (0 for no valid combination)\n" + "Tile 1: ");
                tile1f = scan.nextInt();
                System.out.println("Tile 2: ");
                tile2f = scan.nextInt();
                if (tile1f == 0 && tile2f == 0) {
                    break;
                }
            } while (tile1f + tile2f != x + y || tile1f == tile2f || !arr[tile1f - 1] || !arr[tile2f - 1] || tile1f <= 0 || tile1f >= 10 || tile2f <= 0 || tile2f >= 10);
            if ((tile1f + tile2f) == (x + y)) {
                arr[tile1f - 1] = false;
                arr[tile2f - 1] = false;
                int numclosed = 0;
                for (boolean b : arr) {
                    if (!b) {
                        numclosed++;
                    }
                }
                if (numclosed == 10) {
                    last = 1;
                    break;
                }
            }
            if (tile1f == 0 || tile2f == 0) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j])
                        sum1 += j + 1;
                }
            }
            do {
                System.out.println("Player 2 numbers: \n" + z + "\n" + p);
                outputTiles(arr);
                System.out.println("Which tiles to cover by player 2? (0 for no valid combination)" + "Tile 1: ");
                tile1s = scan.nextInt();
                System.out.println("Tile 2: ");
                tile2s = scan.nextInt();

                if (tile1s == 0 && tile2s == 0) {
                    break;
                }
            } while (tile1s + tile2s != z + p || tile1s == tile2s || !arr[tile1s - 1] || !arr[tile2s - 1] || tile1s <= 0 || tile1s >= 10 || tile2s <= 0 || tile2s >= 10);
            if ((tile1s + tile2s) == (z + p)) {
                arr[tile1s - 1] = false;
                arr[tile2s - 1] = false;
            }
            int numclosed = 0;
            for (boolean b : arr) {
                if (!b) {
                    numclosed++;
                }
            }
            if (numclosed == 10) {
                last = 2;
                break;
            }
            if (tile1s == 0 || tile2s == 0) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j])
                        sum2 += j + 1;
                }
            }
        }
        if (last == 1) {
            System.out.println("Player 1 wins!");
        } else if (last == 2) {
            System.out.println("Player 2 wins!");
        } else {
            if (sum1 == sum2) {
                System.out.println("tie");
            } else if (sum1 > sum2) {
                System.out.println(" player 2 wins");
            } else if (sum1 < sum2) {
                System.out.println("player 1 wins");
            }
        }
    }
}