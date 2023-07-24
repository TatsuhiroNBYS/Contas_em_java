// Estamos importando a biblioteca para entrada e saida de dados
import java.util.Scanner;  // import eh uma diretiva de compilacao

// Estamos implementando a classe pai chamada "Conta"
public class Contas {
    private       // Encapsulamento do atributo "Valor" com visibilidade apenas nessa classe
       float Valor; // Atributo responsavel por controlar o saldo da conta
    protected     // Encapsulamento dos atributos "Nome" e "Conta" com visibilidade
       String Nome; // apenas nessa classe e em todas as classes filhas (subclasses)
       String Conta;// da classe conta

    public float getVlrSaldo(){
      return(Valor);    
    }
       
    public int ExibirMenu() {   // Implementacao do metodo ExibirMenu()
      // Na linha abaixo declaramos o objeto "ler" da classe Scanner
      Scanner ler = new Scanner(System.in);
      System.out.println(" \n\n\n");
      System.out.println(" ******* Conta Bancaria ********");
      System.out.println(" 1) Consultar o Saldo");
      System.out.println(" 2) Depositar");
      System.out.println(" 3) Sacar");
      System.out.println(" 4) Sair");
      System.out.print(" Digite a opcao desejada: ");
      return ler.nextInt();
    }

    public void ConsultarSaldo() {  // Implementacao do metodo ConsultarSaldo()
      System.out.println(" \n\n\n");
      System.out.println(" **** SALDO DA CONTA ****");
      System.out.println(" Nome do correntista: " + Nome);
      System.out.println(" Numero da conta: " + Conta);
      System.out.println(" Valor do Saldo: " + Valor);
      System.out.println(" ************************ \n\n\n");
    }

    public boolean Depositar(float Quanto) {
      if (Quanto <= 0) // Estamos validando NOVAMENTE se valor informado e positivo
        return(false);   // A funcao retorna falso caso receba um valor invalido
      else {
        Valor = Valor + Quanto;  // Incrementamos o valor do saldo
        return(true);            // Retornamos verdadeiro para quem nos chamou
      }
    }

    public boolean Sacar(float Quanto) {
      if (Quanto <= 0) // Estamos validando NOVAMENTE se valor informado e positivo
        return(false); // A funcao retorna falso caso receba um valor invalido
      else if (Quanto > Valor) {  // Validamos se ha saldo suficiente na conta
  System.out.println("\n Saldo insuficiente!");
  return(false);
      }
      else {
        Valor = Valor - Quanto;  // Decrementamos o valor do saldo
        return(true);            // Retornamos verdadeiro para quem nos chamou
      }
    }

    public boolean AbrirConta(String Cliente, String numConta, float Quanto) {
      if (Quanto < 0) {
       System.out.println(" O saldo inicial da conta nao pode ser negativo!");
 return false;
      }
      else {
          Nome = Cliente;
 Valor = Quanto;
 Conta = numConta;
 return true;
      }
    }
  // Na linha a seguir estamos criando o metodo principal da classe Contas  
  public static void main(String[] args) {
    // Na linha abaixo declaramos o objeto "ler" da classe Scanner para entrada de dados
    Scanner ler = new Scanner(System.in);
    int resposta;
    float valor;
    System.out.print(" Informe o nome do correntista: ");
    String NomeCorrentista = ler.nextLine();
    System.out.print(" Informe o numero da conta: ");
    String NumeroConta = ler.nextLine();
    System.out.print(" Informe o valor de abertura: ");
    float vlrSaldo = ler.nextFloat();
   
    // Implementar o menu para a escolha do tipo da conta 1) Corrente; 2) Poupanca; 3) Salario;
      System.out.println(" ******* Escolha o Tipo da Conta ********");
      System.out.println(" 1) Corrente");
      System.out.println(" 2) Poupanca");
      System.out.println(" 3) Salario");
      System.out.println(" 4) Universitaria");
      System.out.println(" 5) Sair");
      System.out.print(" Digite a opcao desejada: ");
      resposta = ler.nextInt();
     
    // Na linha a seguir, estamos instanciando o objeto minhaConta da classe Contas
    Contas minhaConta = null;
    if (resposta == 1)
      minhaConta = new Contas();
    else if (resposta == 2)
      minhaConta = new Poupanca();
    else if (resposta == 3)
      minhaConta = new Salario();
    else if (resposta == 4)
      minhaConta = new Universitaria();

    minhaConta.AbrirConta(NomeCorrentista, NumeroConta, vlrSaldo);
    do {
      resposta = minhaConta.ExibirMenu();
      if (resposta == 1) {
        minhaConta.ConsultarSaldo();
      }  
      else if (resposta == 2) {
        System.out.printf(" Informe o valor a ser depositado: ");
        valor = ler.nextFloat();
        if (minhaConta.Depositar(valor))
          System.out.printf("\n Deposito de " + valor + " realizado com sucesso!");
        else
          System.out.printf("\n Erro ao fazer o deposito!");
      }  
      else if (resposta == 3) {
        System.out.printf(" Informe o valor do saque: ");
        valor = ler.nextFloat();
        if (minhaConta.Sacar(valor)) {
          System.out.printf("\n Saque de " + valor + " realizado com sucesso!");
          //Valor = 1000;
        }
        else
          System.out.println(" O Saque nao foi realizado!");
      }  
      else {
        System.out.println("\n\n Obrigado por usar o nosso banco!!! \n\n");
      }
    }  // Fechando o comando de repeticao "do"
   while (resposta < 4);

  }  // Estamos fechando o metodo principal
 
} // Estamos fechando a classe Contas

// Na linha abaixo estamos criando a subclasse Popanca a partir da super classe Contas
class Poupanca extends Contas {
  private
    // Atributo responsavel por armazenar o valor do limite maximo de saques
    float vlrLimiteSaqueDia = 300;
    // Atributo responsavel por controlar os valores ja sacados
    float vlrTotalSaqueDia = 0;
   
  protected boolean aindaPodeSacar(float vlrSaque) {
    if (vlrSaque + vlrTotalSaqueDia <= vlrLimiteSaqueDia)
      return (true);
    else
      return (false);
  }
 
  // Na linha a seguir estamos reescrevendo o metodo sacar (Polimorfismo)
    public boolean Sacar(float Quanto) {
      if (aindaPodeSacar(Quanto) == true) {
        super.Sacar(Quanto);
        vlrTotalSaqueDia = vlrTotalSaqueDia + Quanto;
        return true;
      }
      else {
        System.out.println(" Limite de saque por dia excedido!");
        return false;
      } // fechando o else
    }  // fechando a sobrescrita do metodo sacar
} // fechando a classe Poupanca
   
  // Tarefa para casa (20/10/2022):
  // Implementar a classe conta salario com as seguinmtes caracteristicas:
  // Na conta salario, ALEM DAS MESMAS RESTRICOES DA CONTA POUPANCA,
  // somente um deposito por mes deve ser permitido, que eh o deposito do salario

  class Salario extends Poupanca {
    private
       // Atributo responsavel por armazenar a quantidade de depositos mensais
       float qtdDepositosMes = 1;
       // Atributo responsavel por controlar a quantidade de depositos ja realizados
       float qtdDepositosRealizados = 0;
       
       // Metodo responsavel por validar se a qtd de depositos ainda esta no limite
       protected boolean aindaPodeDepositar () {
       if (qtdDepositosRealizados < qtdDepositosMes)
         return (true);
       else
         return (false);
        }
       
       // Estamos promovendo o polimorfismo de sobreposicao (pag. 134 cap. 06)
       public boolean Depositar(float Quanto) {
         if (aindaPodeDepositar() == true) {
           super.Depositar(Quanto);
           qtdDepositosRealizados = qtdDepositosRealizados + 1;
           return true;
         }
         else {
           System.out.println(" Limite de depositos para conta salario excedido!");
           return false;
         } // fechando o else
      } // fechando a sobrescrita do metodo Depositar
} // fechando a classe Salario

    // Ultima Tarefa (27/10/2022):
    // Implementar a conta do tipo Universitaria com a seguinte caracteristica:
    // Cada saque nao pode exceder 60% do valor do saldo (powered by Gideao)
class Universitaria extends Contas {
    private
       // Atributo responsavel por armazenar o percentual
       float vlrPercentualSaque = 60;
       
       // Metodo responsavel por validar se a qtd de depositos ainda esta no limite
       protected boolean podeSacar (float vlrSaque) {
       if (vlrSaque <= getVlrSaldo() * vlrPercentualSaque/100)
         return (true);
       else
         return (false);
        }
       
  // Na linha a seguir estamos reescrevendo o metodo sacar (Polimorfismo)
    public boolean Sacar(float Quanto) {
      if (podeSacar(Quanto) == true) {
        super.Sacar(Quanto);
        return true;
      }
      else {
        System.out.println(" So pode sacar ate 60% do seu saldo!");
        return false;
      } // fechando o else
    }  // fechando a sobrescrita do metodo sacar
} // fechando a classe Universitaria
;