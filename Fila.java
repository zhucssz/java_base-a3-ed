import java.util.ArrayList;

class Fila {
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Documento> lista = new ArrayList<>();

    public void inserir(Documento doc) {
        lista.add(doc);
        subir(lista.size() - 1);
    }

    public Documento remover() {
        if (lista.isEmpty()) {
            return null;
        }

        Documento raiz = lista.get(0);
        Documento ultimo = lista.remove(lista.size() - 1);

        if (!lista.isEmpty()) {
            lista.set(0, ultimo);
            descer(0);
        }

        return raiz;
    }

    public void exibirFila() {
        if (lista.isEmpty()) {
            System.out.println("| [!] A fila está vazia!\n");
            return;
        }
        
        for (Documento doc : lista) {
            System.out.println(String.format("| %s", doc));
        }
        System.out.println(" ");
    }

    private void subir(int i) {
        while (i > 0) {
            int pai = (i - 1) / 2;
            if (lista.get(i).prioridade < lista.get(pai).prioridade) {
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

            if (esq < tamanho && lista.get(esq).prioridade < lista.get(menor).prioridade)
                menor = esq;
            if (dir < tamanho && lista.get(dir).prioridade < lista.get(menor).prioridade)
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