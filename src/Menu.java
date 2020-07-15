import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pieces.Command;
import pieces.Knight;
import pieces.Pawn;
import pieces.Rook;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.System.exit;


public class Menu extends Application {
    public int value;

    public static void main(String args[]) {
        // launch the application
        launch(args);

    }

    public void start(Stage stage) throws FileNotFoundException {
        // set title for the stage
        stage.setTitle("Battle of Empires");
        //icon image
        String pathIcon = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\pics\\icon.png";
        Image icon = new Image(pathIcon);
        //set icon
        stage.getIcons().add(icon);
        //background image
        String pathBackground = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\pics\\back.jpg";
        Image image = new Image(pathBackground);
        // nameOfGame image
        String pathName = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\pics\\name.gif";
        Image name = new Image(pathName);
        ImageView imageView = new ImageView(name);
        //state background image
        String pathStateBackground = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\pics\\state back.jpg";
        Image paper = new Image(pathStateBackground);
        // battle background
        String pathBattleBackground = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\pics\\brown.jpg";
        Image battle = new Image(pathBattleBackground);
        //menubar background
        String menubarBack = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\pics\\menubar back.jpg";
        Image menuBack = new Image(menubarBack);
        //buy piece background
        String buyBack = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\pics\\buy back.jpg";
        Image buyBarBack = new Image(buyBack);
        //MUSIC
       /* String bip ="C:\\Users\\Sazgar\\IdeaProjects\\empire\\src\\louder than bombs.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setCycleCount(3);
        mediaPlayer.play();*/
        //creat root for menu
        TilePane rootNode = new TilePane();
        rootNode.setPadding(new Insets(50, 10, 70, 170));
        rootNode.setVgap(15);
        rootNode.setAlignment(Pos.CENTER);
        // set the background image for root
        rootNode.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        // create a textfield
        TextField askBoardSize = new TextField("Enter the size of your board (13 to 23)");
        //get board size from player
        askBoardSize.setMaxWidth(275);
        askBoardSize.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        value = Integer.parseInt(askBoardSize.getText());
                        askBoardSize.setText("Your board made up of a " +
                                Integer.toString(value) + "x" + Integer.toString(value) + " Grid");
                    }
                }
        );
        //set style for textfield
        askBoardSize.setStyle(
                "-fx-background-color: #f9e8a4;" +
                        "-fx-border-color: #a5630f;");
        //create button
        Button start = new Button("Start");
        start.setStyle
                (
                        "-fx-background-color: #e2c07b;"
                                + "-fx-border-style: solid inside;"
                                + "-fx-border-width: 2;"
                                + "-fx-border-insets: 2;"
                                + "-fx-border-radius: 2;"
                                + "-fx-border-color: #a5630f;"
                );
        start.setMinWidth(100);
        //click on start button
        start.setOnAction(new EventHandler<ActionEvent>() {
            String pieceName;
            boolean myTurn = false;
            boolean enemyTurn = false;
            Random random = new Random();
            Player me;
            Player enemy;
            MenuItem coin;
            //create horizontal box for pieces permission
            HBox pieceSituation = new HBox();
            //create texts to show the permission of pieces in each turn
            Text command = new Text();
            Text rook = new Text();
            Text knight = new Text();
            Text pawn = new Text();
            String permitted = "permitted";
            String forbidden = "forbidden";

            @Override
            public void handle(ActionEvent event) {
                //hide the menu window
                stage.getScene().getWindow().hide();
                //create new node for battle window
                VBox baseRoot = new VBox();
                baseRoot.setAlignment(Pos.CENTER);
                baseRoot.setSpacing(35);
                //create horizontal box for boards
                HBox boards = new HBox();
                boards.setAlignment(Pos.CENTER);
                boards.setSpacing(20);
                //create vertical box for required options
                VBox tools = new VBox();
                tools.setAlignment(Pos.BASELINE_CENTER);
                tools.setSpacing(320);
                //set background for battle window
                baseRoot.setBackground(new Background(new BackgroundImage(battle, BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT)));
                pieceSituation.setAlignment(Pos.BOTTOM_CENTER);
                pieceSituation.setSpacing(20);
                setProperties(command);
                setProperties(rook);
                setProperties(knight);
                setProperties(pawn);
                pieceSituation.getChildren().addAll(command, rook, knight, pawn);
                // create player 1
                me = new Player(false, value, event1 -> {
                    //set the object of click mouse on the grids of board
                    Grid grid = (Grid) event1.getSource();
                    //place te pieces on the board by the name which player chose from menubar
                    if (pieceName.equalsIgnoreCase("command")) {
                        if (Command.getCountCommand() < 2) {
                            grid.board.placePiece(new Command(), grid.x, grid.y);
                        }
                    }
                    if (pieceName.equalsIgnoreCase("rook")) {
                        if (Rook.getCountRook() < 4) {
                            grid.board.placePiece(new Rook(), grid.x, grid.y);
                        }
                    }
                    if (pieceName.equalsIgnoreCase("knight")) {
                        if (Knight.getCountKnight() < 6) {
                            grid.board.placePiece(new Knight(), grid.x, grid.y);
                        }
                    }
                    if (pieceName.equalsIgnoreCase("pawn")) {
                        if (Pawn.getCountPawn() < 10) {
                            grid.board.placePiece(new Pawn(), grid.x, grid.y);
                        }
                    }
                    myTurn = true;
                });
                //create player 2
                enemy = new Player(true, value, event1 -> {
                    Grid grid = (Grid) event1.getSource();
                    //start battle
                    startGame(grid);
                });
                //place the enemy pieces
                while (Command.getCountCommand() < 1) {// based on the number of pieces which any player is allowed to place
                    int x = random.nextInt(value);
                    int y = random.nextInt(value);
                    enemy.board.placePiece(new Command(), x, y);
                }
                while ((Rook.getCountRook() < 2)) {// based on the number of pieces which any player is allowed to place
                    int x = random.nextInt(value);
                    int y = random.nextInt(value);
                    enemy.board.placePiece(new Rook(), x, y);
                }
                while (Knight.getCountKnight() < 3) {// based on the number of pieces which any player is allowed to place
                    int x = random.nextInt(value);
                    int y = random.nextInt(value);
                    enemy.board.placePiece(new Knight(), x, y);
                }
                while (Pawn.getCountPawn() < 5) {// based on the number of pieces which any player is allowed to place
                    int x = random.nextInt(value);
                    int y = random.nextInt(value);
                    enemy.board.placePiece(new Pawn(), x, y);
                }
                //create menuBar to choose the pieces
                MenuBar menubar = new MenuBar() {{
                    javafx.scene.control.Menu menu = new javafx.scene.control.Menu("Battle Pieces") {{
                        MenuItem command = new MenuItem("Command");
                        getItems().add(command);
                        command.setOnAction(e -> {
                            pieceName = "command";
                        });
                        MenuItem rook = new MenuItem("Rook");
                        getItems().add(rook);
                        rook.setOnAction(e -> {
                            pieceName = "Rook";
                        });
                        MenuItem knight = new MenuItem("Knight");
                        getItems().add(knight);
                        knight.setOnAction(e -> {
                            pieceName = "Knight";
                        });
                        MenuItem pawn = new MenuItem("Pawn");
                        getItems().add(pawn);
                        pawn.setOnAction(e -> {
                            pieceName = "Pawn";
                        });
                    }};
                    getMenus().add(menu);
                }};
                menubar.setBackground(new Background(new BackgroundImage(menuBack, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));
                //create menu bar to show the coins and buy new piece
                MenuBar buy = new MenuBar() {{
                    javafx.scene.control.Menu buy = new javafx.scene.control.Menu("Buy New Piece ") {{
                        coin = new MenuItem();
                        getItems().add(coin);
                        coin.setStyle("-fx-background-color: #a8cbec;");
                        MenuItem command = new MenuItem("Command : 50 coin");
                        command.setOnAction(e -> {
                            if (enemy.board.getCoin() >= 50 && me.board.rooks.size() > 0) {//at least one rook has to exist
                                enemy.board.collectCoin(-50);
                                me.board.placePiece(new Command(), 0, 0);
                                myTurn = false;
                                enemyTurn = true;
                            }

                        });
                        getItems().add(command);
                        MenuItem rook = new MenuItem("Rook : 20 coin");
                        rook.setOnAction(e -> {
                            if (enemy.board.getCoin() >= 20 && me.board.rooks.size() > 0) {//at least one rook has to exist
                                enemy.board.collectCoin(-20);
                                me.board.placePiece(new Rook(), 0, 0);
                                myTurn = false;
                                enemyTurn = true;
                            }
                        });
                        getItems().add(rook);
                        MenuItem knight = new MenuItem("Knight : 15 coin");
                        knight.setOnAction(e -> {
                            if (enemy.board.getCoin() >= 15 && me.board.rooks.size() > 0) {//at least one rook has to exist
                                enemy.board.collectCoin(-15);
                                me.board.placePiece(new Knight(), 0, 0);
                                myTurn = false;
                                enemyTurn = true;
                            }
                        });
                        getItems().add(knight);
                        MenuItem pawn = new MenuItem("Pawn : 8 coin");
                        pawn.setOnAction(e -> {
                            if (enemy.board.getCoin() >= 8 && me.board.rooks.size() > 0) {//at least one rook has to exist
                                enemy.board.collectCoin(-8);
                                me.board.placePiece(new Pawn(), 0, 0);
                                myTurn = false;
                                enemyTurn = true;
                            }
                        });
                        getItems().add(pawn);
                    }};
                    getMenus().add(buy);
                }};
                buy.setBackground(new Background(new BackgroundImage(buyBarBack, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));

                tools.getChildren().addAll(buy, menubar);
                //add all created elements to node
                boards.getChildren().addAll(me.board.gridPane, tools, enemy.board.gridPane);
                baseRoot.getChildren().addAll(boards, pieceSituation);
                Scene secondScene = new Scene(baseRoot, 1200, 900);
                // create New window (Stage)
                Stage playWindow = new Stage();
                playWindow.setTitle("Battle");
                playWindow.getIcons().add(icon);
                playWindow.setScene(secondScene);
                // Specifies the owner Window (parent) for new window
                playWindow.initOwner(stage);
                playWindow.show();
            }

            private void startGame(Grid grid) {
                while (myTurn) {// attack with specified piece if it is alive and permitted
                    int x = grid.x;
                    int y = grid.y;
                    if (me.board.commandRecovery == 0) {
                        if (pieceName.equalsIgnoreCase("command") && me.board.commands.size() > 0) {
                            int index = me.board.commands.size() - 1;
                            enemy.board.grids[x][y].shoot(me.board.commands.get(index), x, y);
                            me.board.commandRecovery = me.board.commands.get(index).getRecoveryTime();
                            if (me.board.rookRecovery != 0) me.board.rookRecovery--;
                            if (me.board.knightRecovery != 0) me.board.knightRecovery--;
                            if (me.board.pawnRecovery != 0) me.board.pawnRecovery--;
                            myTurn = false;
                            enemyTurn = true;
                        }
                    } else {
                        myTurn = false;
                        enemyTurn = true;
                    }
                    if (me.board.knightRecovery == 0) {
                        if (pieceName.equalsIgnoreCase("knight") && me.board.knights.size() > 0) {
                            int index = me.board.knights.size() - 1;
                            enemy.board.grids[x][y].shoot(me.board.knights.get(index), x, y);
                            me.board.knightRecovery = me.board.knights.get(index).getRecoveryTime();
                            if (me.board.rookRecovery != 0) me.board.rookRecovery--;
                            if (me.board.commandRecovery != 0) me.board.commandRecovery--;
                            if (me.board.pawnRecovery != 0) me.board.pawnRecovery--;
                            myTurn = false;
                            enemyTurn = true;
                        }
                    } else {
                        myTurn = false;
                        enemyTurn = true;
                    }
                    if (me.board.rookRecovery == 0) {
                        if (pieceName.equalsIgnoreCase("rook") && me.board.rooks.size() > 0) {
                            int index = me.board.rooks.size() - 1;
                            enemy.board.grids[x][y].shoot(me.board.rooks.get(index), x, y);
                            me.board.rookRecovery = me.board.rooks.get(index).getRecoveryTime();
                            if (me.board.knightRecovery != 0) me.board.knightRecovery--;
                            if (me.board.commandRecovery != 0) me.board.commandRecovery--;
                            if (me.board.pawnRecovery != 0) me.board.pawnRecovery--;
                            myTurn = false;
                            enemyTurn = true;
                        }
                    } else {
                        myTurn = false;
                        enemyTurn = true;
                    }
                    if (me.board.pawnRecovery == 0) {
                        if (pieceName.equalsIgnoreCase("pawn") && me.board.pawns.size() > 0) {
                            int index = me.board.pawns.size() - 1;
                            enemy.board.grids[x][y].shoot(me.board.pawns.get(index), x, y);
                            me.board.pawnRecovery = me.board.pawns.get(index).getRecoveryTime();
                            if (me.board.knightRecovery != 0) me.board.knightRecovery--;
                            if (me.board.commandRecovery != 0) me.board.commandRecovery--;
                            if (me.board.rookRecovery != 0) me.board.rookRecovery--;
                            myTurn = false;
                            enemyTurn = true;
                        }
                    } else {
                        myTurn = false;
                        enemyTurn = true;
                    }
                    //show the permission of pieces
                    if (me.board.commandRecovery == 0) {
                        command.setText("command \n\n" + permitted);
                        command.setFill(Color.LINEN);
                    } else {
                        command.setText("command \n\n" + forbidden);
                        command.setFill(Color.RED);
                    }
                    if (me.board.rookRecovery == 0) {
                        rook.setText("rook \n\n" + permitted);
                        rook.setFill(Color.LINEN);
                    } else {
                        rook.setText("rook \n\n" + forbidden);
                        rook.setFill(Color.RED);
                    }
                    if (me.board.knightRecovery == 0) {
                        knight.setText("knight \n\n" + permitted);
                        knight.setFill(Color.LINEN);
                    } else {
                        knight.setText("knight \n\n" + forbidden);
                        knight.setFill(Color.RED);
                    }
                    if (me.board.pawnRecovery == 0) {
                        pawn.setText("pawn \n\n" + permitted);
                        pawn.setFill(Color.LINEN);
                    } else {
                        pawn.setText("pawn \n\n" + forbidden);
                        pawn.setFill(Color.RED);
                    }

                    if (me.board.pieceNumber == 0) {
                        System.out.println("You loose");
                        exit(0);
                    }
                    if (me.board.knightRecovery != 0 && me.board.commandRecovery != 0 && me.board.rookRecovery != 0 && me.board.pawnRecovery != 0) {
                        me.board.knightRecovery--;
                        me.board.commandRecovery--;
                        me.board.rookRecovery--;
                        me.board.pawnRecovery--;
                        enemyTurn = true;
                        myTurn = false;
                    }
                    //show collected coins in menubar
                    coin.setText("COLLECTED COINS : " + String.valueOf(enemy.board.getCoin()));
                }
                while (enemyTurn) {// attack with specified piece if it is alive and permitted
                    //generate random places and pieces to attack
                    int randomX = random.nextInt(value - 1);
                    int randomY = random.nextInt(value - 1);
                    int buy = random.nextInt(value);
                    int dice = random.nextInt(3) + 1;
                    //buy new piece by computer
                    if (buy == 5 && me.board.getCoin() >= 50) enemy.board.placePiece(new Command(), randomX, randomY);
                    if (buy == 7 && me.board.getCoin() >= 20) enemy.board.placePiece(new Rook(), randomX, randomY);
                    if (buy == 9 && me.board.getCoin() >= 15) enemy.board.placePiece(new Knight(), randomX, randomY);
                    if (buy == 11 && me.board.getCoin() >= 8) enemy.board.placePiece(new Pawn(), randomX, randomY);

                    if (dice == 1) {
                        //if(enemy.board.pawnRecovery==0) {
                        if (enemy.board.pawns.size() > 0) {
                            int index = enemy.board.pawns.size() - 1;
                            while (me.board.grids[randomX][randomY].wasShot) {
                                randomX = random.nextInt(value);
                                randomY = random.nextInt(value);
                            }
                            me.board.grids[randomX][randomY].shoot(enemy.board.pawns.get(index), randomX, randomY);
                            enemy.board.pawnRecovery = enemy.board.pawns.get(index).getRecoveryTime();
                            if (enemy.board.knightRecovery != 0) enemy.board.knightRecovery--;
                            if (enemy.board.commandRecovery != 0) enemy.board.commandRecovery--;
                            if (enemy.board.rookRecovery != 0) enemy.board.rookRecovery--;
                            enemyTurn = false;
                            myTurn = true;
                        }
                           /* }
                            else {
                                enemyTurn = true;
                                myTurn = false;
                            }*/
                    }
                    if (dice == 3) {
                        if (enemy.board.knightRecovery == 0) {
                            if (enemy.board.knights.size() > 0) {
                                int index = enemy.board.knights.size() - 1;
                                randomX = random.nextInt(value - 3);
                                randomY = random.nextInt(value - 2);
                                while (me.board.grids[randomX][randomY].wasShot) {
                                    randomX = random.nextInt(value - 3);
                                    randomY = random.nextInt(value - 2);
                                }
                                me.board.grids[randomX][randomY].shoot(enemy.board.knights.get(index), randomX, randomY);
                                enemy.board.knightRecovery = enemy.board.knights.get(index).getRecoveryTime();
                                if (enemy.board.pawnRecovery != 0) enemy.board.pawnRecovery--;
                                if (enemy.board.commandRecovery != 0) enemy.board.commandRecovery--;
                                if (enemy.board.rookRecovery != 0) enemy.board.rookRecovery--;
                                enemyTurn = false;
                                myTurn = true;
                            }
                        } else {
                            enemyTurn = true;
                            myTurn = false;
                        }
                    }
                    if (dice == 2) {
                        if (enemy.board.rookRecovery == 0) {
                            if (enemy.board.rooks.size() > 0) {
                                int index = enemy.board.rooks.size() - 1;
                                randomX = random.nextInt(value - 3);
                                randomY = random.nextInt(value - 5);
                                while (me.board.grids[randomX][randomY].wasShot) {
                                    randomX = random.nextInt(value - 3);
                                    randomY = random.nextInt(value - 5);
                                }
                                me.board.grids[randomX][randomY].shoot(enemy.board.rooks.get(index), randomX, randomY);
                                enemy.board.rookRecovery = enemy.board.rooks.get(index).getRecoveryTime();
                                if (enemy.board.pawnRecovery != 0) enemy.board.pawnRecovery--;
                                if (enemy.board.commandRecovery != 0) enemy.board.commandRecovery--;
                                if (enemy.board.knightRecovery != 0) enemy.board.knightRecovery--;
                                enemyTurn = false;
                                myTurn = true;
                            }
                        } else {
                            enemyTurn = true;
                            myTurn = false;
                        }
                    }
                    if (dice == 4) {
                        if (enemy.board.commandRecovery == 0) {
                            if (enemy.board.commands.size() > 0) {
                                int index = enemy.board.commands.size() - 1;
                                randomX = random.nextInt(value - 5);
                                randomY = random.nextInt(value - 5);
                                while (me.board.grids[randomX][randomY].wasShot) {
                                    randomX = random.nextInt(value - 5);
                                    randomY = random.nextInt(value - 5);
                                }
                                me.board.grids[randomX][randomY].shoot(enemy.board.commands.get(index), randomX, randomY);
                                enemy.board.commandRecovery = enemy.board.commands.get(index).getRecoveryTime();
                                if (enemy.board.pawnRecovery != 0) enemy.board.pawnRecovery--;
                                if (enemy.board.rookRecovery != 0) enemy.board.rookRecovery--;
                                if (enemy.board.knightRecovery != 0) enemy.board.knightRecovery--;
                                enemyTurn = false;
                                myTurn = true;
                            }
                        } else {
                            enemyTurn = true;
                            myTurn = false;
                        }
                    }
                    if (enemy.board.knightRecovery != 0 && enemy.board.commandRecovery != 0 && enemy.board.rookRecovery != 0 && enemy.board.pawnRecovery != 0) {
                        enemy.board.knightRecovery--;
                        enemy.board.commandRecovery--;
                        enemy.board.rookRecovery--;
                        enemy.board.pawnRecovery--;
                        enemyTurn = false;
                        myTurn = true;
                    }
                    if (enemy.board.pieceNumber == 0) {
                        System.out.println("You win");
                        exit(0);
                    }
                }
            }

            private void setProperties(Text pieceName) {
                pieceName.setTextAlignment(TextAlignment.CENTER);
                pieceName.setFont(Font.font("Tahoma", FontWeight.BOLD, 17));
            }
        });
        // create button
        Button back = new Button("Back");
        back.setStyle
                (
                        "-fx-background-color: #e2c07b;"
                                + "-fx-border-style: solid inside;"
                                + "-fx-border-width: 2;"
                                + "-fx-border-insets: 2;"
                                + "-fx-border-radius: 2;"
                                + "-fx-border-color: #a5630f;"
                );
        back.setMinWidth(100);
        //create button
        Button state = new Button("State");
        state.setStyle
                (
                        "-fx-background-color: #e2c07b;"
                                + "-fx-border-style: solid inside;"
                                + "-fx-border-width: 2;"
                                + "-fx-border-insets: 2;"
                                + "-fx-border-radius: 2;"
                                + "-fx-border-color: #a5630f;"
                );
        state.setMinWidth(100);
        //set text of state
        HBox box = new HBox();
        box.setPadding(new Insets(150, 0, 0, 0));
        Text text = new Text();
        text.setText("This game is played with 2 players.The one is you and another one is computer.\n" +
                "Each player has one board.The size of boards is made up of the number that you entered at the menu page.\n" +
                "On the left board, you arrange your “BattlePieces” and your board shows the shots by the opponent. \n" +
                "You  can choose your piece by clicking on battlePieces menubar at the center of battle page. \n" +
                "Each piece has a recovery time. after using a specified piece, it becomes forbidden for the next attacks.\n" +
                "you have to use other permitted pieces until that piece became permitted for other turns.\n" +
                "the situation of pieces shows at the bottom of the battle page.\n" +
                "The number of squares for each “BattlePiece” is determined by the type of the “BattlePiece”\n" +
                "which is declared below:\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" +
                "\n" + "\n" + "\n" + "\n" + "\n"
        );
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Courier", FontWeight.BOLD, 16));
        text.setFill(Color.SADDLEBROWN);
        box.getChildren().addAll(text);
        //action of state button
        state.setOnAction(e -> {
            //clear menu to creat the state
            rootNode.getChildren().removeAll(imageView, state, start, askBoardSize);
            rootNode.setPadding(new Insets(158, 0, 0, 0));
            rootNode.setVgap(-65);
            rootNode.setBackground(new Background(new BackgroundImage(paper, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));
            rootNode.getChildren().addAll(box, back);
            back.setOnAction(event -> {
                //back menu to former position
                rootNode.getChildren().addAll(imageView, state, start, askBoardSize);
                rootNode.setPadding(new Insets(50, 10, 70, 170));
                rootNode.setVgap(15);
                rootNode.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));
                rootNode.getChildren().removeAll(box, back);
            });
        });
        rootNode.getChildren().addAll(imageView, state, start, askBoardSize);
        // create a scene
        Scene sc = new Scene(rootNode, 805, 547);
        stage.setScene(sc);
        stage.show();
        stage.setResizable(false);
    }
}