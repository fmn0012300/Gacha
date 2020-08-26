package databasetests;

import events.DataBaseManager;
import events.dataUnits.Cards;
import events.dataUnits.Person;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * This class tests all database manipulation
 */
public class DataBaseAccessTest {
    // test connect

    /**
     * Test if the class can connect to our database correctly
     */
    @Test
    public void testConnect() {
        try {
            DataBaseManager data = new DataBaseManager();
            System.out.println("DataBase connected without error");
            data.closeConnection();
        } catch (SQLException e) {
            assertTrue(false);
        }
    }

    // test getCards
    /**
     * Test if getCards could get all cards present in the database
     */
    @Test
    public void testGetCards() {
        DataBaseManager data = new DataBaseManager();
        Map<String, Set<Cards>> cards = data.getCards();
        int total = 0;
        for (String rarity: cards.keySet()) {
            total = total + cards.get(rarity).size();
        }
        assertEquals(total, 12);
        try {
            data.closeConnection();
        } catch (SQLException e) {
            assertTrue(false);
        }
    }

    // check create players
    /**
     * Test if create table is working as expected
     */
    @Test
    public void testCreatePlayer() throws SQLException {
        DataBaseManager data = new DataBaseManager();
        assertTrue(data.createPlayer(0));
        assertFalse(data.createPlayer(0));  // since same id detected
        data.clearTables();
        data.closeConnection();
    }

    // check if exists
    /**
     * Test if if exists work as promised
     * 1. if id exists
     */
    @Test
    public void testExists() throws SQLException{
        DataBaseManager data = new DataBaseManager();
        data.createPlayer(0);
        assertTrue(data.ifPlayerExists(0));
        assertFalse(data.ifPlayerExists(1));
        data.clearTables();
        try {
            data.closeConnection();
        } catch (SQLException e) {
            assertTrue(false);
        }
    }

    /**
     * Test if updatePlayer and getPlayer works as promised
     */
    @Test
    public void testPlayers() {
        DataBaseManager data = new DataBaseManager();
        data.createPlayer(0);
        Person player = new Person(0, 0);
        data.updatePlayer(player);
        Person playerCopy = data.getPlayer(0, 0);
        assertEquals(player, playerCopy);
        data.clearTables();
        try {
            data.closeConnection();
        } catch (SQLException e) {
            assertTrue(false);
        }
    }
}

