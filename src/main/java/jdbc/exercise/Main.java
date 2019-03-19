package jdbc.exercise;

import jdbc.exercise.beans.Admin;
import jdbc.exercise.tables.AdminManager;
import util.InputHelper;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("Starting application");
		
		ConnectionManager.getInstance().setDBType(DBType.HSQLDB);
		
		AdminManager.displayAllRows();

		int adminId = 0;
		try {
			adminId = InputHelper.getIntegerInput("Select a row to update: ");
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry");
		}

		Admin bean = AdminManager.getRow(adminId);
		if (bean == null) {
			System.err.println("Row not found");
			return;
		}
		
		String password = InputHelper.getInput("Enter new password: ");
		bean.setPassword(password);
		
		if (AdminManager.update(bean)) {
			System.out.println("Success!");
		} else
		{
			System.err.println("whoops!");
		}
		
		ConnectionManager.getInstance().close();
	}
}
