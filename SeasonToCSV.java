import java.io.*;
import java.util.ArrayList;

/**
 * Converts ArrayList of Season objects into a .csv file organized by
 * season, year, month, then day.
 * 
 * @author Noah Haselow
 */
public class SeasonToCSV {
	
	// List of seasons
	ArrayList<Season> seasons;
	
	public SeasonToCSV(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}
	
	/**
	 * Converts seasons list to .csv
	 * 
	 * @throws IOException on FileWriter error
	 */
	public void convert() throws IOException {
		// Relative location of .csv file
		String csv = "src/season_data";
		// DANGER: FileWriter clears previous file when writing.
		FileWriter writer = new FileWriter(csv, false);
		
		// Write Header
		writer.append(HEADER);
		writer.append(NEW_LINE);
		
		/** Iterate through each season */
		for(Season season : seasons) {
			/** For each game in the given season: */
			for(Game game : season.getAllGames()) {
				
				// Skip NULL games (should be none?). We don't throw an error
				// since missed games are in general arbitrary and shouldn't
				// affect how we fit the model.
				if(game == null) continue;
				
				// Write Game Classification
				writer.append(game.toString());
				writer.append(DELIMITER);
				
				/** Home Stats - traditional */
				for(Double stat : game.getHomeStats().trad_stat) {
					writer.append(stat.toString());
					writer.append(DELIMITER);
				}
				/** Home Stats - advanced */
				for(Double stat : game.getHomeStats().adv_stat) {
					writer.append(stat.toString());
					writer.append(DELIMITER);
				}
				
				// Write Away Team's Abbreviation, or null if error in reading html.
				if(game.getAwayTeam() == null)
					writer.append("NULL, ");
				else
					writer.append(game.getAwayTeam().toString() + DELIMITER + " ");
				
				/** Away Stats - traditional */
				for(Double stat : game.getAwayStats().trad_stat) {
					writer.append(stat.toString());
					writer.append(DELIMITER);
				}
				/** Away Stats - advanced */
				for(Double stat : game.getAwayStats().adv_stat) {
					writer.append(stat.toString());
					writer.append(DELIMITER);
				}
				
				writer.append(NEW_LINE);
				writer.flush();
			}
		}
		writer.append(NEW_LINE);
		
		writer.close();
	}
	
	/** Delimiter is comma because .csv */
	private final static char DELIMITER = ',';
	private final static char NEW_LINE = '\n';
	private final static String HEADER = "year, month, day, HOME TEAM,"
			+ "total minutes, fg, fg attempts, fg percentage, 3p, 3p attempts, 3p percentage, ft,"
			+ "ft attempts, ft percentage, orb, drb, ast, stl, blk, tov, pf, pts, "
			+ "ts, efg, three_ar, ft_ar, orb_p, drb_p, trb_p, ast_p, stl_p, "
			+ "blk_p, tov_p, usg_p, or, dr, AWAY TEAM, total minutes, fg, fg attempts, "
			+ "fg percentage, 3p, 3p attempts, 3p percentage, ft,"
			+ "ft attempts, ft percentage, orb, drb, ast, stl, blk, tov, pf, pts, "
			+ "ts, efg, three_ar, ft_ar, orb_p, drb_p, trb_p, ast_p, stl_p, "
			+ "blk_p, tov_p, usg_p, or, dr ";
}
