package se.liu.ida.leoer843.tddd30.tetris;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * The highscoreList class creates or reads in a highscoreList used in the tetris program
 */
public class HighscoreList {
    private Map<String ,Integer> scores;

    public HighscoreList(){
        scores = new HashMap<>();
    }

    //Auto boxing issue can not be resolved as the getter for score needs to be used when adding highscore to highscorelist.
    public void addHighscore(Highscore highscore){
        scores.put(highscore.getName(), highscore.getScore());
    }

    public Map<String, Integer> showHighscores(){
        return scores;
    }
    public void sortValues(){
        ScoreComparator scoreComparator = new ScoreComparator();
        scores = scoreComparator.sortByValue(scores);
    }
    public void saveHighscores(HighscoreList highscoreList) throws FileNotFoundException, IOException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String listAsJson = gson.toJson(highscoreList);
        File newTemp = new File("./highscores_temp.txt");
        //mkdirs is required to make directory for path
        newTemp.getParentFile().mkdirs();
        if(newTemp.exists() && newTemp.isDirectory()){
            throw new FileNotFoundException("File not found");
        }
        //There is no longer a try statment in this method so not sure what the issue is here
        PrintWriter printWriter = new PrintWriter(newTemp);
        printWriter.write(listAsJson);
        printWriter.close();
        File oldHighscores = new File("highscores.txt");
        if(oldHighscores.delete()){
            System.out.println(oldHighscores.getName() + " deleted");
        }
        else{
            System.out.println("Failed");
        }
        File newHighscores = new File(newTemp.getParent(), "highscores.txt");
        if(newHighscores.exists()){
            throw new IOException("File exists");
        }
        // Result not useful to me
        newTemp.renameTo(newHighscores);

    }

    public HighscoreList readHighscores() throws FileNotFoundException{
            Gson gson = new Gson();
            if(!Files.exists(Paths.get("highscores.txt"))){
                throw new FileNotFoundException("File not found");
            }
            FileReader fileReader = new FileReader("highscores.txt");
            HighscoreList highscoreList = gson.fromJson(fileReader, HighscoreList.class);
            return highscoreList;

        }
}
