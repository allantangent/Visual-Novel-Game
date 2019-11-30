import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

//javafx bs
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends javafx.application.Application {
  private static final int FRAME_WIDTH = 1366;
  private static final int FRAME_HEIGHT = 768;
  private JFrame frame;
  private JPanel panel;
  private JLabel label, mainMenu, bgLabel, headLabel, nameLabel, dialogueLabel, textLabel, versatileLabel1, versatileLabel2, versatileLabel3;
  private ImageIcon secretImage, mainScreenBG, playButtonImage, creditsButtonImage, exitButtonImage, playHoveredImage, creditsHoveredImage,
      exitHoveredImage, skipIntroButtonImage, skipIntroHoveredImage, intro1Image, companyLogo, titleScreen, intro2Image, intro3Image, intro4Image,
      intro5Image, intro6Image, intro7Image, intro8Image, intro9Image, intro10Image, intro11Image, intro12Image, intro13Image, chapter1Image,
      tutorial1Image, tutorialClosedProphecyImage, tutorialOpenProphecyImage, tutorialTransitionImage, jtHeadImage, allanHeadImage,
      prophecyTitleImage, chapter11Image;
  private JButton play, credits, exit, secretButt, skipIntro, choice1, choice2, choice3, choice4, choice5, tryAgain;
  private static MediaPlayer mediaPlayer;
  private SpringLayout layout;
  private JTextArea textArea, dialogueArea, prophecyText;
  private int stringCounter, choiceNum;
  private boolean introSkipped, jtBlock, legDay, diamonds;
  private static boolean animationFinished = false;
  private static boolean fadeFinished = false;
  private boolean ballGameFinished= false;
  private int [] stageCnt = { 0 }; // used to track the number of user clicks to know which stage to advance to and also to bypass final error
  private String curStage; // used to check current stage to accurately represent button hovering
  private int counter, ballScore;
  private String chosenFood;

  // private JSlider slider;

  // constructor initializes all images and actionlisteners and starts the game at
  // the menu screen
  public Game() {
    frame = new JFrame();
    panel = new JPanel();
    bgLabel = new JLabel();
    secretImage = getImageIcon( "Secret_Image.png" );
    mainScreenBG = getImageIcon( "Main_Screen_Image.gif" );
    playButtonImage = getImageIcon( "play button.png" );
    creditsButtonImage = getImageIcon( "credits button.png" );
    exitButtonImage = getImageIcon( "exit button.png" );
    playHoveredImage = getImageIcon( "Play Button Hovered.png" );
    creditsHoveredImage = getImageIcon( "Credits Button Hovered.png" );
    exitHoveredImage = getImageIcon( "Exit Button Hovered.png" );
    skipIntroButtonImage = getImageIcon( "Skip_Intro.png" );
    skipIntroHoveredImage = getImageIcon( "Skip_Intro_Hovered.png" );
    intro1Image = getImageIcon( "1_intro.gif" );
    intro2Image = getImageIcon( "2_intro.gif" );
    intro3Image = getImageIcon( "3_intro.gif" );
    intro4Image = getImageIcon( "4_intro.gif" );
    intro5Image = getImageIcon( "5_intro.gif" );
    intro6Image = getImageIcon( "6.1_intro.gif" );
    intro7Image = getImageIcon( "7_intro.gif" );
    intro8Image = getImageIcon( "8_intro.gif" );
    intro9Image = getImageIcon( "9_intro.gif" );
    intro10Image = getImageIcon( "10_intro.gif" );
    intro11Image = getImageIcon( "11_intro.gif" );
    intro12Image = getImageIcon( "12_intro.gif" );
    intro13Image = getImageIcon( "13_intro.gif" );
    chapter1Image = getImageIcon( "14_chapter.gif" );
    companyLogo = getImageIcon( "0_company.gif" );
    titleScreen = getImageIcon( "0.5_title.gif" );
    tutorial1Image = getImageIcon( "1_tutorial.gif" );
    tutorialClosedProphecyImage = getImageIcon( "1.1_closed.gif" );
    tutorialOpenProphecyImage = getImageIcon( "1.1_tutorial.gif" );
    jtHeadImage = getImageIcon( "JT_head.gif" );
    allanHeadImage = getImageIcon( "Allan_head.png" );
    prophecyTitleImage = getImageIcon( "prophecy transition.gif" );
    tutorialTransitionImage = getImageIcon( "2_tutorial.gif" );
    chapter11Image = getImageIcon( "1_chapter1.gif" );
    play = new JButton( playButtonImage );
    credits = new JButton( creditsButtonImage );
    exit = new JButton( exitButtonImage );
    secretButt = new JButton();
    skipIntro = new JButton( skipIntroButtonImage );
    mainMenu = new JLabel( mainScreenBG );
    label = new FadeLabel( "Main_Screen_Image.gif", "0_company.gif" );
    textArea = new JTextArea( 2, 20 );
    textLabel = new JLabel( getImageIcon( "text set.png" ) );
    dialogueArea = new JTextArea( 3, 20 );
    dialogueLabel = new JLabel( getImageIcon( "dialogue set.png" ) );
    setButtonTransparent( play );
    setButtonTransparent( credits );
    setButtonTransparent( exit );
    setButtonTransparent( secretButt );
    setButtonTransparent( skipIntro );
    skipIntro.setFocusPainted( false );
    mainMenu.setLayout( new GridBagLayout() );
    layout = new SpringLayout();
    panel.setLayout( layout );
    textArea.setPreferredSize( new Dimension( 1000, 100 ) );
    textArea.setForeground( new Color( 249, 253, 168 ) );
    textArea.setOpaque( false );
    textArea.setBorder( BorderFactory.createEmptyBorder( 5, 0, 0, 30 ) );
    textArea.setWrapStyleWord( true );
    textArea.setLineWrap( true );
    textArea.setEditable( false );
    textArea.setFocusable( false );
    dialogueArea.setPreferredSize( new Dimension( 850, 100 ) );
    dialogueArea.setForeground( new Color( 249, 253, 168 ) );
    dialogueArea.setOpaque( false );
    dialogueArea.setBorder( BorderFactory.createEmptyBorder( 5, 0, 0, 15 ) );
    dialogueArea.setWrapStyleWord( true );
    dialogueArea.setLineWrap( true );
    dialogueArea.setEditable( false );
    dialogueArea.setFocusable( false );

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 0.9;
    c.insets = new Insets( 0, 0, 30, 0 );
    mainMenu.add( play, c );
    c.gridy = 2;
    mainMenu.add( credits, c );
    c.gridy = 3;
    mainMenu.add( exit, c );
    c.gridy = 0;
    mainMenu.add( secretButt, c );

    try {
      mediaPlayer = new MediaPlayer( new Media( getClass().getResource( "resources/Blue_Bird_Song.mp3" ).toString() ) );
      setNewSong( "Blue_Bird_Song" );

      /*
       * slider = new JSlider( 0, 100, 100 ); c.gridy = 4; mainMenu.add( slider, c ); slider.addChangeListener( new javax.swing.event.ChangeListener()
       * {
       * 
       * @Override public void stateChanged( javax.swing.event.ChangeEvent evt ) { if( evt.getSource().equals( slider ) ) { mediaPlayer.setVolume(
       * 0.01 * slider.getValue() ); } } });
       */
    } catch( Exception e ) {
      e.printStackTrace();
    }

    // adding new font
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont( Font.createFont( Font.TRUETYPE_FONT, getClass().getResourceAsStream( "resources/Pixel-Noir.ttf" ) ) );
    } catch( Exception e ) {
      e.printStackTrace();
    }

    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    panel.add( textArea );
    textArea.setVisible( false );
    panel.add( textLabel );
    textLabel.setVisible( false );
    panel.add( mainMenu );
    panel.add( skipIntro );
    skipIntro.setVisible( false );
    panel.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    play.addMouseListener( new MenuButtonMouseAdapter() );
    credits.setIcon( creditsButtonImage );
    credits.setRolloverIcon( creditsHoveredImage );
    exit.addMouseListener( new MenuButtonMouseAdapter() );
    skipIntro.addMouseListener( new MenuButtonMouseAdapter() );
    play.addActionListener( new MenuButtonActionListener() );
    credits.addActionListener( new MenuButtonActionListener() );
    exit.addActionListener( new MenuButtonActionListener() );
    secretButt.addActionListener( new MenuButtonActionListener() );

    frame.add( panel );
    frame.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    frame.pack();
    frame.setLocationRelativeTo( null );
    frame.setResizable( false );
    frame.setIconImage( getImageIcon( "game icon.png" ).getImage() );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setTitle( "A N A" );
    frame.setVisible( true );
  }

  private void initMainMenu() {
    textArea.setFont( textArea.getFont().deriveFont( Font.PLAIN ) );
    // resetting frame
    panel.removeAll();
    frame.remove( panel );
    frame.getContentPane().removeAll();
    // resetting basics
    animationFinished = false;
    stageCnt[ 0 ] = 0;
    // resetting panel
    panel = new JPanel();
    panel.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    panel.setLayout( layout );
    panel.add( textArea );
    textArea.setVisible( false );
    panel.add( textLabel );
    textLabel.setVisible( false );
    panel.add( mainMenu );
    panel.add( skipIntro );
    skipIntro.setVisible( false );
    frame.add( panel );
    frame.revalidate();
    setNewSong( "Blue_Bird_Song" );
  }

  // checking if mouse enters the button for a hover effect
  private class MenuButtonMouseAdapter extends MouseAdapter {
    public void mouseEntered( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playHoveredImage );
      } else if( evt.getSource().equals( exit ) ) {
        exit.setIcon( exitHoveredImage );
      } else if( evt.getSource().equals( skipIntro ) ) {
        skipIntro.setIcon( skipIntroHoveredImage );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "jt_dialogue" ) ) {
        choice1.setIcon( getImageIcon( "greetH.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "jt_dialogue" ) ) {
        choice2.setIcon( getImageIcon( "passiveH.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "jt_dialogue" ) ) {
        choice3.setIcon( getImageIcon( "nastH.png" ) );
      } else if( evt.getSource().equals( choice4 ) && curStage.equals( "jt_dialogue" ) ) {
        choice4.setIcon( getImageIcon( "replyH.png" ) );
      } else if( evt.getSource().equals( choice5 ) && curStage.equals( "jt_dialogue" ) ) {
        choice5.setIcon( getImageIcon( "nastreplyH.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "ball_game" ) ) {
        choice1.setIcon( getImageIcon( "3 point shotH.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "ball_game" ) ) {
        choice2.setIcon( getImageIcon( "jumpH.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "ball_game" ) ) {
        choice3.setIcon( getImageIcon( "dunkH.png" ) );
      } else if( evt.getSource().equals( tryAgain ) ) {
        tryAgain.setIcon( getImageIcon( "try againH.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "ball_game_end") ) {
        choice1.setIcon( getImageIcon( "district button h.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "ball_game_end" ) ) {
        choice2.setIcon( getImageIcon( "downtown button h.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "district" ) ) {
        choice1.setIcon( getImageIcon( "in n out h.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "district" ) ) {
        choice2.setIcon( getImageIcon( "lil caesars h.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "district" ) ) {
        choice3.setIcon( getImageIcon( "whole foods h.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "downtown" ) ) {
        choice1.setIcon( getImageIcon( "85 degrees button h.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "downtown" ) ) {
        choice2.setIcon( getImageIcon( "coldstone button h.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "downtown" ) ) {
        choice3.setIcon( getImageIcon( "stickyfingers button h.png" ) );
      }
    }

    // checking if mouse leaves button for unhovered effect
    public void mouseExited( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playButtonImage );
      } else if( evt.getSource().equals( exit ) ) {
        exit.setIcon( exitButtonImage );
      } else if( evt.getSource().equals( skipIntro ) ) {
        skipIntro.setIcon( skipIntroButtonImage );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "jt_dialogue" ) ) {
        choice1.setIcon( getImageIcon( "greet.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "jt_dialogue" ) ) {
        choice2.setIcon( getImageIcon( "passive.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "jt_dialogue" ) ) {
        choice3.setIcon( getImageIcon( "nast.png" ) );
      } else if( evt.getSource().equals( choice4 ) && curStage.equals( "jt_dialogue" ) ) {
        choice4.setIcon( getImageIcon( "reply.png" ) );
      } else if( evt.getSource().equals( choice5 ) && curStage.equals( "jt_dialogue" ) ) {
        choice5.setIcon( getImageIcon( "nastreply.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "ball_game" ) ) {
        choice1.setIcon( getImageIcon( "3 point shot.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "ball_game" ) ) {
        choice2.setIcon( getImageIcon( "jump.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "ball_game" ) ) {
        choice3.setIcon( getImageIcon( "dunk.png" ) );
      } else if( evt.getSource().equals( tryAgain ) ) {
        tryAgain.setIcon( getImageIcon( "try again.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "ball_game_end" ) ) {
        choice1.setIcon( getImageIcon( "district button.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "ball_game_end" ) ) {
        choice2.setIcon( getImageIcon( "downtown button.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "district" ) ) {
        choice1.setIcon( getImageIcon( "in n out.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "district" ) ) {
        choice2.setIcon( getImageIcon( "lil caesars.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "district" ) ) {
        choice3.setIcon( getImageIcon( "whole foods.png" ) );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "downtown" ) ) {
        choice1.setIcon( getImageIcon( "85 degrees button.png" ) );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "downtown" ) ) {
        choice2.setIcon( getImageIcon( "coldstone button.png" ) );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "downtown" ) ) {
        choice3.setIcon( getImageIcon( "stickyfingers button.png" ) );
      }
    }
  }

  // action listener for detecting which menu button was pressed
  private class MenuButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setVisible( false );
        credits.setVisible( false );
        exit.setVisible( false );
        secretButt.setVisible( false );
        // slider.setVisible( false );
        setNewSong( "Psycho_Song" );
        panel.remove( mainMenu );
        panel.add( label );
        ( ( FadeLabel ) label ).fadeImages();
        Timer timer = new Timer( 1900, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( label );
            panel.add( new JLabel( companyLogo ) );
            panel.revalidate();
          }
        } );
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( 12000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( panel.getComponent( 3 ) );
            panel.add( new JLabel( titleScreen ) );
            panel.revalidate();
            label = new FadeLabel( "0.5_title.gif", "1_intro.gif", 1000 );
            label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
          }
        } );
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 14000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( panel.getComponent( 3 ) );
            panel.add( label );
            panel.revalidate();
            ( ( FadeLabel ) label ).fadeImages();
          }
        } );
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 1000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            for( int i = 3; i < panel.getComponentCount(); i++ ) {
              panel.remove( panel.getComponent( i ) );
            }
            panel.add( new JLabel( intro1Image ) );
            panel.revalidate();
          }
        } );
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 500, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            startIntro();
          }
        } );
        timer.setRepeats( false );
        timer.start();
      } else if( evt.getSource().equals( credits ) ) {
        resetAll();
        setNewSong( "credits song" );
        frame.add( panel );
        bgLabel.setIcon( getImageIcon( "credits 1.gif" ) );
        JLabel l1 = new JLabel( getImageIcon( "credits 2.png" ) );
        JLabel l2 = new JLabel( getImageIcon( "credits 3.gif" ) );
        JLabel l3 = new JLabel( getImageIcon( "credits 5.gif" ) );
        JLabel l4 = new JLabel( getImageIcon( "credits 4.gif" ) );
        JButton back = new JButton( getImageIcon( "Back Button.png" ) );
        back.setRolloverIcon( getImageIcon( "Back Button Hovered.png" ) );
        back.addActionListener( new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent evt ) {
            initMainMenu();
          }
        });
        setButtonTransparent( back );
        panel.add( back );
        layout.putConstraint( SpringLayout.SOUTH, back, 0, SpringLayout.SOUTH, panel );
        panel.add( l4 );
        layout.putConstraint( SpringLayout.EAST, l4, 0, SpringLayout.EAST, panel );
        panel.add( l3 );
        panel.add( l2 );
        layout.putConstraint( SpringLayout.WEST, l2, 440, SpringLayout.WEST, panel );
        layout.putConstraint( SpringLayout.NORTH, l2, 5, SpringLayout.NORTH, panel );
        panel.add( l1 );
        layout.putConstraint( SpringLayout.WEST, l1, 443, SpringLayout.WEST, panel );
        panel.add( bgLabel );
        panel.revalidate();
      } else if( evt.getSource().equals( exit ) ) {
        //frame.dispatchEvent( new java.awt.event.WindowEvent( frame, java.awt.event.WindowEvent.WINDOW_CLOSING ) );
        resetAll();
        ch2Cinematic();
      } else if( evt.getSource().equals( secretButt ) ) { // USE ME TO DEBUG AND SKIP ALL THE PREVIOUS SCENES
        /*
        mainMenu.setIcon( secretImage );
        play.setVisible( false );
        credits.setVisible( false );
        // slider.setVisible( false );
        secretButt.setEnabled( false );
        mainMenu.revalidate();
        */
        resetAll();
        goToLabyrinth( true );
      }
    }
  }

  // handles the starting of the intro
  // timers are used to mimic a story-cutscene
  private void startIntro() {
    layout.putConstraint( SpringLayout.NORTH, skipIntro, 5, SpringLayout.NORTH, panel );
    skipIntro.setVisible( true );
    skipIntro.addMouseListener( new MouseAdapter() {
      @Override
      public void mouseClicked( MouseEvent evt ) {
        panel.remove( panel.getComponent( 3 ) );
        panel.remove( skipIntro );
        panel.add( new JLabel( chapter1Image ) );
        textArea.setVisible( false );
        textLabel.setVisible( false );
        panel.revalidate();
        panel.addMouseListener( new MouseAdapter() {
          @Override
          public void mouseClicked( MouseEvent arg0 ) {
            startTutorial();
          }
        } );
        introSkipped = true;
        mediaPlayer.stop();
      }
    } );
    placeComponent( 203, 618, textArea );
    textArea.setVisible( true );
    placeComponent( 183, 598, textLabel );
    textLabel.setVisible( true );
    layout.putConstraint( SpringLayout.WEST, dialogueArea, 348, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, dialogueArea, 618, SpringLayout.NORTH, panel );
    placeComponent( 183, 563, dialogueLabel );
    animateText( "This is Allan. Recently his life has been getting quite boring, and he's wondering how his childhood friends are doing.", 15 );
    textArea.setFont( new Font( "Pixel-Noir", Font.PLAIN, 15 ) );
    dialogueArea.setFont( new Font( "Pixel-Noir", Font.PLAIN, 15 ) );
    panel.revalidate();
    Timer t = new Timer( 6250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          textArea.setText( "" );
          animateText( "After dealing with the smoggy air that comes with living in China, coupled with the low quality internet spewing out of "
              + "his busted router, Allan has decided to end it all...", 15 );
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 6250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          textArea.setText( "" );
          panel.add( new JLabel( intro2Image ) );
          panel.revalidate();
          Timer tempTimer = new Timer( 500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              animateText( "...so he packed his bags and decided to venture out into the great beyond.", 25 );
            }
          } );
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 4500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro3Image ) );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 10500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro4Image ) );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 3500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro5Image ) );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 10000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro6Image ) );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          panel.revalidate();
          animateText( "Allan wonders why none of his friends have ever kept in touch with him as of recently. "
              + "He texts the boys back at Irvine with no avail.", 15 );
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 9000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro7Image ) );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 5000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro8Image ) );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          panel.revalidate();
          animateText( "Deciding that his life was more enjoyable with the people that respected him, Allan leaves behind his drab "
              + "life to seek out the happiness that has been locked away for so long.", 25 );
          Timer tempTimer = new Timer( 8500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setText( "" );
              animateText( "The last glimpse of Shanghai fades into the background as the plane heads overseas.", 25 );
            }
          } );
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 13500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro9Image ) );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro10Image ) );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro11Image ) );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          panel.revalidate();
          animateText(
              "The train ride back to the motherland was very nostalgic. Allan breathes in the fresh air he once knew as a child and relishes it. "
                  + "He wonders if his friends still live nearby.",
              25 );
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 13500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro12Image ) );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( getImageIcon( "12.5_intro.gif" ) ) );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          textArea.setText( "" );
          panel.revalidate();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.add( new JLabel( intro13Image ) );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          panel.revalidate();
          animateText( "Irvine's glistening buildings and green trees come into full view as the train comes to a stop.\r\n"
              + "\r\nWelcome back friend. Welcome back to your roots.\r\n", 35 );
          Timer tempTimer = new Timer( 11000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( false );
              textLabel.setVisible( false );
              textArea.setText( "" );
            }
          } );
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 13000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        if( !introSkipped ) {
          panel.remove( panel.getComponent( 3 ) );
          panel.remove( skipIntro );
          panel.add( new JLabel( chapter1Image ) );
          panel.revalidate();
          panel.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent arg0 ) {
              startTutorial();
            }
          } );
          Timer tempTimer = new Timer( 800, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              mediaPlayer.stop();
            }
          } );
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
      }
    } );
    t.setRepeats( false );
    t.start();
  }

  // starts the tutorial section of the game
  private void startTutorial() {
    setNewSong( "tutorial_song" );
    prophecyText = new JTextArea( "To: Allan\r\n" + "From: Chris Lee\r\n" + "\r\n" + "Allan if you are reading this we are in grave danger...\r\n"
        + "All of us have been enslaved by the evil conglomerate because we tried to defend our nuggets.\r\n"
        + "I only have time to write a couple of clues to each of our whereabouts." );
    prophecyText.setPreferredSize( new Dimension( 250, 300 ) );
    prophecyText.addMouseListener( new TutorialScreenClickedMouseAdapter() );
    prophecyText.setOpaque( false );
    prophecyText.setFocusable( false );
    prophecyText.setEditable( false );
    prophecyText.setWrapStyleWord( true );
    prophecyText.setLineWrap( true );
    prophecyText.setFont( new Font( "Pixel-Noir", Font.PLAIN, 10 ) );
    prophecyText.setForeground( new Color( 139, 69, 19 ) );
    prophecyText.setBorder( BorderFactory.createEmptyBorder( 2, 0, 0, 0 ) );
    layout.putConstraint( SpringLayout.WEST, prophecyText, 550, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, prophecyText, 150, SpringLayout.NORTH, panel );
    panel.add( prophecyText );
    prophecyText.setVisible( false );
    bgLabel = new JLabel( tutorial1Image );
    headLabel = new JLabel( allanHeadImage );
    nameLabel = new JLabel( "Allan" );
    nameLabel.setPreferredSize( new Dimension( 200, 40 ) );
    nameLabel.setFont( new Font( "Pixel-Noir", Font.BOLD, 14 ) );
    nameLabel.setOpaque( false );
    nameLabel.setForeground( Color.WHITE );
    headLabel.setPreferredSize( new Dimension( 150, 150 ) );
    headLabel.setOpaque( false );
    layout.putConstraint( SpringLayout.WEST, headLabel, 185, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, headLabel, 560, SpringLayout.NORTH, panel );
    panel.add( headLabel );
    headLabel.setVisible( false );
    layout.putConstraint( SpringLayout.WEST, nameLabel, 350, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, nameLabel, 575, SpringLayout.NORTH, panel );
    panel.add( nameLabel );
    nameLabel.setVisible( false );
    removePanelMouseListener();
    panel.add( dialogueArea );
    dialogueArea.setVisible( false );
    dialogueArea.addMouseListener( new TutorialScreenClickedMouseAdapter() );
    panel.add( dialogueLabel );
    dialogueLabel.setVisible( false );
    panel.remove( panel.getComponent( 2 ) );
    label = new FadeLabel( "14_chapter.gif", "1_tutorial.gif", 3000 );
    label.setPreferredSize( new Dimension( 1366, 768 ) );
    panel.add( label );
    panel.revalidate();
    ( ( FadeLabel ) label ).fadeImages();
    animationFinished = false;
    Timer t = new Timer( 2900, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( label );
        panel.add( bgLabel );
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        panel.revalidate();
        animateText( "Damn that flight was long... finally made it back to Irvine.\r\n", 50, dialogueArea );
      }
    } );
    t.setRepeats( false );
    t.start();
    panel.addMouseListener( new TutorialScreenClickedMouseAdapter() );
    textArea.addMouseListener( new TutorialScreenClickedMouseAdapter() );
  }

  private class TutorialScreenClickedMouseAdapter extends MouseAdapter {
    @Override
    public void mouseClicked( MouseEvent e ) {
      if( stageCnt[ 0 ] == 0 && animationFinished  && fadeFinished ) {
        animationFinished = false;
        final Timer tempTimer = new Timer( 50, null );
        tempTimer.addActionListener( new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent evt ) {
            String s = "Huh what's this?";
            dialogueArea.append( "" + s.charAt( stringCounter ) );
            stringCounter++;
            if( stringCounter >= s.length() ) {
              tempTimer.stop();
              stringCounter = 0;
              animationFinished = true;
            }
          }
        } );
        tempTimer.start();
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 1 && animationFinished ) {
        animationFinished = false;
        bgLabel.setIcon( tutorialClosedProphecyImage );
        dialogueArea.setVisible( false );
        dialogueLabel.setVisible( false );
        headLabel.setVisible( false );
        nameLabel.setVisible( false );
        dialogueArea.setText( "" );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 2 ) {
        bgLabel.setIcon( prophecyTitleImage );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 3 ) {
        bgLabel.setIcon( tutorialOpenProphecyImage );
        prophecyText.setVisible( true );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 4 ) {
        prophecyText.setText( "First is thy brown friend, stuck in his past, and unable to leave his own shadow.\r\n"
            + "Second is thou large fella, lost in a vastness of green, seeking divine light.\r\n"
            + "Third tis be hairier than the rest, opposite of thy first, engulfed in metal.\r\n" + "Jason is a weeb.\r\n"
            + "Fifth is thee strange fisherman, lost at sea, and searching for a massive Dick.\r\n"
            + "And finally, thine vile himself, is held captive in ye Dank Gulag, being forced to..." );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 5 ) {
        prophecyText.setText( "*Tutorial\r\n\r\n" + "Thanks for playing adventurer!\r\n\r\n"
            + "You will be in charge of every move Allan makes in his nast adventure to save his friends.\r\n"
            + "Many scenarios will be brought up and you are the one to decide Allan's fate by choosing amongst several options that would be provided."
            + "\r\n(use your mouse to select each option)\r\n\r\nTIP: It may be best to avoid spam clicking the mouse." );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 6 ) {
        prophecyText.setText( "Each new area will yield unique challenges and scenarios so don't get used to a single strategy.\r\n"
            + "Also, each area will have a boss you must defeat in order to move on to the next.\r\n\r\n"
            + "Good luck out there friend, and may the odds be forever in your favor." );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 7 ) {
        panel.remove( prophecyText );
        dialogueArea.setVisible( true );
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        dialogueLabel.setVisible( true );
        bgLabel.setIcon( tutorial1Image );
        panel.revalidate();
        animateText( "Alright it looks like my boys are in danger.\r\n" + "It seems like Rudraksha is first on the list.\r\n"
            + "I wonder where I should look first...", 50, dialogueArea );
        stageCnt[ 0 ]++;
      } else if( animationFinished && stageCnt[ 0 ] == 8 ) {
        animationFinished = false;
        bgLabel.setIcon( tutorialTransitionImage );
        headLabel.setVisible( false );
        nameLabel.setVisible( false );
        dialogueArea.setVisible( false );
        dialogueLabel.setVisible( false );
        dialogueArea.setText( "" );
        textArea.setText( "" ); // not really sure why needed
        mediaPlayer.stop();
        Timer tempT = new Timer( 1000, new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            textArea.setVisible( true );
            textLabel.setVisible( true );
            animateText( "Allan decides to begin his adventure by searching for Rudraksha.\r\n"
                + "The first place that comes to mind are the local basketball courts.", 35 );
            stageCnt[ 0 ]++;
            setNewSong( "sunflower_song" );
          }
        } );
        tempT.setRepeats( false );
        tempT.start();
      } else if( animationFinished && stageCnt[ 0 ] == 9 ) {
        textArea.setText( "" );
        textArea.setVisible( false );
        textLabel.setVisible( false );
        animationFinished = false;
        removePanelMouseListener();
        startChapter1();
      }
    }
  }

  private void startChapter1() {
    curStage = "jt_dialogue";
    resetScreen();
    frame.add( panel );
    stageCnt = new int[] { 0 }; // used to keep track of current stage and by pass final error
    bgLabel.setIcon( chapter11Image );
    choice1 = new JButton( getImageIcon( "greet.png" ) );
    choice2 = new JButton( getImageIcon( "passive.png" ) );
    choice3 = new JButton( getImageIcon( "nast.png" ) );
    choice4 = new JButton( getImageIcon( "reply.png" ) );
    choice5 = new JButton( getImageIcon( "nastreply.png" ) );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    panel.add( choice4 );
    panel.add( choice5 );
    placeComponent( 383, 280, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice1, 338, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.SOUTH, choice1, 0, SpringLayout.NORTH, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice3, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice3, 0, SpringLayout.SOUTH, choice2 );
    placeComponent( 383, 260, choice4 );
    layout.putConstraint( SpringLayout.WEST, choice5, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice5, 0, SpringLayout.SOUTH, choice4 );
    setButtonTransparent( choice1 );
    setButtonTransparent( choice2 );
    setButtonTransparent( choice3 );
    setButtonTransparent( choice4 );
    setButtonTransparent( choice5 );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    choice4.setVisible( false );
    choice5.setVisible( false );
    choice1.addMouseListener( new MenuButtonMouseAdapter() );
    choice2.addMouseListener( new MenuButtonMouseAdapter() );
    choice3.addMouseListener( new MenuButtonMouseAdapter() );
    choice4.addMouseListener( new MenuButtonMouseAdapter() );
    choice5.addMouseListener( new MenuButtonMouseAdapter() );
    choice1.addMouseListener( new Chapter1ChoiceHandler() );
    choice2.addMouseListener( new Chapter1ChoiceHandler() );
    choice3.addMouseListener( new Chapter1ChoiceHandler() );
    choice4.addMouseListener( new Chapter1ChoiceHandler() );
    choice5.addMouseListener( new Chapter1ChoiceHandler() );
    headLabel.setIcon( jtHeadImage );
    nameLabel.setText( "JT" );
    panel.add( bgLabel );
    panel.revalidate();
    panel.addMouseListener( new Chapter1ScreenClickedMouseAdapter() );
    dialogueArea.addMouseListener( new Chapter1ScreenClickedMouseAdapter() );
    textArea.addMouseListener( new Chapter1ScreenClickedMouseAdapter() );
  }

  private class Chapter1ScreenClickedMouseAdapter extends MouseAdapter {
    public void mouseClicked( MouseEvent e ) {
      if( stageCnt[ 0 ] == 0 ) {
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        animateText( "What are you looking at boy?", 25, dialogueArea );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 1 && animationFinished ) {
        animationFinished = false;
        choice1.setVisible( true );
        choice2.setVisible( true );
        choice3.setVisible( true );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 3 && animationFinished ) {
        animationFinished = false;
        headLabel.setIcon( jtHeadImage );
        nameLabel.setText( "JT" );
        dialogueArea.setText( "" );

        if( choiceNum == 1 ) {
          animateText( "I think I know what you're looking for. Win against me 1v1 and I'll hand it over.", 35, dialogueArea );
        } else if( choiceNum == 2 ) {
          animateText( "Yeah that's right, bug off back to China ya asian gremlin.", 35, dialogueArea );
          Timer tempTimer = new Timer( 2000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice4.setVisible( true );
              choice5.setVisible( true );
            }
          } );
          tempTimer.setRepeats( false );
          tempTimer.start();
        } else if( choiceNum == 3 ) {
          animateText( "What did you say? I didn't quite catch that.", 35, dialogueArea );
          Timer tempTimer = new Timer( 2000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice4.setVisible( true );
              choice5.setVisible( true );
            }
          } );
          tempTimer.setRepeats( false );
          tempTimer.start();
        }
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 4 && animationFinished && choiceNum == 1 ) {
        animationFinished = false;
        headLabel.setIcon( allanHeadImage );
        nameLabel.setText( "Allan" );
        dialogueArea.setText( "" );
        animateText( "Really? Sounds good. Bring it on!", 35, dialogueArea );
        stageCnt[ 0 ] = 10;
      } else if( stageCnt[ 0 ] == 5 && animationFinished && choiceNum == 4 ) {
        animationFinished = false;
        headLabel.setIcon( jtHeadImage );
        nameLabel.setText( "JT" );
        dialogueArea.setText( "" );
        animateText( "Oh really? I think I found it laying around near my gear. Tell you what, since I'm feeling oh so generous, "
            + "beat me 1v1 and I'll let you have it.", 35, dialogueArea );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 6 && animationFinished && choiceNum == 4 ) {
        animationFinished = false;
        headLabel.setIcon( allanHeadImage );
        nameLabel.setText( "Allan" );
        dialogueArea.setText( "" );
        animateText( "Really? Sounds good. Bring it on!", 35, dialogueArea );
        stageCnt[ 0 ] = 10;
      } else if( stageCnt[ 0 ] == 5 && animationFinished && choiceNum == 5 ) {
        animationFinished = false;
        headLabel.setIcon( jtHeadImage );
        nameLabel.setText( "JT" );
        dialogueArea.setText( "" );
        animateText( "Oh you've done it now boy. Square up fool. I'll beat you so hard it'll make your    ancestors dizzy!", 35, dialogueArea );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 6 && animationFinished && choiceNum == 5 ) {
        animationFinished = false;
        headLabel.setIcon( allanHeadImage );
        nameLabel.setText( "Allan" );
        dialogueArea.setText( "" );
        animateText( "Alright bring it! I'll wipe the floor with ya!", 35, dialogueArea );
        stageCnt[ 0 ] = 10;
      } else if( stageCnt[ 0 ] == 10 && animationFinished ) {
        animationFinished = false;
        headLabel.setIcon( jtHeadImage );
        nameLabel.setText( "JT" );
        dialogueArea.setText( "" );
        animateText( "Listen up short stuff. My court my ball. First to 11 takes game and shots count as 1 point but 3's stay 3. And tell ya what, "
            + "if you somehow manage to dunk on meh, I'll admit defeat.", 35, dialogueArea );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 11 && animationFinished ) {
        startBallGame();
      }
    }
  }

  private class Chapter1ChoiceHandler extends MouseAdapter {
    public void mouseClicked( MouseEvent e ) {
      if( stageCnt[ 0 ] == 2 ) {
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        headLabel.setIcon( allanHeadImage );
        nameLabel.setText( "Allan" );
        if( e.getSource().equals( choice1 ) ) {
          choiceNum = 1;
          animateText( "Sorry friend, my name is Allan. I was just looking for something.", 35, dialogueArea );
        } else if( e.getSource().equals( choice2 ) ) {
          choiceNum = 2;
          animateText( "Nothing, my bad bruh.", 35, dialogueArea );
        } else if( e.getSource().equals( choice3 ) ) {
          choiceNum = 3;
          animateText( "Clearly some kind of large subspecies african male who can't hoop if his life depended on it.", 35, dialogueArea );
        }
        choice1.setVisible( false );
        choice2.setVisible( false );
        choice3.setVisible( false );
        stageCnt[ 0 ]++;
      } else if( stageCnt[ 0 ] == 4 ) {
        headLabel.setIcon( allanHeadImage );
        nameLabel.setText( "Allan" );
        if( choiceNum == 2 ) {
          animationFinished = false;
          if( e.getSource().equals( choice4 ) ) {
            choiceNum = 4;
            animateText( "Actually, I was looking for someone else.", 35, dialogueArea );
          } else if( e.getSource().equals( choice5 ) ) {
            choiceNum = 5;
            animateText( "Bug off? How about you quit yappin' with them big lips of yours before I go sicko mode!", 35, dialogueArea );
          }
        } else if( choiceNum == 3 ) {
          animationFinished = false;
          if( e.getSource().equals( choice4 ) ) {
            choiceNum = 4;
            animateText( "It was nothing I swear. I was just looking for something.", 35, dialogueArea );
          } else if( e.getSource().equals( choice5 ) ) {
            choiceNum = 5;
            animateText( "You heard me fool! I see them bricks you throwin' up!", 35, dialogueArea );
          }
        }
        choice4.setVisible( false );
        choice5.setVisible( false );
        stageCnt[ 0 ]++;
      }
    }
  }

  private void startBallGame() {
    curStage = "ball_game";
    resetScreen();
    setNewSong( "ball game song" );
    frame.add( panel );
    ImageIcon beforeBallTransition = getImageIcon( "before ball transition.gif" );
    beforeBallTransition.getImage().flush();
    bgLabel.setIcon( beforeBallTransition );
    versatileLabel1 = new JLabel( getImageIcon( "ball game scoreboard.gif" ) );
    versatileLabel2 = new JLabel( getImageIcon( "score_1.png" ) ); // player score
    versatileLabel3 = new JLabel( getImageIcon( "score_1.png" ) ); // jt score
    panel.add( versatileLabel2 );
    versatileLabel2.setVisible( false );
    panel.add( versatileLabel3 );
    versatileLabel3.setVisible( false );
    panel.add( versatileLabel1 );
    versatileLabel1.setVisible( false );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    panel.add( bgLabel );
    placeComponent( 383, 280, choice2 );
    placeComponent( 60, 7, versatileLabel2 );
    placeComponent( 284, 7, versatileLabel3 );
    layout.putConstraint( SpringLayout.WEST, choice1, 338, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.SOUTH, choice1, 0, SpringLayout.NORTH, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice3, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice3, 0, SpringLayout.SOUTH, choice2 );
    resetChoiceButtons();
    choice1.setIcon( getImageIcon( "3 point shot.png" ) );
    choice2.setIcon( getImageIcon( "jump.png" ) );
    choice3.setIcon( getImageIcon( "dunk.png" ) );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    Timer tempTimer = new Timer( 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent e ) {
        bgLabel.setIcon( getImageIcon( "ball menu.gif" ) );
        versatileLabel1.setVisible( true );
        choice1.setVisible( true );
        choice2.setVisible( true );
        choice3.setVisible( true );
        choice1.addMouseListener( new BallGameChoiceHandler() );
        choice2.addMouseListener( new BallGameChoiceHandler() );
        choice3.addMouseListener( new BallGameChoiceHandler() );
      }
    } );
    tempTimer.setRepeats( false );
    tempTimer.start();
    counter = 1;
    ballScore = 0;
    panel.revalidate();
  }

  private void displayBallGameMenu() {
    if( ballScore >= 11 ) {
      bgLabel.setIcon( getImageIcon( "ball game win.gif" ) );
      Timer timer = new Timer( 6500, new ActionListener(){ 
        public void actionPerformed( ActionEvent evt ) {
          panel.addMouseListener( new MouseAdapter() {
            public void mouseClicked( MouseEvent evt ) {
              if( !ballGameFinished ) {
                ballGameFinished = true;
                endBallGame( false );
              }
            }
          });
        }
      });
      timer.setRepeats( false );
      timer.start();
      return;
    } else {
      bgLabel.setIcon( getImageIcon( "ball menu.gif" ) );
      if( ballScore > 0 ) {
        versatileLabel2.setIcon( getImageIcon( "score_" + ballScore + ".png" ) );
        versatileLabel2.setVisible( true );
      }
      versatileLabel3.setIcon( getImageIcon( "score_" + counter + ".png" ) );
      choice1.setVisible( true );
      choice2.setVisible( true );
      choice3.setVisible( true );
      versatileLabel1.setVisible( true );
      versatileLabel3.setVisible( true );

      if( jtBlock ) {
        String [] jtSayings = { "OUTTA HERE BOY!", "WATCHU TRYIN?", "NOT IN MY HOUSE!", 
          "STAY IN YO LANE!", "BUH BYE! THAT ALL YOU GOT?", "PATHETIC!" };
        int rng = ( int )( Math.random() * jtSayings.length );
        headLabel.setIcon( jtHeadImage );
        nameLabel.setText( "JT" );
        dialogueLabel.setVisible( true );
        dialogueArea.setVisible( true );
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        animateText( jtSayings[ rng ], 35, dialogueArea );
        jtBlock = false;
      } else if( counter == 6 && counter > ballScore ) {
        headLabel.setIcon( jtHeadImage );
        nameLabel.setText( "JT" );
        dialogueLabel.setVisible( true );
        dialogueArea.setVisible( true );
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        animateText( "Is this all you got little man?", 35, dialogueArea );
      }
    }
    panel.revalidate();
  }

  private class BallGameChoiceHandler extends MouseAdapter {
    public void mouseClicked( MouseEvent e ) {
      choice1.setVisible( false );
      choice2.setVisible( false );
      choice3.setVisible( false );
      versatileLabel1.setVisible( false );
      versatileLabel2.setVisible( false );
      versatileLabel3.setVisible( false );
      headLabel.setVisible( false );
      dialogueArea.setVisible( false );
      dialogueArea.setText( "" );
      dialogueLabel.setVisible( false );
      nameLabel.setVisible( false );
      if( e.getSource().equals( choice1 ) ) {
        if( ( ( int )( Math.random() * 10 + 1 ) ) <= 4 ) { // 40% chance 3 pt
          ballScore += 3;
          int shotNumber = ( int )( Math.random() * 2 ) + 1;
          ImageIcon shot = getImageIcon( "allan 3 pt_" + shotNumber + ".gif" );
          shot.getImage().flush();
          bgLabel.setIcon( shot );
        } else {
          ImageIcon shot = getImageIcon( "allan 3 pt miss.gif" );
          shot.getImage().flush();
          bgLabel.setIcon( shot );
        }
        Timer jtScoreTimer = new Timer( 4000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            if( ballScore >= 11 ) {
              displayBallGameMenu();
              return;
            } else if( counter >= 10 ) {
              bgLabel.setIcon( getImageIcon( "ball game lose.gif") );
              counter = 0;
              Timer t = new Timer( 6500, new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e ) {
                  panel.addMouseListener( new MouseAdapter() {
                    public void mouseClicked( MouseEvent evt ) {
                      counter = 0;
                      endBallGame( false );
                    }
                  });
                }
              } );
              t.start();
              t.setRepeats( false );
              return;
            } else {
              int rng = ( int )( Math.random() * 3 ) + 1;
              ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
              shot.getImage().flush();
              bgLabel.setIcon( shot );
              counter++;
              Timer timer = new Timer( 6000, new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent evt ) {
                  if( counter != 0 ) {
                    displayBallGameMenu();
                  }
                }
              } );
              timer.setRepeats( false );
              timer.start();
            }
          }
        } );
        jtScoreTimer.setRepeats( false );
        jtScoreTimer.start();
      } else if( e.getSource().equals( choice2 ) ) {
        if( ( int )( Math.random() * 10 ) + 1 <= 8 ) { // 80% chance jumpshot
          ballScore++;
          int shotNumber = ( int )( Math.random() * 4 ) + 1;
          if( shotNumber == 1 || shotNumber == 3 || shotNumber == 4 ) {
            ImageIcon shot = getImageIcon( "allan jump shot_" + shotNumber + ".gif" );
            shot.getImage().flush();
            bgLabel.setIcon( shot );
            int gifDelay = 4000;
            if( shotNumber == 4 )
              gifDelay = 5500;
            Timer timer = new Timer( gifDelay, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent e ) {
                if( ballScore >= 11 ) {
                  displayBallGameMenu();
                  return;
                }
                if( counter >= 10 ) {
                  bgLabel.setIcon( getImageIcon( "ball game lose.gif") );
                  counter = 0;
                  Timer t = new Timer( 6500, new ActionListener() {
                    @Override
                    public void actionPerformed( ActionEvent e ) {
                      panel.addMouseListener( new MouseAdapter() {
                        public void mouseClicked( MouseEvent evt ) {
                          counter = 0;
                          endBallGame( false );
                        }
                      });
                    }
                  } );
                  t.start();
                  t.setRepeats( false );
                  return;
                } else {
                  int rng = ( int )( Math.random() * 3 ) + 1;
                  ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
                  shot.getImage().flush();
                  bgLabel.setIcon( shot );
                  counter++;
                  Timer t = new Timer( 6000, new ActionListener() {
                    @Override
                    public void actionPerformed( ActionEvent evt ) {
                      if( counter != 0 ) {
                        displayBallGameMenu();
                      }
                    }
                  } );
                  t.setRepeats( false );
                  t.start();
                }
              }
            } );
            timer.setRepeats( false );
            timer.start();
          } else if( shotNumber == 2 ) {
            ImageIcon shot = getImageIcon( "allan jump shot_" + shotNumber + ".gif" );
            shot.getImage().flush();
            bgLabel.setIcon( shot );
            Timer timer = new Timer( 9000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent e ) {
                if( ballScore >= 11 ) {
                  displayBallGameMenu();
                  return;
                }
                if( counter >= 10 ) {
                  bgLabel.setIcon( getImageIcon( "ball game lose.gif") );
                  counter = 0;
                  Timer t = new Timer( 6500, new ActionListener() {
                    @Override
                    public void actionPerformed( ActionEvent e ) {
                      panel.addMouseListener( new MouseAdapter() {
                        public void mouseClicked( MouseEvent evt ) {
                          counter = 0;
                          endBallGame( false );
                        }
                      });
                    }
                  } );
                  t.start();
                  t.setRepeats( false );
                  return;
                } else {
                  int rng = ( int )( Math.random() * 3 ) + 1;
                  ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
                  shot.getImage().flush();
                  bgLabel.setIcon( shot );
                  counter++;
                  Timer t = new Timer( 6000, new ActionListener() {
                    @Override
                    public void actionPerformed( ActionEvent evt ) {
                      if( counter != 0 ) {
                        displayBallGameMenu();
                      }
                    }
                  } );
                  t.setRepeats( false );
                  t.start();
                }
              }
            } );
            timer.setRepeats( false );
            timer.start();
          }
        } else {
          int shotNumber = ( int )( Math.random() * 2 ) + 1;
          ImageIcon shot = getImageIcon( "allan jump shot miss_" + shotNumber + ".gif" );
          shot.getImage().flush();
          bgLabel.setIcon( shot );
          int time = 4000;
          if( shotNumber == 2 ) {
            time = 8000;
          }
          Timer timer = new Timer( time, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              if( counter >= 10 ) {
                bgLabel.setIcon( getImageIcon( "ball game lose.gif") );
                counter = 0;
                Timer t = new Timer( 6500, new ActionListener() {
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    panel.addMouseListener( new MouseAdapter() {
                      public void mouseClicked( MouseEvent evt ) {
                        counter = 0;
                        endBallGame( false );
                      }
                    });
                  }
                } );
                t.start();
                t.setRepeats( false );
                return;
              } else {
                int rng = ( int )( Math.random() * 3 ) + 1;
                ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
                shot.getImage().flush();
                bgLabel.setIcon( shot );
                counter++;
                Timer t = new Timer( 6000, new ActionListener() {
                  @Override
                  public void actionPerformed( ActionEvent evt ) {
                    if( counter != 0 ) {
                      displayBallGameMenu(); 
                    }
                  }
                } );
                t.setRepeats( false );
                t.start();
              }
            }
          } );
          timer.setRepeats( false );
          timer.start();
        }
      } else if( e.getSource().equals( choice3 ) ) {
        if( ( ( int )( Math.random() * 100 ) ) + 1 <= 8 ) { // 8% chance dunk
          ballScore = 69;
          bgLabel.setIcon( getImageIcon( "allan dunk.gif" ) );
          Timer timer = new Timer( 6500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              bgLabel.setIcon( getImageIcon( "ball game win.gif" ) );
              Timer timer = new Timer( 6500, new ActionListener(){ 
                public void actionPerformed( ActionEvent evt ) {
                  panel.addMouseListener( new MouseAdapter() {
                    public void mouseClicked( MouseEvent evt ) {
                      endBallGame( false );
                    }
                  });
                }
              });
              timer.setRepeats( false );
              timer.start();
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else {
          ImageIcon shot = getImageIcon( "allan dunk blocked.gif" );
          shot.getImage().flush();
          bgLabel.setIcon( shot );
          jtBlock = true;
          Timer timer = new Timer( 3200, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              if( counter >= 10 ) {
                bgLabel.setIcon( getImageIcon( "ball game lose.gif") );
                counter = 0;
                Timer t = new Timer( 6500, new ActionListener() {
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    panel.addMouseListener( new MouseAdapter() {
                      public void mouseClicked( MouseEvent evt ) {
                        endBallGame( false );
                      }
                    });
                  }
                } );
                t.start();
                t.setRepeats( false );
                return;
              } else {
                int rng = ( int )( Math.random() * 3 ) + 1;
                ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
                shot.getImage().flush();
                bgLabel.setIcon( shot );
                counter++;
                Timer t = new Timer( 6000, new ActionListener() {
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    if( counter != 0 ) {
                      displayBallGameMenu();
                    }
                  }
                });
                t.setRepeats( false );
                t.start();
              }
            }
          } );
          timer.setRepeats( false );
          timer.start();
        }
      }
    }
  }

  public void endBallGame( boolean skipWin ) {
    for( MouseListener m: panel.getMouseListeners() ) {
      panel.removeMouseListener( m );
      System.err.println( "MouseListener removed" );
    }
    curStage = "ball_game_end";
    resetScreen();
    panel.add( choice1 );
    panel.add( choice2 );
    resetChoiceButtons();
    choice1.setIcon( getImageIcon( "district button.png" ) );
    choice2.setIcon( getImageIcon( "downtown button.png" ) );
    choice1.setVisible( false );
    choice2.setVisible( false );
    panel.add( bgLabel );
    frame.add( panel );
    panel.revalidate();
    if( ballScore >= 11 ) {
      ballScore = 0;
      if( skipWin ){
        bgLabel.setIcon( getImageIcon( "after ball win transition.gif" ) );
        setNewSong( "sunflower_song" );
        dialogueArea.setText( "" );
        dialogueLabel.setVisible( true );
        dialogueArea.setVisible( true );
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        nameLabel.setText( "Allan" );
        animateText( "Man that guy was soft. Heh.\r\nNot sure why I was expecting a challenge honestly.\r\n" +
        "I wonder where I should search next.", 50, dialogueArea );
        for( MouseListener m: panel.getMouseListeners() ) {
          panel.removeMouseListener( m );
        }
        Timer timer2 = new Timer( 6500, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setVisible( true );
            choice2.setVisible( true );
            choice1.addMouseListener( new MouseAdapter() {
              public void mouseClicked( MouseEvent evt ) {
                choice1.setVisible( false );
                choice2.setVisible( false );
                choice1.removeMouseListener( this );
                goToDistrict();
              }
            });
            choice2.addMouseListener( new MouseAdapter() {
              public void mouseClicked( MouseEvent evt ) {
                choice1.setVisible( false );
                choice2.setVisible( false );
                choice2.removeMouseListener( this );
                goToDowntown();
              }
            });
            panel.revalidate();
          }
        } );
        timer2.setRepeats( false );
        timer2.start();
      } else {
        bgLabel.setIcon( getImageIcon( "after ball win.gif" ) );
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        headLabel.setVisible( true );
        headLabel.setIcon( getImageIcon( "JT_head.gif" ) );
        nameLabel.setVisible( true );
        nameLabel.setText( "JT" );
        dialogueArea.setText( null );
        animateText( "Darn! You got me.\r\nAlright I'll admit you're pretty decent bruh.\r\n" + 
        "Here's your reward.", 35, dialogueArea );
        Timer timer = new Timer( 5000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setText( null );
            headLabel.setIcon( allanHeadImage );
            nameLabel.setText( "Allan" );
            animateText( "HAHA. What's up with that? Afraid to shoot?\r\n" + 
            "You thinkin of donating all dem bricks to the third little pig?", 35, dialogueArea );
          }
        } );
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 6000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setText( null );
            animateText( "Woah an Exodia arm.\r\nWonder why someone would leave this here of all places.\r\n" +
            "I guess I'll just hang on to it just in case.", 35, dialogueArea );
          }
        } );
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 6000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            panel.addMouseListener( new MouseAdapter() {
              public void mouseClicked( MouseEvent evt ) {
                bgLabel.setIcon( getImageIcon( "after ball win transition.gif" ) );
                setNewSong( "sunflower_song" );
                dialogueArea.setText( "" );
                animateText( "Man that guy was soft. Heh.\r\nNot sure why I was expecting a challenge honestly.\r\n" +
                "I wonder where I should search next.", 50, dialogueArea );
                for( MouseListener m: panel.getMouseListeners() ) {
                  panel.removeMouseListener( m );
                }
                Timer timer2 = new Timer( 6500, new ActionListener() {
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    choice1.setVisible( true );
                    choice2.setVisible( true );
                    choice1.addMouseListener( new MouseAdapter() {
                      public void mouseClicked( MouseEvent evt ) {
                        choice1.setVisible( false );
                        choice2.setVisible( false );
                        choice1.removeMouseListener( this );
                        goToDistrict();
                      }
                    });
                    choice2.addMouseListener( new MouseAdapter() {
                      public void mouseClicked( MouseEvent evt ) {
                        choice1.setVisible( false );
                        choice2.setVisible( false );
                        choice2.removeMouseListener( this );
                        goToDowntown();
                      }
                    });
                    panel.revalidate();
                  }
                } );
                timer2.setRepeats( false );
                timer2.start();
              }
            } );
          }
        } );
        timer.setRepeats( false );
        timer.start();
      }
    } else if( counter == 0 ) {
      bgLabel.setIcon( getImageIcon( "1_chapter1.gif" ) );
      Timer timer = new Timer( 1500, new ActionListener(){
        @Override
        public void actionPerformed( ActionEvent e ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "JT" );
          headLabel.setIcon( getImageIcon( "JT_head.gif" ) );
          animateText( "Hah! Taste it ya weak ass fool!\r\nI hope ya liked eatin my dunks for breakfast!", 35, dialogueArea );
          Timer timer2 = new Timer( 7000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setText( "" );
              animateText( "Yo, you good? You lookin kinda pale there bud.\r\n . . .\r\nBruh? You good?", 50, dialogueArea );
              Timer timer3 = new Timer( 6500, new ActionListener() {
                public void actionPerformed( ActionEvent evt ) {
                  headLabel.setVisible( false );
                  dialogueArea.setVisible( false );
                  dialogueLabel.setVisible( false );
                  nameLabel.setVisible( false );
                  dialogueArea.setText( "" );
                  label = new FadeLabel( "1_chapter1.gif", "BLACK.gif", 3000 );
                  label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                  panel.remove( bgLabel );
                  panel.add( label );
                  panel.revalidate();
                  ( ( FadeLabel )label ).fadeImages();
                }
              });
              timer3.setRepeats( false );
              timer3.start();
              timer3 = new Timer( timer3.getDelay() + 3100, new ActionListener(){
                @Override
                public void actionPerformed( ActionEvent e ) {
                  panel.remove( label );
                  label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 3000 );
                  label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                  panel.add( label );
                  panel.revalidate();
                  ( ( FadeLabel)label ).fadeImages();
                  gameOverDialogue( "chapter1 defeat.gif" );
                }
              });
              timer3.setRepeats( false );
              timer3.start();
            }
          } );
          timer2.setRepeats( false );
          timer2.start();
        }
      } );
      timer.setRepeats( false );
      timer.start();
    }
    System.err.println( "GAME HAS ENDED" );
  }

  public void goToDistrict() {
    curStage = "district";
    resetScreen();
    frame.add( panel );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    resetChoiceButtons();
    choice1.setIcon( getImageIcon( "in n out.png" ) );
    choice2.setIcon( getImageIcon( "lil caesars.png" ) );
    choice3.setIcon( getImageIcon( "whole foods.png" ) );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    Timer timer = new Timer( 1000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ){
        dialogueLabel.setVisible( true );
        dialogueArea.setVisible( true );
        headLabel.setVisible( true );
        headLabel.setIcon( allanHeadImage );
        nameLabel.setVisible( true );
        nameLabel.setText( "Allan" );
        animateText( "Damn it's been a long time.\r\nWonder what happened to Red Robins and Chick-Fil-A?\r\n" + 
        "Oh well, guess I should eat somewhere else.", 35, dialogueArea );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6000, new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        choice1.setVisible( true );
        choice2.setVisible( true );
        choice3.setVisible( true );
        choice1.addActionListener( new DistrictChoiceHandler() );
        choice2.addActionListener( new DistrictChoiceHandler() );
        choice3.addActionListener( new DistrictChoiceHandler() );
      }
    });
    timer.setRepeats( false );
    timer.start();
    bgLabel.setIcon( getImageIcon( "district.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
  }

  private class DistrictChoiceHandler implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent e ) {
      if( e.getSource().equals( choice1 ) ) {
        curStage = "in n out";
        resetChoiceButtonsNoRollover();
        bgLabel.setIcon( getImageIcon( "inNout.gif" ) );
        choice1.setIcon( getImageIcon( "burger button.png" ) );
        choice1.setRolloverIcon( getImageIcon( "burger button h.png" ) );
        choice2.setIcon( getImageIcon( "soda button.png" ) );
        choice2.setRolloverIcon( getImageIcon( "soda button h.png" ) );
        choice3.setVisible( false );
        choice1.addMouseListener( new FoodRolloverDialogue() );
        choice2.addMouseListener( new FoodRolloverDialogue() );
        choice1.addActionListener( new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_burger.gif" ) );
            chosenFood = "burger";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice2.addActionListener( new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_soda.gif" ) );
            chosenFood = "soda";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        System.err.println( "in n out" );
      } else if( e.getSource().equals( choice2 ) ) {
        curStage = "lil caesars";
        resetChoiceButtonsNoRollover();
        bgLabel.setIcon( getImageIcon( "caesars.gif" ) );
        choice1.setIcon( getImageIcon( "veggie button.png" ) );
        choice1.setRolloverIcon( getImageIcon( "veggie button h.png" ) );
        choice2.setIcon( getImageIcon( "pepperoni button.png" ) );
        choice2.setRolloverIcon( getImageIcon( "pepperoni button h.png" ) );
        choice3.setVisible( false );
        choice1.addMouseListener( new FoodRolloverDialogue() );
        choice2.addMouseListener( new FoodRolloverDialogue() );
        choice1.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_veggie.gif" ) );
            chosenFood = "veggie";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice2.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_pepperoni.gif" ) );
            chosenFood = "pepperoni";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        System.err.println( "lil caesars" );
      } else if( e.getSource().equals( choice3 ) ) {
        curStage = "whole foods";
        resetChoiceButtonsNoRollover();
        bgLabel.setIcon( getImageIcon( "wholefoods.gif" ) );
        choice1.setIcon( getImageIcon( "broccoli button.png" ) );
        choice1.setRolloverIcon( getImageIcon( "broccoli button h.png" ) );
        choice2.setIcon( getImageIcon( "strawberry button.png" ) );
        choice2.setRolloverIcon( getImageIcon( "strawberry button h.png" ) );
        choice3.setIcon( getImageIcon( "watermelon button.png" ) );
        choice3.setRolloverIcon( getImageIcon( "watermelon button h.png" ) );
        choice1.addMouseListener( new FoodRolloverDialogue() );
        choice2.addMouseListener( new FoodRolloverDialogue() );
        choice3.addMouseListener( new FoodRolloverDialogue() );
        choice1.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_broccoli.gif" ) );
            chosenFood = "broccoli";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice2.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_strawberry.gif" ) );
            chosenFood = "strawberry";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice3.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_watermelon.gif" ) );
            chosenFood = "watermelon";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        System.err.println( "whole foods" );
      }
      dialogueArea.setVisible( false );
      dialogueLabel.setVisible( false );
      nameLabel.setVisible( false );
      headLabel.setVisible( false );
    }
  }

  public void goToDowntown() {
    curStage = "downtown";
    resetScreen();
    frame.add( panel );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    resetChoiceButtons();
    choice1.setIcon( getImageIcon( "85 degrees button.png" ) );
    choice2.setIcon( getImageIcon( "coldstone button.png" ) );
    choice3.setIcon( getImageIcon( "stickyfingers button.png" ) );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    Timer timer = new Timer( 1000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ){
        dialogueLabel.setVisible( true );
        dialogueArea.setVisible( true );
        headLabel.setVisible( true );
        headLabel.setIcon( allanHeadImage );
        nameLabel.setVisible( true );
        nameLabel.setText( "Allan" );
        animateText( "Since when did Irvine get this massive?\r\nIt must be due to all those FOB's getting into UCI.\r\n" + 
        "I should grab a bite to eat while I'm here.", 35, dialogueArea );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6000, new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        choice1.setVisible( true );
        choice2.setVisible( true );
        choice3.setVisible( true );
        choice1.addActionListener( new DowntownChoiceHandler() );
        choice2.addActionListener( new DowntownChoiceHandler() );
        choice3.addActionListener( new DowntownChoiceHandler() );
      }
    });
    timer.setRepeats( false );
    timer.start();
    bgLabel.setIcon( getImageIcon( "downtown.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
  }

  private class DowntownChoiceHandler implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      if( evt.getSource().equals( choice1 ) ) {
        curStage = "85 degrees";
        resetChoiceButtonsNoRollover();
        bgLabel.setIcon( getImageIcon( "85degree.gif" ) );
        choice1.setIcon( getImageIcon( "cake button.png" ) );
        choice1.setRolloverIcon( getImageIcon( "cake button h.png" ) );
        choice2.setIcon( getImageIcon( "toast button.png" ) );
        choice2.setRolloverIcon( getImageIcon( "toast button h.png" ) );
        choice3.setVisible( false );
        choice1.addMouseListener( new FoodRolloverDialogue() );
        choice2.addMouseListener( new FoodRolloverDialogue() );
        choice1.addActionListener( new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_cake.gif" ) );
            chosenFood = "cake";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice2.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_toast.gif" ) );
            chosenFood = "toast";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
      } else if( evt.getSource().equals( choice2 ) ) {
        curStage = "coldstone";
        resetChoiceButtonsNoRollover();
        bgLabel.setIcon( getImageIcon( "coldstone.gif" ) );
        choice1.setIcon( getImageIcon( "icecream button.png" ) );
        choice1.setRolloverIcon( getImageIcon( "icecream button h.png" ) );
        choice2.setIcon( getImageIcon( "parfait button.png" ) );
        choice2.setRolloverIcon( getImageIcon( "parfait button h.png" ) );
        choice3.setIcon( getImageIcon( "popsicle button.png" ) );
        choice3.setRolloverIcon( getImageIcon( "popsicle button h.png" ) );
        choice1.addMouseListener( new FoodRolloverDialogue() );
        choice2.addMouseListener( new FoodRolloverDialogue() );
        choice3.addMouseListener( new FoodRolloverDialogue() );
        choice1.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_icecream.gif" ) );
            chosenFood = "icecream";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice2.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_parfait.gif" ) );
            chosenFood = "parfait";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice3.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            choice3.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_popsicle.gif" ) );
            chosenFood = "popsicle";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
      } else if( evt.getSource().equals( choice3 ) ) {
        curStage = "stickyfingers";
        resetChoiceButtonsNoRollover();
        bgLabel.setIcon( getImageIcon( "stickyfingers.gif" ) );
        choice1.setIcon( getImageIcon( "custard button.png" ) );
        choice1.setRolloverIcon( getImageIcon( "custard button h.png" ) );
        choice2.setIcon( getImageIcon( "brownie button.png" ) );
        choice2.setRolloverIcon( getImageIcon( "brownie button h.png" ) );
        choice3.setVisible( false );
        choice1.addMouseListener( new FoodRolloverDialogue() );
        choice2.addMouseListener( new FoodRolloverDialogue() );
        choice1.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_custard.gif" ) );
            chosenFood = "custard";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
        choice2.addActionListener( new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            choice1.setVisible( false );
            choice2.setVisible( false );
            bgLabel.setIcon( getImageIcon( "food_brownie.gif" ) );
            chosenFood = "brownie";
            Timer timer = new Timer( 4000, new ActionListener() {
              public void actionPerformed( ActionEvent evt ) {
                tubbyEncounter();
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        });
      }
      dialogueArea.setVisible( false );
      dialogueLabel.setVisible( false );
      nameLabel.setVisible( false );
      headLabel.setVisible( false );
    }
  }

  private class FoodRolloverDialogue extends MouseAdapter {
    public void mouseEntered( MouseEvent evt ) {
      if( evt.getSource().equals( choice1 ) && curStage.equals( "in n out" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "No burger no pizza sorry... It'd be a sin to buy this." );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "in n out" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "What kind of IN-N-Out serves soda in cans???" );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "whole foods" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "Broccoli infused with iron. Disgusting but will definitely prove helpful when trying to prove one's " + 
        "absolute strength and resolve." );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "whole foods" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "Hmmm... last time I checked it wasn't strawberry season." );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "whole foods" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "This would go well with with some fried chicken and grape soda." );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "lil caesars" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "The slices of pizza seem uneven... very suspicious." );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "lil caesars" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "Only the vilest of the vile and the nastiest of the nast can stomach the will to force this down...." );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "85 degrees" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "This much frosting would give anyone a heart attack." );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "85 degrees" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "Friendly looking toast. Could possibly befriend someone if shared." );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "coldstone" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "Damn I hate brain freezes." );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "coldstone" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "Fruit and yogurt... what could go wrong?" );
      } else if( evt.getSource().equals( choice3 ) && curStage.equals( "coldstone" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( " ( lenny face ) " );
      } else if( evt.getSource().equals( choice1 ) && curStage.equals( "stickyfingers" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "What in the world is this sticky substance? There's no way a human can stomach this slop." );
      } else if( evt.getSource().equals( choice2 ) && curStage.equals( "stickyfingers" ) ) {
        textLabel.setVisible( true );
        textArea.setVisible( true );
        textArea.setText( "Hard, crusty, and dry just like mother used to make." );
      }
    }
    
    public void mouseExited( MouseEvent evt ) {
      textLabel.setVisible( false );
      textArea.setVisible( false );
      textArea.setText( null );
    }
  }

  private void tubbyEncounter() {
    curStage = "tubbies";
    resetScreen();
    frame.add( panel);
    panel.add( choice1 );
    panel.add( choice2 );
    resetChoiceButtonsNoRollover();
    choice1.setVisible( false );
    choice2.setVisible( false );
    Timer timer = new Timer( 1000, new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        animationFinished = false;
        animateText( "Woah, I don't remember this being here.\r\nHmmm... Maybe Rudraksha is on the other side.", 40, dialogueArea );
      }
    });
    timer.setRepeats( false );
    timer.start();
    panel.addMouseListener( new MouseAdapter() {
      public void mouseClicked( MouseEvent evt ) {
        if( !animationFinished ) {
          return;
        }
        panel.removeMouseListener( this );
        bgLabel.setIcon( getImageIcon( "tubby 1.gif" ) );
        dialogueArea.setText( null );
        animateText( "Huh?\r\nWhat the heck is that?", 35, dialogueArea );
        Timer t = new Timer( 3500, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            headLabel.setIcon( getImageIcon( "Dipsy_head.png" ) );
            nameLabel.setText( "DIPSY" );
            dialogueArea.setText( null );
            animateText( "Eh oh!", 35, dialogueArea );
          }
        });
        t.setRepeats( false );
        t.start();
        t = new Timer( t.getDelay() + 3000, new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            bgLabel.setIcon( getImageIcon( "tubby 2.gif" ) );
            headLabel.setIcon( allanHeadImage );
            nameLabel.setText( "Allan" );
            dialogueArea.setText( null );
            animateText( "Hey friends that's kinda close don't ya think?", 40, dialogueArea );
          }
        });
        t.setRepeats( false );
        t.start();
        t = new Timer( t.getDelay() + 4000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            nameLabel.setText( "Tubbies" );
            headLabel.setIcon( getImageIcon( "Tubbies_head.png" ) );
            dialogueArea.setText( null );
            animateText( "Hungy!\r\nYum Yum!", 40, dialogueArea );
          }
        });
        t.setRepeats( false );
        t.start();
        t = new Timer( t.getDelay() + 3500, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            nameLabel.setText( "Allan" );
            headLabel.setIcon( allanHeadImage );
            dialogueArea.setText( null );
            animateText( "Oh you boys hungry now?\r\nHold up, I think I got something y'all are going to like.", 40, dialogueArea );
          }
        });
        t.setRepeats( false );
        t.start();
        t = new Timer( t.getDelay() + 5000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setIcon( getImageIcon( "scarf button.png" ) );
            choice1.setRolloverIcon( getImageIcon( "scarf button h.png" ) );
            choice2.setIcon( getImageIcon( "share button.png" ) );
            choice2.setRolloverIcon( getImageIcon( "share button h.png" ) );
            choice1.setVisible( true );
            choice2.setVisible( true );
            choice1.addActionListener( new TubbyEncounterChoiceHandler() );
            choice2.addActionListener( new TubbyEncounterChoiceHandler() );
          }
        });
        t.setRepeats( false );
        t.start();
      }
    });
    bgLabel.setIcon( getImageIcon( "eat transition.gif" ) );
    panel.add( bgLabel );
    setNewSong( "tubby song" );
    panel.revalidate();
  }

  private class TubbyEncounterChoiceHandler implements ActionListener {
    public void actionPerformed( ActionEvent evt ) {
      stageCnt[ 0 ] = 0;
      choice1.setVisible( false );
      choice2.setVisible( false );
      if( evt.getSource().equals( choice1 ) ) {
        if( chosenFood.equals( "broccoli" ) || chosenFood.equals( "pepperoni" ) ) {
          dialogueArea.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          dialogueLabel.setVisible( false );
          Timer timer = new Timer( 2000, new ActionListener() {
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              headLabel.setIcon( getImageIcon( "Tubbies_head.png" ) );
              dialogueLabel.setVisible( true );
              nameLabel.setText( "Tubbies" );
              dialogueArea.setText( null );
              animateText( "Whoa chill you crazy human.\r\nYeesh.\r\nTake this and leave please.", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          panel.addMouseListener( new MouseAdapter() {
            public void mouseClicked( MouseEvent evt ) {
              if( stageCnt[ 0 ] == 0 ){
                stageCnt[ 0 ]++;
                bgLabel.setIcon( getImageIcon( "tubby win.gif" ) );
                headLabel.setIcon( allanHeadImage );
                nameLabel.setText( "Allan" );
                dialogueArea.setText( null );
                animationFinished = false;
                animateText( "Woah the Exodia legs!\r\nWonder how you guys got these.\r\n" + 
                "Thanks for the gift buddies!", 40, dialogueArea );
              } else if( stageCnt[ 0 ] == 1 && animationFinished ) {
                stageCnt[ 0 ]++;
                bgLabel.setIcon( getImageIcon( "tubby win2.gif" ) );
                headLabel.setIcon( getImageIcon( "Tubbies_head.png" ) );
                nameLabel.setText( "Tubbies" );
                dialogueArea.setText( null );
                animationFinished = false;
                animateText( "Guess this is it friend.\r\nGood luck on your future adventures.\r\n" + 
                "Tubby Bye Bye!", 40, dialogueArea );
              } else if( stageCnt[ 0 ] == 2 && animationFinished ) {
                stageCnt[ 0 ]++;
                bgLabel.setIcon( getImageIcon( "tubby transition.gif" ) );
                headLabel.setIcon( allanHeadImage );
                nameLabel.setText( "Allan" );
                dialogueArea.setText( null );
                animationFinished = false;
                animateText( "Hey, I can see Woodbridge High School from here.\r\nMaybe Rudraksha is hung up near there.", 40, dialogueArea );
                setNewSong( "school song" );
              } else if( stageCnt[ 0 ] == 3 && animationFinished ) {
                stageCnt[ 0 ]++;
                panel.removeMouseListener( this );
                headLabel.setVisible( false );
                nameLabel.setVisible( false );
                dialogueArea.setVisible( false );
                dialogueLabel.setVisible( false );
                goToSchool();
              }
            }
          });
        } else {
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "tubby 3.gif" ) );
          setNewSong( "tubby scare song" );
          Timer timer = new Timer( 2000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setText( null );
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setText( "????????" );
              headLabel.setIcon( getImageIcon( "Tubbies_evil_head.png" ) );
              animateText( "SCREECH!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
            public void actionPerformed( ActionEvent evt ) {
              headLabel.setIcon( allanHeadImage );
              nameLabel.setText( "Allan" );
              dialogueArea.setText( null );
              animateText( "Uhh wait hold on friends. We can talk about this.\r\nCan't we?\r\nPLEASE!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 5500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              headLabel.setVisible( false );
              nameLabel.setVisible( false );
              dialogueArea.setVisible( false );
              dialogueLabel.setVisible( false );
              dialogueArea.setText( null );
              int tubbyLoseNum = ( ( int )( Math.random() * 3 ) + 1 );
              bgLabel.setIcon( getImageIcon( "tubby 3." + tubbyLoseNum + ".gif" ) );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 5000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 3000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.remove( bgLabel );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel )label ).fadeImages();
              ballScore = 69;
              gameOverDialogue( "chapter1 defeat.gif" );
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      } else if( evt.getSource().equals( choice2 ) ) {
        if( chosenFood.equals( "custard" ) || chosenFood.equals( "toast" ) ) {
          dialogueArea.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          dialogueLabel.setVisible( false );
          Timer timer = new Timer( 2000, new ActionListener() {
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              headLabel.setIcon( getImageIcon( "Tubbies_head.png" ) );
              dialogueLabel.setVisible( true );
              nameLabel.setText( "Tubbies" );
              dialogueArea.setText( null );
              animateText( "MMMHMMM!\r\nFREND!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          panel.addMouseListener( new MouseAdapter() {
            public void mouseClicked( MouseEvent evt ) {
              if( stageCnt[ 0 ] == 0 ){
                stageCnt[ 0 ]++;
                bgLabel.setIcon( getImageIcon( "tubby win.gif" ) );
                headLabel.setIcon( allanHeadImage );
                nameLabel.setText( "Allan" );
                dialogueArea.setText( null );
                animationFinished = false;
                animateText( "Woah the Exodia legs!\r\nWonder how you guys got these.\r\n" + 
                "Thanks for the gift buddies!", 40, dialogueArea );
              } else if( stageCnt[ 0 ] == 1 && animationFinished ) {
                stageCnt[ 0 ]++;
                bgLabel.setIcon( getImageIcon( "tubby win2.gif" ) );
                headLabel.setIcon( getImageIcon( "Tubbies_head.png" ) );
                nameLabel.setText( "Tubbies" );
                dialogueArea.setText( null );
                animationFinished = false;
                animateText( "Guess this is it friend.\r\nGood luck on your future adventures.\r\n" + 
                "Tubby Bye Bye!", 40, dialogueArea );
              } else if( stageCnt[ 0 ] == 2 && animationFinished ) {
                stageCnt[ 0 ]++;
                headLabel.setIcon( allanHeadImage );
                nameLabel.setText( "Allan" );
                dialogueArea.setText( null );
                animationFinished = false;
                animateText( "Hey, I can see Woodbridge High School from here.\r\nMaybe Rudraksha is hung up near there.", 40, dialogueArea );
                bgLabel.setIcon( getImageIcon( "tubby transition.gif" ) );
                setNewSong( "school song" );
              } else if( stageCnt[ 0 ] == 3 && animationFinished ) {
                stageCnt[ 0 ]++;
                panel.removeMouseListener( this );
                headLabel.setVisible( false );
                nameLabel.setVisible( false );
                dialogueArea.setVisible( false );
                dialogueLabel.setVisible( false );
                goToSchool();
              }
            }
          });
        } else {
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "tubby 3.gif" ) );
          setNewSong( "tubby scare song" );
          Timer timer = new Timer( 2000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setText( null );
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setText( "????????" );
              headLabel.setIcon( getImageIcon( "Tubbies_evil_head.png" ) );
              animateText( "SCREECH!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
            public void actionPerformed( ActionEvent evt ) {
              headLabel.setIcon( allanHeadImage );
              nameLabel.setText( "Allan" );
              dialogueArea.setText( null );
              animateText( "Uhh wait hold on friends. We can talk about this.\r\nCan't we?\r\nPLEASE!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 5500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              headLabel.setVisible( false );
              nameLabel.setVisible( false );
              dialogueArea.setVisible( false );
              dialogueLabel.setVisible( false );
              dialogueArea.setText( null );
              int tubbyLoseNum = ( ( int )( Math.random() * 3 ) + 1 );
              bgLabel.setIcon( getImageIcon( "tubby 3." + tubbyLoseNum + ".gif" ) );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 5000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 3000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.remove( bgLabel );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel )label ).fadeImages();
              ballScore = 69;
              gameOverDialogue( "chapter1 defeat.gif" );
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      }
    }
  }

  private void goToSchool() {
    curStage = "school";
    stageCnt[ 0 ] = 0;
    resetScreen();
    frame.add( panel );
    panel.add( choice1 );
    panel.add( choice2 );
    resetChoiceButtonsNoRollover();
    choice1.setVisible( false );
    choice2.setVisible( false );
    bgLabel.setIcon( getImageIcon( "school 1.gif" ) );
    panel.add( bgLabel );
    Timer timer = new Timer( 1500, new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        nameLabel.setVisible( true );
        headLabel.setVisible( true );
        nameLabel.setText( "Mr. Dworzak" );
        headLabel.setIcon( getImageIcon( "Dworzak_head.png" ) );
        animateText( "Woah no trespassing on school grounds fella.", 35, dialogueArea );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3500, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        choice1.setVisible( true );
        choice2.setVisible( true );
        choice1.setIcon( getImageIcon( "greet.png" ) );
        choice1.setRolloverIcon( getImageIcon( "greetH.png" ) );
        choice2.setIcon( getImageIcon( "nast.png" ) );
        choice2.setRolloverIcon( getImageIcon( "nastH.png" ) );
        choice1.addActionListener( new SchoolActionListener() );
        choice2.addActionListener( new SchoolActionListener() );
        panel.addMouseListener( new SchoolMouseListener() );
        dialogueLabel.addMouseListener( new SchoolMouseListener() );
      }
    });
    timer.setRepeats( false );
    timer.start();
    panel.revalidate();
  }

  private class SchoolMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked( MouseEvent evt ) {
      if( stageCnt[ 0 ] == 1 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Mr. Dworzak" );
        headLabel.setIcon( getImageIcon( "Dworzak_head.png" ) );
        if( choiceNum == 1 ) {
          animateText( "Oh? What did he look like?", 35, dialogueArea );
        } else if( choiceNum == 2 ) {
          animateText( "Oh no...\r\nLooks like another freshman had one too many huffs of juul.", 35, dialogueArea );
        }
      } else if( stageCnt[ 0 ] == 2 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Allan" );
        headLabel.setIcon( allanHeadImage );
        if( choiceNum == 1 ){
          animateText( "He has brown skin and always likes to smile.", 35, dialogueArea );
        } else if( choiceNum == 2 ) {
          animateText( "How dare you compare me to those chromosome-lacking creatures!\r\n" + 
          "Anyways, did you happen to see a brown fellow walk past here?", 35, dialogueArea );
        }
      } else if( stageCnt[ 0 ] == 3 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Mr. Dworzak" );
        headLabel.setIcon( getImageIcon( "Dworzak_head.png" ) );
        animateText( "Ah yes, actually, hold on a second I'll get him.", 35, dialogueArea );
      } else if( stageCnt[ 0 ] == 4 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        dialogueArea.setVisible( false );
        dialogueLabel.setVisible( false );
        headLabel.setVisible( false );
        nameLabel.setVisible( false );
        bgLabel.setIcon( getImageIcon( "school 2.gif" ) );
        Timer timer = new Timer( 1500, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            dialogueArea.setText( null );
            headLabel.setIcon( getImageIcon( "25 cent_head.png" ) );
            headLabel.setVisible( true );
            nameLabel.setText( "25 Cent" );
            nameLabel.setVisible( true );
            animateText( "Yo what's up my homie!\r\nThey call me 25 cent and I'm gunnin' for the XXL freshmen class of 2020 yuh!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 4000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setIcon( getImageIcon( "reply.png" ) );
            choice1.setRolloverIcon( getImageIcon( "replyH.png" ) );
            choice2.setIcon( getImageIcon( "nastreply.png" ) );
            choice2.setRolloverIcon( getImageIcon( "nastreplyH.png" ) );
            choice1.setVisible( true );
            choice2.setVisible( true );
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( stageCnt[ 0 ] == 6 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Mr. Dworzak" );
        headLabel.setIcon( getImageIcon( "Dworzak_head.png" ) );
        animateText( "Is this not the guy?\r\nOh then it may be this other fellow I saw.\r\n" +
        "He ran into a cave over yonder and dropped this here toy of some sort.", 35, dialogueArea );
      } else if( stageCnt[ 0 ] == 7 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Allan" );
        headLabel.setIcon( allanHeadImage );
        animateText( "That's Him!\r\nMay I have the toy kind sir?", 35, dialogueArea );
      } else if( stageCnt[ 0 ] == 8 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Mr. Dworzak" );
        headLabel.setIcon( getImageIcon( "Dworzak_head.png" ) );
        if( choiceNum == 1 ) {
          animateText( "Oh well since you were so polite, you can have it.", 35, dialogueArea );
        } else if( choiceNum == 2 ) {
          animateText( "Tell ya what sport, if you help uhh Tyrone was it? here out, I'll hand it over.", 35, dialogueArea );
        }
      } else if( stageCnt[ 0 ] == 9 && animationFinished && choiceNum == 1 ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        dialogueArea.setVisible( false );
        dialogueLabel.setVisible( false );
        headLabel.setVisible( false );
        nameLabel.setVisible( false );
        panel.removeMouseListener( this );
        goToGym();
        return;
      } else if( stageCnt[ 0 ] == 9 && animationFinished && choiceNum == 2 ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "25 Cent" );
        headLabel.setIcon( getImageIcon( "25 cent_head.png" ) );
        animateText( "YEA! skrt skrt.\r\nI NEED A SKIN-COLORED PENCIL FOR ART CLASS, ya dig, NEXT PERIOD!\r\n" +
        "HAND ONE OVER WILL YA! Ya know what I'm sayin?", 35, dialogueArea );
        Timer timer = new Timer( 7000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setIcon( getImageIcon( "moral_button.png" ) );
            choice1.setRolloverIcon( getImageIcon( "moral_button h.png" ) );
            choice2.setIcon( getImageIcon( "truth_button.png" ) );
            choice2.setRolloverIcon( getImageIcon( "truth_button h.png" ) );
            choice1.setVisible( true );
            choice2.setVisible( true );
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( stageCnt[ 0 ] == 11 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "25 Cent" );
        headLabel.setIcon( getImageIcon( "25 cent_head.png" ) );
        if( choiceNum == 1 ) {
          animateText( "Ah hell no fool.\r\nHaven't you ever heard of the Emancipation Proclamation?!", 35, dialogueArea );
        } else if( choiceNum == 2 ) {
          animateText( "Ahh yee. That's the good stuff. I mess witchu.\r\nMuch respect bruh. No cap.", 35, dialogueArea );
        }
      } else if( stageCnt[ 0 ] == 12 && animationFinished && choiceNum == 1 ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Allan" );
        headLabel.setIcon( allanHeadImage );
        animateText( "Uhhh I don't listen to hip-hop sorry.", 35, dialogueArea );
      } else if( stageCnt[ 0 ] == 12 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Mr. Dworzak" );
        headLabel.setIcon( getImageIcon( "Dworzak_head.png" ) );
        animateText( "Good job chillun'.\r\nHere's the toy.", 35, dialogueArea );
      } else if( stageCnt[ 0 ] == 13 && animationFinished && choiceNum == 1 ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        nameLabel.setText( "Mr. Dworzack" );
        headLabel.setIcon( getImageIcon( "Dworzak_head.png" ) );
        animateText( "Seems right to me.\r\nHere's the toy, run off now.", 35, dialogueArea );
      } else if( stageCnt[ 0 ] == 13 && animationFinished ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        dialogueArea.setVisible( false );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        panel.removeMouseListener( this );
        goToGym();
        return;
      } else if( stageCnt[ 0 ] == 14 && animationFinished && choiceNum == 1) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        dialogueArea.setText( null );
        dialogueArea.setVisible( false );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        panel.removeMouseListener( this );
        goToGym();
        return;
      }
    }
  }

  private class SchoolActionListener implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent e ) {
      choice1.setVisible( false );
      choice2.setVisible( false );
      if( stageCnt[ 0 ] == 0 ) {
        stageCnt[ 0 ]++;
        dialogueArea.setText( null );
        nameLabel.setText( "Allan" );
        headLabel.setIcon( allanHeadImage );
        animationFinished = false;
        if( e.getSource().equals( choice1 ) ) {
          choiceNum = 1;
          animateText( "Sorry sir! I was just wondering if you've seen my friend.", 35, dialogueArea );
        } else if( e.getSource().equals( choice2 ) ) {
          choiceNum = 2;
          animateText( "I do what I want ya old geezer!", 35, dialogueArea );
        }
      } else if( stageCnt[ 0 ] == 5 && animationFinished ) {
        stageCnt[ 0 ]++;
        dialogueArea.setText( null );
        nameLabel.setText( "Allan" );
        headLabel.setIcon( allanHeadImage );
        animationFinished = false;
        if( e.getSource().equals( choice1 ) ) {
          choiceNum = 1;
          animateText( "Um sorry to bother you, but you're not the person I'm looking for.", 35, dialogueArea );
        } else if( e.getSource().equals( choice2 ) ) {
          choiceNum = 2;
          animateText( "Who's this midget bruh?", 35, dialogueArea );
        }
      } else if( stageCnt[ 0 ] == 10 && animationFinished ) {
        stageCnt[ 0 ]++;
        dialogueArea.setText( null );
        nameLabel.setText( "Allan" );
        headLabel.setIcon( allanHeadImage );
        animationFinished = false;
        animateText( "Here ya go bro!", 35, dialogueArea );
        choiceNum = ( int )( Math.random() * 2 ) + 1;
      }
    }
  }

  private void goToGym() {
    goToGym( false );
  }
  private void goToGym( boolean song ) {
    curStage = "gym";
    stageCnt[ 0 ] = 0;
    if( song ) {
      setNewSong( "school song" );
    }
    resetScreen();
    frame.add( panel );
    panel.add( choice1 );
    panel.add( choice2 );
    resetChoiceButtonsNoRollover();
    choice1.setVisible( false );
    choice2.setVisible( false );
    bgLabel.setIcon( getImageIcon( "school transition.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
    Timer timer = new Timer( 1500, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        headLabel.setIcon( allanHeadImage );
        nameLabel.setText( "Allan" );
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        animateText( "Boy I look out of shape.\r\nBefore I head to the cave, I should prepare myself.\r\n" +
        "Time to hit the gym!", 35, dialogueArea );
      }
    });
    timer.setRepeats( false );
    timer.start();
    panel.addMouseListener( new MouseAdapter() {
      public void mouseClicked( MouseEvent evt ) {
        if( animationFinished ) {
          panel.removeMouseListener( this );
          animationFinished = false;
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "gym 1.gif" ) );
          panel.revalidate();
          Timer timer = new Timer( 1500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              animateText( "Let's see. What should I juice up today?\r\nI forgot if it was leg day or not.\r\n" +
              "But my arms feel kinda flat today as well.", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 5000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setIcon( getImageIcon( "tread_button.png" ) );
              choice1.setRolloverIcon( getImageIcon( "tread_button h.png" ) );
              choice2.setIcon( getImageIcon( "weights_button.png" ) );
              choice2.setRolloverIcon( getImageIcon( "weights_button h.png" ) );
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice1.addActionListener( new GymChoiceHandler() );
              choice2.addActionListener( new GymChoiceHandler() );
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      }
    });
  }

  private class GymChoiceHandler implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent e ) {
      animationFinished = false;
      choice1.setVisible( false );
      choice2.setVisible( false );
      headLabel.setVisible( false );
      nameLabel.setVisible( false );
      dialogueArea.setVisible( false );
      dialogueArea.setText( null );
      dialogueLabel.setVisible( false );
      if( e.getSource().equals( choice1 ) ) {
        bgLabel.setIcon( getImageIcon( "gym tread.gif" ) );
        legDay = true;
        Timer timer = new Timer( 1500, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            headLabel.setVisible( true );
            nameLabel.setVisible( true );
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            animateText( "Damn my quads are feeling fierce today!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( e.getSource().equals( choice2 ) ) {
        bgLabel.setIcon( getImageIcon( "gym weights.gif" ) );
        legDay = false;
        Timer timer = new Timer( 1500, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            headLabel.setVisible( true );
            nameLabel.setVisible( true );
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            animateText( "My guns are locked and loaded!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
      }
      panel.addMouseListener( new MouseAdapter() {
        public void mouseClicked( MouseEvent evt ) {
          if( animationFinished ) {
            panel.removeMouseListener( this );
            animationFinished = false;
            headLabel.setVisible( false );
            nameLabel.setVisible( false );
            dialogueArea.setVisible( false );
            dialogueArea.setText( null );
            dialogueLabel.setVisible( false );
            goToCave();
            return;
          }
        }
      });
    }
  }

  private void goToCave() {
    curStage = "cave 0";
    resetScreen();
    frame.add( panel );
    //add necessary buttons----------
    panel.add( choice1 );
    panel.add( choice2 );
    resetChoiceButtonsNoRollover();
    choice1.setVisible( false );
    choice2.setVisible( false );
    bgLabel.setIcon( getImageIcon( "gym_transition.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
    Timer timer = new Timer( 1500, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        animationFinished = false;
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        animateText( "It's getting dark.\r\nTime to speed up my search.\r\nAlright where that cave at!", 35, dialogueArea );
      }
    });
    timer.setRepeats( false );
    timer.start();
    panel.addMouseListener( new MouseAdapter() {
      public void mouseClicked( MouseEvent evt ) {
        if( stageCnt[ 0 ] == 0 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          dialogueLabel.setVisible( false );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          animateText( "The sun sets as Allan approaches the cave.\r\nThe search must go on!", 35 );
        } else if( stageCnt[ 0 ] == 1 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 1.gif" ) );
          panel.revalidate();
          Timer timer = new Timer( 1500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animationFinished = false;
              animateText( "Well this must be it.\r\nThis cave is way bigger than I thought.", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 2 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          setNewSong( "cave song 1" );
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 2.gif" ) );
          panel.revalidate();
          Timer timer = new Timer( 1500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueArea.setText( null );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animationFinished = false;
              animateText( "Whoa this place looks dank.", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 3 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          animateText( "Allan notices a stray bag on the ledge. After snatching the bag, a strange creature appears to be angered.", 35 );
        } else if( stageCnt[ 0 ] == 4 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 3.gif" ) );
          Timer timer = new Timer( 1500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueArea.setText( null );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animateText( "Uhhhh... uh oh!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 2000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setIcon( getImageIcon( "run button.png" ) );
              choice1.setRolloverIcon( getImageIcon( "run button h.png" ) );
              choice2.setIcon( getImageIcon( "fight button.png" ) );
              choice2.setRolloverIcon( getImageIcon( "fight button h.png" ) );
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice1.addActionListener( new CaveActionListener() );
              choice2.addActionListener( new CaveActionListener() );
            }
          });
          timer.setRepeats( false );
          timer.start();
          panel.removeMouseListener( this );
        }
      }
    });
  }

  private class CaveActionListener implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent e ) {
      choice1.setVisible( false );
      choice2.setVisible( false );
      choice3.setVisible( false );
      choice4.setVisible( false );
      choice5.setVisible( false );
      dialogueArea.setVisible( false );
      dialogueArea.setText( null );
      dialogueLabel.setVisible( false );
      nameLabel.setVisible( false );
      headLabel.setVisible( false );
      textArea.setVisible( false );
      textArea.setText( null );
      textLabel.setVisible( false );
      if( curStage.equals( "cave 0" ) ) {
        if( e.getSource().equals( choice1 ) && legDay ) {
          bgLabel.setIcon( getImageIcon( "cave 5 run.gif" ) );
          headLabel.setVisible( true );
          nameLabel.setVisible( true );
          dialogueLabel.setVisible( true );
          dialogueArea.setVisible( true );
          animateText( "Smell ya later! HEH!", 35, dialogueArea );
          Timer timer = new Timer( 5000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              headLabel.setVisible( false );
              nameLabel.setVisible( false );
              dialogueArea.setVisible( false );
              dialogueArea.setText( null );
              dialogueLabel.setVisible( false );
              try {
                return;
              } finally {
                beginCave();
              }
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else {
          bgLabel.setIcon( getImageIcon( "cave 4.gif" ) );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          animationFinished = false;
          if( e.getSource().equals( choice1 ) ) {
            animateText( "Allan is too slow and the creature catches up.", 35 );
          } else if( e.getSource().equals( choice2 ) && legDay ) {
            animateText( "Allan is too weak and the creature easily overpowers him.", 35 );
          } else if( e.getSource().equals( choice2 ) && !legDay ) {
            animateText( "Thanks to his guns, Allan is able to hold his own against the creature...\r\n" +
            "but he is no match for its friends....", 35 );
          }
          panel.addMouseListener( new MouseAdapter() {
            public void mouseClicked( MouseEvent evt ) {
              if( animationFinished ) {
                panel.removeMouseListener( this );
                ImageIcon death = getImageIcon( "cave 5 fight.gif" );
                death.getImage().flush();
                bgLabel.setIcon( death );
                textArea.setVisible( false );
                textArea.setText( null );
                textLabel.setVisible( false );
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                nameLabel.setVisible( true );
                headLabel.setVisible( true );
                animateText( "I don't feel too good...", 35, dialogueArea );
                Timer timer = new Timer( 2500, new ActionListener(){
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    headLabel.setVisible( false );
                    dialogueArea.setVisible( false );
                    dialogueLabel.setVisible( false );
                    nameLabel.setVisible( false );
                    dialogueArea.setText( null );
                    label = new FadeLabel( "cave 5 fight.png", "chapter1 defeat.gif", 2000 );
                    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                    panel.remove( bgLabel );
                    panel.add( label );
                    panel.revalidate();
                    ( ( FadeLabel )label ).fadeImages();
                    gameOverDialogue( "chapter1 defeat.gif" );
                  }
                });
                timer.setRepeats( false );
                timer.start();
              }
            }
          });
        }
      } else if( curStage.equals( "cave 1" ) ) {
        if( stageCnt[ 0 ] == 1 && e.getSource().equals( choice1 ) ) { //twinkle
          bgLabel.setIcon( getImageIcon( "cave 1_2 twinkle.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "Well would you look at that.\r\nPlayed enough Minecraft to know what I should do now.\r\n" +
              "Good thing I snatched this bag! These ores look heavy.", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( 6000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              stageCnt[ 0 ]++;
              setButtonIcon( choice1, "diamonds_button" );
              setButtonIcon( choice2, "iron_button" );
              choice1.setVisible( true );
              choice2.setVisible( true );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 1 && e.getSource().equals( choice2 ) ) { //water
          bgLabel.setIcon( getImageIcon( "cave 1_2 water.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "Hmmm. Seems to just be an old fountain structure.\r\nNothing much else to see here.\r\n" +
              "I'd better head back the way I came.", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( 6000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              stageCnt[ 0 ] = 0;
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 1 && e.getSource().equals( choice3 ) ) { //winds
          bgLabel.setIcon( getImageIcon( "cave 1_2 winds.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animationFinished = false;
              animateText( "Uhhhhh!\r\nHello there mister nice ghoul.\r\nFancy meeting you down here.", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          panel.addMouseListener( new MouseAdapter() {
            public void mouseClicked( MouseEvent evt ) {
              if( animationFinished ) {
                MouseListener [] m = panel.getMouseListeners();
                for( MouseListener mouse: m ) {
                  panel.removeMouseListener( mouse );
                }
                dialogueArea.setText( null );
                animateText( "Please don't eat me.", 35, dialogueArea );
                Timer timer = new Timer( 2500, new ActionListener(){
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    headLabel.setVisible( false );
                    dialogueArea.setVisible( false );
                    dialogueLabel.setVisible( false );
                    nameLabel.setVisible( false );
                    dialogueArea.setText( null );
                    label = new FadeLabel( "cave 1_2 winds.gif", "BLACK.gif", 2000 );
                    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                    panel.remove( bgLabel );
                    panel.add( label );
                    panel.revalidate();
                    ( ( FadeLabel )label ).fadeImages();
                    Timer timer2 = new Timer( 2100, new ActionListener(){
                      @Override
                      public void actionPerformed( ActionEvent e ) {
                        panel.remove( label );
                        label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 2000 );
                        label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                        panel.add( label );
                        panel.revalidate();
                        ( ( FadeLabel)label ).fadeImages();
                        gameOverDialogue( "chapter1 defeat.gif" );
                      }
                    });
                    timer2.setRepeats( false );
                    timer2.start();
                  }
                });
                timer.setRepeats( false );
                timer.start();
              }
            }
          });
        } else if( stageCnt[ 0 ] == 2 ) {
          stageCnt[ 0 ]++;
          if( e.getSource().equals( choice1 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 1_3 diamonds.gif" ) );
            diamonds = true;
          } else if( e.getSource().equals( choice2 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 1_3 iron.gif" ) );
            diamonds = false;
          }
          Timer timer = new Timer( 3000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              bgLabel.setIcon( getImageIcon( "cave 2_1.gif" ) );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "Allan progresses further into the cave. Another fork in the road! " +
              "Two bright lights shine the way for Allan. Which colored light should he follow?", 35 );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 6000, new ActionListener(){
          
            @Override
            public void actionPerformed( ActionEvent e ) {
              setButtonIcon( choice1, "greenlight_button" );
              setButtonIcon( choice2, "orangelight_button" );
              choice1.setVisible( true );
              choice2.setVisible( true );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 3 && e.getSource().equals( choice1 ) ) {
          stageCnt[ 0 ]++;
          curStage = "cave 2";
          bgLabel.setIcon( getImageIcon( "cave 2_2.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animationFinished = false;
              animateText( "Allan is met by the stench of alkaline pools gleaming a bright green tint. " +
              "There is someone else admiring the falls. It would seem Allan isn't the only one in the cave!", 35 );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 3 && e.getSource().equals( choice2 ) ) {
          stageCnt[ 0 ]++;
          curStage = "cave 3";
          animationFinished = false;
          bgLabel.setIcon( getImageIcon( "cave 3_1.gif" ) );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          animateText( "Allan approaches a long abandoned mineshaft. Soft winds carry chilling sounds from the dark and dingy cave. " +
          "Our hero musters up the strength to proceed!", 35 );
        }
      } else if( curStage.equals( "cave 3" ) ) {
        if( stageCnt[ 0 ] == 14 && e.getSource().equals( choice1 ) ) {
          stageCnt[ 0 ]++;
          curStage = "cave 5";
          animationFinished = false;
          panel.remove( bgLabel );
          panel.remove( choice5 );
          panel.add( choice5 );
          placeComponent( 473, 380, choice5 );
          bgLabel.setIcon( getImageIcon( "cave 5_1.gif" ) );
          panel.add( bgLabel );
          panel.revalidate();
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animateText( "That's a lot of water.", 35, dialogueArea, false );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              nameLabel.setText( "Steve");
              headLabel.setIcon( getImageIcon( "Steve_head.png" ) );
              dialogueArea.setText( null );
              animateText( "It doesn't smell awful anymore.\r\nWe must be getting close to the exit.", 35, dialogueArea, false );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 5000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              nameLabel.setText( "Allan" );
              headLabel.setIcon( allanHeadImage );
              dialogueArea.setText( null );
              animateText( "The water has to be coming from somewhere!\r\nWhere shall we look next?", 35, dialogueArea, false );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 4000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setIcon( getImageIcon( "pillars_button.png" ) );
              choice1.setRolloverIcon( getImageIcon( "pillars_button h.png" ) );
              choice2.setIcon( getImageIcon( "upstream_button.png" ) );
              choice2.setRolloverIcon( getImageIcon( "upstream_button h.png" ) );
              choice3.setIcon( getImageIcon( "downstream_button.png" ) );
              choice3.setRolloverIcon( getImageIcon( "downstream_button h.png" ) );
              choice4.setIcon( getImageIcon( "crack_button.png" ) );
              choice4.setRolloverIcon( getImageIcon( "crack_button h.png" ) );
              choice5.setIcon( getImageIcon( "door_button.png" ) );
              choice5.setRolloverIcon( getImageIcon( "door_button h.png" ) );
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice3.setVisible( true );
              choice4.setVisible( true );
              choice5.setVisible( true );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 14 ) {
          stageCnt[ 0 ] = 13;
          animationFinished = false;
          if( e.getSource().equals( choice4 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 4_2 north.gif" ) );
          } else if( e.getSource().equals( choice2 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 4_2 east.gif" ) );
          } else if( e.getSource().equals( choice3 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 4_2 west.gif" ) );
          }
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              if( e.getSource().equals( choice4 ) ) {
                animateText( "Uhhh nothing to see here!", 35, dialogueArea );
              } else if( e.getSource().equals( choice2 ) ) {
                animateText( "I dont think this is it chief!", 35, dialogueArea, false );
                Timer t = new Timer( 2500, new ActionListener() {
                  @Override
                  public void actionPerformed( ActionEvent event ) {
                    nameLabel.setText( "Steve");
                    headLabel.setIcon( getImageIcon( "Steve_head.png" ) );
                    dialogueArea.setText( null );
                    animateText( "RUN YOU IDIOT!", 35, dialogueArea );
                  }
                });
                t.setRepeats( false );
                t.start();
              } else if( e.getSource().equals( choice3 ) ) {
                animateText( "You know what...\r\nI have a gut feeling this ain't the way.\r\nLet's go back.", 35, dialogueArea );
              }
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      } else if( curStage.equals( "cave 5" ) ) {
        if( stageCnt[ 0 ] == 15 ) {
          animationFinished = false;
          dialogueArea.setText( null );
          nameLabel.setText( "Allan" );
          headLabel.setIcon( allanHeadImage );
          if( e.getSource().equals( choice4 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 5_2 crack.gif" ) );
          } else if( e.getSource().equals( choice1 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 5_2 pillars.gif" ) );
          } else if( e.getSource().equals( choice3 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 5_2 downstream.gif" ) );
          } else if( e.getSource().equals( choice5 ) ) {
            ImageIcon img = getImageIcon( "cave 5_2 door.gif" );
            img.getImage().flush();
            bgLabel.setIcon( img );
          } else if( e.getSource().equals( choice2 ) ) { //correct
            ImageIcon img = getImageIcon( "cave 5_2 upstream.gif" );
            img.getImage().flush();
            bgLabel.setIcon( img );
          }
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              dialogueLabel.setVisible( true );
              if( e.getSource().equals( choice2 ) ) {
                stageCnt[ 0 ] = 14;
                nameLabel.setText( "Steve" );
                headLabel.setIcon( getImageIcon( "Steve_head.png" ) );
                animateText( "Stand clear of that... That's the Ender Dragon!\r\n" +
                "Looks like someone is trying to revive it. We should hurry and find the right path.", 35, dialogueArea );
              } else if( e.getSource().equals( choice4 ) ) {
                stageCnt[ 0 ] = 14;
                animateText( "Uhhhh shhhh...\r\nIt's definitely not the way.", 35, dialogueArea );
              } else if( e.getSource().equals( choice1 ) ) {
                stageCnt[ 0 ] = 14;
                animateText( "What weird looking slugs.\r\nWrong way again...", 35, dialogueArea );
              } else if( e.getSource().equals( choice3 ) ) {
                stageCnt[ 0 ] = 14;
                animateText( "Well unless that giant creature ain't hungry...\r\nI am sure this is not the way.", 35, dialogueArea );
              } else if( e.getSource().equals( choice5 ) ) { //correct
                stageCnt[ 0 ]++;
                animateText( "Heh. What a useless lock.\r\nReminds me of Woodbridge lockers.", 35, dialogueArea );
              }
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      } else if( curStage.equals( "cave 6" ) ) {
        if( stageCnt[ 0 ] == 19 ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          setNewSong( "cave song 4" );
          if( e.getSource().equals( choice1 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 6_2 search.gif" ) );
          } else if( e.getSource().equals( choice2 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 6_2 open1.gif" ) );
          }
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent event ) {
              dialogueArea.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              dialogueLabel.setVisible( true );
              if( e.getSource().equals( choice1 ) ) {
                animateText( "Oh shoot!\r\nA CREEPER!\r\nNOPE CYA!", 35, dialogueArea );
              } else if( e.getSource().equals( choice2 ) ) {
                animateText( "HOHO! TREASURE!\r\nDon't mind if I do.", 35, dialogueArea, false );
                Timer t = new Timer( 3500, new ActionListener(){
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    bgLabel.setIcon( getImageIcon( "cave 6_2 open2.gif" ) );
                    dialogueArea.setText( null );
                    animateText( "What is this???\r\n\r\nOH SHOOT!", 50, dialogueArea, false );
                  }
                });
                t.setRepeats( false );
                t.start();
                t = new Timer( t.getDelay() + 7000, new ActionListener(){
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    dialogueArea.setVisible( false );
                    dialogueArea.setText( null );
                    dialogueLabel.setVisible( false );
                    nameLabel.setVisible( false );
                    headLabel.setVisible( false );
                    label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 2000 );
                    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                    panel.remove( bgLabel );
                    panel.add( label );
                    panel.revalidate();
                    ( ( FadeLabel)label ).fadeImages();
                    gameOverDialogue( "chapter1 defeat.gif" );
                  }
                });
                t.setRepeats( false );
                t.start();
              }
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 21 ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          if( e.getSource().equals( choice1 ) ) {
            ImageIcon img = getImageIcon( "cave 6_5 worm.gif" );
            img.getImage().flush();
            bgLabel.setIcon( img );
          } else if( e.getSource().equals( choice2 ) ) {
            bgLabel.setIcon( getImageIcon( "cave 6_5.gif" ) );
          }
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              dialogueLabel.setVisible( true );
              if( e.getSource().equals( choice1 ) ) {
                animateText( "Nope. Learned my lesson. I'm lookin!\r\nHeh. Nice try you dumb grub!", 35, dialogueArea, false );
                Timer t = new Timer( 9000, new ActionListener() {
                  @Override
                  public void actionPerformed( ActionEvent event ) {
                    dialogueArea.setText( null );
                    animateText( "darn...", 35, dialogueArea, false );
                  }
                });
                t.setRepeats( false );
                t.start();
                t = new Timer( t.getDelay() + 5000, new ActionListener(){
                  @Override
                  public void actionPerformed( ActionEvent event ) {
                    headLabel.setVisible( false );
                    dialogueArea.setVisible( false );
                    dialogueLabel.setVisible( false );
                    nameLabel.setVisible( false );
                    dialogueArea.setText( null );
                    label = new FadeLabel( "cave 6_5 worm.png", "BLACK.gif", 2000 );
                    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                    panel.remove( bgLabel );
                    panel.add( label );
                    panel.revalidate();
                    ( ( FadeLabel )label ).fadeImages();
                  }
                });
                t.setRepeats( false );
                t.start();
                t = new Timer( t.getDelay() + 2100, new ActionListener(){
                  @Override
                  public void actionPerformed( ActionEvent e ) {
                    panel.remove( label );
                    label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 2000 );
                    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                    panel.add( label );
                    panel.revalidate();
                    ( ( FadeLabel)label ).fadeImages();
                    gameOverDialogue( "chapter1 defeat.gif" );
                  }
                });
                t.setRepeats( false );
                t.start();
              } else if( e.getSource().equals( choice2 ) ) {
                animateText( "Well would you look at that!\r\nThe last piece for Exodia!\r\n" +
                "Can't wait to test this out on someone.", 35, dialogueArea );
              }
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      }
    }
  }

  private void beginCave() {
    beginCave( false );
  }
  private void beginCave( boolean song ) {
    curStage = "cave 1";
    stageCnt[ 0 ] = 0;
    if( song )
      setNewSong( "cave song 1" );
    resetScreen();
    frame.add( panel );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    panel.add( choice4 );
    panel.add( choice5 );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    choice4.setVisible( false );
    choice5.setVisible( false );
    resetChoiceButtonsNoRollover();
    choice1.addActionListener( new CaveActionListener() );
    choice2.addActionListener( new CaveActionListener() );
    choice3.addActionListener( new CaveActionListener() );
    choice4.addActionListener( new CaveActionListener() );
    choice5.addActionListener( new CaveActionListener() );
    bgLabel.setIcon( getImageIcon( "cave 6.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
    Timer timer = new Timer( 1000, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        dialogueArea.setVisible( true );
        dialogueArea.setText( null );
        dialogueLabel.setVisible( true );
        animationFinished = false;
        animateText( "The door is shut tight. Looks like there's no going back now. " +
        "Wonder which way I should go first.", 35, dialogueArea );
      }
    });
    timer.setRepeats( false );
    timer.start();
    panel.addMouseListener( new CaveMouseListener() );
  }

  private class CaveMouseListener extends MouseAdapter {
    public void mouseClicked( MouseEvent evt ) {
      if( stageCnt[ 0 ] == 0 && animationFinished && curStage.equals( "cave 1" ) ) {
        stageCnt[ 0 ]++;
        animationFinished = false;
        bgLabel.setIcon( getImageIcon( "cave 1_1.gif" ) );
        dialogueArea.setVisible( false );
        dialogueArea.setText( null );
        dialogueLabel.setVisible( false );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        textArea.setVisible( true );
        textLabel.setVisible( true );
        animateText( "After rummaging through the caverns, Allan hits a fork in the road. " +
        "Three distinct sounds echo the halls of each path.", 35 );
        setButtonIcon( choice1, "twinkle_button" );
        setButtonIcon( choice2, "water_button" );
        setButtonIcon( choice3, "winds_button" );
        Timer timer = new Timer( 5000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            choice1.setVisible( true );
            choice2.setVisible( true );
            choice3.setVisible( true );
          }
        });
        timer.setRepeats( false );
        timer.start();
      }

      if( curStage.equals( "cave 2" ) ) {
        if( stageCnt[ 0 ] == 4 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setVisible( true );
          nameLabel.setVisible( true );
          animateText( "Rudy is that you?\r\nWait a second...\r\nYou're Steve from Minecraft!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 5 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setText( null );
          nameLabel.setText( "Steve" );
          headLabel.setIcon( getImageIcon( "Steve_head.png" ) );
          animateText( "Yessir and to be frank, I'm kinda lost...\r\nTell you what, trade me that bag of yours and in return,\r\n" +
          "I'll craft you a sword with whatever is in it.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 6 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setText( null );
          nameLabel.setText( "Allan" );
          headLabel.setIcon( allanHeadImage );
          animateText( "You've got yourself a deal!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 7 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          if( diamonds ) {
            bgLabel.setIcon( getImageIcon( "cave 2_3 diamonds.gif" ) );
            animateText( "Steve crafts a masterwork diamond sword!", 35 );
          } else {
            bgLabel.setIcon( getImageIcon( "cave 2_3 iron.gif" ) );
            animateText( "Steve crafts a masterwork iron sword!", 35 );
          }
        } else if( stageCnt[ 0 ] == 8 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          bgLabel.setIcon( getImageIcon( "cave 2_2.gif" ) );
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setIcon( getImageIcon ( "Steve_head.png" ) );
          headLabel.setVisible( true );
          nameLabel.setText( "Steve" );
          nameLabel.setVisible( true );
          animateText( "Here ya go, one of my finest works!\r\nOkay let's proceed. I'll give you light.\r\n" +
          "And you lead the way friend.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 9 && animationFinished ) {
          stageCnt[ 0 ]++;
          curStage = "cave 3";
          animationFinished = false;
          bgLabel.setIcon( getImageIcon( "cave 3_1.gif" ) );
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          animateText( "Allan approaches a long abandoned mineshaft. Soft winds carry chilling sounds from the dark and dingy cave. " +
          "Our heros muster up the strength to proceed!", 35 );
        }
      } else if( curStage.equals( "cave 3" ) ) {
        if( stageCnt[ 0 ] == 4 && animationFinished ) {
          stageCnt[ 0 ]++;
          setNewSong( "cave song 2" );
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 2_1 dark.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              headLabel.setVisible( true );
              dialogueArea.setVisible( true );
              dialogueArea.setText( null );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "I can't see anything bruh.\r\nThis was a bad idea....", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 4000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              headLabel.setVisible( false );
              nameLabel.setText( "???" );
              dialogueArea.setText( null );
              animateText( "sssSSSsss...", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              headLabel.setVisible( true );
              nameLabel.setText( "Allan" );
              dialogueArea.setText( null );
              animateText( "Huh? What the heck was that?!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueLabel.setVisible( false );
              dialogueArea.setVisible( false );
              dialogueArea.setText( null );
              nameLabel.setVisible( false );
              headLabel.setVisible( false );
              ImageIcon death = getImageIcon( "cave 2_2 creeper.gif" );
              death.getImage().flush();
              bgLabel.setIcon( death );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 1500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              animateText( "OH SHOOT!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 2000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setText( null );
              dialogueArea.setVisible( false );
              dialogueLabel.setVisible( false );
              nameLabel.setVisible( false );
              headLabel.setVisible( false );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 4500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 2000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.remove( bgLabel );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel)label ).fadeImages();
              gameOverDialogue( "chapter1 defeat.gif" );
            }
          });
          timer.setRepeats( false );
          timer.start();
          panel.removeMouseListener( this );
        } else if ( stageCnt[ 0 ] == 10 && animationFinished ) {
          stageCnt[ 0 ]++;
          setNewSong( "cave song 2" );
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          if( diamonds ) {
            bgLabel.setIcon( getImageIcon( "cave 3_2 diamond.gif" ) );
          } else {
            bgLabel.setIcon( getImageIcon( "cave 3_2 iron.gif" ) );
          }
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              nameLabel.setText( "Allan" );
              headLabel.setVisible( true );
              headLabel.setIcon( allanHeadImage );
              animateText( "Yo! Gross! What are those!?", 35, dialogueArea, false );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setText( null );
              headLabel.setIcon( getImageIcon( "Steve_head.png" ) );
              nameLabel.setText( "Steve" );
              animateText( "Don't just stand there! Give them a taste of that sword!\r\nHURRY!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 11 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          nameLabel.setText( "Allan" );
          nameLabel.setVisible( false );
          headLabel.setIcon( allanHeadImage );
          headLabel.setVisible( false );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          if( diamonds ) {
            bgLabel.setIcon( getImageIcon( "cave 3_3 diamond.gif" ) );
            animateText( "The sword is way too heavy for Allan to wield. His swings are too slow and predictable for the mobs.", 35 );
            Timer timer = new Timer( 7000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                textArea.setVisible( false );
                textArea.setText( null );
                textLabel.setVisible( false );
                bgLabel.setIcon( getImageIcon( "cave 3_4 diamond.gif" ) );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 3000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                ImageIcon death = getImageIcon( "cave 3_5 diamond.gif" );
                death.getImage().flush();
                bgLabel.setIcon( death );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 5000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                label = new FadeLabel( "BLACK.gif", "chapter1 defeat.gif", 2000 );
                label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                panel.remove( bgLabel );
                panel.add( label );
                panel.revalidate();
                ( ( FadeLabel)label ).fadeImages();
                gameOverDialogue( "chapter1 defeat.gif" );
              }
            });
            timer.setRepeats( false );
            timer.start();
            panel.removeMouseListener( this );
          } else {
            bgLabel.setIcon( getImageIcon( "cave 3_3 iron.gif" ) );
            animateText( "The sword strikes swiftly as Allan easily disposes of the mobs.", 35, textArea, false );
            Timer timer = new Timer( 7000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                textArea.setVisible( false );
                textArea.setText( null );
                textLabel.setVisible( false );
                ImageIcon img = getImageIcon( "cave 3_4 iron.gif" );
                img.getImage().flush();
                bgLabel.setIcon( img );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 3000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                ImageIcon img = getImageIcon( "cave 3_5 iron.gif" );
                img.getImage().flush();
                bgLabel.setIcon( img );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 3000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                nameLabel.setVisible( true );
                nameLabel.setText( "Steve" );
                headLabel.setVisible( true );
                headLabel.setIcon( getImageIcon( "Steve_head.png" ) );
                animateText( "Well done!\r\nQuick, let's get out of here!", 35, dialogueArea );
              }
            });
            timer.setRepeats( false );
            timer.start();
          }
        } else if( stageCnt[ 0 ] == 12 && animationFinished && !diamonds ) {
          stageCnt[ 0 ]++;
          setNewSong( "cave song 3" );
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 4_1.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "More paths appear as Allan and Steve approach the heart of the cave. " +
              "The air becomes dense with nast as toxic fumes seem to be seeping from the cavern walls.", 35 );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 13 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          panel.remove( bgLabel );
          panel.remove( choice4 );
          panel.add( choice4 );
          placeComponent( 293, 180, choice4 ); // original: 383, 260
          bgLabel.setIcon( getImageIcon( "cave 4_1.gif" ) );
          panel.add( bgLabel );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage );
          panel.revalidate();
          animateText( "I can't see a thing! Which direction should we go??", 35, dialogueArea );
          Timer timer = new Timer( 3000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setIcon( getImageIcon( "south_button.png" ) );
              choice1.setRolloverIcon( getImageIcon( "south_button h.png" ) );
              choice2.setIcon( getImageIcon( "east_button.png" ) );
              choice2.setRolloverIcon( getImageIcon( "east_button h.png" ) );
              choice3.setIcon( getImageIcon( "west_button.png" ) );
              choice3.setRolloverIcon( getImageIcon( "west_button h.png" ) );
              choice4.setIcon( getImageIcon( "north_button.png" ) );
              choice4.setRolloverIcon( getImageIcon( "north_button h.png" ) );
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice3.setVisible( true );
              choice4.setVisible( true );
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      } else if( curStage.equals( "cave 5" ) ) {
        if( stageCnt[ 0 ] == 14 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          bgLabel.setIcon( getImageIcon( "cave 5_1.gif" ) );
          nameLabel.setText( "Allan" );
          headLabel.setIcon( allanHeadImage );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setVisible( true );
          nameLabel.setVisible( true );
          dialogueArea.setText( null );
          animateText( "The water has to be coming from somewhere!\r\nWhere shall we look next?", 35, dialogueArea, false );
          Timer timer = new Timer( 4000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice3.setVisible( true );
              choice4.setVisible( true );
              choice5.setVisible( true );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 16 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          nameLabel.setVisible( false );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( false );
          headLabel.setIcon( allanHeadImage );
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 5_3.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              animateText( "Yo! Check out these boats!", 35, dialogueArea, false );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              nameLabel.setText( "Steve" );
              headLabel.setIcon( getImageIcon( "Steve_head.png" ) );
              dialogueArea.setText( null );
              animateText( "This is great! You take one and look for you friend.\r\n" +
              "I'll take the other and find my own way out.\r\nFarewell my friend.", 35, dialogueArea, false );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 7000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              nameLabel.setText( "Allan" );
              headLabel.setIcon( allanHeadImage );
              dialogueArea.setText( null );
              animateText( "Good luck and thanks for the sword!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 17 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setText( null );
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 5_4.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "Letting the water carry his vessel, an exit looms near. " +
              "A glint of gold catches the eye of the greedy adventurer.", 35 );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 18 && animationFinished ) {
          stageCnt[ 0 ]++;
          curStage = "cave 6";
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 6_1.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "Lo and behold, it is treasure. Allan is faced with another dilemma. "+
              "How should he proceed?", 35 );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 4000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setIcon( getImageIcon( "search around button.png" ) );
              choice1.setRolloverIcon( getImageIcon( "search around button h.png" ) );
              choice2.setIcon( getImageIcon( "open chest button.png" ) );
              choice2.setRolloverIcon( getImageIcon( "open chest button h.png" ) );
              choice1.setVisible( true );
              choice2.setVisible( true );
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
      } else if( curStage.equals( "cave 6" ) ) {
        if( stageCnt[ 0 ] == 20 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          bgLabel.setIcon( getImageIcon( "cave 6_3.gif" ) );
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          Timer timer = new Timer( 4000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              bgLabel.setIcon( getImageIcon( "cave 6_4.gif" ) );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "After running for a bit, Allan stumbles upon a treasure room full of gold. " +
              "And there is yet another treasure chest. How should he proceed this time?", 35 );
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 5500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setVisible( true );
              choice2.setVisible( true );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 22 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 6_6.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animateText( "Ugh... I'm lost. These paths all look the same!\r\n" +
              "I'm not sure if Rudy is even down here anymore.\r\nWait a second... I hear water!", 35, dialogueArea );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 23 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          bgLabel.setIcon( getImageIcon( "cave 6_7 exit.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "Allan follows the sound of water and stumbles upon a large waterfall. As he looks up...\r\n"+
              "He is greeted by sunlight.", 35 );
            }
          });
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 24 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          textArea.setVisible( false );
          textArea.setText( null );
          textLabel.setVisible( false );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          animateText( "THE EXIT!\r\nFINALLY SOME FRESH AIR!\r\nLooks like it's morning already.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 25 && animationFinished ) {
          stageCnt[ 0 ]++;
          animationFinished = false;
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setVisible( false );
          try {
            return;
          } finally {
            ch1Boss();
          }
        }
      }
    }
  }

  private void ch1Boss() {
    curStage = "ch1 boss";
    resetScreen();
    frame.add( panel );
    resetChoiceButtonsNoRollover();
    bgLabel.setIcon( getImageIcon( "ch1 boss 1.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
    setNewSong( "ch1 boss start" );
    animationFinished = false;
    Timer timer = new Timer( 1000, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        textArea.setVisible( true );
        textLabel.setVisible( true );
        animateText( "A large mass of trees greet Allan near the exit of the cave. "+
        "He follows the river until he reaches a small bridge along the path. The end draws near.", 35 );
      }
    });
    timer.setRepeats( false );
    timer.start();
    SlidePuzzleGUI puzzle = new SlidePuzzleGUI();
    panel.addMouseListener( new MouseAdapter() {
      @Override
      public void mouseClicked( MouseEvent evt ) {
        if( animationFinished ) {
          animationFinished = false;
          if( stageCnt[ 0 ] == 0 ) {
            stageCnt[ 0 ]++;
            bgLabel.setIcon( getImageIcon( "ch1 boss 2.gif" ) );
            textArea.setVisible( false );
            textArea.setText( null );
            textLabel.setVisible( false );
            Timer timer = new Timer( 1000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                nameLabel.setVisible( true );
                headLabel.setVisible( true );
                animateText( "I hope Steve made it out alright.\r\nCome to think of it...\r\n" +
                "I hope Rudy made it out as well.", 35, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 6000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                nameLabel.setText( "???" );
                headLabel.setIcon( null );
                animateText( "HELP!", 25, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 3000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                nameLabel.setText( "Allan" );
                headLabel.setIcon( allanHeadImage );
                animateText( "Huh? Who was that?", 35, dialogueArea );
              }
            });
            timer.setRepeats( false );
            timer.start();
          } else if( stageCnt[ 0 ] == 1 ) {
            stageCnt[ 0 ]++;
            bgLabel.setIcon( getImageIcon( "ch1 boss 3.gif" ) );
            dialogueArea.setVisible( false );
            dialogueArea.setText( null );
            dialogueLabel.setVisible( false );
            headLabel.setVisible( false );
            nameLabel.setVisible( false );
            Timer timer = new Timer( 1000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                nameLabel.setVisible( true );
                headLabel.setVisible( true );
                animateText( "YOU!", 25, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 3000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                nameLabel.setText( "Seto Kaiba" );
                headLabel.setIcon( getImageIcon( "Kaiba_head.png" ) );
                animateText( "Who me?\r\nMove along peasant.\r\nI must deliver this prisoner to justice.", 35, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 6000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                nameLabel.setText( "Rudy" );
                headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
                animateText( "ALLAN IT'S ME.\r\nHelp!", 35, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 4000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                nameLabel.setText( "Allan" );
                headLabel.setIcon( allanHeadImage );
                animateText( "Let my friend go you fiend!", 35, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 4000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                nameLabel.setText( "Seto Kaiba" );
                headLabel.setIcon( getImageIcon( "Kaiba_head.png" ) );
                animateText( "FIEND? HAH!\r\nNope, I don't think I will.", 35, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 4000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                nameLabel.setText( "Allan" );
                headLabel.setIcon( allanHeadImage );
                animateText( "That's it. You asked for it...\r\n\r\nIT'S TIME TO DUEL!", 20, dialogueArea );
              }
            });
            timer.setRepeats( false );
            timer.start();
          } else if( stageCnt[ 0 ] == 2 ) {
            stageCnt[ 0 ]++;
            dialogueArea.setText( null );
            dialogueArea.setVisible( false );
            dialogueLabel.setVisible( false );
            nameLabel.setVisible( false );
            headLabel.setVisible( false );
            bgLabel.setIcon( getImageIcon( "ch1 boss 4.gif" ) );
            Timer timer = new Timer( 1000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                headLabel.setIcon( getImageIcon( "Kaiba_head.png" ) );
                headLabel.setVisible( true );
                nameLabel.setText( "Seto Kaiba" );
                nameLabel.setVisible( true );
                animateText( "Fine I'll play your game you imbecil.\r\nSince you challenged me, I shall take the liberty of going first.\r\n" + 
                "Enjoy my display of strength for it may be your last!", 35, dialogueArea );
              }
            });
            timer.setRepeats( false );
            timer.start();
          } else if( stageCnt[ 0 ] == 3 ) {
            stageCnt[ 0 ]++;
            dialogueArea.setText( null );
            dialogueArea.setVisible( false );
            dialogueLabel.setVisible( false );
            nameLabel.setVisible( false );
            headLabel.setVisible( false );
            ImageIcon img = getImageIcon( "ch1 boss 5.gif" );
            img.getImage().flush();
            bgLabel.setIcon( img );
            Timer timer = new Timer( 3000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                headLabel.setIcon( getImageIcon( "Kaiba_head.png" ) );
                headLabel.setVisible( true );
                nameLabel.setText( "Seto Kaiba" );
                nameLabel.setVisible( true );
                animateText( "My move! I first play the spell Dragon Shrine and send two dragons to the graveyard. " + 
                "Next, I activate Monster Reborn and special summon Blue-Eyes White Dragon.", 35, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 10500, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                animateText( "You're lucky I can't attack when going first, " + 
                "so I guess that concludes my turn.\r\n\r\nYour move.", 35, dialogueArea );
              }
            });
            timer.setRepeats( false );
            timer.start();
          } else if( stageCnt[ 0 ] == 4 ) {
            stageCnt[ 0 ]++;
            dialogueArea.setText( null );
            dialogueArea.setVisible( false );
            dialogueLabel.setVisible( false );
            nameLabel.setVisible( false );
            headLabel.setVisible( false );
            bgLabel.setIcon( getImageIcon( "ch1 boss 6.gif" ) );
            Timer timer = new Timer( 1000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                headLabel.setIcon( allanHeadImage );
                nameLabel.setText( "Allan" );
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                headLabel.setVisible( true );
                nameLabel.setVisible( true );
                animateText( "Aw crap if I don't do something quick he's going to attack and win next turn!\r\n" + 
                "My only hope is to believe in the Heart of the Cards and draw and assemble Exodia.\r\n" +
                "Here goes nothing!", 35, dialogueArea );
              }
            });
            timer.setRepeats( false );
            timer.start();
          } else if( stageCnt[ 0 ] == 5 ) {
            stageCnt[ 0 ]++;
            // slide duel
            panel.removeMouseListener( this );
            resetScreen();
            setNewSong( "ch1 boss battle" );
            frame.setContentPane( puzzle );
            frame.revalidate();
            puzzle.getTimer().start();
          }
        }
      }
    });
  }

  private void afterCh1Boss( boolean playerWin ) {
    resetScreen();
    frame.add( panel );
    animationFinished = false;
    if( playerWin ) {
      bgLabel.setIcon( getImageIcon( "exodia final.png" ) );
      panel.add( bgLabel );
      frame.revalidate();
      Timer timer = new Timer( 1000, new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent e ){
          panel.addMouseListener( new Chapter1EndMouseListener() );
        }
      });
      timer.setRepeats( false );
      timer.start();
    } else {
      //setNewSong( "ch1 boss start" );
      bgLabel.setIcon( getImageIcon( "ch1 boss 6.gif" ) );
      panel.add( bgLabel );
      frame.revalidate();
      Timer timer = new Timer( 1000, new ActionListener(){
        @Override
        public void actionPerformed( ActionEvent e ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setIcon( getImageIcon( "Kaiba_head.png" ) );
          headLabel.setVisible( true );
          nameLabel.setText( "Seto Kaiba" );
          nameLabel.setVisible( true );
          animateText( "HAH! You reek of weakness.\r\nNOW observe true STRENGTH!", 35, dialogueArea );
        }
      });
      timer.setRepeats( false );
      timer.start();
      panel.addMouseListener( new MouseAdapter() {
        @Override
        public void mouseClicked( MouseEvent evt ) {
          if( animationFinished ) {
            panel.removeMouseListener( this );
            dialogueArea.setText( null );
            bgLabel.setIcon( getImageIcon( "ch1 boss lose1.gif" ) );
            animateText( "Blue-Eyes White Dragon!\r\n\r\nBURST STREAM OF DESTRUCTION!", 35, dialogueArea );
            Timer t = new Timer( 4000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                bgLabel.setIcon( getImageIcon( "ch1 boss lose2.gif" ) );
              }
            });
            t.setRepeats( false );
            t.start();
            t = new Timer( t.getDelay() + 3000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setVisible( false );
                dialogueArea.setText( null );
                nameLabel.setVisible( false );
                headLabel.setVisible( false );
                dialogueLabel.setVisible( false );
                label = new FadeLabel( "ch1 boss lose2.gif", "chapter1 defeat.gif", 3000 );
                label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                panel.remove( bgLabel );
                panel.add( label );
                panel.revalidate();
                ( ( FadeLabel )label ).fadeImages();
                gameOverDialogue( "chapter1 defeat.gif" );
              }
            });
            t.setRepeats( false );
            t.start();
          }
        }
      });
    }
  }

  private class Chapter1EndMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked( MouseEvent evt ) {
      if( animationFinished ) {
        dialogueArea.setVisible( false );
        dialogueArea.setText( null );
        dialogueLabel.setVisible( false );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        textArea.setVisible( false );
        textArea.setText( null );
        textLabel.setVisible( false );
      }
      if( stageCnt[ 0 ] == 0 ) {
        stageCnt[ 0 ]++;
        bgLabel.setIcon( getImageIcon( "ch1 boss 6.5.gif" ) );
        Timer timer = new Timer( 1000, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setVisible( true );
            headLabel.setIcon( allanHeadImage );
            nameLabel.setVisible( true );
            nameLabel.setText( "Allan" );
            animateText( "I've assembled all the 5 needed cards!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( stageCnt[ 0 ] == 1 && animationFinished ) {
        stageCnt[ 0 ]++;
        bgLabel.setIcon( getImageIcon( "ch1 boss 7.gif" ) );
        Timer timer = new Timer( 1000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setVisible( true );
            nameLabel.setVisible( true );
            headLabel.setIcon( getImageIcon( "Kaiba_head.png" ) );
            nameLabel.setText( "Seto Kaiba" );
            dialogueArea.setText( null );
            animateText( "NO WAY!\r\nIT'S EXODIA!\r\nNO ONE'S EVER BEEN ABLE TO CALL HIM!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( stageCnt[ 0 ] == 2 && animationFinished ) {
        stageCnt[ 0 ]++;
        bgLabel.setIcon( getImageIcon( "ch1 boss 8.gif" ) );
        Timer timer = new Timer( 1000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setVisible( true );
            headLabel.setIcon( allanHeadImage );
            nameLabel.setVisible( true );
            nameLabel.setText( "Allan" );
            animateText( "HAH! Weak...\r\n\r\nExodia! OBLITERATE!!!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( stageCnt[ 0 ] == 3 && animationFinished ) {
        stageCnt[ 0 ]++;
        bgLabel.setIcon( getImageIcon( "ch1 boss 9.gif" ) );
        Timer timer = new Timer( 500, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setVisible( true );
            nameLabel.setVisible( true );
            headLabel.setIcon( getImageIcon( "Kaiba_head.png" ) );
            nameLabel.setText( "Seto Kaiba" );
            dialogueArea.setText( null );
            animateText( "IMPOSSIBLE!\r\nNOOOOOOOOOOO!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( stageCnt[ 0 ] == 4 && animationFinished ) {
        stageCnt[ 0 ]++;
        panel.removeMouseListener( this );
        bgLabel.setIcon( getImageIcon( "ch1 boss 10.gif" ) );
        setNewSong( "ch1 boss win" );
        Timer timer = new Timer( 1000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setVisible( true );
            headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
            nameLabel.setVisible( true );
            nameLabel.setText( "Rudy" );
            animateText( "DUDE WHAT WAS THAT!\r\nTHAT WAS EPIC!\r\nTHANKS FOR SAVING ME!", 35, dialogueArea );
          }
        });
        timer.setRepeats( false );
        timer.start();
        ch1EndDialogue();
      }
      animationFinished = false;
    }
  }

  private void ch1EndDialogue() {
    ImageIcon rudyHeadImage = getImageIcon( "Rudy_head.png" );
    panel.addMouseListener( new MouseAdapter() {
      @Override
      public void mouseClicked( MouseEvent evt ) {
        if( animationFinished ) {
          animationFinished = false;
          dialogueArea.setText( null );
          if( stageCnt[ 0 ] == 5 ) {
            nameLabel.setText( "Allan" );
            headLabel.setIcon( allanHeadImage );
            animateText( "No problem bro, but I have some questions...\r\nTo put it simply, what happened while I was gone?", 35, dialogueArea );
          } else if( stageCnt[ 0 ] == 6 ) {
            nameLabel.setText( "Rudy" );
            headLabel.setIcon( rudyHeadImage );
            animateText( "I don't know man...\r\nIt just happened suddenly one day.\r\n" +
            "These childhood icons just sprang to life out of nowhere.", 35, dialogueArea, false );
            Timer timer = new Timer( 7000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                dialogueArea.setText( null );
                animateText( "Soon after I couldn't keep in touch with anyone...\r\n" + 
                "And before I knew it, I was captured by that dude you just beat!\r\nI hope everyone else is okay....", 35, dialogueArea );
              }
            });
            timer.setRepeats( false );
            timer.start();
          } else if( stageCnt[ 0 ] == 7 ) {
            nameLabel.setText( "Allan" );
            headLabel.setIcon( allanHeadImage );
            animateText( "Interesting...\r\nAnyways, I believe I know where Jonathan is.\r\nRecalling the hint...", 35, dialogueArea );
          } else if( stageCnt[ 0 ] == 8 ) {
            nameLabel.setVisible( false );
            headLabel.setVisible( false );
            dialogueArea.setVisible( false );
            dialogueLabel.setVisible( false );
            textArea.setVisible( true );
            textLabel.setVisible( true );
            Font font = textArea.getFont();
            textArea.setFont( font.deriveFont( Font.ITALIC ) );
            animateText( "\"Second is thou large fella, lost in a vastness of green, seeking divine light\"", 35 );
          } else if( stageCnt[ 0 ] == 9 ) {
            textArea.setVisible( false );
            textArea.setText( null );
            textLabel.setVisible( false );
            Font font = textArea.getFont();
            textArea.setFont( font.deriveFont( Font.PLAIN ) );
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setIcon( rudyHeadImage );
            headLabel.setVisible( true );
            nameLabel.setText( "Rudy" );
            nameLabel.setVisible( true );
            animateText( "That's gotta be Jonathan!\r\nHe's got to be somewhere in this dense forest! Or is it a jungle?\r\n" + 
            "The dude that captured me also mentioned a light that we must head towards.", 35, dialogueArea );
          } else if( stageCnt[ 0 ] == 10 ) {
            headLabel.setIcon( allanHeadImage );
            nameLabel.setText( "Allan" );
            animateText( "A light you say...\r\nWhatever it is, we must find it.\r\n" +
            "Jonathan must be near it if he is also searching for it.", 35, dialogueArea );
          } else if( stageCnt[ 0 ] == 11 ) {
            headLabel.setIcon( rudyHeadImage );
            nameLabel.setText( "Rudy" );
            animateText( "Alright!\r\nI'm pumped!\r\nLET'S GO SAVE OUR FRIENDS!", 35, dialogueArea );
          } else if( stageCnt[ 0 ] == 12 ) {
            headLabel.setIcon( allanHeadImage );
            nameLabel.setText( "Allan" );
            animateText( "YEAH! ONWARDS!\r\nFollow me closely.\r\nThis is going to be a dangerous ride...", 35, dialogueArea, false );
            Timer timer = new Timer( 5000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                mediaPlayer.stop();
                dialogueArea.setVisible( false );
                dialogueArea.setText( null );
                nameLabel.setVisible( false );
                headLabel.setVisible( false );
                dialogueLabel.setVisible( false );
                label = new FadeLabel( "ch1 boss 10.gif", "BLACK.gif", 3000 );
                label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                panel.remove( bgLabel );
                panel.add( label );
                panel.revalidate();
                ( ( FadeLabel )label ).fadeImages();
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 5000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                panel.remove( label );
                bgLabel.setIcon( getImageIcon( "BLACK.gif" ) );
                panel.add( bgLabel );
                panel.revalidate();
                nameLabel.setText( "||||||||||||||||||||||||||" ); // 26
                nameLabel.setVisible( true );
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                animateText( "It would seem he has surpassed the first trial my liege....\r\n" + 
                "What shall we do about this pest?", 50, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 8000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                nameLabel.setText( "[||||||||||]" );
                nameLabel.setForeground( Color.BLACK );
                animateText( "Not to worry my child.\r\nThis is just the beginning.\r\n" +
                "He will soon learn his place in the world.", 50, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 7500, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                animateText( "They will all learn soon enough...\r\n", 50, dialogueArea, false );
              }
            });
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 5000, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                nameLabel.setForeground( Color.WHITE );
                nameLabel.setVisible( false );
                dialogueLabel.setVisible( false );
                dialogueArea.setVisible( false );
                animationFinished = true;
              }
            });
            timer.setRepeats( false );
            timer.start();
          } else if( stageCnt[ 0 ] == 13 ) {
            try {
              return;
            } finally {
              ch2Cinematic();
            }
          }
          stageCnt[ 0 ]++;
        }
      }
    });
  }

  private void ch2Cinematic() {
    resetAll();
    frame.add( panel );
    bgLabel.setIcon( getImageIcon( "ch2/cinematic 1.gif" ) );
    panel.add( bgLabel );
    frame.revalidate();
    setNewSong( "ch2 cinematic song" );
    Timer timer = new Timer( 500, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 2.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 3.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 4.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 12500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 5.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 6.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 7.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 8.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 9.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 10.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 2750, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 11.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 4500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 12.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 2250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 13.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 14.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 15.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 16.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 5750, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 17.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6750, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 18.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 19.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 20.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 21.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 22.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 23.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 2750, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 24.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 5500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 25.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 2000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 26.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 2250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 27.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 28.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 29.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 30.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 31.gif" ) );
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 6250, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        bgLabel.setIcon( getImageIcon( "ch2/cinematic 32.gif" ) );
        Timer t = new Timer( 1000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
            textArea.setVisible( true );
            textLabel.setVisible( true );
            textLabel.setIcon( getImageIconCh2( "text set 2.png" ) );
            animateText( "With the Beacon in full view of our heroes, another friend yearns for their aid.\r\n\r\n" + 
            "A new act is about to begin...", 50 );
          }
        });
        t.setRepeats( false );
        t.start();
      }
    });
    timer.setRepeats( false );
    timer.start();
    timer = new Timer( timer.getDelay() + 14000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        textArea.setVisible( false );
        textLabel.setIcon( getImageIcon( "text set.png" ) );
        textArea.setText( null );
        textLabel.setVisible( false );
        mediaPlayer.stop();
        bgLabel.setIcon( getImageIcon( "ch2/chapter2 title.gif" ) );
        panel.addMouseListener( new MouseAdapter() {
          @Override
          public void mouseClicked( MouseEvent e ) {
            panel.removeMouseListener( this );
            label = new FadeLabel( "ch2/chapter2 title.gif", "ch2/gate 1.gif", 3000 );
            label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
            panel.remove( bgLabel );
            panel.add( label );
            panel.revalidate();
            ( ( FadeLabel )label ).fadeImages();
            Timer t = new Timer( 3100, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent ee ) {
                try {
                  return;
                } finally {
                  startChapter2( false );
                }
              }
            });
            t.setRepeats( false );
            t.start();
          }
        });
      }
    });
    timer.setRepeats( false );
    timer.start();
  }

  private void startChapter2( boolean skip ) {
    setNewSong( "gatekeeper song" );
    resetScreen();
    curStage = "gatekeeper";
    dialogueLabel.setIcon( getImageIconCh2( "dialogue set 2.png" ) );
    textLabel.setIcon( getImageIconCh2( "text set 2.png" ) );
    frame.add( panel );
    bgLabel.setIcon( getImageIconCh2( "gate 1.gif" ) );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    panel.add( bgLabel );
    resetChoiceButtonsNoRollover();
    panel.revalidate();
    Timer timer = new Timer( 1000, new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        headLabel.setVisible( true );
        headLabel.setIcon( allanHeadImage );
        nameLabel.setVisible( true );
        animateText( "Goddamn that was a TREK!", 35, dialogueArea );
      }
    });
    if( !skip ) {
      timer.setRepeats( false );
      timer.start();
    } else {
      stageCnt[ 0 ] = 14;
    }
    panel.addMouseListener( new GatekeeperMouseListener() );
    if( skip ) {
      bgLabel.setIcon( getImageIconCh2( "gate 2.1.gif" ) );
      Timer t = new Timer( 1000, new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent evt ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage);
          animateText( "Alright Rudy good luck!", 35, dialogueArea, false );
        }
      } );
      t.setRepeats( false );
      t.start();
      t = new Timer( t.getDelay() + 3500, new ActionListener(){
        @Override
        public void actionPerformed( ActionEvent e ) {
          dialogueArea.setText( null );
          Font f = dialogueArea.getFont();
          dialogueArea.setFont( f.deriveFont( Font.ITALIC ) );
          animateText( "Why do I have a bad feeling about this....", 35, dialogueArea );
        }
      } );
      t.setRepeats( false );
      t.start();
    }
  }

  private class GatekeeperMouseListener extends MouseAdapter {
    public void mouseClicked( MouseEvent evt ) {
      if( animationFinished ) {
        animationFinished = false;
        dialogueArea.setVisible( false );
        dialogueArea.setText( null );
        dialogueLabel.setVisible( false );
        textArea.setVisible( false );
        textArea.setText( null );
        textLabel.setVisible( false );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        if( stageCnt[ 0 ] == 0 ) { 
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Rudy" );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          headLabel.setVisible( true );
          animateText( "I'm so tired man.\r\nMy vegan legs are giving out bro...", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 1 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setIcon( allanHeadImage );
          headLabel.setVisible( true );
          animateText( "Hang in there buddy we're almost there!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 2 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Gatekeeper" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIconCh2( "Gatekeeper_head.png" ) );
          animateText( "WHO GOES THERE!\r\nNONE SHALL PASS!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 3 ) {
          bgLabel.setIcon( getImageIconCh2( "gate 2.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              nameLabel.setText( "Allan" );
              headLabel.setVisible( true );
              headLabel.setIcon( allanHeadImage);
              animateText( "Cravens!\r\nWhat is that?!", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 4 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Rudy" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          animateText( "Don't ask me man!\r\nYou're the one with the sword!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 5 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage);
          animateText( "Who are you and what do you want?", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 6 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Gatekeeper" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIconCh2( "Gatekeeper_head.png" ) );
          animateText( "I am the keeper of this fine grove.\r\nYou two will be the last of the many to join into the games.\r\n" +
          "But before we begin, you two will take my test.", 35, dialogueArea );
          Timer timer = new Timer( 6000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              choice1.setIcon( getImageIcon( "greet.png" ) );
              choice1.setRolloverIcon( getImageIcon( "greetH.png" ) );
              choice2.setIcon( getImageIcon( "passive.png" ) );
              choice2.setRolloverIcon( getImageIcon( "passiveH.png" ) );
              choice3.setIcon( getImageIcon( "nast.png" ) );
              choice3.setRolloverIcon( getImageIcon( "nastH.png" ) );
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice3.setVisible( true );
              choice1.addActionListener( new GatekeeperChoiceHandler() );
              choice2.addActionListener( new GatekeeperChoiceHandler() );
              choice3.addActionListener( new GatekeeperChoiceHandler() );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 10 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Gatekeeper" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIconCh2( "Gatekeeper_head.png" ) );
          animateText( "Very well. The initiation has begun.\r\nYou shall fetch me one delightful mushroom from the fields of Fangorn Forest.\r\n" +
          "Pick the wrong shroom, then you shall consume it and reap its pestilence.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 11 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          animateText( "I shall provide but 3 hints for you foolish travelers.\r\nNo more no less.\r\n" + 
          "3 chances to prove your worth for the Glutton Games.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 12 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Rudy" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          animateText( "Easy peasy!\r\nDon't worry buddy, I got this!\r\nGive me the hint already!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 13 ) {
          bgLabel.setIcon( getImageIconCh2( "gate 2.1.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              nameLabel.setText( "Allan" );
              headLabel.setVisible( true );
              headLabel.setIcon( allanHeadImage);
              animateText( "Alright Rudy good luck!", 35, dialogueArea, false );
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3500, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setText( null );
              Font f = dialogueArea.getFont();
              dialogueArea.setFont( f.deriveFont( Font.ITALIC ) );
              animateText( "Why do I have a bad feeling about this....", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 14 ) {
          Font f = dialogueArea.getFont();
          dialogueArea.setFont( f.deriveFont( Font.PLAIN ) );
          bgLabel.setIcon( getImageIconCh2( "gate 3 game.gif" ) );
          String [] hint1 = { "the mushroom I seek is tainted and vile looking.", 
          "the mushroom I crave is plump and delicious looking.",
          "the mushroom I desire is yuck." };
          int rng = ( ( int )( Math.random() * 3 ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              nameLabel.setText( "Gatekeeper" );
              headLabel.setVisible( true );
              headLabel.setIcon( getImageIconCh2( "Gatekeeper_head.png" ) );
              animateText( "First, " + hint1[ rng ], 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 15 ) {
          panel.removeMouseListener( this );
          MushroomGame game = new MushroomGame();
          panel.addMouseListener( game );
        } else if( stageCnt[ 0 ] == 16 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage);
          animateText( "Yo good job Rudy.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 17 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Gatekeeper" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIconCh2( "Gatekeeper_head.png" ) );
          bgLabel.setIcon( getImageIconCh2( "gate 4.gif" ) );
          animateText( "Take this travellers. It is a red infinity stone.\r\nYou shall need it for your endeavors in the games.\r\n" + 
          "Listen closely for I shall only say this once.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 18 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          bgLabel.setIcon( getImageIconCh2( "gate 2.gif" ) );
          animateText( "The games are consisted of teams of various sizes.\r\nEach group bearing one of the 5 accessible infinity stones.\r\n" + 
          "There is one stone only obtainable through the forest. First come first serve.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 19 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          animateText( "Since stones are limited, only 5 teams will be granted access\r\nTo his highness' quarters to take part in the final match.\r\n" +
          "Teams will only be granted access if they have all 6 stones.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 20 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          animateText( "Don't forget, the stones are useless without the glove.\r\nHow you get the stones is up to you.\r\n" +
          "Good luck out there you two.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 21 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Rudy" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          bgLabel.setIcon( getImageIconCh2( "gate 5.gif" ) );
          animateText( "Woah where'd he go?\r\nEh. Whatever.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 22 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage );
          animateText( "Alright Rudy let's get this bread!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 23 ) {
          try {
            return;
          } finally {
            goToDurthu( false );
          }
        } else if( stageCnt[ 0 ] == 24 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Gatekeeper" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIconCh2( "Gatekeeper_head.png" ) );
          animateText( "Those that do not take the test are deemed gay.\r\nAnd are sentenced to five years in ye old gulag.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 25 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage);
          animateText( "Ay bro chill...\r\nAight fine I'll take your test.", 35, dialogueArea );
          stageCnt[ 0 ] = 9; // setting to 10 to separate from rest of dialogue
        } else if( stageCnt[ 0 ] == 26 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Rudy" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          animateText( "Uhhh...\r\nHere you go Allan!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 27 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage);
          animateText( "How bad could it be?\r\n....\r\nYUCK! Tastes like bitter melon!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 28 ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          bgLabel.setIcon( getImageIconCh2( "gate 4 lose.gif" ) );
          animateText( "Whoaaa...\r\nI feel funny.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 29 ) {
          panel.removeMouseListener( this );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          nameLabel.setText( "Rudy" );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          bgLabel.setIcon( getImageIconCh2( "gate 5 lose.gif" ) );
          animateText( "Ay bruh you good???\r\nBro???", 35, dialogueArea );
          Timer timer = new Timer( 4000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              mediaPlayer.stop();
              dialogueArea.setVisible( false );
              dialogueArea.setText( null );
              nameLabel.setVisible( false );
              headLabel.setVisible( false );
              dialogueLabel.setVisible( false );
              label = new FadeLabel( "ch2/gate 5 lose.gif", "BLACK.gif", 3000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.remove( bgLabel );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel )label ).fadeImages();
            }
          });
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 4000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              panel.remove( label );
              label = new FadeLabel( "BLACK.gif", "chapter2 defeat.gif", 2000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel )label ).fadeImages();
              gameOverDialogue( "chapter2 defeat.gif" );
            }
          });
          timer.setRepeats( false );
          timer.start();
        }
        stageCnt[ 0 ]++;
      }
    }
  }

  private class GatekeeperChoiceHandler implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent e ) {
      choice1.setVisible( false );
      choice2.setVisible( false );
      choice3.setVisible( false );
      if( e.getSource().equals( choice1 ) ) {
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        nameLabel.setVisible( true );
        nameLabel.setText( "Allan" );
        headLabel.setVisible( true );
        headLabel.setIcon( allanHeadImage);
        animateText( "Yes sir Mr. gatekeeper sir!\r\nWe'll do our best on your test.", 35, dialogueArea );
        stageCnt[ 0 ] = 10; // setting to 10 to separate from rest of dialogue
      } else if( e.getSource().equals( choice2 ) ) {
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        nameLabel.setVisible( true );
        nameLabel.setText( "Allan" );
        headLabel.setVisible( true );
        headLabel.setIcon( allanHeadImage);
        animateText( "Test? Easy.\r\nGive me your best shot!", 35, dialogueArea );
        stageCnt[ 0 ] = 10; // setting to 10 to separate from rest of dialogue
      } else if( e.getSource().equals( choice3 ) ) {
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        nameLabel.setVisible( true );
        nameLabel.setText( "Allan" );
        headLabel.setVisible( true );
        headLabel.setIcon( allanHeadImage);
        animateText( "Test?\r\nI yaint takin no damn te...", 35, dialogueArea );
        stageCnt[ 0 ] = 24
        ; // setting to 24 to separate from rest of dialogue
      }
    }
  }

  private class MushroomGame extends MouseAdapter {
    String [] allMushrooms;
    String answer;
    private final int ROW = 256;
    private final int COL = 227;
    int rng;
    int numTry = 1;

    public MushroomGame() {
      super();
      rng = ( ( int )( Math.random() * 18 ) ); // pick random mushroom from 18 to be right answer
      allMushrooms = new String[]{ "single|orange", "single|purple", "multiple|yellow", "multiple|purple", "single|green", "multiple|blue",
                       "single|red", "single|yellow", "multiple|red", "multiple|blue", "single|purple", "single|blue",
                       "multiple|green", "multiple|orange", "single|red", "multiple|yellow", "single|green", "multiple|green" };
      answer = allMushrooms[ rng ];
    }

    public void mouseClicked( MouseEvent evt ) {
      //System.err.println( "X: " + evt.getX() + ", Y: " + evt.getY() ); // debugging
      if( numTry == 1 ) {
        numTry++;
        int row = evt.getY() / ROW;
        int col = evt.getX() / COL;
        int choice = row * 6 + col;
        if( choice == rng ) {
          stageCnt[ 0 ] = 15;
          panel.removeMouseListener( this );
          panel.addMouseListener( new GatekeeperMouseListener() );
          bgLabel.setIcon( getImageIconCh2( "gate 2.gif" ) );
          stageCnt[ 0 ] = 16; // win scene
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animateText( "Ahh... that's the one. A fine shroom to say the least.\r\nCongratulations travelers, you have passed my test\r\n" + 
              "And have earned the right to participate in the games.", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          if( answer.contains( "single" ) ) {
            animateText( "Wrong choice lads. The mushroom is singular and likes to grow alone in the shade.", 35, dialogueArea );
          } else if( answer.contains( "multiple" ) ) {
            animateText( "Nope this isn't it. The mushroom tends to grow multiple caps and is not singular.", 35, dialogueArea );
          }
        }
      } else if( numTry == 2 && animationFinished ) {
        animationFinished = false;
        dialogueArea.setVisible( false );
        dialogueLabel.setVisible( false );
        dialogueArea.setText( null );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        numTry++;
      } else if( numTry == 3 ) {
        numTry++;
        int row = evt.getY() / ROW;
        int col = evt.getX() / COL;
        int choice = row * 6 + col;
        if( choice == rng ) {
          stageCnt[ 0 ] = 16;
          panel.removeMouseListener( this );
          panel.addMouseListener( new GatekeeperMouseListener() );
          bgLabel.setIcon( getImageIconCh2( "gate 2.gif" ) );
          stageCnt[ 0 ] = 16; // win scene
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animateText( "Ahh... that's the one. A fine shroom to say the least.\r\nCongratulations travelers, you have passed my test\r\n" + 
              "And have earned the right to participate in the games.", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          nameLabel.setVisible( true );
          headLabel.setVisible( true );
          animateText( "This is it boys. Your last hint. The color of this mushroom is " + 
          answer.substring( answer.indexOf( "|" ) + 1 ) + ".", 35, dialogueArea );
        }
      } else if( numTry == 4 && animationFinished ) {
        animationFinished = false;
        dialogueArea.setVisible( false );
        dialogueLabel.setVisible( false );
        dialogueArea.setText( null );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        numTry++;
      } else if( numTry == 5 ) {
        numTry++;
        int row = evt.getY() / ROW;
        int col = evt.getX() / COL;
        int choice = row * 6 + col;
        panel.removeMouseListener( this );
        panel.addMouseListener( new GatekeeperMouseListener() );
        bgLabel.setIcon( getImageIconCh2( "gate 2.gif" ) );
        if( choice == rng ) {
          stageCnt[ 0 ] = 16; // win scene
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animateText( "Ahh... that's the one. A fine shroom to say the least.\r\nCongratulations travelers, you have passed my test\r\n" + 
              "And have earned the right to participate in the games.", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else {
          stageCnt[ 0 ] = 26; // lose scene
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              nameLabel.setVisible( true );
              headLabel.setVisible( true );
              animateText( "In all my years I have never seen adventurers fail this task.\r\nYou know the drill. Eat up.", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        }
      }
    }
  }

  private void goToDurthu( boolean playSong ) {
    if( playSong )
      setNewSong( "gatekeeper song" );
    curStage = "durthu";
    resetScreen();
    frame.add( panel );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    resetChoiceButtonsNoRollover();
    bgLabel.setIcon( getImageIconCh2( "durthu 1.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
    animationFinished = false;
    Timer timer = new Timer( 1000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        headLabel.setVisible( true );
        headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
        nameLabel.setVisible( true );
        nameLabel.setText( "Rudy" );
        animateText( "So... it's just like the Hunger Games huh.", 35, dialogueArea );
      }
    } );
    timer.setRepeats( false );
    timer.start();
    panel.addMouseListener( new DurthuMouseListener() );
  }

  public class DurthuMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked( MouseEvent e ) {
      if( animationFinished ) {
        animationFinished = false;
        dialogueArea.setText( null );
        textArea.setText( null );
        if( stageCnt[ 0 ] == 0 ) {
          headLabel.setIcon( allanHeadImage );
          nameLabel.setText( "Allan" );
          animateText( "Yeah but we need to collect all the stones to win.\r\nI think every team received a different starter stone than us.\r\n" +
          "Red huh... At least it ain't the green stone. Hate that one...", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 1 ) {
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          textArea.setVisible( true );
          textLabel.setVisible( true );
          animateText( "An announcer's voice can be heard in the distance.", 35 );
        } else if( stageCnt[ 0 ] == 2 ) {
          Font font = textArea.getFont();
          textArea.setFont( font.deriveFont( Font.ITALIC ) );
          animateText( "The 20th team [TEAM RUSH HOUR] has be administered. Our lineup has been concluded.\r\n" +
          "The 24th annual Gluttony Games will begin in a moment's time.\r\nPrepare yourselves, and let God take the wheel.", 35 );
        } else if( stageCnt[ 0 ] == 3 ) {
          Font font = textArea.getFont();
          textArea.setFont( font.deriveFont( Font.PLAIN ) );
          animateText( "A loud siren can be heard in the distance.\r\n\r\nThe games have begun.", 50 );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              setNewSong( "Gluttony Games song" );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 4 ) {
          textArea.setVisible( false );
          textLabel.setVisible( false );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setVisible( true );
          nameLabel.setVisible( true );
          animateText( "What in God's green Earth did that gatekeeper call us?\r\nWhatever.\r\n" + 
          "Alright Rudy stick with me.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 5 ) {
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          bgLabel.setIcon( getImageIconCh2( "durthu 2.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
              nameLabel.setVisible( true );
              nameLabel.setText( "Rudy" );
              animateText( "WHOA DUDE!\r\nTHE TREE'S ALIVE MAN!", 35, dialogueArea, false );
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice3.setVisible( true );
              choice1.setIcon( getImageIcon( "greet.png" ) );
              choice1.setRolloverIcon( getImageIcon( "greetH.png" ) );
              choice2.setIcon( getImageIcon( "passive.png" ) );
              choice2.setRolloverIcon( getImageIcon( "passiveH.png" ) );
              choice3.setIcon( getImageIcon( "nast.png" ) );
              choice3.setRolloverIcon( getImageIcon( "nastH.png" ) );
              choice1.addActionListener( new DurthuChoiceHandler() );
              choice2.addActionListener( new DurthuChoiceHandler() );
              choice3.addActionListener( new DurthuChoiceHandler() );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 10 ) { // friendly
          nameLabel.setText( "Durthu" );
          headLabel.setIcon( getImageIconCh2( "Durthu_head.png" ) );
          animateText( "Oak? I am no oak.\r\nIt is I, Durthu.\r\nThe strongest treant to ever walk these groves.", 35, dialogueArea );
          stageCnt[ 0 ] = 15;
        } else if( stageCnt[ 0 ] == 12 ) {
          nameLabel.setText( "Durthu" );
          headLabel.setIcon( getImageIconCh2( "Durthu_head.png" ) );
          animateText( "Why yes it is I, Durthu.\r\nIt would seem my fame has reached world wide status indeed!\r\n" +
          "Hah!", 35, dialogueArea );
          stageCnt[ 0 ] = 15;
        } else if( stageCnt[ 0 ] == 14 ) {
          nameLabel.setText( "Durthu" );
          headLabel.setIcon( getImageIconCh2( "Durthu_head.png" ) );
          animateText( "Ah. A mere ant wishes to quarrel with the great Durthu.\r\nIt would seem your intelligence is matched by your size!\r\n" +
          "I will have fun with you yes.", 35, dialogueArea );
          stageCnt[ 0 ] = 15;
        } else if( stageCnt[ 0 ] == 16 ) {
          animateText( "You two are the first to encounter me.\r\nAnd I have no wish to partake in these horrid games.\r\n" +
          "I shall grant you my stone, but must first complete my trial.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 17 ) {
          animateText( "A game of references!\r\nI am a fine connoisseur of famous quotes and trivia I'll have you know.\r\n" +
          "Answer these 2 questions, and I shall grant you my stone.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 18 ) {
          animateText( "Answer wrongly, and I shall call the forest's greatest horrors to have their way with you.\r\n" +
          "Are we ready now?", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 19 ) {
          nameLabel.setText( "Allan" );
          headLabel.setIcon( allanHeadImage );
          animateText( "Another test already...\r\nFine. Let's have at it then.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 20 ) {
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          panel.removeMouseListener( this );
          panel.addMouseListener( new DurthuRiddleGame() );
        }
        stageCnt[ 0 ]++;
      }
    }
  }

  private class DurthuChoiceHandler implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent e ) {
      dialogueArea.setText( null );
      headLabel.setIcon( allanHeadImage );
      nameLabel.setText( "Allan" );
      choice1.setVisible( false );
      choice2.setVisible( false );
      choice3.setVisible( false );
      if( e.getSource().equals( choice1 ) ) {
        animateText( "We come in peace!\r\nPlease do not harm us oh wise oak!", 35, dialogueArea );
        stageCnt[ 0 ] = 10;
      } else if( e.getSource().equals( choice2 ) ) {
        animateText( "It.. it can't be...\r\nDurthu is that you?", 35, dialogueArea );
        stageCnt[ 0 ] = 12;
      } else if( e.getSource().equals( choice3 ) ) {
        animateText( "Meh.\r\nThat piece of paper looks weak as hell.\r\nHow do you do ya big ol sprig?", 35, dialogueArea );
        stageCnt[ 0 ] = 14;
      }
    }
  }

  private class DurthuRiddleGame extends MouseAdapter implements ActionListener {
    private Riddle [] riddles = new Riddle[ 8 ];
    private Riddle [] chosenRiddles = new Riddle[ 2 ];
    private int rng;
    private int currentRiddle;
    public DurthuRiddleGame() {
      animationFinished = false;
      stageCnt[ 0 ] = 0;
      resetChoiceButtonsNoRollover();
      choice1.addActionListener( this );
      choice2.addActionListener( this );
      choice3.addActionListener( this );
      riddles[ 0 ] = new Riddle( "What fake name does Channing Tatum call himself when being hustled by gangsters in    the movie 21 Jump Street?" +
      "\r\n(My name's ____.)", choice2 );
      riddles[ 1 ] = new Riddle( "What does Gandalf scream before he dies?\r\n(You shall not ____!)", choice2 );
      riddles[ 2 ] = new Riddle( "Which superhero from The Incredibles lost their super suit?", choice1 );
      riddles[ 3 ] = new Riddle( "Which Spiderman actor famously quotes \"it's pizza time.\"?", choice1 );
      riddles[ 4 ] = new Riddle( "What code references a white sock in Monster's Inc.?", choice2 );
      riddles[ 5 ] = new Riddle( "What vegetable does Shrek compare ogres to?", choice3 );
      riddles[ 6 ] = new Riddle( "What food does Patrick Star think is an instrument?", choice3 );
      riddles[ 7 ] = new Riddle( "What is Lightning McQueen's catchphrase?", choice1 );
      ImageIcon [] images = new ImageIcon[ 6 ];
      for( int i = 0; i < riddles.length; i++ ) {
        for( int j = 1; j <= 6; j++ ) {
          //System.err.println( "Durthu Riddles/" + ( ( char )( ( int )'a' + i ) ) + ( j + 1 ) / 2 + ".png" );
          if( ( j - 1 ) % 2 == 0 )
            images[ j - 1 ] = getImageIconCh2( "Durthu Riddles/" + ( ( char )( ( int )'a' + i ) ) + ( j + 1 ) / 2 + ".png" );
          else
            images[ j - 1 ] = getImageIconCh2( "Durthu Riddles/" + ( ( char )( ( int )'a' + i ) ) + ( j + 1 ) / 2 + "h.png" );
        }
        riddles[ i ].setAnswers( images );
      }
      for( int i = 0; i < chosenRiddles.length; i++ ) {
        rng = ( int )( Math.random() * 8 );
        if( !riddles[ rng ].getChosen() ) {
          chosenRiddles[ i ] = riddles[ rng ];
          riddles[ rng ].setChosen();
        } else {
          i--;
        }
      }
      displayRiddle();
    }

    public void displayRiddle() {
      Timer timer = new Timer( 1000, new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent evt ) {
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setVisible( true );
          headLabel.setIcon( getImageIconCh2( "Durthu_head.png" ) );
          nameLabel.setVisible( true );
          nameLabel.setText( "Durthu" );
          animateText( chosenRiddles[ currentRiddle ].getRiddle(), 35, dialogueArea );
        }
      } );
      timer.setRepeats( false );
      timer.start();
      final Timer t = new Timer( 1000, null );
      t.addActionListener( new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent evt ) {
          if( animationFinished ) {
            animationFinished = false;
            choice1.setIcon( chosenRiddles[ currentRiddle ].getAnswers()[ 0 ] );
            choice1.setRolloverIcon( chosenRiddles[ currentRiddle ].getAnswers()[ 1 ] );
            choice2.setIcon( chosenRiddles[ currentRiddle ].getAnswers()[ 2 ] );
            choice2.setRolloverIcon( chosenRiddles[ currentRiddle ].getAnswers()[ 3 ] );
            choice3.setIcon( chosenRiddles[ currentRiddle ].getAnswers()[ 4 ] );
            choice3.setRolloverIcon( chosenRiddles[ currentRiddle ].getAnswers()[ 5 ] );
            choice1.setVisible( true );
            choice2.setVisible( true );
            choice3.setVisible( true );
            t.stop();
          }
        }
      } );
      t.start();
    }
    @Override
    public void mouseClicked( MouseEvent e ) {
      if( animationFinished ) {
        if( stageCnt[ 0 ] == 1 ) {
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          displayRiddle();
        } else if( stageCnt[ 0 ] == 3 ) {
          panel.removeMouseListener( this );
          goToLabyrinth( false );
        } else if( stageCnt[ 0 ] == 10 ) {
          dialogueArea.setVisible( false );
          dialogueArea.setText( null );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          bgLabel.setIcon( getImageIconCh2( "durthu 3 lose.gif" ) );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "MAOKAI!\r\nIRONBARK!\r\nHave your way with these imbeciles!", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 11 ) {
          dialogueArea.setText( null );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          nameLabel.setText( "Rudy" );
          animateText( "Oh no! Looks like the tables have turned.\r\nThe veggies have come to vanquish us!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 12 ) {
          dialogueArea.setText( null );
          headLabel.setIcon( allanHeadImage );
          nameLabel.setText( "Allan" );
          animateText( "Darn...", 35, dialogueArea );
          panel.removeMouseListener( this );
          Timer timer = new Timer( 3000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              mediaPlayer.stop();
              dialogueArea.setVisible( false );
              dialogueArea.setText( null );
              dialogueLabel.setVisible( false );
              headLabel.setVisible( false );
              nameLabel.setVisible( false );
              label = new FadeLabel( "ch2/durthu 3 lose.gif", "BLACK.gif", 3000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.remove( bgLabel );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel )label ).fadeImages();
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 4000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              panel.remove( label );
              label = new FadeLabel( "BLACK.gif", "chapter2 defeat.gif", 2000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel )label ).fadeImages();
              gameOverDialogue( "chapter2 defeat.gif" );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        }
        stageCnt[ 0 ]++;
      }
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
      choice1.setVisible( false );
      choice2.setVisible( false );
      choice3.setVisible( false );
      dialogueArea.setText( null );
      if( currentRiddle == 1 && e.getSource().equals( chosenRiddles[ currentRiddle ].getCorrectAnswer() ) ) {
        dialogueArea.setVisible( false );
        dialogueLabel.setVisible( false );
        nameLabel.setVisible( false );
        headLabel.setVisible( false );
        Timer timer = new Timer( 1000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent evt ) {
            bgLabel.setIcon( getImageIconCh2( "durthu 3.gif" ) );
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setVisible( true );
            nameLabel.setVisible( true );
            animateText( "I see you two are men of culture as well.\r\nI pass upon you our stone.\r\n" +
            "Fare thee well lads.", 35, dialogueArea );
          }
        } );
        timer.setRepeats( false );
        timer.start();
        stageCnt[ 0 ] = 3; // win
      } else if( e.getSource().equals( chosenRiddles[ currentRiddle ].getCorrectAnswer() ) ) {
        currentRiddle++;
        animateText( "Well done mortals.\r\nNow for the tougher one.", 35, dialogueArea );
        stageCnt[ 0 ] = 1; // continue with riddles
      } else {
        animateText( "How dare you get that wrong.\r\nEvery mortal adores that scene!\r\nBegone from my sight!", 35, dialogueArea );
        stageCnt [ 0 ] = 10; // lose
      }
    }
  }

  private class Riddle {
    private String riddle;
    private ImageIcon [] answers;
    private JButton correct;
    private boolean chosen;
    
    public Riddle( String question, JButton correct ) {
      this.riddle = question;
      this.correct = correct;
      this.answers = new ImageIcon[ 6 ];
      chosen = false;
    }
    public void setAnswers( ImageIcon [] a ) {
      int num = 0;
      for( ImageIcon s: a ) {
        this.answers[ num ] = s;
        num++;
      }
    }
    public void setChosen() {
      chosen = true;
    }
    public String getRiddle() {
      return riddle;
    }
    public ImageIcon[] getAnswers() {
      return answers;
    }
    public JButton getCorrectAnswer() {
      return correct;
    }
    public boolean getChosen() {
      return chosen;
    }
  }

  private void goToLabyrinth( boolean skip ) {
    if( skip ) {
      setNewSong( "Gluttony Games song" );
    }
    curStage = "labyrinth";
    resetScreen();
    frame.add( panel );
    versatileLabel1 = new JLabel( "30" );
    versatileLabel2 = new JLabel( getImageIconCh2( "stamina HUD.gif" ) );
    versatileLabel1.setPreferredSize( new Dimension( 100, 100 ) );
    versatileLabel1.setFont( new Font( "Pixel-Noir", Font.PLAIN, 30 ) );
    versatileLabel1.setForeground( new Color( 0, 255, 0 ) );
    panel.add( versatileLabel1 );
    placeComponent( 653, 25, versatileLabel1 );
    panel.add( versatileLabel2 );
    placeComponent( 633, 0, versatileLabel2 );
    versatileLabel1.setVisible( false );
    versatileLabel2.setVisible( false );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    panel.add( choice4 );
    choice1.setVisible( false );
    choice2.setVisible( false );
    choice3.setVisible( false );
    choice4.setVisible( false );
    resetChoiceButtonsNoRollover();
    choice1.setIcon( getImageIconCh2( "forest_button.png" ) );
    choice1.setRolloverIcon( getImageIconCh2( "forest_button h.png" ) );
    choice2.setIcon( getImageIconCh2( "jungle_button.png" ) );
    choice2.setRolloverIcon( getImageIconCh2( "jungle_button h.png" ) );
    choice3.setIcon( getImageIconCh2( "swamp_button.png" ) );
    choice3.setRolloverIcon( getImageIconCh2( "swamp_button h.png" ) );
    placeComponent( 293, 180, choice4 ); // used to be 383, 260
    bgLabel.setIcon( getImageIconCh2( "lab 1 transition.gif" ) );
    panel.add( bgLabel );
    panel.revalidate();
    Timer timer = new Timer( 1000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        textArea.setVisible( true );
        textLabel.setVisible( true );
        animateText( "The announcer's voice can be heard once again.", 35 );
      }
    } );
    timer.setRepeats( false );
    timer.start();
    panel.addMouseListener( new LabyrinthMouseListener() );
  }

  private class LabyrinthMouseListener extends MouseAdapter implements ActionListener {
    private int stamina;
    private String correctCode;
    private final String [] forestCodes;
    private final String [] jungleCodes;
    private final String [] swampCodes;
    private boolean instantDeath = false;

    public LabyrinthMouseListener() {
      stamina = 30;
      forestCodes = new String []{ "32785", "91246", "06547", "03761", "68954", "47986", "58912", "12470" };
      jungleCodes = new String []{ "N9TT-9G0A", "B7FQ-RANC", "QK6A-JI6S", "7ETR-0A6C", "SXFP-CHYK", "ONI6-S89U", "XNSS-HSJW", "3NGU-8XTJ" };
      swampCodes = new String []{ "Azazer!", "Asozul!", "Aviges!", "Akesel!", "Apajus!", "Aboqor!", "Abipuc!", "Adujor!" };

      choice1.addActionListener( this );
      choice2.addActionListener( this );
      choice3.addActionListener( this );
      choice4.addActionListener( this );
    }

    @Override
    public void mouseClicked( MouseEvent e ) {
      if( animationFinished ) {
        animationFinished = false;
        dialogueArea.setText( null );
        textArea.setText( null );
        if( stageCnt[ 0 ] == 0 ) {
          Font f = textArea.getFont();
          textArea.setFont( f.deriveFont( Font.ITALIC ) );
          animateText( "Alright ladies and gents.\r\n15 teams remain in the games as I speak.\r\n" +
          "6 of which have two stones in their possession.", 35 );
        } else if( stageCnt[ 0 ] == 1 ) {
          animateText( "This has been your hourly update.\r\nGood luck out there adventurers,\r\n" +
          "And as always, may God take the wheel.", 35 );
        } else if( stageCnt[ 0 ] == 2 ) {
          Font f = textArea.getFont();
          textArea.setFont( f.deriveFont( Font.PLAIN ) );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          dialogueArea.setVisible( true );
          dialogueLabel.setVisible( true );
          headLabel.setVisible( true );
          headLabel.setIcon( allanHeadImage );
          nameLabel.setVisible( true );
          nameLabel.setText( "Allan" );
          animateText( "It seems like we are tied for first hah!\r\nToo easy!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 3 ) {
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          nameLabel.setText( "Rudy" );
          animateText( "Is it just me or is the path disappearing?\r\n" +
          "Also... why do I get the feeling that we're being followed?", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 4 ) {
          bgLabel.setIcon( getImageIconCh2( "lab 1.gif" ) );
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "Looks like this is the entrance to a trial of something called the Labyrinth.\r\n" +
              "There's something posted here!\r\nThis might come in handy later so I'll remember it for now.", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 5 ) { // pick biomes
          bgLabel.setIcon( getImageIconCh2( "lab 2.gif"  ) );
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          headLabel.setIcon( allanHeadImage );
          nameLabel.setVisible( false );
          nameLabel.setText( "Allan" );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "It would seem the biomes divide at this junction.\r\nWe should get moving as fast as possible to lose our pursuer.\r\n" +
              "Now which biome should we tackle first? Better watch our stamina!", 35, dialogueArea, false );
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 7000, new ActionListener(){ // show stamina HUD
            @Override
            public void actionPerformed( ActionEvent e ) {
              versatileLabel1.setVisible( true );
              versatileLabel2.setVisible( true );
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice3.setVisible( true );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 8 ) {
          bgLabel.setIcon( getImageIconCh2( "lab lose 1.gif" ) );
          setNewSong( "ch2/lab lose song" );
          versatileLabel1.setVisible( false );
          versatileLabel2.setVisible( false );
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          nameLabel.setText( "Rudy" );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "Hey does the sky look weird or is it just me?", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 9 ) {
          headLabel.setIcon( allanHeadImage );
          nameLabel.setText( "Allan" );
          animateText( "You're right!\r\nThe sun has turned red!\r\nWhat's going on???", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 10 ) {
          bgLabel.setIcon( getImageIconCh2( "lab lose 2.gif" ) );
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "WHO ARE YOU?!", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 11 ) {
          headLabel.setIcon( getImageIconCh2( "Itachi_head.png" ) );
          nameLabel.setText( "Itachi" );
          animateText( "So I've finally caught up to you intrusive infants.\r\nHand over your stones and no one gets hurt!", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 12 ) {
          headLabel.setIcon( allanHeadImage );
          nameLabel.setText( "Allan" );
          animateText( "Nah. Bug off!\r\nWe'll do nothing of the sort.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 13 ) {
          headLabel.setIcon( getImageIconCh2( "Itachi_head.png" ) );
          nameLabel.setText( "Itachi" );
          animateText( "Very well.\r\nLet's see if you can survive the Uchiha clan's signature fireball technique.", 35, dialogueArea );
        } else if( stageCnt[ 0 ] == 14 ) {
          panel.removeMouseListener( this );
          bgLabel.setIcon( getImageIconCh2( "lab lose 3.gif" ) );
          dialogueArea.setVisible( false );
          dialogueLabel.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              dialogueArea.setVisible( true );
              dialogueLabel.setVisible( true );
              headLabel.setVisible( true );
              nameLabel.setVisible( true );
              animateText( "Katon! Goukakyuu no Jutsu!", 35, dialogueArea );
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              bgLabel.setIcon( getImageIconCh2( "lab lose 4.gif" ) );
              dialogueArea.setVisible( false );
              dialogueLabel.setVisible( false );
              headLabel.setVisible( false );
              nameLabel.setVisible( false );
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 3000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              mediaPlayer.stop();
              label = new FadeLabel( "ch2/lab lose 4.gif", "BLACK.gif", 2000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.remove( bgLabel );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel )label ).fadeImages();
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 2100, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              panel.remove( label );
              label = new FadeLabel( "BLACK.gif", "chapter2 defeat.gif", 2000 );
              label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
              panel.add( label );
              panel.revalidate();
              ( ( FadeLabel)label ).fadeImages();
              gameOverDialogue( "chapter2 defeat.gif" );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 16 ) {
          bgLabel.setIcon( getImageIconCh2( "forest 1 transition.gif" ) );
          dialogueLabel.setVisible( false );
          dialogueArea.setVisible( false );
          headLabel.setVisible( false );
          nameLabel.setVisible( false );
          Timer timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "Many leaves and thorn brushes dot the windy, shallow paths of the forest.\r\n" +
              "Progress towards the light has been hindered.\r\n-10 Stamina", 35 );
              updateStamina( -10 );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 17 ) {
          bgLabel.setIcon( getImageIconCh2( "forest 1.gif" ) );
          textArea.setVisible( false );
          textLabel.setVisible( false );
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          nameLabel.setText( "Rudy" );
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              if( checkStaminaLoss() ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                headLabel.setVisible( true );
                nameLabel.setVisible( true );
                animateText( "That's a strong looking river.\r\nHow should we cross it?", 35, dialogueArea, false );
              }
            }
          } );
          timer.setRepeats( false );
          timer.start();
          timer = new Timer( timer.getDelay() + 4000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              choice4.setIcon( getImageIconCh2( "swim_button.png" ) );
              choice4.setRolloverIcon( getImageIconCh2( "swim_button h.png" ) );
              choice1.setIcon( getImageIconCh2( "look_upstream_button.png" ) );
              choice1.setRolloverIcon( getImageIconCh2( "look_upstream_button h.png" ) );
              choice2.setIcon( getImageIconCh2( "look_downstream_button.png" ) );
              choice2.setRolloverIcon( getImageIconCh2( "look_downstream_button h.png" ) );
              choice3.setIcon( getImageIconCh2( "jump_across_button.png" ) );
              choice3.setRolloverIcon( getImageIconCh2( "jump_across_button h.png" ) );
              choice1.setVisible( true );
              choice2.setVisible( true );
              choice3.setVisible( true );
              choice4.setVisible( true );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( stageCnt[ 0 ] == 19 ) {
          if( !instantDeath ) {
            textArea.setText( null );
            bgLabel.setIcon( getImageIconCh2( "forest 2.gif") );
            animateText( "After traveling a while, the duo once again finds themselves trapped between a rock and a hard place....", 35 );
          } else {
            bgLabel.setIcon( getImageIconCh2( "forest 3 lose.gif" ) );
            textArea.setVisible( false );
            textLabel.setVisible( false );
            Timer timer = new Timer( 1000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                headLabel.setVisible( true );
                headLabel.setIcon( allanHeadImage );
                nameLabel.setVisible( true );
                nameLabel.setText( "Allan" );
                animateText( "Look Rudy! A blue buff sentinel!\r\nRudy??? Where'd ya go??\r\nUH OH!", 35, dialogueArea );
              }
            } );
            timer.setRepeats( false );
            timer.start();
          }
        } else if( stageCnt[ 0 ] == 20 ) {
          if( !instantDeath ) {
            textArea.setVisible( false );
            textLabel.setVisible( false );
            bgLabel.setIcon( getImageIconCh2( "forest 3.gif" ) );
            Timer timer = new Timer( 1000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                dialogueArea.setVisible( true );
                dialogueLabel.setVisible( true );
                headLabel.setVisible( true );
                headLabel.setIcon( getImageIconCh2( "Snorlax_head.png" ) );
                nameLabel.setVisible( true );
                nameLabel.setText( "Snorlax" );
                animateText( "Snoree Snoree lax?", 35, dialogueArea );
              }
            } );
            timer.setRepeats( false );
            timer.start();
          } else {
            panel.removeMouseListener( this );
            bgLabel.setIcon( getImageIconCh2( "forest 3.1 lose.gif" ) );
            versatileLabel1.setVisible( false );
            versatileLabel2.setVisible( false );
            dialogueArea.setVisible( false );
            dialogueLabel.setVisible( false );
            headLabel.setVisible( false );
            nameLabel.setVisible( false );
            Timer timer = new Timer( 3000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                mediaPlayer.stop();
                label = new FadeLabel( "ch2/forest 3.1 lose.gif", "BLACK.gif", 2000 );
                label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                panel.remove( bgLabel );
                panel.add( label );
                panel.revalidate();
                ( ( FadeLabel )label ).fadeImages();
              }
            } );
            timer.setRepeats( false );
            timer.start();
            timer = new Timer( timer.getDelay() + 2100, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                panel.remove( label );
                label = new FadeLabel( "BLACK.gif", "chapter2 defeat.gif", 2000 );
                label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
                panel.add( label );
                panel.revalidate();
                ( ( FadeLabel)label ).fadeImages();
                gameOverDialogue( "chapter2 defeat.gif" );
              }
            } );
            timer.setRepeats( false );
            timer.start();
          }
        } else if( stageCnt[ 0 ] == 21 ) {
          headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
          nameLabel.setText( "Rudy" );
          animateText( "Do something bruh!\r\nAt this rate, our pursuer will catch us!", 35, dialogueArea );
          Timer timer = new Timer( 4000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              
            }
          } );
        } else if( stageCnt[ 0 ] == 22 ) {

        }
        stageCnt[ 0 ]++;
      }
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
      choice1.setVisible( false );
      choice2.setVisible( false );
      choice3.setVisible( false );
      choice4.setVisible( false );
      textArea.setVisible( false );
      textArea.setText( null );
      textLabel.setVisible( false );
      dialogueArea.setVisible( false );
      dialogueArea.setText( null );
      dialogueLabel.setVisible( false );
      headLabel.setVisible( false );
      nameLabel.setVisible( false );
      animationFinished = false;
      if( stageCnt[ 0 ] == 6 && e.getSource().equals( choice1 ) ) { // forest
        // generate correct code from forest codes
        correctCode = forestCodes[ (int)(Math.random() * forestCodes.length) ];

        Timer timer = new Timer( 1000, new ActionListener() {
          public void actionPerformed( ActionEvent evt ) {
            dialogueArea.setVisible( true );
            dialogueLabel.setVisible( true );
            headLabel.setVisible( true );
            nameLabel.setVisible( true );
            headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
            nameLabel.setText( "Rudy" );
            animateText( "Alright the code for forest should be... " + correctCode, 35, dialogueArea );
          }
        } );
        timer.setRepeats( false );
        timer.start();
        stageCnt[ 0 ] = 16; // forest arc
      } else if( stageCnt[ 0 ] == 18 ) {
        if( e.getSource().equals( choice4 ) ) { // swim
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "Allan and Rudy easily swim across the river with ease thanks to their vast experience of " +
              "swimming the waves of Huntington. The water reinvigorates the duo.\r\n+5 stamina", 35 );
              updateStamina( 5 );
              stageCnt[ 0 ] = 19;
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( e.getSource().equals( choice1 ) ) { // look upstream (death)
          instantDeath = true;
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "After hiking a mile up the hill, the duo reach the opening to a cave... After entering, a stone golem is there " +
              "to meet the adventurers. It doesn't look too kind....", 35 );
              stageCnt[ 0 ] = 19;
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( e.getSource().equals( choice2 ) ) { // look downstream
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "After traversing harsh terrain, Allan spots a couple of stones conforming together to form a land " +
              "bridge which allows them to cross safely. Lucky!\r\n-5 stamina", 35 );
              updateStamina( -5 );
              stageCnt[ 0 ] = 19;
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else if( e.getSource().equals( choice3 ) ) { // jump across
          Timer timer = new Timer( 1000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              textArea.setVisible( true );
              textLabel.setVisible( true );
              animateText( "The duo easily jump over the river. Well... that was easier done than said.\r\n-0 stamina", 35 );
              stageCnt[ 0 ] = 19;
            }
          } );
          timer.setRepeats( false );
          timer.start();
        }
      }
    }
    
    private void updateStamina( int stam ) { // returns true if stamina > 0 (ALIVE)
      stamina += stam;
      int red, green;
      green = ( int )( 512 * ( ( double )stamina ) / 30 );
      red = ( int )( 512 * ( ( 30.0 - stamina ) / 30 ) );
      if( green > 255 ) {
        green = 255;
      }
      if( red > 255 ) {
        red = 255;
      }
      final int finalRed = red;
      final int finalGreen = green;
      Timer timer = new Timer( 4000, new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent evt ) {
          if( stamina <= 0 ) {
            versatileLabel1.setText( " 0" );
          } else if( stamina < 10 ) {
            versatileLabel1.setText( "0" + stamina );
          } else {
            versatileLabel1.setText( "" + stamina );
          }
          if( stamina >= 30 ) {
            versatileLabel1.setForeground( Color.CYAN );
          } else if( stamina <= 0 ) {
            versatileLabel1.setForeground( Color.RED );
          } else {
            versatileLabel1.setForeground( new Color( finalRed, finalGreen, 0 ) );
          }
        }
      } );
      timer.setRepeats( false );
      timer.start();
    }
    private boolean checkStaminaLoss() {
      if( stamina <= 0 ) {
        headLabel.setIcon( getImageIcon( "Rudy_head.png" ) );
        nameLabel.setText( "Rudy" );
        dialogueArea.setVisible( true );
        dialogueLabel.setVisible( true );
        headLabel.setVisible( true );
        nameLabel.setVisible( true );
        animateText( "This is it man....\r\nI've reached my limit...", 35, dialogueArea, false );
        Timer timer = new Timer( 4500, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent evt ) {
            headLabel.setIcon( allanHeadImage );
            nameLabel.setText( "Allan" );
            animateText( "Come on man we're almost there!", 35, dialogueArea );
            stageCnt[ 0 ] = 7;
          }
        } );
        timer.setRepeats( false );
        timer.start();
        return false;
      }
      return true;
    }
  }

  private void gameOverDialogue( String gameOverBG ) {
    if( gameOverBG.contains( "2" ) ) {
      setNewSong( "defeat music 2" );
    } else {
      setNewSong( "defeat music" );
    }
    stageCnt[ 0 ] = 0;
    Timer timer4 = new Timer( 3200, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        tryAgain = new JButton( getImageIcon( "try again.png" ) );
        setButtonTransparent( tryAgain );
        placeComponent( 523, 576, tryAgain );
        panel.add( tryAgain );
        tryAgain.addMouseListener( new MenuButtonMouseAdapter() );
        tryAgain.setVisible( false );
        panel.remove( label );
        bgLabel.setIcon( getImageIcon( gameOverBG ) );
        panel.add( bgLabel );
        textArea.setVisible( true );
        textLabel.setVisible( true );
        panel.revalidate();
        animateText( "Ah. It seems you have perished from this world.\r\n" + 
        "Isn't that a shame.\r\n" + "Your journey ends here, adventurer.", 35, textArea );
      }
    } );
    timer4.setRepeats( false );
    timer4.start();
    Timer timer5 = new Timer( timer4.getDelay() + 8000, new ActionListener(){
      @Override
      public void actionPerformed( ActionEvent e ) {
        textArea.setText( "" );
        animateText( "But you mustn't give up.\r\nYour friends are still in danger.\r\n" + 
        "And you're the only one who can save them.", 35, textArea );
      }
    } );
    timer5.setRepeats( false );
    timer5.start();
    Timer timer6 = new Timer( timer5.getDelay() + 7000, new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        textArea.setText( "" );
        animateText( "Luckily, we have the tech to phase back time.\r\nYou will graciously be given a second chance.\r\n" +
        "Will you begin your adventure anew?", 35, textArea );
        panel.addMouseListener( new MouseAdapter() {
          public void mouseClicked( MouseEvent evt ) {
            if( stageCnt[ 0 ] == 0 ) {
              stageCnt[ 0 ]++;
              textArea.setText( "" );
              textArea.setVisible( false );
              textLabel.setVisible( false );
              tryAgain.setVisible( true );
              tryAgain.addActionListener( new ActionListener() {
                public void actionPerformed( ActionEvent evt ) {
                  tryAgain.setVisible( false );
                  ImageIcon defeatTransition1 = getImageIcon( "defeat transition1.gif" );
                  defeatTransition1.getImage().flush();
                  bgLabel.setIcon( defeatTransition1 );
                  Timer timer = new Timer( 5000, new ActionListener(){
                    @Override
                    public void actionPerformed( ActionEvent e ) {
                      ImageIcon defeatTransition2 = getImageIcon( "defeat transition2.gif" );
                      defeatTransition2.getImage().flush();
                      bgLabel.setIcon( defeatTransition2 );
                      Timer timer2 = new Timer( 3000, new ActionListener(){
                        @Override
                        public void actionPerformed( ActionEvent e ) {
                          stageCnt[ 0 ]++; //increment to delay retry
                        }
                      } );
                      timer2.setRepeats( false );
                      timer2.start();
                    }
                  } );
                  timer.setRepeats( false );
                  timer.start();
                }
              } );
              panel.revalidate();
            }
            if( curStage.equals( "ball_game_end" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to ball game" );
              try{
                return;
              } finally {
                startBallGame();
              }
            } else if( curStage.equals( "tubbies" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to tubbies" );
              try {
                return;
              } finally {
                endBallGame( true );
              }
            } else if( curStage.equals( "cave 0" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to gym" );
              try {
                return;
              } finally {
                goToGym( true );
              }
            } else if( curStage.contains( "cave" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to beginning of cave" );
              try {
                return;
              } finally {
                beginCave( true );
              }
            } else if( curStage.equals( "ch1 boss" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to ch1 boss" );
              try {
                return;
              } finally {
                ch1Boss();
              }
            } else if( curStage.equals( "gatekeeper" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to gatekeeper" );
              try {
                return;
              } finally {
                startChapter2( true );
              }
            } else if( curStage.equals( "durthu" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to durthu" );
              try{ 
                return;
              } finally {
                goToDurthu( true );
              }
            } else if( curStage.equals( "labyrinth" ) && stageCnt[ 0 ] == 2 ) {
              System.err.println( "resetting to labyrinth" );
              try {
                return;
              } finally {
                goToLabyrinth( true );
              }
            }
          }
        });
      }
    } );
    timer6.setRepeats( false );
    timer6.start();
  }

  private void animateText( String text, int time ) {
    final Timer t = new Timer( time, null );
    t.addActionListener( new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        textArea.setText( text.substring( 0, stringCounter ) );
        stringCounter++;

        if( stringCounter > text.length() ) {
          stringCounter = 0;
          animationFinished = true;
          t.stop();
          return;
        }
      }
    } );
    t.start();
  }

  private void animateText( String text, int time, JTextArea area, boolean changeAnimation ) {
    final Timer t = new Timer( time, null );
    t.addActionListener( new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        try{
          area.setText( text.substring( 0, stringCounter ) );
          stringCounter++;

          if( stringCounter > text.length() ) {
            stringCounter = 0;
            Timer timer = new Timer( 250, new ActionListener(){
              @Override
              public void actionPerformed( ActionEvent e ) {
                animationFinished = changeAnimation;
              }
            } );
            timer.setRepeats( false );
            timer.start();
            //animationFinished = changeAnimation;
            t.stop();
            return;
          }
        } catch( StringIndexOutOfBoundsException e ) {
          return;
        };
      }
    } );
    t.start();
  }
  private void animateText( String text, int time, JTextArea area ) {
    animateText( text, time, area, true );
  }

  // resets nearly entire GUI for debugging purposes
  private void resetAll() {
    // initializing some important stuff
    headLabel = new JLabel( allanHeadImage );
    nameLabel = new JLabel( "Allan" );
    nameLabel.setPreferredSize( new Dimension( 200, 40 ) );
    nameLabel.setFont( new Font( "Pixel-Noir", Font.BOLD, 14 ) );
    nameLabel.setOpaque( false );
    nameLabel.setForeground( Color.WHITE );
    headLabel.setPreferredSize( new Dimension( 150, 150 ) );
    headLabel.setOpaque( false );
    layout.putConstraint( SpringLayout.WEST, headLabel, 185, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, headLabel, 560, SpringLayout.NORTH, panel );
    panel.add( headLabel );
    headLabel.setVisible( false );
    layout.putConstraint( SpringLayout.WEST, nameLabel, 350, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, nameLabel, 575, SpringLayout.NORTH, panel );
    panel.add( nameLabel );
    nameLabel.setVisible( false );
    choice1 = new JButton();
    choice2 = new JButton();
    choice3 = new JButton();
    choice4 = new JButton();
    choice5 = new JButton();
    setButtonTransparent( choice1 );
    setButtonTransparent( choice2 );
    setButtonTransparent( choice3 );
    setButtonTransparent( choice4 );
    setButtonTransparent( choice5 );

    //stop music
    mediaPlayer.stop();
    resetScreen();
  }

  // resets the screen to make it easier to transition between chapters
  private void resetScreen() {
    // resetting frame
    panel.removeAll();
    frame.remove( panel );
    frame.getContentPane().removeAll();
    // resetting basics
    animationFinished = false;
    stageCnt[ 0 ] = 0;
    // resetting panel
    panel = new JPanel();
    panel.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    panel.setLayout( layout );
    panel.add( nameLabel );
    panel.add( headLabel );
    panel.add( dialogueArea );
    panel.add( dialogueLabel );
    panel.add( textArea );
    panel.add( textLabel );
    layout.putConstraint( SpringLayout.WEST, dialogueArea, 348, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, dialogueArea, 618, SpringLayout.NORTH, panel );
    placeComponent( 183, 563, dialogueLabel );
    dialogueArea.setVisible( false );
    dialogueLabel.setVisible( false );
    placeComponent( 203, 618, textArea );
    textArea.setVisible( false );
    placeComponent( 183, 598, textLabel );
    textLabel.setVisible( false );
    layout.putConstraint( SpringLayout.WEST, nameLabel, 350, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, nameLabel, 575, SpringLayout.NORTH, panel );
    nameLabel.setVisible( false );
    layout.putConstraint( SpringLayout.WEST, headLabel, 185, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, headLabel, 560, SpringLayout.NORTH, panel );
    headLabel.setVisible( false );
    // resetting dialogueArea
    dialogueArea.setText( "" );
    MouseListener [] mListeners = dialogueArea.getMouseListeners();
    for( int i = 0; i < mListeners.length; i++ ) {
      dialogueArea.removeMouseListener( mListeners[ i ] );
    }
    dialogueArea.setFont( new Font( "Pixel-Noir", Font.PLAIN, 15 ) );
    // resetting textArea
    textArea.setText( "" );
    mListeners = textArea.getMouseListeners();
    for( int i = 0; i < mListeners.length; i++ ) {
      textArea.removeMouseListener( mListeners[ i ] );
    }
    textArea.setFont( new Font( "Pixel-Noir", Font.PLAIN, 15 ) );
    mListeners = dialogueLabel.getMouseListeners();
    // resetting dialogueLabel
    for( int i = 0; i < mListeners.length; i++ ) {
      dialogueLabel.removeMouseListener( mListeners[ i ] );
    }
    // resetting bgLabel
    bgLabel = new JLabel();
  }

  // Does not need to add buttons to MenuMouseAdapter because JButton.setRolloverIcon() exists
  private void resetChoiceButtonsNoRollover() {
    for( int i = 1; i < choice1.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice1.getMouseListeners();
      choice1.removeMouseListener( m[ i ] );
      System.err.println( "Choice1 MouseListener Removed" );
    }
    for( int i = 1; i < choice2.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice2.getMouseListeners();
      choice2.removeMouseListener( m[ i ] );
      System.err.println( "Choice2 MouseListener Removed" );
    }
    for( int i = 1; i < choice3.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice3.getMouseListeners();
      choice3.removeMouseListener( m[ i ] );
      System.err.println( "Choice3 MouseListener Removed" );
    }
    for( int i = 1; i < choice4.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice4.getMouseListeners();
      choice4.removeMouseListener( m[ i ] );
      System.err.println( "Choice4 MouseListener Removed" );
    }
    for( int i = 1; i < choice5.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice5.getMouseListeners();
      choice5.removeMouseListener( m[ i ] );
      System.err.println( "Choice5 MouseListener Removed" );
    }
    for( ActionListener a: choice1.getActionListeners() ) {
      choice1.removeActionListener( a );
      System.err.println( "Choice1 ActionListener Removed" );
    }
    for( ActionListener a: choice2.getActionListeners() ) {
      choice2.removeActionListener( a );
      System.err.println( "Choice2 ActionListener Removed" );
    }
    for( ActionListener a: choice3.getActionListeners() ) {
      choice3.removeActionListener( a );
      System.err.println( "Choice3 ActionListener Removed" );
    }
    for( ActionListener a: choice4.getActionListeners() ) {
      choice4.removeActionListener( a );
      System.err.println( "Choice4 ActionListener Removed" );
    }
    for( ActionListener a: choice5.getActionListeners() ) {
      choice5.removeActionListener( a );
      System.err.println( "Choice5 ActionListener Removed" );
    }
    choice1.setRolloverIcon( null );
    choice2.setRolloverIcon( null );
    choice3.setRolloverIcon( null );
    choice4.setRolloverIcon( null );
    choice5.setRolloverIcon( null );
    placeComponent( 383, 280, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice1, 338, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.SOUTH, choice1, 0, SpringLayout.NORTH, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice3, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice3, 0, SpringLayout.SOUTH, choice2 );
    placeComponent( 383, 260, choice4 );
    layout.putConstraint( SpringLayout.WEST, choice5, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice5, 0, SpringLayout.SOUTH, choice4 );
  }
  private void resetChoiceButtons() {
    for( int i = 1; i < choice1.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice1.getMouseListeners();
      choice1.removeMouseListener( m[ i ] );
      System.err.println( "Choice1 MouseListener Removed" );
    }
    for( int i = 1; i < choice2.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice2.getMouseListeners();
      choice2.removeMouseListener( m[ i ] );
      System.err.println( "Choice2 MouseListener Removed" );
    }
    for( int i = 1; i < choice3.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice3.getMouseListeners();
      choice3.removeMouseListener( m[ i ] );
      System.err.println( "Choice3 MouseListener Removed" );
    }
    for( int i = 1; i < choice4.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice4.getMouseListeners();
      choice4.removeMouseListener( m[ i ] );
      System.err.println( "Choice4 MouseListener Removed" );
    }
    for( int i = 1; i < choice5.getMouseListeners().length; i++ ) {
      MouseListener [] m = choice5.getMouseListeners();
      choice5.removeMouseListener( m[ i ] );
      System.err.println( "Choice5 MouseListener Removed" );
    }
    for( ActionListener a: choice1.getActionListeners() ) {
      choice1.removeActionListener( a );
      System.err.println( "Choice1 ActionListener Removed" );
    }
    for( ActionListener a: choice2.getActionListeners() ) {
      choice2.removeActionListener( a );
      System.err.println( "Choice2 ActionListener Removed" );
    }
    for( ActionListener a: choice3.getActionListeners() ) {
      choice3.removeActionListener( a );
      System.err.println( "Choice3 ActionListener Removed" );
    }
    for( ActionListener a: choice4.getActionListeners() ) {
      choice4.removeActionListener( a );
      System.err.println( "Choice4 ActionListener Removed" );
    }
    for( ActionListener a: choice5.getActionListeners() ) {
      choice5.removeActionListener( a );
      System.err.println( "Choice5 ActionListener Removed" );
    }
    choice1.addMouseListener( new MenuButtonMouseAdapter() );
    choice2.addMouseListener( new MenuButtonMouseAdapter() );
    choice3.addMouseListener( new MenuButtonMouseAdapter() );
    choice4.addMouseListener( new MenuButtonMouseAdapter() );
    choice5.addMouseListener( new MenuButtonMouseAdapter() );
    choice1.setRolloverIcon( null );
    choice2.setRolloverIcon( null );
    choice3.setRolloverIcon( null );
    choice4.setRolloverIcon( null );
    choice5.setRolloverIcon( null );
    placeComponent( 383, 280, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice1, 338, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.SOUTH, choice1, 0, SpringLayout.NORTH, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice3, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice3, 0, SpringLayout.SOUTH, choice2 );
    placeComponent( 383, 260, choice4 );
    layout.putConstraint( SpringLayout.WEST, choice5, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice5, 0, SpringLayout.SOUTH, choice4 );
  }

  private ImageIcon getImageIcon( String s ) {
    return new ImageIcon( getClass().getResource( "resources/" + s ) );
  }
  private ImageIcon getImageIconCh2( String s ) {
    return getImageIcon( "ch2/" + s );
  }

  private void setButtonIcon( JButton button, String img ) {
    button.setIcon( getImageIcon( img + ".png" ) );
    button.setRolloverIcon( getImageIcon( img + " h.png" ) );
  }

  private void placeComponent( int x, int y, Component c ) {
    layout.putConstraint( SpringLayout.WEST, c, x, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, c, y, SpringLayout.NORTH, panel );
  }

  private void setButtonTransparent( JButton b ) {
    b.setOpaque( false );
    b.setContentAreaFilled( false );
    b.setBorderPainted( false );
    b.setFocusable( false );
  }

  private void setNewSong( String songName ) {
    mediaPlayer.stop();
    mediaPlayer = new MediaPlayer( new Media( getClass().getResource( "resources/" + songName + ".mp3" ).toString() ) );
    mediaPlayer.play();
    mediaPlayer.setOnEndOfMedia( new Runnable() {
      @Override
      public void run() {
        mediaPlayer.seek( Duration.ZERO );
        mediaPlayer.play();
      }
    } );
  }

  @SuppressWarnings("serial")
  private static class FadeLabel extends JLabel {

    private long runningTime = 2000;

    private BufferedImage inImage;
    private BufferedImage outImage;

    private float alpha = 0f;
    private long startTime = -1;

    private Timer timer;

    private boolean firstTime = true;

    public FadeLabel( String image1, String image2 ) {
      try {
        outImage = ImageIO.read( getClass().getResource( "resources/" + image1 ) );
        inImage = ImageIO.read( getClass().getResource( "resources/" + image2 ) );
      } catch( Exception e ) {
        e.printStackTrace();
      }

      timer = new Timer( 40, new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent e ) {
          if( startTime < 0 ) {
            startTime = System.currentTimeMillis();
          } else {

            long time = System.currentTimeMillis();
            long duration = time - startTime;
            if( duration >= runningTime ) {
              startTime = -1;
              ( ( Timer ) e.getSource() ).stop();
              alpha = 0f;
            } else {
              alpha = 1f - ( ( float ) duration / ( float ) runningTime );
            }
            repaint();
          }
        }
      } );
    }

    public FadeLabel( String image1, String image2, long runningTime ) {
      this.runningTime = runningTime;
      try {
        outImage = ImageIO.read( getClass().getResource( "resources/" + image1 ) );
        inImage = ImageIO.read( getClass().getResource( "resources/" + image2 ) );
      } catch( Exception e ) {
        e.printStackTrace();
      }

      timer = new Timer( 40, new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent e ) {
          if( startTime < 0 ) {
            startTime = System.currentTimeMillis();
          } else {

            long time = System.currentTimeMillis();
            long duration = time - startTime;
            if( duration >= runningTime ) {
              startTime = -1;
              ( ( Timer ) e.getSource() ).stop();
              fadeFinished = true;
              alpha = 0f;
            } else {
              alpha = 1f - ( ( float ) duration / ( float ) runningTime );
            }
            repaint();
          }
        }
      } );
    }

    public void fadeImages() {
      alpha = 0f;
      BufferedImage tmp = inImage;
      inImage = outImage;
      outImage = tmp;
      timer.start();
      fadeFinished = false;
    }

    @Override
    protected void paintComponent( Graphics g ) {
      super.paintComponent( g );
      Graphics2D g2d = ( Graphics2D ) g.create();
      g2d.setComposite( AlphaComposite.SrcOver.derive( alpha ) );
      int x = ( getWidth() - inImage.getWidth() ) / 2;
      int y = ( getHeight() - inImage.getHeight() ) / 2;
      if( !firstTime ) {
        g2d.drawImage( inImage, x, y, this );
      }

      g2d.setComposite( AlphaComposite.SrcOver.derive( 1f - alpha ) );
      x = ( getWidth() - outImage.getWidth() ) / 2;
      y = ( getHeight() - outImage.getHeight() ) / 2;
      if( !firstTime ) {
        g2d.drawImage( outImage, x, y, this );
      }
      firstTime = false;
      g2d.dispose();
    }
  }

  @SuppressWarnings( "serial" )
  private class SlidePuzzleGUI extends JPanel {
    private GraphicsPanel puzzleGraphics;
    private SlidePuzzleLogic pLogic = new SlidePuzzleLogic();
    private final Timer timer;
    private int timeCounter;
    private JLabel time, pic, head, name, dSet;
    private JTextArea dArea;
    private SpringLayout layout;
    private boolean paused;
  
    public SlidePuzzleGUI() {
      layout = new SpringLayout();
      puzzleGraphics = new GraphicsPanel();
      time = new JLabel( "180", SwingConstants.CENTER );
      pic = new JLabel( getImageIcon( "exodia pic.png" ) );
      head = new JLabel( getImageIcon( "Kaiba_head.png" ) );
      head.setPreferredSize( new Dimension( 150, 150 ) );
      head.setOpaque( false );
      head.setVisible( false );
      name = new JLabel( "Seto Kaiba" );
      name.setPreferredSize( new Dimension( 200, 40 ) );
      name.setFont( new Font( "Pixel-Noir", Font.BOLD, 14) );
      name.setOpaque( false );
      name.setForeground( Color.WHITE );
      name.setVisible( false );
      dSet = new JLabel( getImageIcon( "dialogue set.png" ) );
      dSet.setVisible( false );
      dArea = new JTextArea();
      dArea.setPreferredSize( new Dimension( 850, 100) );
      dArea.setForeground( new Color( 249, 253, 168 ) );
      dArea.setOpaque( false );
      dArea.setBorder( BorderFactory.createEmptyBorder( 5, 0, 0, 15 ) );
      dArea.setFont( new Font( "Pixel-Noir", Font.PLAIN, 15 ) );
      dArea.setWrapStyleWord( true );
      dArea.setLineWrap( true );
      dArea.setEditable( false );
      dArea.setFocusable( false );
      dArea.setVisible( false );
      time.setFont(new Font( "Pixel-Noir", Font.BOLD, 42 ) );
      time.setForeground( Color.RED );
      time.setBackground( Color.BLACK );
      time.setPreferredSize( new Dimension( 300, 300 ) );
      timer = new Timer( 1000, null );
      timer.addActionListener( new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent evt ) {
          time.setText( "" + ( 180 - timeCounter ) );
          if( ( 180 - timeCounter ) == 60 ) {
            timeCounter++;
            paused = true;
            animationFinished = false;
            dArea.setVisible( true );
            dSet.setVisible( true );
            name.setText( "Seto Kaiba" );
            name.setVisible( true );
            head.setIcon( getImageIcon( "Kaiba_head.png" ) );
            head.setVisible( true );
            animateText( "Your turn time is running out.\r\n" + 
            "Draw your last pathetic card, so I can end this!", 35, dArea );
            SlidePuzzleGUI.this.addMouseListener( new MouseAdapter() {
              @Override
              public void mouseClicked( MouseEvent evt ) {
                if( animationFinished ) {
                  paused = false;
                  animationFinished = false;
                  timer.start();
                  dArea.setVisible( false );
                  dArea.setText( null );
                  dSet.setVisible( false );
                  head.setVisible( false );
                  name.setVisible( false );
                  SlidePuzzleGUI.this.removeMouseListener( this );
                }
              }
            });
            puzzleGraphics.addMouseListener( new MouseAdapter() {
              @Override
              public void mouseClicked( MouseEvent evt ) {
                if( animationFinished ) {
                  paused = false;
                  animationFinished = false;
                  timer.start();
                  dArea.setVisible( false );
                  dArea.setText( null );
                  dSet.setVisible( false );
                  head.setVisible( false );
                  name.setVisible( false );
                  puzzleGraphics.removeMouseListener( this );
                }
              }
            });
            timer.stop();
          }

          if( timeCounter == 180 ) {
            timeCounter = 0;
            timer.stop();
            afterCh1Boss( false );
          }
          
          if( !paused )
            timeCounter++;
        }
      });
      this.setOpaque( true );
      this.setBackground( Color.BLACK );
      this.setLayout( layout );
      this.add( head );
      layout.putConstraint( SpringLayout.WEST, head, 185, SpringLayout.WEST, this );
      layout.putConstraint( SpringLayout.NORTH, head, 560, SpringLayout.NORTH, this );
      this.add( name );
      layout.putConstraint( SpringLayout.WEST, name, 350, SpringLayout.WEST, this );
      layout.putConstraint( SpringLayout.NORTH, name, 575, SpringLayout.NORTH, this );
      this.add( dArea );
      layout.putConstraint( SpringLayout.WEST, dArea, 348, SpringLayout.WEST, this );
      layout.putConstraint( SpringLayout.NORTH, dArea, 618, SpringLayout.NORTH, this );
      this.add( dSet );
      layout.putConstraint( SpringLayout.WEST, dSet, 183, SpringLayout.WEST, this );
      layout.putConstraint( SpringLayout.NORTH, dSet, 563, SpringLayout.NORTH, this );
      this.add( time );
      this.add( pic );
      layout.putConstraint( SpringLayout.EAST, pic, -24, SpringLayout.EAST, this );
      layout.putConstraint( SpringLayout.SOUTH, pic, -24, SpringLayout.SOUTH, this );
      this.add( puzzleGraphics );
      layout.putConstraint( SpringLayout.WEST, puzzleGraphics, 299, SpringLayout.WEST, this );
    }

    public Timer getTimer() {
      return timer;
    }
  
    class GraphicsPanel extends JPanel implements MouseListener {
      private static final int ROWS = 3;
      private static final int COLS = 3;
      private static final int CELL_SIZE = 256;
  
      public GraphicsPanel() {
        this.setPreferredSize( new Dimension( ROWS * CELL_SIZE, COLS * CELL_SIZE ) );
        this.setOpaque( true );
        this.setBackground( new Color( 0, 0, 64 ) );
        this.addMouseListener( this );
      }
  
      @Override
      protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D )g;
        for( int r = 0; r < ROWS; r++ ) {
          for( int c = 0; c < COLS; c++ ) {
            int x = c * CELL_SIZE;
            int y = r * CELL_SIZE;
            Image img = pLogic.getImage( r, c );
            if( img != null ) {
              g2d.drawImage( img, x, y, null );
            }
          }
        }
      }

      @Override
      public void mousePressed( MouseEvent evt ) {
        int row = evt.getY() / CELL_SIZE;
        int col = evt.getX() / CELL_SIZE;
  
        if( !paused ) {
          pLogic.moveTile( row, col );
          this.repaint();
          //Toolkit.getDefaultToolkit().beep();
        }

        if( pLogic.isGameOver() ) {
          timer.stop();
          afterCh1Boss( true );
        }
      }
  
      @Override
      public void mouseClicked( MouseEvent evt ) {}
      @Override
      public void mouseReleased( MouseEvent evt ) {}
      @Override
      public void mouseEntered( MouseEvent evt ) {}
      @Override
      public void mouseExited( MouseEvent evt ) {}
    }
  }

  private void removePanelMouseListener() {
    MouseListener [] arr = panel.getMouseListeners();
    panel.removeMouseListener( arr[ 0 ] );
  }

  @Override
  public void start( Stage arg0 ) throws Exception {
  }

  public static void main( String [] args ) {
    //new Game();

    SwingUtilities.invokeLater( new Runnable() {
      @Override
      public void run() {
        new Game();
      }
    });
  }
}