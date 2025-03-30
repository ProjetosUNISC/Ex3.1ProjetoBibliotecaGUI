import javax.swing.*;
import java.awt.event.*;

public class Listener implements ActionListener {


    private JTextField txtTitulo;

    public Listener(JTextField txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("OK")) {
            String titulo = txtTitulo.getText();
            JOptionPane.showMessageDialog(null, "Livro salvo com sucesso!");
        }

        else {
            System.exit(0);
        }
    }
}