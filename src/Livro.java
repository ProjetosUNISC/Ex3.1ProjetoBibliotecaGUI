public class Livro {

    private int id;
    private String titulo;
    private String dataPublicacao;
    private String autor;
    private EnumCategoria categoria;



    @Override
    public String toString() {
        return "ID: " + id + ", Título: '" + titulo + "', Autor: " + autor +
                ", Data de Publicação: " + dataPublicacao + ", Categoria: " + categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public EnumCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoria categoria) {
        this.categoria = categoria;
    }
}