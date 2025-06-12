import java.util.Scanner;

public class SistemaImpressao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fila fila = new Fila();

        while (true) {
            int opcao;

            do { // menu da aplicação
                System.out.print("|----------------<<<|MENU|>>>----------------|\n");
                System.out.print("| Opção 1 - Enviar documento normal          |\n");
                System.out.print("| Opção 2 - Enviar documento com prioridade  |\n");
                System.out.print("| Opção 3 - Ver fila de impressão            |\n");
                System.out.print("| Opção 4 - Imprimir próximo documento       |\n");
                System.out.print("| Opção 0 - Encerrar programa        <<<-----|\n");
                System.out.print("|--------------------------->>>\n");
                System.out.print("|----->>> [i] Digite uma opção: ");

                if (sc.hasNextInt()) { // se a entrada for um número inteiro
                    opcao = sc.nextInt();
                    sc.nextLine();
                    
                    if (opcao < 0 || opcao > 4) { // se a opção for fora do escopo 0 a 4
                        System.out.print("\n| [!] Opção inválida!\n\n");
                        continue;
                    }
                    break;
                } else { // se a entrada não for um número inteiro
                    System.out.print("\n| [!] Por favor, digite um número válido.\n\n");
                    sc.next();
                }
            } while (true);

            switch (opcao) {
                case 2 -> {
                    String nome;
                    do { // loop do nome na 2° opção
                        System.out.print("\n| [i] Nome do documento: ");
                        nome = sc.nextLine().trim();

                        if (nome.isEmpty()) { // se o nome do documento estiver vazio
                            System.out.println("| [!] O nome do documento não pode estar vazio.");
                            continue;
                        }

                        // confirmação do nome
                        System.out.print(String.format("| [i] Confirmar o nome do documento como [ %s ]? s/n: ", nome));
                        String confirmacao = sc.nextLine().trim();

                        if (!confirmacao.isEmpty() && confirmacao.toLowerCase().charAt(0) == 's') { // se digitar 's' sai do loop
                            break;
                        }
                    } while (true);

                    do {// loop da prioridade na 2° opção
                        System.out.print("\n| [#] Quanto menor o número, maior a prioridade(1 é o mais prioritário, enquanto 5 é o menos prioritário)!");
                        System.out.print("\n| [i] Prioridade do documento[1 a 5]: ");

                        if (sc.hasNextInt()) { // se a entrada for um inteiro
                            int prioridade = sc.nextInt();
                            sc.nextLine(); 

                            if(prioridade > 5 || prioridade < 1) { // a prioridade tem que ser entre 1 e 5
                                System.out.print("| [!] Por favor, use um valor numérico de 1 a 5!\n");
                                continue;
                            }

                            System.out.print(String.format("| [i] Confirmar o valor da prioridade como [ %d ]? s/n: ", prioridade));
                            String confirmacao = sc.nextLine();

                            if (!confirmacao.isEmpty() && confirmacao.toLowerCase().charAt(0) == 's') { // se a entrada for 's', sai do loop
                                fila.inserir(new Documento(nome, prioridade));
                                break;
                            }
                        } else { // se a entrada não for um valor numérico
                            System.out.print("| [!] Por favor, use um valor numérico para a prioridade!\n");
                            sc.next();
                        }
                    } while (true);

                    System.out.println("\n| [+] Documento adicionado!\n");
                }
                case 1 -> {
                    String nome;
                    do {
                        System.out.print("\n| [i] Nome do documento: ");
                        nome = sc.nextLine().trim();

                        if (nome.isEmpty()) {
                            System.out.println("| [!] O nome do documento não pode estar vazio!");
                            continue;
                        }

                        System.out.print(String.format("| [i] Confirmar o nome do documento como [ %s ]? s/n: ", nome));
                        String confirmacao = sc.nextLine().trim();

                        if (!confirmacao.isEmpty() || confirmacao.toLowerCase().charAt(0) == 's') {
                            break;
                        }
                    } while (true);

                    fila.inserir(new Documento(nome, 6)); // insere o documento com prioridade 6 (normal)

                    System.out.println("\n| [+] Documento adicionado!\n");
                }
                case 3 -> {
                    System.out.println("\n| [#] FILA ATUAL: ");
                    fila.exibirFila();
                }
                case 4 -> {
                    String nomeDocTopo = fila.retornarTopo();
                    if (nomeDocTopo.equals("vazia")) {
                        System.out.println("\n| [!] Nenhum documento na fila! \n");
                        break;
                    }

                    System.out.print(String.format("\n| [i] Imprimir o próximo documento na fila [ %s ]? s/n: ", nomeDocTopo));
                    String confirmacao = sc.nextLine().trim();

                    if (confirmacao.isEmpty() || confirmacao.toLowerCase().charAt(0) != 's') {
                        System.out.println("");
                        break;
                    }

                    Documento doc = fila.remover(); 

                    if (doc == null) {
                        System.out.println("\n| [!] Nenhum documento na fila! \n");
                    } else {
                        String prioridadeStr = (doc.prioridade > 5) ? "Normal" : String.valueOf(doc.prioridade);
                        System.out.println(String.format("\n| [-] Imprimindo documento[^%s]: %s...\n", prioridadeStr, doc.nome));
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
