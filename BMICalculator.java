import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("محاسبه شاخص توده بدنی (BMI)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("وزن (کیلوگرم):"), gbc);

        gbc.gridx = 1;
        JTextField weightField = new JTextField(15);
        mainPanel.add(weightField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("قد (سانتی‌متر):"), gbc);

        gbc.gridx = 1;
        JTextField heightField = new JTextField(15);
        mainPanel.add(heightField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton calculateButton = new JButton("محاسبه BMI");
        mainPanel.add(calculateButton, gbc);


        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JLabel bmiResultLabel = new JLabel("BMI: -");
        bmiResultLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        bmiResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(bmiResultLabel, gbc);


        gbc.gridy = 4;
        JLabel statusLabel = new JLabel("وضعیت: -");
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(statusLabel, gbc);


        gbc.gridy = 5;
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(errorLabel, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setText(" ");

                String weightText = weightField.getText().trim();
                String heightText = heightField.getText().trim();

                if (weightText.isEmpty() || heightText.isEmpty()) {
                    errorLabel.setText("لطفاً هر دو فیلد را پر کنید.");
                    return;
                }

                double weight, height;
                try {
                    weight = Double.parseDouble(weightText);
                    height = Double.parseDouble(heightText) / 100;
                } catch (NumberFormatException ex) {
                    errorLabel.setText("لطفاً فقط عدد وارد کنید.");
                    return;
                }

                if (weight <= 0 || height <= 0) {
                    errorLabel.setText("وزن و قد باید مثبت باشند.");
                    return;
                }

                double bmi = weight / (height * height);
                bmiResultLabel.setText(String.format("BMI: %.2f", bmi));

                String status;
                Color statusColor;

                if (bmi < 18.5) {
                    status = "کم وزن";
                    statusColor = Color.BLUE;
                } else if (bmi < 25) {
                    status = "نرمال";
                    statusColor = new Color(0, 128, 0); 
                } else if (bmi < 30) {
                    status = "اضافه وزن";
                    statusColor = Color.ORANGE;
                } else {
                    status = "چاق";
                    statusColor = Color.RED;
                }

                statusLabel.setText("وضعیت: " + status);
                statusLabel.setForeground(statusColor);
            }
        });
    }
}