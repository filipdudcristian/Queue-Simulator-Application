package gui;

import businesslogic.QueueManagement;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {

    private JFrame frame;
    private JLabel nrQueuesLabel;
    private JTextField textNrQueues;
    private JTextField textNrClients;
    private JTextField textMinArrivalTime;
    private JTextField textMaxArrivalTime;
    private JLabel lblMinArrivalTime_1;
    private JTextField textMinProcessingTime;
    private JLabel lblMaxArrivalTime_1;
    private JTextField textMaxProcessingTime;
    private JLabel lblTimeLimit;
    private JTextField textTimeLimit;
    private JTextArea textArea;
    private JLabel lblCurrentTime;
    int nrQueues, nrClients, timeLimit, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime;
    Thread t;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interface window = new Interface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Interface() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(83, 193, 169));
        frame.setBounds(100, 100, 970, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        nrQueuesLabel = new JLabel();
        nrQueuesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nrQueuesLabel.setText("Nr of queues");
        nrQueuesLabel.setBounds(44, 22, 112, 47);
        frame.getContentPane().add(nrQueuesLabel);

        textNrQueues = new JTextField();
        textNrQueues.setBounds(42, 61, 110, 19);
        frame.getContentPane().add(textNrQueues);
        textNrQueues.setColumns(10);

        JLabel lblNrOfClients = new JLabel();
        lblNrOfClients.setText("Nr of clients");
        lblNrOfClients.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNrOfClients.setBounds(219, 22, 112, 47);
        frame.getContentPane().add(lblNrOfClients);

        textNrClients = new JTextField();
        textNrClients.setBounds(219, 61, 112, 19);
        frame.getContentPane().add(textNrClients);
        textNrClients.setColumns(10);

        JLabel lblMinArrivalTime = new JLabel();
        lblMinArrivalTime.setText("Min arrival time");
        lblMinArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMinArrivalTime.setBounds(486, 22, 145, 47);
        frame.getContentPane().add(lblMinArrivalTime);

        textMinArrivalTime = new JTextField();
        textMinArrivalTime.setColumns(10);
        textMinArrivalTime.setBounds(486, 61, 120, 19);
        frame.getContentPane().add(textMinArrivalTime);

        JLabel lblMaxArrivalTime = new JLabel();
        lblMaxArrivalTime.setText("Max arrival time");
        lblMaxArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMaxArrivalTime.setBounds(664, 22, 145, 47);
        frame.getContentPane().add(lblMaxArrivalTime);

        textMaxArrivalTime = new JTextField();
        textMaxArrivalTime.setColumns(10);
        textMaxArrivalTime.setBounds(664, 61, 120, 19);
        frame.getContentPane().add(textMaxArrivalTime);

        lblMinArrivalTime_1 = new JLabel();
        lblMinArrivalTime_1.setText("Min processing time");
        lblMinArrivalTime_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMinArrivalTime_1.setBounds(463, 90, 168, 47);
        frame.getContentPane().add(lblMinArrivalTime_1);

        textMinProcessingTime = new JTextField();
        textMinProcessingTime.setColumns(10);
        textMinProcessingTime.setBounds(486, 129, 120, 19);
        frame.getContentPane().add(textMinProcessingTime);

        lblMaxArrivalTime_1 = new JLabel();
        lblMaxArrivalTime_1.setText("Max processing time");
        lblMaxArrivalTime_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMaxArrivalTime_1.setBounds(653, 90, 168, 47);
        frame.getContentPane().add(lblMaxArrivalTime_1);

        textMaxProcessingTime = new JTextField();
        textMaxProcessingTime.setColumns(10);
        textMaxProcessingTime.setBounds(664, 129, 120, 19);
        frame.getContentPane().add(textMaxProcessingTime);

        lblTimeLimit = new JLabel();
        lblTimeLimit.setForeground(new Color(0, 0, 0));
        lblTimeLimit.setText("Time limit");
        lblTimeLimit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTimeLimit.setBounds(44, 90, 112, 47);
        frame.getContentPane().add(lblTimeLimit);

        textTimeLimit = new JTextField();
        textTimeLimit.setColumns(10);
        textTimeLimit.setBounds(42, 129, 110, 19);
        frame.getContentPane().add(textTimeLimit);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(20, 294, 917, 359);
        frame.getContentPane().add(scrollPane1);
        scrollPane1.setViewportView(textArea);
        textArea.setBounds(20, 294, 917, 359);

        lblCurrentTime = new JLabel();
        lblCurrentTime.setText("Current time:");
        lblCurrentTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCurrentTime.setBounds(331, 147, 177, 47);
        frame.getContentPane().add(lblCurrentTime);

        JTextArea textAreaClients = new JTextArea();
        textAreaClients.setEditable(false);
        textAreaClients.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(20, 202, 917, 70);
        frame.getContentPane().add(scrollPane2);
        scrollPane2.setViewportView(textAreaClients);
        textAreaClients.setBounds(20, 202, 917, 70);

        JButton btnStart = new JButton("START");
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnStart.setBounds(850, 44, 87, 47);
        frame.getContentPane().add(btnStart);
        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timeLimit = Integer.parseInt(textTimeLimit.getText());
                    nrQueues = Integer.parseInt(textNrQueues.getText());
                    nrClients = Integer.parseInt(textNrClients.getText());
                    minArrivalTime = Integer.parseInt(textMinArrivalTime.getText());
                    maxArrivalTime = Integer.parseInt(textMaxArrivalTime.getText());
                    minServiceTime = Integer.parseInt(textMinProcessingTime.getText());
                    maxServiceTime = Integer.parseInt(textMaxProcessingTime.getText());

                    if(timeLimit < 0 || nrQueues < 0 || minArrivalTime < 0 || maxArrivalTime < 0 || minServiceTime < 0 || maxServiceTime < 0)
                        throw new Exception();
                    if(maxArrivalTime < minArrivalTime || maxServiceTime < minServiceTime || maxArrivalTime > timeLimit)
                        throw new Exception();

                    btnStart.setEnabled(false);

                    QueueManagement sim = new QueueManagement(timeLimit, nrClients, nrQueues, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, textArea, textAreaClients ,lblCurrentTime, btnStart);

                    t = new Thread(sim);
                    t.start();

                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Date de intrare invalide!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

}
