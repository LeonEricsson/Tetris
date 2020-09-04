package se.liu.ida.leoer843.tddd30.tetris;

import java.util.*;

/**
 * This class sorts the hashmap highscorelist every time that it is changed.
 */
public class ScoreComparator {

    public ScoreComparator(){
    }

    public Map<String, Integer> sortByValue(Map<String, Integer> hm) {
	// Could not be safely removed
	List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

	//Tried List.sort but this way was more readable
	Collections.sort(list, new Comparator<>() {
	    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });

	Collections.reverse(list);

	Map<String, Integer> sortedHighscores = new LinkedHashMap<String, Integer>();
	for (Map.Entry<String, Integer> highscores : list) {
	    sortedHighscores.put(highscores.getKey(), highscores.getValue());
	}
	return sortedHighscores;
    }
}
