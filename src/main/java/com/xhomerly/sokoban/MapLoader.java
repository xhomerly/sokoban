package com.xhomerly.sokoban;

import javafx.scene.image.ImageView;
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

        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList rows = document.getElementsByTagName("row");

            for (int i = 0; i < rows.getLength(); i++) {
                Node rowNode = rows.item(i);
                if (rowNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element row = (Element) rowNode;
                    NodeList cols = row.getElementsByTagName("col");

                    for (int j = 0; j < cols.getLength(); j++) {
                        Element col = (Element) cols.item(j);
                        int value = Integer.parseInt(col.getTextContent().trim());
                        Cell cell = createCell(value, i, j);
                        gridPane.add(cell, j, i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gridPane;
    }

    private static Cell createCell(int value, int x, int y) {
        return switch (value) {
            case 1 -> new Wall(x, y);
            case 2 -> new Ground(x, y);
            case 3 -> new Crate(x, y);
            case 4 -> new DeliveryPoint(x, y);
            default -> new EmptyCell(x, y);
        };
    }
}
