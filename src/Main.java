import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }


    public byte[][] SwapIndexes(byte[][] message){
        byte[][] swap=new byte[message.length][message[0].length];
        for(int i=0;i<message[0].length;i++){
            for(int j=0;j<message[0].length;j++){
                swap[i][j]=message[j][i];
            }
        }
        return swap;
    }


    public byte[][] AddRoundKey (byte[][] a,byte[][] key){
        byte[][] ARK=new byte[a.length][a[0].length];
        for(int i=0;i<a[0].length;i++){
            for(int j=0;j<a[0].length;j++){
                ARK[i][j]=(byte)(((int)a[i][j])^((int)key[i][j]));
            }
        }
        return ARK;
    }



    public byte[][] Encrypy(byte[][] message,byte[][] key1,byte[][] key2){
    //AES2
    byte[][] aes1=AddRoundKey(SwapIndexes(message),key1);
    return  AddRoundKey(SwapIndexes(aes1),key2);
    }


    public byte[][] Decrypt(byte[][] Ciphertext,byte[][] key1,byte[][] key2){
    byte[][] aes_1=SwapIndexes(AddRoundKey(Ciphertext,key2));
    return SwapIndexes(AddRoundKey(aes_1,key1));
    }

    public void BreakEncryption(byte[][]message, byte[][] ciphertext, String output){
        int len = message.length;
        byte[][] cm = AddRoundKey(message,ciphertext);
        byte[][] k2 = new byte[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                k2[i][j] = 1;
            }
        }
        byte [][] swapK2 = AddRoundKey(cm,k2);
        byte[][] k1 = SwapIndexes(swapK2);
        try (FileOutputStream out = new FileOutputStream(output)) {

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    out.write(k1[i][j]);
                }
            }
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    out.write(k2[i][j]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
