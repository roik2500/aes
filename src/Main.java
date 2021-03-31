import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        if (args[0].equals("e") || args[0].equals("d")) {
            encryptionUtils utils = new encryptionUtils();
            byte[] keyData = new byte[32];
            byte[] mData = new byte[32];
            byte[] cData = new byte[32];
            try {
                keyData = Files.readAllBytes(Paths.get(args[2]));
                mData = Files.readAllBytes(Paths.get(args[4]));
                cData = Files.readAllBytes(Paths.get("C:\\Users\\shimon\\Downloads\\self_testing_files_2021\\message_short"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[][][] keys = new byte[2][4][4];
            byte[][] k1 = new byte[4][4];
            byte[][] k2 = new byte[4][4];
            byte[][] m = new byte[4][4];
            keys[0] = k1;
            keys[1] = k2;
            int d = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        keys[i][j][k] = keyData[d];
                        if (d < 16) {
                            m[j][k] = mData[d];
                        }
                        d += 1;
                    }
                }
            }
            byte[][] c = new byte[0][];
            if (args[0].equals("e")) {
                c = utils.Encrypt(m, k1, k2);
            }
            if (args[0].equals("d")) {
                c = utils.Decrypt(m, k1, k2);
            }
//        Remove!!!!
            d = 0;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (c[j][k] != cData[d]) {
                        System.out.println("false");
                    }
                    d += 1;
                }
            }
//        ///
            try {
                for (int i = 0; i < c.length; i++) {
                    Files.write(Paths.get(args[6]), c[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


