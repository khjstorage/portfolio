import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.MessageFormat;
import java.util.Scanner;

public class TextTool extends Frame implements WindowListener {
	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;
	String[] btnName = {
			"Undo",			//	작업이전 상태로 되돌림
			"짝수줄삭제",		// 짝수줄을 삭제
			"문자삭제",
			"좌우공백삭제",
			"빈줄삭제",
			"접두사/접미사추가",
			"문자열자르기",
			"패턴적용"
	};
	
	Button[] btn =new Button[btnName.length];
	
	private final  String CR_LF =System.getProperty("line.separator");	// 줄바꿈문자
	private String prevText = "";
	
	private void registerEventHandler(){
		addWindowListener(this);
		
		int n = 0;
		
		// Undo 버튼
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				
				if(!prevText.equals("")){
					ta.setText(prevText);
				}
				
				prevText = curText;
			}
		});
		
		//짝수줄 삭제 버튼
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				Scanner s = new Scanner(curText);
				for(int i=0;s.hasNextLine();i++){
					String line = s.nextLine();
					
					if(i%2==0){
						sb.append(line).append(CR_LF);
					}
				}
				ta.setText(sb.toString());
			}
		});
		
		// 문자 삭제 버튼
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String delText = tfParam1.getText();
				
				if("".equals(delText)) return;
				
				for(int i=0;i<curText.length();i++)
				{
					char ch = curText.charAt(i);
					if(delText.indexOf(ch)==-1)
						sb.append(ch);
				}
				
				ta.setText(sb.toString());
			}
		});
		
		// line의 좌우공백을 제거하는 기능 - 좌우공백삭제
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				Scanner s = new Scanner(curText);
				for(int i=0;s.hasNextLine();i++){
					String line = s.nextLine().trim();
					sb.append(line).append(CR_LF);
				}
				
				ta.setText(sb.toString());
			}
		});
		
		//빈줄 삭제
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				Scanner s = new Scanner(curText);
				for(int i=0;s.hasNextLine();i++){
					String line = s.nextLine();
					
					if(line.trim().length()>0)
						sb.append(line).append(CR_LF);
				}
				
				ta.setText(sb.toString());
			}
		});
		
		//접두사/접미사 추가 기능
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				prevText = curText;
				
				String prefix = tfParam1.getText();
				String postfix = tfParam2.getText();
				
				Scanner s = new Scanner(curText);
				
				for(int i=0;s.hasNextLine();i++){
					String line = s.nextLine();
					
					sb.append(prefix);
					sb.append(line);
					sb.append(postfix);
					sb.append(CR_LF);
				}
				
				ta.setText(sb.toString());
			}
		});
		
		// 문자열 자르기
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				prevText = curText;
				
				int from = tfParam1.getText().length();
				int to = tfParam2.getText().length();
				
				Scanner s = new Scanner(curText);
				
				for(int i=0;s.hasNextLine();i++){
					String line = s.nextLine();
					
					if(line.length()<from+to) return;
					
					sb.append(line.substring(from, line.length()-to));
					sb.append(CR_LF);
				}
				ta.setText(sb.toString());
			}
		});
		
		// 패턴 적용하기
		btn[n++].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				prevText = curText;
				
				String pattern = tfParam1.getText();
				String delimiter = tfParam2.getText();
				
				Scanner s = new Scanner(curText);
				if(delimiter.length()==0) delimiter = ",";
				
				for(int i=0;s.hasNextLine();i++){
					String line = s.nextLine();
					
					String[] tokens = line.split(delimiter);
					
					sb.append(MessageFormat.format(pattern, tokens));
					sb.append(CR_LF);
				}
				
				ta.setText(sb.toString());
			}
		});
	}	// end registerEventHandler
	
	public TextTool(String title){
		super(title);
		lb1 = new Label("param1:", Label.RIGHT);
		lb2 = new Label("param2:", Label.RIGHT);
		tfParam1 = new TextField(15);
		tfParam2 = new TextField(15);
		
		ta = new TextArea();
		pNorth = new Panel();
		pSouth = new Panel();
		
		for(int i=0;i<btn.length;i++){
			btn[i] = new Button(btnName[i]);
		}
		
		pNorth.setLayout(new FlowLayout());
		pNorth.add(lb1);
		pNorth.add(tfParam1);
		pNorth.add(lb2);
		pNorth.add(tfParam2);
		
		pSouth.setLayout(new GridLayout(2, 10));
		for(int i=0;i<btn.length;i++){
			pSouth.add(btn[i]);
		}
		
		add(pNorth,"North");
		add(ta, "Center");
		add(pSouth, "South");
		
		setBounds(100,100,600,300);
		ta.requestFocus();
		registerEventHandler();
		setVisible(true);
	} // end TextTool

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextTool win = new TextTool("Text Edit");
	}

}




