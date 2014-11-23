/*
20432863	ANDREW SOARES SILVA
20477652	ROBERTA SARAH SANTOS OLIVEIRA
20428584	RODRIGO CELEBRONE
20432398	RONALDO DOS SANTOS PEREIRA
20432866	RONALDO LOPES DE FREITAS
*/

package br.com.controleestoque;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

        try{
            while(menuPrograma != 5){
                Label("////////////////////Escolha uma opção////////////////////////");
                Label("1. Inserir novo Produto");
                Label("2. Remover Produto");
                Label("3. Buscar Produto por codigo");
                Label("4. Buscar Produtos pelo nome");
                Label("5. Sair da aplicação");
                Label("////////////////////////////////////////////");
                Label("Digite o numero de uma dos opções acima: ");
                menuPrograma = input.nextInt();

                while (menuPrograma < 1 || menuPrograma > 5) {
                    Label("Digite uma das opções 1,2,3,4: ");
                    menuPrograma = input.nextInt();
                }

                Scanner scan;
                
                if(menuPrograma == 1){
                    Produto p = new Produto();

                    try{
                        Label("Código do produto que deseja cadastrar: ");
                        scan = new Scanner(System.in);
                        p.setCodigo(scan.nextInt());

                        Label("Nome do produto que deseja cadastrar: ");
                        scan = new Scanner(System.in);
                        p.setNome(scan.nextLine().trim());

                        if(p.isProduto())
                            storage.Add(p.getNome(), String.valueOf(p.getCodigo()));
                        else
                            throw new Exception("Por favor informe corretamente o nome e o codigo do produto.");
                    }catch(Exception e){
                        Err(e.getMessage());
                    }
                }else if(menuPrograma == 2){
                    Label("Código do produto que deseja remover: ");
                    scan = new Scanner(System.in);

                    storage.Del(scan.nextInt());
                }else if(menuPrograma == 3){
                    Label("Digite o codigo do produto que deseja pesquisar: ");
                    scan = new Scanner(System.in);
                    int codigo = scan.nextInt();
                    String item = storage.Search(codigo);
                    if(item != null){
                        Label("Produto encontrado: " + item.replace(storage.DelimiterFileChar, "(") + ")");
                    }else{
                        Err("Nenhum produto encontrado com o codigo: " + codigo);
                    }
                }else if(menuPrograma == 4){
                    Label("Digite o nome do produto que deseja pesquisar: ");
                    scan = new Scanner(System.in);
                    String term = scan.nextLine().trim();
                    ArrayList<String> lista;
                    if(term.toLowerCase().equals("all"))
                        lista = storage.Search();
                    else
                        lista = storage.Search(term);
                    if(lista.isEmpty())
                        Err("Nenhum produto encontrado com o termo: " + term);
                    else{
                        for(String i : lista){
                            Label(i.replace(storage.DelimiterFileChar, "(") + ")");
                        }
                    }
                }else if(menuPrograma == 5){
                    System.exit(0);
                }
            }
        }catch(InputMismatchException e){
            Err("Valor fornecido é diferente do esperado.");
            main(args);
        }
        
    }
    
    // para simplificar o uso do system.out.print em Strings
    private static void Label(String txt){
        System.out.println(txt);
    }
    
    // para simplificar o uso do system.out.print em Strings
    private static void Err(String txt){
        System.out.println(" ");
        System.err.println(txt);
    }
}
