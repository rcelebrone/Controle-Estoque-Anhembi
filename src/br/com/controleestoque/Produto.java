/*
20432863	ANDREW SOARES SILVA
20477652	ROBERTA SARAH SANTOS OLIVEIRA
20428584	RODRIGO CELEBRONE
20432398	RONALDO DOS SANTOS PEREIRA
20432866	RONALDO LOPES DE FREITAS
*/

package br.com.controleestoque;

/**
 *
 * @author Rodrigo Celebrone
 */
public class Produto {
    private int Codigo;
    private String Nome;

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }
    public String getNome() {
        return Nome;
    }
    public int getCodigo() {
        return Codigo;
    }
    
    public boolean isProduto(){
        return (this.Nome.length() > 0 && this.Codigo > 0); 
    }
    
}
