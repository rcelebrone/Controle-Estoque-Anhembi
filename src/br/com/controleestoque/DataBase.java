/*
20432863	ANDREW SOARES SILVA
20477652	ROBERTA SARAH SANTOS OLIVEIRA
20428584	RODRIGO CELEBRONE
20432398	RONALDO DOS SANTOS PEREIRA
20432866	RONALDO LOPES DE FREITAS
*/

package br.com.controleestoque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo CelebReaderone
 */
public class DataBase {

    private static final String DataBaseFileName = "storage.txt";
    public String DelimiterFileChar = "¬";

    private static void Err(String txt){
        System.err.println(txt);
    }
    
    public boolean Del(int codigo) {
        File originFile;
        BufferedReader bReader;
        PrintWriter pWriter;
        try {
            originFile = new File(DataBaseFileName);

            if (!originFile.exists()) {
                Err("Database file não existe");
                return false;
            }

            //arquivo temporario
            File tempFile = new File(originFile.getAbsolutePath() + ".tmp");

            //carrega o arquivo principal e abre um pWriter para o temp
            bReader = new BufferedReader(new FileReader(originFile));
            pWriter = new PrintWriter(new FileWriter(tempFile));

            //Faz a leitura do arquivo original e grava no temporario apenas o que vamos manter no arquivo
            String line;
            while ((line = bReader.readLine()) != null) {
                if (!line.trim().endsWith(DelimiterFileChar + codigo)) {
                    pWriter.println(line);
                    pWriter.flush();
                }else{
                    String nome = line.trim().replace(DelimiterFileChar + codigo, "");
                    int total = this.Count(nome);
                    if(total <= 5){
                        System.err.println("Existem apenas " + total + " produtos \"" + nome +"\" no estoque");
                    }
                }
            }
            pWriter.close();
            bReader.close();

            //Delete the original file
            if (!originFile.delete()) {
                Err("Não é possivel deletar o arquivo");
                return false;
            } 

            //renomeia o arquivo temporario para o original
            if (!tempFile.renameTo(originFile)){
                Err("Não foi possivel renomear o arquivo temporario");
                return false;
            }
        }catch (FileNotFoundException e) {
            Err(e.getMessage());
            return false;
        }
        catch (IOException e) {
            Err(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean Add(String name, String value) {
        File originFile;
        BufferedReader bReader;
        PrintWriter pWriter;
        try {
            originFile = new File(DataBaseFileName);

            if (!originFile.exists()) {
              originFile.createNewFile();
            }

            //arquivo temporario
            File tempFile = new File(originFile.getAbsolutePath() + ".tmp");

            //carrega o arquivo principal e abre um pWriter para o temp
            bReader = new BufferedReader(new FileReader(originFile));
            pWriter = new PrintWriter(new FileWriter(tempFile));

            //Faz a leitura do arquivo original e grava no temporario apenas o que vamos manter no arquivo
            String line;
            while ((line = bReader.readLine()) != null) {       
                pWriter.println(line);
                pWriter.flush();
            }
            
            //escreve a nova linha
            pWriter.println(name + DelimiterFileChar + value);
            pWriter.flush();
            
            //finaliza
            pWriter.close();
            bReader.close();

            //Delete the original file
            if (!originFile.delete()) {
                Err("Não é possivel deletar o arquivo");
                return false;
            } 

            //renomeia o arquivo temporario para o original
            if (!tempFile.renameTo(originFile)){
                Err("Não foi possivel renomear o arquivo temporario");
                return false;
            }
        }catch (FileNotFoundException e) {
            Err(e.getMessage());
            return false;
        }
        catch (IOException e) {
            Err(e.getMessage());
            return false;
        }
        return true;
    }
    
    public ArrayList<String> Search() {
        File originFile;
        BufferedReader bReader;
        ArrayList aList;
        try {
            originFile = new File(DataBaseFileName);
            aList = new ArrayList();

            if (!originFile.exists()) {
                Err("Database file não existe");
                return null;
            }

            //carrega o arquivo principal e abre um pWriter para o temp
            bReader = new BufferedReader(new FileReader(originFile));

            //Faz a leitura do arquivo original e grava no temporario apenas o que vamos manter no arquivo
            String line;
            while ((line = bReader.readLine()) != null) {
                aList.add(line);
            }

            //finaliza
            bReader.close();
        }catch (IOException e) {
            Err(e.getMessage());
            return null;
        }
        return aList;
    }
    
    public String Search(int codigo) {
        File originFile;
        BufferedReader bReader;
        String sFound = null;
        try {
            originFile = new File(DataBaseFileName);

            if (!originFile.exists()) {
                Err("Database file não existe");
                return null;
            }

            //carrega o arquivo principal e abre um pWriter para o temp
            bReader = new BufferedReader(new FileReader(originFile));

            //Faz a leitura do arquivo original e grava no temporario apenas o que vamos manter no arquivo
            String line;
            while ((line = bReader.readLine()) != null) {
                if(line.endsWith(DelimiterFileChar+String.valueOf(codigo))){
                    sFound = line;
                }
            }

            //finaliza
            bReader.close();
        }catch (IOException e) {
            Err(e.getMessage());
            return null;
        }
        return sFound;
    }
    
    public ArrayList<String> Search(String name) {
        File originFile;
        BufferedReader bReader;
        ArrayList aList;
        try {
            originFile = new File(DataBaseFileName);
            aList = new ArrayList();

            if (!originFile.exists()) {
                Err("Database file não existe");
                return null;
            }

            //carrega o arquivo principal e abre um pWriter para o temp
            bReader = new BufferedReader(new FileReader(originFile));

            //Faz a leitura do arquivo original e grava no temporario apenas o que vamos manter no arquivo
            String line;
            while ((line = bReader.readLine()) != null) {
                if(line.startsWith(name+DelimiterFileChar)){
                    aList.add(line);
                }
            }

            //finaliza
            bReader.close();
        }catch (IOException e) {
            Err(e.getMessage());
            return null;
        }
        return aList;
    }

    public int Count(String name){
        File originFile;
        BufferedReader bReader;
        int iTotal = 0;
        try {
            originFile = new File(DataBaseFileName);

            if (!originFile.exists()) {
                Err("Database file não existe");
                return 0;
            }

            //carrega o arquivo principal e abre um pWriter para o temp
            bReader = new BufferedReader(new FileReader(originFile));

            //Faz a leitura do arquivo original e grava no temporario apenas o que vamos manter no arquivo
            String line;
            while ((line = bReader.readLine()) != null) {
                if(line.startsWith(name+DelimiterFileChar)){
                    iTotal++;
                }
            }

            //finaliza
            bReader.close();
        }catch (IOException e) {
            Err(e.getMessage());
            return 0;
        }
        return iTotal;
    }
}
