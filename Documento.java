class Documento {
    String nome;
    int prioridade;
    int ordemChegada;

    public Documento(String nome, int prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", prioridade, nome);
    }
}