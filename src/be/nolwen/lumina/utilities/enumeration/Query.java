package be.nolwen.lumina.utilities.enumeration;

public enum Query {
	
	CREATE_PLAYERS_TABLE(
			"CREATE TABLE IF NOT EXISTS PLAYERS ("
				+ "ID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "UUID VARCHAR(36),"
				+ "USERNAME VARCHAR(16),"
				+ "IP VARCHAR(45),"
				+ "PASSWORD VARCHAR(24),"
				+ "CONNECTED BOOLEAN,"
				+ "LAST_SEEN DATETIME,"
				+ "UNIQUE KEY UNIQUE_UUID (UUID)"
				+ ")"
	),
	
	CREATE_HOMES_TABLE(
			"CREATE TABLE IF NOT EXISTS HOMES ("
				+ "ID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "LINKED_ID INT,"
				+ "NAME VARCHAR(16),"
				+ "WORLD VARCHAR(64),"
				+ "X DOUBLE,"
				+ "Y DOUBLE,"
				+ "Z DOUBLE,"
				+ "YAW FLOAT,"
				+ "PITCH FLOAT,"
				+ "FOREIGN KEY (LINKED_ID) REFERENCES PLAYERS(ID) ON DELETE CASCADE,"
				+ "UNIQUE (LINKED_ID, NAME)"
				+ ")"
	),
	
	CREATE_BACKPACK_TABLE(
			"CREATE TABLE IF NOT EXISTS BACKPACKS ("
				+ "ID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "LINKED_ID INT,"
				+ "ITEM LONGTEXT,"
				+ "FOREIGN KEY (LINKED_ID) REFERENCES PLAYERS(ID) ON DELETE CASCADE,"
				+ "UNIQUE (LINKED_ID)"
				+ ")"
	),
	
			 // -------------------- \\
	
	UPDATE_PLAYER(
			"INSERT INTO PLAYERS (UUID, USERNAME, IP, PASSWORD, CONNECTED, LAST_SEEN) VALUES (?, ?, ?, ?, ?, NOW())"
				+ "ON DUPLICATE KEY UPDATE "
					+ "USERNAME = VALUES(USERNAME), "
					+ "IP = VALUES(IP), "
					+ "PASSWORD = IFNULL(VALUES(PASSWORD), PASSWORD), "
					+ "CONNECTED = IFNULL(VALUES(CONNECTED), CONNECTED), "
					+ "LAST_SEEN = NOW()"
	),
	RETRIEVE_PLAYER("SELECT * FROM PLAYERS WHERE UUID = ? OR USERNAME = ? OR IP = ?"),

	UPDATE_HOME(
			"INSERT INTO HOMES (LINKED_ID, NAME, WORLD, X, Y, Z, YAW, PITCH) "
				+ "SELECT ID, ?, ?, ?, ?, ?, ?, ? "
				+ "FROM PLAYERS WHERE UUID = ? OR USERNAME = ? "
				+ "ON DUPLICATE KEY UPDATE "
					+ "NAME = VALUES(NAME), "
					+ "WORLD = VALUES(WORLD), "
					+ "X = VALUES(X), " + "Y = VALUES(Y), " + "Z = VALUES(Z), "
					+ "YAW = VALUES(YAW), " + "PITCH = VALUES(PITCH)"
	),
	RETRIEVE_HOME(
			"SELECT home.NAME, home.WORLD, home.X, home.Y, home.Z, home.YAW, home.PITCH FROM HOMES home" + " "
					+ "JOIN PLAYERS player ON home.LINKED_ID = player.ID WHERE player.UUID = ? OR player.USERNAME = ?"
	),
	REMOVE_HOME("DELETE home FROM HOMES home JOIN PLAYERS player ON home.LINKED_ID = player.ID WHERE (player.UUID = ? or player.USERNAME = ?) and home.NAME = ?"),
	
	UPDATE_BACKPACK("INSERT INTO BACKPACKS (LINKED_ID, ITEM) SELECT ID, ? FROM PLAYERS WHERE UUID = ? OR USERNAME = ? ON DUPLICATE KEY UPDATE ITEM = VALUES(ITEM)"),
	RETRIEVE_BACKPACK(
			"SELECT backpack.ITEM FROM BACKPACKS backpack JOIN PLAYERS player ON backpack.LINKED_ID = player.ID WHERE player.UUID = ? OR player.USERNAME = ?"
	);
	
		// ---------------------------------------- \\
	
	private final String query;

	Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
