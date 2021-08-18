 package com.example.password_generator;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES_ extends AppCompatActivity {

    public static String password_txt = "@123";
    EditText input_text;
    TextView output_text;
    Button enc, dec;
    Button clear;
    String outputstring = "";
    String inpt_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_e_s_);

        input_text = (EditText) findViewById(R.id.input_text);
        output_text = (TextView) findViewById(R.id.output_text);
        enc = (Button) findViewById(R.id.encrypt);
        dec = (Button) findViewById(R.id.decrypt);


        enc.setOnClickListener(new View.OnClickListener() {          //OnclickListener for Encrypt Button
            @Override
            public void onClick(View v) {
                try {
                    inpt_text = input_text.getText().toString();       //Storing Input String
                    outputstring = encrypt(inpt_text, password_txt);    //Calling Encryption Fucntion
                } catch (Exception e) {
                    e.printStackTrace();
                }
                output_text.setText(outputstring);                 //Printing the final Encrypted string
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {             //OnclickListener for Decrypt Button
            @Override
            public void onClick(View v) {

                try {
                    inpt_text = input_text.getText().toString();              //Storing the Input String
                    outputstring = decrypt(inpt_text, password_txt);           //Calling Decryption Function
                    output_text.setText(outputstring);                         //Printing the final decrypted String

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
    //Encryption Function
    private String encrypt(String Data, String password_text) throws Exception {
        SecretKeySpec key = generateKey(password_text);
        Cipher cipher = Cipher.getInstance("AES");                //Creating Object
        cipher.init(Cipher.ENCRYPT_MODE, key);                   //Initialisation
        byte[] ciphertext = cipher.doFinal(Data.getBytes("UTF-8"));
        String Encryptedstring = Base64.encodeToString(ciphertext, Base64.DEFAULT);      //Encoding Data using BASE64
        return Encryptedstring;       //returning the encrypted value
    }

    //Decryption Function
    private String decrypt(String Data, String password_text) throws Exception {
        SecretKeySpec key = generateKey(password_text);
        Cipher c = Cipher.getInstance("AES");                                        //Creating Object
        c.init(Cipher.DECRYPT_MODE, key);                                             //Initialisation
        byte[] decodedvalue = Base64.decode(Data, Base64.DEFAULT);                    //Decoding the BASE64 encoded String
        byte[] decvalue = c.doFinal(decodedvalue);                                    //Final decoding
        String decryptedvalue = new String(decvalue, "UTF-8");           //Converts Bytes into String
        return decryptedvalue;     //returning the decrypted value
    }

    //Key Generation Fucntion
    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");     //Using Hash function SHA-256
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();        //Completes the hash computation by performing final operations such as padding.
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;            //returning the Secret Key
    }


}

