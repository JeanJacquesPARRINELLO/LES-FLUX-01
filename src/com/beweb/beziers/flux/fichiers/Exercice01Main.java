/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beweb.beziers.flux.fichiers;

//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.nio.charset.Charset;
//import java.util.Scanner;
/**
 *
 * @author padbrain
 */
public class Exercice01Main {
    
     public static void main(String[] args) {
         System.out.println("Ludo");
     }
     
     public void launch() throws IOException{
        mWriteFile("/home/padbrain/fichiers/exercice1.txt", "Hello world !");
     }
     
     public void readFile(String pWichFile) throws IOException{
         // recupération d'une reference vers le fichier test.txt
        System.out.println("hello world tatat");
         if(!new File(pWichFile).isFile()){
            File myFile = new File(pWichFile);
            myFile.createNewFile();
            // création d'un flux sortant du fichier (entrant pour le programme)
            FileInputStream input = new FileInputStream(myFile);
            // recuperation du flux par paquets de 8 octets
            byte[] paquet = new byte[8];
            // la methode read() retourne le nombre de donnée transmises au tableau  « paquet » et -1 quand il n'y a plus de données
            while(input.read(paquet)!= -1){
            // petite boucle pour lire les caractères se trouvant dans le tableau
            for (int i = 0; i < paquet.length; i++) {
            // les données étant au format octet on les caste en caratère
            System.out.print((char)paquet[i]);
            }
            // on vide le tableau
            paquet = new byte[8];
            }
            // fermeture du flux
            input.close();
         }
     }
     
     public void mWriteFile(String pWichFile, String pInsertText) throws IOException{
         
        if(!new File(pWichFile).isFile()){
            File myFile = new File(pWichFile);
            myFile.createNewFile();
            // création d'un flux entrant du fichier (sortant pour le programme)
            FileOutputStream output = new FileOutputStream(myFile);
            // message a enregistrer dans le fichier
            String message = pInsertText;
            // envoie par paquets de 8 octets
            byte[] paquet = new byte[8];
            // initialisation des variables
            int fin = message.length();
            int index = 0;
            // tant que la fin du message n'est pas atteinte
            while(index < fin){
                // insertion des caractères dans le tableau
                for (int i = 0; i < 8; i++) {
                    // la dernière partie ne tombe pas forcément pile a 8 octets
                    if(index<fin){
                        paquet[i] = (byte)message.charAt(index);
                        index++;
                    }else{
                        break;
                    }
                }
                // on envoie chaque paquets dans le fichier
                output.write(paquet);
                // raz sur le buffer
                paquet = new byte[8];
            }
        }
    }
}
