/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;
import tools.HashGenerationException;
import tools.HashGeneratorUtils;

/**
 *
 * @author Lenovo
 */
public class TestHashFucntion {

    public static void main(String[] args) {
        try {
            System.out.println("Input String");
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
            String inputString = sc.nextLine();
            inputString = inputString.trim();
            System.out.println("Input String: " + inputString);

            String md5Hash = HashGeneratorUtils.generateMD5(inputString);
            System.out.println("MD5 Hash: " + md5Hash);

            String sha1Hash = HashGeneratorUtils.generateSHA1(inputString);
            System.out.println("SHA-1 Hash: " + sha1Hash);

            String sha256Hash = HashGeneratorUtils.generateSHA256(inputString);
            System.out.println("SHA-256 Hash: " + sha256Hash);
        } catch (HashGenerationException ex) {
            ex.printStackTrace();
        }
    }
}
