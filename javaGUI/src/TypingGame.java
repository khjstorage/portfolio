import java.awt.Frame;
import java.util.*; 
import java.awt.*; 
import java.awt.event.*; 


public class TypingGame extends Frame {

  final int FRAME_WIDTH = 400; 
  final int FRAME_HEIGHT = 300; 
  
  final int SCREEN_WIDTH; 
  final int SCREEN_HEIGHT; 

  int speed = 500; // 단어가 떨어지는 속도... 높을 수록 느리다. 
  int interval = 2 * 1000; // 새로운 단어가 나오는 간격 

  int score = 0; 
  int life = 3; 
  int curLevel = 0; 
  final int MAX_LEVEL; 

  boolean isPlaying = false; 

  WordGenerator wg = null; // 단어를 생성하는 쓰레드 Class(Inner Class)
  WordDropper wm = null; // 단어를 떨어뜨리는 쓰레드 Class(Inner Class)

  FontMetrics fm; // 화면에서의 글자 길이를 구하는데 사용 
  ThreadGroup virusGrp = new ThreadGroup("virus"); // 바이러스 쓰레드들의 그룹(lang 패키지에서 제공)

  String[][] data = { 
        {"마무리","정현","깜빡이","절연","카페라떼","바보","아무리","나라","의무"}, 
        {"솔데스크","트와이스","사연","바보","아메리카노","어머님","깜빡이다","대한민국","봉사"}, 
        {"학연","지연","가나다라","음악","핸드폰","쌍방울","튜브","막연한","가난쟁이"}, 
        {"기상청","국세청","아우성","가성비","마무리","우리나라","정열하다","검은옷","가방"}, 
  }; 

  final Level[] LEVEL = { 
        new Level(500, 2000, 1000, data[0]),
        new Level(250, 1500, 2000, data[1]),
        new Level(120, 1000, 3000, data[2]),
        new Level(100, 500, 4000, data[3]),
  }; 

  Vector words = new Vector(); 

  TextField tf = new TextField(); 
  Panel pScore = new Panel(new GridLayout(1,3)); 
  Label lbLevel = new Label("Level:"+curLevel, Label.CENTER); 
  Label lbScore = new Label("Score:"+score, Label.CENTER); 
  Label lbLife = new Label("Life:"+life, Label.CENTER); 
  MyCanvas screen = new MyCanvas(); 

  TypingGame() { 
        this("Typing game ver1.0"); 
  } 

  // 생성자 메서드
  TypingGame(String title) { 
        super(title);
        pScore.setBackground(Color.YELLOW);
        pScore.add(lbLevel);
        pScore.add(lbScore);
        pScore.add(lbLife);
        add(pScore, "North");
        add(screen, "Center");
        add(tf, "South");
        
        MyEventHandler handler = new MyEventHandler();
        addWindowListener(handler);
        tf.addActionListener(handler);
        
        setBounds(500, 200, FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        
        SCREEN_WIDTH = screen.getWidth();
        SCREEN_HEIGHT = screen.getHeight();
        MAX_LEVEL = LEVEL.length-1;        
        
        fm = getFontMetrics(getFont());
  } 

  // 레벨 업 후 화면에 다시 그리기 메서드
  public void repaint() { 
        super.repaint();
        screen.repaint();
  } 

  // 화면 표현 delay
  public void delay(int millis) { 
     try{
    	 Thread.sleep(millis);
     }catch(Exception e){}
  } 

  // 시작 메서드
  public void start() { 
        showLevel(0);
        isPlaying = true;
        
        wg = new WordGenerator();
        wg.start();
        
        wm = new WordDropper();
        wm.start();
  } 

  // Level 얻기 메서드
  public Level getLevel(int level) {
	  if(level > MAX_LEVEL) level = MAX_LEVEL;
	  if(level<0) level = 0;
	  
        return LEVEL[level];
  } 

  // Level check 메서드
  public boolean levelUpCheck() {
	  Level lvl = getLevel(curLevel);
	  
       return score >= lvl.levelUPScore;
  } 

  public synchronized int getCurLevel() { 
        return curLevel; 
  } 

  // LevelUP 하는 기능
  public synchronized void levelUp() { 
       virusGrp.interrupt();
       Level lvl = getLevel(++curLevel);
       lbLevel.setText("Level:"+curLevel);
       words.clear();
       screen.clear();
       showLevel(curLevel);
       
       speed  = lvl.speed;
       interval = lvl.interval;
  } 

  public void showLevel(int level) { 
        String tmp = "Level "+level; 
        showTitle(tmp, 1 * 1000); 
  } 

  public void showTitle(String title, int time) { 
        Graphics g = screen.getGraphics(); 

        Font titleFont = new Font("Serif",Font.BOLD, 20); 
        g.setFont(titleFont); 

        FontMetrics fm = getFontMetrics(titleFont); 
        int width = fm.stringWidth(title); 

        g.drawString(title, (SCREEN_WIDTH-width)/2, SCREEN_HEIGHT/2); 
        delay(time); 
  } 

 
  // 단어 떨어뜨리기 쓰레드 Class
  class WordDropper extends Thread { 
        public void run(){
        	outer:
        	while(isPlaying){
        		delay(speed);
        		for(int i=0;i<words.size();i++){
        			Word temp = (Word)words.get(i);
        			temp.y += temp.step;
        			
        			if(temp.y >= SCREEN_HEIGHT){
        				temp.y = SCREEN_HEIGHT;
        				words.remove(temp);
        				life--;
        				lbLife.setText("Life:"+life);
        				break;
        			}
        			
        			if(life<=0){
        				isPlaying = false;
        				showTitle("Game Over", 0);
        				
        				break outer;
        			}
        		}
        		
        		repaint();
        	}
        }
  } 

  // 단어 생성 쓰레드 Class
  class WordGenerator extends Thread { 
	  public void run(){
		  while(isPlaying){
			  String[] data = LEVEL[getCurLevel()].data;
			  
			  int rand = (int)(Math.random()*data.length);
			  
			  boolean isVirus = ((int)(Math.random()*10)+1)/10 != 0;
			  
			  Word word = new Word(data[rand], isVirus);
			  words.add(word);
			  delay(interval);
		  }
	  }
  } 

  // 화면에 표현 Class
  class MyCanvas extends Canvas { 
    public void clear(){
    	Graphics g = getGraphics();
    	g.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    public void paint(Graphics g){
    	clear();
    	
    	for(int i=0;i<words.size();i++){
    		Word tmp = (Word)words.get(i);
    		g.setColor(tmp.color);
    		g.drawString(tmp.word, tmp.x, tmp.y);
    	}
    }
  } 

  // 바이러스 문자의 동작 쓰레드
  class VirusThread extends Thread { 
    public VirusThread(ThreadGroup group, String name){
    	super(group, name);
    }
    
    public void run(){
    	int rand = (int)(Math.random()*5);
    	
    	int oldValue = 0;
    	int virusTime = 10*1000;	// 바이러스 동작 시간
    	
    	switch(rand){
    	case 0:
    		speed = speed/2;	// 속도 빠름
    		break;
    	case 1:
    		interval = interval/2;		// 글자 표현 간격이 빨라짐
    		break;
    	case 2:
    		speed = speed*2;	// 속도가 느려짐
    		break;
    	case 3:
    		interval = interval*2;
    		break;
    	case 4:
    		words.clear();
    		break;
    	}
    	
    	delay(virusTime);
    	int curLevel = getCurLevel();
    	speed = LEVEL[curLevel].speed;
    	interval = LEVEL[curLevel].interval;
    }
  } 

  // Game Level 적용 Class
  class Level { 
        int speed;
        int interval;
        int levelUPScore;
        String[] data;
        
        Level(int speed, int interval, int levelUPScore, String[] data){
        	this.speed = speed;
        	this.interval = interval;
        	this.levelUPScore = levelUPScore;
        	this.data = data;
        }
  } // GameLevel 

  // 단어 표현하기 Class
  class Word { 
        String word = "";
        int x=0;
        int y =0;
        int step = 5;
        
        Color color = Color.BLACK;
        boolean isVirus = false;
        
        Word(String word){
        	this(word, 10, false);
        }
        
        Word(String word, boolean isVirus){
        	this(word, 10, isVirus);
        }
        
        Word(String word, int step, boolean isVirus){
        	this.word = word;
        	this.step = step;
        	this.isVirus = isVirus;
        	
        	if(isVirus) color = Color.RED;
        	
        	int strWidth = fm.stringWidth(word);
        	
        	x= (int)(Math.random() * SCREEN_WIDTH);
        	
        	if(x+strWidth >= SCREEN_WIDTH)
        		x = SCREEN_WIDTH - strWidth;
        }
        
        public String toString(){
        	return word;
        }
        
  } // end of class Word 

  // Event 적용 Class
  class MyEventHandler extends WindowAdapter implements ActionListener { 
        public void actionPerformed(ActionEvent ae) { 
               String input = tf.getText().trim();
               tf.setText("");
               
               System.out.println(input);
               if(!isPlaying) return;
               
               for(int i=0;i<words.size();i++){
            	   Word tmp = (Word)words.get(i);
            	   
            	   if(input.equals(tmp.word)){
            		   words.remove(i);
            		   score += input.length() *50;
            		   lbScore.setText("score:"+score);
            		   Toolkit.getDefaultToolkit().beep();
            		   
            		   if(curLevel != MAX_LEVEL && levelUpCheck()){
            			   levelUp();
            		   }else{
            			   if(tmp.isVirus){
            				   new VirusThread(virusGrp, "virus").start();
            			   }
            		   }
            		   
            		   break;
            	   }
               }
               
               repaint();
        } 

        public void windowClosing(WindowEvent e) { 
           e.getWindow().setVisible(false);
           e.getWindow().dispose();
           System.exit(0);
        } 
  } // class MyEventHandler
  
  public static void main(String[] args) { 
    TypingGame win = new TypingGame();
    win.start();
  }

}
