package events;

import events.dataUnits.Cards;
import events.dataUnits.Constants;
import events.dataUnits.Deck;
import events.dataUnits.Person;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class handles all database requests
 * It reads and writes from the database
 */
public class DataBaseManager {
    // database connection
    private Connection conn;

    // SQL statements
    private static final String CLEAR_TABLES= "DELETE FROM Player; DELETE FROM Cards; DELETE FROM Own;";
    private PreparedStatement clearTables;

    // GET all Cards' names and id and rarity
    private static final String GET_ALL_CARDS = "SELECT * FROM Cards WHERE rarity = ?;";
    private PreparedStatement getAllCards;

    // get player data
    private static final String GET_PLAYER_DATA = "SELECT P.draw_left as draw, C.cid as card_id, C.name as card_name, " +
            "C.rarity as rarity, O.amount as owned " +
            "FROM Player AS P, Cards AS C, Own as O " +
            "WHERE P.pid = ? AND P.pid = O.pid AND C.cid = O.cid";
    private PreparedStatement getPlayerData;

    // delete all cards owned
    private static final String DELETE_CARDS_OWNED = "DELETE FROM Own WHERE O.pid = ?";
    private PreparedStatement deleteCardsOwned;

    // add in cards
    private static final String ADD_IN_CARDS_FOR_PLAYER = "INSERT INTO Own(pid, cid, amount) " +
            "VALUES (?, ?, ?)";
    private PreparedStatement addInCardsFor;

    // register a player
    private static final String REGISTER = "INSERT INTO Player (pid, draw_left) " +
            "VALUES(?, 100)";
    private PreparedStatement register;

    // check if exists
    private static final String IF_PLAYER_EXISTS = "SELECT * FROM Player WHERE pid = ?";
    private PreparedStatement ifPlayerExists;

    public DataBaseManager() {
        try {
            connect();
            prepareStatement();
        } catch (Exception e) {
            System.exit(1);
        }
    }

    private void prepareStatement() throws SQLException{
        clearTables = conn.prepareStatement(CLEAR_TABLES);
        getAllCards = conn.prepareStatement(GET_ALL_CARDS);
        getPlayerData = conn.prepareStatement(GET_PLAYER_DATA);
        deleteCardsOwned = conn.prepareStatement(DELETE_CARDS_OWNED);
        addInCardsFor = conn.prepareStatement(ADD_IN_CARDS_FOR_PLAYER);
        register = conn.prepareStatement(REGISTER);
        ifPlayerExists = conn.prepareStatement(IF_PLAYER_EXISTS);
    }
    /**
     * Start and connect to the database storing cards, player data
     * @throws SQLException
     */
    private void connect() throws SQLException {
        // db parameters
        String url = "jdbc:sqlite:data.db";
        // create a connection to the database
        conn = DriverManager.getConnection(url);
        conn.setAutoCommit(true);
        System.out.println("Connection to SQLite has been established.");
    }

    /**
     * Close the connection to the database
     * Must be called before exiting
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     * Clear the data in all tables created
     *
     * WARNING! ONLY CALLED for testing purposes
     */
    public void  clearTables() {
        try {
            clearTables.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all cards stored in the database
     * @return a map mapping from rarity(String) to a set of Cards
     *          null if there's any error
     */
    public Map<String, Set<Cards>> getCards() {
        Map<String, Set<Cards>> result = new HashMap<>();
        try {
            for (int i = 0; i < Constants.RARITY.length; i++) {
                getAllCards.setString(1, Constants.RARITY[i]);
                ResultSet rst = getAllCards.executeQuery();
                Set<Cards> thisRarity = new HashSet<>();
                while (rst.next()) {
                    Cards temp = new Cards(rst.getInt("cid"), rst.getString("name"), rst.getString("rarity"));
                }
                result.put(Constants.RARITY[i], thisRarity);
                rst.close();
            }
        } catch (SQLException e) {
            return null;
        }
        return result;
    }

    /**
     * Get the player of the given id data from the database
     * @param id id of the player
     * @param priority the priority of the player
     * @return Person that is created and filled by the player's data,
     *          null if the person does not exist
     */
    public Person getPlayer(int id, int priority) {
        try {
            Person result = new Person(priority, id);
            getPlayerData.setInt(1, id);
            ResultSet rst = getPlayerData.executeQuery();
            Deck owned = new Deck();
            while (rst.next()) {
                result.setDraws(rst.getInt("draw"));
                Cards temp = new Cards(rst.getInt("card_id"), rst.getString("card_name"), rst.getString("rarity"));
                owned.addCard(temp, rst.getInt("owned"));
            }
            result.addCards(owned);
            rst.close();
            return result;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Update the current player's data in the database
     * @param player player to be updated
     * @return true iff the update is successful, otherwise false
     */
    public boolean updatePlayer(Person player) {
        // delete everything first
        try {
            deleteCardsOwned.setInt(1, player.getId());
            deleteCardsOwned.executeUpdate();
            Map<Cards, Integer> cards = player.getOwned().getDeck();
            for (Cards c : cards.keySet()) {
                addInCardsFor.setInt(1, player.getId());
                addInCardsFor.setInt(2, c.getId());
                addInCardsFor.setInt(3, cards.get(c));
                if (addInCardsFor.executeUpdate() != 1) {
                    return false;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if the given player exists in the database
     * @param id id of the player to be checked
     * @return true if it exists, otherwise false
     */
    public boolean ifPlayerExists(int id) {
        try {
            ifPlayerExists.setInt(1, id);
            ResultSet rst = ifPlayerExists.executeQuery();
            if (!rst.next()) {
                rst.close();
                return false;
            }
            rst.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * Create a Player with the given id
     * @param id
     * @return true iff it's successful
     */
    public boolean createPlayer(int id) {
        try {
            register.setInt(1, id);
            if (register.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}

