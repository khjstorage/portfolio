import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class DemoNote extends JFrame {
	//멤버변수 선언
	JTextArea text;
	Container pane;
	JMenuBar nb = new JMenuBar();
	JMenu file, help;
	JMenuItem new1, open1, save1, close1, help1;
	JFileChooser open = new JFileChooser();
	
	public DemoNote(){
		super("MemoNote");
		pane = getContentPane();
		pane.setLayout(new BorderLayout());
		setJMenuBar(nb); // 메뉴바 붙임
		
		// 메뉴 및 메뉴 아이템 설정
		file = new JMenu("파일(F)");
		help = new JMenu("정보(I)");
		
		// 키보드 연상기호 설정
		file.setMnemonic('F');
		help.setMnemonic('I');
		
		//파일 메뉴 내용 생성
		new1 = new JMenuItem("새파일");
		open1 = new JMenuItem("열기");
		save1 = new JMenuItem("저장");
		close1 = new JMenuItem("닫기");
		
		// 메뉴 단축키를 위한 작업
		new1.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));	 // CTRL+N
		open1.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));	 // CTRL+O
		save1.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));	 // CTRL+S
		close1.setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));	 // CTRL+Q
		
		// 정보 메뉴 생성
		help1 = new JMenuItem("정보");
		help1.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));   // CTRL+R
		
		// 상위메뉴에 메뉴 항목들 붙이기
		file.add(new1);
		file.add(open1);
		file.add(save1);
		file.add(close1);
		help.add(help1);
		
		//메뉴바에 상위메뉴 붙이기
		nb.add(file);
		nb.add(help);
		
		// text box 만들기
		text = new JTextArea();
		text.setCaretColor(Color.black);
		text.setSelectedTextColor(Color.white);
		text.setSelectionColor(Color.blue);
		text.setBackground(Color.white);
		
		pane.add(new JScrollPane(text));
		
		// 메뉴에서 새파일 메뉴를 클릭했을 때 발생되는 이벤트 처리
		new1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				text.setText("");
			}
		});
		
		// 메뉴에서 열기를 클릭했을 때 이벤트 처리
		open1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int re = open.showOpenDialog(DemoNote.this);
				
				if(re==JFileChooser.APPROVE_OPTION){
					File file_open = open.getSelectedFile();		// 선택된 파일명을 가져온다.
					
					FileInputStream fis;			// 파일 시스템의 파일 입력 바이트 취급 스트림 클래스 선언
					ByteArrayOutputStream bo;
					try{
						fis = new FileInputStream(file_open);
						bo = new ByteArrayOutputStream();
						int i =0;
						
						while( (i = fis.read()) != -1 ){
							bo.write(i);
						}
						text.setText(bo.toString());
						fis.close();
						bo.close();
					}catch(FileNotFoundException fe){				
					}catch(IOException ie){
						
					}
				}
			}
		});
		
		// 메뉴에서 저장을 클릭했을 때 이벤트 처리
		save1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int re = open.showSaveDialog(DemoNote.this);
				
				if(re == JFileChooser.APPROVE_OPTION){
					File file_save = open.getSelectedFile();		// 저장할 파일명 가져오기
					
					try{
						PrintWriter pw = new PrintWriter(
								new BufferedWriter(new FileWriter(file_save)));
						pw.write(text.getText());
						pw.close();
						text.setText("");
					}catch(FileNotFoundException ie){} catch(IOException fe){}
				}
			}
		});
		
		// 닫기 메뉴 이벤트 처리
		close1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				System.exit(0);
			}
		});
		
		// 정보 메뉴 이벤트 처리
		help1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(pane, "Demo Note 1.0 version");
			}
		});
		
	}// DemoNote 생성자 메서드 끝

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DemoNote note = new DemoNote();
		note.setSize(500, 400);
		note.setVisible(true);
	}

}











