import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
  private JLabel label, mainMenu;
  private ImageIcon secretImage, mainScreenBG, playButtonImage, creditsButtonImage, exitButtonImage, playHoveredImage, creditsHoveredImage, exitHoveredImage, intro1Image, companyLogo, titleScreen, 
  intro2Image, intro3Image, intro4Image, intro5Image, intro6Image, intro7Image, intro8Image, intro9Image, intro10Image, intro11Image, intro12Image, intro13Image;
  private JButton play, credits, exit, secretButt;
  private static MediaPlayer mediaPlayer;
  private SpringLayout layout;
  private JTextArea textArea;
  private int stringCounter = 0;
  
  //private JSlider slider;
  
  public Game() {    
    frame = new JFrame();
    panel = new JPanel();
    secretImage = new ImageIcon( getClass().getResource( "resources/Secret_Image.png" ) );
    mainScreenBG = new ImageIcon( getClass().getResource( "resources/Main_Screen_Image.gif" ) );
    playButtonImage = new ImageIcon( getClass().getResource( "resources/play button.png"  ) );
    creditsButtonImage = new ImageIcon( getClass().getResource( "/resources/credits button.png" ) );
    exitButtonImage = new ImageIcon( getClass().getResource( "resources/exit button.png" ) );
    playHoveredImage = new ImageIcon( getClass().getResource( "resources/Play Button Hovered.png" ) );
    creditsHoveredImage = new ImageIcon( getClass().getResource( "resources/Credits Button Hovered.png" ) );
    exitHoveredImage = new ImageIcon( getClass().getResource( "resources/Exit Button Hovered.png" ) );
    intro1Image = new ImageIcon( getClass().getResource( "resources/1_intro.gif" ) );
    intro2Image = new ImageIcon( getClass().getResource( "resources/2_intro.gif"  ) );
    intro3Image = new ImageIcon( getClass().getResource( "resources/3_intro.gif"  ) );
    intro4Image = new ImageIcon( getClass().getResource( "resources/4_intro.gif"  ) );
    intro5Image = new ImageIcon( getClass().getResource( "resources/5_intro.gif"  ) );
    intro6Image = new ImageIcon( getClass().getResource( "resources/6_intro.gif"  ) );
    intro7Image = new ImageIcon( getClass().getResource( "resources/7_intro.gif"  ) );
    intro8Image = new ImageIcon( getClass().getResource( "resources/8_intro.gif"  ) );
    intro9Image = new ImageIcon( getClass().getResource( "resources/9_intro.gif"  ) );
    intro10Image = new ImageIcon( getClass().getResource( "resources/10_intro.gif"  ) );
    intro11Image = new ImageIcon( getClass().getResource( "resources/11_intro.gif"  ) );
    intro12Image = new ImageIcon( getClass().getResource( "resources/12_intro.gif"  ) );
    intro13Image = new ImageIcon( getClass().getResource( "resources/13_intro.gif"  ) );
    companyLogo = new ImageIcon( getClass().getResource( "resources/0_company.gif" ) );
    titleScreen = new ImageIcon( getClass().getResource( "resources/0.5_title.gif" ) );
    play = new JButton( playButtonImage );
    credits = new JButton( creditsButtonImage );
    exit = new JButton( exitButtonImage );
    secretButt = new JButton();
    mainMenu = new JLabel( mainScreenBG );
    label = new FadeLabel( "Main_Screen_Image.gif", "0_company.gif" );
    textArea = new JTextArea( 2, 20 );
    setButtonTransparent( play );
    setButtonTransparent( credits );
    setButtonTransparent( exit );
    setButtonTransparent( secretButt );
    mainMenu.setLayout( new GridBagLayout() );
    layout = new SpringLayout();
    panel.setLayout( layout );
    textArea.setPreferredSize( new Dimension( 1000, 100 ) );
    textArea.setBackground( Color.WHITE );
    textArea.setOpaque( true );
    textArea.setBorder( BorderFactory.createLineBorder( Color.BLACK, 5 ) );
    textArea.setWrapStyleWord( true );
    textArea.setLineWrap( true );
    textArea.setEditable( false );
    textArea.setFocusable( false );
    
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
      slider = new JSlider( 0, 100, 100 );
      c.gridy = 4;
      mainMenu.add( slider, c );
      slider.addChangeListener( new javax.swing.event.ChangeListener() {
        @Override
        public void stateChanged( javax.swing.event.ChangeEvent evt ) {
          if( evt.getSource().equals( slider ) ) {
            mediaPlayer.setVolume( 0.01 * slider.getValue() );
          }
        }
      });
      */
    } catch( Exception e ) {
      e.printStackTrace();
    }
    
    label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    panel.add( textArea );
    textArea.setVisible( false );
    panel.add( mainMenu );
    panel.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    play.addMouseListener( new MenuButtonMouseAdapter() );
    credits.addMouseListener( new MenuButtonMouseAdapter() );
    exit.addMouseListener( new MenuButtonMouseAdapter() );
    play.addActionListener( new MenuButtonActionListener() );
    credits.addActionListener( new MenuButtonActionListener() );
    exit.addActionListener( new MenuButtonActionListener() );
    secretButt.addActionListener( new MenuButtonActionListener() );
    
    frame.add( panel );
    frame.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
    frame.pack();
    frame.setLocationRelativeTo( null );
    frame.setResizable( false );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setTitle( "A N A" );
    frame.setVisible( true );
  }
  
  private class MenuButtonMouseAdapter extends MouseAdapter {
    public void mouseEntered( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playHoveredImage );
      } else if( evt.getSource().equals( credits ) ) {
        credits.setIcon( creditsHoveredImage );
      } else if( evt.getSource().equals( exit ) ) {
        exit.setIcon( exitHoveredImage );
      }
    }
    
    public void mouseExited( MouseEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setIcon( playButtonImage );
      } else if( evt.getSource().equals( credits ) ) {
        credits.setIcon( creditsButtonImage );
      } else if( evt.getSource().equals( exit ) ) {
        exit.setIcon( exitButtonImage );
        }
      }
    }
  
  private class MenuButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed( ActionEvent evt ) {
      if( evt.getSource().equals( play ) ) {
        play.setVisible( false );
        credits.setVisible( false );
        exit.setVisible( false );
        //slider.setVisible( false );
        setNewSong( "Psycho_Song" );
        panel.remove( mainMenu );
        panel.add( label );
        ( ( FadeLabel )label ).fadeImages();
        Timer timer = new Timer( 2100, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( label );
            panel.add( new JLabel( companyLogo ) );
            panel.revalidate();
          }
        });
        timer.setRepeats(false);
        timer.start();
        timer = new Timer( 12000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( panel.getComponent( 1 ) );
            panel.add( new JLabel( titleScreen ) );
            panel.revalidate();
            label = new FadeLabel( "0.5_title.gif", "1_intro.gif", 1000 );
            label.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
          }
        });
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 14000, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            panel.remove( panel.getComponent( 1 ) );
            panel.add( label );
            panel.revalidate();
            ( ( FadeLabel )label ).fadeImages();
          }
        });
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 2100, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            for( int i = 1; i < panel.getComponentCount(); i++ ) {
              panel.remove( panel.getComponent( i ) );
            }
            panel.add( new JLabel( intro1Image ) );
            panel.revalidate();
          }
        });
        timer.setRepeats( false );
        timer.start();
        timer = new Timer( timer.getDelay() + 500, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent arg0 ) {
            startIntro();
          }
        });
        timer.setRepeats( false );
        timer.start();
      } else if( evt.getSource().equals( credits ) ) {
        mainMenu.setIcon( null );
        mainMenu.setText( "made by sum peeps" );
        mainMenu.revalidate();
      } else if( evt.getSource().equals(  exit ) ) {
        frame.dispatchEvent( new java.awt.event.WindowEvent( frame, java.awt.event.WindowEvent.WINDOW_CLOSING ) );
      } else if( evt.getSource().equals( secretButt ) ) {
        mainMenu.setIcon( secretImage );
        play.setVisible( false );
        credits.setVisible( false );
        //slider.setVisible( false );
        secretButt.setEnabled( false );
        mainMenu.revalidate();
      }
    }
  }
  
  private void startIntro() {
    layout.putConstraint( SpringLayout.WEST, textArea, 183, SpringLayout.WEST, panel );
    layout.putConstraint( SpringLayout.NORTH, textArea, 618, SpringLayout.NORTH, panel );
    textArea.setVisible( true );
    animateText( " This is Allan. He works as a professional League of Legends player for the Shanghai Sharks. "
        + "Recently his life      has been getting quite boring, and he’s wondering how his childhood friends are doing.", 15 );
    textArea.setFont( new Font( "Courier", Font.PLAIN, 20 ) );
    panel.revalidate();
    Timer t = new Timer( 5750, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        textArea.setText( "" );
        animateText( " After dealing with the mental anxiety that comes with being a professional gamer, coupled with the low quality"
            + "      internet spewing out of his busted router, Allan has decided to end it all… ", 15 );
      }
    });
    t.setRepeats( false );
    t.start();
    
    t = new Timer( t.getDelay() + 5750, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        textArea.setText( "" );
        animateText( " ...so he quit his profession and decided to venture out into the great beyond.", 15 );
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 3500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        textArea.setText( "" );
        textArea.setVisible( false );
        panel.add( new JLabel( intro2Image ) );
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 3500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        panel.add( new JLabel( intro3Image ) );
        panel.revalidate();
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 11500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        panel.add( new JLabel( intro4Image ) );
        panel.revalidate();
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 3500, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        panel.add( new JLabel( intro5Image ) );
        panel.revalidate();
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        panel.add( new JLabel( intro6Image ) );
        textArea.setVisible( true );
        panel.revalidate();
        animateText( " Allan wonders why none of his friends have ever kept in touch with him as of recently. He texts the boys "
            + "back at   Irvine with no avail. So that was it, his first destination to reunite with the squad.", 15 );
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 7000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        panel.add( new JLabel( intro7Image ) );
        textArea.setVisible( false );
        textArea.setText( "" );
        panel.revalidate();
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 3000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        panel.add( new JLabel( intro8Image ) );
        panel.revalidate();
      }
    });
    t.setRepeats( false );
    t.start();
    t = new Timer( t.getDelay() + 13000, new ActionListener() {
      @Override
      public void actionPerformed( ActionEvent evt ) {
        panel.remove( panel.getComponent( 1 ) );
        panel.add( new JLabel( intro9Image ) );
        panel.revalidate();
      }
    });
    t.setRepeats( false );
    t.start();
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
          return;
        }
      }
    });
    t.start();
  }
  
  private void setButtonTransparent( JButton b ) {
    b.setOpaque( false );
    b.setContentAreaFilled( false );
    b.setBorderPainted( false );
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
    });
  }
  
  @SuppressWarnings("serial")
  private static class FadeLabel extends JLabel {

    private long runningTime = 2000;

    private BufferedImage inImage;
    private BufferedImage outImage;

    private float alpha = 0f;
    private long startTime = -1;

    private Timer timer;
    
    public FadeLabel( String image1, String image2 ) {
        try {
            outImage = ImageIO.read( getClass().getResource( "resources/" + image1 ) );
            inImage = ImageIO.read( getClass().getResource( "resources/" + image2) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        timer = new Timer( 40, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                if ( startTime < 0 ) {
                    startTime = System.currentTimeMillis();
                } else {

                    long time = System.currentTimeMillis();
                    long duration = time - startTime;
                    if( duration >= runningTime ) {
                        startTime = -1;
                        ( ( Timer )e.getSource() ).stop();
                        alpha = 0f;
                    } else {
                        alpha = 1f - ( ( float )duration / ( float )runningTime );
                    }
                    repaint();
                }
            }
        });
    }
    
    public FadeLabel( String image1, String image2, long runningTime ) {
      this.runningTime = runningTime;
      try {
          outImage = ImageIO.read( getClass().getResource( "resources/" + image1 ) );
          inImage = ImageIO.read( getClass().getResource( "resources/" + image2) );
      } catch ( Exception e ) {
          e.printStackTrace();
      }

      timer = new Timer( 40, new ActionListener() {
          @Override
          public void actionPerformed( ActionEvent e ) {
              if ( startTime < 0 ) {
                  startTime = System.currentTimeMillis();
              } else {

                  long time = System.currentTimeMillis();
                  long duration = time - startTime;
                  if( duration >= runningTime ) {
                      startTime = -1;
                      ( ( Timer )e.getSource() ).stop();
                      alpha = 0f;
                  } else {
                      alpha = 1f - ( ( float )duration / ( float )runningTime );
                  }
                  repaint();
              }
          }
      });
  }
    
    public void fadeImages() {
      alpha = 0f;
      BufferedImage tmp = inImage;
      inImage = outImage;
      outImage = tmp;
      timer.start();
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D )g.create();
        g2d.setComposite( AlphaComposite.SrcOver.derive( alpha ) );
        int x = ( getWidth() - inImage.getWidth() ) / 2;
        int y = ( getHeight() - inImage.getHeight() ) / 2;
        g2d.drawImage( inImage, x, y, this );

        g2d.setComposite( AlphaComposite.SrcOver.derive( 1f - alpha ) );
        x = ( getWidth() - outImage.getWidth() ) / 2;
        y = ( getHeight() - outImage.getHeight() ) / 2;
        g2d.drawImage( outImage, x, y, this );
        g2d.dispose();
    }
  }
  
  @Override
  public void start( Stage arg0 ) throws Exception { }
  
  public static void main( String [] args ) {
    new Game();
  }
}