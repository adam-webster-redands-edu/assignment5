package webster;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {

    private JFrame frame;
    private CustomLinkedList linkedList;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() {
        initialize();
        linkedList = new CustomLinkedList();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("File Upload");
        btnNewButton.setBounds(154, 34, 129, 23);
        frame.getContentPane().add(btnNewButton);

        JLabel lblMean = new JLabel("Mean:");
        lblMean.setBounds(83, 75, 46, 23);
        frame.getContentPane().add(lblMean);

        JLabel lblStdDev = new JLabel("Standard Deviation:");
        lblStdDev.setBounds(256, 75, 129, 23);
        frame.getContentPane().add(lblStdDev);

        JLabel lblMeanResult = new JLabel("");
        lblMeanResult.setBounds(83, 109, 92, 57);
        frame.getContentPane().add(lblMeanResult);

        JLabel lblStdDevResult = new JLabel("");
        lblStdDevResult.setBounds(266, 109, 102, 57);
        frame.getContentPane().add(lblStdDevResult);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        String line;
                        while ((line = br.readLine()) != null) {
                            double num = Double.parseDouble(line);
                            linkedList.add(num);
                        }
                        br.close();
                        
                        // Calculate mean and standard deviation
                        double mean = MeanStdDev.calculateMean(linkedList);
                        double stdDev = MeanStdDev.calculateStdDev(linkedList);

                        // Update the labels with the results
                        lblMeanResult.setText(String.format("%.2f", mean));
                        lblStdDevResult.setText(String.format("%.2f", stdDev));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
