import javax.swing.*;   // in built library of java provide inbuilt methods to build the screen
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameScreen extends JFrame {

    GameScreen(){
        setTitle("Fighting Game");
        setSize(1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // When X presses the games close otherwise without this statement game will continue in background
        setResizable(false); // size will not be resized again
        setLocationRelativeTo(null);  // keep window on centre by default
        // we have to add panel because we have to show drawing on this panel so we will do composition
        GamePanel Panel = new GamePanel(this);   // Composition (Game panel will not exist without Screen)
        add(Panel);  // to add panel inside window


        setVisible(true);
    }
    public static void main(String[] args){

        GameScreen window = new GameScreen();

    }
}
class GamePanel extends JPanel{

    JFrame frame; // aggregation
    // to store image we  are using Image datatype
    Image background;


    // I will use JButton datatype  to store buttons
    JButton startButton;
    JButton quitButton;

    // Constructor Created to load Image
    GamePanel(JFrame frame) {
        this.frame = frame;
        background = new ImageIcon("startingScreen.png").getImage(); // added Game starting screen image

        // on the spot of launching background image so i will launch buttons on the time of game Panel launch
        setLayout(null);  // iam telling java i will do changes by manually otherwise java will set default positions

        startButton = new JButton("Start Game");
        startButton.setBounds(400,370,200,50);
        startButton.setBackground(new Color(46, 125, 50));
        startButton.setForeground(Color.WHITE);  // to give color to text
        startButton.setFont(new Font("Arial",Font.BOLD,20));
        startButton.setFocusPainted(false);  // by default java add doted ugly border by doing false java removes that ugly border
        add(startButton);

        quitButton = new JButton("Quit");
        quitButton.setBounds(400,430,200,50);
        quitButton.setBackground(new Color(198, 40, 40));
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Arial",Font.BOLD,20));
        quitButton.setFocusPainted(false);
        add(quitButton);
        // giving action listner  means when clicked the button should respond
        startButton.addActionListener(new ActionListener() {  // new ActionListner is anonymous class
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();   // remove old screen
                frame.add(new RoundPanel(frame));      // add new screen
                frame.revalidate();
                frame.repaint();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            // this is inbuilt method when button pressed automatically method runs
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // close the game with zero errors
            }
        });


    }

    @Override
    protected void paintComponent(Graphics g) {   // this is inbuilt method provide a tool to creates a drawing on window here g is like a pencil
        super.paintComponent(g);  // to remove the previous drawing

        // Insert Background image
        g.drawImage(background,0,0,1000,600,this); // here this is written exactly as but refer to current panel
        g.setColor(new Color(255, 220, 80)); // slightly darker yellow  // set color to string
        // new Font is in built class in awt library  anf bold is directly accessing by Font class name which is static method
        g.setFont(new Font("Arial",Font.BOLD,45));
        g.drawString("Welcome To 2D Fighting Game",180,200);

    }
}

    class RoundPanel extends JPanel{
        JFrame frame;
        Image levelBackground;
        JButton easy;
        JButton medium;
        JButton hard;
        JLabel heading;  // use to add the heading or text on window
        RoundPanel(JFrame frame){
            this.frame = frame;
            // see without setLayout null i changes values of x and y   but not changes java takes it by default position and our positions are ignored
            // thats why necessary to tell java i will use manually positions
            setLayout(null);
            levelBackground = new ImageIcon("startingScreen.png").getImage();

            heading = new JLabel("Select Difficulty");
            heading.setBounds(300,110,400,50);
            heading.setForeground(new Color(255, 220, 80));
            heading.setFont(new Font("Arial",Font.BOLD,40));
            add(heading);

//             add Difficulty levels buttons
            easy = new JButton("Easy");
            easy.setBounds(400,250,150,50);
            easy.setBackground(new Color(76, 175, 80));
            easy.setForeground(Color.WHITE);
            easy.setFont(new Font("Arial", Font.BOLD,20));
            easy.setFocusPainted(false);
            add(easy);

            medium = new JButton("Medium");
            medium.setBounds(400,325,150,50);
            medium.setBackground(new Color(255, 152, 0));
            medium.setForeground(Color.WHITE);
            medium.setFont(new Font("Arial", Font.BOLD,20));
            medium.setFocusPainted(false);
            add(medium);

            hard = new JButton("Hard");
            hard.setBounds(400,400,150,50);
            hard.setBackground(new Color(229, 57, 53));
            hard.setForeground(Color.WHITE);
            hard.setFont(new Font("Arial", Font.BOLD,20));
            hard.setFocusPainted(false);
            add(hard);

            easy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();   // remove old screen
                    frame.add(new PlayerInformation("Easy",frame));      // add new screen
                    frame.revalidate();
                    frame.repaint();
                }
            });

            medium.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();   // remove old screen
                    frame.add(new PlayerInformation("Medium",frame));      // add new screen
                    frame.revalidate();
                    frame.repaint();
                }
            });

            hard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();   // remove old screen
                    frame.add(new PlayerInformation("Hard",frame));      // add new screen
                    frame.revalidate();
                    frame.repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(levelBackground,0,0,1000,600,this);
        }
    }

    class PlayerInformation extends JPanel{
        JFrame frame;
        Image userBackground;
        JLabel name;
        JLabel heading2;
        JLabel player1;
        JTextField player1Field;
        JLabel textOfPassword;
        JPasswordField passwordField;
        //  get string to compare string because above is only gui component so it cannot be compared to compare we get String
        String password;
        String Name;

        JLabel textOfConfirmPassword;
        JPasswordField confirmPasswordField;
        String confirmPassword;

        JButton startGame;
        String level;
        PlayerInformation(String level,JFrame frame){

            this.level = level;
            this.frame = frame;
            // will move into submit button
//            password = new String(passwordField.getPassword());
//            confirmPassword = new String(confirmPasswordField.getPassword());
        userBackground = new ImageIcon("startingScreen.png").getImage();
        setLayout(null);
        //lets add the labeling of information for user
        heading2 = new JLabel("Difficulty "+level);
        heading2.setBounds(370,50,400,50);
        heading2.setForeground(new Color(255, 220, 80));
        heading2.setFont(new Font("Arial", Font.BOLD,40));
        add(heading2);

        name = new JLabel("ENTER PLAYER DETAILS");
        name.setBounds(70,150,400,50);
        name.setForeground(new Color(255, 215, 0));
        name.setFont(new Font("Arial",Font.BOLD,30));
        add(name);

        player1 = new JLabel("Player 1 Name :");
        player1.setBounds(70,215,200,30);
        player1.setForeground(new Color(220, 220, 220));
        player1.setFont(new Font("Arial",Font.BOLD,20));
        add(player1);

        player1Field = new JTextField();
        player1Field.setBounds(230,215,200,25);
        player1Field.setBackground(new Color(245, 245, 245));
        player1Field.setForeground(Color.BLACK);
        player1Field.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));
        player1Field.setFont(new Font("Arial",Font.BOLD,20));
        add(player1Field);

//        // for password
            textOfPassword = new JLabel("Password :");
            textOfPassword.setBounds(70,250,200,30);
            textOfPassword.setForeground(new Color(220, 220, 220));
            textOfPassword.setFont(new Font("Arial",Font.BOLD,20));
            add(textOfPassword);

            passwordField = new JPasswordField();
            passwordField.setBounds(200,250,400,25);
            passwordField.setBackground(new Color(245, 245, 245));
            passwordField.setForeground(Color.BLACK);
            passwordField.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));
            passwordField.setFont(new Font("Arial",Font.BOLD,20));
            add(passwordField);

            //for confirm password
            textOfConfirmPassword = new JLabel("Confirm Password :");
            textOfConfirmPassword.setBounds(70,290,200,30);
            textOfConfirmPassword.setForeground(new Color(220, 220, 220));
            textOfConfirmPassword.setFont(new Font("Arial",Font.BOLD,20));
            add(textOfConfirmPassword);

            confirmPasswordField = new JPasswordField();
            confirmPasswordField.setBounds(270,290,400,25);
            confirmPasswordField.setBackground(new Color(245, 245, 245));
            confirmPasswordField.setForeground(Color.BLACK);
            confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));
            confirmPasswordField.setFont(new Font("Arial",Font.BOLD,20));
            add(confirmPasswordField);

            // after filling details button of start game

            startGame = new JButton("Start Game");
            startGame.setBounds(400,400,200,50);
            startGame.setBackground(new Color(46, 125, 50));
            startGame.setForeground(Color.WHITE);
            startGame.setFont(new Font("Arial",Font.BOLD,20));
            startGame.setFocusPainted(false);  // by default java add doted ugly border by doing false java removes that ugly border
            add(startGame);

            startGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Name = player1Field.getText();
                    password = new String(passwordField.getPassword());
                    confirmPassword = new String(confirmPasswordField.getPassword());
                    if(password.equals(confirmPassword)){
                        frame.getContentPane().removeAll();   // remove old screen
                        frame.add(new GameMode(frame,Name,level));      // add new screen
                        frame.revalidate();
                        frame.repaint();

//
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Password do not match");
                    }
                }
            });


        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(userBackground,0,0,1000,600,this);
            g.setColor(new Color(0, 0, 0, 160));
        }
    }
    class GameMode extends JPanel implements KeyListener {


        JFrame frame;
        Image easyGround;
        int player1X = 100;  // movement of player 1 along x axis
        int player2X = 500;
        String playerName;
        String Opponent = "AI Player";
        Image player1;
        Image AiPlayer;
        int player1Health = 100;  // intially both player has max health which is 100
        int player2Health = 100;
        int score1 = 0;  // for player 1 intially score will be 0
        int score2 = 0;
        int timeLeft = 60;  // Game round will be only 1 minutes so here 60 is seconds

        // Timer is inbuilt datatype in swing library
        Timer countDownTimer;  //   this is time in which game will be over
        Timer gamerRefresher; //   After this time game screen refreshes to keep smoothly game
        String level;
        Timer aiTimer;
        Timer aiPunchTimer;
        int aiSpeed = 0;


        GameMode(JFrame frame, String playerName, String level) {
            setLayout(null);
            this.frame = frame;
            this.playerName = playerName;
            this.level = level;
            if (level.equalsIgnoreCase("Easy")) aiSpeed = 2;
            else if (level.equalsIgnoreCase("Medium")) aiSpeed = 5;
            else if (level.equalsIgnoreCase("Hard")) aiSpeed = 8;

            setFocusable(true);
            addKeyListener(this);
            grabFocus();
            easyGround = new ImageIcon("EasyRoundGround.png").getImage();
            player1 = new ImageIcon("P1.png").getImage();
            AiPlayer = new ImageIcon("P2.png").getImage();

            // Time created when screen launch at that time
            // delay 16 means  after 16 milliseconds screen refreshes approximately 60 times per seconds
            gamerRefresher = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            // here 1000 is millisecond so which is equal to 1 second
            countDownTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLeft = timeLeft - 1;

                    if (timeLeft == -1) {  // -1 is ued instead of 0 to only get one render the screen to visually show the correct timing
                        countDownTimer.stop();
                        gamerRefresher.stop();

                        JOptionPane.showMessageDialog(null, "Game Over");
                        frame.getContentPane().removeAll();   // remove old screen
                        frame.add(new WinnerScreen());      // add new screen
                        frame.revalidate();
                        frame.repaint();

                    }
                }
            });

            countDownTimer.start();
            gamerRefresher.start();

            // timer 1 for ai movement
            aiTimer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player1X < player2X) {   // player1 is on left side
                        player2X = player2X - aiSpeed;   // ai will move toward ai
                    } else if (player1X > player2X) {  // ai player on right side
                        player2X = player2X + aiSpeed; // ai Player will move toward right side of palyer 1
                    }
                }
            });
            aiTimer.start();

            // timer 2 used for ai punch
            aiPunchTimer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    aiPunch();
                    checkWinner();
                }
            });
            aiPunchTimer.start();


        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(easyGround, 0, 0, 1000, 600, this);
            g.drawImage(player1, player1X, 350, 100, 150, this);
            g.drawImage(AiPlayer, player2X, 350, 100, 150, this);
            // for player
            g.setColor(new Color(255, 215, 0)); // Yellow
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Player:" + playerName, 10, 60);


            // opponent player
            g.setColor(new Color(255, 215, 0));
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Opponent:" + Opponent, 650, 60);

            // for player 1 health bar
            g.setColor(new Color(229, 57, 53));  // red
            g.fillRect(10, 70, player1Health * 3, 25);

            // for player 2 health bar
            g.setColor(new Color(229, 57, 53));  // red
            g.fillRect(650, 70, player2Health * 3, 25);

            // score for player 1
            g.setColor(new Color(100, 200, 255));
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score :" + score1, 250, 60);

            // score for player 2

            g.setColor(new Color(100, 200, 255));
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score :" + score2, 850, 60);

            // Timer for game
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Timer :" + timeLeft, 390, 90);


        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();  // each keyboard key has specific code so when key pressed it detect that code and store in key

            if (key == KeyEvent.VK_A) {
                player1X = player1X - 10;  // when A key si pressed player will go back
            } else if (key == KeyEvent.VK_D) {
                player1X = player1X + 10;
            } else if (key == KeyEvent.VK_F) {
                playerPunch();
                checkWinner();

            }
            repaint();
        }


        // These functions are not part of game but written are because implement is enforcing
        public void keyReleased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }

        //“Some methods like focus only work after component is visible. So we use addNotify to run code after the panel is added.”

        @Override
        public void addNotify() {
            super.addNotify();
            grabFocus();   // enforces to focus the panel to ensure key listens
        }

        void playerPunch() {
            if (player1X + 100 > player2X) {

                if (level.equalsIgnoreCase("Easy")) {
                    score1 += 10;
                    player2Health -= 10;
                } else if (level.equalsIgnoreCase("Medium")) {
                    score1 += 20;
                    player2Health -= 20;
                } else if (level.equalsIgnoreCase("Hard")) {
                    score1 += 40;
                    player2Health -= 50;
                }
            }
        }

        void aiPunch() {
            if (player1X >= player2X - 100 && player1X <= player2X) {

                if (level.equalsIgnoreCase("Easy")) {
                    score2 += 10;
                    player1Health -= 10;
                } else if (level.equalsIgnoreCase("Medium")) {
                    score2 += 20;
                    player1Health -= 20;
                } else if (level.equalsIgnoreCase("Hard")) {
                    score2 += 40;
                    player1Health -= 50;
                }
            }
        }

        // player 1 is winner
        void checkWinner() {

            if (player2Health <= 0) {

                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WinnerScreen());
                frame.revalidate();
                frame.repaint();
            } else if (timeLeft == 0 && player1Health > player2Health) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WinnerScreen());
                frame.revalidate();
                frame.repaint();
            } else if (timeLeft == 0 && player2Health > player1Health) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WinnerScreen());
                frame.revalidate();
                frame.repaint();
            } else if (player1Health<=0) {
                countDownTimer.stop();
                gamerRefresher.stop();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new WinnerScreen());
                frame.revalidate();
                frame.repaint();
            }
        }
    }

    class WinnerScreen extends JPanel{

    Image WinnerGround;

        WinnerScreen(){
            WinnerGround = new ImageIcon("WinnerBackground.png").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(WinnerGround, 0, 0, 1000, 600, this);

        }
    }



