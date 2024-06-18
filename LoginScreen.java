import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginScreen extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField senhaTextField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;
    private JButton registerButton;
    private JComboBox<String> tipoUsuarioField;

    public LoginScreen() {
        setTitle("Logar");
        setSize(250, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 10, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(10, 30, 200, 25);
        panel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 60, 80, 25);
        panel.add(senhaLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBounds(10, 80, 200, 25);
        panel.add(senhaField);

        senhaTextField = new JTextField(20);
        senhaTextField.setBounds(10, 80, 200, 25);
        senhaTextField.setVisible(false);
        panel.add(senhaTextField);

        showPasswordCheckBox = new JCheckBox();
        showPasswordCheckBox.setBounds(211, 80, 20, 25);
        panel.add(showPasswordCheckBox);

        tipoUsuarioField = new JComboBox<>(new String[]{"Aluno", "Professor"});
        tipoUsuarioField.setBounds(10, 120, 200, 25);
        panel.add(tipoUsuarioField);

        loginButton = new JButton("Entrar");
        loginButton.setBounds(10, 160, 80, 25);
        panel.add(loginButton);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(120, 160, 90, 25);
        panel.add(registerButton);

        loginButton.addActionListener(new LoginButtonListener());
        registerButton.addActionListener(new RegisterButtonListener());
        showPasswordCheckBox.addActionListener(new ShowPasswordCheckBoxListener());
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String senha = showPasswordCheckBox.isSelected() ? senhaTextField.getText() : new String(senhaField.getPassword());
            String tipoUsuario = (String) tipoUsuarioField.getSelectedItem();

            try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                boolean validUser = false;
                while ((line = reader.readLine()) != null) {
                    String[] userDetails = line.split(",");
                    if (userDetails[5].equals(email) && userDetails[6].equals(senha) && userDetails[8].equals(tipoUsuario)) {
                        validUser = true;
                        if (userDetails[8].equals("Professor")) {
                            ProfessorScreen professorScreen = new ProfessorScreen();
                            professorScreen.setVisible(true);
                        } else {
                            StudentScreen studentScreen = new StudentScreen();
                            studentScreen.setVisible(true);
                        }
                        dispose();
                        break;
                    }
                }
                if (!validUser) {
                    JOptionPane.showMessageDialog(null, "Email ou senha está incorreto, ou o tipo de usuário está errado \"" + tipoUsuario + "\"");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterScreen registerScreen = new RegisterScreen();
            registerScreen.setVisible(true);
            dispose();
        }
    }

    private class ShowPasswordCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (showPasswordCheckBox.isSelected()) {
                senhaTextField.setText(new String(senhaField.getPassword()));
                senhaTextField.setVisible(true);
                senhaField.setVisible(false);
            } else {
                senhaField.setText(senhaTextField.getText());
                senhaField.setVisible(true);
                senhaTextField.setVisible(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}