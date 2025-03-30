import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nome;
    private List<Livro> acervo;


    public Biblioteca(String nome) {
        this.nome = nome;
        this.acervo = new ArrayList<Livro>();
    }

    public void addLivro(String titulo, String autor, String dataPublicacao, EnumCategoria categoria) {
        Livro livro = new Livro();

        livro.setId(acervo.size() + 1); //ordena o id baseado no tamanho
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setDataPublicacao(dataPublicacao);
        livro.setCategoria(categoria);

        acervo.add(livro); // Adiciona à lista da biblioteca
        System.out.println("Livro '" + titulo + "' adicionado com sucesso!");
    }

    public void removerLivro(int id) {
        for (Livro livro : acervo) {
            if (livro.getId() == id) {
                acervo.remove(livro);
                System.out.println("Livro removido com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado!");
    }


    public void listarLivros() {
        if (acervo.isEmpty()) {
            System.out.println("\nA biblioteca está vazia");
        } else {
            System.out.println("Livros na biblioteca '" + nome + "':");
            for (Livro livro : acervo) {
                System.out.println(livro);
            }
        }
    }

    public int contarLivros(){
        return acervo.size();
    }


    @Override
    public String toString() {
        String ret = "Nome: " + this.nome;
        if (!acervo.isEmpty()) {
            ret += "\nAcervo:";
            for (Livro o : acervo) {
                ret += "\n" + o.toString();
            }
        }
        return ret;
    }

    public void limparAcervo() {
        acervo.clear();
        System.out.println("Todos os livros foram removidos da biblioteca.");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}