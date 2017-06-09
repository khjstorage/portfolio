import java.awt.*;
import java.awt.event.*; 

public class BingoGa extends Frame {
  final int SIZE = 5;  // 빙고판의 크기
  int bingoCnt = 0;  // 완성된 라인의 수

  Button[] btnArr = null; 
  boolean[][] bArr = new boolean[SIZE][SIZE]; // 빙고판 체크여부 확인을 위한 배열

   // 빙고판 버튼에 사용할 문자열, 빙고판의 크기에 따라 이들의 일부만 사용될 수 있다.
  String[] values = { 
        "글쎄","기로로","이지우","김포대표","까꿍", 
        "이은서","김민정","넓게보기","네라주리","다밀", 
        "더클레오","들개","디벨로","레몬","루션", 
        "루이지노","무색이","문학청년","사천사","상상", 
        "세피룸","스쿨쥐","쌩","쏭양","씨드", 
        "양수호","에노야","에비츄","에이스","엔즈", 
        "오이날다","오케클릭","용주니","우기파파","잠탱이", 
        "제러스","조땜","지냔","카라","캉스", 
        "태연","파티쉐","페르소마","폭풍","핏빛노을", 
        "핑크팬더","하늘이","하루","한경훈","헐레벌떡", 
        "화염병","흑빛" 
  };
  
  public BingoGa() {
    // TODO Auto-generated constructor stub
   
    this("Bingo Game"); 
  } 

  BingoGa(String title) { 
    super(title); 

    setLayout(new GridLayout(SIZE, SIZE));
    
    btnArr = new Button[SIZE*SIZE];
    MyEventHandler handler = new MyEventHandler();
    addWindowListener(handler);
    
    shuffle();		// 문자열 배열값 섞는 작업
    
    // Frame에 버튼 추가
    for(int i=0;i<SIZE*SIZE;i++){
    	btnArr[i] = new Button(values[i]);
    	add(btnArr[i]);
    	btnArr[i].addActionListener(handler);
    }
    
    setBounds(500, 200, 300, 300);  // x:500, y:200 크기 : 300*300
    setVisible(true);
  }
  
  // 빙고판의 버튼에 사용될 문자열 배열값이 저장되어있는 values의 문자열들을 섞는다.
  void shuffle(){
	  for(int i=0;i<values.length*2;i++){
		  int r1 = (int)(Math.random()*values.length);
		  int r2 = (int)(Math.random()*values.length);
		  
		  String tmp = values[r1];
		  values[r1] =values[r2];
		  values[r2] = tmp;
	  }
  }
  
  // 2차원 배열 bArr을 출력하는 기능
  void print(){
	  for(int i=0;i<bArr.length;i++){
		  for(int j=0;j<bArr.length;j++){
			  System.out.print(bArr[i][j] ? "O":"X");
		  }
		  System.out.println();
	  }
	  System.out.println("-----------------------------");
  }
  
  // 빙고 게임판이 완성이 되면 완성된 라인은 녹색으로 변경
  boolean checkBingo(){
	  bingoCnt = 0;
	  int garoCnt = 0;		// 가로줄의 0개의 개수
	  int seroCnt = 0;		// 세로줄의 0개의 개수
	  int crossCnt1 = 0;		// 대각선의 0개의 개수
	  int crossCnt2 = 0;		// 역대각선 0개의 개수
	  
	  // 빙고 게임판이 완성이 되면 완성된 라인은 녹색으로 표현
	  for(int i = 0;i<SIZE;i++){
		  garoCnt =0;
		  seroCnt = 0;
		  
		  for(int j=0;j<SIZE;j++){
			  if(bArr[i][j]){
				  garoCnt++;
				  
				  if(garoCnt==SIZE){
					  for(int a=i*SIZE, b=SIZE;a<btnArr.length&&b>0;a++,b--){
						  btnArr[a].setBackground(Color.GREEN);
					  }
				  }//if end
			  }// 가로줄을 다 채웠을 때 녹색으로 버튼 색깔 변경
			  
			  // 세로 줄 다 채웠을 때 녹색으로 버튼 변경
			  if(bArr[j][i]){
				  seroCnt++;
				  
				  if(seroCnt==SIZE){
					  for(int a=i, b=SIZE;a<btnArr.length&&b>0;a+=SIZE, b--){
						  btnArr[a].setBackground(Color.GREEN);
					  }
				  }
			  }
			  
			  if(i==j&&bArr[i][i]) crossCnt1++;
			  if(i+j==SIZE-1&&bArr[i][j]) crossCnt2++;
		  }//for j end
		  
		  // 역대각선 버튼 색깍 변경
		  if(crossCnt2 == SIZE){
			  ++bingoCnt;
			  for(int a=SIZE-1;a<btnArr.length-1;a+=(SIZE-1)){
				  btnArr[a].setBackground(Color.green);
			  }
		  }
		  
		  // 대각선 버튼 색깔 변경
		  if(crossCnt1 ==SIZE){
			  ++bingoCnt;
			  for(int a=0;a<btnArr.length;a+=SIZE+1){
				  btnArr[a].setBackground(Color.GREEN);
			  }
		  }
		  
		  if(garoCnt==SIZE) ++bingoCnt;
		  if(seroCnt==SIZE) ++bingoCnt; 		  
	  }// for i end
	  
	  System.out.println(bingoCnt);
	  return bingoCnt >= SIZE;
  }
  
  class MyEventHandler extends WindowAdapter implements ActionListener{
	  public void actionPerformed(ActionEvent ae){
		  Button btn = (Button)ae.getSource();	// 선택되어진 button 객체값을 가져오는 작업
		  
		  System.out.println(btn.getLabel());		// 눌러진 버튼의 이름을 출력
		  
		  // 버튼이 선택되어지면 선택되어진 버튼과 연결된 2차원배열의 위치값을 '0'으로 변경
		  for(int i=0;i<btnArr.length;i++){
			  if(btnArr[i]==btn){
				  bArr[i/SIZE][i%SIZE] = true;
				  break;
			  }
		  }
		  
		  btn.setBackground(Color.YELLOW);
		  print();
		  //checkBingo()
		  if(checkBingo()){
			  System.out.println("Bingo~~~ 완성");
		  }
	  }
	  
	  public void windowClosing(WindowEvent e){
		  e.getWindow().setVisible(false);
		  e.getWindow().dispose();
		  System.exit(0);
	  }
  }


  public static void main(String[] args) {
    // TODO Auto-generated method stub
    BingoGa win = new BingoGa("Bingo Game Ver1.0"); 
    win.show(); 

  }

}






