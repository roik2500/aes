import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        encryptionUtils utils = new encryptionUtils();
        // write your code here
        if (args[0].equals("-e") || args[0].equals("-d")) {
            byte[] keyData = new byte[32];
            byte[] mData = new byte[16];
            byte[] cData = new byte[16];
            byte[][][] keys = new byte[2][4][4];
            byte[][] k1 = new byte[4][4];
            byte[][] k2 = new byte[4][4];
            byte[][] m = new byte[4][4];
            byte[][] c = new byte[4][4];
            int finalIndex = 0;
            keys[0] = k1;
            keys[1] = k2;
            int d = 0;
            try {
                keyData = Files.readAllBytes(Paths.get(args[2]));
                mData = Files.readAllBytes(Paths.get(args[4]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] finalArr = new byte[mData.length];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        keys[i][j][k] = keyData[d];
                        d += 1;
                    }
                }
            }
            d = 0;
            while (d < mData.length) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        m[j][k] = mData[d];
                        d += 1;
                    }
                }
                if (args[0].equals("-e")) {
                    c = utils.Encrypt(m, k1, k2);
                    try {
                        cData = Files.readAllBytes(Paths.get("C:\\Users\\shimon\\Downloads\\self_testing_files_2021\\cipher_long"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (args[0].equals("-d")) {
                    c = utils.Decrypt(m, k1, k2);
                    try {
                        cData = Files.readAllBytes(Paths.get("C:\\Users\\shimon\\Downloads\\self_testing_files_2021\\message_long"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        finalArr[finalIndex] = c[i][j];
                        finalIndex += 1;
                    }
                }

            }
            test1(finalArr, cData);
            try {
                Files.write(Paths.get(args[6]), finalArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            byte[] mData = new byte[16];
            byte[] cData = new byte[16];
            try {
                mData = Files.readAllBytes(Paths.get(args[2]));
                cData = Files.readAllBytes(Paths.get(args[4]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[][] m = new byte[4][4];
            byte[][] c = new byte[4][4];
            int d = 0;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    m[j][k] = mData[d];
                    c[j][k] = cData[d];
                    d += 1;
                }
            }
            byte[][][] keys;
            keys = utils.BreakEncryption(m,c,args[6]);

//            test
            c = utils.Encrypt(m, keys[0], keys[1]);

            byte[] cFinal = new byte[cData.length];
            d = 0;
            for (int i = 0; i <c.length ; i++) {
                for (int j = 0; j <c[0].length ; j++) {
                   cFinal[d] = c[i][j];
                   d += 1;
                }
            }
//            try {
//                cData = Files.readAllBytes(Paths.get("C:\\Users\\shimon\\Downloads\\self_testing_files_2021\\cipher_long"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            boolean bool = test1(cFinal,cData);
        }
    }
    public static boolean test1(byte[] c, byte[] cData){
        for (int j = 0; j < c.length; j++) {
                if (c[j] != cData[j]) {
                   return false;
                }
        }
        System.out.println("ff");
        return true;
    }
}


