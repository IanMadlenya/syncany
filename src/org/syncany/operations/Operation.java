package org.syncany.operations;

import java.io.File;
import java.io.IOException;

import org.syncany.config.Config;
import org.syncany.database.Database;
import org.syncany.database.DatabaseDAO;
import org.syncany.database.DatabaseVersion;
import org.syncany.database.DatabaseXmlDAO;

public abstract class Operation {
	protected Config config;
	
	public Operation(Config config) {
		this.config = config;
	}	

	protected void saveLocalDatabase(Database db, File localDatabaseFile) throws IOException {
		saveLocalDatabase(db, null, null, localDatabaseFile);
	}	
	
	protected void saveLocalDatabase(Database db, DatabaseVersion fromVersion, DatabaseVersion toVersion, File localDatabaseFile) throws IOException {
		DatabaseDAO dao = new DatabaseXmlDAO(config.getTransformer());
		dao.save(db, fromVersion, toVersion, localDatabaseFile);
	}	
	
	public abstract OperationResult execute() throws Exception;
	
	public interface OperationOptions {
		// Marker interface for type safety
	}
	
	public interface OperationResult {
		// Marker interface for type safety
	}
}
