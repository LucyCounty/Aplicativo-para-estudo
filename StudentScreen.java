import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class StudentScreen extends JFrame {
    private JList<String> scheduleList;

    public StudentScreen() {
        setTitle("Tela do Aluno");
        setSize(1000, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Bem-vindo, Aluno!");
        panel.add(label, BorderLayout.NORTH);

        scheduleList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(scheduleList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton selectButton = new JButton("Selecionar Aula");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = scheduleList.getSelectedValue();
                if (selectedClass != null && !selectedClass.isEmpty()) {
                    String[] classInfo = selectedClass.split("\"");
                    String subject = classInfo[7];
                    String startTime = classInfo[9];
                    String endTime = classInfo[11];

                    String studentName = showInputDialog("Digite seu nome:");
                    if (studentName == null || studentName.isEmpty()) {
                        return;
                    }

                    String studentPhoneNumber = showInputDialog("Digite seu número de telefone:");
                    if (studentPhoneNumber == null || studentPhoneNumber.isEmpty()) {
                        return;
                    }

                    saveRequest("solicitacoes.txt", studentName, studentPhoneNumber, subject, startTime, endTime);
                    JOptionPane.showMessageDialog(null, "Solicitação enviada ao professor.");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma aula.");
                }
            }
        });
        panel.add(selectButton, BorderLayout.SOUTH);

        add(panel);

        loadSchedule("aulas_marcadas.txt");

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
    

    private void loadSchedule(String fileName) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                listModel.addElement(line);
            }
            scheduleList.setModel(listModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRequest(String fileName, String studentName, String studentPhoneNumber, String subject, String startTime, String endTime) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(studentName + " | " + studentPhoneNumber + " | " + subject + " | " + startTime + " | "+ endTime);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String showInputDialog(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message);
        JTextField textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(200, 24));
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(errorLabel, BorderLayout.SOUTH);

        String[] options = {"Confirmar", "Cancelar"};
        int option;
        do {
            option = JOptionPane.showOptionDialog(null, panel, "Input", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (option == 0 && textField.getText().trim().isEmpty()) {
                errorLabel.setText("Este campo é obrigatório.");
            } else {
                errorLabel.setText(" ");
            }
        } while (option == 0 && textField.getText().trim().isEmpty());

        if (option == 0) {
            return textField.getText();
        } else {
            return null;
        }
    }
}