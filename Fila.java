import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Fila {
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Documento> lista = new ArrayList<>();
    private int ordemChegadaCounter = 0;

    public void inserir(Documento doc) {
        doc.ordemChegada = ordemChegadaCounter++;
        lista.add(doc);
        subir(lista.size() - 1);
    }

    public Documento remover() {
        if (lista.isEmpty()) {
            return null;
        }

        Documento docTopo = lista.get(0);
        Documento noFinal = lista.remove(lista.size() - 1);

        if (!lista.isEmpty()) {
            lista.set(0, noFinal);
            descer(0);
        }

        return docTopo;
    }

    public void exibirFila() {
        if (lista.isEmpty()) {
            System.out.println("| [!] A fila está vazia!\n");
            return;
        }

        List<Documento> copiaOrdenada = new ArrayList<>(lista); // copia só pra ordenar ao exibir
        copiaOrdenada.sort(
            Comparator.comparingInt((Documento doc) -> doc.prioridade)
            .thenComparingInt(doc -> doc.ordemChegada)
        );

        for (Documento doc : copiaOrdenada) {
            if (doc.prioridade > 5) {
                System.out.println(String.format("| [Normal] %s", doc.nome));
            } else {
                System.out.println(String.format("| [Prioridade: %d] %s", doc.prioridade, doc.nome));
            }
        }
        System.out.println(" ");
    }

    public String retornarTopo() {
        if (lista.isEmpty()) {
            return "vazia";
        }

        String docTopo = lista.get(0).nome;

        return docTopo;
    }

    private int compara(Documento a, Documento b) {
        if (a.prioridade != b.prioridade) {
            return Integer.compare(a.prioridade, b.prioridade);
        } else {
            return Integer.compare(a.ordemChegada, b.ordemChegada);
        }
    }

    private void subir(int i) {
        while (i > 0) {
            int pai = (i - 1) / 2;
            if (compara(lista.get(i), lista.get(pai)) < 0) {
                trocar(i, pai);
                i = pai;
            } else {
                break;
            }
        }
    }

    private void descer(int i) {
        int tamanho = lista.size();

        while (true) {
            int menor = i;
            int esq = 2 * i + 1;
            int dir = 2 * i + 2;

            if (esq < tamanho && compara(lista.get(esq), lista.get(menor)) < 0)
                menor = esq;
            if (dir < tamanho && compara(lista.get(dir), lista.get(menor)) < 0)
                menor = dir;

            if (menor != i) {
                trocar(i, menor);
                i = menor;
            } else {
                break;
            }
        }
    }

    private void trocar(int i, int j) {
        Documento temp = lista.get(i);

        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}