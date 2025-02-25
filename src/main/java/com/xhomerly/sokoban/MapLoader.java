package com.xhomerly.sokoban;

import javafx.scene.layout.GridPane;
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
    static GridPane gridPane = new GridPane();
    static int winX, winY;

    public static GridPane loadMap(String filePath) {
        try {
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
                    if (type.equals("delivery_point")) {
                        winX = x;
                        winY = y;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gridPane;
    }

    private static Cell createCell(String type) {
        return switch (type) {
            case "wall" -> new Wall();
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
                    boolean canMoveCrate = isCellEmpty(crateToMove.getX(), crateToMove.getY()-1);

                    if (canMoveCrate) {
                        crateToMove.setY(crateToMove.getY()-1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setY(Player.getY()-1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    Player.setY(Player.getY()-1);
                    drawPlayer(player, false, gridPane);
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
                    boolean canMoveCrate = isCellEmpty(crateToMove.getX(), crateToMove.getY()+1);

                    if (canMoveCrate) {
                        crateToMove.setY(crateToMove.getY()+1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setY(Player.getY()+1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    Player.setY(Player.getY()+1);
                    drawPlayer(player, false, gridPane);
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
                    boolean canMoveCrate = isCellEmpty(crateToMove.getX()-1, crateToMove.getY());

                    if (canMoveCrate) {
                        crateToMove.setX(crateToMove.getX()-1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setX(Player.getX()-1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    Player.setX(Player.getX()-1);
                    drawPlayer(player, false, gridPane);
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
                    boolean canMoveCrate = isCellEmpty(crateToMove.getX()+1, crateToMove.getY());

                    if (canMoveCrate) {
                        crateToMove.setX(crateToMove.getX()+1);
                        drawCrate(crateToMove, false, gridPane);
                        Player.setX(Player.getX()+1);
                        drawPlayer(player, false, gridPane);
                    }
                } else {
                    Player.setX(Player.getX()+1);
                    drawPlayer(player, false, gridPane);
                }
            }
        }
    }

    private static void drawCrate(Crate crate, boolean crateStartingPosition, GridPane gridPane) {
        if (crateStartingPosition) {
            gridPane.add(crate, crate.getX(), crate.getY()); //TODO: jaky je rozdil mezi Crate a crate?
        } else {
            gridPane.getChildren().remove(crate);
            gridPane.add(crate, crate.getX(), crate.getY());

            if (winX == crate.getX() && winY == crate.getY()) {
                System.out.println("Game won!");
            }
        }
    }

    private static boolean isCellEmpty(int x, int y) {
        for (Crate crate : crates) {
            if (crate.getX() == x && crate.getY() == y) {
                return false;
            }
        }
        return true;
    }
}
