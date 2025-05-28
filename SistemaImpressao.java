import java.util.Scanner;

public class SistemaImpressao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fila fila = new Fila();

        while (true) {
            int opcao;

            do {
                System.out.print("|-------------<<<|MENU|>>>-------------|\n");
                System.out.print("| Opção 1 - Enviar documento           |\n");
                System.out.print("| Opção 2 - Ver fila de impressão      |\n");
                System.out.print("| Opção 3 - Imprimir próximo documento |\n");
                System.out.print("| Opção 0 - Encerrar programa     <<<--|\n");
                System.out.print("|---------------------->>>\n");
                System.out.print("|-------->>> [i] Digite uma opção: ");

                if (sc.hasNextInt()) {
                    opcao = sc.nextInt();
                    sc.nextLine(); // odeio o buffer do scanner me mata
                    
                    if (opcao < 0 || opcao > 3) {
                        System.out.print("| [!] Opção inválida!\n");
                        continue;
                    }
                    break;
                } else {
                    System.out.print("| [!] Por favor, digite um número válido.\n");
                    sc.next();
                }
            } while (true);

            switch (opcao) {
                case 1 -> {
                    String nome;
                    do {
                        System.out.print("\n| [i] Nome do documento: ");
                        nome = sc.nextLine();

                        System.out.print(String.format("| [i] Confirmar o nome do documento como [ %s ]? s/n: ", nome));
                        String confirmacao = sc.nextLine();

                        if (confirmacao.toLowerCase().charAt(0) == 's') {
                            break;
                        }
                    } while (true);

                    do {
                        System.out.print("\n| [#] Quanto menor o número, mais prioridade ele tem!");
                        System.out.print("\n| [i] Prioridade do documento: ");

                        if (sc.hasNextInt()) {
                            int prioridade = sc.nextInt();
                            sc.nextLine(); 

                            System.out.print(String.format("| [i] Confirmar o valor da prioridade como [ %d ]? s/n: ", prioridade));
                            String confirmacao = sc.nextLine();

                            if (confirmacao.toLowerCase().charAt(0) == 's') {
                                fila.inserir(new Documento(nome, prioridade));
                                break;
                            }
                        } else {
                            System.out.print("| [!] Por favor, use um valor numérico para a prioridade!\n");
                            sc.next();
                        }
                    } while (true);

                    System.out.println("\n| [+] Documento adicionado!\n");
                }
                case 2 -> {
                    System.out.println("\n| [#] FILA ATUAL: ");
                    fila.exibirFila();
                }
                case 3 -> {
                    Documento doc = fila.remover();

                    if (doc == null) {
                        System.out.println("\n| [!] Nenhum documento na fila! \n");
                    } else {
                        System.out.println(String.format("\n| [-] Imprimindo documento: %s...\n", doc));
                    }
                }
                case 0 -> {
                    System.out.println("\n| [!] Programa encerrado. ");
                    sc.close();
                    return;
                }
                default -> System.out.println("\n| [!] Opção inválida!\n");
            }
        }
    }
}
