package queues;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import cs1c.SongEntry;


/**
 * Three objects of class Jukebox store the genre data set from three different type of genres 
 * favorites lounge and roadtrip 
 */
public class Jukebox {

	/** The favorite playlist. */
	private Queue<SongEntry> favoritePL;

	/** The lounge playlist. */
	private Queue<SongEntry> loungePL;

	/** The road trip playlist. */
	private Queue<SongEntry> roadTripPL;
	

	/**
	 * reads the songs of the requested file and accordingly stores the songs
	 * to the playlist
	 *
	 * @param requestFile the request file
	 * @param allSongs the all songs in the list
	 */
	public void fillPlaylists(String requestFile, SongEntry[] allSongs) {

		BufferedReader fileReader = null;
		try {

			favoritePL = new Queue<SongEntry>("favorites");
			loungePL = new Queue<SongEntry>("lounge");
			roadTripPL = new Queue<SongEntry>("road trip");

			String line = "";
			String type = "";
			String title = "";
			String[] list = null;
			fileReader = new BufferedReader(new FileReader(requestFile));

			while ((line = fileReader.readLine()) != null) {

				list = line.split(",");
				type = list[0];
				title = list[1];
				fill(type, allSongs, title);

			}
			fileReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error reading from file");
		}

	}

	/**
	 * checks for title of the songs and depending on the title it fills the 3
	 * different queues.If the song has more versions it stores the first found
	 * in allSongs file.
	 *
	 * @param type the genre  
	 * @param allSongs the all songs
	 * @param title the title
	 */
	public void fill(String type, SongEntry[] allSongs, String title) {
		int firstAvailable = 0;
		for (int i = 0; i < allSongs.length; i++) {
			SongEntry songs = allSongs[i];
			if (allSongs[i].getTitle().equals((title))) {

				if (type.equals("lounge")) {
					firstAvailable++;
					if (firstAvailable == 1)
						loungePL.enqueue(songs);
				} else if (type.equals("favorites")) {
					firstAvailable++;
					if (firstAvailable == 1)
						favoritePL.enqueue(songs);
				} else if (type.equals("road trip")) {
					firstAvailable++;
					if (firstAvailable == 1)
						roadTripPL.enqueue(songs);
				}
			}
		}
	}

	/**
	 * Gets the favorite playlist.
	 *
	 * @return the favorite playlist
	 */
	public Queue<SongEntry> getFavoritePL() {
		return favoritePL;
	}

	/**
	 * Gets the lounge playlist.
	 *
	 * @return the lounge playlist
	 */
	public Queue<SongEntry> getLoungePL() {
		return loungePL;
	}

	/**
	 * Gets the road trip playlist.
	 *
	 * @return the road trip playlist
	 */
	public Queue<SongEntry> getRoadTripPL() {
		return roadTripPL;
	}

}
