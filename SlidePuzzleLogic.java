import java.awt.Image;
import javax.swing.ImageIcon;

public class SlidePuzzleLogic {
  private static final int ROWS = 3;
  private static final int COLS = 3;
  private Tile[][] contents;
  private Tile emptyTile;

  public SlidePuzzleLogic() {
    contents = new Tile[ ROWS ][ COLS ];
    reset();
  }

  Image getImage( int r, int c ) {
    return contents[ r ][ c ].getImage();
  }

  public void reset() {
    for( int r = 0; r < ROWS; r++ ) {
      for( int c = 0; c < COLS; c++ ) {
        //System.err.println( "exodia " + (r * COLS + c + 1) + ".png" );
        contents[ r ][ c ] = new Tile( r, c, "exodia " + ( r * COLS + c + 1 ) + ".png" );
      }
    }
    emptyTile = contents[ 1 ][ 1 ];
    emptyTile.setImg( null );

    for( int r = 0; r < ROWS; r++ ) {
      for( int c = 0; c < COLS; c++ ) {
        exchangeTiles( r, c, ( int )( Math.random() * ROWS ), ( int )( Math.random() * COLS ) );
      }
    }

    if( !isSolvable() ) {
      reset();
    }
  }

  private boolean isSolvable() {
    int inversions = 0;
    int [] contentsNum = new int[ 9 ];
    int counter = 0;

    for( int r = 0; r < ROWS; r++ ) {
      for( int c = 0; c < COLS; c++ ) {
        contentsNum[ counter ] = contents[ r ][ c ].getNum();
        counter++;
      }
    }

    for( int i = 0; i < contentsNum.length - 1; i++ ) {
      for( int j = i + 1; j < contentsNum.length; j++ ) {
        if( contentsNum[ i ] > 0 && contentsNum[ j ] > 0 && contentsNum[ i ] > contentsNum[ j ] ) {
          inversions++;
        }
      }
    }

    return inversions % 2 == 0;
  }

  public boolean moveTile( int r, int c ) {
    return checkEmpty( r, c, -1, 0 ) || checkEmpty( r, c, 1, 0 ) || checkEmpty( r, c, 0, -1 ) || checkEmpty( r, c, 0, 1 );
  }

  private boolean checkEmpty( int r, int c, int rDelta, int cDelta ) {
    int rNeighbor = r + rDelta;
    int cNeighbor = c + cDelta;

    if( isLegal( rNeighbor, cNeighbor ) && contents[ rNeighbor ][ cNeighbor ] == emptyTile ) {
      exchangeTiles( r, c, rNeighbor, cNeighbor );
      return true;
    }
    return false;
  }

  public boolean isLegal( int r, int c ) {
    return r >= 0 && r < ROWS && c >= 0 && c < COLS;
  }

  public void exchangeTiles( int r1, int c1, int r2, int c2 ) {
    Tile temp = contents[ r1 ][ c1 ];
    contents[ r1 ][ c1 ] = contents[ r2 ][ c2 ];
    contents[ r2 ][ c2 ] = temp;
  }

  public boolean isGameOver() {
    for( int r = 0; r < ROWS; r++ ) {
      for( int c = 0; c < COLS; c++ ) {
        Tile t = contents[ r ][ c ];
        if( !t.isFinal( r, c) ) {
          return false;
        }
      }
    }
    return true;
  }

  class Tile {
    private int finalRow;
    private int finalCol;
    private int num;
    private Image img;

    public Tile( int r, int c, String imgName ) {
      finalRow = r;
      finalCol = c;
      //img = new ImageIcon( "src/resources/" + imgName ).getImage();
      if( imgName != null ) {
        img = new ImageIcon( getClass().getResource( "resources/" + imgName ) ).getImage();
        num = Integer.parseInt( "" + imgName.charAt( 7 ) );
      } else {
        img = null;
        num = 0;
      }
    }

    public void setImg( String imgName ) {
      if( imgName == null ) {
        img = null;
        num = 0;
        return;
      }
      img = new ImageIcon( getClass().getResource( "resources/" + imgName ) ).getImage();
      //img = new ImageIcon( "src/resources/" + imgName ).getImage();
    }

    public Image getImage() {
      return img;
    }

    public int getNum() {
      return num;
    }

    public boolean isFinal( int r, int c ) {
      return finalRow == r && finalCol == c;
    }
  }
}