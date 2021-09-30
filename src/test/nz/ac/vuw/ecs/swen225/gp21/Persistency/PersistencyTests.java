package test.nz.ac.vuw.ecs.swen225.gp21.Persistency;

import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.persistency.WriteLevel;
import org.junit.Test;

import static nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel.getBoardString;

public class PersistencyTests {

    @Test
    public void loadBoard1(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                        "c c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard1(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "f c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "f");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard2(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "a c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "a");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard3(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "e c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "e");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard4(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "q c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "q");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard5(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "i c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "i");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard6(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "k c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "k");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard7(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "l c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "l");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard8(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "l c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "w");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

    @Test
    public void editBoard9(){
        WriteLevel w = new WriteLevel();
        LoadLevel l = new LoadLevel();

        String boardCorrect =
                "l c c \n"  +
                        "c c c \n"  +
                        "c c c \n";


        w.createLevel("test2.xml", "c", 3);
        WriteLevel.editCellType("test2.xml", 0, 0, "t");
        Tile[][] tiles = l.loadLevel("test2.xml");
        String board = getBoardString(tiles);

        assert(board.equals(boardCorrect));
    }

}
