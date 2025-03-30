import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Frame extends JFrame {


    /**
     * VARIAVEIS GLOBAIS
     */
    private CardLayout cardLayout;
    private JPanel painelCentral;
    ArrayList<Biblioteca> bibliotecas = new ArrayList<>();



    private Component criarBotaoFormatado(String text) {

        JButton botao = new JButton(text);

        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(200, 40));
        botao.setPreferredSize(new Dimension(200, 40));
        botao.setMinimumSize(new Dimension(200, 40));

        return botao;
    }


    public Frame() {


        /**
         * CONFIGURAÇÕES DA JANELA
         */
        super("Exercicio 3");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(34, 34, 34)); // Azul escuro
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        //layout para o conteudo da janela para layout de borda
        this.setLayout(new BorderLayout());


        /**
         * CRIAÇÃO DO TITULO FIXO NA JANELA USANDO BORDER LAYOUT
         */
        JLabel titulo = new JLabel("Gerenciador de Livros", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        this.add(titulo, BorderLayout.NORTH); //add o titulo na tela principal


        /**
         *
         *  ===ORGANIZAÇÃO TELAs CENTRAIS===
         */
        cardLayout = new CardLayout();
        painelCentral = new JPanel(cardLayout);
        painelCentral.setBackground(new Color(34, 34, 34));
        this.add(painelCentral, BorderLayout.CENTER);


        /**
         * GERENCIAMENTO DAS TELAS
         */
        JPanel telaMenu = PainelMenu(); ///menu principal
        JPanel telaBibliotecaCriar = CriarBiblioteca(); ///criar biblioteca
        JPanel telaBibliotecaEntrar = EntrarBilioteca(); ///entrar biblioteca

        //Menu gerenciaLivro
        JPanel telaCriarLivro = AdicionarLivro(); ///adiciona livro


        //ADICIONA AS TELAS
        painelCentral.add(telaMenu, "Menu"); ///menu principal
        painelCentral.add(telaBibliotecaCriar, "criarBiblioteca");
        painelCentral.add(telaBibliotecaEntrar, "entrarBiblioteca");


        //Menu gerenciaLivro
        painelCentral.add(telaCriarLivro, "criarLivro");


        this.setVisible(true);


        // Força a mudança para o painel "Menu" após o carregamento completo
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardLayout.show(painelCentral, "Menu"); // Garante que o menu apareça inicialmente
            }
        });
    }



    /**
     * PARTE MENU PRINCIPAL
     */
    private JPanel PainelMenu() {


        JPanel painelMenu = new JPanel();
        painelMenu.setBackground(new Color(34, 34, 34));
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);


        /**
         * Adiciona os botões no menu
         */
        //espaço antes do primeiro botao
        painelMenu.add(Box.createRigidArea(new Dimension(0, 50)));


        ///BOTÃO CRIAR BIBLIOTECA E SUA AÇÃO
        JButton botaoCriar = new JButton("Criar uma biblioteca");
        botaoCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelCentral, "criarBiblioteca");
            }
        });
        botaoCriar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelMenu.add(botaoCriar);
        painelMenu.add(Box.createRigidArea(new Dimension(0, 10)));


        ///BOTÃO ENTRAR na BIBLIOTECA E SUA AÇÃO
        JButton botaoEntrar = new JButton("Entrar em uma biblioteca");
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica se a lista de bibliotecas está vazia
                if (bibliotecas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhuma biblioteca cadastrada.");
                    cardLayout.show(painelCentral, "Menu");
                    //return new JPanel();  // Retorna um painel vazio
                }
                cardLayout.show(painelCentral, "entrarBiblioteca");
            }
        });
        botaoEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelMenu.add(botaoEntrar);
        painelMenu.add(Box.createRigidArea(new Dimension(0, 10)));


        /// BOTÃO EXCLUIR UMA BIBLIOTECA E SUA AÇÃO
        painelMenu.add(criarBotaoFormatado("Excluir uma biblioteca"));


        this.add(painelMenu, BorderLayout.CENTER);//add o titulo na tela principal


        return painelMenu;
    }


    /**
     * BOTÃO DE CRIAR BIBLIOTECA
     */
    private JPanel CriarBiblioteca() {


        // Cria o painel para a tela
        JPanel painelBiblioteca = new JPanel();
        painelBiblioteca.setBackground(new Color(34, 34, 34));  // Cor de fundo
        painelBiblioteca.setLayout(new BoxLayout(painelBiblioteca, BoxLayout.Y_AXIS));  // Layout vertical


        //Cria o campo de texto para o user
        JTextField novaBiblioteca = new JTextField();
        novaBiblioteca.setEditable(true);  // O campo pode ser editado
        novaBiblioteca.setColumns(10);  // Largura do campo de texto
        novaBiblioteca.setAlignmentX(Component.CENTER_ALIGNMENT);  // Alinha centro
        novaBiblioteca.setMaximumSize(new Dimension(200, 40));  // tamanho máximo
        novaBiblioteca.setPreferredSize(new Dimension(200, 40));  // tamanho preferido
        novaBiblioteca.setMinimumSize(new Dimension(200, 40));  // tamanho mínimo


        painelBiblioteca.add(novaBiblioteca); // Adiciona o campo de texto ao painel


        painelBiblioteca.add(Box.createRigidArea(new Dimension(0, 40))); // Adiciona um espaco caras


        /**
         * B O T A O DE C O N F I R M A R
         */
        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);  // Alinha no centro
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeBiblioteca = novaBiblioteca.getText();  // Pega o texto digitado
                JOptionPane.showMessageDialog(painelBiblioteca, "Biblioteca '" + nomeBiblioteca + "' criada!");
                bibliotecas.add(new Biblioteca(nomeBiblioteca));
                novaBiblioteca.setText("");  // Limpa o campo de texto
                cardLayout.show(painelCentral, "Menu");
            }
        });



        /**
         * B O T A O DE C A N C E L A R
         */
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaBiblioteca.setText("");  // Limpa o campo de texto
                JOptionPane.showMessageDialog(painelBiblioteca, "Criação cancelada.");
                cardLayout.show(painelCentral, "Menu");
            }
        });

        // Adiciona os botões ao painel
        painelBiblioteca.add(botaoConfirmar);
        painelBiblioteca.add(Box.createRigidArea(new Dimension(0, 10)));  // Espaço entre os botões
        painelBiblioteca.add(botaoCancelar);


        return painelBiblioteca;  // Retorna o painel pronto
    }


    /**
     *BOTÃO DE ENTRAR NA BIBLIOTECA
     */

    private JPanel EntrarBilioteca() {

        // CONFIGURAÇÃO PADRÃO DO LAYOUT
        JPanel entrarBiblioteca = new JPanel();
        entrarBiblioteca.setBackground(new Color(34, 34, 34));
        entrarBiblioteca.setLayout(new BorderLayout());
        entrarBiblioteca.setPreferredSize(new Dimension(400, 300)); // Definindo o tamanho do painel


        // CONFIGURAÇÃO DA LISTA DE BIBLIOTECAS
        String[] listaBibliotecas = bibliotecas.toArray(new String[0]);

        JList<String> lista = new JList<>(listaBibliotecas);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setLayoutOrientation(JList.VERTICAL);

        lista.setForeground(Color.BLACK);
        lista.setBackground(new Color(34, 34, 34));

        JScrollPane scroll = new JScrollPane(lista);


        entrarBiblioteca.add(scroll, BorderLayout.CENTER);


        /**
         * B O T A O DE C A N C E L A R
         */
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(entrarBiblioteca, "Criação cancelada.");
                cardLayout.show(painelCentral, "Menu");
            }
        });


        // Adiciona os botões ao painel
        //entrarBiblioteca.add(botaoConfirmar);
        entrarBiblioteca.add(botaoCancelar, BorderLayout.SOUTH);



        return entrarBiblioteca;
    }


    /**
     * SUBMENU da biblioteca
     */
    private JPanel MenuLivro() {

        JPanel menuLivro = new JPanel();
        menuLivro.setBackground(new Color(34, 34, 34));
        menuLivro.setLayout(new BorderLayout());

        return menuLivro;
    }







    /**
     * Parte submenu adicionar livro
     */
    private JPanel AdicionarLivro() {


        // Painel para os campos de texto
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10));  // Layout de grade (6 linhas e 2 colunas)
        painel.setBackground(new Color(34, 34, 34));

        // Campos de texto
        painel.add(new JLabel("ID:"));
        JTextField campoID = new JTextField(10);  // Campo pequeno para o ID
        painel.add(campoID);

        painel.add(new JLabel("Título:"));
        JTextField campoTitulo = new JTextField(25);  // Campo maior para o Título
        painel.add(campoTitulo);

        painel.add(new JLabel("Autor:"));
        JTextField campoAutor = new JTextField(20);  // Campo médio para o Autor
        painel.add(campoAutor);

        painel.add(new JLabel("Categoria:"));
        JTextField campoCategoria = new JTextField(15);  // Campo médio para a Categoria
        painel.add(campoCategoria);

        painel.add(new JLabel("Data de Lcto:"));
        JTextField campoDataLcto = new JTextField(10);  // Campo pequeno para Data de Lcto
        painel.add(campoDataLcto);

        // Botões
        JButton botaoOK = new JButton("OK");
        painel.add(botaoOK);

        JButton botaoCancelar = new JButton("Cancelar");
        painel.add(botaoCancelar);

        // Adiciona o painel à janela
        add(painel, BorderLayout.CENTER);


        return painel;
    }






}
