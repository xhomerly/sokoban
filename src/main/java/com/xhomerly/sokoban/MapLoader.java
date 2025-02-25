package com.xhomerly.sokoban;

import javafx.scene.layout.GridPane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class MapLoader {
    public static GridPane loadMap(String filePath) {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: black;");
        Player player;

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
                    Ground ground = new Ground(x, y);
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

                    Cell cell = createCell(type, x, y);
                    gridPane.add(cell, x, y);

                    if (isPlayer) {
                        player = new Player(x, y);
                        gridPane.add(player, x, y);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gridPane;
    }

    private static Cell createCell(String type, int x, int y) {
        return switch (type) {
            case "wall" -> new Wall(x, y);
            case "crate" -> new Crate(x, y);
            case "delivery_point" -> new DeliveryPoint(x, y);
            default -> new EmptyCell(x, y);
        };
    }
}
