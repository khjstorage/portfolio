package DBConnectionExam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import java.awt.*;

public class AddressBook extends JFrame implements ActionListener {
    JLabel label, id, name, phone, address, company;
    JTextField fid, fname, fphone, faddress, fcompany, search;
    JButton add, delete, update, clear, all, sbutton;
    String items[] = {"이름", "주소", "회사"};
    JComboBox combo;
    JPanel bottomPanel, leftPanel, center, pid, pname, pphone, padd, pcom;

    JScrollPane sp;
    Vector outer, title, noresult, msg;
    JTable table;
    DefaultTableModel model;

    String connect = "jdbc:oracle:thin:@127.0.0.1:1521";
    String user = "soldesk";
    String passwd = "1234";

    Connection conn;
    Statement stat;
    PreparedStatement prestat1, prestat2, prestat3;
    ResultSet rs;

    public AddressBook(){
        super("주소록관리");
        makeGUI();   // 화면구성
        prepareDB();   //DB 연결
        select(null);

        model.setDataVector(outer, title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        try{
            if(o==add){
                if(fid.getText().length()>0)
                    add();
                select(null);
            }else if(o==delete){
                if(fid.getText().length()>0)
                    delete();
                select(null);
            }else if(o==clear){
                clear();
            }else if(o==all){
                select(null);
            }else if(o==update){
                if(fid.getText().length()>0)
                    update();
                select(null);
            }else if(o==search || o==sbutton){
                search();
            }

            if(outer.isEmpty()){
                outer.clear();
                msg.clear();

                msg.add("찾은 데이터가 없습니다...");
                outer.add(msg);
                model.setDataVector(outer, noresult);
            }else{
                model.setDataVector(outer, title);
            }

            clear();

        }catch(Exception ew){
            ew.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AddressBook f = new AddressBook();
        f.pack();
        f.setVisible(true);
    }

    // 화면 구성 메소드
    public void makeGUI(){
        label = new JLabel("Address Book", JLabel.CENTER);
        id = new JLabel("   I D : ", JLabel.CENTER);

        fid = new JTextField(15);
        pid = new JPanel();
        pid.add(id);
        pid.add(fid);

        name = new JLabel("이름: ");
        fname = new JTextField(15);
        pname = new JPanel();
        pname.add(name);
        pname.add(fname);

        phone = new JLabel("전화: ");
        fphone = new JTextField(15);
        pphone = new JPanel();
        pphone.add(phone);
        pphone.add(fphone);

        address = new JLabel("주소: ");
        faddress = new JTextField(15);
        padd = new JPanel();
        padd.add(address);
        padd.add(faddress);

        company = new JLabel("회사: ");
        fcompany = new JTextField(15);
        pcom = new JPanel();
        pcom.add(company);
        pcom.add(fcompany);

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1));
        leftPanel.add(pid);
        leftPanel.add(pname);
        leftPanel.add(pphone);
        leftPanel.add(padd);
        leftPanel.add(pcom);

        add = new JButton("추가");
        add.addActionListener(this);
        delete = new JButton("삭제");
        delete.addActionListener(this);
        update = new JButton("수정");
        update.addActionListener(this);

        combo = new JComboBox(items);

        search = new JTextField(15);
        search.addActionListener(this);

        sbutton = new JButton("검색");
        sbutton.addActionListener(this);

        clear = new JButton("지우기");
        clear.addActionListener(this);

        all = new JButton("전체보기");
        all.addActionListener(this);

        bottomPanel = new JPanel();
        bottomPanel.add(add);
        bottomPanel.add(delete);
        bottomPanel.add(update);
        bottomPanel.add(combo);
        bottomPanel.add(search);
        bottomPanel.add(sbutton);
        bottomPanel.add(clear);
        bottomPanel.add(all);

        title = new Vector();
        outer = new Vector();
        noresult = new Vector();
        msg = new Vector();

        title.add("ID");
        title.add("Name");
        title.add("Phone");
        title.add("Address");
        title.add("Company");

        noresult.add("실행결과");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                try{
                    rs.close();
                    stat.close();
                    conn.close();

                    setVisible(false);
                    dispose();
                    System.exit(0);
                }catch(Exception ie){
                    ie.printStackTrace();
                }
            }
        });

        Container c = getContentPane();

        model = new DefaultTableModel();

        table = new JTable(model);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent m){
                // 클릭한 행의 인덱스 알아내기
                int index = table.getSelectedRow();

                //인덱스 이용해서 out안의 작은 벡터 데이타 꺼내기
                Vector msg = (Vector)outer.get(index);

                // in 안에 들어 있는 번호, 이름, 주소 알아내서 텍스트 필드에 넣어주기
                String id = (String)msg.get(0);
                String name = (String)msg.get(1);
                String phone = (String)msg.get(2);
                String address = (String)msg.get(3);
                String company = (String)msg.get(4);

                fid.setText(id);
                fname.setText(name);
                fphone.setText(phone);
                faddress.setText(address);
                fcompany.setText(company);

                //번호가 들어가는 텍스트필드는 편집 불가능한 상태로 만들기
                fid.setEditable(false);
            }
        });

        sp = new JScrollPane(table);

        center = new JPanel();
        center.add(leftPanel);
        center.add(sp);

        c.add(label, "North");
        c.add(center, "Center");
        c.add(bottomPanel, "South");
    }

    //DB 연결 메소드
    public void prepareDB(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(connect, user, passwd);

            stat = conn.createStatement();
            prestat1 = conn.prepareStatement("insert into addressBook values(?, ?, ?, ?, ?)");
            prestat2 = conn.prepareStatement("delete from addressBook where id = ?");
            prestat3 = conn.prepareStatement("update addressBook set name=?, phone= ?, address=?, company=? where id=?");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 추가, 삭제, 수정 작업 메소드
    public void update(){
        String id = fid.getText();
        String name = fname.getText();
        String phone = fphone.getText();
        String address = faddress.getText();
        String company = fcompany.getText();

        try{
            prestat3.setString(1, name);
            prestat3.setString(2, phone);
            prestat3.setString(3, address);
            prestat3.setString(4, company);
            prestat3.setString(5, id);

            prestat3.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }

    // delete 작업하는 메소드
    public void delete(){
        String id = fid.getText();

        try{
            prestat2.setString(1, id);

            prestat2.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }

    // insert 작업하는 메소드
    public void add(){
        String id = fid.getText();
        String name = fname.getText();
        String phone = fphone.getText();
        String address = faddress.getText();
        String company = fcompany.getText();

        try{
            prestat1.setString(1, id);
            prestat1.setString(2, name);
            prestat1.setString(3, phone);
            prestat1.setString(4, address);
            prestat1.setString(5, company);

            prestat1.executeUpdate();
        }catch(Exception e){ e.printStackTrace(); }
    }

    // 검색 조회 메소드
    public void search(){
        String keyword = search.getText();

        int i = combo.getSelectedIndex();
        String index;

        if(i==0){
            index = "name";
        }else if(i==1){
            index = "address";
        }else
        {
            index = "company";
        }

        String p = "select * from addressBook where "+index+"='"+keyword+"' order by id";
        select(p);
        search.setText("");
    }

    public void select(String query){
        try{
            if(query==null)
                query = "select * from addressBook order by id";

            rs = stat.executeQuery(query);

            outer.clear();
            while(rs.next()){
                msg = new Vector<String>();

                msg.add(rs.getString(1));
                msg.add(rs.getString(2));
                msg.add(rs.getString(3));
                msg.add(rs.getString(4));
                msg.add(rs.getString(5));

                outer.add(msg);
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void clear(){
        fid.setText("");
        fname.setText("");
        fphone.setText("");
        faddress.setText("");
        fcompany.setText("");

        fid.setEditable(true);
    }
}
