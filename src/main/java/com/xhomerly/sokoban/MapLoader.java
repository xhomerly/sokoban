package com.xhomerly.sokoban;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    static Player player;
    static List<Crate> crates = new ArrayList<>();
    static List<Wall> walls = new ArrayList<>();
    static GridPane gridPane = new GridPane();
    static int winX, winY;
    static StackPane root = new StackPane();

    public static StackPane loadMap(String filePath) {
        try {
            gridPane = new GridPane(); // Reset grid pane
            root.getChildren().clear(); // Clear previous content
            root.getChildren().add(gridPane); // Add game grid

            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            Element mapElement = document.getDocumentElement();
            int width = Integer.parseInt(mapElement.getAttribute("width"));
            int height = Integer.parseInt(mapElement.getAttribute("height"));

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Ground ground = new Ground();
                    gridPane.add(ground, x, y);
                }
            }

            NodeList tiles = document.getElementsByTagName("tile");

            for (int i = 0; i < tiles.getLength(); i++) {
                Node tileNode = tiles.item(i);
                if (tileNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tile = (Element) tileNode;

                    int x = Integer.parseInt(tile.getAttribute("x"));
                    int y = Integer.parseInt(tile.getAttribute("y"));
                    String type = tile.getAttribute("type");

                    boolean isPlayer = tile.hasAttribute("player_starting_position") &&
                            tile.getAttribute("player_starting_position").equalsIgnoreCase("True");
                    boolean isCrate = tile.hasAttribute("crate_starting_position") &&
                            tile.getAttribute("crate_starting_position").equalsIgnoreCase("True");

                    Cell cell = createCell(type);
                    gridPane.add(cell, x, y);

                    if (isPlayer) {
                        player = new Player(x, y);
                        drawPlayer(player, true, gridPane);
                    }
                    if (isCrate) {
                        Crate crate = new Crate(x, y);
                        crates.add(crate);
                        drawCrate(crate, true, gridPane);
                    }
                    if (type.equals("delivery_point")) { //TODO: vice delivery_pointu vyresit, do Cell pridat x,y?
                        winX = x;
                        winY = y;
                    }
                    if (type.equals("wall")) {
                        Wall wall = new Wall(x, y);
                        walls.add(wall);
                        gridPane.add(wall, x, y);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    private static Cell createCell(String type) {
        return switch (type) {
            case "delivery_point" -> new DeliveryPoint();
            default -> new EmptyCell();
        };
    }

    private static void drawPlayer(Player player, boolean playerStartingPosition, GridPane gridPane) {
        if (playerStartingPosition) {
            gridPane.add(player, Player.getX(), Player.getY());
        } else {
            gridPane.getChildren().remove(player);
            gridPane.add(player, Player.getX(), Player.getY());
        }
    }

    public static void movePlayer(String action) {
        switch (action) {
            case "W" -> {
                Crate crateToMove = null;
                for (Crate crate : crates) {
                    if (crate.getX() == Player.getX() && crate.getY() == Player.getY()-1) {
                        crateToMove = crate;
                        break;
                    }
                }

                if (crateToMove != null) {
                    boolean canMove = isCellEmpty(crateToMove.getX(), crateToMove.getY()-1);

                    if (canMove) {
                        crateToMove.setY(crateToMove.getY()-1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setY(Player.getY()-1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    boolean canMove = isCellEmpty(Player.getX(), Player.getY()-1);

                    if (canMove) {
                        Player.setY(Player.getY()-1);
                        drawPlayer(player, false, gridPane);
                    }
                }
            }
            case "S" -> {
                Crate crateToMove = null;
                for (Crate crate : crates) {
                    if (crate.getX() == Player.getX() && crate.getY() == Player.getY()+1) {
                        crateToMove = crate;
                        break;
                    }
                }

                if (crateToMove != null) {
                    boolean canMove = isCellEmpty(crateToMove.getX(), crateToMove.getY()+1);

                    if (canMove) {
                        crateToMove.setY(crateToMove.getY()+1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setY(Player.getY()+1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    boolean canMove = isCellEmpty(Player.getX(), Player.getY()+1);

                    if (canMove) {
                        Player.setY(Player.getY()+1);
                        drawPlayer(player, false, gridPane);
                    }
                }
            }
            case "A" -> {
                Crate crateToMove = null;
                for (Crate crate : crates) {
                    if (crate.getX() == Player.getX()-1 && crate.getY() == Player.getY()) {
                        crateToMove = crate;
                        break;
                    }
                }

                if (crateToMove != null) {
                    boolean canMove = isCellEmpty(crateToMove.getX()-1, crateToMove.getY());

                    if (canMove) {
                        crateToMove.setX(crateToMove.getX()-1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setX(Player.getX()-1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    boolean canMove = isCellEmpty(Player.getX()-1, Player.getY());

                    if (canMove) {
                        Player.setX(Player.getX()-1);
                        drawPlayer(player, false, gridPane);
                    }
                }
            }
            case "D" -> {
                Crate crateToMove = null;
                for (Crate crate : crates) {
                    if (crate.getX() == Player.getX()+1 && crate.getY() == Player.getY()) {
                        crateToMove = crate;
                        break;
                    }
                }

                if (crateToMove != null) {
                    boolean canMove = isCellEmpty(crateToMove.getX()+1, crateToMove.getY());

                    if (canMove) {
                        crateToMove.setX(crateToMove.getX()+1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setX(Player.getX()+1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    boolean canMove = isCellEmpty(Player.getX()+1, Player.getY());

                    if (canMove) {
                        Player.setX(Player.getX()+1);
                        drawPlayer(player, false, gridPane);
                    }
                }
            }
        }
    }

    private static void drawCrate(Crate crate, boolean crateStartingPosition, GridPane gridPane) {
        if (crateStartingPosition) {
            gridPane.add(crate, crate.getX(), crate.getY());
        } else {
            gridPane.getChildren().remove(crate);
            gridPane.add(crate, crate.getX(), crate.getY());

            if (winX == crate.getX() && winY == crate.getY()) {
                System.out.println("Game won!");
                Label label = new Label("Game won!");
                label.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: white;");

                StackPane wonOverlay = new StackPane();
                wonOverlay.getChildren().add(label);
                wonOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5)");
                root.getChildren().add(wonOverlay);

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), _ -> Application.loadMenu()));
                timeline.setCycleCount(1);
                timeline.play();
            }
        }
    }

    private static boolean isCellEmpty(int x, int y) {
        for (Crate crate : crates) {
            if (x == crate.getX() && y == crate.getY()) {
                return false;
            }
            for (Wall wall : walls) {
                if (x == wall.getX() && y == wall.getY()) {
                    return false;
                }
            }
        }
        return true;
    }
}
