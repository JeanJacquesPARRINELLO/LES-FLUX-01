/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beweb.beziers.flux.fichiers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author padbrain
 */
public class Exercice02Main {
    
    //  CHAQUE LIGNE DU FICHIER REPRESENTE UNE ENTRÉE DU TABLEAU
    List  linesArray;
    
     public static void main(String[] args) {
     }
     
    public void launch() throws IOException{
        linesArray = new LinkedList();
        mReadFile("/home/padbrain/fichiers/exercice2.txt");
        mDisplayContents();
    }
     
    public void mReadFile(String pWichFile) throws IOException{
        
        //  CRÉATION D'UNE VARIABLE DE TYPE STRINGBUILDER
        //  QUI VA SERVIR À RECONSTITUER LES LIGNES CARACTÈRE PAR CARACTÈRE
        StringBuilder phrase = new StringBuilder("");
        
        //  RÉCUPÉRATION D'UNE RÉFÉRENCE VERS LE FICHIER
        File myFile = new File(pWichFile);

        //  CRÉATION D'UN FLUX SORTANT DU FICHIER (ENTRANT POUR LE PROGRAMME)
        FileInputStream input = new FileInputStream(myFile);
        // recuperation du flux par paquets de 8 octets
        
        //  LECTURE DU FICHIER OCTET PAR OCTET
        byte[] paquet = new byte[1];

        //  LA MÉTHODE READ() RETOURNE LE NOMBRE DE DONNÉE TRANSMISES AU TABLEAU
        //  "PAQUET" ET -1 QUAND IL N'Y A PLUS DE DONNÉES
        while(input.read(paquet)!= -1){
            //  UN SEUL OCTET TRANSMIT À paquet DONC UN SEUL INDICE "0" DANS LE TABLEAU PAQUET
            //  CONVERSION DE L'OCTET EN CARACTÈRE PUIS EN STRING
            String newChar = (char)paquet[0] + "";

            //  ON PEUT VÉRIFIER ALORS SI LE CARACTÈRE TRANSMIS EST UN SAUT DE LIGNE
            if(newChar.equals("\n") != true){
                //  S'IL NE L'EST PAS, ON RECONSTITUE LA PHRASE AVEC LES CARACTÈRES TRANSMIS
                phrase.append(newChar);
            }else{
                //  S'IL S'AGIT D'UN SAUT DE LIGNE, LA PHRASE EST COMPLÈTE ET AJOUTÉE
                //  AU TABLEAU DE PHRASES
                linesArray.add(phrase);
                //  RESET DE LA PHRASE POUR LA PROCHAINE LIGNE
                phrase = new StringBuilder("");
            }
            //  PROCHAIN OCTET À CONTRÔLER DANS LE PASSAGE SUIVANT DE LA BOUCLE WHILE
            paquet = new byte[1];
        }
        //  PLUS D'OCTET À VÉRIFIER, FERMETURE DU FLUX TEXTE
        input.close();
    }
    
    /*
    *   MÉTHODE D'AFFICHAGE DU CONTENU DU FICHIER
    */
    public void mDisplayContents(){
        for(int line = 0 ; line < linesArray.size() ; line++)
        {
            //  UN SEUL # CORRESPOND À UN GRAND TITRE
            if((linesArray.get(line).toString().charAt(0) == '#') && (linesArray.get(line).toString().charAt(1) != '#')){
                //  AFFICHAGE D'UNE BORDURE PUIS DU TITRE PUIS UNE BORDURE
                setBorders();
                System.out.println(linesArray.get(line));
                setBorders();
            }
            //  DEUX # CORRESPONDENT À UN TITRE
            else if((linesArray.get(line).toString().charAt(0) == '#') && (linesArray.get(line).toString().charAt(1) == '#')){
                //  MISE EN MAJUSCULES DU TITRE
                System.out.println(linesArray.get(line).toString().toUpperCase());
            }else{
                //  LES LIGNES SANS TRAITEMENT PARTICULIER
                System.out.println(linesArray.get(line));
//                System.out.println(linesArray.get(line).toString().length());
            }
        }
    }
    
    
    /**
     * Une ligne bordure haute et basse
     */
    public void setBorders(){
        String line = "";
        int maxWidth = 62;
        for (int i = 0; i < maxWidth; i++) {
            line += "*";
        }
        System.out.println(line);
    }
     
}
