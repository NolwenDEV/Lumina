package be.nolwen.lumina.utilities.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;

import org.bukkit.Bukkit;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.enumeration.Query;

public class SQLManager {

	private Connection database;
	
	private boolean reconnecting;
	private final Queue<Runnable> pendingQueries = new ConcurrentLinkedDeque<>();
	
		// ---------------------------------------- \\
	
	public void connect() {
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
			try {
				if(!Main.getInstance().getDatabaseManager().isEnabled()) {
					Main.getInstance().getLogger().warning("⚠️ | Database is disabled in 'Configuration.yml', features using database have been disabled !");
					return;
				}
				
				if(isConnected()) {
					Main.getInstance().getLogger().warning("⚠️ | Database is already connected, connection attempt rejected !");
					return;
				}
				
				database = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s",
						Main.getInstance().getDatabaseManager().getHostname(), Main.getInstance().getDatabaseManager().getName()
				), Main.getInstance().getDatabaseManager().getUsername(), Main.getInstance().getDatabaseManager().getPassword());
				
				database.createStatement().execute(Query.CREATE_PLAYERS_TABLE.getQuery());
				database.createStatement().execute(Query.CREATE_HOMES_TABLE.getQuery());
				database.createStatement().execute(Query.CREATE_BACKPACK_TABLE.getQuery());
				
				flushPendingQueries();
				
				Main.getInstance().getLogger().info("✅ | Connection with the database successfully established !");
			} catch(SQLException exception) {
				Main.getInstance().getLogger().severe(String.format("⚠️ | An error has occurred while connecting to the database : %s", exception.getMessage()));
				reconnect();
			}
		});
	}
	
	public void disconnect() {
		try {
			if(isConnected()) database.close();
			Main.getInstance().getLogger().info("✅ | Database successfully disconnected, features using database are now disabled !");
		} catch(SQLException exception) {
			Main.getInstance().getLogger().severe(String.format("⚠️ | An error has occurred while disconnecting from the database : %s", exception.getMessage()));
		}
	}
	
		// ---------------------------------------- \\
	
	private void reconnect() {
		if (reconnecting) return;
        
        reconnecting = true;
        Main.getInstance().getLogger().warning("⚠️ | An error has occurred while retrieving the database, reconnecting...");
        
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), () -> {
        	reconnecting = false;
            connect();
        }, 20 * 5);
    }

    private void flushPendingQueries() {
    	Runnable task;
    	
        while ((task = pendingQueries.poll()) != null) { Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), task); }
    }
	
		// ---------------------------------------- \\
	
	public void executeQuery(Query query, Object... objects) {
		Runnable task = () -> {
			Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
				try(PreparedStatement statement = database.prepareStatement(query.getQuery())) {				
					int iteration = 1;
					for(Object object : objects) {
						statement.setObject(iteration, object);
						iteration++;
					}
					
					statement.executeUpdate();
				} catch(SQLException exception) {
					Main.getInstance().getLogger().severe(String.format(
							"⚠️ | An error has occurred while executing query '%s' : %s",
							query.name(), exception.getMessage()
					));
				}
			});
		};
		
		if(ensureConnection(task)) Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), task);
	}
	
	public void executeQuery(Query query, Consumer<List<Map<String, Object>>> callback, Object... objects) {
		Runnable task = () -> {
			Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
				try(PreparedStatement statement = database.prepareStatement(query.getQuery());) {
					int iteration = 1;
					for(Object object : objects) statement.setObject(iteration++, object);
					
					try(ResultSet result = statement.executeQuery()) {
						List<Map<String, Object>> rows = new ArrayList<>();
						
						int columnCount = result.getMetaData().getColumnCount();
						while(result.next()) {
							Map<String, Object> row = new HashMap<>();
							for(int index = 1; index <= columnCount; index++) { row.put(result.getMetaData().getColumnName(index), result.getObject(index)); }
							rows.add(row);
						}

		                callback.accept(rows);
					}
				} catch(SQLException exception) {
					Main.getInstance().getLogger().severe(String.format(
							"⚠️ | An error has occurred while retrieving result from query '%s' : %s",
							query.name(), exception.getMessage()
					));
				}
			});
		};
		
		if (ensureConnection(task)) Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), task);
	}
	
		// ---------------------------------------- \\
		
	private boolean ensureConnection(Runnable queryExecution) {
		if(isConnected()) return true;
		
		pendingQueries.add(queryExecution);
		reconnect();
		
		return false;
	}
	
	public boolean isConnected() {
		try {
			if(database != null && !database.isClosed() && database.isValid(5)) return true;
		} catch(SQLException exception) {
			Main.getInstance().getLogger().severe(String.format("⚠️ | An error has occurred while checking database status : %s", exception.getMessage()));
		}
		
		return false;
	}
	
}
