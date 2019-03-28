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
  private JLabel label, mainMenu, bgLabel, headLabel, nameLabel, dialogueLabel, textLabel, versatileLabel1, versatileLabel2;
  private ImageIcon secretImage, mainScreenBG, playButtonImage, creditsButtonImage, exitButtonImage, playHoveredImage, creditsHoveredImage,
      exitHoveredImage, skipIntroButtonImage, skipIntroHoveredImage, intro1Image, companyLogo, titleScreen, intro2Image, intro3Image, intro4Image,
      intro5Image, intro6Image, intro7Image, intro8Image, intro9Image, intro10Image, intro11Image, intro12Image, intro13Image, chapter1Image,
      tutorial1Image, tutorialClosedProphecyImage, tutorialOpenProphecyImage, tutorialTransitionImage, jtHeadImage, rudyHeadImage, allanHeadImage,
      prophecyTitleImage, chapter11Image;
  private JButton play, credits, exit, secretButt, skipIntro, choice1, choice2, choice3, choice4, choice5;
  private static MediaPlayer mediaPlayer;
  private SpringLayout layout;
  private JTextArea textArea, dialogueArea, prophecyText;
  private int stringCounter, choiceNum;
  private boolean introSkipped;
  private static boolean animationFinished = false;
  private int [] stageCnt = { 0 }; // used to track the number of user clicks to know which stage to advance to and also to bypass final error
  private String curStage; // used to check current stage to accurately represent button hovering
  private int counter, ballScore;

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
    rudyHeadImage = getImageIcon( "Rudy_head.gif" );
    allanHeadImage = getImageIcon( "Allan_head.gif" );
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
    dialogueArea = new JTextArea( 2, 20 );
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
    dialogueArea.setBorder( BorderFactory.createEmptyBorder( 5, 0, 0, 10 ) );
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
    credits.addMouseListener( new MenuButtonMouseAdapter() );
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

  // checking if mouse enters the button for a hover effect
  private class MenuButtonMouseAdapter extends MouseAdapter {
    public void mouseEntered( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playHoveredImage );
      } else if( evt.getSource().equals( credits ) ) {
        credits.setIcon( creditsHoveredImage );
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
      }
    }

    // checking if mouse leaves button for unhovered effect
    public void mouseExited( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playButtonImage );
      } else if( evt.getSource().equals( credits ) ) {
        credits.setIcon( creditsButtonImage );
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
      }
    }
  }

  // mouse listener for detecting which menu button was pressed
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
        mainMenu.setIcon( null );
        mainMenu.setText( "made by sum peeps" );
        mainMenu.revalidate();
      } else if( evt.getSource().equals( exit ) ) {
        frame.dispatchEvent( new java.awt.event.WindowEvent( frame, java.awt.event.WindowEvent.WINDOW_CLOSING ) );
      } else if( evt.getSource().equals( secretButt ) ) {
        mainMenu.setIcon( secretImage );
        play.setVisible( false );
        credits.setVisible( false );
        // slider.setVisible( false );
        secretButt.setEnabled( false );
        mainMenu.revalidate();
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
    t = new Timer( t.getDelay() + 14000, new ActionListener() {
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
    nameLabel.setPreferredSize( new Dimension( 100, 40 ) );
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
        // delay necessary to prevent spam clicking
        Timer tempTimer = new Timer( 3200, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent evt ) {
            animationFinished = false;
          }
        } );
        tempTimer.setRepeats( false );
        tempTimer.start();
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
      if( stageCnt[ 0 ] == 0 && animationFinished ) {
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
            + "Many scenarios will be brought up and you are the one to decide Allan's fate by choosing amongst several options that would be provided. "
            + "\r\n(use your mouse to select each option)" );
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
        animateText( "Oh you've done it now boy. Square up fool. I'll beat you so hard it'll make your ancestors dizzy!", 35, dialogueArea );
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
        animateText( "Listen up short stuff. My court my ball. First to 11 takes game and shot counts as 1 point but 3's stay 3. And tell ya what, "
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
          if( e.getSource().equals( choice4 ) ) {
            choiceNum = 4;
            animateText( "Actually, I was looking for someone else", 35, dialogueArea );
          } else if( e.getSource().equals( choice5 ) ) {
            choiceNum = 5;
            animateText( "Bug off? How about you quit yappin' with them big lips of yours before I go sicko mode!", 35, dialogueArea );
          }
        } else if( choiceNum == 3 ) {
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
    frame.add( panel );
    bgLabel.setIcon( getImageIcon( "before ball transition.gif" ) );
    panel.add( new JLabel( getImageIcon( "ball game scoreboard.gif") ) );
    panel.add( choice1 );
    panel.add( choice2 );
    panel.add( choice3 );
    panel.add( bgLabel );
    placeComponent( 383, 280, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice1, 338, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.SOUTH, choice1, 0, SpringLayout.NORTH, choice2 );
    layout.putConstraint( SpringLayout.WEST, choice3, 428, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, choice3, 0, SpringLayout.SOUTH, choice2 );
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
    panel.revalidate();
  }

  private void displayBallGameMenu() {
    bgLabel.setIcon( getImageIcon( "ball menu.gif" ) );
    choice1.setVisible( true );
    choice2.setVisible( true );
    choice3.setVisible( true );
    panel.revalidate();
  }

  private class BallGameChoiceHandler extends MouseAdapter {
    public void mouseClicked( MouseEvent e ) {
      choice1.setVisible( false );
      choice2.setVisible( false );
      choice3.setVisible( false );
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
            int rng = ( int )( Math.random() * 3 ) + 1;
            ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
            shot.getImage().flush();
            bgLabel.setIcon( shot );
            counter++;
          }
        } );
        jtScoreTimer.setRepeats( false );
        jtScoreTimer.start();
        Timer timer = new Timer( 10000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent evt ) {
            displayBallGameMenu();
          }
        } );
        timer.setRepeats( false );
        timer.start();
      } else if( e.getSource().equals( choice2 ) ) {
        ballScore++;
        if( ( int )( Math.random() * 10 ) + 1 <= 8 ) { // 80% chance jumpshot
          int shotNumber = ( int )( Math.random() * 3 ) + 1;
          if( shotNumber == 1 || shotNumber == 3 ) {
            ImageIcon shot = getImageIcon( "allan jump shot_" + shotNumber + ".gif" );
            shot.getImage().flush();
            bgLabel.setIcon( shot );
            Timer timer = new Timer( 4000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent e ) {
                int rng = ( int )( Math.random() * 3 ) + 1;
                ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
                shot.getImage().flush();
                bgLabel.setIcon( shot );
                counter++;
              }
            } );
            timer.setRepeats( false );
            timer.start();
            Timer t = new Timer( 10000, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                displayBallGameMenu();
              }
            } );
            t.setRepeats( false );
            t.start();
          } else if( shotNumber == 2 ) {
            ImageIcon shot = getImageIcon( "allan jump shot_" + shotNumber + ".gif" );
            shot.getImage().flush();
            bgLabel.setIcon( shot );
            Timer timer = new Timer( 6500, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent e ) {
                int rng = ( int )( Math.random() * 3 ) + 1;
                ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
                shot.getImage().flush();
                bgLabel.setIcon( shot );
                counter++;
              }
            } );
            timer.setRepeats( false );
            timer.start();
            Timer t = new Timer( 12500, new ActionListener() {
              @Override
              public void actionPerformed( ActionEvent evt ) {
                displayBallGameMenu();
              }
            } );
            t.setRepeats( false );
            t.start();
          }
        } else {
          int shotNumber = ( int )( Math.random() * 2 ) + 1;
          ImageIcon shot = getImageIcon( "allan jump shot miss_" + shotNumber + ".gif" );
          shot.getImage().flush();
          bgLabel.setIcon( shot );
          Timer timer = new Timer( 4000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              int rng = ( int )( Math.random() * 3 ) + 1;
              ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
              shot.getImage().flush();
              bgLabel.setIcon( shot );
              counter++;
            }
          } );
          timer.setRepeats( false );
          timer.start();
          Timer t = new Timer( 10000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              displayBallGameMenu();
            }
          } );
          t.setRepeats( false );
          t.start();
        }
      } else if( e.getSource().equals( choice3 ) ) {
        if( ( ( int )( Math.random() * 10 ) ) + 1 <= 1 ) { // 10% chance dunk
          bgLabel.setIcon( getImageIcon( "allan dunk.gif" ) );
          Timer timer = new Timer( 6500, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              bgLabel.setIcon( getImageIcon( "ball game win.gif" ) );
            }
          } );
          timer.setRepeats( false );
          timer.start();
        } else {
          ImageIcon shot = getImageIcon( "allan dunk blocked.gif" );
          shot.getImage().flush();
          bgLabel.setIcon( shot );
          Timer timer = new Timer( 3200, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
              int rng = ( int )( Math.random() * 3 ) + 1;
              ImageIcon shot = getImageIcon( "jt score " + rng + ".gif" );
              shot.getImage().flush();
              bgLabel.setIcon( shot );
              counter++;
            }
          } );
          timer.setRepeats( false );
          timer.start();
          Timer t = new Timer( timer.getDelay() + 6000, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
              displayBallGameMenu();
            }
          });
          t.setRepeats( false );
          t.start();
        }
      }
    }
  }

  private void animateText( String text, int time ) {
    final Timer t = new Timer( time, null );
    t.addActionListener( new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        textArea.setText( text.substring( 0, stringCounter ) );
        stringCounter++;

        if( stringCounter > text.length() ) {
          t.stop();
          stringCounter = 0;
          animationFinished = true;
          return;
        }
      }
    } );
    t.start();
  }

  private void animateText( String text, int time, JTextArea area ) {
    final Timer t = new Timer( time, null );
    t.addActionListener( new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        area.setText( text.substring( 0, stringCounter ) );
        stringCounter++;

        if( stringCounter > text.length() ) {
          t.stop();
          stringCounter = 0;
          animationFinished = true;
          return;
        }
      }
    } );
    t.start();
  }

  // resets the screen to make it easier to transition between chapters
  private void resetScreen() {
    // resetting frame
    frame.remove( panel );
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
    // resetting textArea
    textArea.setText( "" );
    mListeners = textArea.getMouseListeners();
    for( int i = 0; i < mListeners.length; i++ ) {
      textArea.removeMouseListener( mListeners[ i ] );
    }
    // resetting bgLabel
    bgLabel = new JLabel();
  }

  private ImageIcon getImageIcon( String s ) {
    return new ImageIcon( getClass().getResource( "resources/" + s ) );
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
      animationFinished = false;
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

  private void removePanelMouseListener() {
    MouseListener [] arr = panel.getMouseListeners();
    panel.removeMouseListener( arr[ 0 ] );
  }

  @Override
  public void start( Stage arg0 ) throws Exception {
  }

  public static void main( String [] args ) {
    new Game();
  }
}