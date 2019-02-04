package main.com.stormlin;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Util {

    static String get8BitIdFromString(String string) {
        MessageDigest generator;
        try {
            generator = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] messageDigest = generator.digest(string.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        StringBuilder hashText = new StringBuilder(no.toString(16));
        while (hashText.length() < 32) {
            hashText.insert(0, "0");
        }
        return hashText.substring(0, 8);
    }

    static TodoList readFromListFile(String listPath) {
        TodoList list;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(listPath));
            list = (TodoList) inputStream.readObject();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}