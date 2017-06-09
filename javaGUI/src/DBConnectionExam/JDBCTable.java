package DBConnectionExam;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Administrator on 2017-06-09.
 */
public class JDBCTable extends JFrame implements ActionListener {

    Vector out, title;
    JTable table;
    DefaultTableModel model;
    JButton add, del, update, clear;
    JTextField tnum, tname, taddres;
    JLabel lnum, lname, laddres;

    String conncet = "jdbc:oracle:thin:@127.0.0.1:1521";
    String user = "soldesk";
    String passwd = "1234";

    Connection conn;
    Statement stat;
    PreparedStatement pin, pdel, pup;

    public JDBCTable() {
        super("TableDemo");
        prepareDB();
        out = new Vector();
        title = new Vector();
        title.add("num");
        title.add("name");
        title.add("address");

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        Vector result = selectAll();
        model.setDataVector(result, title);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = table.getSelectedRow();
                Vector in = (Vector) out.get(index);

                String num = (String) in.get(0);
                String name = (String) in.get(1);
                String address = (String) in.get(2);

                tnum.setText(num);
                tname.setText(name);
                taddres.setText(address);
                tnum.setEditable(false);
            }
        });

        JPanel panel = new JPanel();

        tnum = new JTextField(8);
        tname = new JTextField(10);
        taddres = new JTextField(8);

        lnum = new JLabel("num");
        lname = new JLabel("name");
        laddres = new JLabel("address");

        add = new JButton("add");
        del = new JButton("del");
        update = new JButton("update");
        clear = new JButton("clear");

        add.addActionListener(this);
        del.addActionListener(this);
        update.addActionListener(this);
        clear.addActionListener(this);

        panel.add(lnum);
        panel.add(tnum);
        panel.add(lname);
        panel.add(tname);
        panel.add(laddres);
        panel.add(taddres);
        panel.add(add);
        panel.add(del);
        panel.add(update);
        panel.add(clear);

        Container c = getContentPane();
        c.add(new JLabel("TableDemo", JLabel.CENTER), "North");
        c.add(scrollPane, BorderLayout.CENTER);
        c.add(panel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    stat.close();
                    pin.close();
                    pdel.close();
                    pup.close();
                    conn.close();
                    setVisible(false);
                    dispose();
                    System.exit(0);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o != null) {
            if (o == add) {

                if (tnum.getText().equals("")){
                    showMessageDialog(null, "num을 입력해주세요");
                }

                for (int i = 0; i < selectNum().size(); i++) {
                    if (tnum.getText().equals(selectNum().get(i))) {
                        showMessageDialog(null, "중복된 num값 입니다");
                    }
                }

                String num = tnum.getText();
                String name = tname.getText();
                String address = taddres.getText();
                insert(num, name, address);
            }
            if (o == del) {
                String num = tnum.getText();
                delete(num);
            }
            if (o == update) {
                String num = tnum.getText();
                String name = tname.getText();
                String address = taddres.getText();
                update(name, address, num);
            }

            Vector result = selectAll();
            model.setDataVector(result, title);
        }

        tnum.setText("");
        tname.setText("");
        taddres.setText("");
        tnum.setEditable(true);
    }

    public static void main(String[] args) {
        JDBCTable jdbcTable = new JDBCTable();
        jdbcTable.pack();
        jdbcTable.setVisible(true);


    }

    public void prepareDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(conncet, user, passwd);
            stat = conn.createStatement();
            pin = conn.prepareStatement("INSERT INTO CUSTOMER VALUES(?,?,?)");
            pdel = conn.prepareStatement("DELETE FROM CUSTOMER WHERE NUM = ?");
            pup = conn.prepareStatement("UPDATE CUSTOMER SET NAME=?, ADDRESS=? WHERE NUM=?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vector selectAll() {
        out.clear();
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM CUSTOMER ORDER BY TO_NUMBER(NUM)");

            while (rs.next()) {
                Vector<String> in = new Vector<String>();
                String num = rs.getString("num");
                String name = rs.getString("name");
                String address = rs.getString("address");
                in.add(num);
                in.add(name);
                in.add(address);
                out.add(in);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    private ArrayList selectNum() {
        ArrayList list = new ArrayList<String>();
        try {
            ResultSet rs = stat.executeQuery("SELECT num FROM CUSTOMER");
            while (rs.next()) {
                String num = rs.getString("num");
                list.add(num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    private void update(String name, String address, String num) {
        try {
            pup.setString(1, name);
            pup.setString(2, address);
            pup.setString(3, num);
            pup.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete(String num) {
        try {
            pdel.setString(1, num);
            pdel.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(String num, String name, String address) {
        try {
            pin.setString(1, num);
            pin.setString(2, name);
            pin.setString(3, address);
            pin.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
