package com.azahri.gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JButton buttonFilter;
    private JTextField textFieldFilter;
    private JTextField textFieldNim;
    private JTextField textFieldNama;
    private JTextField textFieldIpk;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonClear;
    private List<Mahasiswa> arraylistMahasiswa = new ArrayList<>();
    private DefaultListModel defaultListModel = new DefaultListModel();

    class Mahasiswa {
        private String nim;
        private String nama;
        private float ipk;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }
    }

    public MainScreen () {
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = textFieldNim.getText();
                String nama = textFieldNama.getText();
                float ipk = Float.parseFloat(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setIpk(ipk);
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);

                arraylistMahasiswa.add(mahasiswa);
                clearForm();

                fronListMahasiswaToListModel();
            }
        });

        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraylistMahasiswa.size(); i++) {
                    if (arraylistMahasiswa.get(i).getNama().equals(nama)) {
                        Mahasiswa mahasiswa = arraylistMahasiswa.get(i);
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        break;
                    }
                }
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue(). toString();

                for (int i = 0; i < arraylistMahasiswa.size(); i++) {
                   if  (arraylistMahasiswa.get(i).getNama().equals(nama) ) {
                        arraylistMahasiswa.remove(i);
                        break;
                    }
                }

                clearForm();
                fronListMahasiswaToListModel();
            }
        });
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arraylistMahasiswa.size(); i++) {
                    if (arraylistMahasiswa.get(i).getNama().contains(keyword)) {
                        filtered.add(arraylistMahasiswa.get(i).getNama());
                    }
                }

                refreshlistModel(filtered);
            }
        });
    }

    private void fronListMahasiswaToListModel() {
        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i =0; i < arraylistMahasiswa.size(); i++) {
            listNamaMahasiswa.add(
                    arraylistMahasiswa.get(i).getNama()
            );
        }

        refreshlistModel(listNamaMahasiswa);
    }


    private void clearForm() {
        textFieldIpk.setText("");
        textFieldNim.setText("");
        textFieldNama.setText("");
    }

    private void refreshlistModel(List<String> list) {
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListMahasiswa.setModel(defaultListModel);
    }

    public static void main(String [] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
