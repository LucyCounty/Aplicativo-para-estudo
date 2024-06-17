import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterScreen extends JFrame {
    private JTextField nomeField;
    private JTextField sobrenomeField;
    private JTextField dobField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField senhaTextField;
    private JCheckBox showPasswordCheckBox;
    private JPasswordField confirmSenhaField;
    private JTextField confirmSenhaTextField;
    private JCheckBox showConfirmPasswordCheckBox;
    private JComboBox<String> nivelEscolaridadeField;
    private JComboBox<String> tipoUsuarioField;
    private JButton registerButton;
    private JButton backButton;

    private JLabel nomeErrorLabel;
    private JLabel sobrenomeErrorLabel;
    private JLabel dobErrorLabel;
    private JLabel cpfErrorLabel;
    private JLabel telefoneErrorLabel;
    private JLabel emailErrorLabel;
    private JLabel senhaErrorLabel;
    private JLabel confirmarSenhaErrorLabel;

    public RegisterScreen() {
        setTitle("Registrar");
        setSize(395, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 120, 25);
        panel.add(nomeLabel);

        nomeField = new JTextField(20);
        nomeField.setBounds(10, 40, 100, 25);
        panel.add(nomeField);

        nomeErrorLabel = new JLabel("");
        nomeErrorLabel.setBounds(10, 60, 200, 25);
        nomeErrorLabel.setForeground(Color.RED);
        panel.add(nomeErrorLabel);

        JLabel sobrenomeLabel = new JLabel("Sobrenome:");
        sobrenomeLabel.setBounds(140, 20, 120, 25);
        panel.add(sobrenomeLabel);

        sobrenomeField = new JTextField(20);
        sobrenomeField.setBounds(140, 40, 200, 25);
        panel.add(sobrenomeField);

        sobrenomeErrorLabel = new JLabel("");
        sobrenomeErrorLabel.setBounds(140, 60, 200, 25);
        sobrenomeErrorLabel.setForeground(Color.RED);
        panel.add(sobrenomeErrorLabel);

        JLabel dobLabel = new JLabel("Data de Nascimento:");
        dobLabel.setBounds(10, 80, 120, 25);
        panel.add(dobLabel);

        dobField = new JTextField(20);
        dobField.setBounds(10, 100, 100, 25);
        panel.add(dobField);

        dobErrorLabel = new JLabel("");
        dobErrorLabel.setBounds(10, 120, 200, 25);
        dobErrorLabel.setForeground(Color.RED);
        panel.add(dobErrorLabel);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(140, 80, 120, 25);
        panel.add(cpfLabel);

        cpfField = new JTextField(20);
        cpfField.setBounds(140, 100, 100, 25);
        panel.add(cpfField);

        cpfErrorLabel = new JLabel("");
        cpfErrorLabel.setBounds(140, 120, 200, 25);
        cpfErrorLabel.setForeground(Color.RED);
        panel.add(cpfErrorLabel);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(270, 80, 120, 25);
        panel.add(telefoneLabel);

        telefoneField = new JTextField(20);
        telefoneField.setBounds(270, 100, 100, 25);
        panel.add(telefoneField);

        telefoneErrorLabel = new JLabel("");
        telefoneErrorLabel.setBounds(270, 120, 200, 25);
        telefoneErrorLabel.setForeground(Color.RED);
        panel.add(telefoneErrorLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 140, 120, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(10, 160, 200, 25);
        panel.add(emailField);

        emailErrorLabel = new JLabel("");
        emailErrorLabel.setBounds(10, 180, 300, 25);
        emailErrorLabel.setForeground(Color.RED);
        panel.add(emailErrorLabel);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 200, 120, 25);
        panel.add(senhaLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBounds(10, 220, 200, 25);
        panel.add(senhaField);

        senhaTextField = new JTextField(20);
        senhaTextField.setBounds(10, 220, 200, 25);
        senhaTextField.setVisible(false);
        panel.add(senhaTextField);

        showPasswordCheckBox = new JCheckBox();
        showPasswordCheckBox.setBounds(211, 220, 20, 25);
        panel.add(showPasswordCheckBox);

        senhaErrorLabel = new JLabel("");
        senhaErrorLabel.setBounds(10, 240, 200, 25);
        senhaErrorLabel.setForeground(Color.RED);
        panel.add(senhaErrorLabel);

        JLabel confirmSenhaLabel = new JLabel("Confirmar Senha:");
        confirmSenhaLabel.setBounds(10, 260, 120, 25);
        panel.add(confirmSenhaLabel);

        confirmSenhaField = new JPasswordField(20);
        confirmSenhaField.setBounds(10, 280, 200, 25);
        panel.add(confirmSenhaField);

        confirmSenhaTextField = new JTextField(20);
        confirmSenhaTextField.setBounds(10, 280, 200, 25);
        confirmSenhaTextField.setVisible(false);
        panel.add(confirmSenhaTextField);

        showConfirmPasswordCheckBox = new JCheckBox();
        showConfirmPasswordCheckBox.setBounds(211, 280, 20, 25);
        panel.add(showConfirmPasswordCheckBox);

        confirmarSenhaErrorLabel = new JLabel("");
        confirmarSenhaErrorLabel.setBounds(10, 300, 200, 25);
        confirmarSenhaErrorLabel.setForeground(Color.RED);
        panel.add(confirmarSenhaErrorLabel);

        JLabel nivelEscolaridadeLabel = new JLabel("Nível de Escolaridade:");
        nivelEscolaridadeLabel.setBounds(10, 320, 120, 25);
        panel.add(nivelEscolaridadeLabel);

        String[] niveisEscolaridade = {"Ensino fundamental", "Ensino Médio", "Faculdade", "Mestrado", "Doutorado"};
        nivelEscolaridadeField = new JComboBox<>(niveisEscolaridade);
        nivelEscolaridadeField.setBounds(10, 340, 200, 25);
        panel.add(nivelEscolaridadeField);

        JLabel tipoUsuarioLabel = new JLabel("Tipo de Usuário:");
        tipoUsuarioLabel.setBounds(10, 380, 120, 25);
        panel.add(tipoUsuarioLabel);

        String[] tiposUsuario = {"Aluno", "Professor"};
        tipoUsuarioField = new JComboBox<>(tiposUsuario);
        tipoUsuarioField.setBounds(10, 400, 200, 25);
        panel.add(tipoUsuarioField);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(10, 450, 120, 25);
        panel.add(registerButton);

        backButton = new JButton("Voltar");
        backButton.setBounds(245, 450, 120, 25);
        panel.add(backButton);

        registerButton.addActionListener(new RegisterButtonListener());
        backButton.addActionListener(new BackButtonListener());
        showPasswordCheckBox.addActionListener(new ShowPasswordCheckBoxListener());
        showConfirmPasswordCheckBox.addActionListener(new ShowConfirmPasswordCheckBoxListener());
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean hasErrors = false;

            String senha;
            String confirmarSenha;

            if (showPasswordCheckBox.isSelected()) {
                senha = senhaTextField.getText();
            } else {
                senha = new String(senhaField.getPassword());
            }
            
            if (showConfirmPasswordCheckBox.isSelected()) {
                confirmarSenha = confirmSenhaTextField.getText();
            } else {
                confirmarSenha = new String(confirmSenhaField.getPassword());
            }
            
            nomeErrorLabel.setText("");
            sobrenomeErrorLabel.setText("");
            dobErrorLabel.setText("");
            cpfErrorLabel.setText("");
            telefoneErrorLabel.setText("");
            emailErrorLabel.setText("");
            senhaErrorLabel.setText("");
            confirmarSenhaErrorLabel.setText("");

            if (nomeField.getText().trim().isEmpty()) {
                nomeErrorLabel.setText("Digite o nome.");
                hasErrors = true;
            }
            if (sobrenomeField.getText().trim().isEmpty()) {
                sobrenomeErrorLabel.setText("Digite o sobrenome.");
                hasErrors = true;
            }
            if (dobField.getText().trim().isEmpty()) {
                dobErrorLabel.setText("Digite a data.");
                hasErrors = true;
            }
            if (cpfField.getText().trim().isEmpty()) {
                cpfErrorLabel.setText("Digite o CPF.");
                hasErrors = true;
            }
            if (telefoneField.getText().trim().isEmpty()) {
                telefoneErrorLabel.setText("Digite o telefone.");
                hasErrors = true;
            }
            if (emailField.getText().trim().isEmpty()) {
                emailErrorLabel.setText("Digite o email.");
                hasErrors = true;
            } else if (isEmailRegistered(emailField.getText().trim(), (String) tipoUsuarioField.getSelectedItem())) {
                emailErrorLabel.setText("Email já registrado para esse tipo de usuário.");
                hasErrors = true;
            }
            if (senha.trim().isEmpty()) {
                senhaErrorLabel.setText("Digite a senha.");
                hasErrors = true;
            }
            if (confirmarSenha.trim().isEmpty()) {
                confirmarSenhaErrorLabel.setText("Confirme a senha.");
                hasErrors = true;
            }
            if (!senha.equals(confirmarSenha)) {
                confirmarSenhaErrorLabel.setText("Senhas não coincidem!");
                hasErrors = true;
            }

            if (hasErrors) {
                return;
            }

            String nome = nomeField.getText();
            String sobrenome = sobrenomeField.getText();
            String dob = dobField.getText();
            String cpf = cpfField.getText();
            String telefone = telefoneField.getText();
            String email = emailField.getText();
            senha = showPasswordCheckBox.isSelected() ? senhaTextField.getText() : new String(senhaField.getPassword());
            String nivelEscolaridade = (String) nivelEscolaridadeField.getSelectedItem();
            String tipoUsuario = (String) tipoUsuarioField.getSelectedItem();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                writer.write(nome + "," + sobrenome + "," + dob + "," + cpf + "," + telefone + "," + email + "," + senha + "," + nivelEscolaridade + "," + tipoUsuario);
                writer.newLine();
                writer.flush();
                JOptionPane.showMessageDialog(null, "Usuário " + nome + " registrado com sucesso!");

                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean isEmailRegistered(String email, String tipoUsuario) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 8 && parts[5].equals(email) && parts[8].equals(tipoUsuario)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
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

    private class ShowConfirmPasswordCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (showConfirmPasswordCheckBox.isSelected()) {
                confirmSenhaTextField.setText(new String(confirmSenhaField.getPassword()));
                confirmSenhaTextField.setVisible(true);
                confirmSenhaField.setVisible(false);
            } else {
                confirmSenhaField.setText(confirmSenhaTextField.getText());
                confirmSenhaField.setVisible(true);
                confirmSenhaTextField.setVisible(false);
            }
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
            dispose();
        }
    }
}