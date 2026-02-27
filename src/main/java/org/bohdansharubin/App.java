package org.bohdansharubin;
import org.bohdansharubin.controllers.MainController;
import org.bohdansharubin.utils.Deserializer;
import org.bohdansharubin.utils.Serializer;
import org.bohdansharubin.models.*;
import org.bohdansharubin.services.ClothesService;
import org.bohdansharubin.views.MainView;
import org.bohdansharubin.views.View;

import java.io.IOException;
import java.util.*;
/**
 * Entry point of the Clothes application.
 * <p>
 * Responsible for:
 * <ul>
 *     <li>Initializing application components</li>
 *     <li>Loading saved clothes data from disk</li>
 *     <li>Starting the main application controller</li>
 *     <li>Saving data before application shutdown</li>
 * </ul>
 */
public class App {

    /**
     * Starts the application.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if saving data fails
     * @throws ClassNotFoundException if loaded data has invalid format
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello in Clothes App");
        System.out.println("Init clothes");

        final String inputFileName = "input.txt";
        Scanner input = new Scanner(System.in);

        List<Clothes> clothesList = loadFromDisk(inputFileName);

        final ClothesService service = new ClothesService(clothesList);
        final View view = new MainView();
        MainController controller = new MainController(view, service, input);

        controller.run();

        Serializer.saveObject(service.getClothesList(), inputFileName);
        input.close();
    }

    /**
     * Loads clothes data from file.
     *
     * @param filename name of the file containing serialized clothes list
     * @return list of clothes if file exists and is valid, otherwise empty list
     */
    public static List<Clothes> loadFromDisk(String filename) {
        try {
            List<Clothes> list = (List<Clothes>) Deserializer.loadObject(filename);
            System.out.println("Loaded " + list.size() + " clothes");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't find file " + filename);
        }
        return new ArrayList<>();
    }
}
