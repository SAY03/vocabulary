package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;


public class Window extends JDialog {
    private JPanel contentPane;//контейнер для объектов
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldFind;
    private JTextArea textAreaResult;
    private JTextField textFieldDescription;
    private JTextField textFieldWord;
    private JButton buttonAdd;

    public Window() {
        setContentPane(contentPane);//метод заполнения кадра
        setModal(true);
        setTitle("Словарь");

        buttonOK.addActionListener(new ActionListener() { //механизм обработки вызова
            public void actionPerformed(ActionEvent e) {//обработчик события слушателя ActionListener
                onOK();
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

    }

    private void onOK() {
        String tf = textFieldFind.getText();
        textAreaResult.setText(Main.request(tf));
    }
    private void onAdd() {
        String word = textFieldWord.getText();
        String description = textFieldDescription.getText();

        System.out.println(word+description);
        try {
            Main.insert(word,description);
        } catch (SQLException se){
            se.printStackTrace();
        }

        textFieldWord.setText("");
        textFieldDescription.setText("");

    }
    private void onCancel() {
        dispose();
    }
}

