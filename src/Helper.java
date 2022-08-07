package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Helper {
    public LinkedHashMap<String, Float> sortHashMapByValues (HashMap<String, Float> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Float> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues, Collections.reverseOrder());
        Collections.sort(mapKeys, Collections.reverseOrder());

        LinkedHashMap<String, Float> sortedMap =
                new LinkedHashMap<>();

        Iterator<Float> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Float val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Float comp1 = passedMap.get(key);
                Float comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public HashMap<String, Float> findUniversityWithScore() throws FileNotFoundException {
        // read data
        var USNews = new File("src/USNews.txt");
        var scanner = new Scanner(USNews);
        scanner.useDelimiter("\\Z");

        // split
        var splitPattern = Pattern.compile("View all [0-9]* photos");
        var universities = splitPattern.split(scanner.next());

        // search for pattern
        var namePattern = Pattern.compile("(.*[A-Z]*)(?=\\n[A-Z]*.*, [A-Z]*)");
        var scorePattern = Pattern.compile("(?<=(PEER ASSESSMENT SCORE\\n))([0-9]+.[0-9]+)");
        var uniNameScore = new HashMap<String, Float>();

        for (String uni : universities) {
            var uniName = namePattern.matcher(uni);
            var uniScore = scorePattern.matcher(uni);

            if (uniName.find() && uniScore.find())
                uniNameScore.put(uniName.group(1), Float.valueOf(uniScore.group(2)));
        }

        scanner.close();

        return uniNameScore;
    }

    public static boolean isFootNote(Character strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(String.valueOf(strNum));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void saveResults (LinkedHashMap<String, Float> results){
        try {
            var csvOutput = new FileWriter("src/result.csv");
            csvOutput.write("University, Score\n");
            for (var temp : results.entrySet()) {
                var lastIndex = temp.getKey().charAt(temp.getKey().length() - 1);

                if (isFootNote(lastIndex))
                    csvOutput.write(temp.getKey().substring(0, temp.getKey().length() - 2) +
                                        ", " +
                                        temp.getValue() + "\n");

                else
                    csvOutput.write(temp.getKey() +
                                        ", " +
                                        temp.getValue() + "\n");

            }
            csvOutput.close();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
