package src;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        var helper = new Helper();

        System.out.println("--> Finding universities ...");
        var universityScoreMap = helper.findUniversityWithScore();
        System.out.println("--> Sorting Results ...");
        var results = helper.sortHashMapByValues(universityScoreMap);
        System.out.println("--> Saving Results ...");
        helper.saveResults(results);
        System.out.println("--> Done");
    }
}
