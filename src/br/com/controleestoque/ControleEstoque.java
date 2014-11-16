/*
20432863	ANDREW SOARES SILVA
20477652	ROBERTA SARAH SANTOS OLIVEIRA
20428584	RODRIGO CELEBRONE
20432398	RONALDO DOS SANTOS PEREIRA
20432866	RONALDO LOPES DE FREITAS
*/

package br.com.controleestoque;

import java.util.Scanner;
/**
 *
 * @author Rodrigo Celebrone
 */
public class ControleEstoque {
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        DataBase storage = new DataBase();
        int menuPrograma = 0;

        while(menuPrograma != 5){
            Label("////////////////////Escolha uma opção////////////////////////");
            Label("1. Inserir novo Produto");
            Label("2. Listar dados dos Produto");
            Label("3. Remover Produto");
            Label("4. Sair da aplicação");
            Label("////////////////////////////////////////////");
            Label("Digite o numero de uma dos opções acima: ");
            menuPrograma = input.nextInt();
            
            while (menuPrograma < 1 || menuPrograma > 4) {
                Label("Digite uma das opções 1,2,3,4: ");
                menuPrograma = input.nextInt();
            }
            
            if(menuPrograma == 1){
                Scanner produto;
                Produto p = new Produto();
                
                Label("Código do produto: ");
                produto = new Scanner(System.in);
                p.setCodigo(produto.nextInt());
                
                Label("Nome do produto: ");
                produto = new Scanner(System.in);
                p.setNome(produto.nextLine());
                
                storage.Add(p);
            }
            
        }
        
    }
    
    // para simplificar o uso do system.out.print em Strings
    private static void Label(String txt){
        System.out.println(txt);
    }
    
}
