package com.semanticsquare.thrillio;

import java.util.List;

import com.semanticsquare.thrillio.bgjobs.WebPageDownloaderTask;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

public class Launch {

	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	private static void loadData() {
		// TODO Auto-generated method stub
		System.out.println("1. loading data");
		DataStore.loadData();
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
//		System.out.println("Printing Data");
//		printUserData();
//		printBookmarkData();
	}
	
	private static void printBookmarkData() {
		// TODO Auto-generated method stub
		for(List<Bookmark> b1: bookmarks ){
			for(Bookmark b: b1)
				System.out.println(b);
		}
		
	}

	private static void printUserData() {
		// TODO Auto-generated method stub
		for(User user: users ){
			System.out.println(user);
		}
		
	}

	private static void startBookmarking() {
//		System.out.println("\n2.Bookmarking ...");
		for(User user: users){
//			View.bookmark(user, bookmarks);
			View.browse(user, bookmarks);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadData();
		startBookmarking();
		runDownloaderJob();
	}
	
	private static void runDownloaderJob() {
		WebPageDownloaderTask task = new WebPageDownloaderTask(true);
		(new Thread(task)).start();
	}

	

}
