import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ProfessorScreen extends JFrame {
    private JTextArea scheduledClassesTextArea;

    public ProfessorScreen() {
        setTitle("Tela do Professor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bem-vindo, Professor!");
        panel.add(welcomeLabel, BorderLayout.NORTH);

        scheduledClassesTextArea = new JTextArea();
        scheduledClassesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scheduledClassesTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton registerButton = new JButton("Cadastrar Aulas");
        registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                registerClass();
                }
            }
        );
        panel.add(registerButton, BorderLayout.WEST);
        

        JButton viewButton = new JButton("Visualizar Aulas Marcadas pelos Alunos");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewScheduledClasses();
            }
        });
        panel.add(viewButton, BorderLayout.EAST);

        JButton disconnectButton = new JButton("Desconectar");
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnect();
            }
        });
        panel.add(disconnectButton, BorderLayout.NORTH);

        add(panel);
    }

    private void disconnect() {
        this.dispose();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    private ArrayList<String> loadScheduledClasses(String fileName) {
        ArrayList<String> scheduledClasses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                scheduledClasses.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scheduledClasses;
    }

    private void registerClass() {
        JDialog classDialog = new JDialog(this, "Cadastrar Aula", true);
        classDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        classDialog.setSize(500, 600);
        classDialog.setLocationRelativeTo(null);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy++;
        classDialog.add(new JLabel("Nome do Professor:"), gbc);
        JTextField teacherNameField = new JTextField(20);
        gbc.gridy++;
        classDialog.add(teacherNameField, gbc);

        JLabel teacherNameError = new JLabel("Nome do Professor é obrigatório");
        teacherNameError.setForeground(Color.RED);
        teacherNameError.setVisible(false);
        gbc.gridy++;
        classDialog.add(teacherNameError, gbc);

        gbc.gridy++;
        classDialog.add(new JLabel("Número da Aula:"), gbc);
        JTextField classNumberField = new JTextField(20);
        gbc.gridy++;
        classDialog.add(classNumberField, gbc);

        JLabel classNumberError = new JLabel("Número da Aula é obrigatório");
        classNumberError.setForeground(Color.RED);
        classNumberError.setVisible(false);
        gbc.gridy++;
        classDialog.add(classNumberError, gbc);

        gbc.gridy++;
        classDialog.add(new JLabel("Email:"), gbc);
        JTextField emailField = new JTextField(20);
        gbc.gridy++;
        classDialog.add(emailField, gbc);

        JLabel emailError = new JLabel("Email é obrigatório");
        emailError.setForeground(Color.RED);
        emailError.setVisible(false);
        gbc.gridy++;
        classDialog.add(emailError, gbc);

        gbc.gridy++;
        classDialog.add(new JLabel("Matéria:"), gbc);
        String[] subjects = {"Biológicas", "Exatas", "Humanas"};
        JComboBox<String> subjectComboBox = new JComboBox<>(subjects);
        gbc.gridy++;
        classDialog.add(subjectComboBox, gbc);

        gbc.gridy++;
        classDialog.add(new JLabel("Horário Início (dd/MM/yyyy HH:mm):"), gbc);
        JTextField startTimeField = new JTextField(20);
        gbc.gridy++;
        classDialog.add(startTimeField, gbc);

        JLabel startTimeError = new JLabel("Horário Início é obrigatório");
        startTimeError.setForeground(Color.RED);
        startTimeError.setVisible(false);
        gbc.gridy++;
        classDialog.add(startTimeError, gbc);

        gbc.gridy++;
        classDialog.add(new JLabel("Horário Fim (dd/MM/yyyy HH:mm):"), gbc);
        JTextField endTimeField = new JTextField(20);
        gbc.gridy++;
        classDialog.add(endTimeField, gbc);

        JLabel endTimeError = new JLabel("Horário Fim é obrigatório");
        endTimeError.setForeground(Color.RED);
        endTimeError.setVisible(false);
        gbc.gridy++;
        classDialog.add(endTimeError, gbc);

        gbc.gridy++;
        classDialog.add(new JLabel("Descrição:"), gbc);
        JTextArea descriptionTextArea = new JTextArea(10, 20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        gbc.gridy++;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        classDialog.add(scrollPane, gbc);

        gbc.gridy += gbc.gridheight;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton saveButton = new JButton("Salvar");
        classDialog.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;
                teacherNameError.setVisible(false);
                classNumberError.setVisible(false);
                emailError.setVisible(false);
                startTimeError.setVisible(false);
                endTimeError.setVisible(false);

                if (teacherNameField.getText().isEmpty()) {
                    teacherNameError.setVisible(true);
                    valid = false;
                }
                if (classNumberField.getText().isEmpty()) {
                    classNumberError.setVisible(true);
                    valid = false;
                }
                if (emailField.getText().isEmpty()) {
                    emailError.setVisible(true);
                    valid = false;
                }
                if (startTimeField.getText().isEmpty()) {
                    startTimeError.setVisible(true);
                    valid = false;
                }
                if (endTimeField.getText().isEmpty()) {
                    endTimeError.setVisible(true);
                    valid = false;
                }

                if (valid) {
                    String teacherName = teacherNameField.getText();
                    String classNumber = classNumberField.getText();
                    String email = emailField.getText();
                    String subject = (String) subjectComboBox.getSelectedItem();
                    String startTime = startTimeField.getText();
                    String endTime = endTimeField.getText();
                    String description = descriptionTextArea.getText();

                    String classDetails = "Professor: \"" + teacherName + "\" - Número da aula: \"" + classNumber + "\" - Email: \"" + email + "\" - Matéria: \""
                            + subject + "\" - Horário de Inicio: \"" + startTime + "\" - Horário de termino: \"" + endTime + "\" - Descrição: " + description + "\"\n";

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("aulas_marcadas.txt", true))) {
                        bw.write(classDetails);
                        bw.newLine();
                        bw.flush();
                        JOptionPane.showMessageDialog(classDialog, "Aula cadastrada com sucesso!");
                        classDialog.dispose();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(classDialog, "Erro ao salvar a aula.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    
        classDialog.setVisible(true);
    }

    private void viewScheduledClasses() {
        JDialog viewDialog = new JDialog(this, "Aulas Marcadas pelos Alunos", true);
        viewDialog.setSize(600, 400);
        viewDialog.setLocationRelativeTo(null);
        viewDialog.setLayout(new BorderLayout());
    
        String[] columnNames = {"Aluno", "Telefone", "Matéria", "Horário inicial", "Horário final"};
        ArrayList<String[]> data = new ArrayList<>();
    
        ArrayList<String> scheduledClasses = loadScheduledClasses("solicitacoes.txt");
        
        for (String scheduledClass : scheduledClasses) {
            String[] classDetails = scheduledClass.replaceAll("Aluno: |Telefone: |Matéria: |Horário inicial: |Horário final: ", "").split(" \\| ");
            data.add(classDetails);
        }
    
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(viewDialog, "Não há aulas marcadas para exibir.", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        String[][] tableData = new String[data.size()][];
        tableData = data.toArray(tableData);
    
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        viewDialog.add(scrollPane, BorderLayout.CENTER);
    
        viewDialog.setVisible(true);
    }
}