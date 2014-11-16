/*
20432863	ANDREW SOARES SILVA
20477652	ROBERTA SARAH SANTOS OLIVEIRA
20428584	RODRIGO CELEBRONE
20432398	RONALDO DOS SANTOS PEREIRA
20432866	RONALDO LOPES DE FREITAS
*/

package br.com.controleestoque;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Rodrigo Celebrone
 */
public class DataBase {
    
    private String Nome;
    
    private static String DataBaseFileName = "storage.txt";

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public boolean Add(Produto p){
        PrintWriter writer;
        try{
            writer = new PrintWriter(DataBaseFileName, "UTF-8");
            writer.println(p.getNome() + ";" + p.getCodigo());
            writer.close();
        }catch(IOException e){
            Err(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean Del(Produto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static void Err(String txt){
        System.err.println(txt);
    }
    
}
