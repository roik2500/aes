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

    public Byte[][] BreakEncryption(byte[][] message,byte[][] Ciphertext){

    }


}
